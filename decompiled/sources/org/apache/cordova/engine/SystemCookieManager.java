package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.webkit.CookieManager;
import android.webkit.WebView;
import org.apache.cordova.ICordovaCookieManager;

/* loaded from: classes.dex */
class SystemCookieManager implements ICordovaCookieManager {
    private final CookieManager cookieManager = CookieManager.getInstance();
    protected final WebView webView;

    @TargetApi(MotionEventCompat.AXIS_WHEEL)
    public SystemCookieManager(WebView webview) {
        this.webView = webview;
        if (Build.VERSION.SDK_INT >= 21) {
            this.cookieManager.setAcceptThirdPartyCookies(this.webView, true);
        }
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public void setCookiesEnabled(boolean accept) {
        this.cookieManager.setAcceptCookie(accept);
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public void setCookie(String url, String value) {
        this.cookieManager.setCookie(url, value);
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public String getCookie(String url) {
        return this.cookieManager.getCookie(url);
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public void clearCookies() {
        this.cookieManager.removeAllCookie();
    }

    @Override // org.apache.cordova.ICordovaCookieManager
    public void flush() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.cookieManager.flush();
        }
    }
}
