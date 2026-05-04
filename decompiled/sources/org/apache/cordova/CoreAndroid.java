package org.apache.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class CoreAndroid extends CordovaPlugin {
    public static final String PLUGIN_NAME = "CoreAndroid";
    protected static final String TAG = "CordovaApp";
    private CallbackContext messageChannel;
    private final Object messageChannelLock = new Object();
    private PluginResult pendingResume;
    private BroadcastReceiver telephonyReceiver;

    CoreAndroid() {
    }

    public void fireJavascriptEvent(String action) throws JSONException {
        sendEventMessage(action);
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void pluginInitialize() {
        initTelephonyReceiver();
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        PluginResult.Status status = PluginResult.Status.OK;
        try {
            if (action.equals("clearCache")) {
                clearCache();
            } else if (action.equals("show")) {
                this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.CoreAndroid.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CoreAndroid.this.webView.getPluginManager().postMessage("spinner", "stop");
                    }
                });
            } else if (action.equals("loadUrl")) {
                loadUrl(args.getString(0), args.optJSONObject(1));
            } else if (!action.equals("cancelLoadUrl")) {
                if (action.equals("clearHistory")) {
                    clearHistory();
                } else if (action.equals("backHistory")) {
                    backHistory();
                } else if (action.equals("overrideButton")) {
                    overrideButton(args.getString(0), args.getBoolean(1));
                } else if (action.equals("overrideBackbutton")) {
                    overrideBackbutton(args.getBoolean(0));
                } else if (action.equals("exitApp")) {
                    exitApp();
                } else if (action.equals("messageChannel")) {
                    synchronized (this.messageChannelLock) {
                        this.messageChannel = callbackContext;
                        if (this.pendingResume != null) {
                            sendEventMessage(this.pendingResume);
                            this.pendingResume = null;
                        }
                    }
                    return true;
                }
            }
            callbackContext.sendPluginResult(new PluginResult(status, ""));
            return true;
        } catch (JSONException e) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
            return false;
        }
    }

    public void clearCache() {
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.CoreAndroid.2
            @Override // java.lang.Runnable
            public void run() {
                CoreAndroid.this.webView.clearCache(true);
            }
        });
    }

    public void loadUrl(String url, JSONObject props) throws JSONException {
        LOG.d("App", "App.loadUrl(" + url + "," + props + ")");
        int wait = 0;
        boolean openExternal = false;
        boolean clearHistory = false;
        HashMap<String, Object> params = new HashMap<>();
        if (props != null) {
            JSONArray keys = props.names();
            for (int i = 0; i < keys.length(); i++) {
                String key = keys.getString(i);
                if (key.equals("wait")) {
                    wait = props.getInt(key);
                } else if (key.equalsIgnoreCase("openexternal")) {
                    openExternal = props.getBoolean(key);
                } else if (key.equalsIgnoreCase("clearhistory")) {
                    clearHistory = props.getBoolean(key);
                } else {
                    Object value = props.get(key);
                    if (value != null) {
                        if (value.getClass().equals(String.class)) {
                            params.put(key, (String) value);
                        } else if (value.getClass().equals(Boolean.class)) {
                            params.put(key, (Boolean) value);
                        } else if (value.getClass().equals(Integer.class)) {
                            params.put(key, (Integer) value);
                        }
                    }
                }
            }
        }
        if (wait > 0) {
            try {
                synchronized (this) {
                    wait(wait);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.webView.showWebPage(url, openExternal, clearHistory, params);
    }

    public void clearHistory() {
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.CoreAndroid.3
            @Override // java.lang.Runnable
            public void run() {
                CoreAndroid.this.webView.clearHistory();
            }
        });
    }

    public void backHistory() {
        this.cordova.getActivity().runOnUiThread(new Runnable() { // from class: org.apache.cordova.CoreAndroid.4
            @Override // java.lang.Runnable
            public void run() {
                CoreAndroid.this.webView.backHistory();
            }
        });
    }

    public void overrideBackbutton(boolean override) {
        LOG.i("App", "WARNING: Back Button Default Behavior will be overridden.  The backbutton event will be fired!");
        this.webView.setButtonPlumbedToJs(4, override);
    }

    public void overrideButton(String button, boolean override) {
        LOG.i("App", "WARNING: Volume Button Default Behavior will be overridden.  The volume event will be fired!");
        if (button.equals("volumeup")) {
            this.webView.setButtonPlumbedToJs(24, override);
        } else if (button.equals("volumedown")) {
            this.webView.setButtonPlumbedToJs(25, override);
        } else if (button.equals("menubutton")) {
            this.webView.setButtonPlumbedToJs(82, override);
        }
    }

    public boolean isBackbuttonOverridden() {
        return this.webView.isButtonPlumbedToJs(4);
    }

    public void exitApp() {
        this.webView.getPluginManager().postMessage("exit", null);
    }

    private void initTelephonyReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.telephonyReceiver = new BroadcastReceiver() { // from class: org.apache.cordova.CoreAndroid.5
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction().equals("android.intent.action.PHONE_STATE") && intent.hasExtra("state")) {
                    String extraData = intent.getStringExtra("state");
                    if (extraData.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                        LOG.i(CoreAndroid.TAG, "Telephone RINGING");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "ringing");
                    } else if (extraData.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                        LOG.i(CoreAndroid.TAG, "Telephone OFFHOOK");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "offhook");
                    } else if (extraData.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                        LOG.i(CoreAndroid.TAG, "Telephone IDLE");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "idle");
                    }
                }
            }
        };
        this.webView.getContext().registerReceiver(this.telephonyReceiver, intentFilter);
    }

    private void sendEventMessage(String action) throws JSONException {
        JSONObject obj = new JSONObject();
        try {
            obj.put("action", action);
        } catch (JSONException e) {
            LOG.e(TAG, "Failed to create event message", e);
        }
        sendEventMessage(new PluginResult(PluginResult.Status.OK, obj));
    }

    private void sendEventMessage(PluginResult payload) {
        payload.setKeepCallback(true);
        if (this.messageChannel != null) {
            this.messageChannel.sendPluginResult(payload);
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        this.webView.getContext().unregisterReceiver(this.telephonyReceiver);
    }

    public void sendResumeEvent(PluginResult resumeEvent) {
        synchronized (this.messageChannelLock) {
            if (this.messageChannel != null) {
                sendEventMessage(resumeEvent);
            } else {
                this.pendingResume = resumeEvent;
            }
        }
    }
}
