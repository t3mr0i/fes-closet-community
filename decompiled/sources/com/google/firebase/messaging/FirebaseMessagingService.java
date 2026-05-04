package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzq;
import java.util.Iterator;

/* loaded from: classes.dex */
public class FirebaseMessagingService extends com.google.firebase.iid.zzb {
    static boolean zzag(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return "1".equals(bundle.getString("google.c.a.e"));
    }

    static void zzq(Bundle bundle) {
        Iterator<String> it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && next.startsWith("google.c.")) {
                it.remove();
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0012  */
    @Override // com.google.firebase.iid.zzb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleIntent(android.content.Intent r6) throws java.lang.NoSuchMethodException, java.lang.InstantiationException, java.lang.ClassNotFoundException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.FirebaseMessagingService.handleIntent(android.content.Intent):void");
    }

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @WorkerThread
    public void onMessageSent(String str) {
    }

    @WorkerThread
    public void onSendError(String str, Exception exc) {
    }

    @Override // com.google.firebase.iid.zzb
    protected final Intent zzn(Intent intent) {
        return zzq.zzbys().zzbyt();
    }

    @Override // com.google.firebase.iid.zzb
    public final boolean zzo(Intent intent) throws PendingIntent.CanceledException {
        if (!"com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            return false;
        }
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra("pending_intent");
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                Log.e("FirebaseMessaging", "Notification pending intent canceled");
            }
        }
        if (zzag(intent.getExtras())) {
            zzd.zzh(this, intent);
        }
        return true;
    }
}
