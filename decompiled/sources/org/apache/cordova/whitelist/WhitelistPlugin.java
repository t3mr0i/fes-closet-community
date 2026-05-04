package org.apache.cordova.whitelist;

import android.content.Context;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.Whitelist;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class WhitelistPlugin extends CordovaPlugin {
    private static final String LOG_TAG = "WhitelistPlugin";
    private Whitelist allowedIntents;
    private Whitelist allowedNavigations;
    private Whitelist allowedRequests;

    public WhitelistPlugin() {
    }

    public WhitelistPlugin(Context context) {
        this(new Whitelist(), new Whitelist(), null);
        new CustomConfigXmlParser().parse(context);
    }

    public WhitelistPlugin(XmlPullParser xmlParser) {
        this(new Whitelist(), new Whitelist(), null);
        new CustomConfigXmlParser().parse(xmlParser);
    }

    public WhitelistPlugin(Whitelist allowedNavigations, Whitelist allowedIntents, Whitelist allowedRequests) {
        if (allowedRequests == null) {
            allowedRequests = new Whitelist();
            allowedRequests.addWhiteListEntry("file:///*", false);
            allowedRequests.addWhiteListEntry("data:*", false);
        }
        this.allowedNavigations = allowedNavigations;
        this.allowedIntents = allowedIntents;
        this.allowedRequests = allowedRequests;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void pluginInitialize() {
        if (this.allowedNavigations == null) {
            this.allowedNavigations = new Whitelist();
            this.allowedIntents = new Whitelist();
            this.allowedRequests = new Whitelist();
            new CustomConfigXmlParser().parse(this.webView.getContext());
        }
    }

    private class CustomConfigXmlParser extends ConfigXmlParser {
        private CustomConfigXmlParser() {
        }

        @Override // org.apache.cordova.ConfigXmlParser
        public void handleStartTag(XmlPullParser xml) {
            String strNode = xml.getName();
            if (strNode.equals(FirebaseAnalytics.Param.CONTENT)) {
                String startPage = xml.getAttributeValue(null, "src");
                WhitelistPlugin.this.allowedNavigations.addWhiteListEntry(startPage, false);
                return;
            }
            if (strNode.equals("allow-navigation")) {
                String origin = xml.getAttributeValue(null, "href");
                if ("*".equals(origin)) {
                    WhitelistPlugin.this.allowedNavigations.addWhiteListEntry("http://*/*", false);
                    WhitelistPlugin.this.allowedNavigations.addWhiteListEntry("https://*/*", false);
                    WhitelistPlugin.this.allowedNavigations.addWhiteListEntry("data:*", false);
                    return;
                }
                WhitelistPlugin.this.allowedNavigations.addWhiteListEntry(origin, false);
                return;
            }
            if (strNode.equals("allow-intent")) {
                WhitelistPlugin.this.allowedIntents.addWhiteListEntry(xml.getAttributeValue(null, "href"), false);
                return;
            }
            if (strNode.equals("access")) {
                String origin2 = xml.getAttributeValue(null, FirebaseAnalytics.Param.ORIGIN);
                String subdomains = xml.getAttributeValue(null, "subdomains");
                boolean external = xml.getAttributeValue(null, "launch-external") != null;
                if (origin2 != null) {
                    if (external) {
                        Log.w(WhitelistPlugin.LOG_TAG, "Found <access launch-external> within config.xml. Please use <allow-intent> instead.");
                        WhitelistPlugin.this.allowedIntents.addWhiteListEntry(origin2, subdomains != null && subdomains.compareToIgnoreCase("true") == 0);
                    } else if ("*".equals(origin2)) {
                        WhitelistPlugin.this.allowedRequests.addWhiteListEntry("http://*/*", false);
                        WhitelistPlugin.this.allowedRequests.addWhiteListEntry("https://*/*", false);
                    } else {
                        WhitelistPlugin.this.allowedRequests.addWhiteListEntry(origin2, subdomains != null && subdomains.compareToIgnoreCase("true") == 0);
                    }
                }
            }
        }

        @Override // org.apache.cordova.ConfigXmlParser
        public void handleEndTag(XmlPullParser xml) {
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public Boolean shouldAllowNavigation(String url) {
        return this.allowedNavigations.isUrlWhiteListed(url) ? true : null;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public Boolean shouldAllowRequest(String url) {
        if (Boolean.TRUE == shouldAllowNavigation(url) || this.allowedRequests.isUrlWhiteListed(url)) {
            return true;
        }
        return null;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public Boolean shouldOpenExternalUrl(String url) {
        return this.allowedIntents.isUrlWhiteListed(url) ? true : null;
    }

    public Whitelist getAllowedNavigations() {
        return this.allowedNavigations;
    }

    public void setAllowedNavigations(Whitelist allowedNavigations) {
        this.allowedNavigations = allowedNavigations;
    }

    public Whitelist getAllowedIntents() {
        return this.allowedIntents;
    }

    public void setAllowedIntents(Whitelist allowedIntents) {
        this.allowedIntents = allowedIntents;
    }

    public Whitelist getAllowedRequests() {
        return this.allowedRequests;
    }

    public void setAllowedRequests(Whitelist allowedRequests) {
        this.allowedRequests = allowedRequests;
    }
}
