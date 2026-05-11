package jp.co.sony.fes.nativebridge;

import android.app.NotificationManager;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sony.cdp.plugin.nativebridge.Gate;
import com.sony.cdp.plugin.nativebridge.MethodContext;
import java.io.IOException;
import java.util.ArrayList;
import jp.co.sony.fes.MainActivity;
import jp.co.sony.fes.services.FESMessagingService;
import jp.co.sony.fes.services.FESTokenService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NotificationHandler extends Gate implements MainActivity.OnTapNotificationListener {
    private static final String TAG = "NotificationHandler";
    private MethodContext mContext;

    public void startListening(final JSONObject options) {
        this.mContext = getContext();
        try {
            final String token = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "Acquired Token from Firebase Service: " + token);
            Thread thread = new Thread() { // from class: jp.co.sony.fes.nativebridge.NotificationHandler.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() throws JSONException, IOException {
                    FESTokenService.sendRegistrationToServer(token, options);
                }
            };
            thread.start();
            ((MainActivity) this.cordova.getActivity()).setTapNotificationListener(this);
            processQueuedNotifications();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void clear() {
        NotificationManager mNotificationManager = (NotificationManager) this.cordova.getActivity().getSystemService("notification");
        mNotificationManager.cancel(FESMessagingService.FES_NOTIFICATION_ID);
    }

    @Override // jp.co.sony.fes.MainActivity.OnTapNotificationListener
    public void onTapNotification(String campaign) {
        Log.d(TAG, "Recevied Tapped notification campaign: " + campaign);
        notifyParams(this.mContext, campaign);
    }

    private void processQueuedNotifications() {
        ArrayList<String> notifications = ((MainActivity) this.cordova.getActivity()).getQueuedNotifications();
        for (int i = 0; i < notifications.size(); i++) {
            String campaign = notifications.get(i);
            Log.d(TAG, "Processing queued up notification: campaign = " + campaign);
            notifyParams(this.mContext, campaign);
        }
    }
}
