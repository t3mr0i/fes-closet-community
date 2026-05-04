package com.sony.cdp.plugin.nativebridge;

import android.util.Log;
import android.util.SparseArray;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class MessageUtils {
    public static final int ERROR_CANCEL = 3;
    public static final int ERROR_CLASS_NOT_FOUND = 8;
    public static final int ERROR_FAIL = 2;
    public static final int ERROR_INVALID_ARG = 4;
    public static final int ERROR_INVALID_OPERATION = 7;
    public static final int ERROR_METHOD_NOT_FOUND = 9;
    public static final int ERROR_NOT_IMPLEMENT = 5;
    public static final int ERROR_NOT_SUPPORT = 6;
    public static final int SUCCESS_OK = 0;
    public static final int SUCCESS_PROGRESS = 1;
    private static final String TAG = "[com.sony.cdp.plugin.nativebridge][Native][MessageUtils] ";
    private static SparseArray<String> mErrorTbl = null;

    public static JSONObject makeMessage(int code, String message, String taskId, Object... params) throws JSONException {
        String name;
        JSONObject result;
        if (mErrorTbl == null) {
            init();
        }
        JSONObject result2 = null;
        try {
            name = mErrorTbl.get(code) != null ? mErrorTbl.get(code) : String.format("ERROR_CUSTOM:0x%x", Integer.valueOf(code));
            result = new JSONObject();
        } catch (JSONException e) {
            e = e;
        }
        try {
            result.put("code", code);
            result.put("name", TAG + name);
            if (message != null) {
                result.put("message", message);
            }
            if (taskId != null) {
                result.put("taskId", taskId);
            }
            if (params.length > 0) {
                JSONArray paramsInfo = new JSONArray();
                for (Object obj : params) {
                    paramsInfo.put(obj);
                }
                result.put("params", paramsInfo);
            }
            return result;
        } catch (JSONException e2) {
            e = e2;
            result2 = result;
            Log.e(TAG, "create result JSON object failed.", e);
            return result2;
        }
    }

    public static JSONObject makeMessage(String message, String taskId, Object... params) {
        return makeMessage(0, message, taskId, params);
    }

    public static JSONObject makeMessage(String taskId, Object... params) {
        return makeMessage(0, null, taskId, params);
    }

    public static void sendSuccessResult(CallbackContext callbackContext, String taskId) throws JSONException {
        sendSuccessResult(callbackContext, makeMessage(taskId, new Object[0]));
    }

    public static void sendSuccessResult(CallbackContext callbackContext, JSONObject result) throws JSONException {
        try {
            if (result == null) {
                result = makeMessage(null, new Object[0]);
            } else if (result.isNull("code")) {
                result.put("code", 0);
            }
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
        } catch (JSONException e) {
            Log.e(TAG, "create result JSON object failed.", e);
        }
    }

    public static void sendErrorResult(CallbackContext callbackContext, String taskId, int code, String message) throws JSONException {
        sendErrorResult(callbackContext, makeMessage(code, message, taskId, new Object[0]));
    }

    public static void sendErrorResult(CallbackContext callbackContext, JSONObject result) throws JSONException {
        try {
            if (result == null) {
                result = makeMessage(2, null, null, new Object[0]);
            } else if (result.isNull("code")) {
                result.put("code", 2);
            }
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, result));
        } catch (JSONException e) {
            Log.e(TAG, "create result JSON object failed.", e);
        }
    }

    private static synchronized void init() {
        if (mErrorTbl == null) {
            mErrorTbl = new SparseArray<>();
            mErrorTbl.put(0, "SUCCESS_OK");
            mErrorTbl.put(1, "SUCCESS_PROGRESS");
            mErrorTbl.put(2, "ERROR_FAIL");
            mErrorTbl.put(3, "ERROR_CANCEL");
            mErrorTbl.put(4, "ERROR_INVALID_ARG");
            mErrorTbl.put(5, "ERROR_NOT_IMPLEMENT");
            mErrorTbl.put(6, "ERROR_NOT_SUPPORT");
            mErrorTbl.put(7, "ERROR_INVALID_OPERATION");
            mErrorTbl.put(8, "ERROR_CLASS_NOT_FOUND");
            mErrorTbl.put(8, "ERROR_CLASS_NOT_FOUND");
            mErrorTbl.put(9, "ERROR_METHOD_NOT_FOUND");
        }
    }
}
