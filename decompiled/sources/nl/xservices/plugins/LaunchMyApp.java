package nl.xservices.plugins;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class LaunchMyApp extends CordovaPlugin {
    private static final String ACTION_CHECKINTENT = "checkIntent";
    private static final String ACTION_CLEARINTENT = "clearIntent";
    private static final String ACTION_GETLASTINTENT = "getLastIntent";
    private String lastIntentString = null;
    private boolean resetIntent;

    @Override // org.apache.cordova.CordovaPlugin
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        this.resetIntent = this.preferences.getBoolean("resetIntent", false) || this.preferences.getBoolean("CustomURLSchemePluginClearsAndroidIntent", false);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (ACTION_CLEARINTENT.equalsIgnoreCase(action)) {
            Intent intent = ((CordovaActivity) this.webView.getContext()).getIntent();
            if (this.resetIntent) {
                intent.setData(null);
            }
            return true;
        }
        if (ACTION_CHECKINTENT.equalsIgnoreCase(action)) {
            Intent intent2 = ((CordovaActivity) this.webView.getContext()).getIntent();
            String intentString = intent2.getDataString();
            if (intentString != null && intent2.getScheme() != null) {
                this.lastIntentString = intentString;
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, intent2.getDataString()));
            } else {
                callbackContext.error("App was not started via the launchmyapp URL scheme. Ignoring this errorcallback is the best approach.");
            }
            return true;
        }
        if (ACTION_GETLASTINTENT.equalsIgnoreCase(action)) {
            if (this.lastIntentString != null) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, this.lastIntentString));
            } else {
                callbackContext.error("No intent received so far.");
            }
            return true;
        }
        callbackContext.error("This plugin only responds to the checkIntent action.");
        return false;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onNewIntent(Intent intent) {
        String intentString = intent.getDataString();
        if (intentString != null && intent.getScheme() != null) {
            if (this.resetIntent) {
                intent.setData(null);
            }
            try {
                StringWriter writer = new StringWriter(intentString.length() * 2);
                escapeJavaStyleString(writer, intentString, true, false);
                this.webView.loadUrl("javascript:handleOpenURL('" + writer.toString() + "');");
            } catch (IOException e) {
            }
        }
    }

    private static void escapeJavaStyleString(Writer out, String str, boolean escapeSingleQuote, boolean escapeForwardSlash) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (str != null) {
            int sz = str.length();
            for (int i = 0; i < sz; i++) {
                char ch = str.charAt(i);
                if (ch > 4095) {
                    out.write("\\u" + hex(ch));
                } else if (ch > 255) {
                    out.write("\\u0" + hex(ch));
                } else if (ch > 127) {
                    out.write("\\u00" + hex(ch));
                } else if (ch < ' ') {
                    switch (ch) {
                        case '\b':
                            out.write(92);
                            out.write(98);
                            break;
                        case '\t':
                            out.write(92);
                            out.write(116);
                            break;
                        case '\n':
                            out.write(92);
                            out.write(110);
                            break;
                        case 11:
                        default:
                            if (ch > 15) {
                                out.write("\\u00" + hex(ch));
                                break;
                            } else {
                                out.write("\\u000" + hex(ch));
                                break;
                            }
                        case '\f':
                            out.write(92);
                            out.write(102);
                            break;
                        case '\r':
                            out.write(92);
                            out.write(114);
                            break;
                    }
                } else {
                    switch (ch) {
                        case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                            out.write(92);
                            out.write(34);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_8 /* 39 */:
                            if (escapeSingleQuote) {
                                out.write(92);
                            }
                            out.write(39);
                            break;
                        case MotionEventCompat.AXIS_GENERIC_16 /* 47 */:
                            if (escapeForwardSlash) {
                                out.write(92);
                            }
                            out.write(47);
                            break;
                        case '\\':
                            out.write(92);
                            out.write(92);
                            break;
                        default:
                            out.write(ch);
                            break;
                    }
                }
            }
        }
    }

    private static String hex(char ch) {
        return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
    }
}
