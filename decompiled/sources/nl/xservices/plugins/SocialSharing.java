package nl.xservices.plugins;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.util.Base64;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SocialSharing extends CordovaPlugin {
    private static final String ACTION_AVAILABLE_EVENT = "available";
    private static final String ACTION_CAN_SHARE_VIA = "canShareVia";
    private static final String ACTION_CAN_SHARE_VIA_EMAIL = "canShareViaEmail";
    private static final String ACTION_SHARE_EVENT = "share";
    private static final String ACTION_SHARE_VIA = "shareVia";
    private static final String ACTION_SHARE_VIA_EMAIL_EVENT = "shareViaEmail";
    private static final String ACTION_SHARE_VIA_FACEBOOK_EVENT = "shareViaFacebook";
    private static final String ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT = "shareViaFacebookWithPasteMessageHint";
    private static final String ACTION_SHARE_VIA_INSTAGRAM_EVENT = "shareViaInstagram";
    private static final String ACTION_SHARE_VIA_SMS_EVENT = "shareViaSMS";
    private static final String ACTION_SHARE_VIA_TWITTER_EVENT = "shareViaTwitter";
    private static final String ACTION_SHARE_VIA_WHATSAPP_EVENT = "shareViaWhatsApp";
    private static final String ACTION_SHARE_WITH_OPTIONS_EVENT = "shareWithOptions";
    private static final int ACTIVITY_CODE_SENDVIAEMAIL = 3;
    private static final int ACTIVITY_CODE_SENDVIAWHATSAPP = 4;
    private static final int ACTIVITY_CODE_SEND__BOOLRESULT = 1;
    private static final int ACTIVITY_CODE_SEND__OBJECT = 2;
    private static final Map<String, String> MIME_Map = new HashMap();
    private CallbackContext _callbackContext;
    private String pasteMessage;

    private abstract class SocialSharingRunnable implements Runnable {
        public CallbackContext callbackContext;

        SocialSharingRunnable(CallbackContext cb) {
            this.callbackContext = cb;
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this._callbackContext = callbackContext;
        this.pasteMessage = null;
        if (ACTION_AVAILABLE_EVENT.equals(action)) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            return true;
        }
        if ("share".equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), null, null, false, true);
        }
        if (ACTION_SHARE_WITH_OPTIONS_EVENT.equals(action)) {
            return shareWithOptions(callbackContext, args.getJSONObject(0));
        }
        if (ACTION_SHARE_VIA_TWITTER_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "twitter", null, false, true);
        }
        if (ACTION_SHARE_VIA_FACEBOOK_EVENT.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "com.facebook.katana", null, false, true, "com.facebook.composer.shareintent");
        }
        if (ACTION_SHARE_VIA_FACEBOOK_WITH_PASTEMESSAGEHINT.equals(action)) {
            this.pasteMessage = args.getString(4);
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "com.facebook.katana", null, false, true, "com.facebook.composer.shareintent");
        }
        if (ACTION_SHARE_VIA_WHATSAPP_EVENT.equals(action)) {
            if (notEmpty(args.getString(4))) {
                return shareViaWhatsAppDirectly(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4));
            }
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "whatsapp", null, false, true);
        }
        if (ACTION_SHARE_VIA_INSTAGRAM_EVENT.equals(action)) {
            if (notEmpty(args.getString(0))) {
                copyHintToClipboard(args.getString(0), "Instagram paste message");
            }
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), "instagram", null, false, true);
        }
        if (ACTION_CAN_SHARE_VIA.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4), null, true, true);
        }
        if (ACTION_CAN_SHARE_VIA_EMAIL.equals(action)) {
            if (isEmailAvailable()) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                return true;
            }
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "not available"));
            return false;
        }
        if (ACTION_SHARE_VIA.equals(action)) {
            return doSendIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.getString(3), args.getString(4), null, false, true);
        }
        if (ACTION_SHARE_VIA_SMS_EVENT.equals(action)) {
            return invokeSMSIntent(callbackContext, args.getJSONObject(0), args.getString(1));
        }
        if (ACTION_SHARE_VIA_EMAIL_EVENT.equals(action)) {
            return invokeEmailIntent(callbackContext, args.getString(0), args.getString(1), args.getJSONArray(2), args.isNull(3) ? null : args.getJSONArray(3), args.isNull(4) ? null : args.getJSONArray(4), args.isNull(5) ? null : args.getJSONArray(5));
        }
        callbackContext.error("socialSharing." + action + " is not a supported function. Did you mean 'share'?");
        return false;
    }

    private boolean isEmailAvailable() {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "someone@domain.com", null));
        return this.cordova.getActivity().getPackageManager().queryIntentActivities(intent, 0).size() > 0;
    }

    private boolean invokeEmailIntent(CallbackContext callbackContext, final String message, final String subject, final JSONArray to, final JSONArray cc, final JSONArray bcc, final JSONArray files) throws JSONException {
        this.cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) { // from class: nl.xservices.plugins.SocialSharing.1
            @Override // java.lang.Runnable
            public void run() {
                String dir;
                final Intent draft = new Intent("android.intent.action.SEND_MULTIPLE");
                if (SocialSharing.notEmpty(message)) {
                    Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*", 32);
                    if (htmlPattern.matcher(message).matches()) {
                        draft.putExtra("android.intent.extra.TEXT", Html.fromHtml(message));
                        draft.setType("text/html");
                    } else {
                        draft.putExtra("android.intent.extra.TEXT", message);
                        draft.setType("text/plain");
                    }
                }
                if (SocialSharing.notEmpty(subject)) {
                    draft.putExtra("android.intent.extra.SUBJECT", subject);
                }
                try {
                    if (to != null && to.length() > 0) {
                        draft.putExtra("android.intent.extra.EMAIL", SocialSharing.toStringArray(to));
                    }
                    if (cc != null && cc.length() > 0) {
                        draft.putExtra("android.intent.extra.CC", SocialSharing.toStringArray(cc));
                    }
                    if (bcc != null && bcc.length() > 0) {
                        draft.putExtra("android.intent.extra.BCC", SocialSharing.toStringArray(bcc));
                    }
                    if (files.length() > 0 && (dir = SocialSharing.this.getDownloadDir()) != null) {
                        ArrayList<Uri> fileUris = new ArrayList<>();
                        for (int i = 0; i < files.length(); i++) {
                            Uri fileUri = SocialSharing.this.getFileUriAndSetType(draft, dir, files.getString(i), subject, i);
                            if (fileUri != null) {
                                fileUris.add(fileUri);
                            }
                        }
                        if (!fileUris.isEmpty()) {
                            draft.putExtra("android.intent.extra.STREAM", fileUris);
                        }
                    }
                    draft.addFlags(268435456);
                    draft.setType("application/octet-stream");
                    SocialSharing.this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: nl.xservices.plugins.SocialSharing.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SocialSharing.this.cordova.startActivityForResult(this, Intent.createChooser(draft, "Choose Email App"), 3);
                        }
                    });
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDownloadDir() throws IOException {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        String dir = this.webView.getContext().getExternalFilesDir(null) + "/socialsharing-downloads";
        createOrCleanDir(dir);
        return dir;
    }

    private boolean shareWithOptions(CallbackContext callbackContext, JSONObject jsonObject) {
        return doSendIntent(callbackContext, jsonObject.optString("message", null), jsonObject.optString("subject", null), jsonObject.optJSONArray("files") == null ? new JSONArray() : jsonObject.optJSONArray("files"), jsonObject.optString("url", null), null, jsonObject.optString("chooserTitle", null), false, false);
    }

    private boolean doSendIntent(CallbackContext callbackContext, String msg, String subject, JSONArray files, String url, String appPackageName, String chooserTitle, boolean peek, boolean boolResult) {
        return doSendIntent(callbackContext, msg, subject, files, url, appPackageName, chooserTitle, peek, boolResult, null);
    }

    private boolean doSendIntent(CallbackContext callbackContext, String msg, String subject, JSONArray files, String url, String appPackageName, String chooserTitle, boolean peek, boolean boolResult, String appName) {
        CordovaInterface mycordova = this.cordova;
        this.cordova.getThreadPool().execute(new AnonymousClass2(callbackContext, msg, files, subject, url, appPackageName, appName, peek, mycordova, this, chooserTitle, boolResult));
        return true;
    }

    /* renamed from: nl.xservices.plugins.SocialSharing$2, reason: invalid class name */
    class AnonymousClass2 extends SocialSharingRunnable {
        final /* synthetic */ String val$appName;
        final /* synthetic */ String val$appPackageName;
        final /* synthetic */ boolean val$boolResult;
        final /* synthetic */ String val$chooserTitle;
        final /* synthetic */ JSONArray val$files;
        final /* synthetic */ String val$msg;
        final /* synthetic */ CordovaInterface val$mycordova;
        final /* synthetic */ boolean val$peek;
        final /* synthetic */ CordovaPlugin val$plugin;
        final /* synthetic */ String val$subject;
        final /* synthetic */ String val$url;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(CallbackContext cb, String str, JSONArray jSONArray, String str2, String str3, String str4, String str5, boolean z, CordovaInterface cordovaInterface, CordovaPlugin cordovaPlugin, String str6, boolean z2) {
            super(cb);
            this.val$msg = str;
            this.val$files = jSONArray;
            this.val$subject = str2;
            this.val$url = str3;
            this.val$appPackageName = str4;
            this.val$appName = str5;
            this.val$peek = z;
            this.val$mycordova = cordovaInterface;
            this.val$plugin = cordovaPlugin;
            this.val$chooserTitle = str6;
            this.val$boolResult = z2;
        }

        @Override // java.lang.Runnable
        public void run() {
            String dir;
            String message = this.val$msg;
            boolean hasMultipleAttachments = this.val$files.length() > 1;
            final Intent sendIntent = new Intent(hasMultipleAttachments ? "android.intent.action.SEND_MULTIPLE" : "android.intent.action.SEND");
            sendIntent.addFlags(524288);
            try {
                if (this.val$files.length() > 0 && !"".equals(this.val$files.getString(0)) && (dir = SocialSharing.this.getDownloadDir()) != null) {
                    ArrayList<Uri> fileUris = new ArrayList<>();
                    Uri fileUri = null;
                    for (int i = 0; i < this.val$files.length(); i++) {
                        fileUri = SocialSharing.this.getFileUriAndSetType(sendIntent, dir, this.val$files.getString(i), this.val$subject, i);
                        if (Build.VERSION.SDK_INT > 23) {
                            fileUri = FileProvider.getUriForFile(SocialSharing.this.webView.getContext(), SocialSharing.this.cordova.getActivity().getPackageName() + ".sharing.provider", new File(fileUri.getPath()));
                        }
                        if (fileUri != null) {
                            fileUris.add(fileUri);
                        }
                    }
                    if (!fileUris.isEmpty()) {
                        if (hasMultipleAttachments) {
                            sendIntent.putExtra("android.intent.extra.STREAM", fileUris);
                        } else {
                            sendIntent.putExtra("android.intent.extra.STREAM", fileUri);
                        }
                    }
                } else {
                    sendIntent.setType("text/plain");
                }
            } catch (Exception e) {
                this.callbackContext.error(e.getMessage());
            }
            if (SocialSharing.notEmpty(this.val$subject)) {
                sendIntent.putExtra("android.intent.extra.SUBJECT", this.val$subject);
            }
            if (SocialSharing.notEmpty(this.val$url)) {
                if (SocialSharing.notEmpty(message)) {
                    message = message + " " + this.val$url;
                } else {
                    message = this.val$url;
                }
            }
            if (SocialSharing.notEmpty(message)) {
                sendIntent.putExtra("android.intent.extra.TEXT", message);
                if (Build.VERSION.SDK_INT < 21) {
                    sendIntent.putExtra("sms_body", message);
                }
            }
            sendIntent.addFlags(268435456);
            if (this.val$appPackageName != null) {
                String packageName = this.val$appPackageName;
                String passedActivityName = null;
                if (packageName.contains("/")) {
                    String[] items = this.val$appPackageName.split("/");
                    packageName = items[0];
                    passedActivityName = items[1];
                }
                ActivityInfo activity = SocialSharing.this.getActivity(this.callbackContext, sendIntent, packageName, this.val$appName);
                if (activity != null) {
                    if (this.val$peek) {
                        this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                        return;
                    }
                    sendIntent.addCategory("android.intent.category.LAUNCHER");
                    String str = activity.applicationInfo.packageName;
                    if (passedActivityName == null) {
                        passedActivityName = activity.name;
                    }
                    sendIntent.setComponent(new ComponentName(str, passedActivityName));
                    SocialSharing.this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: nl.xservices.plugins.SocialSharing.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AnonymousClass2.this.val$mycordova.startActivityForResult(AnonymousClass2.this.val$plugin, sendIntent, 0);
                        }
                    });
                    if (SocialSharing.this.pasteMessage != null) {
                        new Timer().schedule(new TimerTask() { // from class: nl.xservices.plugins.SocialSharing.2.2
                            @Override // java.util.TimerTask, java.lang.Runnable
                            public void run() {
                                SocialSharing.this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: nl.xservices.plugins.SocialSharing.2.2.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        SocialSharing.this.copyHintToClipboard(AnonymousClass2.this.val$msg, SocialSharing.this.pasteMessage);
                                        SocialSharing.this.showPasteMessage(SocialSharing.this.pasteMessage);
                                    }
                                });
                            }
                        }, 2000L);
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.val$peek) {
                this.callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            } else {
                SocialSharing.this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: nl.xservices.plugins.SocialSharing.2.3
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass2.this.val$mycordova.startActivityForResult(AnonymousClass2.this.val$plugin, Intent.createChooser(sendIntent, AnonymousClass2.this.val$chooserTitle), AnonymousClass2.this.val$boolResult ? 1 : 2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void copyHintToClipboard(String msg, String label) {
        if (Build.VERSION.SDK_INT >= 11) {
            ClipboardManager clipboard = (ClipboardManager) this.cordova.getActivity().getSystemService("clipboard");
            ClipData clip = ClipData.newPlainText(label, msg);
            clipboard.setPrimaryClip(clip);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void showPasteMessage(String label) {
        if (Build.VERSION.SDK_INT >= 11) {
            Toast toast = Toast.makeText(this.webView.getContext(), label, 1);
            toast.setGravity(17, 0, 0);
            toast.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Uri getFileUriAndSetType(Intent sendIntent, String dir, String image, String subject, int nthFile) throws IOException {
        String fileName;
        String localImage = image;
        if (image.endsWith("mp4") || image.endsWith("mov") || image.endsWith("3gp")) {
            sendIntent.setType("video/*");
        } else if (image.endsWith("mp3")) {
            sendIntent.setType("audio/x-mpeg");
        } else {
            sendIntent.setType("image/*");
        }
        if (image.startsWith("http") || image.startsWith("www/")) {
            String filename = getFileName(image);
            localImage = "file://" + dir + "/" + filename;
            if (image.startsWith("http")) {
                URLConnection connection = new URL(image).openConnection();
                String disposition = connection.getHeaderField("Content-Disposition");
                if (disposition != null) {
                    Pattern dispositionPattern = Pattern.compile("filename=([^;]+)");
                    Matcher matcher = dispositionPattern.matcher(disposition);
                    if (matcher.find()) {
                        filename = matcher.group(1).replaceAll("[^a-zA-Z0-9._-]", "");
                        if (filename.length() == 0) {
                            filename = "file";
                        }
                        localImage = "file://" + dir + "/" + filename;
                    }
                }
                saveFile(getBytes(connection.getInputStream()), dir, filename);
            } else {
                saveFile(getBytes(this.webView.getContext().getAssets().open(image)), dir, filename);
            }
        } else if (image.startsWith("data:")) {
            if (!image.contains(";base64,")) {
                sendIntent.setType("text/plain");
                return null;
            }
            String encodedImg = image.substring(image.indexOf(";base64,") + 8);
            if (!image.contains("data:image/")) {
                sendIntent.setType(image.substring(image.indexOf("data:") + 5, image.indexOf(";base64")));
            }
            String imgExtension = image.substring(image.indexOf("/") + 1, image.indexOf(";base64"));
            if (notEmpty(subject)) {
                fileName = sanitizeFilename(subject) + (nthFile == 0 ? "" : "_" + nthFile) + "." + imgExtension;
            } else {
                fileName = "file" + (nthFile == 0 ? "" : "_" + nthFile) + "." + imgExtension;
            }
            saveFile(Base64.decode(encodedImg, 0), dir, fileName);
            localImage = "file://" + dir + "/" + fileName;
        } else if (image.startsWith("df:")) {
            if (!image.contains(";base64,")) {
                sendIntent.setType("text/plain");
                return null;
            }
            String fileName2 = image.substring(image.indexOf("df:") + 3, image.indexOf(";data:"));
            String fileType = image.substring(image.indexOf(";data:") + 6, image.indexOf(";base64,"));
            String encodedImg2 = image.substring(image.indexOf(";base64,") + 8);
            sendIntent.setType(fileType);
            saveFile(Base64.decode(encodedImg2, 0), dir, sanitizeFilename(fileName2));
            localImage = "file://" + dir + "/" + sanitizeFilename(fileName2);
        } else {
            if (!image.startsWith("file://")) {
                throw new IllegalArgumentException("URL_NOT_SUPPORTED");
            }
            String type = getMIMEType(image);
            sendIntent.setType(type);
        }
        return Uri.parse(localImage);
    }

    private String getMIMEType(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return "*/*";
        }
        String end = fileName.substring(dotIndex + 1, fileName.length()).toLowerCase();
        String fromMap = MIME_Map.get(end);
        return fromMap != null ? fromMap : "*/*";
    }

    static {
        MIME_Map.put("3gp", "video/3gpp");
        MIME_Map.put("apk", "application/vnd.android.package-archive");
        MIME_Map.put("asf", "video/x-ms-asf");
        MIME_Map.put("avi", "video/x-msvideo");
        MIME_Map.put("bin", "application/octet-stream");
        MIME_Map.put("bmp", "image/bmp");
        MIME_Map.put("c", "text/plain");
        MIME_Map.put("class", "application/octet-stream");
        MIME_Map.put("conf", "text/plain");
        MIME_Map.put("cpp", "text/plain");
        MIME_Map.put("doc", "application/msword");
        MIME_Map.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        MIME_Map.put("xls", "application/vnd.ms-excel");
        MIME_Map.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        MIME_Map.put("exe", "application/octet-stream");
        MIME_Map.put("gif", "image/gif");
        MIME_Map.put("gtar", "application/x-gtar");
        MIME_Map.put("gz", "application/x-gzip");
        MIME_Map.put("h", "text/plain");
        MIME_Map.put("htm", "text/html");
        MIME_Map.put("html", "text/html");
        MIME_Map.put("jar", "application/java-archive");
        MIME_Map.put("java", "text/plain");
        MIME_Map.put("jpeg", "image/jpeg");
        MIME_Map.put("jpg", "image/*");
        MIME_Map.put("js", "application/x-javascript");
        MIME_Map.put("log", "text/plain");
        MIME_Map.put("m3u", "audio/x-mpegurl");
        MIME_Map.put("m4a", "audio/mp4a-latm");
        MIME_Map.put("m4b", "audio/mp4a-latm");
        MIME_Map.put("m4p", "audio/mp4a-latm");
        MIME_Map.put("m4u", "video/vnd.mpegurl");
        MIME_Map.put("m4v", "video/x-m4v");
        MIME_Map.put("mov", "video/quicktime");
        MIME_Map.put("mp2", "audio/x-mpeg");
        MIME_Map.put("mp3", "audio/x-mpeg");
        MIME_Map.put("mp4", "video/mp4");
        MIME_Map.put("mpc", "application/vnd.mpohun.certificate");
        MIME_Map.put("mpe", "video/mpeg");
        MIME_Map.put("mpeg", "video/mpeg");
        MIME_Map.put("mpg", "video/mpeg");
        MIME_Map.put("mpg4", "video/mp4");
        MIME_Map.put("mpga", "audio/mpeg");
        MIME_Map.put("msg", "application/vnd.ms-outlook");
        MIME_Map.put("ogg", "audio/ogg");
        MIME_Map.put("pdf", "application/pdf");
        MIME_Map.put("png", "image/png");
        MIME_Map.put("pps", "application/vnd.ms-powerpoint");
        MIME_Map.put("ppt", "application/vnd.ms-powerpoint");
        MIME_Map.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        MIME_Map.put("prop", "text/plain");
        MIME_Map.put("rc", "text/plain");
        MIME_Map.put("rmvb", "audio/x-pn-realaudio");
        MIME_Map.put("rtf", "application/rtf");
        MIME_Map.put("sh", "text/plain");
        MIME_Map.put("tar", "application/x-tar");
        MIME_Map.put("tgz", "application/x-compressed");
        MIME_Map.put("txt", "text/plain");
        MIME_Map.put("wav", "audio/x-wav");
        MIME_Map.put("wma", "audio/x-ms-wma");
        MIME_Map.put("wmv", "audio/x-ms-wmv");
        MIME_Map.put("wps", "application/vnd.ms-works");
        MIME_Map.put("xml", "text/plain");
        MIME_Map.put("z", "application/x-compress");
        MIME_Map.put("zip", "application/x-zip-compressed");
        MIME_Map.put("", "*/*");
    }

    private boolean shareViaWhatsAppDirectly(CallbackContext callbackContext, String message, final String subject, final JSONArray files, String url, final String number) {
        if (notEmpty(url)) {
            if (notEmpty(message)) {
                message = message + " " + url;
            } else {
                message = url;
            }
        }
        final String shareMessage = message;
        this.cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) { // from class: nl.xservices.plugins.SocialSharing.3
            @Override // java.lang.Runnable
            public void run() {
                final Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse("smsto:" + number));
                intent.putExtra("sms_body", shareMessage);
                intent.putExtra("sms_subject", subject);
                intent.setPackage("com.whatsapp");
                try {
                    if (files.length() > 0 && !"".equals(files.getString(0))) {
                        boolean hasMultipleAttachments = files.length() > 1;
                        String dir = SocialSharing.this.getDownloadDir();
                        if (dir != null) {
                            ArrayList<Uri> fileUris = new ArrayList<>();
                            Uri fileUri = null;
                            for (int i = 0; i < files.length(); i++) {
                                fileUri = SocialSharing.this.getFileUriAndSetType(intent, dir, files.getString(i), subject, i);
                                if (fileUri != null) {
                                    fileUris.add(fileUri);
                                }
                            }
                            if (!fileUris.isEmpty()) {
                                if (hasMultipleAttachments) {
                                    intent.putExtra("android.intent.extra.STREAM", fileUris);
                                } else {
                                    intent.putExtra("android.intent.extra.STREAM", fileUri);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
                try {
                    intent.addFlags(268435456);
                    SocialSharing.this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: nl.xservices.plugins.SocialSharing.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SocialSharing.this.cordova.startActivityForResult(this, intent, 4);
                        }
                    });
                } catch (Exception e2) {
                    this.callbackContext.error(e2.getMessage());
                }
            }
        });
        return true;
    }

    private boolean invokeSMSIntent(CallbackContext callbackContext, JSONObject options, String p_phonenumbers) {
        final String message = options.optString("message");
        final String subject = null;
        final String image = null;
        final String phonenumbers = getPhoneNumbersWithManufacturerSpecificSeparators(p_phonenumbers);
        this.cordova.getThreadPool().execute(new SocialSharingRunnable(callbackContext) { // from class: nl.xservices.plugins.SocialSharing.4
            @Override // java.lang.Runnable
            public void run() {
                Intent intent;
                Uri fileUri;
                if (Build.VERSION.SDK_INT >= 19) {
                    intent = new Intent("android.intent.action.SENDTO");
                    intent.setData(Uri.parse("smsto:" + (SocialSharing.notEmpty(phonenumbers) ? phonenumbers : "")));
                } else {
                    intent = new Intent("android.intent.action.VIEW");
                    intent.setType("vnd.android-dir/mms-sms");
                    if (phonenumbers != null) {
                        intent.putExtra("address", phonenumbers);
                    }
                }
                intent.putExtra("sms_body", message);
                intent.putExtra("sms_subject", subject);
                try {
                    if (image != null && !"".equals(image) && (fileUri = SocialSharing.this.getFileUriAndSetType(intent, SocialSharing.this.getDownloadDir(), image, subject, 0)) != null) {
                        intent.putExtra("android.intent.extra.STREAM", fileUri);
                    }
                    intent.addFlags(268435456);
                    SocialSharing.this.cordova.startActivityForResult(this, intent, 0);
                } catch (Exception e) {
                    this.callbackContext.error(e.getMessage());
                }
            }
        });
        return true;
    }

    private static String getPhoneNumbersWithManufacturerSpecificSeparators(String phonenumbers) {
        char separator;
        if (notEmpty(phonenumbers)) {
            if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
                separator = ',';
            } else {
                separator = ';';
            }
            return phonenumbers.replace(';', separator).replace(',', separator);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ActivityInfo getActivity(CallbackContext callbackContext, Intent shareIntent, String appPackageName, String appName) {
        PackageManager pm = this.webView.getContext().getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (ResolveInfo app : activityList) {
            if (app.activityInfo.packageName.contains(appPackageName) && (appName == null || app.activityInfo.name.contains(appName))) {
                return app.activityInfo;
            }
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, getShareActivities(activityList)));
        return null;
    }

    private JSONArray getShareActivities(List<ResolveInfo> activityList) {
        List<String> packages = new ArrayList<>();
        for (ResolveInfo app : activityList) {
            packages.add(app.activityInfo.packageName);
        }
        return new JSONArray((Collection) packages);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onActivityResult(int requestCode, int resultCode, Intent intent) throws JSONException {
        super.onActivityResult(requestCode, resultCode, intent);
        if (this._callbackContext != null) {
            switch (requestCode) {
                case 1:
                    this._callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, resultCode == -1));
                    break;
                case 2:
                    JSONObject json = new JSONObject();
                    try {
                        json.put("completed", resultCode == -1);
                        json.put("app", "");
                        this._callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, json));
                        break;
                    } catch (JSONException e) {
                        this._callbackContext.error(e.getMessage());
                        return;
                    }
                default:
                    this._callbackContext.success();
                    break;
            }
        }
    }

    private void createOrCleanDir(String downloadDir) throws IOException {
        File dir = new File(downloadDir);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("CREATE_DIRS_FAILED");
            }
        } else {
            cleanupOldFiles(dir);
        }
    }

    private static String getFileName(String url) {
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        Pattern r = Pattern.compile(".*/([^?#]+)?");
        Matcher m = r.matcher(url);
        return m.find() ? m.group(1) : "file";
    }

    private byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[16384];
        while (true) {
            int nRead = is.read(data, 0, data.length);
            if (nRead != -1) {
                buffer.write(data, 0, nRead);
            } else {
                buffer.flush();
                return buffer.toByteArray();
            }
        }
    }

    private void saveFile(byte[] bytes, String dirName, String fileName) throws IOException {
        File dir = new File(dirName);
        FileOutputStream fos = new FileOutputStream(new File(dir, fileName));
        fos.write(bytes);
        fos.flush();
        fos.close();
    }

    private void cleanupOldFiles(File dir) {
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean notEmpty(String what) {
        return (what == null || "".equals(what) || "null".equalsIgnoreCase(what)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String[] toStringArray(JSONArray jsonArray) throws JSONException {
        String[] result = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = jsonArray.getString(i);
        }
        return result;
    }

    public static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*?|<> ]", "_");
    }
}
