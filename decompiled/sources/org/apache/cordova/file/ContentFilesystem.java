package org.apache.cordova.file;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.cordova.CordovaResourceApi;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ContentFilesystem extends Filesystem {
    private final Context context;

    public ContentFilesystem(Context context, CordovaResourceApi resourceApi) {
        super(Uri.parse("content://"), FirebaseAnalytics.Param.CONTENT, resourceApi);
        this.context = context;
    }

    @Override // org.apache.cordova.file.Filesystem
    public Uri toNativeUri(LocalFilesystemURL inputURL) {
        String authorityAndPath = inputURL.uri.getEncodedPath().substring(this.name.length() + 2);
        if (authorityAndPath.length() < 2) {
            return null;
        }
        String ret = "content://" + authorityAndPath;
        String query = inputURL.uri.getEncodedQuery();
        if (query != null) {
            ret = ret + '?' + query;
        }
        String frag = inputURL.uri.getEncodedFragment();
        if (frag != null) {
            ret = ret + '#' + frag;
        }
        return Uri.parse(ret);
    }

    @Override // org.apache.cordova.file.Filesystem
    public LocalFilesystemURL toLocalUri(Uri inputURL) {
        if (!FirebaseAnalytics.Param.CONTENT.equals(inputURL.getScheme())) {
            return null;
        }
        String subPath = inputURL.getEncodedPath();
        if (subPath.length() > 0) {
            subPath = subPath.substring(1);
        }
        Uri.Builder b = new Uri.Builder().scheme(LocalFilesystemURL.FILESYSTEM_PROTOCOL).authority("localhost").path(this.name).appendPath(inputURL.getAuthority());
        if (subPath.length() > 0) {
            b.appendEncodedPath(subPath);
        }
        Uri localUri = b.encodedQuery(inputURL.getEncodedQuery()).encodedFragment(inputURL.getEncodedFragment()).build();
        return LocalFilesystemURL.parse(localUri);
    }

    @Override // org.apache.cordova.file.Filesystem
    public JSONObject getFileForLocalURL(LocalFilesystemURL inputURL, String fileName, JSONObject options, boolean directory) throws JSONException, IOException, TypeMismatchException {
        throw new UnsupportedOperationException("getFile() not supported for content:. Use resolveLocalFileSystemURL instead.");
    }

    @Override // org.apache.cordova.file.Filesystem
    public boolean removeFileAtLocalURL(LocalFilesystemURL inputURL) throws NoModificationAllowedException {
        Uri contentUri = toNativeUri(inputURL);
        try {
            this.context.getContentResolver().delete(contentUri, null, null);
            return true;
        } catch (UnsupportedOperationException e) {
            throw new NoModificationAllowedException("Deleting not supported for content uri: " + contentUri);
        }
    }

    @Override // org.apache.cordova.file.Filesystem
    public boolean recursiveRemoveFileAtLocalURL(LocalFilesystemURL inputURL) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Cannot remove content url");
    }

    @Override // org.apache.cordova.file.Filesystem
    public LocalFilesystemURL[] listChildren(LocalFilesystemURL inputURL) throws FileNotFoundException {
        throw new UnsupportedOperationException("readEntriesAtLocalURL() not supported for content:. Use resolveLocalFileSystemURL instead.");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0054 A[Catch: IOException -> 0x005d, all -> 0x0064, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IOException -> 0x005d, blocks: (B:4:0x0014, B:6:0x001a, B:8:0x0028, B:14:0x0054), top: B:28:0x0014, outer: #0 }] */
    @Override // org.apache.cordova.file.Filesystem
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.json.JSONObject getFileMetadataForLocalURL(org.apache.cordova.file.LocalFilesystemURL r14) throws java.net.ProtocolException, org.json.JSONException, java.io.FileNotFoundException {
        /*
            r13 = this;
            r10 = -1
            r2 = 0
            android.net.Uri r7 = r13.toNativeUri(r14)
            org.apache.cordova.CordovaResourceApi r9 = r13.resourceApi
            java.lang.String r5 = r9.getMimeType(r7)
            android.database.Cursor r0 = r13.openCursorForURL(r7)
            if (r0 == 0) goto L54
            boolean r9 = r0.moveToFirst()     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L64
            if (r9 == 0) goto L54
            java.lang.Long r9 = r13.resourceSizeForCursor(r0)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L64
            long r10 = r9.longValue()     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L64
            java.lang.Long r6 = r13.lastModifiedDateForCursor(r0)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L64
            if (r6 == 0) goto L2c
            long r2 = r6.longValue()     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L64
        L2c:
            if (r0 == 0) goto L31
            r0.close()
        L31:
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>()
            java.lang.String r9 = "size"
            r4.put(r9, r10)     // Catch: org.json.JSONException -> L6b
            java.lang.String r9 = "type"
            r4.put(r9, r5)     // Catch: org.json.JSONException -> L6b
            java.lang.String r9 = "name"
            java.lang.String r12 = r13.name     // Catch: org.json.JSONException -> L6b
            r4.put(r9, r12)     // Catch: org.json.JSONException -> L6b
            java.lang.String r9 = "fullPath"
            java.lang.String r12 = r14.path     // Catch: org.json.JSONException -> L6b
            r4.put(r9, r12)     // Catch: org.json.JSONException -> L6b
            java.lang.String r9 = "lastModifiedDate"
            r4.put(r9, r2)     // Catch: org.json.JSONException -> L6b
        L53:
            return r4
        L54:
            org.apache.cordova.CordovaResourceApi r9 = r13.resourceApi     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L64
            org.apache.cordova.CordovaResourceApi$OpenForReadResult r8 = r9.openForRead(r7)     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L64
            long r10 = r8.length     // Catch: java.io.IOException -> L5d java.lang.Throwable -> L64
            goto L2c
        L5d:
            r1 = move-exception
            java.io.FileNotFoundException r9 = new java.io.FileNotFoundException     // Catch: java.lang.Throwable -> L64
            r9.<init>()     // Catch: java.lang.Throwable -> L64
            throw r9     // Catch: java.lang.Throwable -> L64
        L64:
            r9 = move-exception
            if (r0 == 0) goto L6a
            r0.close()
        L6a:
            throw r9
        L6b:
            r1 = move-exception
            r4 = 0
            goto L53
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.file.ContentFilesystem.getFileMetadataForLocalURL(org.apache.cordova.file.LocalFilesystemURL):org.json.JSONObject");
    }

    @Override // org.apache.cordova.file.Filesystem
    public long writeToFileAtURL(LocalFilesystemURL inputURL, String data, int offset, boolean isBinary) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Couldn't write to file given its content URI");
    }

    @Override // org.apache.cordova.file.Filesystem
    public long truncateFileAtURL(LocalFilesystemURL inputURL, long size) throws NoModificationAllowedException {
        throw new NoModificationAllowedException("Couldn't truncate file given its content URI");
    }

    protected Cursor openCursorForURL(Uri nativeUri) {
        ContentResolver contentResolver = this.context.getContentResolver();
        try {
            return contentResolver.query(nativeUri, null, null, null, null);
        } catch (UnsupportedOperationException e) {
            return null;
        }
    }

    private Long resourceSizeForCursor(Cursor cursor) {
        String sizeStr;
        int columnIndex = cursor.getColumnIndex("_size");
        if (columnIndex == -1 || (sizeStr = cursor.getString(columnIndex)) == null) {
            return null;
        }
        return Long.valueOf(Long.parseLong(sizeStr));
    }

    protected Long lastModifiedDateForCursor(Cursor cursor) {
        String dateStr;
        int columnIndex = cursor.getColumnIndex("date_modified");
        if (columnIndex == -1) {
            columnIndex = cursor.getColumnIndex("last_modified");
        }
        if (columnIndex == -1 || (dateStr = cursor.getString(columnIndex)) == null) {
            return null;
        }
        return Long.valueOf(Long.parseLong(dateStr));
    }

    @Override // org.apache.cordova.file.Filesystem
    public String filesystemPathForURL(LocalFilesystemURL url) {
        File f = this.resourceApi.mapUriToFile(toNativeUri(url));
        if (f == null) {
            return null;
        }
        return f.getAbsolutePath();
    }

    @Override // org.apache.cordova.file.Filesystem
    public LocalFilesystemURL URLforFilesystemPath(String path) {
        return null;
    }

    @Override // org.apache.cordova.file.Filesystem
    public boolean canRemoveFileAtLocalURL(LocalFilesystemURL inputURL) {
        return true;
    }
}
