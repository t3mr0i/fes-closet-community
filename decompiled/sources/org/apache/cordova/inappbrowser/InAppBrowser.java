package org.apache.cordova.inappbrowser;

import android.R;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaHttpAuthHandler;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
/* loaded from: classes.dex */
public class InAppBrowser extends CordovaPlugin {
    private static final String CLEAR_ALL_CACHE = "clearcache";
    private static final String CLEAR_SESSION_CACHE = "clearsessioncache";
    private static final String EXIT_EVENT = "exit";
    private static final String HARDWARE_BACK_BUTTON = "hardwareback";
    private static final String HIDDEN = "hidden";
    private static final String LOAD_ERROR_EVENT = "loaderror";
    private static final String LOAD_START_EVENT = "loadstart";
    private static final String LOAD_STOP_EVENT = "loadstop";
    private static final String LOCATION = "location";
    protected static final String LOG_TAG = "InAppBrowser";
    private static final String MEDIA_PLAYBACK_REQUIRES_USER_ACTION = "mediaPlaybackRequiresUserAction";
    private static final String NULL = "null";
    private static final String SELF = "_self";
    private static final String SYSTEM = "_system";
    private static final String ZOOM = "zoom";
    private CallbackContext callbackContext;
    private InAppBrowserDialog dialog;
    private EditText edittext;
    private WebView inAppWebView;
    private boolean showLocationBar = true;
    private boolean showZoomControls = true;
    private boolean openWindowHidden = false;
    private boolean clearAllCache = false;
    private boolean clearSessionCache = false;
    private boolean hadwareBackButton = true;
    private boolean mediaPlaybackRequiresUserGesture = false;

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
        String jsWrapper;
        String jsWrapper2;
        String jsWrapper3;
        if (action.equals("open")) {
            this.callbackContext = callbackContext;
            final String url = args.getString(0);
            String t = args.optString(1);
            if (t == null || t.equals("") || t.equals(NULL)) {
                t = SELF;
            }
            final String target = t;
            final HashMap<String, Boolean> features = parseFeature(args.optString(2));
            Log.d(LOG_TAG, "target = " + target);
            this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.1
                @Override // java.lang.Runnable
                public void run() throws NoSuchMethodException, SecurityException {
                    String result = "";
                    if (InAppBrowser.SELF.equals(target)) {
                        Log.d(InAppBrowser.LOG_TAG, "in self");
                        Boolean shouldAllowNavigation = null;
                        if (url.startsWith("javascript:")) {
                            shouldAllowNavigation = true;
                        }
                        if (shouldAllowNavigation == null) {
                            try {
                                Method iuw = Config.class.getMethod("isUrlWhiteListed", String.class);
                                shouldAllowNavigation = (Boolean) iuw.invoke(null, url);
                            } catch (IllegalAccessException e) {
                            } catch (NoSuchMethodException e2) {
                            } catch (InvocationTargetException e3) {
                            }
                        }
                        if (shouldAllowNavigation == null) {
                            try {
                                Method gpm = InAppBrowser.this.webView.getClass().getMethod("getPluginManager", new Class[0]);
                                PluginManager pm = (PluginManager) gpm.invoke(InAppBrowser.this.webView, new Object[0]);
                                Method san = pm.getClass().getMethod("shouldAllowNavigation", String.class);
                                shouldAllowNavigation = (Boolean) san.invoke(pm, url);
                            } catch (IllegalAccessException e4) {
                            } catch (NoSuchMethodException e5) {
                            } catch (InvocationTargetException e6) {
                            }
                        }
                        if (Boolean.TRUE.equals(shouldAllowNavigation)) {
                            Log.d(InAppBrowser.LOG_TAG, "loading in webview");
                            InAppBrowser.this.webView.loadUrl(url);
                        } else if (url.startsWith("tel:")) {
                            try {
                                Log.d(InAppBrowser.LOG_TAG, "loading in dialer");
                                Intent intent = new Intent("android.intent.action.DIAL");
                                intent.setData(Uri.parse(url));
                                InAppBrowser.this.cordova.getActivity().startActivity(intent);
                            } catch (ActivityNotFoundException e7) {
                                LOG.e(InAppBrowser.LOG_TAG, "Error dialing " + url + ": " + e7.toString());
                            }
                        } else {
                            Log.d(InAppBrowser.LOG_TAG, "loading in InAppBrowser");
                            result = InAppBrowser.this.showWebPage(url, features);
                        }
                    } else if (InAppBrowser.SYSTEM.equals(target)) {
                        Log.d(InAppBrowser.LOG_TAG, "in system");
                        result = InAppBrowser.this.openExternal(url);
                    } else {
                        Log.d(InAppBrowser.LOG_TAG, "in blank");
                        result = InAppBrowser.this.showWebPage(url, features);
                    }
                    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, result);
                    pluginResult.setKeepCallback(true);
                    callbackContext.sendPluginResult(pluginResult);
                }
            });
        } else if (action.equals("close")) {
            closeDialog();
        } else if (action.equals("injectScriptCode")) {
            String jsWrapper4 = null;
            if (args.getBoolean(1)) {
                jsWrapper4 = String.format("(function(){prompt(JSON.stringify([eval(%%s)]), 'gap-iab://%s')})()", callbackContext.getCallbackId());
            }
            injectDeferredObject(args.getString(0), jsWrapper4);
        } else if (action.equals("injectScriptFile")) {
            if (args.getBoolean(1)) {
                jsWrapper3 = String.format("(function(d) { var c = d.createElement('script'); c.src = %%s; c.onload = function() { prompt('', 'gap-iab://%s'); }; d.body.appendChild(c); })(document)", callbackContext.getCallbackId());
            } else {
                jsWrapper3 = "(function(d) { var c = d.createElement('script'); c.src = %s; d.body.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper3);
        } else if (action.equals("injectStyleCode")) {
            if (args.getBoolean(1)) {
                jsWrapper2 = String.format("(function(d) { var c = d.createElement('style'); c.innerHTML = %%s; d.body.appendChild(c); prompt('', 'gap-iab://%s');})(document)", callbackContext.getCallbackId());
            } else {
                jsWrapper2 = "(function(d) { var c = d.createElement('style'); c.innerHTML = %s; d.body.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper2);
        } else if (action.equals("injectStyleFile")) {
            if (args.getBoolean(1)) {
                jsWrapper = String.format("(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %%s; d.head.appendChild(c); prompt('', 'gap-iab://%s');})(document)", callbackContext.getCallbackId());
            } else {
                jsWrapper = "(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %s; d.head.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper);
        } else {
            if (!action.equals("show")) {
                return false;
            }
            this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.2
                @Override // java.lang.Runnable
                public void run() {
                    InAppBrowser.this.dialog.show();
                }
            });
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
            pluginResult.setKeepCallback(true);
            this.callbackContext.sendPluginResult(pluginResult);
        }
        return true;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onReset() {
        closeDialog();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        closeDialog();
    }

    private void injectDeferredObject(String source, String jsWrapper) {
        String scriptToInject;
        if (jsWrapper != null) {
            JSONArray jsonEsc = new JSONArray();
            jsonEsc.put(source);
            String jsonRepr = jsonEsc.toString();
            String jsonSourceString = jsonRepr.substring(1, jsonRepr.length() - 1);
            scriptToInject = String.format(jsWrapper, jsonSourceString);
        } else {
            scriptToInject = source;
        }
        final String finalScriptToInject = scriptToInject;
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.3
            @Override // java.lang.Runnable
            @SuppressLint({"NewApi"})
            public void run() {
                if (Build.VERSION.SDK_INT < 19) {
                    InAppBrowser.this.inAppWebView.loadUrl("javascript:" + finalScriptToInject);
                } else {
                    InAppBrowser.this.inAppWebView.evaluateJavascript(finalScriptToInject, null);
                }
            }
        });
    }

    private HashMap<String, Boolean> parseFeature(String optString) {
        if (optString.equals(NULL)) {
            return null;
        }
        HashMap<String, Boolean> map = new HashMap<>();
        StringTokenizer features = new StringTokenizer(optString, ",");
        while (features.hasMoreElements()) {
            StringTokenizer option = new StringTokenizer(features.nextToken(), "=");
            if (option.hasMoreElements()) {
                String key = option.nextToken();
                Boolean value = option.nextToken().equals("no") ? Boolean.FALSE : Boolean.TRUE;
                map.put(key, value);
            }
        }
        return map;
    }

    public String openExternal(String url) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            try {
                Uri uri = Uri.parse(url);
                if ("file".equals(uri.getScheme())) {
                    intent.setDataAndType(uri, this.webView.getResourceApi().getMimeType(uri));
                } else {
                    intent.setData(uri);
                }
                intent.putExtra("com.android.browser.application_id", this.cordova.getActivity().getPackageName());
                this.cordova.getActivity().startActivity(intent);
                return "";
            } catch (ActivityNotFoundException e) {
                e = e;
                Log.d(LOG_TAG, "InAppBrowser: Error loading url " + url + ":" + e.toString());
                return e.toString();
            }
        } catch (ActivityNotFoundException e2) {
            e = e2;
        }
    }

    public void closeDialog() {
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.4
            @Override // java.lang.Runnable
            public void run() throws JSONException {
                WebView childView = InAppBrowser.this.inAppWebView;
                if (childView != null) {
                    childView.setWebViewClient(new WebViewClient() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.4.1
                        @Override // android.webkit.WebViewClient
                        public void onPageFinished(WebView view, String url) {
                            if (InAppBrowser.this.dialog != null) {
                                InAppBrowser.this.dialog.dismiss();
                                InAppBrowser.this.dialog = null;
                            }
                        }
                    });
                    childView.loadUrl("about:blank");
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("type", InAppBrowser.EXIT_EVENT);
                        InAppBrowser.this.sendUpdate(obj, false);
                    } catch (JSONException e) {
                        Log.d(InAppBrowser.LOG_TAG, "Should never happen");
                    }
                }
            }
        });
    }

    public void goBack() {
        if (this.inAppWebView.canGoBack()) {
            this.inAppWebView.goBack();
        }
    }

    public boolean canGoBack() {
        return this.inAppWebView.canGoBack();
    }

    public boolean hardwareBack() {
        return this.hadwareBackButton;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goForward() {
        if (this.inAppWebView.canGoForward()) {
            this.inAppWebView.goForward();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void navigate(String url) {
        InputMethodManager imm = (InputMethodManager) this.cordova.getActivity().getSystemService("input_method");
        imm.hideSoftInputFromWindow(this.edittext.getWindowToken(), 0);
        if (!url.startsWith("http") && !url.startsWith("file:")) {
            this.inAppWebView.loadUrl("http://" + url);
        } else {
            this.inAppWebView.loadUrl(url);
        }
        this.inAppWebView.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getShowLocationBar() {
        return this.showLocationBar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InAppBrowser getInAppBrowser() {
        return this;
    }

    public String showWebPage(final String url, HashMap<String, Boolean> features) {
        this.showLocationBar = true;
        this.showZoomControls = true;
        this.openWindowHidden = false;
        this.mediaPlaybackRequiresUserGesture = false;
        if (features != null) {
            Boolean show = features.get("location");
            if (show != null) {
                this.showLocationBar = show.booleanValue();
            }
            Boolean zoom = features.get(ZOOM);
            if (zoom != null) {
                this.showZoomControls = zoom.booleanValue();
            }
            Boolean hidden = features.get(HIDDEN);
            if (hidden != null) {
                this.openWindowHidden = hidden.booleanValue();
            }
            Boolean hardwareBack = features.get(HARDWARE_BACK_BUTTON);
            if (hardwareBack != null) {
                this.hadwareBackButton = hardwareBack.booleanValue();
            }
            Boolean mediaPlayback = features.get(MEDIA_PLAYBACK_REQUIRES_USER_ACTION);
            if (mediaPlayback != null) {
                this.mediaPlaybackRequiresUserGesture = mediaPlayback.booleanValue();
            }
            Boolean cache = features.get(CLEAR_ALL_CACHE);
            if (cache != null) {
                this.clearAllCache = cache.booleanValue();
            } else {
                Boolean cache2 = features.get(CLEAR_SESSION_CACHE);
                if (cache2 != null) {
                    this.clearSessionCache = cache2.booleanValue();
                }
            }
        }
        final CordovaWebView thatWebView = this.webView;
        Runnable runnable = new Runnable() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.5
            private int dpToPixels(int dipValue) {
                int value = (int) TypedValue.applyDimension(1, dipValue, InAppBrowser.this.cordova.getActivity().getResources().getDisplayMetrics());
                return value;
            }

            @Override // java.lang.Runnable
            @SuppressLint({"NewApi"})
            public void run() throws Resources.NotFoundException {
                if (InAppBrowser.this.dialog != null) {
                    InAppBrowser.this.dialog.dismiss();
                }
                InAppBrowser.this.dialog = new InAppBrowserDialog(InAppBrowser.this.cordova.getActivity(), R.style.Theme.NoTitleBar);
                InAppBrowser.this.dialog.getWindow().getAttributes().windowAnimations = R.style.Animation.Dialog;
                InAppBrowser.this.dialog.requestWindowFeature(1);
                InAppBrowser.this.dialog.setCancelable(true);
                InAppBrowser.this.dialog.setInAppBroswer(InAppBrowser.this.getInAppBrowser());
                LinearLayout linearLayout = new LinearLayout(InAppBrowser.this.cordova.getActivity());
                linearLayout.setOrientation(1);
                RelativeLayout relativeLayout = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
                relativeLayout.setBackgroundColor(-3355444);
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, dpToPixels(44)));
                relativeLayout.setPadding(dpToPixels(2), dpToPixels(2), dpToPixels(2), dpToPixels(2));
                relativeLayout.setHorizontalGravity(3);
                relativeLayout.setVerticalGravity(48);
                RelativeLayout relativeLayout2 = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
                relativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
                relativeLayout2.setHorizontalGravity(3);
                relativeLayout2.setVerticalGravity(16);
                Integer num = 1;
                relativeLayout2.setId(num.intValue());
                ImageButton back = new ImageButton(InAppBrowser.this.cordova.getActivity());
                RelativeLayout.LayoutParams backLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
                backLayoutParams.addRule(5);
                back.setLayoutParams(backLayoutParams);
                back.setContentDescription("Back Button");
                Integer num2 = 2;
                back.setId(num2.intValue());
                Resources activityRes = InAppBrowser.this.cordova.getActivity().getResources();
                int backResId = activityRes.getIdentifier("ic_action_previous_item", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName());
                Drawable backIcon = activityRes.getDrawable(backResId);
                back.setBackground(null);
                back.setImageDrawable(backIcon);
                back.setScaleType(ImageView.ScaleType.FIT_CENTER);
                back.setPadding(0, dpToPixels(10), 0, dpToPixels(10));
                back.getAdjustViewBounds();
                back.setOnClickListener(new View.OnClickListener() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.5.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        InAppBrowser.this.goBack();
                    }
                });
                ImageButton forward = new ImageButton(InAppBrowser.this.cordova.getActivity());
                RelativeLayout.LayoutParams forwardLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
                forwardLayoutParams.addRule(1, 2);
                forward.setLayoutParams(forwardLayoutParams);
                forward.setContentDescription("Forward Button");
                Integer num3 = 3;
                forward.setId(num3.intValue());
                int fwdResId = activityRes.getIdentifier("ic_action_next_item", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName());
                Drawable fwdIcon = activityRes.getDrawable(fwdResId);
                forward.setBackground(null);
                forward.setImageDrawable(fwdIcon);
                forward.setScaleType(ImageView.ScaleType.FIT_CENTER);
                forward.setPadding(0, dpToPixels(10), 0, dpToPixels(10));
                forward.getAdjustViewBounds();
                forward.setOnClickListener(new View.OnClickListener() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.5.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        InAppBrowser.this.goForward();
                    }
                });
                InAppBrowser.this.edittext = new EditText(InAppBrowser.this.cordova.getActivity());
                RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
                textLayoutParams.addRule(1, 1);
                textLayoutParams.addRule(0, 5);
                InAppBrowser.this.edittext.setLayoutParams(textLayoutParams);
                Integer num4 = 4;
                InAppBrowser.this.edittext.setId(num4.intValue());
                InAppBrowser.this.edittext.setSingleLine(true);
                InAppBrowser.this.edittext.setText(url);
                InAppBrowser.this.edittext.setInputType(16);
                InAppBrowser.this.edittext.setImeOptions(2);
                InAppBrowser.this.edittext.setInputType(0);
                InAppBrowser.this.edittext.setOnKeyListener(new View.OnKeyListener() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.5.3
                    @Override // android.view.View.OnKeyListener
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() != 0 || keyCode != 66) {
                            return false;
                        }
                        InAppBrowser.this.navigate(InAppBrowser.this.edittext.getText().toString());
                        return true;
                    }
                });
                ImageButton close = new ImageButton(InAppBrowser.this.cordova.getActivity());
                RelativeLayout.LayoutParams closeLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
                closeLayoutParams.addRule(11);
                close.setLayoutParams(closeLayoutParams);
                forward.setContentDescription("Close Button");
                Integer num5 = 5;
                close.setId(num5.intValue());
                int closeResId = activityRes.getIdentifier("ic_action_remove", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName());
                Drawable closeIcon = activityRes.getDrawable(closeResId);
                close.setBackground(null);
                close.setImageDrawable(closeIcon);
                close.setScaleType(ImageView.ScaleType.FIT_CENTER);
                back.setPadding(0, dpToPixels(10), 0, dpToPixels(10));
                close.getAdjustViewBounds();
                close.setOnClickListener(new View.OnClickListener() { // from class: org.apache.cordova.inappbrowser.InAppBrowser.5.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        InAppBrowser.this.closeDialog();
                    }
                });
                InAppBrowser.this.inAppWebView = new WebView(InAppBrowser.this.cordova.getActivity());
                InAppBrowser.this.inAppWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                Integer num6 = 6;
                InAppBrowser.this.inAppWebView.setId(num6.intValue());
                InAppBrowser.this.inAppWebView.setWebChromeClient(new InAppChromeClient(thatWebView));
                WebViewClient client = InAppBrowser.this.new InAppBrowserClient(thatWebView, InAppBrowser.this.edittext);
                InAppBrowser.this.inAppWebView.setWebViewClient(client);
                WebSettings settings = InAppBrowser.this.inAppWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setBuiltInZoomControls(InAppBrowser.this.showZoomControls);
                settings.setPluginState(WebSettings.PluginState.ON);
                if (Build.VERSION.SDK_INT >= 17) {
                    settings.setMediaPlaybackRequiresUserGesture(InAppBrowser.this.mediaPlaybackRequiresUserGesture);
                }
                String overrideUserAgent = InAppBrowser.this.preferences.getString("OverrideUserAgent", null);
                String appendUserAgent = InAppBrowser.this.preferences.getString("AppendUserAgent", null);
                if (overrideUserAgent != null) {
                    settings.setUserAgentString(overrideUserAgent);
                }
                if (appendUserAgent != null) {
                    settings.setUserAgentString(settings.getUserAgentString() + appendUserAgent);
                }
                Bundle appSettings = InAppBrowser.this.cordova.getActivity().getIntent().getExtras();
                boolean enableDatabase = appSettings == null ? true : appSettings.getBoolean("InAppBrowserStorageEnabled", true);
                if (enableDatabase) {
                    String databasePath = InAppBrowser.this.cordova.getActivity().getApplicationContext().getDir("inAppBrowserDB", 0).getPath();
                    settings.setDatabasePath(databasePath);
                    settings.setDatabaseEnabled(true);
                }
                settings.setDomStorageEnabled(true);
                if (!InAppBrowser.this.clearAllCache) {
                    if (InAppBrowser.this.clearSessionCache) {
                        CookieManager.getInstance().removeSessionCookie();
                    }
                } else {
                    CookieManager.getInstance().removeAllCookie();
                }
                InAppBrowser.this.inAppWebView.loadUrl(url);
                Integer num7 = 6;
                InAppBrowser.this.inAppWebView.setId(num7.intValue());
                InAppBrowser.this.inAppWebView.getSettings().setLoadWithOverviewMode(true);
                InAppBrowser.this.inAppWebView.getSettings().setUseWideViewPort(true);
                InAppBrowser.this.inAppWebView.requestFocus();
                InAppBrowser.this.inAppWebView.requestFocusFromTouch();
                relativeLayout2.addView(back);
                relativeLayout2.addView(forward);
                relativeLayout.addView(relativeLayout2);
                relativeLayout.addView(InAppBrowser.this.edittext);
                relativeLayout.addView(close);
                if (InAppBrowser.this.getShowLocationBar()) {
                    linearLayout.addView(relativeLayout);
                }
                linearLayout.addView(InAppBrowser.this.inAppWebView);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(InAppBrowser.this.dialog.getWindow().getAttributes());
                lp.width = -1;
                lp.height = -1;
                InAppBrowser.this.dialog.setContentView(linearLayout);
                InAppBrowser.this.dialog.show();
                InAppBrowser.this.dialog.getWindow().setAttributes(lp);
                if (InAppBrowser.this.openWindowHidden) {
                    InAppBrowser.this.dialog.hide();
                }
            }
        };
        this.cordova.getActivity().runOnUiThread(runnable);
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUpdate(JSONObject obj, boolean keepCallback) {
        sendUpdate(obj, keepCallback, PluginResult.Status.OK);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUpdate(JSONObject obj, boolean keepCallback, PluginResult.Status status) {
        if (this.callbackContext != null) {
            PluginResult result = new PluginResult(status, obj);
            result.setKeepCallback(keepCallback);
            this.callbackContext.sendPluginResult(result);
            if (!keepCallback) {
                this.callbackContext = null;
            }
        }
    }

    public class InAppBrowserClient extends WebViewClient {
        EditText edittext;
        CordovaWebView webView;

        public InAppBrowserClient(CordovaWebView webView, EditText mEditText) {
            this.webView = webView;
            this.edittext = mEditText;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            String address;
            if (url.startsWith("tel:")) {
                try {
                    Intent intent = new Intent("android.intent.action.DIAL");
                    intent.setData(Uri.parse(url));
                    InAppBrowser.this.cordova.getActivity().startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    LOG.e(InAppBrowser.LOG_TAG, "Error dialing " + url + ": " + e.toString());
                }
            } else if (url.startsWith("geo:") || url.startsWith("mailto:") || url.startsWith("market:")) {
                try {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(url));
                    InAppBrowser.this.cordova.getActivity().startActivity(intent2);
                    return true;
                } catch (ActivityNotFoundException e2) {
                    LOG.e(InAppBrowser.LOG_TAG, "Error with " + url + ": " + e2.toString());
                }
            } else if (url.startsWith("sms:")) {
                try {
                    Intent intent3 = new Intent("android.intent.action.VIEW");
                    int parmIndex = url.indexOf(63);
                    if (parmIndex == -1) {
                        address = url.substring(4);
                    } else {
                        address = url.substring(4, parmIndex);
                        Uri uri = Uri.parse(url);
                        String query = uri.getQuery();
                        if (query != null && query.startsWith("body=")) {
                            intent3.putExtra("sms_body", query.substring(5));
                        }
                    }
                    intent3.setData(Uri.parse("sms:" + address));
                    intent3.putExtra("address", address);
                    intent3.setType("vnd.android-dir/mms-sms");
                    InAppBrowser.this.cordova.getActivity().startActivity(intent3);
                    return true;
                } catch (ActivityNotFoundException e3) {
                    LOG.e(InAppBrowser.LOG_TAG, "Error sending sms " + url + ":" + e3.toString());
                }
            }
            return false;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView view, String url, Bitmap favicon) throws JSONException {
            String newloc;
            super.onPageStarted(view, url, favicon);
            if (url.startsWith("http:") || url.startsWith("https:") || url.startsWith("file:")) {
                newloc = url;
            } else {
                LOG.e(InAppBrowser.LOG_TAG, "Possible Uncaught/Unknown URI");
                newloc = "http://" + url;
            }
            if (!newloc.equals(this.edittext.getText().toString())) {
                this.edittext.setText(newloc);
            }
            try {
                JSONObject obj = new JSONObject();
                obj.put("type", InAppBrowser.LOAD_START_EVENT);
                obj.put("url", newloc);
                InAppBrowser.this.sendUpdate(obj, true);
            } catch (JSONException e) {
                LOG.e(InAppBrowser.LOG_TAG, "URI passed in has caused a JSON error.");
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView view, String url) throws JSONException {
            super.onPageFinished(view, url);
            if (Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
            try {
                JSONObject obj = new JSONObject();
                obj.put("type", InAppBrowser.LOAD_STOP_EVENT);
                obj.put("url", url);
                InAppBrowser.this.sendUpdate(obj, true);
            } catch (JSONException e) {
                Log.d(InAppBrowser.LOG_TAG, "Should never happen");
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) throws JSONException {
            super.onReceivedError(view, errorCode, description, failingUrl);
            try {
                JSONObject obj = new JSONObject();
                obj.put("type", InAppBrowser.LOAD_ERROR_EVENT);
                obj.put("url", failingUrl);
                obj.put("code", errorCode);
                obj.put("message", description);
                InAppBrowser.this.sendUpdate(obj, true, PluginResult.Status.ERROR);
            } catch (JSONException e) {
                Log.d(InAppBrowser.LOG_TAG, "Should never happen");
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) throws NoSuchFieldException, NoSuchMethodException, SecurityException {
            PluginManager pluginManager = null;
            try {
                Method gpm = this.webView.getClass().getMethod("getPluginManager", new Class[0]);
                pluginManager = (PluginManager) gpm.invoke(this.webView, new Object[0]);
            } catch (IllegalAccessException e) {
            } catch (NoSuchMethodException e2) {
            } catch (InvocationTargetException e3) {
            }
            if (pluginManager == null) {
                try {
                    Field pmf = this.webView.getClass().getField("pluginManager");
                    pluginManager = (PluginManager) pmf.get(this.webView);
                } catch (IllegalAccessException e4) {
                } catch (NoSuchFieldException e5) {
                }
            }
            if (pluginManager == null || !pluginManager.onReceivedHttpAuthRequest(this.webView, new CordovaHttpAuthHandler(handler), host, realm)) {
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }
        }
    }
}
