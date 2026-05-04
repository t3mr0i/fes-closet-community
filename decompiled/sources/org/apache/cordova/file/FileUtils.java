package org.apache.cordova.file;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.apache.cordova.file.Filesystem;
import org.apache.cordova.file.PendingRequests;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FileUtils extends CordovaPlugin {
    public static final int ACTION_GET_DIRECTORY = 2;
    public static final int ACTION_GET_FILE = 0;
    public static final int ACTION_WRITE = 1;
    private static final String LOG_TAG = "FileUtils";
    public static final int READ = 4;
    public static final int WRITE = 3;
    private static FileUtils filePlugin;
    private ArrayList<Filesystem> filesystems;
    private PendingRequests pendingRequests;
    public static int NOT_FOUND_ERR = 1;
    public static int SECURITY_ERR = 2;
    public static int ABORT_ERR = 3;
    public static int NOT_READABLE_ERR = 4;
    public static int ENCODING_ERR = 5;
    public static int NO_MODIFICATION_ALLOWED_ERR = 6;
    public static int INVALID_STATE_ERR = 7;
    public static int SYNTAX_ERR = 8;
    public static int INVALID_MODIFICATION_ERR = 9;
    public static int QUOTA_EXCEEDED_ERR = 10;
    public static int TYPE_MISMATCH_ERR = 11;
    public static int PATH_EXISTS_ERR = 12;
    public static int UNKNOWN_ERR = 1000;
    private boolean configured = false;
    private String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    private interface FileOp {
        void run(JSONArray jSONArray) throws Exception;
    }

    public void registerFilesystem(Filesystem fs) {
        if (fs != null && filesystemForName(fs.name) == null) {
            this.filesystems.add(fs);
        }
    }

    private Filesystem filesystemForName(String name) {
        Iterator<Filesystem> it = this.filesystems.iterator();
        while (it.hasNext()) {
            Filesystem fs = it.next();
            if (fs != null && fs.name != null && fs.name.equals(name)) {
                return fs;
            }
        }
        return null;
    }

    protected String[] getExtraFileSystemsPreference(Activity activity) {
        String fileSystemsStr = this.preferences.getString("androidextrafilesystems", "files,files-external,documents,sdcard,cache,cache-external,root");
        return fileSystemsStr.split(",");
    }

    protected void registerExtraFileSystems(String[] filesystems, HashMap<String, String> availableFileSystems) {
        HashSet<String> installedFileSystems = new HashSet<>();
        for (String fsName : filesystems) {
            if (!installedFileSystems.contains(fsName)) {
                String fsRoot = availableFileSystems.get(fsName);
                if (fsRoot != null) {
                    File newRoot = new File(fsRoot);
                    if (newRoot.mkdirs() || newRoot.isDirectory()) {
                        registerFilesystem(new LocalFilesystem(fsName, this.webView.getContext(), this.webView.getResourceApi(), newRoot));
                        installedFileSystems.add(fsName);
                    } else {
                        Log.d(LOG_TAG, "Unable to create root dir for filesystem \"" + fsName + "\", skipping");
                    }
                } else {
                    Log.d(LOG_TAG, "Unrecognized extra filesystem identifier: " + fsName);
                }
            }
        }
    }

    protected HashMap<String, String> getAvailableFileSystems(Activity activity) {
        Context context = activity.getApplicationContext();
        HashMap<String, String> availableFileSystems = new HashMap<>();
        availableFileSystems.put("files", context.getFilesDir().getAbsolutePath());
        availableFileSystems.put("documents", new File(context.getFilesDir(), "Documents").getAbsolutePath());
        availableFileSystems.put("cache", context.getCacheDir().getAbsolutePath());
        availableFileSystems.put("root", "/");
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                availableFileSystems.put("files-external", context.getExternalFilesDir(null).getAbsolutePath());
                availableFileSystems.put("sdcard", Environment.getExternalStorageDirectory().getAbsolutePath());
                availableFileSystems.put("cache-external", context.getExternalCacheDir().getAbsolutePath());
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, "External storage unavailable, check to see if USB Mass Storage Mode is on");
            }
        }
        return availableFileSystems;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.filesystems = new ArrayList<>();
        this.pendingRequests = new PendingRequests();
        String persistentRoot = null;
        Activity activity = cordova.getActivity();
        String packageName = activity.getPackageName();
        String location = this.preferences.getString("androidpersistentfilelocation", "internal");
        String tempRoot = activity.getCacheDir().getAbsolutePath();
        if ("internal".equalsIgnoreCase(location)) {
            persistentRoot = activity.getFilesDir().getAbsolutePath() + "/files/";
            this.configured = true;
        } else if ("compatibility".equalsIgnoreCase(location)) {
            if (Environment.getExternalStorageState().equals("mounted")) {
                persistentRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
                tempRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + packageName + "/cache/";
            } else {
                persistentRoot = "/data/data/" + packageName;
            }
            this.configured = true;
        }
        if (this.configured) {
            File tmpRootFile = new File(tempRoot);
            File persistentRootFile = new File(persistentRoot);
            tmpRootFile.mkdirs();
            persistentRootFile.mkdirs();
            registerFilesystem(new LocalFilesystem("temporary", webView.getContext(), webView.getResourceApi(), tmpRootFile));
            registerFilesystem(new LocalFilesystem("persistent", webView.getContext(), webView.getResourceApi(), persistentRootFile));
            registerFilesystem(new ContentFilesystem(webView.getContext(), webView.getResourceApi()));
            registerFilesystem(new AssetFilesystem(webView.getContext().getAssets(), webView.getResourceApi()));
            registerExtraFileSystems(getExtraFileSystemsPreference(activity), getAvailableFileSystems(activity));
            if (filePlugin == null) {
                filePlugin = this;
                return;
            }
            return;
        }
        Log.e(LOG_TAG, "File plugin configuration error: Please set AndroidPersistentFileLocation in config.xml to one of \"internal\" (for new applications) or \"compatibility\" (for compatibility with previous versions)");
        activity.finish();
    }

    public static FileUtils getFilePlugin() {
        return filePlugin;
    }

    private Filesystem filesystemForURL(LocalFilesystemURL localURL) {
        if (localURL == null) {
            return null;
        }
        return filesystemForName(localURL.fsName);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public Uri remapUri(Uri uri) {
        if (!LocalFilesystemURL.FILESYSTEM_PROTOCOL.equals(uri.getScheme())) {
            return null;
        }
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(uri);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                return null;
            }
            String path = fs.filesystemPathForURL(inputURL);
            if (path != null) {
                return Uri.parse("file://" + fs.filesystemPathForURL(inputURL));
            }
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, final String rawArgs, final CallbackContext callbackContext) {
        if (!this.configured) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "File plugin is not configured. Please see the README.md file for details on how to update config.xml"));
            return true;
        }
        if (action.equals("testSaveLocationExists")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.1
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) {
                    boolean b = DirectoryManager.testSaveLocationExists();
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, b));
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("getFreeDiskSpace")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.2
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) {
                    long l = DirectoryManager.getFreeExternalStorageSpace();
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, l));
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("testFileExists")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.3
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException {
                    String fname = args.getString(0);
                    boolean b = DirectoryManager.testFileExists(fname);
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, b));
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("testDirectoryExists")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.4
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException {
                    String fname = args.getString(0);
                    boolean b = DirectoryManager.testFileExists(fname);
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, b));
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("readAsText")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.5
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, MalformedURLException {
                    String encoding = args.getString(1);
                    int start = args.getInt(2);
                    int end = args.getInt(3);
                    String fname = args.getString(0);
                    FileUtils.this.readFileAs(fname, start, end, callbackContext, encoding, 1);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("readAsDataURL")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.6
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, MalformedURLException {
                    int start = args.getInt(1);
                    int end = args.getInt(2);
                    String fname = args.getString(0);
                    FileUtils.this.readFileAs(fname, start, end, callbackContext, null, -1);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("readAsArrayBuffer")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.7
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, MalformedURLException {
                    int start = args.getInt(1);
                    int end = args.getInt(2);
                    String fname = args.getString(0);
                    FileUtils.this.readFileAs(fname, start, end, callbackContext, null, 6);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("readAsBinaryString")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.8
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, MalformedURLException {
                    int start = args.getInt(1);
                    int end = args.getInt(2);
                    String fname = args.getString(0);
                    FileUtils.this.readFileAs(fname, start, end, callbackContext, null, 7);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("write")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.9
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, IllegalAccessException, NoSuchMethodException, NoModificationAllowedException, SecurityException, IOException, IllegalArgumentException, InvocationTargetException {
                    String fname = args.getString(0);
                    String nativeURL = FileUtils.this.resolveLocalFileSystemURI(fname).getString("nativeURL");
                    String data = args.getString(1);
                    int offset = args.getInt(2);
                    Boolean isBinary = Boolean.valueOf(args.getBoolean(3));
                    if (FileUtils.this.needPermission(nativeURL, 3)) {
                        FileUtils.this.getWritePermission(rawArgs, 1, callbackContext);
                    } else {
                        long fileSize = FileUtils.this.write(fname, data, offset, isBinary.booleanValue());
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, fileSize));
                    }
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("truncate")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.10
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, NoModificationAllowedException, IOException {
                    String fname = args.getString(0);
                    int offset = args.getInt(1);
                    long fileSize = FileUtils.this.truncateFile(fname, offset);
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, fileSize));
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("requestAllFileSystems")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.11
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, IOException {
                    callbackContext.success(FileUtils.this.requestAllFileSystems());
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("requestAllPaths")) {
            this.cordova.getThreadPool().execute(new Runnable() { // from class: org.apache.cordova.file.FileUtils.12
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        callbackContext.success(FileUtils.this.requestAllPaths());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return true;
        }
        if (action.equals("requestFileSystem")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.13
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException {
                    int fstype = args.getInt(0);
                    long requiredSize = args.optLong(1);
                    FileUtils.this.requestFileSystem(fstype, requiredSize, callbackContext);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("resolveLocalFileSystemURI")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.14
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, IOException {
                    String fname = args.getString(0);
                    JSONObject obj = FileUtils.this.resolveLocalFileSystemURI(fname);
                    callbackContext.success(obj);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("getFileMetadata")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.15
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, MalformedURLException, FileNotFoundException {
                    String fname = args.getString(0);
                    JSONObject obj = FileUtils.this.getFileMetadata(fname);
                    callbackContext.success(obj);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("getParent")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.16
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, IOException {
                    String fname = args.getString(0);
                    JSONObject obj = FileUtils.this.getParent(fname);
                    callbackContext.success(obj);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("getDirectory")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.17
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, IllegalAccessException, FileExistsException, NoSuchMethodException, EncodingException, SecurityException, IOException, TypeMismatchException, IllegalArgumentException, InvocationTargetException {
                    String dirname = args.getString(0);
                    String path = args.getString(1);
                    String nativeURL = FileUtils.this.resolveLocalFileSystemURI(dirname).getString("nativeURL");
                    boolean containsCreate = args.isNull(2) ? false : args.getJSONObject(2).optBoolean("create", false);
                    if (containsCreate && FileUtils.this.needPermission(nativeURL, 3)) {
                        FileUtils.this.getWritePermission(rawArgs, 2, callbackContext);
                    } else if (containsCreate || !FileUtils.this.needPermission(nativeURL, 4)) {
                        JSONObject obj = FileUtils.this.getFile(dirname, path, args.optJSONObject(2), true);
                        callbackContext.success(obj);
                    } else {
                        FileUtils.this.getReadPermission(rawArgs, 2, callbackContext);
                    }
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("getFile")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.18
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, IllegalAccessException, FileExistsException, NoSuchMethodException, EncodingException, SecurityException, IOException, TypeMismatchException, IllegalArgumentException, InvocationTargetException {
                    String dirname = args.getString(0);
                    String path = args.getString(1);
                    String nativeURL = FileUtils.this.resolveLocalFileSystemURI(dirname).getString("nativeURL");
                    boolean containsCreate = args.isNull(2) ? false : args.getJSONObject(2).optBoolean("create", false);
                    if (containsCreate && FileUtils.this.needPermission(nativeURL, 3)) {
                        FileUtils.this.getWritePermission(rawArgs, 0, callbackContext);
                    } else if (containsCreate || !FileUtils.this.needPermission(nativeURL, 4)) {
                        JSONObject obj = FileUtils.this.getFile(dirname, path, args.optJSONObject(2), false);
                        callbackContext.success(obj);
                    } else {
                        FileUtils.this.getReadPermission(rawArgs, 0, callbackContext);
                    }
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals(ProductAction.ACTION_REMOVE)) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.19
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, InvalidModificationException, MalformedURLException, NoModificationAllowedException {
                    String fname = args.getString(0);
                    boolean success = FileUtils.this.remove(fname);
                    if (success) {
                        callbackContext.success();
                    } else {
                        callbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
                    }
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("removeRecursively")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.20
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, MalformedURLException, FileExistsException, NoModificationAllowedException {
                    String fname = args.getString(0);
                    boolean success = FileUtils.this.removeRecursively(fname);
                    if (success) {
                        callbackContext.success();
                    } else {
                        callbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
                    }
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("moveTo")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.21
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, InvalidModificationException, FileExistsException, EncodingException, NoModificationAllowedException, IOException {
                    String fname = args.getString(0);
                    String newParent = args.getString(1);
                    String newName = args.getString(2);
                    JSONObject entry = FileUtils.this.transferTo(fname, newParent, newName, true);
                    callbackContext.success(entry);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("copyTo")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.22
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, InvalidModificationException, FileExistsException, EncodingException, NoModificationAllowedException, IOException {
                    String fname = args.getString(0);
                    String newParent = args.getString(1);
                    String newName = args.getString(2);
                    JSONObject entry = FileUtils.this.transferTo(fname, newParent, newName, false);
                    callbackContext.success(entry);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("readEntries")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.23
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, MalformedURLException, FileNotFoundException {
                    String fname = args.getString(0);
                    JSONArray entries = FileUtils.this.readEntries(fname);
                    callbackContext.success(entries);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        if (action.equals("_getLocalFilesystemPath")) {
            threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.24
                @Override // org.apache.cordova.file.FileUtils.FileOp
                public void run(JSONArray args) throws JSONException, MalformedURLException, FileNotFoundException {
                    String localURLstr = args.getString(0);
                    String fname = FileUtils.this.filesystemPathForURL(localURLstr);
                    callbackContext.success(fname);
                }
            }, rawArgs, callbackContext);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getReadPermission(String rawArgs, int action, CallbackContext callbackContext) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int requestCode = this.pendingRequests.createRequest(rawArgs, action, callbackContext);
        PermissionHelper.requestPermission(this, requestCode, "android.permission.READ_EXTERNAL_STORAGE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getWritePermission(String rawArgs, int action, CallbackContext callbackContext) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int requestCode = this.pendingRequests.createRequest(rawArgs, action, callbackContext);
        PermissionHelper.requestPermission(this, requestCode, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    private boolean hasReadPermission() {
        return PermissionHelper.hasPermission(this, "android.permission.READ_EXTERNAL_STORAGE");
    }

    private boolean hasWritePermission() {
        return PermissionHelper.hasPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean needPermission(String nativeURL, int permissionType) throws JSONException {
        JSONObject j = requestAllPaths();
        String[] allowedStorageDirectories = {j.getString("applicationStorageDirectory"), j.getString("externalApplicationStorageDirectory")};
        if (permissionType == 4 && hasReadPermission()) {
            return false;
        }
        if (permissionType == 3 && hasWritePermission()) {
            return false;
        }
        for (String directory : allowedStorageDirectories) {
            if (nativeURL.startsWith(directory)) {
                return false;
            }
        }
        return true;
    }

    public LocalFilesystemURL resolveNativeUri(Uri nativeUri) {
        LocalFilesystemURL localURL = null;
        Iterator<Filesystem> it = this.filesystems.iterator();
        while (it.hasNext()) {
            Filesystem fs = it.next();
            LocalFilesystemURL url = fs.toLocalUri(nativeUri);
            if (url != null && (localURL == null || url.uri.toString().length() < localURL.toString().length())) {
                localURL = url;
            }
        }
        return localURL;
    }

    public String filesystemPathForURL(String localURLstr) throws MalformedURLException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(localURLstr);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            return fs.filesystemPathForURL(inputURL);
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    public LocalFilesystemURL filesystemURLforLocalPath(String localPath) {
        LocalFilesystemURL localURL = null;
        int shortestFullPath = 0;
        Iterator<Filesystem> it = this.filesystems.iterator();
        while (it.hasNext()) {
            Filesystem fs = it.next();
            LocalFilesystemURL url = fs.URLforFilesystemPath(localPath);
            if (url != null && (localURL == null || url.path.length() < shortestFullPath)) {
                localURL = url;
                shortestFullPath = url.path.length();
            }
        }
        return localURL;
    }

    private void threadhelper(final FileOp f, final String rawArgs, final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() { // from class: org.apache.cordova.file.FileUtils.25
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONArray args = new JSONArray(rawArgs);
                    f.run(args);
                } catch (Exception e) {
                    if (e instanceof EncodingException) {
                        callbackContext.error(FileUtils.ENCODING_ERR);
                        return;
                    }
                    if (e instanceof FileNotFoundException) {
                        callbackContext.error(FileUtils.NOT_FOUND_ERR);
                        return;
                    }
                    if (e instanceof FileExistsException) {
                        callbackContext.error(FileUtils.PATH_EXISTS_ERR);
                        return;
                    }
                    if (e instanceof NoModificationAllowedException) {
                        callbackContext.error(FileUtils.NO_MODIFICATION_ALLOWED_ERR);
                        return;
                    }
                    if (e instanceof InvalidModificationException) {
                        callbackContext.error(FileUtils.INVALID_MODIFICATION_ERR);
                        return;
                    }
                    if (e instanceof MalformedURLException) {
                        callbackContext.error(FileUtils.ENCODING_ERR);
                        return;
                    }
                    if (e instanceof IOException) {
                        callbackContext.error(FileUtils.INVALID_MODIFICATION_ERR);
                        return;
                    }
                    if (e instanceof EncodingException) {
                        callbackContext.error(FileUtils.ENCODING_ERR);
                        return;
                    }
                    if (e instanceof TypeMismatchException) {
                        callbackContext.error(FileUtils.TYPE_MISMATCH_ERR);
                    } else if (e instanceof JSONException) {
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
                    } else {
                        e.printStackTrace();
                        callbackContext.error(FileUtils.UNKNOWN_ERR);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject resolveLocalFileSystemURI(String uriString) throws JSONException, IOException {
        if (uriString == null) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
        Uri uri = Uri.parse(uriString);
        boolean isNativeUri = false;
        LocalFilesystemURL inputURL = LocalFilesystemURL.parse(uri);
        if (inputURL == null) {
            inputURL = resolveNativeUri(uri);
            isNativeUri = true;
        }
        try {
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            if (fs.exists(inputURL)) {
                if (!isNativeUri) {
                    inputURL = fs.toLocalUri(fs.toNativeUri(inputURL));
                }
                return fs.getEntryForLocalURL(inputURL);
            }
            throw new FileNotFoundException();
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray readEntries(String baseURLstr) throws JSONException, MalformedURLException, FileNotFoundException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(baseURLstr);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            return fs.readEntriesAtLocalURL(inputURL);
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject transferTo(String srcURLstr, String destURLstr, String newName, boolean move) throws JSONException, InvalidModificationException, FileExistsException, EncodingException, NoModificationAllowedException, IOException {
        if (srcURLstr == null || destURLstr == null) {
            throw new FileNotFoundException();
        }
        LocalFilesystemURL srcURL = LocalFilesystemURL.parse(srcURLstr);
        LocalFilesystemURL destURL = LocalFilesystemURL.parse(destURLstr);
        Filesystem srcFs = filesystemForURL(srcURL);
        Filesystem destFs = filesystemForURL(destURL);
        if (newName != null && newName.contains(":")) {
            throw new EncodingException("Bad file name");
        }
        return destFs.copyFileToURL(destURL, newName, srcFs, srcURL, move);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeRecursively(String baseURLstr) throws MalformedURLException, FileExistsException, NoModificationAllowedException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(baseURLstr);
            if ("".equals(inputURL.path) || "/".equals(inputURL.path)) {
                throw new NoModificationAllowedException("You can't delete the root directory");
            }
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            return fs.recursiveRemoveFileAtLocalURL(inputURL);
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean remove(String baseURLstr) throws InvalidModificationException, MalformedURLException, NoModificationAllowedException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(baseURLstr);
            if ("".equals(inputURL.path) || "/".equals(inputURL.path)) {
                throw new NoModificationAllowedException("You can't delete the root directory");
            }
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            return fs.removeFileAtLocalURL(inputURL);
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getFile(String baseURLstr, String path, JSONObject options, boolean directory) throws JSONException, FileExistsException, EncodingException, IOException, TypeMismatchException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(baseURLstr);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            return fs.getFileForLocalURL(inputURL, path, options, directory);
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getParent(String baseURLstr) throws JSONException, IOException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(baseURLstr);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            return fs.getParentForLocalURL(inputURL);
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getFileMetadata(String baseURLstr) throws JSONException, MalformedURLException, FileNotFoundException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(baseURLstr);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            return fs.getFileMetadataForLocalURL(inputURL);
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFileSystem(int type, long requiredSize, CallbackContext callbackContext) throws JSONException {
        Filesystem rootFs = null;
        try {
            rootFs = this.filesystems.get(type);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        if (rootFs == null) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, NOT_FOUND_ERR));
            return;
        }
        long availableSize = 0;
        if (requiredSize > 0) {
            availableSize = rootFs.getFreeSpaceInBytes();
        }
        if (availableSize < requiredSize) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, QUOTA_EXCEEDED_ERR));
            return;
        }
        JSONObject fs = new JSONObject();
        fs.put("name", rootFs.name);
        fs.put("root", rootFs.getRootEntry());
        callbackContext.success(fs);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray requestAllFileSystems() throws JSONException, IOException {
        JSONArray ret = new JSONArray();
        Iterator<Filesystem> it = this.filesystems.iterator();
        while (it.hasNext()) {
            Filesystem fs = it.next();
            ret.put(fs.getRootEntry());
        }
        return ret;
    }

    private static String toDirUrl(File f) {
        return Uri.fromFile(f).toString() + '/';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject requestAllPaths() throws JSONException {
        Context context = this.cordova.getActivity();
        JSONObject ret = new JSONObject();
        ret.put("applicationDirectory", "file:///android_asset/");
        ret.put("applicationStorageDirectory", toDirUrl(context.getFilesDir().getParentFile()));
        ret.put("dataDirectory", toDirUrl(context.getFilesDir()));
        ret.put("cacheDirectory", toDirUrl(context.getCacheDir()));
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                ret.put("externalApplicationStorageDirectory", toDirUrl(context.getExternalFilesDir(null).getParentFile()));
                ret.put("externalDataDirectory", toDirUrl(context.getExternalFilesDir(null)));
                ret.put("externalCacheDirectory", toDirUrl(context.getExternalCacheDir()));
                ret.put("externalRootDirectory", toDirUrl(Environment.getExternalStorageDirectory()));
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, "Unable to access these paths, most liklely due to USB storage");
            }
        }
        return ret;
    }

    public JSONObject getEntryForFile(File file) throws JSONException {
        Iterator<Filesystem> it = this.filesystems.iterator();
        while (it.hasNext()) {
            Filesystem fs = it.next();
            JSONObject entry = fs.makeEntryForFile(file);
            if (entry != null) {
                return entry;
            }
        }
        return null;
    }

    @Deprecated
    public static JSONObject getEntry(File file) throws JSONException {
        if (getFilePlugin() != null) {
            return getFilePlugin().getEntryForFile(file);
        }
        return null;
    }

    public void readFileAs(String srcURLstr, int start, int end, final CallbackContext callbackContext, final String encoding, final int resultType) throws MalformedURLException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(srcURLstr);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            fs.readFileAtURL(inputURL, start, end, new Filesystem.ReadFileCallback() { // from class: org.apache.cordova.file.FileUtils.26
                @Override // org.apache.cordova.file.Filesystem.ReadFileCallback
                public void handleData(InputStream inputStream, String contentType) throws IOException {
                    PluginResult result;
                    try {
                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        byte[] buffer = new byte[8192];
                        while (true) {
                            int bytesRead = inputStream.read(buffer, 0, 8192);
                            if (bytesRead <= 0) {
                                break;
                            } else {
                                os.write(buffer, 0, bytesRead);
                            }
                        }
                        switch (resultType) {
                            case 1:
                                result = new PluginResult(PluginResult.Status.OK, os.toString(encoding));
                                break;
                            case 6:
                                result = new PluginResult(PluginResult.Status.OK, os.toByteArray());
                                break;
                            case 7:
                                result = new PluginResult(PluginResult.Status.OK, os.toByteArray(), true);
                                break;
                            default:
                                byte[] base64 = Base64.encode(os.toByteArray(), 2);
                                String s = "data:" + contentType + ";base64," + new String(base64, "US-ASCII");
                                result = new PluginResult(PluginResult.Status.OK, s);
                                break;
                        }
                        callbackContext.sendPluginResult(result);
                    } catch (IOException e) {
                        Log.d(FileUtils.LOG_TAG, e.getLocalizedMessage());
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, FileUtils.NOT_READABLE_ERR));
                    }
                }
            });
        } catch (FileNotFoundException e) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, NOT_FOUND_ERR));
        } catch (IOException e2) {
            Log.d(LOG_TAG, e2.getLocalizedMessage());
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.IO_EXCEPTION, NOT_READABLE_ERR));
        } catch (IllegalArgumentException e3) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    public long write(String srcURLstr, String data, int offset, boolean isBinary) throws NoModificationAllowedException, IOException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(srcURLstr);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            long x = fs.writeToFileAtURL(inputURL, data, offset, isBinary);
            Log.d("TEST", srcURLstr + ": " + x);
            return x;
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long truncateFile(String srcURLstr, long size) throws NoModificationAllowedException, IOException {
        try {
            LocalFilesystemURL inputURL = LocalFilesystemURL.parse(srcURLstr);
            Filesystem fs = filesystemForURL(inputURL);
            if (fs == null) {
                throw new MalformedURLException("No installed handlers for this URL");
            }
            return fs.truncateFileAtURL(inputURL, size);
        } catch (IllegalArgumentException e) {
            throw new MalformedURLException("Unrecognized filesystem URL");
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
        final PendingRequests.Request req = this.pendingRequests.getAndRemove(requestCode);
        if (req != null) {
            for (int r : grantResults) {
                if (r == -1) {
                    req.getCallbackContext().sendPluginResult(new PluginResult(PluginResult.Status.ERROR, SECURITY_ERR));
                    return;
                }
            }
            switch (req.getAction()) {
                case 0:
                    threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.27
                        @Override // org.apache.cordova.file.FileUtils.FileOp
                        public void run(JSONArray args) throws JSONException, FileExistsException, EncodingException, IOException, TypeMismatchException {
                            String dirname = args.getString(0);
                            String path = args.getString(1);
                            JSONObject obj = FileUtils.this.getFile(dirname, path, args.optJSONObject(2), false);
                            req.getCallbackContext().success(obj);
                        }
                    }, req.getRawArgs(), req.getCallbackContext());
                    break;
                case 1:
                    threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.29
                        @Override // org.apache.cordova.file.FileUtils.FileOp
                        public void run(JSONArray args) throws JSONException, NoModificationAllowedException, IOException {
                            String fname = args.getString(0);
                            String data = args.getString(1);
                            int offset = args.getInt(2);
                            Boolean isBinary = Boolean.valueOf(args.getBoolean(3));
                            long fileSize = FileUtils.this.write(fname, data, offset, isBinary.booleanValue());
                            req.getCallbackContext().sendPluginResult(new PluginResult(PluginResult.Status.OK, fileSize));
                        }
                    }, req.getRawArgs(), req.getCallbackContext());
                    break;
                case 2:
                    threadhelper(new FileOp() { // from class: org.apache.cordova.file.FileUtils.28
                        @Override // org.apache.cordova.file.FileUtils.FileOp
                        public void run(JSONArray args) throws JSONException, FileExistsException, EncodingException, IOException, TypeMismatchException {
                            String dirname = args.getString(0);
                            String path = args.getString(1);
                            JSONObject obj = FileUtils.this.getFile(dirname, path, args.optJSONObject(2), true);
                            req.getCallbackContext().success(obj);
                        }
                    }, req.getRawArgs(), req.getCallbackContext());
                    break;
            }
            return;
        }
        Log.d(LOG_TAG, "Received permission callback for unknown request code");
    }
}
