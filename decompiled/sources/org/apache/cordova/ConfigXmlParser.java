package org.apache.cordova;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class ConfigXmlParser {
    private static String TAG = "ConfigXmlParser";
    private String launchUrl = "file:///android_asset/www/index.html";
    private CordovaPreferences prefs = new CordovaPreferences();
    private ArrayList<PluginEntry> pluginEntries = new ArrayList<>(20);
    boolean insideFeature = false;
    String service = "";
    String pluginClass = "";
    String paramType = "";
    boolean onload = false;

    public CordovaPreferences getPreferences() {
        return this.prefs;
    }

    public ArrayList<PluginEntry> getPluginEntries() {
        return this.pluginEntries;
    }

    public String getLaunchUrl() {
        return this.launchUrl;
    }

    public void parse(Context action) throws XmlPullParserException, IOException {
        int id = action.getResources().getIdentifier("config", "xml", action.getClass().getPackage().getName());
        if (id == 0 && (id = action.getResources().getIdentifier("config", "xml", action.getPackageName())) == 0) {
            LOG.e(TAG, "res/xml/config.xml is missing!");
        } else {
            parse(action.getResources().getXml(id));
        }
    }

    public void parse(XmlPullParser xml) throws XmlPullParserException, IOException {
        int eventType = -1;
        while (eventType != 1) {
            if (eventType == 2) {
                handleStartTag(xml);
            } else if (eventType == 3) {
                handleEndTag(xml);
            }
            try {
                eventType = xml.next();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void handleStartTag(XmlPullParser xml) {
        String src;
        String strNode = xml.getName();
        if (strNode.equals("feature")) {
            this.insideFeature = true;
            this.service = xml.getAttributeValue(null, "name");
            return;
        }
        if (this.insideFeature && strNode.equals("param")) {
            this.paramType = xml.getAttributeValue(null, "name");
            if (this.paramType.equals(NotificationCompat.CATEGORY_SERVICE)) {
                this.service = xml.getAttributeValue(null, FirebaseAnalytics.Param.VALUE);
                return;
            }
            if (this.paramType.equals("package") || this.paramType.equals("android-package")) {
                this.pluginClass = xml.getAttributeValue(null, FirebaseAnalytics.Param.VALUE);
                return;
            } else {
                if (this.paramType.equals("onload")) {
                    this.onload = "true".equals(xml.getAttributeValue(null, FirebaseAnalytics.Param.VALUE));
                    return;
                }
                return;
            }
        }
        if (strNode.equals("preference")) {
            String name = xml.getAttributeValue(null, "name").toLowerCase(Locale.ENGLISH);
            String value = xml.getAttributeValue(null, FirebaseAnalytics.Param.VALUE);
            this.prefs.set(name, value);
        } else if (strNode.equals(FirebaseAnalytics.Param.CONTENT) && (src = xml.getAttributeValue(null, "src")) != null) {
            setStartUrl(src);
        }
    }

    public void handleEndTag(XmlPullParser xml) {
        String strNode = xml.getName();
        if (strNode.equals("feature")) {
            this.pluginEntries.add(new PluginEntry(this.service, this.pluginClass, this.onload));
            this.service = "";
            this.pluginClass = "";
            this.insideFeature = false;
            this.onload = false;
        }
    }

    private void setStartUrl(String src) {
        Pattern schemeRegex = Pattern.compile("^[a-z-]+://");
        Matcher matcher = schemeRegex.matcher(src);
        if (matcher.find()) {
            this.launchUrl = src;
            return;
        }
        if (src.charAt(0) == '/') {
            src = src.substring(1);
        }
        this.launchUrl = "file:///android_asset/www/" + src;
    }
}
