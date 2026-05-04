package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.RequiresPermission;

/* loaded from: classes.dex */
public final class zzaou {
    static Object zzaqc = new Object();
    private static Boolean zzdjp;
    static zzcqh zzdtv;

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static void onReceive(Context context, Intent intent) throws PackageManager.NameNotFoundException {
        zzaon zzaonVarZzvy = zzamu.zzbg(context).zzvy();
        if (intent == null) {
            zzaonVarZzvy.zzdp("AnalyticsReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        zzaonVarZzvy.zza("Local AnalyticsReceiver got", action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean zZzbi = zzaov.zzbi(context);
            Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (zzaqc) {
                context.startService(intent2);
                if (zZzbi) {
                    try {
                        if (zzdtv == null) {
                            zzcqh zzcqhVar = new zzcqh(context, 1, "Analytics WakeLock");
                            zzdtv = zzcqhVar;
                            zzcqhVar.setReferenceCounted(false);
                        }
                        zzdtv.acquire(1000L);
                    } catch (SecurityException e) {
                        zzaonVarZzvy.zzdp("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                }
            }
        }
    }

    public static boolean zzbe(Context context) throws PackageManager.NameNotFoundException {
        com.google.android.gms.common.internal.zzbp.zzu(context);
        if (zzdjp != null) {
            return zzdjp.booleanValue();
        }
        boolean zZza = zzapd.zza(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzdjp = Boolean.valueOf(zZza);
        return zZza;
    }
}
