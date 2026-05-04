package org.apache.cordova.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LocalFilesystem extends Filesystem {
    private final Context context;

    public LocalFilesystem(String name, Context context, CordovaResourceApi resourceApi, File fsRoot) {
        super(Uri.fromFile(fsRoot).buildUpon().appendEncodedPath("").build(), name, resourceApi);
        this.context = context;
    }

    public String filesystemPathForFullPath(String fullPath) {
        return new File(this.rootUri.getPath(), fullPath).toString();
    }

    @Override // org.apache.cordova.file.Filesystem
    public String filesystemPathForURL(LocalFilesystemURL url) {
        return filesystemPathForFullPath(url.path);
    }

    private String fullPathForFilesystemPath(String absolutePath) {
        if (absolutePath == null || !absolutePath.startsWith(this.rootUri.getPath())) {
            return null;
        }
        return absolutePath.substring(this.rootUri.getPath().length() - 1);
    }

    @Override // org.apache.cordova.file.Filesystem
    public Uri toNativeUri(LocalFilesystemURL inputURL) {
        return nativeUriForFullPath(inputURL.path);
    }

    @Override // org.apache.cordova.file.Filesystem
    public LocalFilesystemURL toLocalUri(Uri inputURL) {
        if (!"file".equals(inputURL.getScheme())) {
            return null;
        }
        File f = new File(inputURL.getPath());
        Uri resolvedUri = Uri.fromFile(f);
        String rootUriNoTrailingSlash = this.rootUri.getEncodedPath();
        String rootUriNoTrailingSlash2 = rootUriNoTrailingSlash.substring(0, rootUriNoTrailingSlash.length() - 1);
        if (!resolvedUri.getEncodedPath().startsWith(rootUriNoTrailingSlash2)) {
            return null;
        }
        String subPath = resolvedUri.getEncodedPath().substring(rootUriNoTrailingSlash2.length());
        if (!subPath.isEmpty()) {
            subPath = subPath.substring(1);
        }
        Uri.Builder b = new Uri.Builder().scheme(LocalFilesystemURL.FILESYSTEM_PROTOCOL).authority("localhost").path(this.name);
        if (!subPath.isEmpty()) {
            b.appendEncodedPath(subPath);
        }
        if (f.isDirectory()) {
            b.appendEncodedPath("");
        }
        return LocalFilesystemURL.parse(b.build());
    }

    @Override // org.apache.cordova.file.Filesystem
    public LocalFilesystemURL URLforFilesystemPath(String path) {
        return localUrlforFullPath(fullPathForFilesystemPath(path));
    }

    @Override // org.apache.cordova.file.Filesystem
    public JSONObject getFileForLocalURL(LocalFilesystemURL inputURL, String path, JSONObject options, boolean directory) throws JSONException, FileExistsException, EncodingException, IOException, TypeMismatchException {
        LocalFilesystemURL requestedURL;
        boolean create = false;
        boolean exclusive = false;
        if (options != null && (create = options.optBoolean("create"))) {
            exclusive = options.optBoolean("exclusive");
        }
        if (path.contains(":")) {
            throw new EncodingException("This path has an invalid \":\" in it.");
        }
        if (directory && !path.endsWith("/")) {
            path = path + "/";
        }
        if (path.startsWith("/")) {
            requestedURL = localUrlforFullPath(normalizePath(path));
        } else {
            requestedURL = localUrlforFullPath(normalizePath(inputURL.path + "/" + path));
        }
        File fp = new File(filesystemPathForURL(requestedURL));
        if (create) {
            if (exclusive && fp.exists()) {
                throw new FileExistsException("create/exclusive fails");
            }
            if (directory) {
                fp.mkdir();
            } else {
                fp.createNewFile();
            }
            if (!fp.exists()) {
                throw new FileExistsException("create fails");
            }
        } else {
            if (!fp.exists()) {
                throw new FileNotFoundException("path does not exist");
            }
            if (directory) {
                if (fp.isFile()) {
                    throw new TypeMismatchException("path doesn't exist or is file");
                }
            } else if (fp.isDirectory()) {
                throw new TypeMismatchException("path doesn't exist or is directory");
            }
        }
        return makeEntryForURL(requestedURL);
    }

    @Override // org.apache.cordova.file.Filesystem
    public boolean removeFileAtLocalURL(LocalFilesystemURL inputURL) throws InvalidModificationException {
        File fp = new File(filesystemPathForURL(inputURL));
        if (fp.isDirectory() && fp.list().length > 0) {
            throw new InvalidModificationException("You can't delete a directory that is not empty.");
        }
        return fp.delete();
    }

    @Override // org.apache.cordova.file.Filesystem
    public boolean exists(LocalFilesystemURL inputURL) {
        File fp = new File(filesystemPathForURL(inputURL));
        return fp.exists();
    }

    @Override // org.apache.cordova.file.Filesystem
    public long getFreeSpaceInBytes() {
        return DirectoryManager.getFreeSpaceInBytes(this.rootUri.getPath());
    }

    @Override // org.apache.cordova.file.Filesystem
    public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL inputURL) throws FileExistsException {
        File directory = new File(filesystemPathForURL(inputURL));
        return removeDirRecursively(directory);
    }

    protected boolean removeDirRecursively(File directory) throws FileExistsException {
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                removeDirRecursively(file);
            }
        }
        if (!directory.delete()) {
            throw new FileExistsException("could not delete: " + directory.getName());
        }
        return true;
    }

    @Override // org.apache.cordova.file.Filesystem
    public LocalFilesystemURL[] listChildren(LocalFilesystemURL inputURL) throws FileNotFoundException {
        File fp = new File(filesystemPathForURL(inputURL));
        if (!fp.exists()) {
            throw new FileNotFoundException();
        }
        File[] files = fp.listFiles();
        if (files == null) {
            return null;
        }
        LocalFilesystemURL[] entries = new LocalFilesystemURL[files.length];
        for (int i = 0; i < files.length; i++) {
            entries[i] = URLforFilesystemPath(files[i].getPath());
        }
        return entries;
    }

    @Override // org.apache.cordova.file.Filesystem
    public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL inputURL) throws JSONException, FileNotFoundException {
        File file = new File(filesystemPathForURL(inputURL));
        if (!file.exists()) {
            throw new FileNotFoundException("File at " + inputURL.uri + " does not exist.");
        }
        JSONObject metadata = new JSONObject();
        try {
            metadata.put("size", file.isDirectory() ? 0L : file.length());
            metadata.put("type", this.resourceApi.getMimeType(Uri.fromFile(file)));
            metadata.put("name", file.getName());
            metadata.put("fullPath", inputURL.path);
            metadata.put("lastModifiedDate", file.lastModified());
            return metadata;
        } catch (JSONException e) {
            return null;
        }
    }

    private void copyFile(Filesystem srcFs, LocalFilesystemURL srcURL, File destFile, boolean move) throws InvalidModificationException, NoModificationAllowedException, IOException {
        String realSrcPath;
        if (move && (realSrcPath = srcFs.filesystemPathForURL(srcURL)) != null) {
            File srcFile = new File(realSrcPath);
            if (srcFile.renameTo(destFile)) {
                return;
            }
        }
        CordovaResourceApi.OpenForReadResult offr = this.resourceApi.openForRead(srcFs.toNativeUri(srcURL));
        copyResource(offr, new FileOutputStream(destFile));
        if (move) {
            srcFs.removeFileAtLocalURL(srcURL);
        }
    }

    private void copyDirectory(Filesystem srcFs, LocalFilesystemURL srcURL, File dstDir, boolean move) throws InvalidModificationException, FileExistsException, NoModificationAllowedException, IOException {
        String realSrcPath;
        if (move && (realSrcPath = srcFs.filesystemPathForURL(srcURL)) != null) {
            File srcDir = new File(realSrcPath);
            if (dstDir.exists()) {
                if (dstDir.list().length > 0) {
                    throw new InvalidModificationException("directory is not empty");
                }
                dstDir.delete();
            }
            if (srcDir.renameTo(dstDir)) {
                return;
            }
        }
        if (dstDir.exists()) {
            if (dstDir.list().length > 0) {
                throw new InvalidModificationException("directory is not empty");
            }
        } else if (!dstDir.mkdir()) {
            throw new NoModificationAllowedException("Couldn't create the destination directory");
        }
        LocalFilesystemURL[] children = srcFs.listChildren(srcURL);
        for (LocalFilesystemURL childLocalUrl : children) {
            File target = new File(dstDir, new File(childLocalUrl.path).getName());
            if (childLocalUrl.isDirectory) {
                copyDirectory(srcFs, childLocalUrl, target, false);
            } else {
                copyFile(srcFs, childLocalUrl, target, false);
            }
        }
        if (move) {
            srcFs.recursiveRemoveFileAtLocalURL(srcURL);
        }
    }

    @Override // org.apache.cordova.file.Filesystem
    public JSONObject copyFileToURL(LocalFilesystemURL destURL, String newName, Filesystem srcFs, LocalFilesystemURL srcURL, boolean move) throws JSONException, InvalidModificationException, FileExistsException, NoModificationAllowedException, IOException {
        String newParent = filesystemPathForURL(destURL);
        File destinationDir = new File(newParent);
        if (!destinationDir.exists()) {
            throw new FileNotFoundException("The source does not exist");
        }
        LocalFilesystemURL destinationURL = makeDestinationURL(newName, srcURL, destURL, srcURL.isDirectory);
        Uri dstNativeUri = toNativeUri(destinationURL);
        Uri srcNativeUri = srcFs.toNativeUri(srcURL);
        if (dstNativeUri.equals(srcNativeUri)) {
            throw new InvalidModificationException("Can't copy onto itself");
        }
        if (move && !srcFs.canRemoveFileAtLocalURL(srcURL)) {
            throw new InvalidModificationException("Source URL is read-only (cannot move)");
        }
        File destFile = new File(dstNativeUri.getPath());
        if (destFile.exists()) {
            if (!srcURL.isDirectory && destFile.isDirectory()) {
                throw new InvalidModificationException("Can't copy/move a file to an existing directory");
            }
            if (srcURL.isDirectory && destFile.isFile()) {
                throw new InvalidModificationException("Can't copy/move a directory to an existing file");
            }
        }
        if (srcURL.isDirectory) {
            if (dstNativeUri.toString().startsWith(srcNativeUri.toString() + '/')) {
                throw new InvalidModificationException("Can't copy directory into itself");
            }
            copyDirectory(srcFs, srcURL, destFile, move);
        } else {
            copyFile(srcFs, srcURL, destFile, move);
        }
        return makeEntryForURL(destinationURL);
    }

    @Override // org.apache.cordova.file.Filesystem
    public long writeToFileAtURL(LocalFilesystemURL inputURL, String data, int offset, boolean isBinary) throws NoModificationAllowedException, IOException {
        byte[] rawData;
        boolean append = false;
        if (offset > 0) {
            truncateFileAtURL(inputURL, offset);
            append = true;
        }
        if (isBinary) {
            rawData = Base64.decode(data, 0);
        } else {
            rawData = data.getBytes();
        }
        ByteArrayInputStream in = new ByteArrayInputStream(rawData);
        try {
            byte[] buff = new byte[rawData.length];
            String absolutePath = filesystemPathForURL(inputURL);
            FileOutputStream out = new FileOutputStream(absolutePath, append);
            try {
                in.read(buff, 0, buff.length);
                out.write(buff, 0, rawData.length);
                out.flush();
                out.close();
                if (isPublicDirectory(absolutePath)) {
                    broadcastNewFile(Uri.fromFile(new File(absolutePath)));
                }
                return rawData.length;
            } catch (Throwable th) {
                out.close();
                throw th;
            }
        } catch (NullPointerException e) {
            NoModificationAllowedException realException = new NoModificationAllowedException(inputURL.toString());
            throw realException;
        }
    }

    private boolean isPublicDirectory(String absolutePath) {
        if (Build.VERSION.SDK_INT >= 21) {
            for (File f : this.context.getExternalMediaDirs()) {
                if (f != null && absolutePath.startsWith(f.getAbsolutePath())) {
                    return true;
                }
            }
        }
        String extPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        return absolutePath.startsWith(extPath);
    }

    private void broadcastNewFile(Uri nativeUri) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", nativeUri);
        this.context.sendBroadcast(intent);
    }

    @Override // org.apache.cordova.file.Filesystem
    public long truncateFileAtURL(LocalFilesystemURL inputURL, long size) throws IOException {
        File file = new File(filesystemPathForURL(inputURL));
        if (!file.exists()) {
            throw new FileNotFoundException("File at " + inputURL.uri + " does not exist.");
        }
        RandomAccessFile raf = new RandomAccessFile(filesystemPathForURL(inputURL), "rw");
        try {
            if (raf.length() >= size) {
                FileChannel channel = raf.getChannel();
                channel.truncate(size);
            } else {
                size = raf.length();
            }
            return size;
        } finally {
            raf.close();
        }
    }

    @Override // org.apache.cordova.file.Filesystem
    public boolean canRemoveFileAtLocalURL(LocalFilesystemURL inputURL) {
        String path = filesystemPathForURL(inputURL);
        File file = new File(path);
        return file.exists();
    }

    private static void copyResource(CordovaResourceApi.OpenForReadResult input, OutputStream outputStream) throws IOException {
        try {
            InputStream inputStream = input.inputStream;
            if ((inputStream instanceof FileInputStream) && (outputStream instanceof FileOutputStream)) {
                FileChannel inChannel = ((FileInputStream) input.inputStream).getChannel();
                FileChannel outChannel = ((FileOutputStream) outputStream).getChannel();
                long offset = 0;
                long length = input.length;
                if (input.assetFd != null) {
                    offset = input.assetFd.getStartOffset();
                }
                inChannel.position(offset);
                outChannel.transferFrom(inChannel, 0L, length);
            } else {
                byte[] buffer = new byte[8192];
                while (true) {
                    int bytesRead = inputStream.read(buffer, 0, 8192);
                    if (bytesRead <= 0) {
                        break;
                    } else {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        } finally {
            input.inputStream.close();
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
