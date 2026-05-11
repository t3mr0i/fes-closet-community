package org.apache.cordova.camera;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.cordova.CordovaInterface;

/* loaded from: classes.dex */
public class FileHelper {
    private static final String LOG_TAG = "FileUtils";
    private static final String _DATA = "_data";

    public static String getRealPath(Uri uri, CordovaInterface cordova) throws IllegalArgumentException {
        if (Build.VERSION.SDK_INT < 11) {
            String realPath = getRealPathFromURI_BelowAPI11(cordova.getActivity(), uri);
            return realPath;
        }
        String realPath2 = getRealPathFromURI_API11_And_Above(cordova.getActivity(), uri);
        return realPath2;
    }

    public static String getRealPath(String uriString, CordovaInterface cordova) {
        return getRealPath(Uri.parse(uriString), cordova);
    }

    @SuppressLint({"NewApi"})
    public static String getRealPathFromURI_API11_And_Above(Context context, Uri uri) {
        boolean isKitKat = Build.VERSION.SDK_INT >= 19;
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                return null;
            }
            if (isDownloadsDocument(uri)) {
                String id = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id).longValue());
                return getDataColumn(context, contentUri, null, null);
            }
            if (!isMediaDocument(uri)) {
                return null;
            }
            String docId2 = DocumentsContract.getDocumentId(uri);
            String[] split2 = docId2.split(":");
            String type = split2[0];
            Uri contentUri2 = null;
            if ("image".equals(type)) {
                contentUri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                contentUri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            String[] selectionArgs = {split2[1]};
            return getDataColumn(context, contentUri2, "_id=?", selectionArgs);
        }
        if (FirebaseAnalytics.Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        }
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri) throws IllegalArgumentException {
        String[] proj = {_DATA};
        try {
            Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(_DATA);
            cursor.moveToFirst();
            String result = cursor.getString(column_index);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static InputStream getInputStreamFromUriString(String uriString, CordovaInterface cordova) throws IOException {
        InputStream returnValue;
        if (uriString.startsWith(FirebaseAnalytics.Param.CONTENT)) {
            Uri uri = Uri.parse(uriString);
            InputStream returnValue2 = cordova.getActivity().getContentResolver().openInputStream(uri);
            return returnValue2;
        }
        if (uriString.startsWith("file://")) {
            int question = uriString.indexOf("?");
            if (question > -1) {
                uriString = uriString.substring(0, question);
            }
            if (uriString.startsWith("file:///android_asset/")) {
                Uri uri2 = Uri.parse(uriString);
                String relativePath = uri2.getPath().substring(15);
                InputStream returnValue3 = cordova.getActivity().getAssets().open(relativePath);
                return returnValue3;
            }
            try {
                returnValue = cordova.getActivity().getContentResolver().openInputStream(Uri.parse(uriString));
            } catch (Exception e) {
                returnValue = null;
            }
            if (returnValue == null) {
                InputStream returnValue4 = new FileInputStream(getRealPath(uriString, cordova));
                return returnValue4;
            }
            return returnValue;
        }
        InputStream returnValue5 = new FileInputStream(uriString);
        return returnValue5;
    }

    public static String stripFileProtocol(String uriString) {
        if (uriString.startsWith("file://")) {
            return uriString.substring(7);
        }
        return uriString;
    }

    public static String getMimeTypeForExtension(String path) {
        String extension = path;
        int lastDot = extension.lastIndexOf(46);
        if (lastDot != -1) {
            extension = extension.substring(lastDot + 1);
        }
        String extension2 = extension.toLowerCase(Locale.getDefault());
        return extension2.equals("3ga") ? "audio/3gpp" : MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension2);
    }

    public static String getMimeType(String uriString, CordovaInterface cordova) {
        Uri uri = Uri.parse(uriString);
        if (uriString.startsWith("content://")) {
            String mimeType = cordova.getActivity().getContentResolver().getType(uri);
            return mimeType;
        }
        String mimeType2 = getMimeTypeForExtension(uri.getPath());
        return mimeType2;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String[] projection = {_DATA};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor == null || !cursor.moveToFirst()) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            int column_index = cursor.getColumnIndexOrThrow(_DATA);
            String string = cursor.getString(column_index);
            if (cursor == null) {
                return string;
            }
            cursor.close();
            return string;
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
