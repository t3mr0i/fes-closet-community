package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.zzl;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final Pattern zzmmi = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private static FirebaseMessaging zzmmj;
    private final FirebaseInstanceId zzmli;

    private FirebaseMessaging(FirebaseInstanceId firebaseInstanceId) {
        this.zzmli = firebaseInstanceId;
    }

    public static synchronized FirebaseMessaging getInstance() {
        if (zzmmj == null) {
            zzmmj = new FirebaseMessaging(FirebaseInstanceId.getInstance());
        }
        return zzmmj;
    }

    public void send(RemoteMessage remoteMessage) {
        if (TextUtils.isEmpty(remoteMessage.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
        String strZzdf = zzl.zzdf(applicationContext);
        if (strZzdf == null) {
            Log.e("FirebaseMessaging", "Google Play services package is missing. Impossible to send message");
            return;
        }
        Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        zzl.zzd(applicationContext, intent);
        intent.setPackage(strZzdf);
        intent.putExtras(remoteMessage.mBundle);
        applicationContext.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    public void subscribeToTopic(String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
            str = str.substring(8);
        }
        if (str == null || !zzmmi.matcher(str).matches()) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 55 + String.valueOf("[a-zA-Z0-9-_.~%]{1,900}").length()).append("Invalid topic name: ").append(str).append(" does not match the allowed format ").append("[a-zA-Z0-9-_.~%]{1,900}").toString());
        }
        FirebaseInstanceId firebaseInstanceId = this.zzmli;
        String strValueOf = String.valueOf("S!");
        String strValueOf2 = String.valueOf(str);
        firebaseInstanceId.zzqa(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
    }

    public void unsubscribeFromTopic(String str) {
        if (str != null && str.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
            str = str.substring(8);
        }
        if (str == null || !zzmmi.matcher(str).matches()) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 55 + String.valueOf("[a-zA-Z0-9-_.~%]{1,900}").length()).append("Invalid topic name: ").append(str).append(" does not match the allowed format ").append("[a-zA-Z0-9-_.~%]{1,900}").toString());
        }
        FirebaseInstanceId firebaseInstanceId = this.zzmli;
        String strValueOf = String.valueOf("U!");
        String strValueOf2 = String.valueOf(str);
        firebaseInstanceId.zzqa(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
    }
}
