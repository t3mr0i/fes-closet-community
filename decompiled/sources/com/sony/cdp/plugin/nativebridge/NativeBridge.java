package com.sony.cdp.plugin.nativebridge;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class NativeBridge extends CordovaPlugin {
    private static final String TAG = "[com.sony.cdp.plugin.nativebridge][Native][NativeBridge] ";
    private Map<String, Gate> mGates = new HashMap();

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        MethodContext context = new MethodContext(callbackContext.getCallbackId(), this.webView, args);
        if (context.className == null) {
            MessageUtils.sendErrorResult(callbackContext, context.taskId, 6, "[com.sony.cdp.plugin.nativebridge][Native][NativeBridge] the function is not supported on android.");
            return true;
        }
        if (action.equals("execTask")) {
            execTask(context);
            return true;
        }
        if (action.equals("cancelTask")) {
            cancelTask(context);
            return true;
        }
        if (action.equals("disposeTask")) {
            disposeTask(context);
            return true;
        }
        return false;
    }

    private void execTask(MethodContext context) throws JSONException {
        Gate gate = getGateClass(context.objectId, context.className);
        if (gate == null) {
            MessageUtils.sendErrorResult(context, context.taskId, 8, "[com.sony.cdp.plugin.nativebridge][Native][NativeBridge] class not found. class: " + context.className);
            return;
        }
        if (context.compatible) {
            if (!gate.execute(context.methodName, context.methodArgs, context)) {
                MessageUtils.sendErrorResult(context, context.taskId, 5, "[com.sony.cdp.plugin.nativebridge][Native][NativeBridge] execute() is not implemented. class: " + context.className);
            }
        } else {
            JSONObject errorResult = gate.invoke(context);
            if (errorResult != null) {
                MessageUtils.sendErrorResult(context, errorResult);
            }
        }
    }

    private String cancelTask(MethodContext context) throws JSONException {
        Gate gate = getGateClass(context.objectId, context.className);
        if (gate == null) {
            MessageUtils.sendErrorResult(context, context.taskId, 8, "[com.sony.cdp.plugin.nativebridge][Native][NativeBridge] class not found. class: " + context.className);
            return null;
        }
        gate.cancel(context);
        MessageUtils.sendSuccessResult(context, MessageUtils.makeMessage(null, new Object[0]));
        String objectId = context.objectId;
        return objectId;
    }

    private void disposeTask(MethodContext context) throws JSONException {
        String objectId = cancelTask(context);
        if (objectId != null) {
            this.mGates.remove(objectId);
        }
    }

    private Gate getGateClass(String objectId, String className) {
        Gate ret = this.mGates.get(objectId);
        if (ret == null && (ret = createGateClass(className)) != null) {
            this.mGates.put(objectId, ret);
        }
        return ret;
    }

    private Gate createGateClass(String className) throws ClassNotFoundException {
        Gate ret = null;
        Class<?> cls = null;
        if (className != null) {
            try {
                if (!"".equals(className)) {
                    cls = Class.forName(className);
                }
            } catch (Exception e) {
                Log.d(TAG, "class not found. class: " + className, e);
                return ret;
            }
        }
        if (cls == null || !Gate.class.isAssignableFrom(cls)) {
            return null;
        }
        ret = (Gate) cls.newInstance();
        ret.privateInitialize(this.cordova, this.webView, this.preferences);
        return ret;
    }
}
