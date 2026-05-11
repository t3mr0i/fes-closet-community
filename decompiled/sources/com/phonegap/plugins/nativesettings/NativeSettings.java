package com.phonegap.plugins.nativesettings;

import android.content.Intent;
import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
public class NativeSettings extends CordovaPlugin {
    @Override // org.apache.cordova.CordovaPlugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Intent intent;
        PluginResult.Status status = PluginResult.Status.OK;
        Uri packageUri = Uri.parse("package:" + this.cordova.getActivity().getPackageName());
        String action2 = args.getString(0);
        if (action2.equals("accessibility")) {
            intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
        } else if (action2.equals("account")) {
            intent = new Intent("android.settings.ADD_ACCOUNT_SETTINGS");
        } else if (action2.equals("airplane_mode")) {
            intent = new Intent("android.settings.AIRPLANE_MODE_SETTINGS");
        } else if (action2.equals("apn")) {
            intent = new Intent("android.settings.APN_SETTINGS");
        } else if (action2.equals("application_details")) {
            intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", packageUri);
        } else if (action2.equals("application_development")) {
            intent = new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS");
        } else if (action2.equals("application")) {
            intent = new Intent("android.settings.APPLICATION_SETTINGS");
        } else if (action2.equals("bluetooth")) {
            intent = new Intent("android.settings.BLUETOOTH_SETTINGS");
        } else if (action2.equals("captioning")) {
            intent = new Intent("android.settings.CAPTIONING_SETTINGS");
        } else if (action2.equals("cast")) {
            intent = new Intent("android.settings.CAST_SETTINGS");
        } else if (action2.equals("data_roaming")) {
            intent = new Intent("android.settings.DATA_ROAMING_SETTINGS");
        } else if (action2.equals("date")) {
            intent = new Intent("android.settings.DATE_SETTINGS");
        } else if (action2.equals("about")) {
            intent = new Intent("android.settings.DEVICE_INFO_SETTINGS");
        } else if (action2.equals("display")) {
            intent = new Intent("android.settings.DISPLAY_SETTINGS");
        } else if (action2.equals("dream")) {
            intent = new Intent("android.settings.DREAM_SETTINGS");
        } else if (action2.equals("home")) {
            intent = new Intent("android.settings.HOME_SETTINGS");
        } else if (action2.equals("keyboard")) {
            intent = new Intent("android.settings.INPUT_METHOD_SETTINGS");
        } else if (action2.equals("keyboard_subtype")) {
            intent = new Intent("android.settings.INPUT_METHOD_SUBTYPE_SETTINGS");
        } else if (action2.equals("storage")) {
            intent = new Intent("android.settings.INTERNAL_STORAGE_SETTINGS");
        } else if (action2.equals("locale")) {
            intent = new Intent("android.settings.LOCALE_SETTINGS");
        } else if (action2.equals(FirebaseAnalytics.Param.LOCATION)) {
            intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        } else if (action2.equals("manage_all_applications")) {
            intent = new Intent("android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS");
        } else if (action2.equals("manage_applications")) {
            intent = new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS");
        } else if (action2.equals("memory_card")) {
            intent = new Intent("android.settings.MEMORY_CARD_SETTINGS");
        } else if (action2.equals("network")) {
            intent = new Intent("android.settings.NETWORK_OPERATOR_SETTINGS");
        } else if (action2.equals("nfcsharing")) {
            intent = new Intent("android.settings.NFCSHARING_SETTINGS");
        } else if (action2.equals("nfc_payment")) {
            intent = new Intent("android.settings.NFC_PAYMENT_SETTINGS");
        } else if (action2.equals("nfc_settings")) {
            intent = new Intent("android.settings.NFC_SETTINGS");
        } else if (action2.equals("print")) {
            intent = new Intent("android.settings.ACTION_PRINT_SETTINGS");
        } else if (action2.equals("privacy")) {
            intent = new Intent("android.settings.PRIVACY_SETTINGS");
        } else if (action2.equals("quick_launch")) {
            intent = new Intent("android.settings.QUICK_LAUNCH_SETTINGS");
        } else if (action2.equals(FirebaseAnalytics.Event.SEARCH)) {
            intent = new Intent("android.search.action.SEARCH_SETTINGS");
        } else if (action2.equals("security")) {
            intent = new Intent("android.settings.SECURITY_SETTINGS");
        } else if (action2.equals("settings")) {
            intent = new Intent("android.settings.SETTINGS");
        } else if (action2.equals("show_regulatory_info")) {
            intent = new Intent("android.settings.SHOW_REGULATORY_INFO");
        } else if (action2.equals("sound")) {
            intent = new Intent("android.settings.SOUND_SETTINGS");
        } else if (action2.equals("store")) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.cordova.getActivity().getPackageName()));
        } else if (action2.equals("sync")) {
            intent = new Intent("android.settings.SYNC_SETTINGS");
        } else if (action2.equals("usage")) {
            intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
        } else if (action2.equals("user_dictionary")) {
            intent = new Intent("android.settings.USER_DICTIONARY_SETTINGS");
        } else if (action2.equals("voice_input")) {
            intent = new Intent("android.settings.VOICE_INPUT_SETTINGS");
        } else if (action2.equals("wifi_ip")) {
            intent = new Intent("android.settings.WIFI_IP_SETTINGS");
        } else if (action2.equals("wifi")) {
            intent = new Intent("android.settings.WIFI_SETTINGS");
        } else if (action2.equals("wireless")) {
            intent = new Intent("android.settings.WIRELESS_SETTINGS");
        } else {
            PluginResult.Status status2 = PluginResult.Status.INVALID_ACTION;
            callbackContext.sendPluginResult(new PluginResult(status2, ""));
            return false;
        }
        if (args.length() > 1 && args.getBoolean(1)) {
            intent.addFlags(268435456);
        }
        this.cordova.getActivity().startActivity(intent);
        callbackContext.sendPluginResult(new PluginResult(status, ""));
        return true;
    }
}
