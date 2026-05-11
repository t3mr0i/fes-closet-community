package org.apache.cordova;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CordovaInterfaceImpl implements CordovaInterface {
    private static final String TAG = "CordovaInterfaceImpl";
    protected Activity activity;
    protected CordovaPlugin activityResultCallback;
    protected int activityResultRequestCode;
    protected boolean activityWasDestroyed;
    protected String initCallbackService;
    protected CordovaPlugin permissionResultCallback;
    protected PluginManager pluginManager;
    protected Bundle savedPluginState;
    protected ActivityResultHolder savedResult;
    protected ExecutorService threadPool;

    public CordovaInterfaceImpl(Activity activity) {
        this(activity, Executors.newCachedThreadPool());
    }

    public CordovaInterfaceImpl(Activity activity, ExecutorService threadPool) {
        this.activityWasDestroyed = false;
        this.activity = activity;
        this.threadPool = threadPool;
    }

    @Override // org.apache.cordova.CordovaInterface
    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {
        setActivityResultCallback(command);
        try {
            this.activity.startActivityForResult(intent, requestCode);
        } catch (RuntimeException e) {
            this.activityResultCallback = null;
            throw e;
        }
    }

    @Override // org.apache.cordova.CordovaInterface
    public void setActivityResultCallback(CordovaPlugin plugin) {
        if (this.activityResultCallback != null) {
            this.activityResultCallback.onActivityResult(this.activityResultRequestCode, 0, null);
        }
        this.activityResultCallback = plugin;
    }

    @Override // org.apache.cordova.CordovaInterface
    public Activity getActivity() {
        return this.activity;
    }

    @Override // org.apache.cordova.CordovaInterface
    public Object onMessage(String id, Object data) {
        if ("exit".equals(id)) {
            this.activity.finish();
            return null;
        }
        return null;
    }

    @Override // org.apache.cordova.CordovaInterface
    public ExecutorService getThreadPool() {
        return this.threadPool;
    }

    public void onCordovaInit(PluginManager pluginManager) {
        CoreAndroid appPlugin;
        this.pluginManager = pluginManager;
        if (this.savedResult == null) {
            if (this.activityWasDestroyed) {
                this.activityWasDestroyed = false;
                if (pluginManager != null && (appPlugin = (CoreAndroid) pluginManager.getPlugin(CoreAndroid.PLUGIN_NAME)) != null) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("action", "resume");
                    } catch (JSONException e) {
                        LOG.e(TAG, "Failed to create event message", e);
                    }
                    appPlugin.sendResumeEvent(new PluginResult(PluginResult.Status.OK, obj));
                    return;
                }
                return;
            }
            return;
        }
        onActivityResult(this.savedResult.requestCode, this.savedResult.resultCode, this.savedResult.intent);
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        CordovaPlugin callback = this.activityResultCallback;
        if (callback == null && this.initCallbackService != null) {
            this.savedResult = new ActivityResultHolder(requestCode, resultCode, intent);
            if (this.pluginManager != null && (callback = this.pluginManager.getPlugin(this.initCallbackService)) != null) {
                callback.onRestoreStateForActivityResult(this.savedPluginState.getBundle(callback.getServiceName()), new ResumeCallback(callback.getServiceName(), this.pluginManager));
            }
        }
        this.activityResultCallback = null;
        if (callback != null) {
            Log.d(TAG, "Sending activity result to plugin");
            this.initCallbackService = null;
            this.savedResult = null;
            callback.onActivityResult(requestCode, resultCode, intent);
            return true;
        }
        Log.w(TAG, "Got an activity result, but no plugin was registered to receive it" + (this.savedResult != null ? " yet!" : "."));
        return false;
    }

    public void setActivityResultRequestCode(int requestCode) {
        this.activityResultRequestCode = requestCode;
    }

    public void onSaveInstanceState(Bundle outState) {
        if (this.activityResultCallback != null) {
            String serviceName = this.activityResultCallback.getServiceName();
            outState.putString("callbackService", serviceName);
        }
        if (this.pluginManager != null) {
            outState.putBundle("plugin", this.pluginManager.onSaveInstanceState());
        }
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        this.initCallbackService = savedInstanceState.getString("callbackService");
        this.savedPluginState = savedInstanceState.getBundle("plugin");
        this.activityWasDestroyed = true;
    }

    private static class ActivityResultHolder {
        private Intent intent;
        private int requestCode;
        private int resultCode;

        public ActivityResultHolder(int requestCode, int resultCode, Intent intent) {
            this.requestCode = requestCode;
            this.resultCode = resultCode;
            this.intent = intent;
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
        if (this.permissionResultCallback != null) {
            this.permissionResultCallback.onRequestPermissionResult(requestCode, permissions, grantResults);
            this.permissionResultCallback = null;
        }
    }

    @Override // org.apache.cordova.CordovaInterface
    public void requestPermission(CordovaPlugin plugin, int requestCode, String permission) {
        this.permissionResultCallback = plugin;
        String[] permissions = {permission};
        getActivity().requestPermissions(permissions, requestCode);
    }

    @Override // org.apache.cordova.CordovaInterface
    public void requestPermissions(CordovaPlugin plugin, int requestCode, String[] permissions) {
        this.permissionResultCallback = plugin;
        getActivity().requestPermissions(permissions, requestCode);
    }

    @Override // org.apache.cordova.CordovaInterface
    public boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        int result = this.activity.checkSelfPermission(permission);
        return result == 0;
    }
}
