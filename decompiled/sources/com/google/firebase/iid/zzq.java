package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes.dex */
public final class zzq {
    private static zzq zzmma;
    private final SimpleArrayMap<String, String> zzmmb = new SimpleArrayMap<>();
    private Boolean zzmmc = null;

    @VisibleForTesting
    final Queue<Intent> zzmmd = new LinkedList();

    @VisibleForTesting
    private Queue<Intent> zzmme = new LinkedList();

    private zzq() {
    }

    public static PendingIntent zza(Context context, int i, Intent intent, int i2) {
        return zza(context, 0, "com.google.firebase.INSTANCE_ID_EVENT", intent, 134217728);
    }

    private static PendingIntent zza(Context context, int i, String str, Intent intent, int i2) {
        Intent intent2 = new Intent(context, (Class<?>) FirebaseInstanceIdInternalReceiver.class);
        intent2.setAction(str);
        intent2.putExtra("wrapped_intent", intent);
        return PendingIntent.getBroadcast(context, i, intent2, i2);
    }

    public static PendingIntent zzb(Context context, int i, Intent intent, int i2) {
        return zza(context, i, "com.google.firebase.MESSAGING_EVENT", intent, 1073741824);
    }

    public static synchronized zzq zzbys() {
        if (zzmma == null) {
            zzmma = new zzq();
        }
        return zzmma;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzf(android.content.Context r7, android.content.Intent r8) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzq.zzf(android.content.Context, android.content.Intent):int");
    }

    public final int zza(Context context, String str, Intent intent) {
        switch (str) {
            case "com.google.firebase.INSTANCE_ID_EVENT":
                this.zzmmd.offer(intent);
                break;
            case "com.google.firebase.MESSAGING_EVENT":
                this.zzmme.offer(intent);
                break;
            default:
                String strValueOf = String.valueOf(str);
                Log.w("FirebaseInstanceId", strValueOf.length() != 0 ? "Unknown service action: ".concat(strValueOf) : new String("Unknown service action: "));
                return 500;
        }
        Intent intent2 = new Intent(str);
        intent2.setPackage(context.getPackageName());
        return zzf(context, intent2);
    }

    public final Intent zzbyt() {
        return this.zzmme.poll();
    }

    public final void zze(Context context, Intent intent) {
        zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent);
    }
}
