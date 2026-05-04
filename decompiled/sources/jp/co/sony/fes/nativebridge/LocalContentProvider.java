package jp.co.sony.fes.nativebridge;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sony.cdp.plugin.nativebridge.Gate;
import com.sony.cdp.plugin.nativebridge.MethodContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LocalContentProvider extends Gate {
    private static final String DB_QUERY_SORT_ORDER = "datetaken DESC";
    private static final String IMAGE_COMPRESS_PREFIX = "data:image/jpeg;base64,";
    private static final int IMAGE_COMPRESS_QUALITY = 90;
    private static final String TAG = "[LocalContentProvider] ";
    private static final Uri DB_CONTENT_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static final String DB_COLUMN_FILEPATH = "_data";
    private static final String DB_COLUMN_ID = "_id";
    private static final String DB_COLUMN_MIME_TYPE = "mime_type";
    private static final String DB_COLUMN_MODIFIED_DATE = "date_modified";
    private static final String DB_COLUMN_SIZE = "_size";
    private static final String DB_COLUMN_WIDTH = "width";
    private static final String DB_COLUMN_HEIGHT = "height";
    private static final String DB_COLUMN_ORIENTATION = "orientation";
    private static final String DB_COLUMN_ADDED_DATE = "date_added";
    private static final String DB_COLUMN_RECORDED_DATE = "datetaken";
    private static final String[] DB_QUERY_COLUMN_LIST = {DB_COLUMN_FILEPATH, DB_COLUMN_ID, DB_COLUMN_MIME_TYPE, DB_COLUMN_MODIFIED_DATE, DB_COLUMN_SIZE, DB_COLUMN_WIDTH, DB_COLUMN_HEIGHT, DB_COLUMN_ORIENTATION, DB_COLUMN_ADDED_DATE, DB_COLUMN_RECORDED_DATE};
    private static final Bitmap.CompressFormat IMAGE_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;

    public void queryLocalContents(final double index, final double limit) {
        final MethodContext context = getContext();
        this.cordova.getThreadPool().execute(new Runnable() { // from class: jp.co.sony.fes.nativebridge.LocalContentProvider.1
            @Override // java.lang.Runnable
            public void run() {
                Cursor cursor = null;
                try {
                    try {
                        try {
                            try {
                                LocalContentProvider.this.setCancelable(context);
                                JSONObject result = new JSONObject();
                                JSONArray contents = new JSONArray();
                                Cursor cursor2 = LocalContentProvider.this.openAllImages();
                                int totalContentCount = cursor2.getCount();
                                int MAX_QUERY_SIZE = (int) limit;
                                if (totalContentCount != 0 && totalContentCount <= index) {
                                    LocalContentProvider.this.rejectParams(4, "[LocalContentProvider] Invalid index: " + String.valueOf(index) + "total count: " + String.valueOf(totalContentCount), context, new Object[0]);
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    LocalContentProvider.this.removeCancelable(context);
                                    return;
                                }
                                cursor2.moveToPosition((int) index);
                                int querySize = Math.min(MAX_QUERY_SIZE, totalContentCount - ((int) index));
                                for (int i = 0; i < querySize && !LocalContentProvider.this.isCanceled(context); i++) {
                                    contents.put(LocalContentProvider.this.getContentInfo(cursor2, ((int) index) + i));
                                    cursor2.moveToNext();
                                }
                                if (LocalContentProvider.this.isCanceled(context)) {
                                    LocalContentProvider.this.rejectParams(3, "[LocalContentProvider] queryLocalContents() is canceled.", context, new Object[0]);
                                } else {
                                    result.put("totalContentCount", totalContentCount);
                                    result.put("contents", contents);
                                    LocalContentProvider.this.resolveParams(context, result);
                                }
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                LocalContentProvider.this.removeCancelable(context);
                            } catch (JSONException e) {
                                String errorMsg = "JSONException: " + e.getMessage();
                                Log.e(LocalContentProvider.TAG, errorMsg, e);
                                LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg, context, new Object[0]);
                                if (0 != 0) {
                                    cursor.close();
                                }
                                LocalContentProvider.this.removeCancelable(context);
                            }
                        } catch (SQLiteException e2) {
                            String errorMsg2 = "SQLiteException: " + e2.getMessage();
                            Log.e(LocalContentProvider.TAG, errorMsg2, e2);
                            LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg2, context, new Object[0]);
                            if (0 != 0) {
                                cursor.close();
                            }
                            LocalContentProvider.this.removeCancelable(context);
                        }
                    } catch (SecurityException e3) {
                        String errorMsg3 = "SecurityException: " + e3.getMessage();
                        Log.e(LocalContentProvider.TAG, errorMsg3, e3);
                        LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg3, context, new Object[0]);
                        if (0 != 0) {
                            cursor.close();
                        }
                        LocalContentProvider.this.removeCancelable(context);
                    } catch (Exception e4) {
                        String errorMsg4 = "Exception: " + e4.getMessage();
                        Log.e(LocalContentProvider.TAG, errorMsg4, e4);
                        LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg4, context, new Object[0]);
                        if (0 != 0) {
                            cursor.close();
                        }
                        LocalContentProvider.this.removeCancelable(context);
                    }
                } catch (Throwable th) {
                    if (0 != 0) {
                        cursor.close();
                    }
                    LocalContentProvider.this.removeCancelable(context);
                    throw th;
                }
            }
        });
    }

    public void queryThumbnailByKey(final String key) {
        final MethodContext context = getContext();
        this.cordova.getThreadPool().execute(new Runnable() { // from class: jp.co.sony.fes.nativebridge.LocalContentProvider.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LocalContentProvider.this.setCancelable(context);
                    String base64 = LocalContentProvider.this.getThumbnailDataURL(context, key);
                    if (LocalContentProvider.this.isCanceled(context)) {
                        LocalContentProvider.this.rejectParams(3, "[LocalContentProvider] queryThumbnailByKey() is canceled.", context, new Object[0]);
                    } else {
                        LocalContentProvider.this.resolveParams(context, base64);
                    }
                } catch (IllegalStateException e) {
                    String errorMsg = "IllegalStateException: " + e.getMessage();
                    Log.e(LocalContentProvider.TAG, errorMsg, e);
                    LocalContentProvider.this.rejectParams(2, errorMsg, context, new Object[0]);
                } catch (SQLiteException e2) {
                    String errorMsg2 = "SQLiteException: " + e2.getMessage();
                    Log.e(LocalContentProvider.TAG, errorMsg2, e2);
                    LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg2, context, new Object[0]);
                } catch (IOException e3) {
                    String errorMsg3 = "IOException: " + e3.getMessage();
                    Log.e(LocalContentProvider.TAG, errorMsg3, e3);
                    LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg3, context, new Object[0]);
                } catch (IllegalArgumentException e4) {
                    String errorMsg4 = "IllegalArgumentException: " + e4.getMessage();
                    Log.e(LocalContentProvider.TAG, errorMsg4, e4);
                    LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg4, context, new Object[0]);
                } finally {
                    LocalContentProvider.this.removeCancelable(context);
                }
            }
        });
    }

    public void queryImageSourceByKey(final String key) {
        final MethodContext context = getContext();
        this.cordova.getThreadPool().execute(new Runnable() { // from class: jp.co.sony.fes.nativebridge.LocalContentProvider.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LocalContentProvider.this.setCancelable(context);
                    String base64 = LocalContentProvider.this.getImageDataURL(context, key);
                    if (LocalContentProvider.this.isCanceled(context)) {
                        LocalContentProvider.this.rejectParams(3, "[LocalContentProvider] queryImageSourceByKey() is canceled.", context, new Object[0]);
                    } else {
                        LocalContentProvider.this.resolveParams(context, base64);
                    }
                } catch (IOException e) {
                    String errorMsg = "IOException: " + e.getMessage();
                    Log.e(LocalContentProvider.TAG, errorMsg, e);
                    LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg, context, new Object[0]);
                } catch (IllegalArgumentException e2) {
                    String errorMsg2 = "IllegalArgumentException: " + e2.getMessage();
                    Log.e(LocalContentProvider.TAG, errorMsg2, e2);
                    LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg2, context, new Object[0]);
                } catch (SQLiteException e3) {
                    String errorMsg3 = "SQLiteException: " + e3.getMessage();
                    Log.e(LocalContentProvider.TAG, errorMsg3, e3);
                    LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg3, context, new Object[0]);
                } catch (IllegalStateException e4) {
                    String errorMsg4 = "IllegalStateException: " + e4.getMessage();
                    Log.e(LocalContentProvider.TAG, errorMsg4, e4);
                    LocalContentProvider.this.rejectParams(2, LocalContentProvider.TAG + errorMsg4, context, new Object[0]);
                } finally {
                    LocalContentProvider.this.removeCancelable(context);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Cursor openAllImages() {
        ContentResolver resolver = this.cordova.getActivity().getApplicationContext().getContentResolver();
        return resolver.query(DB_CONTENT_URI, DB_QUERY_COLUMN_LIST, null, null, DB_QUERY_SORT_ORDER);
    }

    private Cursor openImageByKey(String key) {
        return openImageByKey(key, this.cordova.getActivity().getApplicationContext().getContentResolver());
    }

    private Cursor openImageByKey(String key, ContentResolver resolver) throws IllegalArgumentException {
        Cursor cursor = resolver.query(DB_CONTENT_URI, DB_QUERY_COLUMN_LIST, "_id=?", new String[]{key}, DB_QUERY_SORT_ORDER);
        if (cursor == null) {
            throw new IllegalArgumentException("invalid key: " + key);
        }
        cursor.moveToFirst();
        return cursor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getContentInfo(Cursor cursor, int index) throws JSONException {
        JSONObject content = new JSONObject();
        long key = cursor.getLong(cursor.getColumnIndexOrThrow(DB_COLUMN_ID));
        long width = cursor.getLong(cursor.getColumnIndexOrThrow(DB_COLUMN_WIDTH));
        long height = cursor.getLong(cursor.getColumnIndexOrThrow(DB_COLUMN_HEIGHT));
        long orientation = cursor.getLong(cursor.getColumnIndexOrThrow(DB_COLUMN_ORIENTATION));
        content.put("key", String.valueOf(key));
        content.put(FirebaseAnalytics.Param.INDEX, index);
        if (90 == orientation || 270 == orientation) {
            content.put(DB_COLUMN_WIDTH, height);
            content.put(DB_COLUMN_HEIGHT, width);
        } else {
            content.put(DB_COLUMN_WIDTH, width);
            content.put(DB_COLUMN_HEIGHT, height);
        }
        return content;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getThumbnailDataURL(MethodContext context, String key) throws IllegalStateException, IOException, IllegalArgumentException {
        int id = Integer.parseInt(key);
        ContentResolver resolver = this.cordova.getActivity().getContentResolver();
        String base64 = null;
        Cursor cursor = null;
        Bitmap thumbnailRaw = null;
        Bitmap thumbnail = null;
        try {
            cursor = openImageByKey(key, resolver);
            long orientation = cursor.getLong(cursor.getColumnIndexOrThrow(DB_COLUMN_ORIENTATION));
            if (!isCanceled(context)) {
                thumbnailRaw = MediaStore.Images.Thumbnails.getThumbnail(resolver, id, 3, null);
                if (thumbnailRaw == null) {
                    throw new IllegalArgumentException("thumbnail == empty");
                }
                Matrix matrix = new Matrix();
                matrix.postRotate(0.0f);
                if (orientation > 0) {
                    matrix.postRotate(orientation);
                }
                thumbnail = Bitmap.createBitmap(thumbnailRaw, 0, 0, thumbnailRaw.getWidth(), thumbnailRaw.getHeight(), matrix, true);
            }
            if (!isCanceled(context)) {
                if (thumbnail == null) {
                    throw new IllegalStateException("thumbnail create failed.");
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                thumbnail.compress(IMAGE_COMPRESS_FORMAT, IMAGE_COMPRESS_QUALITY, stream);
                base64 = IMAGE_COMPRESS_PREFIX + Base64.encodeToString(stream.toByteArray(), 2);
                stream.close();
            }
            return base64;
        } finally {
            if (0 != 0 && !thumbnailRaw.isRecycled()) {
                thumbnailRaw.recycle();
            }
            if (0 != 0 && !thumbnail.isRecycled()) {
                thumbnail.recycle();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getImageDataURL(MethodContext context, String key) throws IOException, IllegalArgumentException {
        String base64 = null;
        Cursor cursor = null;
        Bitmap imageRaw = null;
        Bitmap image = null;
        try {
            cursor = openImageByKey(key);
            long orientation = cursor.getLong(cursor.getColumnIndexOrThrow(DB_COLUMN_ORIENTATION));
            String filePath = cursor.getString(cursor.getColumnIndexOrThrow(DB_COLUMN_FILEPATH));
            if (!isCanceled(context)) {
                imageRaw = decodeBitmap(filePath);
                Matrix matrix = new Matrix();
                matrix.postRotate(0.0f);
                if (orientation > 0) {
                    matrix.postRotate(orientation);
                }
                image = Bitmap.createBitmap(imageRaw, 0, 0, imageRaw.getWidth(), imageRaw.getHeight(), matrix, true);
            }
            if (!isCanceled(context)) {
                if (image == null) {
                    throw new IllegalStateException("thumbnail create failed.");
                }
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(IMAGE_COMPRESS_FORMAT, IMAGE_COMPRESS_QUALITY, stream);
                base64 = IMAGE_COMPRESS_PREFIX + Base64.encodeToString(stream.toByteArray(), 2);
                stream.close();
            }
            return base64;
        } finally {
            if (0 != 0 && !imageRaw.isRecycled()) {
                imageRaw.recycle();
            }
            if (0 != 0 && !image.isRecycled()) {
                image.recycle();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private Bitmap decodeBitmap(String filePath) throws IllegalStateException, IllegalArgumentException {
        Bitmap tmpBmp = null;
        if (filePath != null) {
            try {
                if (!filePath.isEmpty()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, options);
                    int scaleW = Math.round(options.outWidth / 1024.0f);
                    int scaleH = Math.round(options.outHeight / 1024.0f);
                    int scale = Math.max(scaleW, scaleH);
                    options.inSampleSize = Math.max(scale, 1);
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    options.inJustDecodeBounds = false;
                    tmpBmp = BitmapFactory.decodeFile(filePath, options);
                    if (tmpBmp == null) {
                        throw new IllegalStateException("BitmapFactory#decodeFile() failed.");
                    }
                    Bitmap bmpImage = tmpBmp.copy(Bitmap.Config.ARGB_8888, true);
                    return bmpImage;
                }
            } finally {
                if (tmpBmp != null && !tmpBmp.isRecycled()) {
                    tmpBmp.recycle();
                }
            }
        }
        throw new IllegalArgumentException("filePath == null || filePath.isEmpty()");
    }
}
