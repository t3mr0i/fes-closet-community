package com.google.android.gms.internal;

import android.content.Context;

/* loaded from: classes.dex */
public final class zzbeb {
    private static Context zzfzp;
    private static Boolean zzfzq;

    public static synchronized boolean zzcp(Context context) {
        boolean zBooleanValue;
        Context applicationContext = context.getApplicationContext();
        if (zzfzp == null || zzfzq == null || zzfzp != applicationContext) {
            zzfzq = null;
            if (com.google.android.gms.common.util.zzp.isAtLeastO()) {
                zzfzq = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
            } else {
                try {
                    context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                    zzfzq = true;
                } catch (ClassNotFoundException e) {
                    zzfzq = false;
                }
            }
            zzfzp = applicationContext;
            zBooleanValue = zzfzq.booleanValue();
        } else {
            zBooleanValue = zzfzq.booleanValue();
        }
        return zBooleanValue;
    }
}
