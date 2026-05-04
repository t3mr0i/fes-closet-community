package nl.xservices.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class Insomnia extends CordovaPlugin {
    private static final String ACTION_ALLOW_SLEEP_AGAIN = "allowSleepAgain";
    private static final String ACTION_KEEP_AWAKE = "keepAwake";

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        boolean z = true;
        try {
            if (ACTION_KEEP_AWAKE.equals(action)) {
                this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: nl.xservices.plugins.Insomnia.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Insomnia.this.cordova.getActivity().getWindow().addFlags(128);
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                    }
                });
            } else if (ACTION_ALLOW_SLEEP_AGAIN.equals(action)) {
                this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: nl.xservices.plugins.Insomnia.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Insomnia.this.cordova.getActivity().getWindow().clearFlags(128);
                        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                    }
                });
            } else {
                callbackContext.error("insomnia." + action + " is not a supported function. Did you mean '" + ACTION_KEEP_AWAKE + "'?");
                z = false;
            }
            return z;
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
            return false;
        }
    }
}
