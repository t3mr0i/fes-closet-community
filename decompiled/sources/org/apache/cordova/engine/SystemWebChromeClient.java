package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.util.Arrays;
import org.apache.cordova.CordovaDialogsHelper;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONException;

/* loaded from: classes.dex */
public class SystemWebChromeClient extends WebChromeClient {
    private static final int FILECHOOSER_RESULTCODE = 5173;
    private static final String LOG_TAG = "SystemWebChromeClient";
    private long MAX_QUOTA = 104857600;
    private Context appContext;
    private CordovaDialogsHelper dialogsHelper;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private View mVideoProgressView;
    protected final SystemWebViewEngine parentEngine;

    public SystemWebChromeClient(SystemWebViewEngine parentEngine) {
        this.parentEngine = parentEngine;
        this.appContext = parentEngine.webView.getContext();
        this.dialogsHelper = new CordovaDialogsHelper(this.appContext);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        this.dialogsHelper.showAlert(message, new CordovaDialogsHelper.Result() { // from class: org.apache.cordova.engine.SystemWebChromeClient.1
            @Override // org.apache.cordova.CordovaDialogsHelper.Result
            public void gotResult(boolean success, String value) {
                if (success) {
                    result.confirm();
                } else {
                    result.cancel();
                }
            }
        });
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        this.dialogsHelper.showConfirm(message, new CordovaDialogsHelper.Result() { // from class: org.apache.cordova.engine.SystemWebChromeClient.2
            @Override // org.apache.cordova.CordovaDialogsHelper.Result
            public void gotResult(boolean success, String value) {
                if (success) {
                    result.confirm();
                } else {
                    result.cancel();
                }
            }
        });
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView view, String origin, String message, String defaultValue, final JsPromptResult result) throws JSONException, NumberFormatException {
        String handledRet = this.parentEngine.bridge.promptOnJsPrompt(origin, message, defaultValue);
        if (handledRet != null) {
            result.confirm(handledRet);
            return true;
        }
        this.dialogsHelper.showPrompt(message, defaultValue, new CordovaDialogsHelper.Result() { // from class: org.apache.cordova.engine.SystemWebChromeClient.3
            @Override // org.apache.cordova.CordovaDialogsHelper.Result
            public void gotResult(boolean success, String value) {
                if (success) {
                    result.confirm(value);
                } else {
                    result.cancel();
                }
            }
        });
        return true;
    }

    @Override // android.webkit.WebChromeClient
    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
        LOG.d(LOG_TAG, "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", Long.valueOf(estimatedSize), Long.valueOf(currentQuota), Long.valueOf(totalUsedQuota));
        quotaUpdater.updateQuota(this.MAX_QUOTA);
    }

    @Override // android.webkit.WebChromeClient
    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        if (Build.VERSION.SDK_INT == 7) {
            LOG.d(LOG_TAG, "%s: Line %d : %s", sourceID, Integer.valueOf(lineNumber), message);
            super.onConsoleMessage(message, lineNumber, sourceID);
        }
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(8)
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (consoleMessage.message() != null) {
            LOG.d(LOG_TAG, "%s: Line %d : %s", consoleMessage.sourceId(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.message());
        }
        return super.onConsoleMessage(consoleMessage);
    }

    @Override // android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
        callback.invoke(origin, true, false);
        CordovaPlugin geolocation = this.parentEngine.pluginManager.getPlugin("Geolocation");
        if (geolocation != null && !geolocation.hasPermisssion()) {
            geolocation.requestPermissions(0);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        this.parentEngine.getCordovaWebView().showCustomView(view, callback);
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        this.parentEngine.getCordovaWebView().hideCustomView();
    }

    @Override // android.webkit.WebChromeClient
    public View getVideoLoadingProgressView() {
        if (this.mVideoProgressView == null) {
            LinearLayout linearLayout = new LinearLayout(this.parentEngine.getView().getContext());
            linearLayout.setOrientation(1);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            linearLayout.setLayoutParams(layoutParams);
            ProgressBar bar = new ProgressBar(this.parentEngine.getView().getContext());
            LinearLayout.LayoutParams barLayoutParams = new LinearLayout.LayoutParams(-2, -2);
            barLayoutParams.gravity = 17;
            bar.setLayoutParams(barLayoutParams);
            linearLayout.addView(bar);
            this.mVideoProgressView = linearLayout;
        }
        return this.mVideoProgressView;
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "*/*");
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openFileChooser(uploadMsg, acceptType, null);
    }

    public void openFileChooser(final ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        this.parentEngine.cordova.startActivityForResult(new CordovaPlugin() { // from class: org.apache.cordova.engine.SystemWebChromeClient.4
            @Override // org.apache.cordova.CordovaPlugin
            public void onActivityResult(int requestCode, int resultCode, Intent intent2) {
                Uri result = (intent2 == null || resultCode != -1) ? null : intent2.getData();
                Log.d(SystemWebChromeClient.LOG_TAG, "Receive file chooser URL: " + result);
                uploadMsg.onReceiveValue(result);
            }
        }, intent, FILECHOOSER_RESULTCODE);
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(MotionEventCompat.AXIS_WHEEL)
    public boolean onShowFileChooser(WebView webView, final ValueCallback<Uri[]> filePathsCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        Intent intent = fileChooserParams.createIntent();
        try {
            this.parentEngine.cordova.startActivityForResult(new CordovaPlugin() { // from class: org.apache.cordova.engine.SystemWebChromeClient.5
                @Override // org.apache.cordova.CordovaPlugin
                public void onActivityResult(int requestCode, int resultCode, Intent intent2) {
                    Uri[] result = WebChromeClient.FileChooserParams.parseResult(resultCode, intent2);
                    Log.d(SystemWebChromeClient.LOG_TAG, "Receive file chooser URL: " + result);
                    filePathsCallback.onReceiveValue(result);
                }
            }, intent, FILECHOOSER_RESULTCODE);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w("No activity found to handle file chooser intent.", e);
            filePathsCallback.onReceiveValue(null);
            return true;
        }
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(MotionEventCompat.AXIS_WHEEL)
    public void onPermissionRequest(PermissionRequest request) {
        Log.d(LOG_TAG, "onPermissionRequest: " + Arrays.toString(request.getResources()));
        request.grant(request.getResources());
    }

    public void destroyLastDialog() {
        this.dialogsHelper.destroyLastDialog();
    }
}
