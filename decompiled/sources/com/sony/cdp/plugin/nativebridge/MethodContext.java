package com.sony.cdp.plugin.nativebridge;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class MethodContext extends CallbackContext {
    public final String className;
    public final boolean compatible;
    public final JSONArray methodArgs;
    public final String methodName;
    public boolean needSendResult;
    public final String objectId;
    public final String taskId;
    public final String threadId;

    public MethodContext(String callbackId, CordovaWebView webView, JSONArray args) throws JSONException {
        super(callbackId, webView);
        this.threadId = Thread.currentThread().getName();
        this.needSendResult = true;
        JSONObject execInfo = args.getJSONObject(0);
        JSONObject feature = execInfo.getJSONObject("feature");
        this.className = feature.isNull("android") ? null : feature.getJSONObject("android").getString("packageInfo");
        this.methodName = execInfo.isNull("method") ? null : execInfo.getString("method");
        this.methodArgs = getMethodArgs(args);
        this.objectId = execInfo.getString("objectId");
        this.taskId = execInfo.isNull("taskId") ? null : execInfo.getString("taskId");
        this.compatible = execInfo.getBoolean("compatible");
    }

    private JSONArray getMethodArgs(JSONArray rawArgs) throws JSONException {
        JSONArray methodArgs = new JSONArray();
        for (int i = 1; i < rawArgs.length(); i++) {
            methodArgs.put(rawArgs.get(i));
        }
        return methodArgs;
    }
}
