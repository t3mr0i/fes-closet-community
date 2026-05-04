package com.sony.cdp.plugin.nativebridge;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Gate {
    private static final String TAG = "[com.sony.cdp.plugin.nativebridge][Native][Gate] ";
    protected CordovaInterface cordova;
    protected CordovaPreferences preferences;
    protected CordovaWebView webView;
    private MethodContext mCurrentContext = null;
    private Map<String, Boolean> mCancelableTask = new HashMap();

    public final void privateInitialize(CordovaInterface cordova, CordovaWebView webView, CordovaPreferences preferences) {
        this.cordova = cordova;
        this.webView = webView;
        this.preferences = preferences;
        initialize(cordova, webView);
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return execute(action, new CordovaArgs(args), callbackContext);
    }

    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        Log.w(TAG, "execute() method should be override from sub class.");
        return false;
    }

    public final JSONObject invoke(MethodContext context) {
        JSONObject jSONObjectMakeMessage = null;
        synchronized (this) {
            try {
                try {
                    try {
                        try {
                            try {
                                JSONArray args = context.methodArgs;
                                Class<?> cls = getClass();
                                int length = args.length();
                                Class<?>[] argTypes = new Class[length];
                                Object[] argValues = new Object[length];
                                for (int i = 0; i < length; i++) {
                                    Object arg = args.get(i);
                                    argTypes[i] = normalizeType(arg.getClass());
                                    argValues[i] = arg;
                                }
                                Method method = cls.getMethod(context.methodName, argTypes);
                                this.mCurrentContext = context;
                                method.invoke(this, argValues);
                                if (this.mCurrentContext.needSendResult) {
                                    MessageUtils.sendSuccessResult(context, context.taskId);
                                }
                                this.mCurrentContext = null;
                            } catch (NoSuchMethodException e) {
                                Log.d(TAG, "method not found", e);
                                jSONObjectMakeMessage = MessageUtils.makeMessage(9, "[com.sony.cdp.plugin.nativebridge][Native][Gate] method not found. method: " + context.className + "#" + context.methodName, context.taskId, new Object[0]);
                                this.mCurrentContext = null;
                            }
                        } catch (IllegalArgumentException e2) {
                            Log.e(TAG, "Invalid Arg", e2);
                            jSONObjectMakeMessage = MessageUtils.makeMessage(4, "[com.sony.cdp.plugin.nativebridge][Native][Gate] IllegalArgumentException occured.", context.taskId, new Object[0]);
                            this.mCurrentContext = null;
                        }
                    } catch (InvocationTargetException e3) {
                        Log.e(TAG, "Invocation Target Exception", e3);
                        jSONObjectMakeMessage = MessageUtils.makeMessage(7, "[com.sony.cdp.plugin.nativebridge][Native][Gate] InvocationTargetException occured.", context.taskId, new Object[0]);
                        this.mCurrentContext = null;
                    }
                } catch (IllegalAccessException e4) {
                    Log.e(TAG, "Illegal Access", e4);
                    jSONObjectMakeMessage = MessageUtils.makeMessage(4, "[com.sony.cdp.plugin.nativebridge][Native][Gate] IllegalAccessException occured.", context.taskId, new Object[0]);
                    this.mCurrentContext = null;
                } catch (JSONException e5) {
                    Log.e(TAG, "Invalid JSON object", e5);
                    jSONObjectMakeMessage = MessageUtils.makeMessage(4, "[com.sony.cdp.plugin.nativebridge][Native][Gate] JSONException occured.", context.taskId, new Object[0]);
                    this.mCurrentContext = null;
                }
            } catch (Throwable th) {
                this.mCurrentContext = null;
                throw th;
            }
        }
        return jSONObjectMakeMessage;
    }

    public final void cancel(MethodContext context) {
        setCancelState(context.taskId);
        onCancel(context.taskId);
    }

    protected void initialize(CordovaInterface cordova, CordovaWebView webView) {
    }

    protected final MethodContext getContext() {
        return getContext(false);
    }

    protected final MethodContext getContext(boolean autoSendResult) {
        MethodContext methodContext;
        synchronized (this) {
            if (this.mCurrentContext != null && Thread.currentThread().getName().equals(this.mCurrentContext.threadId)) {
                this.mCurrentContext.needSendResult = autoSendResult;
                methodContext = this.mCurrentContext;
            } else {
                Log.e(TAG, "Calling getContext() is permitted only from method entry thread.");
                methodContext = null;
            }
        }
        return methodContext;
    }

    protected final void returnParams(Object param) throws JSONException {
        if (this.mCurrentContext != null && Thread.currentThread().getName().equals(this.mCurrentContext.threadId)) {
            this.mCurrentContext.needSendResult = false;
            MessageUtils.sendSuccessResult(this.mCurrentContext, MessageUtils.makeMessage(this.mCurrentContext.taskId, param));
        } else {
            Log.e(TAG, "Calling returnMessage() is permitted only from method entry thread.");
        }
    }

    protected final void notifyParams(MethodContext context, Object... params) {
        notifyParams(true, context, params);
    }

    protected final void notifyParams(boolean keepCallback, MethodContext context, Object... params) {
        if (context == null) {
            Log.e(TAG, "Invalid context object.");
            return;
        }
        int resultCode = keepCallback ? 1 : 0;
        PluginResult result = new PluginResult(PluginResult.Status.OK, MessageUtils.makeMessage(resultCode, null, context.taskId, params));
        result.setKeepCallback(keepCallback);
        context.sendPluginResult(result);
    }

    protected final void resolveParams(MethodContext context, Object... params) throws JSONException {
        if (context == null) {
            Log.e(TAG, "Invalid context object.");
        } else {
            MessageUtils.sendSuccessResult(context, MessageUtils.makeMessage(context.taskId, params));
        }
    }

    protected final void rejectParams(MethodContext context, Object... params) throws JSONException {
        rejectParams(2, null, context, params);
    }

    protected final void rejectParams(int code, String message, MethodContext context, Object... params) throws JSONException {
        if (context == null) {
            Log.e(TAG, "Invalid context object.");
        } else {
            MessageUtils.sendErrorResult(context, MessageUtils.makeMessage(code, message, context.taskId, params));
        }
    }

    protected final void setCancelable(MethodContext context) {
        synchronized (this) {
            this.mCancelableTask.put(context.taskId, false);
        }
    }

    protected final void removeCancelable(MethodContext context) {
        synchronized (this) {
            this.mCancelableTask.remove(context.taskId);
        }
    }

    protected final boolean isCanceled(MethodContext context) {
        boolean zBooleanValue;
        synchronized (this) {
            zBooleanValue = this.mCancelableTask.get(context.taskId).booleanValue();
        }
        return zBooleanValue;
    }

    protected void onCancel(String taskId) {
    }

    private Class<?> normalizeType(Class<?> src) {
        String type = src.getName();
        if (type.equals("java.lang.Byte") || type.equals("java.lang.Short") || type.equals("java.lang.Integer") || type.equals("java.lang.Long") || type.equals("java.lang.Float") || type.equals("java.lang.Double")) {
            return Double.TYPE;
        }
        if (type.equals("java.lang.Boolean")) {
            return Boolean.TYPE;
        }
        return src;
    }

    private void setCancelState(String taskId) {
        synchronized (this) {
            if (taskId == null) {
                for (String key : this.mCancelableTask.keySet()) {
                    this.mCancelableTask.put(key, true);
                }
            } else if (this.mCancelableTask.get(taskId) != null) {
                this.mCancelableTask.put(taskId, true);
            }
        }
    }
}
