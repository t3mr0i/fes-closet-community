package org.apache.cordova.file;

import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class Filesystem {
    public final String name;
    protected final CordovaResourceApi resourceApi;
    private JSONObject rootEntry;
    protected final Uri rootUri;

    public interface ReadFileCallback {
        void handleData(InputStream inputStream, String str) throws IOException;
    }

    abstract LocalFilesystemURL URLforFilesystemPath(String str);

    abstract boolean canRemoveFileAtLocalURL(LocalFilesystemURL localFilesystemURL);

    abstract String filesystemPathForURL(LocalFilesystemURL localFilesystemURL);

    abstract JSONObject getFileForLocalURL(LocalFilesystemURL localFilesystemURL, String str, JSONObject jSONObject, boolean z) throws JSONException, FileExistsException, EncodingException, IOException, TypeMismatchException;

    abstract JSONObject getFileMetadataForLocalURL(LocalFilesystemURL localFilesystemURL) throws FileNotFoundException;

    abstract LocalFilesystemURL[] listChildren(LocalFilesystemURL localFilesystemURL) throws FileNotFoundException;

    abstract boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL localFilesystemURL) throws FileExistsException, NoModificationAllowedException;

    abstract boolean removeFileAtLocalURL(LocalFilesystemURL localFilesystemURL) throws InvalidModificationException, NoModificationAllowedException;

    public abstract LocalFilesystemURL toLocalUri(Uri uri);

    public abstract Uri toNativeUri(LocalFilesystemURL localFilesystemURL);

    abstract long truncateFileAtURL(LocalFilesystemURL localFilesystemURL, long j) throws NoModificationAllowedException, IOException;

    abstract long writeToFileAtURL(LocalFilesystemURL localFilesystemURL, String str, int i, boolean z) throws NoModificationAllowedException, IOException;

    public Filesystem(Uri rootUri, String name, CordovaResourceApi resourceApi) {
        this.rootUri = rootUri;
        this.name = name;
        this.resourceApi = resourceApi;
    }

    public static JSONObject makeEntryForURL(LocalFilesystemURL inputURL, Uri nativeURL) throws JSONException {
        try {
            String path = inputURL.path;
            int end = path.endsWith("/") ? 1 : 0;
            String[] parts = path.substring(0, path.length() - end).split("/+");
            String fileName = parts[parts.length - 1];
            JSONObject entry = new JSONObject();
            entry.put("isFile", !inputURL.isDirectory);
            entry.put("isDirectory", inputURL.isDirectory);
            entry.put("name", fileName);
            entry.put("fullPath", path);
            entry.put("filesystemName", inputURL.fsName);
            entry.put("filesystem", "temporary".equals(inputURL.fsName) ? 0 : 1);
            String nativeUrlStr = nativeURL.toString();
            if (inputURL.isDirectory && !nativeUrlStr.endsWith("/")) {
                nativeUrlStr = nativeUrlStr + "/";
            }
            entry.put("nativeURL", nativeUrlStr);
            return entry;
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public JSONObject makeEntryForURL(LocalFilesystemURL inputURL) {
        Uri nativeUri = toNativeUri(inputURL);
        if (nativeUri == null) {
            return null;
        }
        return makeEntryForURL(inputURL, nativeUri);
    }

    public JSONObject makeEntryForNativeUri(Uri nativeUri) {
        LocalFilesystemURL inputUrl = toLocalUri(nativeUri);
        if (inputUrl == null) {
            return null;
        }
        return makeEntryForURL(inputUrl, nativeUri);
    }

    public JSONObject getEntryForLocalURL(LocalFilesystemURL inputURL) throws IOException {
        return makeEntryForURL(inputURL);
    }

    public JSONObject makeEntryForFile(File file) {
        return makeEntryForNativeUri(Uri.fromFile(file));
    }

    public final JSONArray readEntriesAtLocalURL(LocalFilesystemURL inputURL) throws FileNotFoundException {
        LocalFilesystemURL[] children = listChildren(inputURL);
        JSONArray entries = new JSONArray();
        if (children != null) {
            for (LocalFilesystemURL url : children) {
                entries.put(makeEntryForURL(url));
            }
        }
        return entries;
    }

    public Uri getRootUri() {
        return this.rootUri;
    }

    public boolean exists(LocalFilesystemURL inputURL) {
        try {
            getFileMetadataForLocalURL(inputURL);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public Uri nativeUriForFullPath(String fullPath) {
        if (fullPath == null) {
            return null;
        }
        String encodedPath = Uri.fromFile(new File(fullPath)).getEncodedPath();
        if (encodedPath.startsWith("/")) {
            encodedPath = encodedPath.substring(1);
        }
        Uri ret = this.rootUri.buildUpon().appendEncodedPath(encodedPath).build();
        return ret;
    }

    public LocalFilesystemURL localUrlforFullPath(String fullPath) {
        Uri nativeUri = nativeUriForFullPath(fullPath);
        if (nativeUri != null) {
            return toLocalUri(nativeUri);
        }
        return null;
    }

    protected static String normalizePath(String rawPath) {
        boolean isAbsolutePath = rawPath.startsWith("/");
        if (isAbsolutePath) {
            rawPath = rawPath.replaceFirst("/+", "");
        }
        ArrayList<String> components = new ArrayList<>(Arrays.asList(rawPath.split("/+")));
        int index = 0;
        while (index < components.size()) {
            if (components.get(index).equals("..")) {
                components.remove(index);
                if (index > 0) {
                    components.remove(index - 1);
                    index--;
                }
            }
            index++;
        }
        StringBuilder normalizedPath = new StringBuilder();
        Iterator<String> it = components.iterator();
        while (it.hasNext()) {
            String component = it.next();
            normalizedPath.append("/");
            normalizedPath.append(component);
        }
        return isAbsolutePath ? normalizedPath.toString() : normalizedPath.toString().substring(1);
    }

    public long getFreeSpaceInBytes() {
        return 0L;
    }

    public JSONObject getRootEntry() {
        if (this.rootEntry == null) {
            this.rootEntry = makeEntryForNativeUri(this.rootUri);
        }
        return this.rootEntry;
    }

    public JSONObject getParentForLocalURL(LocalFilesystemURL inputURL) throws IOException {
        Uri parentUri = inputURL.uri;
        String parentPath = new File(inputURL.uri.getPath()).getParent();
        if (!"/".equals(parentPath)) {
            parentUri = inputURL.uri.buildUpon().path(parentPath + '/').build();
        }
        return getEntryForLocalURL(LocalFilesystemURL.parse(parentUri));
    }

    protected LocalFilesystemURL makeDestinationURL(String newName, LocalFilesystemURL srcURL, LocalFilesystemURL destURL, boolean isDirectory) {
        String newDest;
        if ("null".equals(newName) || "".equals(newName)) {
            newName = srcURL.uri.getLastPathSegment();
        }
        String newDest2 = destURL.uri.toString();
        if (newDest2.endsWith("/")) {
            newDest = newDest2 + newName;
        } else {
            newDest = newDest2 + "/" + newName;
        }
        if (isDirectory) {
            newDest = newDest + '/';
        }
        return LocalFilesystemURL.parse(newDest);
    }

    public JSONObject copyFileToURL(LocalFilesystemURL destURL, String newName, Filesystem srcFs, LocalFilesystemURL srcURL, boolean move) throws JSONException, InvalidModificationException, FileExistsException, NoModificationAllowedException, IOException {
        if (move && !srcFs.canRemoveFileAtLocalURL(srcURL)) {
            throw new NoModificationAllowedException("Cannot move file at source URL");
        }
        LocalFilesystemURL destination = makeDestinationURL(newName, srcURL, destURL, srcURL.isDirectory);
        Uri srcNativeUri = srcFs.toNativeUri(srcURL);
        CordovaResourceApi.OpenForReadResult ofrr = this.resourceApi.openForRead(srcNativeUri);
        try {
            OutputStream os = getOutputStreamForURL(destination);
            this.resourceApi.copyResource(ofrr, os);
            if (move) {
                srcFs.removeFileAtLocalURL(srcURL);
            }
            return getEntryForLocalURL(destination);
        } catch (IOException e) {
            ofrr.inputStream.close();
            throw e;
        }
    }

    public OutputStream getOutputStreamForURL(LocalFilesystemURL inputURL) throws IOException {
        return this.resourceApi.openOutputStream(toNativeUri(inputURL));
    }

    public void readFileAtURL(LocalFilesystemURL inputURL, long start, long end, ReadFileCallback readFileCallback) throws IOException {
        CordovaResourceApi.OpenForReadResult ofrr = this.resourceApi.openForRead(toNativeUri(inputURL));
        if (end < 0) {
            end = ofrr.length;
        }
        long numBytesToRead = end - start;
        if (start > 0) {
            try {
                ofrr.inputStream.skip(start);
            } finally {
                ofrr.inputStream.close();
            }
        }
        InputStream inputStream = ofrr.inputStream;
        if (end < ofrr.length) {
            inputStream = new LimitedInputStream(inputStream, numBytesToRead);
        }
        readFileCallback.handleData(inputStream, ofrr.mimeType);
    }

    protected class LimitedInputStream extends FilterInputStream {
        long numBytesToRead;

        public LimitedInputStream(InputStream in, long numBytesToRead) {
            super(in);
            this.numBytesToRead = numBytesToRead;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            if (this.numBytesToRead <= 0) {
                return -1;
            }
            this.numBytesToRead--;
            return this.in.read();
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
            if (this.numBytesToRead <= 0) {
                return -1;
            }
            int bytesToRead = byteCount;
            if (byteCount > this.numBytesToRead) {
                bytesToRead = (int) this.numBytesToRead;
            }
            int numBytesRead = this.in.read(buffer, byteOffset, bytesToRead);
            this.numBytesToRead -= numBytesRead;
            return numBytesRead;
        }
    }
}
