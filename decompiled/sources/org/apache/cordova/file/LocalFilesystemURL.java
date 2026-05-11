package org.apache.cordova.file;

import android.net.Uri;

/* loaded from: classes.dex */
public class LocalFilesystemURL {
    public static final String FILESYSTEM_PROTOCOL = "cdvfile";
    public final String fsName;
    public final boolean isDirectory;
    public final String path;
    public final Uri uri;

    private LocalFilesystemURL(Uri uri, String fsName, String fsPath, boolean isDirectory) {
        this.uri = uri;
        this.fsName = fsName;
        this.path = fsPath;
        this.isDirectory = isDirectory;
    }

    public static LocalFilesystemURL parse(Uri uri) {
        int firstSlashIdx;
        if (!FILESYSTEM_PROTOCOL.equals(uri.getScheme())) {
            return null;
        }
        String path = uri.getPath();
        if (path.length() < 1 || (firstSlashIdx = path.indexOf(47, 1)) < 0) {
            return null;
        }
        String fsName = path.substring(1, firstSlashIdx);
        String path2 = path.substring(firstSlashIdx);
        boolean isDirectory = path2.charAt(path2.length() + (-1)) == '/';
        return new LocalFilesystemURL(uri, fsName, path2, isDirectory);
    }

    public static LocalFilesystemURL parse(String uri) {
        return parse(Uri.parse(uri));
    }

    public String toString() {
        return this.uri.toString();
    }
}
