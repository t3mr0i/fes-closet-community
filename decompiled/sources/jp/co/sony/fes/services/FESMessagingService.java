package jp.co.sony.fes.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import jp.co.sony.fes.MainActivity;
import jp.co.sony.fes.R;

/* loaded from: classes.dex */
public class FESMessagingService extends FirebaseMessagingService {
    public static final String CAMPAIGN_KEY = "campaign_name";
    public static final int FES_NOTIFICATION_ID = 2558;
    public static final String MESSAGE_KEY = "msg";
    private static final String TAG = "FESMessagingService";
    public static final String TITLE_KEY = "title";

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            sendNotification(remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(Map<String, String> data) {
        int smallIcon;
        String appName;
        String messageBody = data.get("msg");
        String campaign = data.get(CAMPAIGN_KEY);
        Log.d(TAG, "Message Body: " + messageBody);
        Log.d(TAG, "Campaign: " + campaign);
        if (messageBody != null && campaign != null) {
            Intent intent = new Intent(this, (Class<?>) MainActivity.class);
            intent.putExtra("msg", messageBody);
            intent.putExtra(CAMPAIGN_KEY, campaign);
            intent.addFlags(335544320);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 134217728);
            if (Build.VERSION.SDK_INT > 23) {
                smallIcon = R.drawable.ic_fes_logo_2x1;
                appName = getString(R.string.app_name);
            } else {
                smallIcon = R.drawable.ic_fes_logo_2x1;
                appName = getString(R.string.app_name);
            }
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            registerChannel(channelId, channelName);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(2);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId).setSmallIcon(smallIcon).setContentTitle(appName).setContentText(messageBody).setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent).setCategory(NotificationCompat.CATEGORY_RECOMMENDATION);
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            notificationManager.notify(FES_NOTIFICATION_ID, notificationBuilder.build());
        }
    }

    private void registerChannel(String channelId, String channelName) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager.getNotificationChannel(channelId) == null) {
                NotificationChannel channel = new NotificationChannel(channelId, channelName, 3);
                channel.enableLights(true);
                channel.enableVibration(true);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
