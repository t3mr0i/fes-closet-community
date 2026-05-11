package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import org.apache.cordova.AuthenticationToken;
import org.apache.cordova.CordovaClientCertRequest;
import org.apache.cordova.CordovaHttpAuthHandler;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;

/* loaded from: classes.dex */
public class SystemWebViewClient extends WebViewClient {
    private static final String TAG = "SystemWebViewClient";
    boolean isCurrentlyLoading;
    protected final SystemWebViewEngine parentEngine;
    private boolean doClearHistory = false;
    private Hashtable<String, AuthenticationToken> authenticationTokens = new Hashtable<>();

    public SystemWebViewClient(SystemWebViewEngine parentEngine) {
        this.parentEngine = parentEngine;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return this.parentEngine.client.onNavigationAttempt(url);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        AuthenticationToken token = getAuthenticationToken(host, realm);
        if (token != null) {
            handler.proceed(token.getUserName(), token.getPassword());
            return;
        }
        PluginManager pluginManager = this.parentEngine.pluginManager;
        if (pluginManager != null && pluginManager.onReceivedHttpAuthRequest(null, new CordovaHttpAuthHandler(handler), host, realm)) {
            this.parentEngine.client.clearLoadTimeoutTimer();
        } else {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(MotionEventCompat.AXIS_WHEEL)
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        PluginManager pluginManager = this.parentEngine.pluginManager;
        if (pluginManager != null && pluginManager.onReceivedClientCertRequest(null, new CordovaClientCertRequest(request))) {
            this.parentEngine.client.clearLoadTimeoutTimer();
        } else {
            super.onReceivedClientCertRequest(view, request);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        this.isCurrentlyLoading = true;
        this.parentEngine.bridge.reset();
        this.parentEngine.client.onPageStarted(url);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (this.isCurrentlyLoading || url.startsWith("about:")) {
            this.isCurrentlyLoading = false;
            if (this.doClearHistory) {
                view.clearHistory();
                this.doClearHistory = false;
            }
            this.parentEngine.client.onPageFinishedLoading(url);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if (this.isCurrentlyLoading) {
            LOG.d(TAG, "CordovaWebViewClient.onReceivedError: Error code=%s Description=%s URL=%s", Integer.valueOf(errorCode), description, failingUrl);
            if (errorCode == -10) {
                this.parentEngine.client.clearLoadTimeoutTimer();
                if (view.canGoBack()) {
                    view.goBack();
                    return;
                }
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
            this.parentEngine.client.onReceivedError(errorCode, description, failingUrl);
        }
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(8)
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) throws PackageManager.NameNotFoundException {
        String packageName = this.parentEngine.cordova.getActivity().getPackageName();
        PackageManager pm = this.parentEngine.cordova.getActivity().getPackageManager();
        try {
            ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 128);
            if ((appInfo.flags & 2) != 0) {
                handler.proceed();
            } else {
                super.onReceivedSslError(view, handler, error);
            }
        } catch (PackageManager.NameNotFoundException e) {
            super.onReceivedSslError(view, handler, error);
        }
    }

    public void setAuthenticationToken(AuthenticationToken authenticationToken, String host, String realm) {
        if (host == null) {
            host = "";
        }
        if (realm == null) {
            realm = "";
        }
        this.authenticationTokens.put(host.concat(realm), authenticationToken);
    }

    public AuthenticationToken removeAuthenticationToken(String host, String realm) {
        return this.authenticationTokens.remove(host.concat(realm));
    }

    public AuthenticationToken getAuthenticationToken(String host, String realm) {
        AuthenticationToken token = this.authenticationTokens.get(host.concat(realm));
        if (token == null) {
            AuthenticationToken token2 = this.authenticationTokens.get(host);
            if (token2 == null) {
                token2 = this.authenticationTokens.get(realm);
            }
            if (token2 == null) {
                return this.authenticationTokens.get("");
            }
            return token2;
        }
        return token;
    }

    public void clearAuthenticationTokens() {
        this.authenticationTokens.clear();
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        WebResourceResponse webResourceResponse;
        try {
            if (!this.parentEngine.pluginManager.shouldAllowRequest(url)) {
                LOG.w(TAG, "URL blocked by whitelist: " + url);
                webResourceResponse = new WebResourceResponse("text/plain", "UTF-8", null);
            } else {
                CordovaResourceApi resourceApi = this.parentEngine.resourceApi;
                Uri origUri = Uri.parse(url);
                Uri remappedUri = resourceApi.remapUri(origUri);
                if (!origUri.equals(remappedUri) || needsSpecialsInAssetUrlFix(origUri) || needsKitKatContentUrlFix(origUri)) {
                    CordovaResourceApi.OpenForReadResult result = resourceApi.openForRead(remappedUri, true);
                    webResourceResponse = new WebResourceResponse(result.mimeType, "UTF-8", result.inputStream);
                } else {
                    webResourceResponse = null;
                }
            }
            return webResourceResponse;
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                LOG.e(TAG, "Error occurred while loading a file (returning a 404).", e);
            }
            return new WebResourceResponse("text/plain", "UTF-8", null);
        }
    }

    private static boolean needsKitKatContentUrlFix(Uri uri) {
        return Build.VERSION.SDK_INT >= 19 && FirebaseAnalytics.Param.CONTENT.equals(uri.getScheme());
    }

    private static boolean needsSpecialsInAssetUrlFix(Uri uri) {
        if (CordovaResourceApi.getUriType(uri) != 1) {
            return false;
        }
        if (uri.getQuery() != null || uri.getFragment() != null) {
            return true;
        }
        if (!uri.toString().contains("%")) {
            return false;
        }
        switch (Build.VERSION.SDK_INT) {
            case 14:
            case 15:
                return true;
            default:
                return false;
        }
    }
}
