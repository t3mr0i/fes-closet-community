package com.randdusing.bluetoothle;

import android.bluetooth.BluetoothDevice;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class Operation {
    public JSONArray args;
    public CallbackContext callbackContext;
    public BluetoothDevice device;
    public String type;

    public Operation(String type, JSONArray args, CallbackContext callbackContext) {
        this.type = type;
        this.args = args;
        this.callbackContext = callbackContext;
    }
}
