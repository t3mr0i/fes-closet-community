package org.apache.cordova;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.engine.SystemWebViewEngine;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CordovaWebViewImpl implements CordovaWebView {
    static final /* synthetic */ boolean $assertionsDisabled;
    public static final String TAG = "CordovaWebViewImpl";
    private CoreAndroid appPlugin;
    private CordovaInterface cordova;
    protected final CordovaWebViewEngine engine;
    private boolean hasPausedEver;
    String loadedUrl;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private NativeToJsMessageQueue nativeToJsMessageQueue;
    private PluginManager pluginManager;
    private CordovaPreferences preferences;
    private CordovaResourceApi resourceApi;
    private int loadUrlTimeout = 0;
    private EngineClient engineClient = new EngineClient();
    private Set<Integer> boundKeyCodes = new HashSet();

    static {
        $assertionsDisabled = !CordovaWebViewImpl.class.desiredAssertionStatus();
    }

    static /* synthetic */ int access$108(CordovaWebViewImpl x0) {
        int i = x0.loadUrlTimeout;
        x0.loadUrlTimeout = i + 1;
        return i;
    }

    public static CordovaWebViewEngine createEngine(Context context, CordovaPreferences preferences) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
        String className = preferences.getString("webview", SystemWebViewEngine.class.getCanonicalName());
        try {
            Class<?> webViewClass = Class.forName(className);
            Constructor<?> constructor = webViewClass.getConstructor(Context.class, CordovaPreferences.class);
            return (CordovaWebViewEngine) constructor.newInstance(context, preferences);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create webview. ", e);
        }
    }

    public CordovaWebViewImpl(CordovaWebViewEngine cordovaWebViewEngine) {
        this.engine = cordovaWebViewEngine;
    }

    public void init(CordovaInterface cordova) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        init(cordova, new ArrayList(), new CordovaPreferences());
    }

    @Override // org.apache.cordova.CordovaWebView
    public void init(CordovaInterface cordova, List<PluginEntry> pluginEntries, CordovaPreferences preferences) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (this.cordova != null) {
            throw new IllegalStateException();
        }
        this.cordova = cordova;
        this.preferences = preferences;
        this.pluginManager = new PluginManager(this, this.cordova, pluginEntries);
        this.resourceApi = new CordovaResourceApi(this.engine.getView().getContext(), this.pluginManager);
        this.nativeToJsMessageQueue = new NativeToJsMessageQueue();
        this.nativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.NoOpBridgeMode());
        this.nativeToJsMessageQueue.addBridgeMode(new NativeToJsMessageQueue.LoadUrlBridgeMode(this.engine, cordova));
        if (preferences.getBoolean("DisallowOverscroll", false)) {
            this.engine.getView().setOverScrollMode(2);
        }
        this.engine.init(this, cordova, this.engineClient, this.resourceApi, this.pluginManager, this.nativeToJsMessageQueue);
        if (!$assertionsDisabled && !(this.engine.getView() instanceof CordovaWebViewEngine.EngineView)) {
            throw new AssertionError();
        }
        this.pluginManager.addService(CoreAndroid.PLUGIN_NAME, "org.apache.cordova.CoreAndroid");
        this.pluginManager.init();
    }

    @Override // org.apache.cordova.CordovaWebView
    public boolean isInitialized() {
        return this.cordova != null;
    }

    @Override // org.apache.cordova.CordovaWebView
    public void loadUrlIntoView(final String url, boolean recreatePlugins) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        LOG.d(TAG, ">>> loadUrl(" + url + ")");
        if (url.equals("about:blank") || url.startsWith("javascript:")) {
            this.engine.loadUrl(url, false);
            return;
        }
        boolean recreatePlugins2 = recreatePlugins || this.loadedUrl == null;
        if (recreatePlugins2) {
            if (this.loadedUrl != null) {
                this.pluginManager.init();
            }
            this.loadedUrl = url;
        }
        final int currentLoadUrlTimeout = this.loadUrlTimeout;
        final int loadUrlTimeoutValue = this.preferences.getInteger("LoadUrlTimeoutValue", 20000);
        final Runnable loadError = new Runnable() { // from class: org.apache.cordova.CordovaWebViewImpl.1
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                CordovaWebViewImpl.this.stopLoading();
                LOG.e(CordovaWebViewImpl.TAG, "CordovaWebView: TIMEOUT ERROR!");
                JSONObject data = new JSONObject();
                try {
                    data.put("errorCode", -6);
                    data.put("description", "The connection to the server was unsuccessful.");
                    data.put("url", url);
                } catch (JSONException e) {
                }
                CordovaWebViewImpl.this.pluginManager.postMessage("onReceivedError", data);
            }
        };
        final Runnable timeoutCheck = new Runnable() { // from class: org.apache.cordova.CordovaWebViewImpl.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (this) {
                        wait(loadUrlTimeoutValue);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (CordovaWebViewImpl.this.loadUrlTimeout == currentLoadUrlTimeout) {
                    CordovaWebViewImpl.this.cordova.getActivity().runOnUiThread(loadError);
                }
            }
        };
        final boolean _recreatePlugins = recreatePlugins2;
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.CordovaWebViewImpl.3
            @Override // java.lang.Runnable
            public void run() {
                if (loadUrlTimeoutValue > 0) {
                    CordovaWebViewImpl.this.cordova.getThreadPool().execute(timeoutCheck);
                }
                CordovaWebViewImpl.this.engine.loadUrl(url, _recreatePlugins);
            }
        });
    }

    @Override // org.apache.cordova.CordovaWebView
    public void loadUrl(String url) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        loadUrlIntoView(url, true);
    }

    @Override // org.apache.cordova.CordovaWebView
    public void showWebPage(String url, boolean openExternal, boolean clearHistory, Map<String, Object> params) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        LOG.d(TAG, "showWebPage(%s, %b, %b, HashMap)", url, Boolean.valueOf(openExternal), Boolean.valueOf(clearHistory));
        if (clearHistory) {
            this.engine.clearHistory();
        }
        if (!openExternal) {
            if (this.pluginManager.shouldAllowNavigation(url)) {
                loadUrlIntoView(url, true);
            } else {
                LOG.w(TAG, "showWebPage: Refusing to load URL into webview since it is not in the <allow-navigation> whitelist. URL=" + url);
            }
        }
        if (!this.pluginManager.shouldOpenExternalUrl(url).booleanValue()) {
            LOG.w(TAG, "showWebPage: Refusing to send intent for URL since it is not in the <allow-intent> whitelist. URL=" + url);
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            Uri uri = Uri.parse(url);
            if ("file".equals(uri.getScheme())) {
                intent.setDataAndType(uri, this.resourceApi.getMimeType(uri));
            } else {
                intent.setData(uri);
            }
            this.cordova.getActivity().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LOG.e(TAG, "Error loading url " + url, e);
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    @Deprecated
    public void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        Log.d(TAG, "showing Custom View");
        if (this.mCustomView != null) {
            callback.onCustomViewHidden();
            return;
        }
        this.mCustomView = view;
        this.mCustomViewCallback = callback;
        ViewGroup parent = (ViewGroup) this.engine.getView().getParent();
        parent.addView(view, new FrameLayout.LayoutParams(-1, -1, 17));
        this.engine.getView().setVisibility(8);
        parent.setVisibility(0);
        parent.bringToFront();
    }

    @Override // org.apache.cordova.CordovaWebView
    @Deprecated
    public void hideCustomView() {
        if (this.mCustomView != null) {
            Log.d(TAG, "Hiding Custom View");
            this.mCustomView.setVisibility(8);
            ViewGroup parent = (ViewGroup) this.engine.getView().getParent();
            parent.removeView(this.mCustomView);
            this.mCustomView = null;
            this.mCustomViewCallback.onCustomViewHidden();
            this.engine.getView().setVisibility(0);
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    @Deprecated
    public boolean isCustomViewShowing() {
        return this.mCustomView != null;
    }

    @Override // org.apache.cordova.CordovaWebView
    @Deprecated
    public void sendJavascript(String statement) {
        this.nativeToJsMessageQueue.addJavaScript(statement);
    }

    @Override // org.apache.cordova.CordovaWebView
    public void sendPluginResult(PluginResult cr, String callbackId) {
        this.nativeToJsMessageQueue.addPluginResult(cr, callbackId);
    }

    @Override // org.apache.cordova.CordovaWebView
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    @Override // org.apache.cordova.CordovaWebView
    public CordovaPreferences getPreferences() {
        return this.preferences;
    }

    @Override // org.apache.cordova.CordovaWebView
    public ICordovaCookieManager getCookieManager() {
        return this.engine.getCookieManager();
    }

    @Override // org.apache.cordova.CordovaWebView
    public CordovaResourceApi getResourceApi() {
        return this.resourceApi;
    }

    @Override // org.apache.cordova.CordovaWebView
    public CordovaWebViewEngine getEngine() {
        return this.engine;
    }

    @Override // org.apache.cordova.CordovaWebView
    public View getView() {
        return this.engine.getView();
    }

    @Override // org.apache.cordova.CordovaWebView
    public Context getContext() {
        return this.engine.getView().getContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendJavascriptEvent(String event) throws JSONException {
        if (this.appPlugin == null) {
            this.appPlugin = (CoreAndroid) this.pluginManager.getPlugin(CoreAndroid.PLUGIN_NAME);
        }
        if (this.appPlugin == null) {
            LOG.w(TAG, "Unable to fire event without existing plugin");
        } else {
            this.appPlugin.fireJavascriptEvent(event);
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    public void setButtonPlumbedToJs(int keyCode, boolean override) {
        switch (keyCode) {
            case 4:
            case MotionEventCompat.AXIS_DISTANCE /* 24 */:
            case 25:
            case 82:
                if (override) {
                    this.boundKeyCodes.add(Integer.valueOf(keyCode));
                    return;
                } else {
                    this.boundKeyCodes.remove(Integer.valueOf(keyCode));
                    return;
                }
            default:
                throw new IllegalArgumentException("Unsupported keycode: " + keyCode);
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    public boolean isButtonPlumbedToJs(int keyCode) {
        return this.boundKeyCodes.contains(Integer.valueOf(keyCode));
    }

    @Override // org.apache.cordova.CordovaWebView
    public Object postMessage(String id, Object data) {
        return this.pluginManager.postMessage(id, data);
    }

    @Override // org.apache.cordova.CordovaWebView
    public String getUrl() {
        return this.engine.getUrl();
    }

    @Override // org.apache.cordova.CordovaWebView
    public void stopLoading() {
        this.loadUrlTimeout++;
    }

    @Override // org.apache.cordova.CordovaWebView
    public boolean canGoBack() {
        return this.engine.canGoBack();
    }

    @Override // org.apache.cordova.CordovaWebView
    public void clearCache() {
        this.engine.clearCache();
    }

    @Override // org.apache.cordova.CordovaWebView
    @Deprecated
    public void clearCache(boolean b) {
        this.engine.clearCache();
    }

    @Override // org.apache.cordova.CordovaWebView
    public void clearHistory() {
        this.engine.clearHistory();
    }

    @Override // org.apache.cordova.CordovaWebView
    public boolean backHistory() {
        return this.engine.goBack();
    }

    @Override // org.apache.cordova.CordovaWebView
    public void onNewIntent(Intent intent) {
        if (this.pluginManager != null) {
            this.pluginManager.onNewIntent(intent);
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    public void handlePause(boolean keepRunning) throws JSONException {
        if (isInitialized()) {
            this.hasPausedEver = true;
            this.pluginManager.onPause(keepRunning);
            sendJavascriptEvent("pause");
            if (!keepRunning) {
                this.engine.setPaused(true);
            }
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    public void handleResume(boolean keepRunning) throws JSONException {
        if (isInitialized()) {
            this.engine.setPaused(false);
            this.pluginManager.onResume(keepRunning);
            if (this.hasPausedEver) {
                sendJavascriptEvent("resume");
            }
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    public void handleStart() {
        if (isInitialized()) {
            this.pluginManager.onStart();
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    public void handleStop() {
        if (isInitialized()) {
            this.pluginManager.onStop();
        }
    }

    @Override // org.apache.cordova.CordovaWebView
    public void handleDestroy() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (isInitialized()) {
            this.loadUrlTimeout++;
            this.pluginManager.onDestroy();
            loadUrl("about:blank");
            this.engine.destroy();
            hideCustomView();
        }
    }

    protected class EngineClient implements CordovaWebViewEngine.Client {
        protected EngineClient() {
        }

        @Override // org.apache.cordova.CordovaWebViewEngine.Client
        public void clearLoadTimeoutTimer() {
            CordovaWebViewImpl.access$108(CordovaWebViewImpl.this);
        }

        @Override // org.apache.cordova.CordovaWebViewEngine.Client
        public void onPageStarted(String newUrl) {
            LOG.d(CordovaWebViewImpl.TAG, "onPageDidNavigate(" + newUrl + ")");
            CordovaWebViewImpl.this.boundKeyCodes.clear();
            CordovaWebViewImpl.this.pluginManager.onReset();
            CordovaWebViewImpl.this.pluginManager.postMessage("onPageStarted", newUrl);
        }

        @Override // org.apache.cordova.CordovaWebViewEngine.Client
        public void onReceivedError(int errorCode, String description, String failingUrl) throws JSONException {
            clearLoadTimeoutTimer();
            JSONObject data = new JSONObject();
            try {
                data.put("errorCode", errorCode);
                data.put("description", description);
                data.put("url", failingUrl);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CordovaWebViewImpl.this.pluginManager.postMessage("onReceivedError", data);
        }

        @Override // org.apache.cordova.CordovaWebViewEngine.Client
        public void onPageFinishedLoading(String url) {
            LOG.d(CordovaWebViewImpl.TAG, "onPageFinished(" + url + ")");
            clearLoadTimeoutTimer();
            CordovaWebViewImpl.this.pluginManager.postMessage("onPageFinished", url);
            if (CordovaWebViewImpl.this.engine.getView().getVisibility() != 0) {
                Thread t = new Thread(new Runnable() { // from class: org.apache.cordova.CordovaWebViewImpl.EngineClient.1
                    @Override // java.lang.Runnable
                    public void run() throws InterruptedException {
                        try {
                            Thread.sleep(2000L);
                            CordovaWebViewImpl.this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.CordovaWebViewImpl.EngineClient.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    CordovaWebViewImpl.this.pluginManager.postMessage("spinner", "stop");
                                }
                            });
                        } catch (InterruptedException e) {
                        }
                    }
                });
                t.start();
            }
            if (url.equals("about:blank")) {
                CordovaWebViewImpl.this.pluginManager.postMessage("exit", null);
            }
        }

        @Override // org.apache.cordova.CordovaWebViewEngine.Client
        public Boolean onDispatchKeyEvent(KeyEvent event) throws JSONException {
            int keyCode = event.getKeyCode();
            boolean isBackButton = keyCode == 4;
            if (event.getAction() == 0) {
                if ((!isBackButton || CordovaWebViewImpl.this.mCustomView == null) && !CordovaWebViewImpl.this.boundKeyCodes.contains(Integer.valueOf(keyCode))) {
                    if (isBackButton) {
                        return Boolean.valueOf(CordovaWebViewImpl.this.engine.canGoBack());
                    }
                }
                return true;
            }
            if (event.getAction() == 1) {
                if (!isBackButton || CordovaWebViewImpl.this.mCustomView == null) {
                    if (CordovaWebViewImpl.this.boundKeyCodes.contains(Integer.valueOf(keyCode))) {
                        String eventName = null;
                        switch (keyCode) {
                            case 4:
                                eventName = "backbutton";
                                break;
                            case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                                eventName = "volumeupbutton";
                                break;
                            case 25:
                                eventName = "volumedownbutton";
                                break;
                            case 82:
                                eventName = "menubutton";
                                break;
                            case 84:
                                eventName = "searchbutton";
                                break;
                        }
                        if (eventName != null) {
                            CordovaWebViewImpl.this.sendJavascriptEvent(eventName);
                            return true;
                        }
                    } else if (isBackButton) {
                        return Boolean.valueOf(CordovaWebViewImpl.this.engine.goBack());
                    }
                } else {
                    CordovaWebViewImpl.this.hideCustomView();
                    return true;
                }
            }
            return null;
        }

        @Override // org.apache.cordova.CordovaWebViewEngine.Client
        public boolean onNavigationAttempt(String url) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
            if (CordovaWebViewImpl.this.pluginManager.onOverrideUrlLoading(url)) {
                return true;
            }
            if (CordovaWebViewImpl.this.pluginManager.shouldAllowNavigation(url)) {
                return false;
            }
            if (CordovaWebViewImpl.this.pluginManager.shouldOpenExternalUrl(url).booleanValue()) {
                CordovaWebViewImpl.this.showWebPage(url, true, false, null);
                return true;
            }
            LOG.w(CordovaWebViewImpl.TAG, "Blocked (possibly sub-frame) navigation to non-allowed URL: " + url);
            return true;
        }
    }
}
