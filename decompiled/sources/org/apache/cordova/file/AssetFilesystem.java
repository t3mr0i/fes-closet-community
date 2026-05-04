package org.apache.cordova.file;

import android.content.res.AssetManager;
import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AssetFilesystem extends Filesystem {
    private static Map<String, Long> lengthCache;
    private static Map<String, String[]> listCache;
    private static boolean listCacheFromFile;
    private static Object listCacheLock = new Object();
    private final AssetManager assetManager;

    /* JADX WARN: Removed duplicated region for block: B:14:0x0032 A[Catch: all -> 0x0061, TryCatch #1 {, blocks: (B:4:0x0003, B:10:0x002a, B:12:0x002e, B:14:0x0032, B:34:0x005d, B:35:0x0060, B:28:0x0054, B:22:0x004b, B:15:0x0040), top: B:48:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void lazyInitCaches() {
        /*
            r6 = this;
            java.lang.Object r4 = org.apache.cordova.file.AssetFilesystem.listCacheLock
            monitor-enter(r4)
            java.util.Map<java.lang.String, java.lang.String[]> r3 = org.apache.cordova.file.AssetFilesystem.listCache     // Catch: java.lang.Throwable -> L61
            if (r3 != 0) goto L40
            r1 = 0
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch: java.lang.ClassNotFoundException -> L45 java.io.IOException -> L51 java.lang.Throwable -> L5a
            android.content.res.AssetManager r3 = r6.assetManager     // Catch: java.lang.ClassNotFoundException -> L45 java.io.IOException -> L51 java.lang.Throwable -> L5a
            java.lang.String r5 = "cdvasset.manifest"
            java.io.InputStream r3 = r3.open(r5)     // Catch: java.lang.ClassNotFoundException -> L45 java.io.IOException -> L51 java.lang.Throwable -> L5a
            r2.<init>(r3)     // Catch: java.lang.ClassNotFoundException -> L45 java.io.IOException -> L51 java.lang.Throwable -> L5a
            java.lang.Object r3 = r2.readObject()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69 java.lang.ClassNotFoundException -> L6c
            java.util.Map r3 = (java.util.Map) r3     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69 java.lang.ClassNotFoundException -> L6c
            org.apache.cordova.file.AssetFilesystem.listCache = r3     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69 java.lang.ClassNotFoundException -> L6c
            java.lang.Object r3 = r2.readObject()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69 java.lang.ClassNotFoundException -> L6c
            java.util.Map r3 = (java.util.Map) r3     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69 java.lang.ClassNotFoundException -> L6c
            org.apache.cordova.file.AssetFilesystem.lengthCache = r3     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69 java.lang.ClassNotFoundException -> L6c
            r3 = 1
            org.apache.cordova.file.AssetFilesystem.listCacheFromFile = r3     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69 java.lang.ClassNotFoundException -> L6c
            if (r2 == 0) goto L6f
            r2.close()     // Catch: java.io.IOException -> L42 java.lang.Throwable -> L61
            r1 = r2
        L2e:
            java.util.Map<java.lang.String, java.lang.String[]> r3 = org.apache.cordova.file.AssetFilesystem.listCache     // Catch: java.lang.Throwable -> L61
            if (r3 != 0) goto L40
            java.lang.String r3 = "AssetFilesystem"
            java.lang.String r5 = "Asset manifest not found. Recursive copies and directory listing will be slow."
            android.util.Log.w(r3, r5)     // Catch: java.lang.Throwable -> L61
            java.util.HashMap r3 = new java.util.HashMap     // Catch: java.lang.Throwable -> L61
            r3.<init>()     // Catch: java.lang.Throwable -> L61
            org.apache.cordova.file.AssetFilesystem.listCache = r3     // Catch: java.lang.Throwable -> L61
        L40:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L61
            return
        L42:
            r3 = move-exception
            r1 = r2
            goto L2e
        L45:
            r0 = move-exception
        L46:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L5a
            if (r1 == 0) goto L2e
            r1.close()     // Catch: java.io.IOException -> L4f java.lang.Throwable -> L61
            goto L2e
        L4f:
            r3 = move-exception
            goto L2e
        L51:
            r3 = move-exception
        L52:
            if (r1 == 0) goto L2e
            r1.close()     // Catch: java.io.IOException -> L58 java.lang.Throwable -> L61
            goto L2e
        L58:
            r3 = move-exception
            goto L2e
        L5a:
            r3 = move-exception
        L5b:
            if (r1 == 0) goto L60
            r1.close()     // Catch: java.lang.Throwable -> L61 java.io.IOException -> L64
        L60:
            throw r3     // Catch: java.lang.Throwable -> L61
        L61:
            r3 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L61
            throw r3
        L64:
            r5 = move-exception
            goto L60
        L66:
            r3 = move-exception
            r1 = r2
            goto L5b
        L69:
            r3 = move-exception
            r1 = r2
            goto L52
        L6c:
            r0 = move-exception
            r1 = r2
            goto L46
        L6f:
            r1 = r2
            goto L2e
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.file.AssetFilesystem.lazyInitCaches():void");
    }

    private String[] listAssets(String assetPath) throws IOException {
        if (assetPath.startsWith("/")) {
            assetPath = assetPath.substring(1);
        }
        if (assetPath.endsWith("/")) {
            assetPath = assetPath.substring(0, assetPath.length() - 1);
        }
        lazyInitCaches();
        String[] ret = listCache.get(assetPath);
        if (ret == null) {
            if (listCacheFromFile) {
                return new String[0];
            }
            String[] ret2 = this.assetManager.list(assetPath);
            listCache.put(assetPath, ret2);
            return ret2;
        }
        return ret;
    }

    private long getAssetSize(String assetPath) throws IOException {
        if (assetPath.startsWith("/")) {
            assetPath = assetPath.substring(1);
        }
        lazyInitCaches();
        if (lengthCache != null) {
            Long ret = lengthCache.get(assetPath);
            if (ret == null) {
                throw new FileNotFoundException("Asset not found: " + assetPath);
            }
            return ret.longValue();
        }
        CordovaResourceApi.OpenForReadResult offr = null;
        try {
            try {
                offr = this.resourceApi.openForRead(nativeUriForFullPath(assetPath));
                long length = offr.length;
                if (length < 0) {
                    length = offr.inputStream.available();
                }
                if (offr == null) {
                    return length;
                }
                try {
                    offr.inputStream.close();
                    return length;
                } catch (IOException e) {
                    return length;
                }
            } catch (IOException e2) {
                throw new FileNotFoundException("File not found: " + assetPath);
            }
        } catch (Throwable th) {
            if (offr != null) {
                try {
                    offr.inputStream.close();
                } catch (IOException e3) {
                }
            }
            throw th;
        }
    }

    public AssetFilesystem(AssetManager assetManager, CordovaResourceApi resourceApi) {
        super(Uri.parse("file:///android_asset/"), "assets", resourceApi);
        this.assetManager = assetManager;
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
        if (isDirectory(subPath) || inputURL.getPath().endsWith("/")) {
            b.appendEncodedPath("");
        }
        return LocalFilesystemURL.parse(b.build());
    }

    private boolean isDirectory(String assetPath) {
        try {
            return listAssets(assetPath).length != 0;
        } catch (IOException e) {
            return false;
        }
    }

    @Override // org.apache.cordova.file.Filesystem
    public LocalFilesystemURL[] listChildren(LocalFilesystemURL inputURL) throws FileNotFoundException {
        String pathNoSlashes = inputURL.path.substring(1);
        if (pathNoSlashes.endsWith("/")) {
            pathNoSlashes = pathNoSlashes.substring(0, pathNoSlashes.length() - 1);
        }
        try {
            String[] files = listAssets(pathNoSlashes);
            LocalFilesystemURL[] entries = new LocalFilesystemURL[files.length];
            for (int i = 0; i < files.length; i++) {
                entries[i] = localUrlforFullPath(new File(inputURL.path, files[i]).getPath());
            }
            return entries;
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    @Override // org.apache.cordova.file.Filesystem
    public JSONObject getFileForLocalURL(LocalFilesystemURL inputURL, String path, JSONObject options, boolean directory) throws JSONException, FileExistsException, EncodingException, TypeMismatchException, IOException {
        LocalFilesystemURL requestedURL;
        if (options != null && options.optBoolean("create")) {
            throw new UnsupportedOperationException("Assets are read-only");
        }
        if (directory && !path.endsWith("/")) {
            path = path + "/";
        }
        if (path.startsWith("/")) {
            requestedURL = localUrlforFullPath(normalizePath(path));
        } else {
            requestedURL = localUrlforFullPath(normalizePath(inputURL.path + "/" + path));
        }
        getFileMetadataForLocalURL(requestedURL);
        boolean isDir = isDirectory(requestedURL.path);
        if (directory && !isDir) {
            throw new TypeMismatchException("path doesn't exist or is file");
        }
        if (!directory && isDir) {
            throw new TypeMismatchException("path doesn't exist or is directory");
        }
        return makeEntryForURL(requestedURL);
    }

    @Override // org.apache.cordova.file.Filesystem
    public JSONObject getFileMetadataForLocalURL(LocalFilesystemURL inputURL) throws JSONException, FileNotFoundException {
        JSONObject metadata = new JSONObject();
        long size = inputURL.isDirectory ? 0L : getAssetSize(inputURL.path);
        try {
            metadata.put("size", size);
            metadata.put("type", inputURL.isDirectory ? "text/directory" : this.resourceApi.getMimeType(toNativeUri(inputURL)));
            metadata.put("name", new File(inputURL.path).getName());
            metadata.put("fullPath", inputURL.path);
            metadata.put("lastModifiedDate", 0);
            return metadata;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override // org.apache.cordova.file.Filesystem
    public boolean canRemoveFileAtLocalURL(LocalFilesystemURL inputURL) {
        return false;
    }

    @Override // org.apache.cordova.file.Filesystem
    long writeToFileAtURL(LocalFilesystemURL inputURL, String data, int offset, boolean isBinary) throws NoModificationAllowedException, IOException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    @Override // org.apache.cordova.file.Filesystem
    long truncateFileAtURL(LocalFilesystemURL inputURL, long size) throws NoModificationAllowedException, IOException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    @Override // org.apache.cordova.file.Filesystem
    String filesystemPathForURL(LocalFilesystemURL url) {
        return null;
    }

    @Override // org.apache.cordova.file.Filesystem
    LocalFilesystemURL URLforFilesystemPath(String path) {
        return null;
    }

    @Override // org.apache.cordova.file.Filesystem
    boolean removeFileAtLocalURL(LocalFilesystemURL inputURL) throws InvalidModificationException, NoModificationAllowedException {
        throw new NoModificationAllowedException("Assets are read-only");
    }

    @Override // org.apache.cordova.file.Filesystem
    boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL inputURL) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Assets are read-only");
    }
}
