package jp.co.sony.fes;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.IOException;
import java.util.ArrayList;
import jp.co.sony.fes.services.FESMessagingService;
import org.apache.cordova.CordovaActivity;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class MainActivity extends CordovaActivity {
    private static final int FCM_ERROR_REQUEST_CODE = 0;
    private static final String FCM_ERROR_TAG = "GoogleApiAvailabilityError";
    private static final String TAG = "MainActivity";
    private ArrayList<String> mNotifications = new ArrayList<>();
    private OnFocusChangeListener mFocusChangeListener = null;
    private OnTapNotificationListener mTapNotificationListener = null;

    public interface OnFocusChangeListener {
        void onFocusChanged(boolean z);
    }

    public interface OnTapNotificationListener {
        void onTapNotification(String str);
    }

    public void setFocusChangeListener(OnFocusChangeListener listener) {
        this.mFocusChangeListener = listener;
    }

    public void setTapNotificationListener(OnTapNotificationListener listener) {
        this.mTapNotificationListener = listener;
    }

    public ArrayList<String> getQueuedNotifications() {
        ArrayList<String> arr = new ArrayList<>(this.mNotifications);
        this.mNotifications.clear();
        return arr;
    }

    @Override // org.apache.cordova.CordovaActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) throws XmlPullParserException, IOException {
        Intent intent;
        String intentData;
        if (savedInstanceState != null && (intent = getIntent()) != null && (intentData = intent.getDataString()) != null && intentData.startsWith("jpcosonyfes:")) {
            intent.setData(Uri.parse("_jpcosonyfes_:"));
            setIntent(intent);
        }
        super.onCreate(savedInstanceState);
        checkIntentParams(getIntent());
        loadUrl(this.launchUrl);
        this.appView.getView().setVerticalScrollBarEnabled(true);
        this.appView.getView().setHorizontalScrollBarEnabled(false);
        this.appView.getView().setScrollBarStyle(0);
        ((WebView) this.appView.getView()).getSettings().setTextZoom(100);
        if (!isBuildForCN()) {
            ensureGoogleApiAvailability();
        }
    }

    @Override // org.apache.cordova.CordovaActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);
        checkIntentParams(intent);
    }

    @Override // org.apache.cordova.CordovaActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (this.mFocusChangeListener != null) {
            this.mFocusChangeListener.onFocusChanged(hasFocus);
        }
    }

    private void checkIntentParams(Intent intent) {
        Log.d(TAG, "Checking intent params");
        if (intent.getExtras() != null && intent.getExtras().get(FESMessagingService.CAMPAIGN_KEY) != null) {
            String campaign = intent.getExtras().get(FESMessagingService.CAMPAIGN_KEY).toString();
            Log.d(TAG, "Intent params found: Campaign Value = " + campaign);
            if (this.mTapNotificationListener != null) {
                Log.d(TAG, "Triggering onTapNotification");
                this.mTapNotificationListener.onTapNotification(campaign);
                return;
            } else {
                Log.d(TAG, "No TapNotificationListener found. Pushing to notifications queue");
                this.mNotifications.add(campaign);
                return;
            }
        }
        Log.d(TAG, "No intent params found");
    }

    private boolean isBuildForCN() {
        return this.preferences.getBoolean("isBuildForCN", false);
    }

    private boolean ensureGoogleApiAvailability() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (result == 0) {
            return true;
        }
        Log.d(TAG, "GooglePlayServices are not Available: " + result);
        Dialog dialog = googleApiAvailability.getErrorDialog(this, result, 0);
        if (dialog == null) {
            return false;
        }
        ErrorDialogFragment dialogFragment = ErrorDialogFragment.newInstance(dialog);
        dialogFragment.show(getFragmentManager(), FCM_ERROR_TAG);
        return false;
    }
}
