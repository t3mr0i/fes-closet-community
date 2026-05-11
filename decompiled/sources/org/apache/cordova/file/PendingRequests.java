package org.apache.cordova.file;

import android.util.SparseArray;
import org.apache.cordova.CallbackContext;

/* loaded from: classes.dex */
class PendingRequests {
    private int currentReqId = 0;
    private SparseArray<Request> requests = new SparseArray<>();

    PendingRequests() {
    }

    static /* synthetic */ int access$208(PendingRequests x0) {
        int i = x0.currentReqId;
        x0.currentReqId = i + 1;
        return i;
    }

    public synchronized int createRequest(String rawArgs, int action, CallbackContext callbackContext) {
        Request req;
        req = new Request(rawArgs, action, callbackContext);
        this.requests.put(req.requestCode, req);
        return req.requestCode;
    }

    public synchronized Request getAndRemove(int requestCode) {
        Request result;
        result = this.requests.get(requestCode);
        this.requests.remove(requestCode);
        return result;
    }

    public class Request {
        private int action;
        private CallbackContext callbackContext;
        private String rawArgs;
        private int requestCode;

        private Request(String rawArgs, int action, CallbackContext callbackContext) {
            this.rawArgs = rawArgs;
            this.action = action;
            this.callbackContext = callbackContext;
            this.requestCode = PendingRequests.access$208(PendingRequests.this);
        }

        public int getAction() {
            return this.action;
        }

        public String getRawArgs() {
            return this.rawArgs;
        }

        public CallbackContext getCallbackContext() {
            return this.callbackContext;
        }
    }
}
