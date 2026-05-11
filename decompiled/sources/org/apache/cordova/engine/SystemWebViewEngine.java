package org.apache.cordova.engine;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.cordova.CordovaBridge;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.ICordovaCookieManager;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.PluginManager;

/* loaded from: classes.dex */
public class SystemWebViewEngine implements CordovaWebViewEngine {
    public static final String TAG = "SystemWebViewEngine";
    protected CordovaBridge bridge;
    protected CordovaWebViewEngine.Client client;
    protected final SystemCookieManager cookieManager;
    protected CordovaInterface cordova;
    protected NativeToJsMessageQueue nativeToJsMessageQueue;
    protected CordovaWebView parentWebView;
    protected PluginManager pluginManager;
    protected CordovaPreferences preferences;
    private BroadcastReceiver receiver;
    protected CordovaResourceApi resourceApi;
    protected final SystemWebView webView;

    public SystemWebViewEngine(Context context, CordovaPreferences preferences) {
        this(new SystemWebView(context), preferences);
    }

    public SystemWebViewEngine(SystemWebView webView) {
        this(webView, (CordovaPreferences) null);
    }

    public SystemWebViewEngine(SystemWebView webView, CordovaPreferences preferences) {
        this.preferences = preferences;
        this.webView = webView;
        this.cookieManager = new SystemCookieManager(webView);
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public void init(CordovaWebView parentWebView, CordovaInterface cordova, CordovaWebViewEngine.Client client, CordovaResourceApi resourceApi, PluginManager pluginManager, NativeToJsMessageQueue nativeToJsMessageQueue) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (this.cordova != null) {
            throw new IllegalStateException();
        }
        if (this.preferences == null) {
            this.preferences = parentWebView.getPreferences();
        }
        this.parentWebView = parentWebView;
        this.cordova = cordova;
        this.client = client;
        this.resourceApi = resourceApi;
        this.pluginManager = pluginManager;
        this.nativeToJsMessageQueue = nativeToJsMessageQueue;
        this.webView.init(this, cordova);
        initWebViewSettings();
        nativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.OnlineEventsBridgeMode(new NativeToJsMessageQueue.OnlineEventsBridgeMode.OnlineEventsBridgeModeDelegate() { // from class: org.apache.cordova.engine.SystemWebViewEngine.1
            @Override // org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode.OnlineEventsBridgeModeDelegate
            public void setNetworkAvailable(boolean value) {
                SystemWebViewEngine.this.webView.setNetworkAvailable(value);
            }

            @Override // org.apache.cordova.NativeToJsMessageQueue.OnlineEventsBridgeMode.OnlineEventsBridgeModeDelegate
            public void runOnUiThread(Runnable r) {
                SystemWebViewEngine.this.cordova.getActivity().runOnUiThread(r);
            }
        }));
        this.bridge = new CordovaBridge(pluginManager, nativeToJsMessageQueue);
        exposeJsInterface(this.webView, this.bridge);
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public CordovaWebView getCordovaWebView() {
        return this.parentWebView;
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public ICordovaCookieManager getCookieManager() {
        return this.cookieManager;
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public View getView() {
        return this.webView;
    }

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    private void initWebViewSettings() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        this.webView.setInitialScale(0);
        this.webView.setVerticalScrollBarEnabled(false);
        final WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        try {
            Method gingerbread_getMethod = WebSettings.class.getMethod("setNavDump", Boolean.TYPE);
            String manufacturer = Build.MANUFACTURER;
            Log.d(TAG, "CordovaWebView is running on device made by: " + manufacturer);
            if (Build.VERSION.SDK_INT < 11 && Build.MANUFACTURER.contains("HTC")) {
                gingerbread_getMethod.invoke(settings, true);
            }
        } catch (IllegalAccessException e) {
            Log.d(TAG, "This should never happen: IllegalAccessException means this isn't Android anymore");
        } catch (IllegalArgumentException e2) {
            Log.d(TAG, "Doing the NavDump failed with bad arguments");
        } catch (NoSuchMethodException e3) {
            Log.d(TAG, "We are on a modern version of Android, we will deprecate HTC 2.3 devices in 2.8");
        } catch (InvocationTargetException e4) {
            Log.d(TAG, "This should never happen: InvocationTargetException means this isn't Android anymore.");
        }
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        String databasePath = this.webView.getContext().getApplicationContext().getDir("database", 0).getPath();
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(databasePath);
        ApplicationInfo appInfo = this.webView.getContext().getApplicationContext().getApplicationInfo();
        if ((appInfo.flags & 2) != 0 && Build.VERSION.SDK_INT >= 19) {
            enableRemoteDebugging();
        }
        settings.setGeolocationDatabasePath(databasePath);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(databasePath);
        settings.setAppCacheEnabled(true);
        String defaultUserAgent = settings.getUserAgentString();
        String overrideUserAgent = this.preferences.getString("OverrideUserAgent", null);
        if (overrideUserAgent != null) {
            settings.setUserAgentString(overrideUserAgent);
        } else {
            String appendUserAgent = this.preferences.getString("AppendUserAgent", null);
            if (appendUserAgent != null) {
                settings.setUserAgentString(defaultUserAgent + " " + appendUserAgent);
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        if (this.receiver == null) {
            this.receiver = new BroadcastReceiver() { // from class: org.apache.cordova.engine.SystemWebViewEngine.2
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    settings.getUserAgentString();
                }
            };
            this.webView.getContext().registerReceiver(this.receiver, intentFilter);
        }
    }

    @TargetApi(19)
    private void enableRemoteDebugging() {
        try {
            WebView.setWebContentsDebuggingEnabled(true);
        } catch (IllegalArgumentException e) {
            Log.d(TAG, "You have one job! To turn on Remote Web Debugging! YOU HAVE FAILED! ");
            e.printStackTrace();
        }
    }

    private static void exposeJsInterface(WebView webView, CordovaBridge bridge) {
        if (Build.VERSION.SDK_INT < 17) {
            Log.i(TAG, "Disabled addJavascriptInterface() bridge since Android version is old.");
        } else {
            SystemExposedJsApi exposedJsApi = new SystemExposedJsApi(bridge);
            webView.addJavascriptInterface(exposedJsApi, "_cordovaNative");
        }
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public void loadUrl(String url, boolean clearNavigationStack) {
        this.webView.loadUrl(url);
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public String getUrl() {
        return this.webView.getUrl();
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public void stopLoading() {
        this.webView.stopLoading();
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public void clearCache() {
        this.webView.clearCache(true);
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public void clearHistory() {
        this.webView.clearHistory();
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public boolean canGoBack() {
        return this.webView.canGoBack();
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public boolean goBack() {
        if (!this.webView.canGoBack()) {
            return false;
        }
        this.webView.goBack();
        return true;
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public void setPaused(boolean value) {
        if (value) {
            this.webView.pauseTimers();
        } else {
            this.webView.resumeTimers();
        }
    }

    @Override // org.apache.cordova.CordovaWebViewEngine
    public void destroy() {
        this.webView.chromeClient.destroyLastDialog();
        this.webView.destroy();
        if (this.receiver != null) {
            try {
                this.webView.getContext().unregisterReceiver(this.receiver);
            } catch (Exception e) {
                Log.e(TAG, "Error unregistering configuration receiver: " + e.getMessage(), e);
            }
        }
    }
}
