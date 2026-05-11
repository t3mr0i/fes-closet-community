package org.apache.cordova.camera;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.cordova.BuildHelper;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class CameraLauncher extends CordovaPlugin implements MediaScannerConnection.MediaScannerConnectionClient {
    private static final int ALLMEDIA = 2;
    private static final int CAMERA = 1;
    private static final int CROP_CAMERA = 100;
    private static final int DATA_URL = 0;
    private static final int FILE_URI = 1;
    private static final String GET_All = "Get All";
    private static final String GET_PICTURE = "Get Picture";
    private static final String GET_VIDEO = "Get Video";
    private static final int JPEG = 0;
    private static final String LOG_TAG = "CameraLauncher";
    private static final int NATIVE_URI = 2;
    public static final int PERMISSION_DENIED_ERROR = 20;
    private static final int PHOTOLIBRARY = 0;
    private static final int PICTURE = 0;
    private static final int PNG = 1;
    private static final int SAVEDPHOTOALBUM = 2;
    public static final int SAVE_TO_ALBUM_SEC = 1;
    public static final int TAKE_PIC_SEC = 0;
    private static final int VIDEO = 1;
    protected static final String[] permissions = {"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private boolean allowEdit;
    private String applicationId;
    public CallbackContext callbackContext;
    private MediaScannerConnection conn;
    private boolean correctOrientation;
    private Uri croppedUri;
    private int destType;
    private int encodingType;
    private ExifHelper exifData;
    private CordovaUri imageUri;
    private int mQuality;
    private int mediaType;
    private int numPics;
    private boolean orientationCorrected;
    private boolean saveToPhotoAlbum;
    private Uri scanMe;
    private int srcType;
    private int targetHeight;
    private int targetWidth;

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws IllegalAccessException, JSONException, NoSuchMethodException, SecurityException, InvocationTargetException {
        this.callbackContext = callbackContext;
        this.applicationId = (String) BuildHelper.getBuildConfigValue(this.cordova.getActivity(), "APPLICATION_ID");
        this.applicationId = this.preferences.getString("applicationId", this.applicationId);
        if (!action.equals("takePicture")) {
            return false;
        }
        this.srcType = 1;
        this.destType = 1;
        this.saveToPhotoAlbum = false;
        this.targetHeight = 0;
        this.targetWidth = 0;
        this.encodingType = 0;
        this.mediaType = 0;
        this.mQuality = 50;
        this.destType = args.getInt(1);
        this.srcType = args.getInt(2);
        this.mQuality = args.getInt(0);
        this.targetWidth = args.getInt(3);
        this.targetHeight = args.getInt(4);
        this.encodingType = args.getInt(5);
        this.mediaType = args.getInt(6);
        this.allowEdit = args.getBoolean(7);
        this.correctOrientation = args.getBoolean(8);
        this.saveToPhotoAlbum = args.getBoolean(9);
        if (this.targetWidth < 1) {
            this.targetWidth = -1;
        }
        if (this.targetHeight < 1) {
            this.targetHeight = -1;
        }
        if (this.targetHeight == -1 && this.targetWidth == -1 && this.mQuality == CROP_CAMERA && !this.correctOrientation && this.encodingType == 1 && this.srcType == 1) {
            this.encodingType = 0;
        }
        try {
            if (this.srcType == 1) {
                callTakePicture(this.destType, this.encodingType);
            } else if (this.srcType == 0 || this.srcType == 2) {
                if (!PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE")) {
                    PermissionHelper.requestPermission(this, 1, "android.permission.READ_EXTERNAL_STORAGE");
                } else {
                    getImage(this.srcType, this.destType, this.encodingType);
                }
            }
            PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
            r.setKeepCallback(true);
            callbackContext.sendPluginResult(r);
            return true;
        } catch (IllegalArgumentException e) {
            callbackContext.error("Illegal Argument Exception");
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
            return true;
        }
    }

    private String getTempDirectoryPath() {
        File cache;
        if (Environment.getExternalStorageState().equals("mounted")) {
            cache = this.cordova.getActivity().getExternalCacheDir();
        } else {
            cache = this.cordova.getActivity().getCacheDir();
        }
        cache.mkdirs();
        return cache.getAbsolutePath();
    }

    public void callTakePicture(int returnType, int encodingType) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        boolean saveAlbumPermission = PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE") && PermissionHelper.hasPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        boolean takePicturePermission = PermissionHelper.hasPermission(this, "android.permission.CAMERA");
        if (!takePicturePermission) {
            takePicturePermission = true;
            try {
                PackageManager packageManager = this.cordova.getActivity().getPackageManager();
                String[] permissionsInPackage = packageManager.getPackageInfo(this.cordova.getActivity().getPackageName(), 4096).requestedPermissions;
                if (permissionsInPackage != null) {
                    int length = permissionsInPackage.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        String permission = permissionsInPackage[i];
                        if (!permission.equals("android.permission.CAMERA")) {
                            i++;
                        } else {
                            takePicturePermission = false;
                            break;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        if (takePicturePermission && saveAlbumPermission) {
            takePicture(returnType, encodingType);
            return;
        }
        if (saveAlbumPermission && !takePicturePermission) {
            PermissionHelper.requestPermission(this, 0, "android.permission.CAMERA");
        } else if (!saveAlbumPermission && takePicturePermission) {
            PermissionHelper.requestPermissions(this, 0, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"});
        } else {
            PermissionHelper.requestPermissions(this, 0, permissions);
        }
    }

    public void takePicture(int returnType, int encodingType) {
        this.numPics = queryImgDB(whichContentStore()).getCount();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = createCaptureFile(encodingType);
        this.imageUri = new CordovaUri(android.support.v4.content.FileProvider.getUriForFile(this.cordova.getActivity(), this.applicationId + ".provider", photo));
        intent.putExtra("output", this.imageUri.getCorrectUri());
        intent.addFlags(2);
        if (this.cordova != null) {
            PackageManager mPm = this.cordova.getActivity().getPackageManager();
            if (intent.resolveActivity(mPm) != null) {
                this.cordova.startActivityForResult(this, intent, returnType + 32 + 1);
            } else {
                LOG.d(LOG_TAG, "Error: You don't have a default camera.  Your device may not be CTS complaint.");
            }
        }
    }

    private File createCaptureFile(int encodingType) {
        return createCaptureFile(encodingType, "");
    }

    private File createCaptureFile(int encodingType, String fileName) {
        String fileName2;
        if (fileName.isEmpty()) {
            fileName = ".Pic";
        }
        if (encodingType == 0) {
            fileName2 = fileName + ".jpg";
        } else if (encodingType == 1) {
            fileName2 = fileName + ".png";
        } else {
            throw new IllegalArgumentException("Invalid Encoding Type: " + encodingType);
        }
        return new File(getTempDirectoryPath(), fileName2);
    }

    public void getImage(int srcType, int returnType, int encodingType) {
        Intent intent = new Intent();
        String title = GET_PICTURE;
        this.croppedUri = null;
        if (this.mediaType == 0) {
            intent.setType("image/*");
            if (this.allowEdit) {
                intent.setAction("android.intent.action.PICK");
                intent.putExtra("crop", "true");
                if (this.targetWidth > 0) {
                    intent.putExtra("outputX", this.targetWidth);
                }
                if (this.targetHeight > 0) {
                    intent.putExtra("outputY", this.targetHeight);
                }
                if (this.targetHeight > 0 && this.targetWidth > 0 && this.targetWidth == this.targetHeight) {
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                }
                File photo = createCaptureFile(0);
                this.croppedUri = Uri.fromFile(photo);
                intent.putExtra("output", this.croppedUri);
            } else {
                intent.setAction("android.intent.action.GET_CONTENT");
                intent.addCategory("android.intent.category.OPENABLE");
            }
        } else if (this.mediaType == 1) {
            intent.setType("video/*");
            title = GET_VIDEO;
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        } else if (this.mediaType == 2) {
            intent.setType("*/*");
            title = GET_All;
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        }
        if (this.cordova != null) {
            this.cordova.startActivityForResult(this, Intent.createChooser(intent, new String(title)), ((srcType + 1) * 16) + returnType + 1);
        }
    }

    private void performCrop(Uri picUri, int destType, Intent cameraIntent) throws NumberFormatException {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            if (this.targetWidth > 0) {
                cropIntent.putExtra("outputX", this.targetWidth);
            }
            if (this.targetHeight > 0) {
                cropIntent.putExtra("outputY", this.targetHeight);
            }
            if (this.targetHeight > 0 && this.targetWidth > 0 && this.targetWidth == this.targetHeight) {
                cropIntent.putExtra("aspectX", 1);
                cropIntent.putExtra("aspectY", 1);
            }
            this.croppedUri = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
            cropIntent.addFlags(1);
            cropIntent.addFlags(2);
            cropIntent.putExtra("output", this.croppedUri);
            if (this.cordova != null) {
                this.cordova.startActivityForResult(this, cropIntent, destType + CROP_CAMERA);
            }
        } catch (ActivityNotFoundException e) {
            LOG.e(LOG_TAG, "Crop operation not supported on this device");
            try {
                processResultFromCamera(destType, cameraIntent);
            } catch (IOException e2) {
                e2.printStackTrace();
                LOG.e(LOG_TAG, "Unable to write to file");
            }
        }
    }

    private void processResultFromCamera(int destType, Intent intent) throws IOException, NumberFormatException {
        String sourcePath;
        int rotate = 0;
        ExifHelper exif = new ExifHelper();
        if (this.allowEdit && this.croppedUri != null) {
            sourcePath = FileHelper.stripFileProtocol(this.croppedUri.toString());
        } else {
            sourcePath = this.imageUri.getFilePath();
        }
        if (this.encodingType == 0) {
            try {
                exif.createInFile(sourcePath);
                exif.readExifData();
                rotate = exif.getOrientation();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Bitmap bitmap = null;
        Uri galleryUri = null;
        if (this.saveToPhotoAlbum) {
            galleryUri = Uri.fromFile(new File(getPicturesPath()));
            if (this.allowEdit && this.croppedUri != null) {
                writeUncompressedImage(this.croppedUri, galleryUri);
            } else {
                Uri imageUri = this.imageUri.getFileUri();
                writeUncompressedImage(imageUri, galleryUri);
            }
            refreshGallery(galleryUri);
        }
        if (destType == 0) {
            bitmap = getScaledAndRotatedBitmap(sourcePath);
            if (bitmap == null) {
                bitmap = (Bitmap) intent.getExtras().get("data");
            }
            if (bitmap == null) {
                LOG.d(LOG_TAG, "I either have a null image path or bitmap");
                failPicture("Unable to create bitmap!");
                return;
            } else {
                processPicture(bitmap, this.encodingType);
                if (!this.saveToPhotoAlbum) {
                    checkForDuplicateImage(0);
                }
            }
        } else if (destType == 1 || destType == 2) {
            if (this.targetHeight == -1 && this.targetWidth == -1 && this.mQuality == CROP_CAMERA && !this.correctOrientation) {
                if (this.saveToPhotoAlbum) {
                    this.callbackContext.success(galleryUri.toString());
                } else {
                    Uri uri = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
                    if (this.allowEdit && this.croppedUri != null) {
                        Uri croppedUri = Uri.fromFile(new File(getFileNameFromUri(this.croppedUri)));
                        writeUncompressedImage(croppedUri, uri);
                    } else {
                        Uri imageUri2 = this.imageUri.getFileUri();
                        writeUncompressedImage(imageUri2, uri);
                    }
                    this.callbackContext.success(uri.toString());
                }
            } else {
                Uri uri2 = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
                bitmap = getScaledAndRotatedBitmap(sourcePath);
                if (bitmap == null) {
                    LOG.d(LOG_TAG, "I either have a null image path or bitmap");
                    failPicture("Unable to create bitmap!");
                    return;
                }
                OutputStream os = this.cordova.getActivity().getContentResolver().openOutputStream(uri2);
                Bitmap.CompressFormat compressFormat = this.encodingType == 0 ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG;
                bitmap.compress(compressFormat, this.mQuality, os);
                os.close();
                if (this.encodingType == 0) {
                    String exifPath = uri2.getPath();
                    if (rotate != 1) {
                        exif.resetOrientation();
                    }
                    exif.createOutFile(exifPath);
                    exif.writeExifData();
                }
                this.callbackContext.success(uri2.toString());
            }
        } else {
            throw new IllegalStateException();
        }
        cleanup(1, this.imageUri.getFileUri(), galleryUri, bitmap);
    }

    private String getPicturesPath() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + (this.encodingType == 0 ? ".jpg" : ".png");
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String galleryPath = storageDir.getAbsolutePath() + "/" + imageFileName;
        return galleryPath;
    }

    private void refreshGallery(Uri contentUri) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        mediaScanIntent.setData(contentUri);
        this.cordova.getActivity().sendBroadcast(mediaScanIntent);
    }

    private String getMimetypeForFormat(int outputFormat) {
        return outputFormat == 1 ? "image/png" : outputFormat == 0 ? "image/jpeg" : "";
    }

    private String outputModifiedBitmap(Bitmap bitmap, Uri uri) throws IOException, IllegalArgumentException {
        String fileName;
        String realPath = FileHelper.getRealPath(uri, this.cordova);
        if (realPath != null) {
            fileName = realPath.substring(realPath.lastIndexOf(47) + 1);
        } else {
            fileName = "modified." + (this.encodingType == 0 ? "jpg" : "png");
        }
        new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String modifiedPath = getTempDirectoryPath() + "/" + fileName;
        OutputStream os = new FileOutputStream(modifiedPath);
        Bitmap.CompressFormat compressFormat = this.encodingType == 0 ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG;
        bitmap.compress(compressFormat, this.mQuality, os);
        os.close();
        if (this.exifData != null && this.encodingType == 0) {
            try {
                if (this.correctOrientation && this.orientationCorrected) {
                    this.exifData.resetOrientation();
                }
                this.exifData.createOutFile(modifiedPath);
                this.exifData.writeExifData();
                this.exifData = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return modifiedPath;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processResultFromGallery(int destType, Intent intent) throws IllegalArgumentException {
        Uri uri = intent.getData();
        if (uri == null) {
            if (this.croppedUri != null) {
                uri = this.croppedUri;
            } else {
                failPicture("null data from photo library");
                return;
            }
        }
        String fileLocation = FileHelper.getRealPath(uri, this.cordova);
        LOG.d(LOG_TAG, "File locaton is: " + fileLocation);
        if (this.mediaType != 0) {
            this.callbackContext.success(fileLocation);
            return;
        }
        String uriString = uri.toString();
        String mimeType = FileHelper.getMimeType(uriString, this.cordova);
        if (this.targetHeight == -1 && this.targetWidth == -1 && ((destType == 1 || destType == 2) && !this.correctOrientation && mimeType.equalsIgnoreCase(getMimetypeForFormat(this.encodingType)))) {
            this.callbackContext.success(uriString);
            return;
        }
        if (!"image/jpeg".equalsIgnoreCase(mimeType) && !"image/png".equalsIgnoreCase(mimeType)) {
            LOG.d(LOG_TAG, "I either have a null image path or bitmap");
            failPicture("Unable to retrieve path to picture!");
            return;
        }
        Bitmap bitmap = null;
        try {
            bitmap = getScaledAndRotatedBitmap(uriString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap == null) {
            LOG.d(LOG_TAG, "I either have a null image path or bitmap");
            failPicture("Unable to create bitmap!");
            return;
        }
        if (destType == 0) {
            processPicture(bitmap, this.encodingType);
        } else if (destType == 1 || destType == 2) {
            if ((this.targetHeight > 0 && this.targetWidth > 0) || ((this.correctOrientation && this.orientationCorrected) || !mimeType.equalsIgnoreCase(getMimetypeForFormat(this.encodingType)))) {
                try {
                    String modifiedPath = outputModifiedBitmap(bitmap, uri);
                    this.callbackContext.success("file://" + modifiedPath + "?" + System.currentTimeMillis());
                } catch (Exception e2) {
                    e2.printStackTrace();
                    failPicture("Error retrieving image.");
                }
            } else {
                this.callbackContext.success(fileLocation);
            }
        }
        if (bitmap != null) {
            bitmap.recycle();
        }
        System.gc();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onActivityResult(int requestCode, int resultCode, final Intent intent) throws NumberFormatException {
        int srcType = (requestCode / 16) - 1;
        final int destType = (requestCode % 16) - 1;
        if (requestCode >= CROP_CAMERA) {
            if (resultCode == -1) {
                try {
                    processResultFromCamera(requestCode - 100, intent);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    LOG.e(LOG_TAG, "Unable to write to file");
                    return;
                }
            }
            if (resultCode == 0) {
                failPicture("Camera cancelled.");
                return;
            } else {
                failPicture("Did not complete!");
                return;
            }
        }
        if (srcType == 1) {
            if (resultCode == -1) {
                try {
                    if (this.allowEdit) {
                        Uri tmpFile = android.support.v4.content.FileProvider.getUriForFile(this.cordova.getActivity(), this.applicationId + ".provider", createCaptureFile(this.encodingType));
                        performCrop(tmpFile, destType, intent);
                    } else {
                        processResultFromCamera(destType, intent);
                    }
                    return;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    failPicture("Error capturing image.");
                    return;
                }
            }
            if (resultCode == 0) {
                failPicture("Camera cancelled.");
                return;
            } else {
                failPicture("Did not complete!");
                return;
            }
        }
        if (srcType == 0 || srcType == 2) {
            if (resultCode == -1 && intent != null) {
                this.cordova.getThreadPool().execute(new Runnable() { // from class: org.apache.cordova.camera.CameraLauncher.1
                    @Override // java.lang.Runnable
                    public void run() throws IllegalArgumentException {
                        CameraLauncher.this.processResultFromGallery(destType, intent);
                    }
                });
            } else if (resultCode == 0) {
                failPicture("Selection cancelled.");
            } else {
                failPicture("Selection did not complete!");
            }
        }
    }

    private int exifToDegrees(int exifOrientation) {
        if (exifOrientation == 6) {
            return 90;
        }
        if (exifOrientation == 3) {
            return 180;
        }
        if (exifOrientation == 8) {
            return 270;
        }
        return 0;
    }

    private void writeUncompressedImage(InputStream fis, Uri dest) throws IOException {
        OutputStream os = null;
        try {
            os = this.cordova.getActivity().getContentResolver().openOutputStream(dest);
            byte[] buffer = new byte[4096];
            while (true) {
                int len = fis.read(buffer);
                if (len == -1) {
                    break;
                } else {
                    os.write(buffer, 0, len);
                }
            }
            os.flush();
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.d(LOG_TAG, "Exception while closing output stream.");
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e2) {
                    LOG.d(LOG_TAG, "Exception while closing file input stream.");
                }
            }
        } catch (Throwable th) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e3) {
                    LOG.d(LOG_TAG, "Exception while closing output stream.");
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e4) {
                    LOG.d(LOG_TAG, "Exception while closing file input stream.");
                }
            }
            throw th;
        }
    }

    private void writeUncompressedImage(Uri src, Uri dest) throws IOException {
        FileInputStream fis = new FileInputStream(FileHelper.stripFileProtocol(src.toString()));
        writeUncompressedImage(fis, dest);
    }

    private Uri getUriFromMediaStore() {
        ContentValues values = new ContentValues();
        values.put("mime_type", "image/jpeg");
        try {
            return this.cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        } catch (RuntimeException e) {
            LOG.d(LOG_TAG, "Can't write to external media storage.");
            try {
                return this.cordova.getActivity().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
            } catch (RuntimeException e2) {
                LOG.d(LOG_TAG, "Can't write to internal media storage.");
                return null;
            }
        }
    }

    private Bitmap getScaledAndRotatedBitmap(String imageUrl) throws IOException {
        int rotatedWidth;
        int rotatedHeight;
        if (this.targetWidth <= 0 && this.targetHeight <= 0 && !this.correctOrientation) {
            InputStream fileStream = null;
            try {
                fileStream = FileHelper.getInputStreamFromUriString(imageUrl, this.cordova);
                Bitmap image = BitmapFactory.decodeStream(fileStream);
                if (fileStream == null) {
                    return image;
                }
                try {
                    fileStream.close();
                    return image;
                } catch (IOException e) {
                    LOG.d(LOG_TAG, "Exception while closing file input stream.");
                    return image;
                }
            } catch (Throwable th) {
                if (fileStream != null) {
                    try {
                        fileStream.close();
                    } catch (IOException e2) {
                        LOG.d(LOG_TAG, "Exception while closing file input stream.");
                    }
                }
                throw th;
            }
        }
        File localFile = null;
        Uri galleryUri = null;
        int rotate = 0;
        try {
            InputStream fileStream2 = FileHelper.getInputStreamFromUriString(imageUrl, this.cordova);
            if (fileStream2 != null) {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = "IMG_" + timeStamp + (this.encodingType == 0 ? ".jpg" : ".png");
                File localFile2 = new File(getTempDirectoryPath() + fileName);
                try {
                    galleryUri = Uri.fromFile(localFile2);
                    writeUncompressedImage(fileStream2, galleryUri);
                    try {
                        String mimeType = FileHelper.getMimeType(imageUrl.toString(), this.cordova);
                        if ("image/jpeg".equalsIgnoreCase(mimeType)) {
                            String filePath = galleryUri.toString().replace("file://", "");
                            this.exifData = new ExifHelper();
                            this.exifData.createInFile(filePath);
                            if (this.correctOrientation) {
                                ExifInterface exif = new ExifInterface(filePath);
                                rotate = exifToDegrees(exif.getAttributeInt("Orientation", 0));
                            }
                        }
                        localFile = localFile2;
                    } catch (Exception oe) {
                        LOG.w(LOG_TAG, "Unable to read Exif data: " + oe.toString());
                        rotate = 0;
                        localFile = localFile2;
                    }
                } catch (Exception e3) {
                    e = e3;
                    LOG.e(LOG_TAG, "Exception while getting input stream: " + e.toString());
                    return null;
                }
            }
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                InputStream fileStream3 = null;
                try {
                    fileStream3 = FileHelper.getInputStreamFromUriString(galleryUri.toString(), this.cordova);
                    BitmapFactory.decodeStream(fileStream3, null, options);
                    if (fileStream3 != null) {
                        try {
                            fileStream3.close();
                        } catch (IOException e4) {
                            LOG.d(LOG_TAG, "Exception while closing file input stream.");
                        }
                    }
                    if (options.outWidth == 0 || options.outHeight == 0) {
                    }
                    if (this.targetWidth <= 0 && this.targetHeight <= 0) {
                        this.targetWidth = options.outWidth;
                        this.targetHeight = options.outHeight;
                    }
                    boolean rotated = false;
                    if (rotate == 90 || rotate == 270) {
                        rotatedWidth = options.outHeight;
                        rotatedHeight = options.outWidth;
                        rotated = true;
                    } else {
                        rotatedWidth = options.outWidth;
                        rotatedHeight = options.outHeight;
                    }
                    int[] widthHeight = calculateAspectRatio(rotatedWidth, rotatedHeight);
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = calculateSampleSize(rotatedWidth, rotatedHeight, widthHeight[0], widthHeight[1]);
                    try {
                        fileStream3 = FileHelper.getInputStreamFromUriString(galleryUri.toString(), this.cordova);
                        Bitmap unscaledBitmap = BitmapFactory.decodeStream(fileStream3, null, options);
                        if (fileStream3 != null) {
                            try {
                                fileStream3.close();
                            } catch (IOException e5) {
                                LOG.d(LOG_TAG, "Exception while closing file input stream.");
                            }
                        }
                        if (unscaledBitmap == null) {
                            if (localFile == null) {
                                return null;
                            }
                            localFile.delete();
                            return null;
                        }
                        int scaledWidth = !rotated ? widthHeight[0] : widthHeight[1];
                        int scaledHeight = !rotated ? widthHeight[1] : widthHeight[0];
                        Bitmap scaledBitmap = Bitmap.createScaledBitmap(unscaledBitmap, scaledWidth, scaledHeight, true);
                        if (scaledBitmap != unscaledBitmap) {
                            unscaledBitmap.recycle();
                        }
                        if (this.correctOrientation && rotate != 0) {
                            Matrix matrix = new Matrix();
                            matrix.setRotate(rotate);
                            try {
                                scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
                                this.orientationCorrected = true;
                            } catch (OutOfMemoryError e6) {
                                this.orientationCorrected = false;
                            }
                        }
                        if (localFile != null) {
                            localFile.delete();
                        }
                        return scaledBitmap;
                    } finally {
                    }
                } finally {
                }
            } finally {
                if (localFile != null) {
                    localFile.delete();
                }
            }
        } catch (Exception e7) {
            e = e7;
        }
    }

    public int[] calculateAspectRatio(int origWidth, int origHeight) {
        int newWidth = this.targetWidth;
        int newHeight = this.targetHeight;
        if (newWidth <= 0 && newHeight <= 0) {
            newWidth = origWidth;
            newHeight = origHeight;
        } else if (newWidth > 0 && newHeight <= 0) {
            newHeight = (int) ((newWidth / origWidth) * origHeight);
        } else if (newWidth <= 0 && newHeight > 0) {
            newWidth = (int) ((newHeight / origHeight) * origWidth);
        } else {
            double newRatio = newWidth / newHeight;
            double origRatio = origWidth / origHeight;
            if (origRatio > newRatio) {
                newHeight = (newWidth * origHeight) / origWidth;
            } else if (origRatio < newRatio) {
                newWidth = (newHeight * origWidth) / origHeight;
            }
        }
        int[] retval = {newWidth, newHeight};
        return retval;
    }

    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight) {
        float srcAspect = srcWidth / srcHeight;
        float dstAspect = dstWidth / dstHeight;
        return srcAspect > dstAspect ? srcWidth / dstWidth : srcHeight / dstHeight;
    }

    private Cursor queryImgDB(Uri contentStore) {
        return this.cordova.getActivity().getContentResolver().query(contentStore, new String[]{"_id"}, null, null, null);
    }

    private void cleanup(int imageType, Uri oldImage, Uri newImage, Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
        }
        new File(FileHelper.stripFileProtocol(oldImage.toString())).delete();
        checkForDuplicateImage(imageType);
        if (this.saveToPhotoAlbum && newImage != null) {
            scanForGallery(newImage);
        }
        System.gc();
    }

    private void checkForDuplicateImage(int type) {
        int diff = 1;
        Uri contentStore = whichContentStore();
        Cursor cursor = queryImgDB(contentStore);
        int currentNumOfImages = cursor.getCount();
        if (type == 1 && this.saveToPhotoAlbum) {
            diff = 2;
        }
        if (currentNumOfImages - this.numPics == diff) {
            cursor.moveToLast();
            int id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("_id"))).intValue();
            if (diff == 2) {
                id--;
            }
            Uri uri = Uri.parse(contentStore + "/" + id);
            this.cordova.getActivity().getContentResolver().delete(uri, null, null);
            cursor.close();
        }
    }

    private Uri whichContentStore() {
        return Environment.getExternalStorageState().equals("mounted") ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Images.Media.INTERNAL_CONTENT_URI;
    }

    public void processPicture(Bitmap bitmap, int encodingType) {
        ByteArrayOutputStream jpeg_data = new ByteArrayOutputStream();
        Bitmap.CompressFormat compressFormat = encodingType == 0 ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG;
        try {
            if (bitmap.compress(compressFormat, this.mQuality, jpeg_data)) {
                byte[] code = jpeg_data.toByteArray();
                byte[] output = Base64.encode(code, 2);
                String js_out = new String(output);
                this.callbackContext.success(js_out);
            }
        } catch (Exception e) {
            failPicture("Error compressing image.");
        }
    }

    public void failPicture(String err) {
        this.callbackContext.error(err);
    }

    private void scanForGallery(Uri newImage) {
        this.scanMe = newImage;
        if (this.conn != null) {
            this.conn.disconnect();
        }
        this.conn = new MediaScannerConnection(this.cordova.getActivity().getApplicationContext(), this);
        this.conn.connect();
    }

    @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
    public void onMediaScannerConnected() {
        try {
            this.conn.scanFile(this.scanMe.toString(), "image/*");
        } catch (IllegalStateException e) {
            LOG.e(LOG_TAG, "Can't scan file in MediaScanner after taking picture");
        }
    }

    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
    public void onScanCompleted(String path, Uri uri) {
        this.conn.disconnect();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRequestPermissionResult(int requestCode, String[] permissions2, int[] grantResults) throws JSONException {
        for (int r : grantResults) {
            if (r == -1) {
                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, 20));
            }
        }
        switch (requestCode) {
            case 0:
                takePicture(this.destType, this.encodingType);
                break;
            case 1:
                getImage(this.srcType, this.destType, this.encodingType);
                break;
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public Bundle onSaveInstanceState() {
        Bundle state = new Bundle();
        state.putInt("destType", this.destType);
        state.putInt("srcType", this.srcType);
        state.putInt("mQuality", this.mQuality);
        state.putInt("targetWidth", this.targetWidth);
        state.putInt("targetHeight", this.targetHeight);
        state.putInt("encodingType", this.encodingType);
        state.putInt("mediaType", this.mediaType);
        state.putInt("numPics", this.numPics);
        state.putBoolean("allowEdit", this.allowEdit);
        state.putBoolean("correctOrientation", this.correctOrientation);
        state.putBoolean("saveToPhotoAlbum", this.saveToPhotoAlbum);
        if (this.croppedUri != null) {
            state.putString("croppedUri", this.croppedUri.toString());
        }
        if (this.imageUri != null) {
            state.putString("imageUri", this.imageUri.getFileUri().toString());
        }
        return state;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) {
        this.destType = state.getInt("destType");
        this.srcType = state.getInt("srcType");
        this.mQuality = state.getInt("mQuality");
        this.targetWidth = state.getInt("targetWidth");
        this.targetHeight = state.getInt("targetHeight");
        this.encodingType = state.getInt("encodingType");
        this.mediaType = state.getInt("mediaType");
        this.numPics = state.getInt("numPics");
        this.allowEdit = state.getBoolean("allowEdit");
        this.correctOrientation = state.getBoolean("correctOrientation");
        this.saveToPhotoAlbum = state.getBoolean("saveToPhotoAlbum");
        if (state.containsKey("croppedUri")) {
            this.croppedUri = Uri.parse(state.getString("croppedUri"));
        }
        if (state.containsKey("imageUri")) {
            this.imageUri = new CordovaUri(Uri.parse(state.getString("imageUri")));
        }
        this.callbackContext = callbackContext;
    }

    private String getFileNameFromUri(Uri uri) {
        String fullUri = uri.toString();
        String partial_path = fullUri.split("external_files")[1];
        File external_storage = Environment.getExternalStorageDirectory();
        String path = external_storage.getAbsolutePath() + partial_path;
        return path;
    }
}
