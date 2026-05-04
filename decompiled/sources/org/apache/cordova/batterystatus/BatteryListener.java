package org.apache.cordova.batterystatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BatteryListener extends CordovaPlugin {
    private static final String LOG_TAG = "BatteryManager";
    private CallbackContext batteryCallbackContext = null;
    BroadcastReceiver receiver = null;

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("start")) {
            if (this.batteryCallbackContext != null) {
                callbackContext.error("Battery listener already running.");
                return true;
            }
            this.batteryCallbackContext = callbackContext;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            if (this.receiver == null) {
                this.receiver = new BroadcastReceiver() { // from class: org.apache.cordova.batterystatus.BatteryListener.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context, Intent intent) {
                        BatteryListener.this.updateBatteryInfo(intent);
                    }
                };
                this.webView.getContext().registerReceiver(this.receiver, intentFilter);
            }
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return true;
        }
        if (!action.equals("stop")) {
            return false;
        }
        removeBatteryListener();
        sendUpdate(new JSONObject(), false);
        this.batteryCallbackContext = null;
        callbackContext.success();
        return true;
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        removeBatteryListener();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onReset() {
        removeBatteryListener();
    }

    private void removeBatteryListener() {
        if (this.receiver != null) {
            try {
                this.webView.getContext().unregisterReceiver(this.receiver);
                this.receiver = null;
            } catch (Exception e) {
                Log.e(LOG_TAG, "Error unregistering battery receiver: " + e.getMessage(), e);
            }
        }
    }

    private JSONObject getBatteryInfo(Intent batteryIntent) throws JSONException {
        JSONObject obj = new JSONObject();
        try {
            obj.put(FirebaseAnalytics.Param.LEVEL, batteryIntent.getIntExtra(FirebaseAnalytics.Param.LEVEL, 0));
            obj.put("isPlugged", batteryIntent.getIntExtra("plugged", -1) > 0);
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBatteryInfo(Intent batteryIntent) {
        sendUpdate(getBatteryInfo(batteryIntent), true);
    }

    private void sendUpdate(JSONObject info, boolean keepCallback) {
        if (this.batteryCallbackContext != null) {
            PluginResult result = new PluginResult(PluginResult.Status.OK, info);
            result.setKeepCallback(keepCallback);
            this.batteryCallbackContext.sendPluginResult(result);
        }
    }
}
