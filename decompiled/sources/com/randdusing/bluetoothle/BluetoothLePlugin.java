package com.randdusing.bluetoothle;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.ParcelUuid;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Base64;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BluetoothLePlugin extends CordovaPlugin {
    private CallbackContext addServiceCallback;
    private CallbackContext advertiseCallbackContext;
    private BluetoothAdapter bluetoothAdapter;
    private HashMap<Object, HashMap<Object, Object>> connections;
    private BluetoothGattServer gattServer;
    private CallbackContext initCallbackContext;
    private CallbackContext initPeripheralCallback;
    private CallbackContext locationCallback;
    private CallbackContext permissionsCallback;
    private CallbackContext scanCallbackContext;
    private final int REQUEST_BT_ENABLE = 59627;
    private final int REQUEST_ACCESS_COARSE_LOCATION = 59628;
    private final int REQUEST_LOCATION_SOURCE_SETTINGS = 59629;
    private boolean isReceiverRegistered = false;
    private boolean isBondReceiverRegistered = false;
    private boolean isAdvertising = false;
    private HashMap<String, CallbackContext> bonds = new HashMap<>();
    private final int STATE_UNDISCOVERED = 0;
    private final int STATE_DISCOVERING = 1;
    private final int STATE_DISCOVERED = 2;
    private LinkedList<byte[]> queueQuick = new LinkedList<>();
    private LinkedList<Operation> queue = new LinkedList<>();
    private final String keyStatus = NotificationCompat.CATEGORY_STATUS;
    private final String keyError = "error";
    private final String keyMessage = "message";
    private final String keyRequest = "request";
    private final String keyStatusReceiver = "statusReceiver";
    private final String keyName = "name";
    private final String keyAddress = "address";
    private final String keyRssi = "rssi";
    private final String keyScanMode = "scanMode";
    private final String keyMatchMode = "matchMode";
    private final String keyMatchNum = "matchNum";
    private final String keyCallbackType = "callbackType";
    private final String keyAdvertisement = "advertisement";
    private final String keyUuid = "uuid";
    private final String keyService = NotificationCompat.CATEGORY_SERVICE;
    private final String keyServices = "services";
    private final String keyCharacteristic = "characteristic";
    private final String keyCharacteristics = "characteristics";
    private final String keyProperties = "properties";
    private final String keyPermissions = "permissions";
    private final String keyDescriptor = "descriptor";
    private final String keyDescriptors = "descriptors";
    private final String keyValue = FirebaseAnalytics.Param.VALUE;
    private final String keyType = "type";
    private final String keyIsInitialized = "isInitialized";
    private final String keyIsEnabled = "isEnabled";
    private final String keyIsScanning = "isScanning";
    private final String keyIsBonded = "isBonded";
    private final String keyIsConnected = "isConnected";
    private final String keyIsDiscovered = "isDiscovered";
    private final String keyPeripheral = "peripheral";
    private final String keyState = "state";
    private final String keyDiscoveredState = "discoveredState";
    private final String keyConnectionPriority = "connectionPriority";
    private final String keyMtu = "mtu";
    private final String writeTypeNoResponse = "noResponse";
    private final String statusEnabled = "enabled";
    private final String statusDisabled = "disabled";
    private final String statusScanStarted = "scanStarted";
    private final String statusScanStopped = "scanStopped";
    private final String statusScanResult = "scanResult";
    private final String statusBonded = "bonded";
    private final String statusBonding = "bonding";
    private final String statusUnbonded = "unbonded";
    private final String statusConnected = "connected";
    private final String statusDisconnected = "disconnected";
    private final String statusClosed = "closed";
    private final String statusDiscovered = "discovered";
    private final String statusRead = "read";
    private final String statusSubscribed = "subscribed";
    private final String statusSubscribedResult = "subscribedResult";
    private final String statusUnsubscribed = "unsubscribed";
    private final String statusWritten = "written";
    private final String statusReadDescriptor = "readDescriptor";
    private final String statusWrittenDescriptor = "writtenDescriptor";
    private final String statusRssi = "rssi";
    private final String statusConnectionPriorityRequested = "connectionPriorityRequested";
    private final String statusMtu = "mtu";
    private final String propertyBroadcast = "broadcast";
    private final String propertyRead = "read";
    private final String propertyWriteWithoutResponse = "writeWithoutResponse";
    private final String propertyWrite = "write";
    private final String propertyNotify = "notify";
    private final String propertyIndicate = "indicate";
    private final String propertyAuthenticatedSignedWrites = "authenticatedSignedWrites";
    private final String propertyExtendedProperties = "extendedProperties";
    private final String propertyNotifyEncryptionRequired = "notifyEncryptionRequired";
    private final String propertyIndicateEncryptionRequired = "indicateEncryptionRequired";
    private final String propertyConnectionPriorityHigh = "high";
    private final String propertyConnectionPriorityLow = "low";
    private final String propertyConnectionPriorityBalanced = "balanced";
    private final String permissionRead = "read";
    private final String permissionReadEncrypted = "readEncrypted";
    private final String permissionReadEncryptedMITM = "readEncryptedMITM";
    private final String permissionWrite = "write";
    private final String permissionWriteEncrypted = "writeEncrypted";
    private final String permissionWriteEncryptedMITM = "writeEncryptedMITM";
    private final String permissionWriteSigned = "writeSigned";
    private final String permissionWriteSignedMITM = "writeSignedMITM";
    private final String errorInitialize = "initialize";
    private final String errorEnable = "enable";
    private final String errorDisable = "disable";
    private final String errorArguments = "arguments";
    private final String errorStartScan = "startScan";
    private final String errorStopScan = "stopScan";
    private final String errorBond = "bond";
    private final String errorUnbond = "unbond";
    private final String errorConnect = "connect";
    private final String errorReconnect = "reconnect";
    private final String errorDiscover = "discover";
    private final String errorServices = "services";
    private final String errorCharacteristics = "characteristics";
    private final String errorDescriptors = "descriptors";
    private final String errorRead = "read";
    private final String errorSubscription = "subscription";
    private final String errorWrite = "write";
    private final String errorReadDescriptor = "readDescriptor";
    private final String errorWriteDescriptor = "writeDescriptor";
    private final String errorRssi = "rssi";
    private final String errorNeverConnected = "neverConnected";
    private final String errorIsNotDisconnected = "isNotDisconnected";
    private final String errorIsNotConnected = "isNotConnected";
    private final String errorIsDisconnected = "isDisconnected";
    private final String errorService = NotificationCompat.CATEGORY_SERVICE;
    private final String errorCharacteristic = "characteristic";
    private final String errorDescriptor = "descriptor";
    private final String errorRequestConnectionPriority = "requestConnectPriority";
    private final String errorMtu = "mtu";
    private final String logNotEnabled = "Bluetooth not enabled";
    private final String logNotDisabled = "Bluetooth not disabled";
    private final String logNotInit = "Bluetooth not initialized";
    private final String logOperationUnsupported = "Operation unsupported";
    private final String logAlreadyScanning = "Scanning already in progress";
    private final String logScanStartFail = "Scan failed to start";
    private final String logNotScanning = "Not scanning";
    private final String logBonded = "Device already bonded";
    private final String logBonding = "Device already bonding";
    private final String logUnbonded = "Device already unbonded";
    private final String logBondFail = "Device failed to bond on return";
    private final String logUnbondFail = "Device failed to unbond on return";
    private final String logPreviouslyConnected = "Device previously connected, reconnect or close for new device";
    private final String logConnectFail = "Connection failed";
    private final String logNeverConnected = "Never connected to device";
    private final String logIsNotConnected = "Device isn't connected";
    private final String logIsNotDisconnected = "Device isn't disconnected";
    private final String logIsDisconnected = "Device is disconnected";
    private final String logNoAddress = "No device address";
    private final String logNoDevice = "Device not found";
    private final String logReconnectFail = "Reconnection to device failed";
    private final String logAlreadyDiscovering = "Already discovering device";
    private final String logDiscoveryFail = "Unable to discover device";
    private final String logNoArgObj = "Argument object not found";
    private final String logNoService = "Service not found";
    private final String logNoCharacteristic = "Characteristic not found";
    private final String logNoDescriptor = "Descriptor not found";
    private final String logReadFail = "Unable to read";
    private final String logReadFailReturn = "Unable to read on return";
    private final String logSubscribeFail = "Unable to subscribe";
    private final String logSubscribeAlready = "Already subscribed";
    private final String logUnsubscribeFail = "Unable to unsubscribe";
    private final String logUnsubscribeAlready = "Already unsubscribed";
    private final String logWriteFail = "Unable to write";
    private final String logWriteFailReturn = "Unable to write on return";
    private final String logWriteValueNotFound = "Write value not found";
    private final String logWriteValueNotSet = "Write value not set";
    private final String logReadDescriptorFail = "Unable to read descriptor";
    private final String logReadDescriptorFailReturn = "Unable to read descriptor on return";
    private final String logWriteDescriptorNotAllowed = "Unable to write client configuration descriptor";
    private final String logWriteDescriptorFail = "Unable to write descriptor";
    private final String logWriteDescriptorValueNotFound = "Write descriptor value not found";
    private final String logWriteDescriptorValueNotSet = "Write descriptor value not set";
    private final String logWriteDescriptorFailReturn = "Descriptor not written on return";
    private final String logRssiFail = "Unable to read RSSI";
    private final String logRssiFailReturn = "Unable to read RSSI on return";
    private final String logRequestConnectionPriorityNull = "Request connection priority not set";
    private final String logRequestConnectionPriorityInvalid = "Request connection priority is invalid";
    private final String logRequestConnectionPriorityFailed = "Request connection priority failed";
    private final String logMtuFail = "Unable to set MTU";
    private final String logMtuFailReturn = "Unable to set MTU on return";
    private final String logRequiresAPI21 = "Requires API level 21";
    private final String operationConnect = "connect";
    private final String operationDiscover = "discover";
    private final String operationRssi = "rssi";
    private final String operationRead = "read";
    private final String operationSubscribe = "subscribe";
    private final String operationUnsubscribe = "unsubscribe";
    private final String operationWrite = "write";
    private final String operationMtu = "mtu";
    private final String baseUuidStart = "0000";
    private final String baseUuidEnd = "-0000-1000-8000-00805f9b34fb";
    private final UUID clientConfigurationDescriptorUuid = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.randdusing.bluetoothle.BluetoothLePlugin.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws JSONException {
            if (BluetoothLePlugin.this.initCallbackContext != null && intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                JSONObject returnObj = new JSONObject();
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", ExploreByTouchHelper.INVALID_ID)) {
                    case 10:
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "disabled");
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Bluetooth not enabled");
                        BluetoothLePlugin.this.connections = new HashMap();
                        BluetoothLePlugin.this.scanCallbackContext = null;
                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                        pluginResult.setKeepCallback(true);
                        BluetoothLePlugin.this.initCallbackContext.sendPluginResult(pluginResult);
                        break;
                    case 12:
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "enabled");
                        PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK, returnObj);
                        pluginResult2.setKeepCallback(true);
                        BluetoothLePlugin.this.initCallbackContext.sendPluginResult(pluginResult2);
                        break;
                }
            }
        }
    };
    private final BroadcastReceiver mBondReceiver = new BroadcastReceiver() { // from class: com.randdusing.bluetoothle.BluetoothLePlugin.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws JSONException {
            if (intent.getAction().equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int bondState = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
                intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", -1);
                String address = device.getAddress();
                CallbackContext callback = (CallbackContext) BluetoothLePlugin.this.bonds.get(address);
                if (callback != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    boolean keepCallback = false;
                    switch (bondState) {
                        case 10:
                            BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "unbonded");
                            break;
                        case 11:
                            BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "bonding");
                            keepCallback = true;
                            break;
                        case 12:
                            BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "bonded");
                            break;
                    }
                    if (!keepCallback) {
                        BluetoothLePlugin.this.bonds.remove(address);
                    }
                    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                    pluginResult.setKeepCallback(keepCallback);
                    callback.sendPluginResult(pluginResult);
                }
            }
        }
    };
    private BluetoothAdapter.LeScanCallback scanCallbackKitKat = new BluetoothAdapter.LeScanCallback() { // from class: com.randdusing.bluetoothle.BluetoothLePlugin.3
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            synchronized (BluetoothLePlugin.this) {
                if (BluetoothLePlugin.this.scanCallbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    BluetoothLePlugin.this.addProperty(returnObj, "rssi", Integer.valueOf(rssi));
                    BluetoothLePlugin.this.addPropertyBytes(returnObj, "advertisement", scanRecord);
                    BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "scanResult");
                    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                    pluginResult.setKeepCallback(true);
                    BluetoothLePlugin.this.scanCallbackContext.sendPluginResult(pluginResult);
                }
            }
        }
    };
    private ScanCallback scanCallback = null;
    private AdvertiseCallback advertiseCallback = null;
    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() { // from class: com.randdusing.bluetoothle.BluetoothLePlugin.6
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) throws JSONException {
            Operation operation;
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            if (newState == 0 && (operation = (Operation) BluetoothLePlugin.this.queue.peek()) != null && operation.device != null && operation.device.getAddress().equals(address)) {
                BluetoothLePlugin.this.queueRemove();
            }
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                CallbackContext callbackContext = (CallbackContext) connection.get("connect");
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj, device);
                int oldState = Integer.valueOf(connection.get("state").toString()).intValue();
                if (status != 0 && oldState == 1) {
                    HashMap<Object, Object> connection2 = new HashMap<>();
                    connection2.put("peripheral", gatt);
                    connection2.put("state", 0);
                    BluetoothLePlugin.this.connections.put(device.getAddress(), connection2);
                    if (callbackContext != null) {
                        BluetoothLePlugin.this.addProperty(returnObj, "error", "connect");
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Connection failed");
                        callbackContext.error(returnObj);
                        return;
                    }
                    return;
                }
                connection.put("state", Integer.valueOf(newState));
                if (newState == 2) {
                    if (callbackContext != null) {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "connected");
                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                        pluginResult.setKeepCallback(true);
                        callbackContext.sendPluginResult(pluginResult);
                        return;
                    }
                    return;
                }
                if (newState == 0) {
                    CallbackContext[] callbacks = BluetoothLePlugin.this.GetCallbacks(connection);
                    BluetoothLePlugin.this.addProperty(returnObj, "error", "isDisconnected");
                    BluetoothLePlugin.this.addProperty(returnObj, "message", "Device is disconnected");
                    for (CallbackContext callback : callbacks) {
                        callback.error(returnObj);
                    }
                    returnObj.remove("error");
                    returnObj.remove("message");
                    Object discoveredState = connection.get("discoveredState");
                    HashMap<Object, Object> connection3 = new HashMap<>();
                    connection3.put("peripheral", gatt);
                    connection3.put("state", 0);
                    connection3.put("discoveredState", discoveredState);
                    BluetoothLePlugin.this.connections.put(device.getAddress(), connection3);
                    if (callbackContext != null) {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "disconnected");
                        callbackContext.success(returnObj);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt gatt, int status) throws JSONException {
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                int discoveredState = status == 0 ? 2 : 0;
                connection.put("discoveredState", Integer.valueOf(discoveredState));
                CallbackContext callbackContext = (CallbackContext) connection.get("discover");
                connection.remove("discover");
                if (callbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    if (status != 0) {
                        BluetoothLePlugin.this.addProperty(returnObj, "error", "discover");
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to discover device");
                        callbackContext.error(returnObj);
                        return;
                    }
                    callbackContext.success(BluetoothLePlugin.this.getDiscovery(gatt));
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) throws JSONException {
            BluetoothLePlugin.this.queueRemove();
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                UUID characteristicUuid = characteristic.getUuid();
                CallbackContext callbackContext = BluetoothLePlugin.this.GetCallback(characteristicUuid, connection, "read");
                BluetoothLePlugin.this.RemoveCallback(characteristicUuid, connection, "read");
                if (callbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addCharacteristic(returnObj, characteristic);
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    if (status == 0) {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "read");
                        BluetoothLePlugin.this.addPropertyBytes(returnObj, FirebaseAnalytics.Param.VALUE, characteristic.getValue());
                        callbackContext.success(returnObj);
                    } else {
                        BluetoothLePlugin.this.addProperty(returnObj, "error", "read");
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to read on return");
                        callbackContext.error(returnObj);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) throws JSONException {
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                UUID characteristicUuid = characteristic.getUuid();
                CallbackContext callbackContext = BluetoothLePlugin.this.GetCallback(characteristicUuid, connection, "subscribe");
                if (callbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    BluetoothLePlugin.this.addCharacteristic(returnObj, characteristic);
                    BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "subscribedResult");
                    BluetoothLePlugin.this.addPropertyBytes(returnObj, FirebaseAnalytics.Param.VALUE, characteristic.getValue());
                    PluginResult result = new PluginResult(PluginResult.Status.OK, returnObj);
                    result.setKeepCallback(true);
                    callbackContext.sendPluginResult(result);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) throws JSONException {
            BluetoothLePlugin.this.queueRemove();
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                UUID characteristicUuid = characteristic.getUuid();
                CallbackContext callbackContext = BluetoothLePlugin.this.GetCallback(characteristicUuid, connection, "write");
                if (BluetoothLePlugin.this.queueQuick.size() <= 0) {
                    BluetoothLePlugin.this.RemoveCallback(characteristicUuid, connection, "write");
                    if (callbackContext != null) {
                        JSONObject returnObj = new JSONObject();
                        BluetoothLePlugin.this.addDevice(returnObj, device);
                        BluetoothLePlugin.this.addCharacteristic(returnObj, characteristic);
                        if (status == 0) {
                            BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "written");
                            BluetoothLePlugin.this.addPropertyBytes(returnObj, FirebaseAnalytics.Param.VALUE, characteristic.getValue());
                            callbackContext.success(returnObj);
                            return;
                        } else {
                            BluetoothLePlugin.this.addProperty(returnObj, "error", "write");
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to write on return");
                            callbackContext.error(returnObj);
                            return;
                        }
                    }
                    return;
                }
                if (status == 0) {
                    BluetoothLePlugin.this.writeQ(connection, characteristic, gatt);
                    return;
                }
                BluetoothLePlugin.this.queueQuick.clear();
                if (callbackContext != null) {
                    JSONObject returnObj2 = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj2, device);
                    BluetoothLePlugin.this.addCharacteristic(returnObj2, characteristic);
                    BluetoothLePlugin.this.addProperty(returnObj2, "error", "write");
                    BluetoothLePlugin.this.addProperty(returnObj2, "message", "Unable to write on return");
                    callbackContext.error(returnObj2);
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) throws JSONException {
            BluetoothLePlugin.this.queueRemove();
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                BluetoothGattCharacteristic characteristic = descriptor.getCharacteristic();
                UUID characteristicUuid = characteristic.getUuid();
                UUID descriptorUuid = descriptor.getUuid();
                CallbackContext callbackContext = BluetoothLePlugin.this.GetDescriptorCallback(descriptorUuid, characteristicUuid, connection, "read");
                BluetoothLePlugin.this.RemoveDescriptorCallback(descriptorUuid, characteristicUuid, connection, "read");
                if (callbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    BluetoothLePlugin.this.addDescriptor(returnObj, descriptor);
                    if (status == 0) {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "readDescriptor");
                        BluetoothLePlugin.this.addPropertyBytes(returnObj, FirebaseAnalytics.Param.VALUE, descriptor.getValue());
                        callbackContext.success(returnObj);
                    } else {
                        BluetoothLePlugin.this.addProperty(returnObj, "error", "readDescriptor");
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to read descriptor on return");
                        callbackContext.error(returnObj);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) throws JSONException {
            BluetoothLePlugin.this.queueRemove();
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                BluetoothGattCharacteristic characteristic = descriptor.getCharacteristic();
                UUID characteristicUuid = characteristic.getUuid();
                UUID descriptorUuid = descriptor.getUuid();
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj, device);
                BluetoothLePlugin.this.addDescriptor(returnObj, descriptor);
                if (!descriptorUuid.equals(BluetoothLePlugin.this.clientConfigurationDescriptorUuid)) {
                    CallbackContext callbackContext = BluetoothLePlugin.this.GetDescriptorCallback(descriptorUuid, characteristicUuid, connection, "write");
                    BluetoothLePlugin.this.RemoveDescriptorCallback(descriptorUuid, characteristicUuid, connection, "write");
                    if (callbackContext != null) {
                        if (status == 0) {
                            BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "writtenDescriptor");
                            BluetoothLePlugin.this.addPropertyBytes(returnObj, FirebaseAnalytics.Param.VALUE, descriptor.getValue());
                            callbackContext.success(returnObj);
                            return;
                        } else {
                            BluetoothLePlugin.this.addProperty(returnObj, "error", "writeDescriptor");
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Descriptor not written on return");
                            callbackContext.error(returnObj);
                            return;
                        }
                    }
                    return;
                }
                if (descriptor.getValue() == BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE) {
                    boolean result = gatt.setCharacteristicNotification(characteristic, false);
                    CallbackContext callbackContext2 = BluetoothLePlugin.this.GetCallback(characteristicUuid, connection, "unsubscribe");
                    if (callbackContext2 != null) {
                        if (status != 0) {
                            BluetoothLePlugin.this.addProperty(returnObj, "error", "subscription");
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to unsubscribe");
                            callbackContext2.error(returnObj);
                            return;
                        } else if (!result) {
                            BluetoothLePlugin.this.addProperty(returnObj, "error", "subscription");
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to unsubscribe");
                            callbackContext2.error(returnObj);
                            return;
                        } else {
                            BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "unsubscribed");
                            callbackContext2.success(returnObj);
                            return;
                        }
                    }
                    return;
                }
                boolean result2 = gatt.setCharacteristicNotification(characteristic, true);
                CallbackContext callbackContext3 = BluetoothLePlugin.this.GetCallback(characteristicUuid, connection, "subscribe");
                if (callbackContext3 != null) {
                    if (!result2) {
                        BluetoothLePlugin.this.addProperty(returnObj, "error", "subscription");
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to subscribe");
                        callbackContext3.error(returnObj);
                    } else {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "subscribed");
                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                        pluginResult.setKeepCallback(true);
                        callbackContext3.sendPluginResult(pluginResult);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) throws JSONException {
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                CallbackContext callbackContext = (CallbackContext) connection.get("rssi");
                connection.remove("rssi");
                if (callbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    if (status == 0) {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "rssi");
                        BluetoothLePlugin.this.addProperty(returnObj, "rssi", Integer.valueOf(rssi));
                        callbackContext.success(returnObj);
                    } else {
                        BluetoothLePlugin.this.addProperty(returnObj, "error", "rssi");
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to read RSSI on return");
                        callbackContext.error(returnObj);
                    }
                }
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) throws JSONException {
            BluetoothDevice device = gatt.getDevice();
            String address = device.getAddress();
            HashMap<Object, Object> connection = (HashMap) BluetoothLePlugin.this.connections.get(address);
            if (connection != null) {
                CallbackContext callbackContext = (CallbackContext) connection.get("mtu");
                connection.remove("mtu");
                if (callbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    if (status == 0) {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "mtu");
                        BluetoothLePlugin.this.addProperty(returnObj, "mtu", Integer.valueOf(mtu));
                        callbackContext.success(returnObj);
                    } else {
                        BluetoothLePlugin.this.addProperty(returnObj, "error", "mtu");
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to set MTU on return");
                        callbackContext.error(returnObj);
                    }
                }
            }
        }
    };
    private BluetoothGattServerCallback bluetoothGattServerCallback = new BluetoothGattServerCallback() { // from class: com.randdusing.bluetoothle.BluetoothLePlugin.7
        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onCharacteristicReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattCharacteristic characteristic) throws JSONException {
            if (BluetoothLePlugin.this.initPeripheralCallback != null) {
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj, device);
                BluetoothLePlugin.this.addCharacteristic(returnObj, characteristic);
                BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "readRequested");
                BluetoothLePlugin.this.addProperty(returnObj, "requestId", Integer.valueOf(requestId));
                BluetoothLePlugin.this.addProperty(returnObj, "offset", Integer.valueOf(offset));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                pluginResult.setKeepCallback(true);
                BluetoothLePlugin.this.initPeripheralCallback.sendPluginResult(pluginResult);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) throws JSONException {
            if (BluetoothLePlugin.this.initPeripheralCallback != null) {
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj, device);
                BluetoothLePlugin.this.addCharacteristic(returnObj, characteristic);
                BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "writeRequested");
                BluetoothLePlugin.this.addProperty(returnObj, "requestId", Integer.valueOf(requestId));
                BluetoothLePlugin.this.addProperty(returnObj, "offset", Integer.valueOf(offset));
                BluetoothLePlugin.this.addPropertyBytes(returnObj, FirebaseAnalytics.Param.VALUE, value);
                BluetoothLePlugin.this.addProperty(returnObj, "preparedWrite", Boolean.valueOf(preparedWrite));
                BluetoothLePlugin.this.addProperty(returnObj, "responseNeeded", Boolean.valueOf(responseNeeded));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                pluginResult.setKeepCallback(true);
                BluetoothLePlugin.this.initPeripheralCallback.sendPluginResult(pluginResult);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onConnectionStateChange(BluetoothDevice device, int status, int newState) throws JSONException {
            if (BluetoothLePlugin.this.initPeripheralCallback != null) {
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj, device);
                if (newState == 2) {
                    BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "connected");
                } else {
                    BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "disconnected");
                }
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                pluginResult.setKeepCallback(true);
                BluetoothLePlugin.this.initPeripheralCallback.sendPluginResult(pluginResult);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onDescriptorReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattDescriptor descriptor) throws JSONException {
            if (BluetoothLePlugin.this.initPeripheralCallback != null) {
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj, device);
                BluetoothLePlugin.this.addDescriptor(returnObj, descriptor);
                BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "readRequested");
                BluetoothLePlugin.this.addProperty(returnObj, "requestId", Integer.valueOf(requestId));
                BluetoothLePlugin.this.addProperty(returnObj, "offset", Integer.valueOf(offset));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                pluginResult.setKeepCallback(true);
                BluetoothLePlugin.this.initPeripheralCallback.sendPluginResult(pluginResult);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onDescriptorWriteRequest(BluetoothDevice device, int requestId, BluetoothGattDescriptor descriptor, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) throws JSONException {
            if (BluetoothLePlugin.this.initPeripheralCallback != null) {
                if (descriptor.getUuid().equals(BluetoothLePlugin.this.clientConfigurationDescriptorUuid)) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addDevice(returnObj, device);
                    BluetoothLePlugin.this.addCharacteristic(returnObj, descriptor.getCharacteristic());
                    if (Arrays.equals(value, BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE)) {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "unsubscribed");
                    } else {
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "subscribed");
                    }
                    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                    pluginResult.setKeepCallback(true);
                    BluetoothLePlugin.this.initPeripheralCallback.sendPluginResult(pluginResult);
                    BluetoothLePlugin.this.gattServer.sendResponse(device, requestId, 0, offset, value);
                    return;
                }
                JSONObject returnObj2 = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj2, device);
                BluetoothLePlugin.this.addDescriptor(returnObj2, descriptor);
                BluetoothLePlugin.this.addProperty(returnObj2, NotificationCompat.CATEGORY_STATUS, "writeRequested");
                BluetoothLePlugin.this.addProperty(returnObj2, "requestId", Integer.valueOf(requestId));
                BluetoothLePlugin.this.addProperty(returnObj2, "offset", Integer.valueOf(offset));
                BluetoothLePlugin.this.addPropertyBytes(returnObj2, FirebaseAnalytics.Param.VALUE, value);
                BluetoothLePlugin.this.addProperty(returnObj2, "preparedWrite", Boolean.valueOf(preparedWrite));
                BluetoothLePlugin.this.addProperty(returnObj2, "responseNeeded", Boolean.valueOf(responseNeeded));
                PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK, returnObj2);
                pluginResult2.setKeepCallback(true);
                BluetoothLePlugin.this.initPeripheralCallback.sendPluginResult(pluginResult2);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onExecuteWrite(BluetoothDevice device, int requestId, boolean execute) {
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onMtuChanged(BluetoothDevice device, int mtu) throws JSONException {
            if (BluetoothLePlugin.this.initPeripheralCallback != null) {
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj, device);
                BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "mtuChanged");
                BluetoothLePlugin.this.addProperty(returnObj, "mtu", Integer.valueOf(mtu));
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                pluginResult.setKeepCallback(true);
                BluetoothLePlugin.this.initPeripheralCallback.sendPluginResult(pluginResult);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onNotificationSent(BluetoothDevice device, int status) throws JSONException {
            if (BluetoothLePlugin.this.initPeripheralCallback != null) {
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addDevice(returnObj, device);
                if (status == 0) {
                    BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "notificationSent");
                } else {
                    BluetoothLePlugin.this.addProperty(returnObj, "error", "notificationSent");
                    BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to send notification");
                }
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                pluginResult.setKeepCallback(true);
                BluetoothLePlugin.this.initPeripheralCallback.sendPluginResult(pluginResult);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onServiceAdded(int status, BluetoothGattService service) throws JSONException {
            if (BluetoothLePlugin.this.addServiceCallback != null) {
                JSONObject returnObj = new JSONObject();
                BluetoothLePlugin.this.addService(returnObj, service);
                if (status == 0) {
                    BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "serviceAdded");
                    BluetoothLePlugin.this.addServiceCallback.success(returnObj);
                } else {
                    BluetoothLePlugin.this.addProperty(returnObj, "error", NotificationCompat.CATEGORY_SERVICE);
                    BluetoothLePlugin.this.addProperty(returnObj, "message", "Unable to add service");
                    BluetoothLePlugin.this.addServiceCallback.error(returnObj);
                }
            }
        }
    };

    public BluetoothLePlugin() {
        if (Build.VERSION.SDK_INT >= 21) {
            createScanCallback();
            createAdvertiseCallback();
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException, NoSuchMethodException, SecurityException {
        if ("initialize".equals(action)) {
            initializeAction(args, callbackContext);
        } else if ("enable".equals(action)) {
            enableAction(callbackContext);
        } else if ("disable".equals(action)) {
            disableAction(callbackContext);
        } else if ("startScan".equals(action)) {
            startScanAction(args, callbackContext);
        } else if ("stopScan".equals(action)) {
            stopScanAction(callbackContext);
        } else if ("retrieveConnected".equals(action)) {
            retrieveConnectedAction(args, callbackContext);
        } else if ("bond".equals(action)) {
            bondAction(args, callbackContext);
        } else if ("unbond".equals(action)) {
            unbondAction(args, callbackContext);
        } else if ("connect".equals(action)) {
            connectAction(args, callbackContext);
        } else if ("reconnect".equals(action)) {
            reconnectAction(args, callbackContext);
        } else if ("disconnect".equals(action)) {
            disconnectAction(args, callbackContext);
        } else if ("services".equals(action)) {
            JSONObject returnObj = new JSONObject();
            addProperty(returnObj, "error", "services");
            addProperty(returnObj, "message", "Operation unsupported");
            callbackContext.error(returnObj);
        } else if ("characteristics".equals(action)) {
            JSONObject returnObj2 = new JSONObject();
            addProperty(returnObj2, "error", "characteristics");
            addProperty(returnObj2, "message", "Operation unsupported");
            callbackContext.error(returnObj2);
        } else if ("descriptors".equals(action)) {
            JSONObject returnObj3 = new JSONObject();
            addProperty(returnObj3, "error", "descriptors");
            addProperty(returnObj3, "message", "Operation unsupported");
            callbackContext.error(returnObj3);
        } else if ("close".equals(action)) {
            closeAction(args, callbackContext);
        } else if ("discover".equals(action)) {
            discoverAction(args, callbackContext);
        } else if ("read".equals(action)) {
            Operation operation = new Operation("read", args, callbackContext);
            this.queue.add(operation);
            queueStart();
        } else if ("subscribe".equals(action)) {
            Operation operation2 = new Operation("subscribe", args, callbackContext);
            this.queue.add(operation2);
            queueStart();
        } else if ("unsubscribe".equals(action)) {
            Operation operation3 = new Operation("unsubscribe", args, callbackContext);
            this.queue.add(operation3);
            queueStart();
        } else if ("write".equals(action)) {
            Operation operation4 = new Operation("write", args, callbackContext);
            this.queue.add(operation4);
            queueStart();
        } else if ("writeQ".equals(action)) {
            writeQAction(args, callbackContext);
        } else if ("readDescriptor".equals(action)) {
            Operation operation5 = new Operation("readDescriptor", args, callbackContext);
            this.queue.add(operation5);
            queueStart();
        } else if ("writeDescriptor".equals(action)) {
            Operation operation6 = new Operation("writeDescriptor", args, callbackContext);
            this.queue.add(operation6);
            queueStart();
        } else if ("rssi".equals(action)) {
            rssiAction(args, callbackContext);
        } else if ("isInitialized".equals(action)) {
            isInitializedAction(callbackContext);
        } else if ("isEnabled".equals(action)) {
            isEnabledAction(callbackContext);
        } else if ("isScanning".equals(action)) {
            isScanningAction(callbackContext);
        } else if ("wasConnected".equals(action)) {
            wasConnectedAction(args, callbackContext);
        } else if ("isConnected".equals(action)) {
            isConnectedAction(args, callbackContext);
        } else if ("isDiscovered".equals(action)) {
            isDiscoveredAction(args, callbackContext);
        } else if ("isBonded".equals(action)) {
            isBondedAction(args, callbackContext);
        } else if ("requestConnectionPriority".equals(action)) {
            requestConnectionPriorityAction(args, callbackContext);
        } else if ("mtu".equals(action)) {
            mtuAction(args, callbackContext);
        } else if ("hasPermission".equals(action)) {
            hasPermissionAction(callbackContext);
        } else if ("requestPermission".equals(action)) {
            requestPermissionAction(callbackContext);
        } else if ("isLocationEnabled".equals(action)) {
            isLocationEnabledAction(callbackContext);
        } else if ("requestLocation".equals(action)) {
            requestLocationAction(callbackContext);
        } else if ("initializePeripheral".equals(action)) {
            initializePeripheralAction(args, callbackContext);
        } else if ("addService".equals(action)) {
            addServiceAction(args, callbackContext);
        } else if ("removeService".equals(action)) {
            removeServiceAction(args, callbackContext);
        } else if ("removeAllServices".equals(action)) {
            removeAllServicesAction(args, callbackContext);
        } else if ("startAdvertising".equals(action)) {
            startAdvertisingAction(args, callbackContext);
        } else if ("stopAdvertising".equals(action)) {
            stopAdvertisingAction(args, callbackContext);
        } else if ("isAdvertising".equals(action)) {
            isAdvertisingAction(callbackContext);
        } else if ("respond".equals(action)) {
            respondAction(args, callbackContext);
        } else if ("notify".equals(action)) {
            notifyAction(args, callbackContext);
        } else {
            return false;
        }
        return true;
    }

    private void initializePeripheralAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (Build.VERSION.SDK_INT < 21) {
            JSONObject returnObj = new JSONObject();
            addProperty(returnObj, "error", "initializePeripheral");
            addProperty(returnObj, "message", "Operation unsupported");
            callbackContext.error(returnObj);
            return;
        }
        this.initPeripheralCallback = callbackContext;
        if (this.gattServer == null) {
            Activity activity = this.cordova.getActivity();
            BluetoothManager bluetoothManager = (BluetoothManager) activity.getSystemService("bluetooth");
            this.gattServer = bluetoothManager.openGattServer(activity.getApplicationContext(), this.bluetoothGattServerCallback);
        }
        JSONObject returnObj2 = new JSONObject();
        addProperty(returnObj2, NotificationCompat.CATEGORY_STATUS, "enabled");
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj2);
        pluginResult.setKeepCallback(true);
        this.initPeripheralCallback.sendPluginResult(pluginResult);
    }

    private void addServiceAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject obj = getArgsObject(args);
        if (!isNotArgsObject(obj, callbackContext)) {
            this.addServiceCallback = callbackContext;
            UUID uuid = getUUID(obj.optString(NotificationCompat.CATEGORY_SERVICE, null));
            BluetoothGattService service = new BluetoothGattService(uuid, 0);
            JSONArray characteristicsIn = obj.optJSONArray("characteristics");
            int i = 0;
            while (i < characteristicsIn.length()) {
                try {
                    JSONObject characteristicIn = characteristicsIn.getJSONObject(i);
                    UUID characteristicUuid = getUUID(characteristicIn.optString("uuid", null));
                    boolean includeClientConfiguration = false;
                    JSONObject propertiesIn = characteristicIn.optJSONObject("properties");
                    int properties = 0;
                    if (propertiesIn != null) {
                        if (propertiesIn.optString("broadcast", null) != null) {
                            properties = 0 | 1;
                        }
                        if (propertiesIn.optString("extendedProps", null) != null) {
                            properties |= 128;
                        }
                        if (propertiesIn.optString("indicate", null) != null) {
                            properties |= 32;
                            includeClientConfiguration = true;
                        }
                        if (propertiesIn.optString("notify", null) != null) {
                            properties |= 16;
                            includeClientConfiguration = true;
                        }
                        if (propertiesIn.optString("read", null) != null) {
                            properties |= 2;
                        }
                        if (propertiesIn.optString("signedWrite", null) != null) {
                            properties |= 64;
                        }
                        if (propertiesIn.optString("write", null) != null) {
                            properties |= 8;
                        }
                        if (propertiesIn.optString("writeNoResponse", null) != null) {
                            properties |= 4;
                        }
                        if (propertiesIn.optString("notifyEncryptionRequired", null) != null) {
                            properties |= 256;
                        }
                        if (propertiesIn.optString("indicateEncryptionRequired", null) != null) {
                            properties |= 512;
                        }
                    }
                    JSONObject permissionsIn = characteristicIn.optJSONObject("permissions");
                    int permissions = 0;
                    if (permissionsIn != null) {
                        if (permissionsIn.optString("read", null) != null) {
                            permissions = 0 | 1;
                        }
                        if (permissionsIn.optString("readEncrypted", null) != null) {
                            permissions |= 2;
                        }
                        if (permissionsIn.optString("readEncryptedMITM", null) != null) {
                            permissions |= 4;
                        }
                        if (permissionsIn.optString("write", null) != null) {
                            permissions |= 16;
                        }
                        if (permissionsIn.optString("writeEncrypted", null) != null) {
                            permissions |= 32;
                        }
                        if (permissionsIn.optString("writeEncryptedMITM", null) != null) {
                            permissions |= 64;
                        }
                        if (permissionsIn.optString("writeSigned", null) != null) {
                            permissions |= 128;
                        }
                        if (permissionsIn.optString("writeSignedMITM", null) != null) {
                            permissions |= 256;
                        }
                    }
                    BluetoothGattCharacteristic characteristic = new BluetoothGattCharacteristic(characteristicUuid, properties, permissions);
                    if (includeClientConfiguration) {
                        BluetoothGattDescriptor descriptor = new BluetoothGattDescriptor(this.clientConfigurationDescriptorUuid, 17);
                        characteristic.addDescriptor(descriptor);
                    }
                    JSONArray descriptorsIn = obj.optJSONArray("descriptors");
                    if (descriptorsIn != null) {
                        while (0 < descriptorsIn.length()) {
                            try {
                                JSONObject descriptorIn = descriptorsIn.getJSONObject(i);
                                UUID descriptorUuid = getUUID(descriptorIn.optString("uuid", null));
                                JSONObject permissionsIn2 = descriptorIn.optJSONObject("permissions");
                                int permissions2 = 0;
                                if (permissionsIn2 != null) {
                                    if (permissionsIn2.optString("read", null) != null) {
                                        permissions2 = 0 | 1;
                                    }
                                    if (permissionsIn2.optString("readEncrypted", null) != null) {
                                        permissions2 |= 2;
                                    }
                                    if (permissionsIn2.optString("readEncryptedMITM", null) != null) {
                                        permissions2 |= 4;
                                    }
                                    if (permissionsIn2.optString("write", null) != null) {
                                        permissions2 |= 16;
                                    }
                                    if (permissionsIn2.optString("writeEncrypted", null) != null) {
                                        permissions2 |= 32;
                                    }
                                    if (permissionsIn2.optString("writeEncryptedMITM", null) != null) {
                                        permissions2 |= 64;
                                    }
                                    if (permissionsIn2.optString("writeSigned", null) != null) {
                                        permissions2 |= 128;
                                    }
                                    if (permissionsIn2.optString("writeSignedMITM", null) != null) {
                                        permissions2 |= 256;
                                    }
                                }
                                BluetoothGattDescriptor descriptor2 = new BluetoothGattDescriptor(descriptorUuid, permissions2);
                                characteristic.addDescriptor(descriptor2);
                            } catch (JSONException e) {
                            }
                            i++;
                        }
                    }
                    service.addCharacteristic(characteristic);
                } catch (JSONException e2) {
                }
                i++;
            }
            boolean result = this.gattServer.addService(service);
            if (result) {
                JSONObject returnObj = new JSONObject();
                addProperty(returnObj, NotificationCompat.CATEGORY_SERVICE, uuid.toString());
                addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "serviceAdded");
                callbackContext.success(returnObj);
                return;
            }
            JSONObject returnObj2 = new JSONObject();
            addProperty(returnObj2, NotificationCompat.CATEGORY_SERVICE, uuid.toString());
            addProperty(returnObj2, "error", NotificationCompat.CATEGORY_SERVICE);
            addProperty(returnObj2, "message", "Failed to add service");
            callbackContext.error(returnObj2);
        }
    }

    private void removeServiceAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject obj = getArgsObject(args);
        if (!isNotArgsObject(obj, callbackContext)) {
            UUID uuid = getUUID(obj.optString(NotificationCompat.CATEGORY_SERVICE, null));
            BluetoothGattService service = this.gattServer.getService(uuid);
            if (service == null) {
                JSONObject returnObj = new JSONObject();
                addProperty(returnObj, NotificationCompat.CATEGORY_SERVICE, uuid.toString());
                addProperty(returnObj, "error", NotificationCompat.CATEGORY_SERVICE);
                addProperty(returnObj, "message", "Service doesn't exist");
                callbackContext.error(returnObj);
                return;
            }
            boolean result = this.gattServer.removeService(service);
            if (result) {
                JSONObject returnObj2 = new JSONObject();
                addProperty(returnObj2, NotificationCompat.CATEGORY_SERVICE, uuid.toString());
                addProperty(returnObj2, NotificationCompat.CATEGORY_STATUS, "serviceRemoved");
                callbackContext.success(returnObj2);
                return;
            }
            JSONObject returnObj3 = new JSONObject();
            addProperty(returnObj3, NotificationCompat.CATEGORY_SERVICE, uuid.toString());
            addProperty(returnObj3, "error", NotificationCompat.CATEGORY_SERVICE);
            addProperty(returnObj3, "message", "Failed to remove service");
            callbackContext.error(returnObj3);
        }
    }

    private void removeAllServicesAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.gattServer.clearServices();
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "allServicesRemoved");
        callbackContext.success(returnObj);
    }

    private void startAdvertisingAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject obj = getArgsObject(args);
        if (!isNotArgsObject(obj, callbackContext)) {
            BluetoothLeAdvertiser advertiser = this.bluetoothAdapter.getBluetoothLeAdvertiser();
            if (advertiser == null || !this.bluetoothAdapter.isMultipleAdvertisementSupported()) {
                JSONObject returnObj = new JSONObject();
                addProperty(returnObj, "error", "startAdvertising");
                addProperty(returnObj, "message", "Advertising isn't supported");
                callbackContext.error(returnObj);
                return;
            }
            AdvertiseSettings.Builder settingsBuilder = new AdvertiseSettings.Builder();
            String modeS = obj.optString("mode", "balanced");
            int mode = 1;
            if (modeS.equals("lowLatency")) {
                mode = 2;
            } else if (modeS.equals("lowPower")) {
                mode = 0;
            }
            settingsBuilder.setAdvertiseMode(mode);
            boolean connectable = obj.optBoolean("connectable", true);
            settingsBuilder.setConnectable(connectable);
            int timeout = obj.optInt("timeout", 1000);
            if (timeout < 1 || timeout > 180000) {
                JSONObject returnObj2 = new JSONObject();
                addProperty(returnObj2, "error", "startAdvertising");
                addProperty(returnObj2, "message", "Invalid timeout (1 - 180000)");
                callbackContext.error(returnObj2);
                return;
            }
            settingsBuilder.setTimeout(timeout);
            String txPowerLevelS = obj.optString("txPowerLevel", FirebaseAnalytics.Param.MEDIUM);
            int txPowerLevel = 2;
            if (txPowerLevelS.equals("high")) {
                txPowerLevel = 3;
            } else if (txPowerLevelS.equals("low")) {
                txPowerLevel = 1;
            } else if (txPowerLevelS.equals("ultraLow")) {
                txPowerLevel = 0;
            }
            settingsBuilder.setTxPowerLevel(txPowerLevel);
            AdvertiseSettings advertiseSettings = settingsBuilder.build();
            AdvertiseData.Builder dataBuilder = new AdvertiseData.Builder();
            int manufacturerId = obj.optInt("manufacturerId", 0);
            byte[] manufacturerSpecificData = getPropertyBytes(obj, "manufacturerSpecificData");
            if (manufacturerId >= 0 && manufacturerSpecificData != null) {
                dataBuilder.addManufacturerData(manufacturerId, manufacturerSpecificData);
            }
            UUID uuid = getUUID(obj.optString(NotificationCompat.CATEGORY_SERVICE, null));
            if (uuid != null) {
                dataBuilder.addServiceUuid(new ParcelUuid(uuid));
            }
            dataBuilder.setIncludeDeviceName(obj.optBoolean("includeDeviceName", true));
            dataBuilder.setIncludeTxPowerLevel(obj.optBoolean("includeTxPowerLevel", true));
            AdvertiseData advertiseData = dataBuilder.build();
            this.advertiseCallbackContext = callbackContext;
            advertiser.startAdvertising(advertiseSettings, advertiseData, this.advertiseCallback);
        }
    }

    private void stopAdvertisingAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        BluetoothLeAdvertiser advertiser = this.bluetoothAdapter.getBluetoothLeAdvertiser();
        if (advertiser == null || !this.bluetoothAdapter.isMultipleAdvertisementSupported()) {
            JSONObject returnObj = new JSONObject();
            addProperty(returnObj, "error", "startAdvertising");
            addProperty(returnObj, "message", "Advertising isn't supported");
            callbackContext.error(returnObj);
            return;
        }
        advertiser.stopAdvertising(this.advertiseCallback);
        JSONObject returnObj2 = new JSONObject();
        addProperty(returnObj2, NotificationCompat.CATEGORY_STATUS, "advertisingStopped");
        callbackContext.success(returnObj2);
    }

    private void isAdvertisingAction(CallbackContext callbackContext) throws JSONException {
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "isAdvertising", Boolean.valueOf(this.isAdvertising));
        callbackContext.success(returnObj);
    }

    private void respondAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject obj = getArgsObject(args);
        if (!isNotArgsObject(obj, callbackContext)) {
            String address = getAddress(obj);
            if (!isNotAddress(address, callbackContext)) {
                BluetoothDevice device = this.bluetoothAdapter.getRemoteDevice(address);
                int requestId = obj.optInt("requestId", 0);
                obj.optInt(NotificationCompat.CATEGORY_STATUS, 0);
                int offset = obj.optInt("offset", 0);
                byte[] value = getPropertyBytes(obj, FirebaseAnalytics.Param.VALUE);
                boolean result = this.gattServer.sendResponse(device, requestId, 0, offset, value);
                if (result) {
                    JSONObject returnObj = new JSONObject();
                    addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "responded");
                    addProperty(returnObj, "requestId", Integer.valueOf(requestId));
                    callbackContext.success(returnObj);
                    return;
                }
                JSONObject returnObj2 = new JSONObject();
                addProperty(returnObj2, "error", "respond");
                addProperty(returnObj2, "message", "Failed to respond");
                addProperty(returnObj2, "requestId", Integer.valueOf(requestId));
                callbackContext.error(returnObj2);
            }
        }
    }

    private void notifyAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject obj = getArgsObject(args);
        if (!isNotArgsObject(obj, callbackContext)) {
            String address = getAddress(obj);
            if (!isNotAddress(address, callbackContext)) {
                BluetoothDevice device = this.bluetoothAdapter.getRemoteDevice(address);
                UUID serviceUuid = getUUID(obj.optString(NotificationCompat.CATEGORY_SERVICE, null));
                BluetoothGattService service = this.gattServer.getService(serviceUuid);
                if (service == null) {
                    JSONObject returnObj = new JSONObject();
                    addProperty(returnObj, "error", NotificationCompat.CATEGORY_SERVICE);
                    addProperty(returnObj, "message", "Service not found");
                    callbackContext.error(returnObj);
                }
                UUID characteristicUuid = getUUID(obj.optString("characteristic", null));
                BluetoothGattCharacteristic characteristic = service.getCharacteristic(characteristicUuid);
                if (characteristic == null) {
                    JSONObject returnObj2 = new JSONObject();
                    addProperty(returnObj2, "error", "characteristic");
                    addProperty(returnObj2, "message", "Characteristic not found");
                    callbackContext.error(returnObj2);
                }
                byte[] value = getPropertyBytes(obj, FirebaseAnalytics.Param.VALUE);
                boolean setResult = characteristic.setValue(value);
                if (!setResult) {
                    JSONObject returnObj3 = new JSONObject();
                    addProperty(returnObj3, "error", "respond");
                    addProperty(returnObj3, "message", "Failed to set value");
                    callbackContext.error(returnObj3);
                }
                BluetoothGattDescriptor descriptor = characteristic.getDescriptor(this.clientConfigurationDescriptorUuid);
                byte[] descriptorValue = descriptor.getValue();
                boolean isIndicate = false;
                if (Arrays.equals(descriptorValue, BluetoothGattDescriptor.ENABLE_INDICATION_VALUE)) {
                    isIndicate = true;
                }
                boolean result = this.gattServer.notifyCharacteristicChanged(device, characteristic, isIndicate);
                if (!result) {
                    JSONObject returnObj4 = new JSONObject();
                    addProperty(returnObj4, "error", "notify");
                    addProperty(returnObj4, "message", "Failed to notify");
                    callbackContext.error(returnObj4);
                }
            }
        }
    }

    public void hasPermissionAction(CallbackContext callbackContext) throws JSONException {
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "hasPermission", Boolean.valueOf(this.cordova.hasPermission("android.permission.ACCESS_COARSE_LOCATION")));
        callbackContext.success(returnObj);
    }

    public void requestPermissionAction(CallbackContext callbackContext) throws JSONException {
        if (Build.VERSION.SDK_INT < 23) {
            JSONObject returnObj = new JSONObject();
            addProperty(returnObj, "error", "requestPermission");
            addProperty(returnObj, "message", "Operation unsupported");
            callbackContext.error(returnObj);
            return;
        }
        this.permissionsCallback = callbackContext;
        this.cordova.requestPermission(this, 59628, "android.permission.ACCESS_COARSE_LOCATION");
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
        if (this.permissionsCallback != null) {
            JSONObject returnObj = new JSONObject();
            addProperty(returnObj, "requestPermission", Boolean.valueOf(this.cordova.hasPermission("android.permission.ACCESS_COARSE_LOCATION")));
            this.permissionsCallback.success(returnObj);
        }
    }

    private void isLocationEnabledAction(CallbackContext callbackContext) throws JSONException {
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "isLocationEnabled", Boolean.valueOf(isLocationEnabled()));
        callbackContext.success(returnObj);
    }

    private boolean isLocationEnabled() {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        try {
            return Settings.Secure.getInt(this.cordova.getActivity().getContentResolver(), "location_mode") != 0;
        } catch (Settings.SettingNotFoundException e) {
            return true;
        }
    }

    private void requestLocationAction(CallbackContext callbackContext) {
        this.locationCallback = callbackContext;
        Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        this.cordova.startActivityForResult(this, intent, 59629);
    }

    private void initializeAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.initCallbackContext = callbackContext;
        if (this.bluetoothAdapter != null) {
            JSONObject returnObj = new JSONObject();
            if (this.bluetoothAdapter.isEnabled()) {
                addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "enabled");
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                pluginResult.setKeepCallback(true);
                this.initCallbackContext.sendPluginResult(pluginResult);
                return;
            }
            addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "disabled");
            addProperty(returnObj, "message", "Bluetooth not enabled");
            PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK, returnObj);
            pluginResult2.setKeepCallback(true);
            this.initCallbackContext.sendPluginResult(pluginResult2);
            return;
        }
        Activity activity = this.cordova.getActivity();
        JSONObject obj = getArgsObject(args);
        if (obj != null && getStatusReceiver(obj)) {
            activity.registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            this.isReceiverRegistered = true;
        }
        BluetoothManager bluetoothManager = (BluetoothManager) activity.getSystemService("bluetooth");
        this.bluetoothAdapter = bluetoothManager.getAdapter();
        this.connections = new HashMap<>();
        JSONObject returnObj2 = new JSONObject();
        if (this.bluetoothAdapter.isEnabled()) {
            addProperty(returnObj2, NotificationCompat.CATEGORY_STATUS, "enabled");
            PluginResult pluginResult3 = new PluginResult(PluginResult.Status.OK, returnObj2);
            pluginResult3.setKeepCallback(true);
            this.initCallbackContext.sendPluginResult(pluginResult3);
            return;
        }
        boolean request = false;
        if (obj != null) {
            request = getRequest(obj);
        }
        if (request) {
            Intent enableBtIntent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
            this.cordova.startActivityForResult(this, enableBtIntent, 59627);
            return;
        }
        addProperty(returnObj2, NotificationCompat.CATEGORY_STATUS, "disabled");
        addProperty(returnObj2, "message", "Bluetooth not enabled");
        PluginResult pluginResult4 = new PluginResult(PluginResult.Status.OK, returnObj2);
        pluginResult4.setKeepCallback(true);
        this.initCallbackContext.sendPluginResult(pluginResult4);
    }

    private void enableAction(CallbackContext callbackContext) throws JSONException {
        if (!isNotInitialized(callbackContext, false) && !isNotDisabled(callbackContext)) {
            boolean result = this.bluetoothAdapter.enable();
            if (!result) {
                JSONObject returnObj = new JSONObject();
                addProperty(returnObj, "error", "enable");
                addProperty(returnObj, "message", "Bluetooth not enabled");
                callbackContext.error(returnObj);
            }
        }
    }

    private void disableAction(CallbackContext callbackContext) throws JSONException {
        if (!isNotInitialized(callbackContext, true)) {
            boolean result = this.bluetoothAdapter.disable();
            if (!result) {
                JSONObject returnObj = new JSONObject();
                addProperty(returnObj, "error", "disable");
                addProperty(returnObj, "message", "Bluetooth not disabled");
                callbackContext.error(returnObj);
            }
        }
    }

    private synchronized void startScanAction(JSONArray args, CallbackContext callbackContext) {
        if (!isNotInitialized(callbackContext, true)) {
            if (this.scanCallbackContext != null) {
                JSONObject returnObj = new JSONObject();
                addProperty(returnObj, "error", "startScan");
                addProperty(returnObj, "message", "Scanning already in progress");
                callbackContext.error(returnObj);
            } else {
                JSONObject obj = getArgsObject(args);
                if (obj == null) {
                    obj = new JSONObject();
                }
                UUID[] uuids = getServiceUuids(obj);
                this.scanCallbackContext = callbackContext;
                if (Build.VERSION.SDK_INT < 21) {
                    boolean result = uuids.length == 0 ? this.bluetoothAdapter.startLeScan(this.scanCallbackKitKat) : this.bluetoothAdapter.startLeScan(uuids, this.scanCallbackKitKat);
                    if (!result) {
                        JSONObject returnObj2 = new JSONObject();
                        addProperty(returnObj2, "error", "startScan");
                        addProperty(returnObj2, "message", "Scan failed to start");
                        callbackContext.error(returnObj2);
                        this.scanCallbackContext = null;
                    }
                } else {
                    ArrayList<ScanFilter> scanFilter = new ArrayList<>();
                    for (UUID uuid : uuids) {
                        ScanFilter.Builder builder = new ScanFilter.Builder();
                        builder.setServiceUuid(new ParcelUuid(uuid));
                        scanFilter.add(builder.build());
                    }
                    ScanSettings.Builder scanSettings = new ScanSettings.Builder();
                    scanSettings.setReportDelay(0L);
                    int scanMode = obj.optInt("scanMode", 2);
                    try {
                        scanSettings.setScanMode(scanMode);
                    } catch (IllegalArgumentException e) {
                    }
                    if (Build.VERSION.SDK_INT >= 23) {
                        int matchMode = obj.optInt("matchMode", 1);
                        try {
                            scanSettings.setMatchMode(matchMode);
                        } catch (IllegalArgumentException e2) {
                        }
                        int matchNum = obj.optInt("matchNum", 3);
                        try {
                            scanSettings.setNumOfMatches(matchNum);
                        } catch (IllegalArgumentException e3) {
                        }
                        int callbackType = obj.optInt("callbackType", 1);
                        try {
                            scanSettings.setCallbackType(callbackType);
                        } catch (IllegalArgumentException e4) {
                        }
                    }
                    this.bluetoothAdapter.getBluetoothLeScanner().startScan(scanFilter, scanSettings.build(), this.scanCallback);
                }
                JSONObject returnObj3 = new JSONObject();
                addProperty(returnObj3, NotificationCompat.CATEGORY_STATUS, "scanStarted");
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj3);
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);
            }
        }
    }

    private synchronized void stopScanAction(CallbackContext callbackContext) {
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject returnObj = new JSONObject();
            if (this.scanCallbackContext == null) {
                addProperty(returnObj, "error", "stopScan");
                addProperty(returnObj, "message", "Not scanning");
                callbackContext.error(returnObj);
            } else {
                if (Build.VERSION.SDK_INT < 21) {
                    this.bluetoothAdapter.stopLeScan(this.scanCallbackKitKat);
                } else {
                    this.bluetoothAdapter.getBluetoothLeScanner().stopScan(this.scanCallback);
                }
                this.scanCallbackContext = null;
                addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "scanStopped");
                callbackContext.success(returnObj);
            }
        }
    }

    private void retrieveConnectedAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (!isNotInitialized(callbackContext, true)) {
            JSONArray returnArray = new JSONArray();
            Set<BluetoothDevice> devices = this.bluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : devices) {
                if (device.getType() == 2) {
                    JSONObject returnObj = new JSONObject();
                    addDevice(returnObj, device);
                    returnArray.put(returnObj);
                }
            }
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnArray);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
        }
    }

    private void bondAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (!this.isBondReceiverRegistered) {
            this.cordova.getActivity().registerReceiver(this.mBondReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
            this.isBondReceiverRegistered = true;
        }
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext)) {
                    BluetoothDevice device = this.bluetoothAdapter.getRemoteDevice(address);
                    if (device == null) {
                        JSONObject returnObj = new JSONObject();
                        addProperty(returnObj, "error", "bond");
                        addProperty(returnObj, "message", "Device not found");
                        addProperty(returnObj, "address", address);
                        callbackContext.error(returnObj);
                        return;
                    }
                    CallbackContext checkCallback = this.bonds.get(address);
                    if (checkCallback != null) {
                        JSONObject returnObj2 = new JSONObject();
                        addDevice(returnObj2, device);
                        addProperty(returnObj2, "error", "bond");
                        addProperty(returnObj2, "message", "Device already bonding");
                        callbackContext.error(returnObj2);
                        return;
                    }
                    int bondState = device.getBondState();
                    if (bondState == 12 || bondState == 11) {
                        JSONObject returnObj3 = new JSONObject();
                        addDevice(returnObj3, device);
                        addProperty(returnObj3, "error", "bond");
                        addProperty(returnObj3, "message", bondState == 12 ? "Device already bonded" : "Device already bonding");
                        callbackContext.error(returnObj3);
                        return;
                    }
                    this.bonds.put(address, callbackContext);
                    boolean result = device.createBond();
                    if (!result) {
                        JSONObject returnObj4 = new JSONObject();
                        addDevice(returnObj4, device);
                        addProperty(returnObj4, "error", "bond");
                        addProperty(returnObj4, "message", "Device failed to bond on return");
                        callbackContext.error(returnObj4);
                        this.bonds.remove(address);
                    }
                }
            }
        }
    }

    private void unbondAction(JSONArray args, CallbackContext callbackContext) throws JSONException, NoSuchMethodException, SecurityException {
        if (!this.isBondReceiverRegistered) {
            this.cordova.getActivity().registerReceiver(this.mBondReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
            this.isBondReceiverRegistered = true;
        }
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext)) {
                    BluetoothDevice device = this.bluetoothAdapter.getRemoteDevice(address);
                    if (device == null) {
                        JSONObject returnObj = new JSONObject();
                        addProperty(returnObj, "error", "bond");
                        addProperty(returnObj, "message", "Device not found");
                        addProperty(returnObj, "address", address);
                        callbackContext.error(returnObj);
                        return;
                    }
                    CallbackContext checkCallback = this.bonds.get(address);
                    if (checkCallback != null) {
                        JSONObject returnObj2 = new JSONObject();
                        addDevice(returnObj2, device);
                        addProperty(returnObj2, "error", "bond");
                        addProperty(returnObj2, "message", "Device already bonding");
                        callbackContext.error(returnObj2);
                        return;
                    }
                    int bondState = device.getBondState();
                    if (bondState == 10 || bondState == 11) {
                        JSONObject returnObj3 = new JSONObject();
                        addDevice(returnObj3, device);
                        addProperty(returnObj3, "error", "unbond");
                        addProperty(returnObj3, "message", bondState == 10 ? "Device already unbonded" : "Device already bonding");
                        callbackContext.error(returnObj3);
                        return;
                    }
                    this.bonds.put(address, callbackContext);
                    boolean result = false;
                    try {
                        Method mi = device.getClass().getMethod("removeBond", new Class[0]);
                        Boolean returnValue = (Boolean) mi.invoke(device, new Object[0]);
                        result = returnValue.booleanValue();
                    } catch (Exception e) {
                        Log.d("BLE", e.getMessage());
                    }
                    if (!result) {
                        JSONObject returnObj4 = new JSONObject();
                        addDevice(returnObj4, device);
                        addProperty(returnObj4, "error", "unbond");
                        addProperty(returnObj4, "message", "Device failed to unbond on return");
                        callbackContext.error(returnObj4);
                        this.bonds.remove(address);
                    }
                }
            }
        }
    }

    private void connectAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && !wasConnected(address, callbackContext)) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothDevice device = this.bluetoothAdapter.getRemoteDevice(address);
                    if (device == null) {
                        addProperty(returnObj, "error", "connect");
                        addProperty(returnObj, "message", "Device not found");
                        addProperty(returnObj, "address", address);
                        callbackContext.error(returnObj);
                        return;
                    }
                    HashMap<Object, Object> connection = new HashMap<>();
                    connection.put("state", 1);
                    connection.put("discoveredState", 0);
                    connection.put("connect", callbackContext);
                    BluetoothGatt bluetoothGatt = device.connectGatt(this.cordova.getActivity().getApplicationContext(), false, this.bluetoothGattCallback);
                    connection.put("peripheral", bluetoothGatt);
                    this.connections.put(device.getAddress(), connection);
                }
            }
        }
    }

    private void reconnectAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    if (!isNotDisconnected(connection, device, callbackContext)) {
                        JSONObject returnObj = new JSONObject();
                        addDevice(returnObj, device);
                        boolean result = bluetoothGatt.connect();
                        if (!result) {
                            addProperty(returnObj, "error", "reconnect");
                            addProperty(returnObj, "message", "Reconnection to device failed");
                            callbackContext.error(returnObj);
                        } else {
                            connection.put("state", 1);
                            connection.put("connect", callbackContext);
                        }
                    }
                }
            }
        }
    }

    private void disconnectAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    if (!isDisconnected(connection, device, callbackContext)) {
                        int state = Integer.valueOf(connection.get("state").toString()).intValue();
                        JSONObject returnObj = new JSONObject();
                        addDevice(returnObj, device);
                        if (state == 1) {
                            addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "disconnected");
                            connection.put("state", 0);
                            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                            pluginResult.setKeepCallback(false);
                            callbackContext.sendPluginResult(pluginResult);
                            connection.remove("connect");
                        } else {
                            connection.put("connect", callbackContext);
                        }
                        bluetoothGatt.disconnect();
                    }
                }
            }
        }
    }

    private void closeAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    JSONObject returnObj = new JSONObject();
                    addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "closed");
                    addDevice(returnObj, device);
                    bluetoothGatt.close();
                    this.connections.remove(device.getAddress());
                    callbackContext.success(returnObj);
                    Operation operation = this.queue.peek();
                    if (operation != null && operation.device != null && operation.device.getAddress().equals(address)) {
                        queueRemove();
                    }
                }
            }
        }
    }

    private void discoverAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    if (!isNotConnected(connection, device, callbackContext)) {
                        JSONObject returnObj = new JSONObject();
                        addDevice(returnObj, device);
                        int discoveredState = Integer.valueOf(connection.get("discoveredState").toString()).intValue();
                        if (discoveredState == 1) {
                            addProperty(returnObj, "error", "discover");
                            addProperty(returnObj, "message", "Already discovering device");
                            callbackContext.error(returnObj);
                        } else {
                            if (discoveredState == 2) {
                                callbackContext.success(getDiscovery(bluetoothGatt));
                                return;
                            }
                            connection.put("discoveredState", 1);
                            connection.put("discover", callbackContext);
                            bluetoothGatt.discoverServices();
                        }
                    }
                }
            }
        }
    }

    private boolean readAction(Operation operation) throws JSONException {
        HashMap<Object, Object> connection;
        JSONArray args = operation.args;
        CallbackContext callbackContext = operation.callbackContext;
        if (isNotInitialized(callbackContext, true)) {
            return false;
        }
        JSONObject obj = getArgsObject(args);
        if (isNotArgsObject(obj, callbackContext)) {
            return false;
        }
        String address = getAddress(obj);
        if (isNotAddress(address, callbackContext) || (connection = wasNeverConnected(address, callbackContext)) == null) {
            return false;
        }
        BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
        BluetoothDevice device = bluetoothGatt.getDevice();
        if (isNotConnected(connection, device, callbackContext)) {
            return false;
        }
        BluetoothGattService service = getService(bluetoothGatt, obj);
        if (isNotService(service, device, callbackContext)) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = getCharacteristic(obj, service);
        if (isNotCharacteristic(characteristic, device, callbackContext)) {
            return false;
        }
        UUID characteristicUuid = characteristic.getUuid();
        AddCallback(characteristicUuid, connection, "read", callbackContext);
        boolean result = bluetoothGatt.readCharacteristic(characteristic);
        if (!result) {
            JSONObject returnObj = new JSONObject();
            addDevice(returnObj, device);
            addCharacteristic(returnObj, characteristic);
            addProperty(returnObj, "error", "read");
            addProperty(returnObj, "message", "Unable to read");
            callbackContext.error(returnObj);
            RemoveCallback(characteristicUuid, connection, "read");
            return false;
        }
        operation.device = device;
        return true;
    }

    private boolean subscribeAction(Operation operation) throws JSONException {
        HashMap<Object, Object> connection;
        boolean result;
        JSONArray args = operation.args;
        CallbackContext callbackContext = operation.callbackContext;
        if (isNotInitialized(callbackContext, true)) {
            return false;
        }
        JSONObject obj = getArgsObject(args);
        if (isNotArgsObject(obj, callbackContext)) {
            return false;
        }
        String address = getAddress(obj);
        if (isNotAddress(address, callbackContext) || (connection = wasNeverConnected(address, callbackContext)) == null) {
            return false;
        }
        BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
        BluetoothDevice device = bluetoothGatt.getDevice();
        if (isNotConnected(connection, device, callbackContext)) {
            return false;
        }
        BluetoothGattService service = getService(bluetoothGatt, obj);
        if (isNotService(service, device, callbackContext)) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = getCharacteristic(obj, service);
        if (isNotCharacteristic(characteristic, device, callbackContext)) {
            return false;
        }
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(this.clientConfigurationDescriptorUuid);
        if (isNotDescriptor(descriptor, device, callbackContext)) {
            return false;
        }
        UUID characteristicUuid = characteristic.getUuid();
        JSONObject returnObj = new JSONObject();
        addDevice(returnObj, device);
        addCharacteristic(returnObj, characteristic);
        CallbackContext checkExisting = GetCallback(characteristicUuid, connection, "subscribe");
        if (checkExisting != null) {
            addProperty(returnObj, "error", "subscription");
            addProperty(returnObj, "message", "Already subscribed");
            callbackContext.error(returnObj);
            return false;
        }
        if ((characteristic.getProperties() & 16) == 16) {
            result = descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else {
            result = descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
        }
        if (!result) {
            addProperty(returnObj, "error", "writeDescriptor");
            addProperty(returnObj, "message", "Write descriptor value not set");
            callbackContext.error(returnObj);
            return false;
        }
        AddCallback(characteristicUuid, connection, "subscribe", callbackContext);
        boolean result2 = bluetoothGatt.writeDescriptor(descriptor);
        if (!result2) {
            addProperty(returnObj, "error", "writeDescriptor");
            addProperty(returnObj, "message", "Unable to write descriptor");
            callbackContext.error(returnObj);
            RemoveCallback(characteristicUuid, connection, "subscribe");
            return false;
        }
        operation.device = device;
        return true;
    }

    private boolean unsubscribeAction(Operation operation) throws JSONException {
        HashMap<Object, Object> connection;
        JSONArray args = operation.args;
        CallbackContext callbackContext = operation.callbackContext;
        if (isNotInitialized(callbackContext, true)) {
            return false;
        }
        JSONObject obj = getArgsObject(args);
        if (isNotArgsObject(obj, callbackContext)) {
            return false;
        }
        String address = getAddress(obj);
        if (isNotAddress(address, callbackContext) || (connection = wasNeverConnected(address, callbackContext)) == null) {
            return false;
        }
        BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
        BluetoothDevice device = bluetoothGatt.getDevice();
        if (isNotConnected(connection, device, callbackContext)) {
            return false;
        }
        BluetoothGattService service = getService(bluetoothGatt, obj);
        if (isNotService(service, device, callbackContext)) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = getCharacteristic(obj, service);
        if (isNotCharacteristic(characteristic, device, callbackContext)) {
            return false;
        }
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(this.clientConfigurationDescriptorUuid);
        if (isNotDescriptor(descriptor, device, callbackContext)) {
            return false;
        }
        UUID characteristicUuid = characteristic.getUuid();
        JSONObject returnObj = new JSONObject();
        addDevice(returnObj, device);
        addCharacteristic(returnObj, characteristic);
        CallbackContext checkExisting = GetCallback(characteristicUuid, connection, "subscribe");
        if (checkExisting == null) {
            addProperty(returnObj, "error", "subscription");
            addProperty(returnObj, "message", "Already unsubscribed");
            callbackContext.error(returnObj);
            return false;
        }
        RemoveCallback(characteristicUuid, connection, "subscribe");
        boolean result = descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        if (!result) {
            addProperty(returnObj, "error", "writeDescriptor");
            addProperty(returnObj, "message", "Write descriptor value not set");
            callbackContext.error(returnObj);
            return false;
        }
        AddCallback(characteristicUuid, connection, "unsubscribe", callbackContext);
        boolean result2 = bluetoothGatt.writeDescriptor(descriptor);
        if (!result2) {
            addProperty(returnObj, "error", "writeDescriptor");
            addProperty(returnObj, "message", "Unable to write descriptor");
            callbackContext.error(returnObj);
            RemoveCallback(characteristicUuid, connection, "unsubscribe");
            return false;
        }
        operation.device = device;
        return true;
    }

    private boolean writeAction(Operation operation) throws JSONException {
        HashMap<Object, Object> connection;
        JSONArray args = operation.args;
        CallbackContext callbackContext = operation.callbackContext;
        if (isNotInitialized(callbackContext, true)) {
            return false;
        }
        JSONObject obj = getArgsObject(args);
        if (isNotArgsObject(obj, callbackContext)) {
            return false;
        }
        String address = getAddress(obj);
        if (isNotAddress(address, callbackContext) || (connection = wasNeverConnected(address, callbackContext)) == null) {
            return false;
        }
        BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
        BluetoothDevice device = bluetoothGatt.getDevice();
        if (isNotConnected(connection, device, callbackContext)) {
            return false;
        }
        BluetoothGattService service = getService(bluetoothGatt, obj);
        if (isNotService(service, device, callbackContext)) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = getCharacteristic(obj, service);
        if (isNotCharacteristic(characteristic, device, callbackContext)) {
            return false;
        }
        UUID characteristicUuid = characteristic.getUuid();
        JSONObject returnObj = new JSONObject();
        addDevice(returnObj, device);
        addCharacteristic(returnObj, characteristic);
        byte[] value = getPropertyBytes(obj, FirebaseAnalytics.Param.VALUE);
        if (value == null) {
            addProperty(returnObj, "error", "write");
            addProperty(returnObj, "message", "Write value not found");
            callbackContext.error(returnObj);
            return false;
        }
        int writeType = getWriteType(obj);
        characteristic.setWriteType(writeType);
        boolean result = characteristic.setValue(value);
        if (!result) {
            addProperty(returnObj, "error", "write");
            addProperty(returnObj, "message", "Write value not set");
            callbackContext.error(returnObj);
            return false;
        }
        AddCallback(characteristicUuid, connection, "write", callbackContext);
        boolean result2 = bluetoothGatt.writeCharacteristic(characteristic);
        if (!result2) {
            addProperty(returnObj, "error", "write");
            addProperty(returnObj, "message", "Unable to write");
            callbackContext.error(returnObj);
            RemoveCallback(characteristicUuid, connection, "write");
            return false;
        }
        operation.device = device;
        return true;
    }

    private void writeQAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        int thisChunkSize;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    if (!isNotConnected(connection, device, callbackContext)) {
                        BluetoothGattService service = getService(bluetoothGatt, obj);
                        if (!isNotService(service, device, callbackContext)) {
                            BluetoothGattCharacteristic characteristic = getCharacteristic(obj, service);
                            if (!isNotCharacteristic(characteristic, device, callbackContext)) {
                                UUID characteristicUuid = characteristic.getUuid();
                                JSONObject returnObj = new JSONObject();
                                addDevice(returnObj, device);
                                addCharacteristic(returnObj, characteristic);
                                byte[] value = getPropertyBytes(obj, FirebaseAnalytics.Param.VALUE);
                                if (value == null) {
                                    addProperty(returnObj, "error", "write");
                                    addProperty(returnObj, "message", "Write value not found");
                                    callbackContext.error(returnObj);
                                    return;
                                }
                                int writeType = getWriteType(obj);
                                characteristic.setWriteType(writeType);
                                AddCallback(characteristicUuid, connection, "write", callbackContext);
                                this.queueQuick.clear();
                                int length = value.length;
                                int offset = 0;
                                do {
                                    if (length - offset > 20) {
                                        thisChunkSize = 20;
                                    } else {
                                        thisChunkSize = length - offset;
                                    }
                                    byte[] chunk = Arrays.copyOfRange(value, offset, offset + thisChunkSize);
                                    offset += thisChunkSize;
                                    this.queueQuick.add(chunk);
                                } while (offset < length);
                                writeQ(connection, characteristic, bluetoothGatt);
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeQ(HashMap<Object, Object> connection, BluetoothGattCharacteristic characteristic, BluetoothGatt bluetoothGatt) throws JSONException {
        byte[] value = this.queueQuick.poll();
        if (value == null) {
            JSONObject returnObj = new JSONObject();
            addDevice(returnObj, bluetoothGatt.getDevice());
            addCharacteristic(returnObj, characteristic);
            addProperty(returnObj, "error", "write");
            addProperty(returnObj, "message", "Queue was empty");
            CallbackContext callbackContext = GetCallback(characteristic.getUuid(), connection, "write");
            RemoveCallback(characteristic.getUuid(), connection, "write");
            callbackContext.error(returnObj);
            return;
        }
        boolean result = characteristic.setValue(value);
        if (!result) {
            this.queueQuick.clear();
            JSONObject returnObj2 = new JSONObject();
            addDevice(returnObj2, bluetoothGatt.getDevice());
            addCharacteristic(returnObj2, characteristic);
            addProperty(returnObj2, "error", "write");
            addProperty(returnObj2, "message", "Write value not set");
            CallbackContext callbackContext2 = GetCallback(characteristic.getUuid(), connection, "write");
            RemoveCallback(characteristic.getUuid(), connection, "write");
            callbackContext2.error(returnObj2);
            return;
        }
        boolean result2 = bluetoothGatt.writeCharacteristic(characteristic);
        if (!result2) {
            this.queueQuick.clear();
            JSONObject returnObj3 = new JSONObject();
            addDevice(returnObj3, bluetoothGatt.getDevice());
            addCharacteristic(returnObj3, characteristic);
            addProperty(returnObj3, "error", "write");
            addProperty(returnObj3, "message", "Unable to write");
            CallbackContext callbackContext3 = GetCallback(characteristic.getUuid(), connection, "write");
            RemoveCallback(characteristic.getUuid(), connection, "write");
            callbackContext3.error(returnObj3);
        }
    }

    private boolean readDescriptorAction(Operation operation) throws JSONException {
        HashMap<Object, Object> connection;
        JSONArray args = operation.args;
        CallbackContext callbackContext = operation.callbackContext;
        if (isNotInitialized(callbackContext, true)) {
            return false;
        }
        JSONObject obj = getArgsObject(args);
        if (isNotArgsObject(obj, callbackContext)) {
            return false;
        }
        String address = getAddress(obj);
        if (isNotAddress(address, callbackContext) || (connection = wasNeverConnected(address, callbackContext)) == null) {
            return false;
        }
        BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
        BluetoothDevice device = bluetoothGatt.getDevice();
        if (isNotConnected(connection, device, callbackContext)) {
            return false;
        }
        BluetoothGattService service = getService(bluetoothGatt, obj);
        if (isNotService(service, device, callbackContext)) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = getCharacteristic(obj, service);
        if (isNotCharacteristic(characteristic, device, callbackContext)) {
            return false;
        }
        BluetoothGattDescriptor descriptor = getDescriptor(obj, characteristic);
        if (isNotDescriptor(descriptor, device, callbackContext)) {
            return false;
        }
        UUID descriptorUuid = descriptor.getUuid();
        UUID characteristicUuid = characteristic.getUuid();
        AddDescriptorCallback(descriptorUuid, characteristicUuid, connection, "read", callbackContext);
        boolean result = bluetoothGatt.readDescriptor(descriptor);
        if (!result) {
            JSONObject returnObj = new JSONObject();
            addDevice(returnObj, device);
            addDescriptor(returnObj, descriptor);
            addProperty(returnObj, "error", "readDescriptor");
            addProperty(returnObj, "message", "Unable to read descriptor");
            callbackContext.error(returnObj);
            RemoveDescriptorCallback(descriptorUuid, characteristicUuid, connection, "read");
            return false;
        }
        operation.device = device;
        return true;
    }

    private boolean writeDescriptorAction(Operation operation) throws JSONException {
        HashMap<Object, Object> connection;
        JSONArray args = operation.args;
        CallbackContext callbackContext = operation.callbackContext;
        if (isNotInitialized(callbackContext, true)) {
            return false;
        }
        JSONObject obj = getArgsObject(args);
        if (isNotArgsObject(obj, callbackContext)) {
            return false;
        }
        String address = getAddress(obj);
        if (isNotAddress(address, callbackContext) || (connection = wasNeverConnected(address, callbackContext)) == null) {
            return false;
        }
        BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
        BluetoothDevice device = bluetoothGatt.getDevice();
        if (isNotConnected(connection, device, callbackContext)) {
            return false;
        }
        BluetoothGattService service = getService(bluetoothGatt, obj);
        if (isNotService(service, device, callbackContext)) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = getCharacteristic(obj, service);
        if (isNotCharacteristic(characteristic, device, callbackContext)) {
            return false;
        }
        BluetoothGattDescriptor descriptor = getDescriptor(obj, characteristic);
        if (isNotDescriptor(descriptor, device, callbackContext)) {
            return false;
        }
        UUID descriptorUuid = descriptor.getUuid();
        UUID characteristicUuid = characteristic.getUuid();
        JSONObject returnObj = new JSONObject();
        addDevice(returnObj, device);
        addDescriptor(returnObj, descriptor);
        if (descriptor.getUuid().equals(this.clientConfigurationDescriptorUuid)) {
            addProperty(returnObj, "error", "writeDescriptor");
            addProperty(returnObj, "message", "Unable to write client configuration descriptor");
            callbackContext.error(returnObj);
            return false;
        }
        byte[] value = getPropertyBytes(obj, FirebaseAnalytics.Param.VALUE);
        if (value == null) {
            addProperty(returnObj, "error", "writeDescriptor");
            addProperty(returnObj, "message", "Write descriptor value not found");
            callbackContext.error(returnObj);
            return false;
        }
        boolean result = descriptor.setValue(value);
        if (!result) {
            addProperty(returnObj, "error", "writeDescriptor");
            addProperty(returnObj, "message", "Write descriptor value not set");
            callbackContext.error(returnObj);
            return false;
        }
        AddDescriptorCallback(descriptorUuid, characteristicUuid, connection, "write", callbackContext);
        boolean result2 = bluetoothGatt.writeDescriptor(descriptor);
        if (!result2) {
            addProperty(returnObj, "error", "writeDescriptor");
            addProperty(returnObj, "message", "Unable to write descriptor");
            callbackContext.error(returnObj);
            RemoveDescriptorCallback(descriptorUuid, characteristicUuid, connection, "write");
            return false;
        }
        operation.device = device;
        return true;
    }

    private void rssiAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    if (!isNotConnected(connection, device, callbackContext)) {
                        connection.put("rssi", callbackContext);
                        boolean result = bluetoothGatt.readRemoteRssi();
                        if (!result) {
                            JSONObject returnObj = new JSONObject();
                            addDevice(returnObj, device);
                            addProperty(returnObj, "error", "rssi");
                            addProperty(returnObj, "message", "Unable to read RSSI");
                            callbackContext.error(returnObj);
                            connection.remove("rssi");
                        }
                    }
                }
            }
        }
    }

    private void mtuAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    if (Build.VERSION.SDK_INT < 21) {
                        JSONObject returnObj = new JSONObject();
                        addDevice(returnObj, device);
                        addProperty(returnObj, "error", "mtu");
                        addProperty(returnObj, "message", "Requires API level 21");
                        callbackContext.error(returnObj);
                        return;
                    }
                    if (!isNotConnected(connection, device, callbackContext)) {
                        connection.put("mtu", callbackContext);
                        int mtu = getMtu(obj);
                        boolean result = bluetoothGatt.requestMtu(mtu);
                        if (!result) {
                            JSONObject returnObj2 = new JSONObject();
                            addDevice(returnObj2, device);
                            addProperty(returnObj2, "error", "mtu");
                            addProperty(returnObj2, "message", "Unable to set MTU");
                            callbackContext.error(returnObj2);
                            connection.remove("mtu");
                        }
                    }
                }
            }
        }
    }

    private void isInitializedAction(CallbackContext callbackContext) throws JSONException {
        boolean result = this.bluetoothAdapter != null;
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "isInitialized", Boolean.valueOf(result));
        callbackContext.success(returnObj);
    }

    private void isEnabledAction(CallbackContext callbackContext) throws JSONException {
        boolean result = this.bluetoothAdapter != null && this.bluetoothAdapter.isEnabled();
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "isEnabled", Boolean.valueOf(result));
        callbackContext.success(returnObj);
    }

    private void isScanningAction(CallbackContext callbackContext) throws JSONException {
        boolean result = this.scanCallbackContext != null;
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "isScanning", Boolean.valueOf(result));
        callbackContext.success(returnObj);
    }

    private void isBondedAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext)) {
                    BluetoothDevice device = this.bluetoothAdapter.getRemoteDevice(address);
                    if (device == null) {
                        JSONObject returnObj = new JSONObject();
                        addProperty(returnObj, "error", "bond");
                        addProperty(returnObj, "message", "Device not found");
                        addProperty(returnObj, "address", address);
                        callbackContext.error(returnObj);
                        return;
                    }
                    boolean result = device.getBondState() == 12;
                    JSONObject returnObj2 = new JSONObject();
                    addProperty(returnObj2, "isBonded", Boolean.valueOf(result));
                    addDevice(returnObj2, device);
                    callbackContext.success(returnObj2);
                }
            }
        }
    }

    private void wasConnectedAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext)) {
                    HashMap<Object, Object> connection = this.connections.get(address);
                    if (connection == null) {
                        JSONObject returnObj = new JSONObject();
                        addProperty(returnObj, "wasConnected", false);
                        addProperty(returnObj, "address", address);
                        callbackContext.success(returnObj);
                        return;
                    }
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    JSONObject returnObj2 = new JSONObject();
                    addProperty(returnObj2, "wasConnected", true);
                    addDevice(returnObj2, device);
                    callbackContext.success(returnObj2);
                }
            }
        }
    }

    private void isConnectedAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    int state = Integer.valueOf(connection.get("state").toString()).intValue();
                    boolean result = state == 2;
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    JSONObject returnObj = new JSONObject();
                    addProperty(returnObj, "isConnected", Boolean.valueOf(result));
                    addDevice(returnObj, device);
                    callbackContext.success(returnObj);
                }
            }
        }
    }

    private void isDiscoveredAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    BluetoothDevice device = bluetoothGatt.getDevice();
                    if (!isNotConnected(connection, device, callbackContext)) {
                        int state = Integer.valueOf(connection.get("discoveredState").toString()).intValue();
                        boolean result = state == 2;
                        JSONObject returnObj = new JSONObject();
                        addProperty(returnObj, "isDiscovered", Boolean.valueOf(result));
                        addDevice(returnObj, device);
                        callbackContext.success(returnObj);
                    }
                }
            }
        }
    }

    private void requestConnectionPriorityAction(JSONArray args, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection;
        int androidPriority;
        if (!isNotInitialized(callbackContext, true)) {
            JSONObject obj = getArgsObject(args);
            if (!isNotArgsObject(obj, callbackContext)) {
                String address = getAddress(obj);
                if (!isNotAddress(address, callbackContext) && (connection = wasNeverConnected(address, callbackContext)) != null) {
                    BluetoothGatt bluetoothGatt = (BluetoothGatt) connection.get("peripheral");
                    if (Build.VERSION.SDK_INT < 21) {
                        JSONObject returnObj = new JSONObject();
                        addDevice(returnObj, bluetoothGatt.getDevice());
                        addProperty(returnObj, "error", "requestConnectPriority");
                        addProperty(returnObj, "message", "Requires API level 21");
                        callbackContext.error(returnObj);
                        return;
                    }
                    String priority = obj.optString("connectionPriority", null);
                    if (priority == null) {
                        JSONObject returnObj2 = new JSONObject();
                        addDevice(returnObj2, bluetoothGatt.getDevice());
                        addProperty(returnObj2, "error", "requestConnectPriority");
                        addProperty(returnObj2, "message", "Request connection priority not set");
                        callbackContext.error(returnObj2);
                        return;
                    }
                    if (priority.equals("low")) {
                        androidPriority = 2;
                    } else if (priority.equals("balanced")) {
                        androidPriority = 0;
                    } else if (priority.equals("high")) {
                        androidPriority = 1;
                    } else {
                        JSONObject returnObj3 = new JSONObject();
                        addDevice(returnObj3, bluetoothGatt.getDevice());
                        addProperty(returnObj3, "error", "requestConnectPriority");
                        addProperty(returnObj3, "message", "Request connection priority is invalid");
                        callbackContext.error(returnObj3);
                        return;
                    }
                    boolean result = bluetoothGatt.requestConnectionPriority(androidPriority);
                    if (!result) {
                        JSONObject returnObj4 = new JSONObject();
                        addDevice(returnObj4, bluetoothGatt.getDevice());
                        addProperty(returnObj4, "error", "requestConnectPriority");
                        addProperty(returnObj4, "message", "Request connection priority failed");
                        callbackContext.error(returnObj4);
                        return;
                    }
                    JSONObject returnObj5 = new JSONObject();
                    addProperty(returnObj5, NotificationCompat.CATEGORY_STATUS, "connectionPriorityRequested");
                    addDevice(returnObj5, bluetoothGatt.getDevice());
                    callbackContext.success(returnObj5);
                }
            }
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onDestroy() {
        super.onDestroy();
        if (this.isReceiverRegistered) {
            this.cordova.getActivity().unregisterReceiver(this.mReceiver);
        }
        if (this.isBondReceiverRegistered) {
            this.cordova.getActivity().unregisterReceiver(this.mBondReceiver);
        }
    }

    @Override // org.apache.cordova.CordovaPlugin
    public void onActivityResult(int requestCode, int resultCode, Intent intent) throws JSONException {
        if (requestCode == 59627) {
            if (this.initCallbackContext != null && !this.bluetoothAdapter.isEnabled()) {
                JSONObject returnObj = new JSONObject();
                addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "disabled");
                addProperty(returnObj, "message", "Bluetooth not enabled");
                PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, returnObj);
                pluginResult.setKeepCallback(true);
                this.initCallbackContext.sendPluginResult(pluginResult);
                return;
            }
            return;
        }
        if (requestCode == 59629 && this.locationCallback != null) {
            JSONObject returnObj2 = new JSONObject();
            addProperty(returnObj2, "requestLocation", Boolean.valueOf(isLocationEnabled()));
            this.locationCallback.success(returnObj2);
            this.locationCallback = null;
        }
    }

    private void createScanCallback() {
        this.scanCallback = new ScanCallback() { // from class: com.randdusing.bluetoothle.BluetoothLePlugin.4
            @Override // android.bluetooth.le.ScanCallback
            public void onBatchScanResults(List<ScanResult> results) {
                if (BluetoothLePlugin.this.scanCallbackContext == null) {
                }
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int errorCode) {
                synchronized (BluetoothLePlugin.this) {
                    if (BluetoothLePlugin.this.scanCallbackContext != null) {
                        JSONObject returnObj = new JSONObject();
                        BluetoothLePlugin.this.addProperty(returnObj, "error", "startScan");
                        if (errorCode == 1) {
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Scan already started");
                        } else if (errorCode == 2) {
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Application registration failed");
                        } else if (errorCode == 4) {
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Feature unsupported");
                        } else if (errorCode == 3) {
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Internal error");
                        } else {
                            BluetoothLePlugin.this.addProperty(returnObj, "message", "Scan failed to start");
                        }
                        BluetoothLePlugin.this.scanCallbackContext.error(returnObj);
                        BluetoothLePlugin.this.scanCallbackContext = null;
                    }
                }
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int callbackType, ScanResult result) {
                synchronized (BluetoothLePlugin.this) {
                    if (BluetoothLePlugin.this.scanCallbackContext != null) {
                        JSONObject returnObj = new JSONObject();
                        BluetoothLePlugin.this.addDevice(returnObj, result.getDevice());
                        BluetoothLePlugin.this.addProperty(returnObj, "rssi", Integer.valueOf(result.getRssi()));
                        BluetoothLePlugin.this.addPropertyBytes(returnObj, "advertisement", result.getScanRecord().getBytes());
                        BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "scanResult");
                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, returnObj);
                        pluginResult.setKeepCallback(true);
                        BluetoothLePlugin.this.scanCallbackContext.sendPluginResult(pluginResult);
                    }
                }
            }
        };
    }

    private void createAdvertiseCallback() {
        this.advertiseCallback = new AdvertiseCallback() { // from class: com.randdusing.bluetoothle.BluetoothLePlugin.5
            @Override // android.bluetooth.le.AdvertiseCallback
            public void onStartFailure(int errorCode) throws JSONException {
                BluetoothLePlugin.this.isAdvertising = false;
                if (BluetoothLePlugin.this.advertiseCallbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addProperty(returnObj, "error", "startAdvertising");
                    if (errorCode == 3) {
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Already started");
                    } else if (errorCode == 1) {
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Too large data");
                    } else if (errorCode == 5) {
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Feature unsupported");
                    } else if (errorCode == 4) {
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Internal error");
                    } else if (errorCode == 2) {
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Too many advertisers");
                    } else {
                        BluetoothLePlugin.this.addProperty(returnObj, "message", "Advertising error");
                    }
                    BluetoothLePlugin.this.advertiseCallbackContext.error(returnObj);
                    BluetoothLePlugin.this.advertiseCallbackContext = null;
                }
            }

            @Override // android.bluetooth.le.AdvertiseCallback
            public void onStartSuccess(AdvertiseSettings settingsInEffect) throws JSONException {
                BluetoothLePlugin.this.isAdvertising = true;
                if (BluetoothLePlugin.this.advertiseCallbackContext != null) {
                    JSONObject returnObj = new JSONObject();
                    BluetoothLePlugin.this.addProperty(returnObj, "mode", Integer.valueOf(settingsInEffect.getMode()));
                    BluetoothLePlugin.this.addProperty(returnObj, "timeout", Integer.valueOf(settingsInEffect.getTimeout()));
                    BluetoothLePlugin.this.addProperty(returnObj, "txPowerLevel", Integer.valueOf(settingsInEffect.getTxPowerLevel()));
                    BluetoothLePlugin.this.addProperty(returnObj, "isConnectable", Boolean.valueOf(settingsInEffect.isConnectable()));
                    BluetoothLePlugin.this.addProperty(returnObj, NotificationCompat.CATEGORY_STATUS, "advertisingStarted");
                    BluetoothLePlugin.this.advertiseCallbackContext.success(returnObj);
                    BluetoothLePlugin.this.advertiseCallbackContext = null;
                }
            }
        };
    }

    private String formatUuid(UUID uuid) {
        String uuidString = uuid.toString();
        if (uuidString.startsWith("0000") && uuidString.endsWith("-0000-1000-8000-00805f9b34fb")) {
            return uuidString.substring(4, 8);
        }
        return uuidString;
    }

    private UUID getUUID(String value) {
        if (value == null) {
            return null;
        }
        if (value.length() == 4) {
            value = "0000" + value + "-0000-1000-8000-00805f9b34fb";
        }
        try {
            UUID uuid = UUID.fromString(value);
            return uuid;
        } catch (Exception e) {
            return null;
        }
    }

    private BluetoothGattService getService(BluetoothGatt bluetoothGatt, JSONObject obj) {
        UUID uuid = getUUID(obj.optString(NotificationCompat.CATEGORY_SERVICE, null));
        BluetoothGattService service = bluetoothGatt.getService(uuid);
        if (service == null) {
            return null;
        }
        return service;
    }

    private BluetoothGattCharacteristic getCharacteristic(JSONObject obj, BluetoothGattService service) {
        UUID uuid = getUUID(obj.optString("characteristic", null));
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid);
        if (characteristic == null) {
            return null;
        }
        return characteristic;
    }

    private BluetoothGattDescriptor getDescriptor(JSONObject obj, BluetoothGattCharacteristic characteristic) {
        UUID uuid = getUUID(obj.optString("descriptor", null));
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(uuid);
        if (descriptor == null) {
            return null;
        }
        return descriptor;
    }

    private void queueStart() throws JSONException {
        if (this.queue.size() <= 1) {
            queueNext();
        }
    }

    private void queueNext() throws JSONException {
        boolean result;
        Operation operation = this.queue.peek();
        if (operation.type.equals("read")) {
            result = readAction(operation);
        } else if (operation.type.equals("write")) {
            result = writeAction(operation);
        } else if (operation.type.equals("readDescriptor")) {
            result = readDescriptorAction(operation);
        } else if (operation.type.equals("writeDescriptor")) {
            result = writeDescriptorAction(operation);
        } else if (operation.type.equals("subscribe")) {
            result = subscribeAction(operation);
        } else {
            result = unsubscribeAction(operation);
        }
        if (!result) {
            queueRemove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queueRemove() throws JSONException {
        if (this.queue.size() != 0) {
            this.queue.poll();
            if (this.queue.size() != 0) {
                queueNext();
            }
        }
    }

    /* JADX WARN: Generic types in debug info not equals: java.lang.Object != java.util.HashMap<java.lang.Object, java.lang.Object> */
    private HashMap<Object, Object> EnsureCallback(UUID characteristicUuid, HashMap<Object, Object> connection) {
        HashMap<Object, Object> characteristicCallbacks = (HashMap) connection.get(characteristicUuid);
        if (characteristicCallbacks != null) {
            return characteristicCallbacks;
        }
        HashMap<Object, Object> characteristicCallbacks2 = new HashMap<>();
        connection.put(characteristicUuid, characteristicCallbacks2);
        return characteristicCallbacks2;
    }

    private void AddCallback(UUID characteristicUuid, HashMap<Object, Object> connection, String operationType, CallbackContext callbackContext) {
        HashMap<Object, Object> characteristicCallbacks = EnsureCallback(characteristicUuid, connection);
        characteristicCallbacks.put(operationType, callbackContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CallbackContext GetCallback(UUID characteristicUuid, HashMap<Object, Object> connection, String operationType) {
        HashMap<Object, Object> characteristicCallbacks = (HashMap) connection.get(characteristicUuid);
        if (characteristicCallbacks == null) {
            return null;
        }
        return (CallbackContext) characteristicCallbacks.get(operationType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CallbackContext[] GetCallbacks(HashMap<Object, Object> connection) {
        ArrayList<CallbackContext> callbacks = new ArrayList<>();
        for (Object key : connection.keySet()) {
            if (key instanceof String) {
                if (key.equals("discover") || key.equals("rssi") || key.equals("mtu")) {
                    CallbackContext callback = (CallbackContext) connection.get(key);
                    if (callback != null) {
                        callbacks.add(callback);
                    }
                }
            } else if (key instanceof UUID) {
                HashMap<Object, Object> characteristic = (HashMap) connection.get(key);
                GetMoreCallbacks(characteristic, callbacks);
            }
        }
        return (CallbackContext[]) callbacks.toArray(new CallbackContext[callbacks.size()]);
    }

    private void GetMoreCallbacks(HashMap<Object, Object> lower, ArrayList<CallbackContext> callbacks) {
        CallbackContext callback;
        for (Object key : lower.keySet()) {
            if (key instanceof UUID) {
                HashMap<Object, Object> next = (HashMap) lower.get(key);
                GetMoreCallbacks(next, callbacks);
            } else if ((key instanceof String) && (callback = (CallbackContext) lower.get(key)) != null) {
                callbacks.add(callback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RemoveCallback(UUID characteristicUuid, HashMap<Object, Object> connection, String operationType) {
        HashMap<Object, Object> characteristicCallbacks = (HashMap) connection.get(characteristicUuid);
        if (characteristicCallbacks != null) {
            characteristicCallbacks.remove(operationType);
        }
    }

    /* JADX WARN: Generic types in debug info not equals: java.lang.Object != java.util.HashMap<java.lang.Object, java.lang.Object> */
    private HashMap<Object, Object> EnsureDescriptorCallback(UUID descriptorUuid, UUID characteristicUuid, HashMap<Object, Object> connection) {
        HashMap<Object, Object> characteristicCallbacks = EnsureCallback(characteristicUuid, connection);
        HashMap<Object, Object> descriptorCallbacks = (HashMap) characteristicCallbacks.get(descriptorUuid);
        if (descriptorCallbacks != null) {
            return descriptorCallbacks;
        }
        HashMap<Object, Object> descriptorCallbacks2 = new HashMap<>();
        characteristicCallbacks.put(descriptorUuid, descriptorCallbacks2);
        return descriptorCallbacks2;
    }

    private void AddDescriptorCallback(UUID descriptorUuid, UUID characteristicUuid, HashMap<Object, Object> connection, String operationType, CallbackContext callbackContext) {
        HashMap<Object, Object> descriptorCallbacks = EnsureDescriptorCallback(descriptorUuid, characteristicUuid, connection);
        descriptorCallbacks.put(operationType, callbackContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CallbackContext GetDescriptorCallback(UUID descriptorUuid, UUID characteristicUuid, HashMap<Object, Object> connection, String operationType) {
        HashMap<Object, Object> descriptorCallbacks;
        HashMap<Object, Object> characteristicCallbacks = (HashMap) connection.get(characteristicUuid);
        if (characteristicCallbacks == null || (descriptorCallbacks = (HashMap) characteristicCallbacks.get(descriptorUuid)) == null) {
            return null;
        }
        return (CallbackContext) descriptorCallbacks.get(operationType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RemoveDescriptorCallback(UUID descriptorUuid, UUID characteristicUuid, HashMap<Object, Object> connection, String operationType) {
        HashMap<Object, Object> descriptorCallbacks;
        HashMap<Object, Object> characteristicCallbacks = (HashMap) connection.get(characteristicUuid);
        if (characteristicCallbacks != null && (descriptorCallbacks = (HashMap) characteristicCallbacks.get(descriptorUuid)) != null) {
            descriptorCallbacks.remove(descriptorUuid);
        }
    }

    private boolean isNotInitialized(CallbackContext callbackContext, boolean checkIsNotEnabled) throws JSONException {
        if (this.bluetoothAdapter == null) {
            JSONObject returnObj = new JSONObject();
            addProperty(returnObj, "error", "initialize");
            addProperty(returnObj, "message", "Bluetooth not initialized");
            callbackContext.error(returnObj);
            return true;
        }
        if (checkIsNotEnabled) {
            return isNotEnabled(callbackContext);
        }
        return false;
    }

    private boolean isNotEnabled(CallbackContext callbackContext) throws JSONException {
        if (this.bluetoothAdapter.isEnabled()) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "enable");
        addProperty(returnObj, "message", "Bluetooth not enabled");
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isNotDisabled(CallbackContext callbackContext) throws JSONException {
        if (!this.bluetoothAdapter.isEnabled()) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "disable");
        addProperty(returnObj, "message", "Bluetooth not disabled");
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isNotArgsObject(JSONObject obj, CallbackContext callbackContext) throws JSONException {
        if (obj != null) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "arguments");
        addProperty(returnObj, "message", "Argument object not found");
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isNotAddress(String address, CallbackContext callbackContext) throws JSONException {
        if (address != null) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "connect");
        addProperty(returnObj, "message", "No device address");
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isNotService(BluetoothGattService service, BluetoothDevice device, CallbackContext callbackContext) throws JSONException {
        if (service != null) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", NotificationCompat.CATEGORY_SERVICE);
        addProperty(returnObj, "message", "Service not found");
        addDevice(returnObj, device);
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isNotCharacteristic(BluetoothGattCharacteristic characteristic, BluetoothDevice device, CallbackContext callbackContext) throws JSONException {
        if (characteristic != null) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "characteristic");
        addProperty(returnObj, "message", "Characteristic not found");
        addDevice(returnObj, device);
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isNotDescriptor(BluetoothGattDescriptor descriptor, BluetoothDevice device, CallbackContext callbackContext) throws JSONException {
        if (descriptor != null) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "descriptor");
        addProperty(returnObj, "message", "Descriptor not found");
        addDevice(returnObj, device);
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isNotDisconnected(HashMap<Object, Object> connection, BluetoothDevice device, CallbackContext callbackContext) throws JSONException {
        int state = Integer.valueOf(connection.get("state").toString()).intValue();
        if (state == 0) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "isNotDisconnected");
        addProperty(returnObj, "message", "Device isn't disconnected");
        addDevice(returnObj, device);
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isDisconnected(HashMap<Object, Object> connection, BluetoothDevice device, CallbackContext callbackContext) throws JSONException {
        int state = Integer.valueOf(connection.get("state").toString()).intValue();
        if (state != 0) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "isDisconnected");
        addProperty(returnObj, "message", "Device is disconnected");
        addDevice(returnObj, device);
        callbackContext.error(returnObj);
        return true;
    }

    private boolean isNotConnected(HashMap<Object, Object> connection, BluetoothDevice device, CallbackContext callbackContext) throws JSONException {
        int state = Integer.valueOf(connection.get("state").toString()).intValue();
        if (state == 2) {
            return false;
        }
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "isNotConnected");
        addProperty(returnObj, "message", "Device isn't connected");
        addDevice(returnObj, device);
        callbackContext.error(returnObj);
        return true;
    }

    private boolean wasConnected(String address, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection = this.connections.get(address);
        if (connection == null) {
            return false;
        }
        BluetoothGatt peripheral = (BluetoothGatt) connection.get("peripheral");
        BluetoothDevice device = peripheral.getDevice();
        JSONObject returnObj = new JSONObject();
        addProperty(returnObj, "error", "connect");
        addProperty(returnObj, "message", "Device previously connected, reconnect or close for new device");
        addDevice(returnObj, device);
        callbackContext.error(returnObj);
        return true;
    }

    private HashMap<Object, Object> wasNeverConnected(String address, CallbackContext callbackContext) throws JSONException {
        HashMap<Object, Object> connection = this.connections.get(address);
        if (connection == null) {
            JSONObject returnObj = new JSONObject();
            addProperty(returnObj, "error", "neverConnected");
            addProperty(returnObj, "message", "Never connected to device");
            addProperty(returnObj, "address", address);
            callbackContext.error(returnObj);
            return null;
        }
        return connection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDevice(JSONObject returnObj, BluetoothDevice device) throws JSONException {
        addProperty(returnObj, "address", device.getAddress());
        addProperty(returnObj, "name", device.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addService(JSONObject returnObj, BluetoothGattService service) throws JSONException {
        addProperty(returnObj, NotificationCompat.CATEGORY_SERVICE, formatUuid(service.getUuid()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCharacteristic(JSONObject returnObj, BluetoothGattCharacteristic characteristic) throws JSONException {
        addService(returnObj, characteristic.getService());
        addProperty(returnObj, "characteristic", formatUuid(characteristic.getUuid()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDescriptor(JSONObject returnObj, BluetoothGattDescriptor descriptor) throws JSONException {
        addCharacteristic(returnObj, descriptor.getCharacteristic());
        addProperty(returnObj, "descriptor", formatUuid(descriptor.getUuid()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProperty(JSONObject obj, String key, Object value) throws JSONException {
        try {
            if (value == null) {
                obj.put(key, JSONObject.NULL);
            } else {
                obj.put(key, value);
            }
        } catch (JSONException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPropertyBytes(JSONObject obj, String key, byte[] bytes) throws JSONException {
        String string = Base64.encodeToString(bytes, 2);
        addProperty(obj, key, string);
    }

    private JSONObject getArgsObject(JSONArray args) {
        if (args.length() == 1) {
            try {
                return args.getJSONObject(0);
            } catch (JSONException e) {
            }
        }
        return null;
    }

    private byte[] getPropertyBytes(JSONObject obj, String key) {
        String string = obj.optString(key, null);
        if (string == null) {
            return null;
        }
        byte[] bytes = Base64.decode(string, 2);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return bytes;
    }

    private UUID[] getServiceUuids(JSONObject obj) {
        if (obj == null) {
            return new UUID[0];
        }
        JSONArray array = obj.optJSONArray("services");
        if (array == null) {
            return new UUID[0];
        }
        ArrayList<UUID> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            String value = array.optString(i, null);
            if (value != null) {
                if (value.length() == 4) {
                    value = "0000" + value + "-0000-1000-8000-00805f9b34fb";
                }
                try {
                    UUID uuid = UUID.fromString(value);
                    arrayList.add(uuid);
                } catch (Exception e) {
                }
            }
        }
        UUID[] uuids = new UUID[arrayList.size()];
        return (UUID[]) arrayList.toArray(uuids);
    }

    private String getAddress(JSONObject obj) {
        String address = obj.optString("address", null);
        if (address != null && BluetoothAdapter.checkBluetoothAddress(address)) {
            return address;
        }
        return null;
    }

    private boolean getRequest(JSONObject obj) {
        return obj.optBoolean("request", false);
    }

    private boolean getStatusReceiver(JSONObject obj) {
        return obj.optBoolean("statusReceiver", true);
    }

    private int getWriteType(JSONObject obj) {
        String writeType = obj.optString("type", null);
        return (writeType == null || !writeType.equals("noResponse")) ? 2 : 1;
    }

    private int getMtu(JSONObject obj) {
        int mtu = obj.optInt("mtu");
        if (mtu == 0) {
            return 23;
        }
        return mtu;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getDiscovery(BluetoothGatt bluetoothGatt) throws JSONException {
        JSONObject deviceObject = new JSONObject();
        BluetoothDevice device = bluetoothGatt.getDevice();
        addProperty(deviceObject, NotificationCompat.CATEGORY_STATUS, "discovered");
        addDevice(deviceObject, device);
        JSONArray servicesArray = new JSONArray();
        List<BluetoothGattService> services = bluetoothGatt.getServices();
        for (BluetoothGattService service : services) {
            JSONObject serviceObject = new JSONObject();
            addProperty(serviceObject, "uuid", formatUuid(service.getUuid()));
            JSONArray characteristicsArray = new JSONArray();
            List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
            for (BluetoothGattCharacteristic characteristic : characteristics) {
                JSONObject characteristicObject = new JSONObject();
                addProperty(characteristicObject, "uuid", formatUuid(characteristic.getUuid()));
                addProperty(characteristicObject, "properties", getProperties(characteristic));
                addProperty(characteristicObject, "permissions", getPermissions(characteristic));
                JSONArray descriptorsArray = new JSONArray();
                List<BluetoothGattDescriptor> descriptors = characteristic.getDescriptors();
                for (BluetoothGattDescriptor descriptor : descriptors) {
                    JSONObject descriptorObject = new JSONObject();
                    addProperty(descriptorObject, "uuid", formatUuid(descriptor.getUuid()));
                    addProperty(descriptorObject, "permissions", getPermissions(descriptor));
                    descriptorsArray.put(descriptorObject);
                }
                addProperty(characteristicObject, "descriptors", descriptorsArray);
                characteristicsArray.put(characteristicObject);
            }
            addProperty(serviceObject, "characteristics", characteristicsArray);
            servicesArray.put(serviceObject);
        }
        addProperty(deviceObject, "services", servicesArray);
        return deviceObject;
    }

    private JSONObject getProperties(BluetoothGattCharacteristic characteristic) throws JSONException {
        int properties = characteristic.getProperties();
        JSONObject propertiesObject = new JSONObject();
        if ((properties & 1) == 1) {
            addProperty(propertiesObject, "broadcast", true);
        }
        if ((properties & 2) == 2) {
            addProperty(propertiesObject, "read", true);
        }
        if ((properties & 4) == 4) {
            addProperty(propertiesObject, "writeWithoutResponse", true);
        }
        if ((properties & 8) == 8) {
            addProperty(propertiesObject, "write", true);
        }
        if ((properties & 16) == 16) {
            addProperty(propertiesObject, "notify", true);
        }
        if ((properties & 32) == 32) {
            addProperty(propertiesObject, "indicate", true);
        }
        if ((properties & 64) == 64) {
            addProperty(propertiesObject, "authenticatedSignedWrites", true);
        }
        if ((properties & 128) == 128) {
            addProperty(propertiesObject, "extendedProperties", true);
        }
        if ((properties & 256) == 256) {
            addProperty(propertiesObject, "notifyEncryptionRequired", true);
        }
        if ((properties & 512) == 512) {
            addProperty(propertiesObject, "indicateEncryptionRequired", true);
        }
        return propertiesObject;
    }

    private JSONObject getPermissions(BluetoothGattCharacteristic characteristic) throws JSONException {
        int permissions = characteristic.getPermissions();
        JSONObject permissionsObject = new JSONObject();
        if ((permissions & 1) == 1) {
            addProperty(permissionsObject, "read", true);
        }
        if ((permissions & 2) == 2) {
            addProperty(permissionsObject, "readEncrypted", true);
        }
        if ((permissions & 4) == 4) {
            addProperty(permissionsObject, "readEncryptedMITM", true);
        }
        if ((permissions & 16) == 16) {
            addProperty(permissionsObject, "write", true);
        }
        if ((permissions & 32) == 32) {
            addProperty(permissionsObject, "writeEncrypted", true);
        }
        if ((permissions & 64) == 64) {
            addProperty(permissionsObject, "writeEncryptedMITM", true);
        }
        if ((permissions & 128) == 128) {
            addProperty(permissionsObject, "writeSigned", true);
        }
        if ((permissions & 256) == 256) {
            addProperty(permissionsObject, "writeSignedMITM", true);
        }
        return permissionsObject;
    }

    private JSONObject getPermissions(BluetoothGattDescriptor descriptor) throws JSONException {
        int permissions = descriptor.getPermissions();
        JSONObject permissionsObject = new JSONObject();
        if ((permissions & 1) == 1) {
            addProperty(permissionsObject, "read", true);
        }
        if ((permissions & 2) == 2) {
            addProperty(permissionsObject, "readEncrypted", true);
        }
        if ((permissions & 4) == 4) {
            addProperty(permissionsObject, "readEncryptedMITM", true);
        }
        if ((permissions & 16) == 16) {
            addProperty(permissionsObject, "write", true);
        }
        if ((permissions & 32) == 32) {
            addProperty(permissionsObject, "writeEncrypted", true);
        }
        if ((permissions & 64) == 64) {
            addProperty(permissionsObject, "writeEncryptedMITM", true);
        }
        if ((permissions & 128) == 128) {
            addProperty(permissionsObject, "writeSigned", true);
        }
        if ((permissions & 256) == 256) {
            addProperty(permissionsObject, "writeSignedMITM", true);
        }
        return permissionsObject;
    }
}
