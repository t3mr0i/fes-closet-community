package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class CordovaActivity extends Activity {
    protected CordovaWebView appView;
    protected CordovaInterfaceImpl cordovaInterface;
    protected boolean immersiveMode;
    protected boolean keepRunning = true;
    protected String launchUrl;
    protected ArrayList<PluginEntry> pluginEntries;
    protected CordovaPreferences preferences;
    public static String TAG = "CordovaActivity";
    private static int ACTIVITY_STARTING = 0;
    private static int ACTIVITY_RUNNING = 1;
    private static int ACTIVITY_EXITING = 2;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) throws XmlPullParserException, IOException {
        LOG.i(TAG, "Apache Cordova native platform version 5.1.1 is starting");
        LOG.d(TAG, "CordovaActivity.onCreate()");
        loadConfig();
        if (!this.preferences.getBoolean("ShowTitle", false)) {
            getWindow().requestFeature(1);
        }
        if (this.preferences.getBoolean("SetFullscreen", false)) {
            Log.d(TAG, "The SetFullscreen configuration is deprecated in favor of Fullscreen, and will be removed in a future version.");
            this.preferences.set("Fullscreen", true);
        }
        if (this.preferences.getBoolean("Fullscreen", false)) {
            if (Build.VERSION.SDK_INT >= 19) {
                this.immersiveMode = true;
            } else {
                getWindow().setFlags(1024, 1024);
            }
        } else {
            getWindow().setFlags(2048, 2048);
        }
        super.onCreate(savedInstanceState);
        this.cordovaInterface = makeCordovaInterface();
        if (savedInstanceState != null) {
            this.cordovaInterface.restoreInstanceState(savedInstanceState);
        }
    }

    protected void init() {
        this.appView = makeWebView();
        createViews();
        if (!this.appView.isInitialized()) {
            this.appView.init(this.cordovaInterface, this.pluginEntries, this.preferences);
        }
        this.cordovaInterface.onCordovaInit(this.appView.getPluginManager());
        String volumePref = this.preferences.getString("DefaultVolumeStream", "");
        if ("media".equals(volumePref.toLowerCase(Locale.ENGLISH))) {
            setVolumeControlStream(3);
        }
    }

    protected void loadConfig() throws XmlPullParserException, IOException {
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);
        this.preferences = parser.getPreferences();
        this.preferences.setPreferencesBundle(getIntent().getExtras());
        this.launchUrl = parser.getLaunchUrl();
        this.pluginEntries = parser.getPluginEntries();
        Config.parser = parser;
    }

    protected void createViews() {
        this.appView.getView().setId(100);
        this.appView.getView().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setContentView(this.appView.getView());
        if (this.preferences.contains("BackgroundColor")) {
            int backgroundColor = this.preferences.getInteger("BackgroundColor", ViewCompat.MEASURED_STATE_MASK);
            this.appView.getView().setBackgroundColor(backgroundColor);
        }
        this.appView.getView().requestFocusFromTouch();
    }

    protected CordovaWebView makeWebView() {
        return new CordovaWebViewImpl(makeWebViewEngine());
    }

    protected CordovaWebViewEngine makeWebViewEngine() {
        return CordovaWebViewImpl.createEngine(this, this.preferences);
    }

    protected CordovaInterfaceImpl makeCordovaInterface() {
        return new CordovaInterfaceImpl(this) { // from class: org.apache.cordova.CordovaActivity.1
            @Override // org.apache.cordova.CordovaInterfaceImpl, org.apache.cordova.CordovaInterface
            public Object onMessage(String id, Object data) {
                return CordovaActivity.this.onMessage(id, data);
            }
        };
    }

    public void loadUrl(String url) {
        if (this.appView == null) {
            init();
        }
        this.keepRunning = this.preferences.getBoolean("KeepRunning", true);
        this.appView.loadUrlIntoView(url, true);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        LOG.d(TAG, "Paused the activity.");
        if (this.appView != null) {
            boolean keepRunning = this.keepRunning || this.cordovaInterface.activityResultCallback != null;
            this.appView.handlePause(keepRunning);
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.appView != null) {
            this.appView.onNewIntent(intent);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        LOG.d(TAG, "Resumed the activity.");
        if (this.appView != null) {
            getWindow().getDecorView().requestFocus();
            this.appView.handleResume(this.keepRunning);
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        LOG.d(TAG, "Stopped the activity.");
        if (this.appView != null) {
            this.appView.handleStop();
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        LOG.d(TAG, "Started the activity.");
        if (this.appView != null) {
            this.appView.handleStart();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        LOG.d(TAG, "CordovaActivity.onDestroy()");
        super.onDestroy();
        if (this.appView != null) {
            this.appView.handleDestroy();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && this.immersiveMode) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    @Override // android.app.Activity
    @SuppressLint({"NewApi"})
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        this.cordovaInterface.setActivityResultRequestCode(requestCode);
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        LOG.d(TAG, "Incoming Result. Request code = " + requestCode);
        super.onActivityResult(requestCode, resultCode, intent);
        this.cordovaInterface.onActivityResult(requestCode, resultCode, intent);
    }

    public void onReceivedError(int errorCode, final String description, final String failingUrl) {
        final String errorUrl = this.preferences.getString("errorUrl", null);
        if (errorUrl != null && !failingUrl.equals(errorUrl) && this.appView != null) {
            runOnUiThread(new Runnable() { // from class: org.apache.cordova.CordovaActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    this.appView.showWebPage(errorUrl, false, true, null);
                }
            });
        } else {
            final boolean exit = errorCode != -2;
            runOnUiThread(new Runnable() { // from class: org.apache.cordova.CordovaActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    if (exit) {
                        this.appView.getView().setVisibility(8);
                        this.displayError("Application Error", description + " (" + failingUrl + ")", "OK", exit);
                    }
                }
            });
        }
    }

    public void displayError(final String title, final String message, final String button, final boolean exit) {
        runOnUiThread(new Runnable() { // from class: org.apache.cordova.CordovaActivity.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setMessage(message);
                    dlg.setTitle(title);
                    dlg.setCancelable(false);
                    dlg.setPositiveButton(button, new DialogInterface.OnClickListener() { // from class: org.apache.cordova.CordovaActivity.4.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (exit) {
                                CordovaActivity.this.finish();
                            }
                        }
                    });
                    dlg.create();
                    dlg.show();
                } catch (Exception e) {
                    CordovaActivity.this.finish();
                }
            }
        });
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.appView != null) {
            this.appView.getPluginManager().postMessage("onCreateOptionsMenu", menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.appView != null) {
            this.appView.getPluginManager().postMessage("onPrepareOptionsMenu", menu);
            return true;
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.appView != null) {
            this.appView.getPluginManager().postMessage("onOptionsItemSelected", item);
            return true;
        }
        return true;
    }

    public Object onMessage(String id, Object data) {
        if ("onReceivedError".equals(id)) {
            JSONObject d = (JSONObject) data;
            try {
                onReceivedError(d.getInt("errorCode"), d.getString("description"), d.getString("url"));
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        if ("exit".equals(id)) {
            finish();
            return null;
        }
        return null;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        this.cordovaInterface.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        PluginManager pm;
        super.onConfigurationChanged(newConfig);
        if (this.appView != null && (pm = this.appView.getPluginManager()) != null) {
            pm.onConfigurationChanged(newConfig);
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try {
            this.cordovaInterface.onRequestPermissionResult(requestCode, permissions, grantResults);
        } catch (JSONException e) {
            LOG.d(TAG, "JSONException: Parameters fed into the method are not valid");
            e.printStackTrace();
        }
    }
}
