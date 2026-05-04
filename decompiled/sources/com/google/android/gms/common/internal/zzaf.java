package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

/* loaded from: classes.dex */
public abstract class zzaf {
    private static final Object zzfus = new Object();
    private static zzaf zzfut;

    public static zzaf zzce(Context context) {
        synchronized (zzfus) {
            if (zzfut == null) {
                zzfut = new zzah(context.getApplicationContext());
            }
        }
        return zzfut;
    }

    public final void zza(String str, String str2, int i, ServiceConnection serviceConnection, String str3) {
        zzb(new zzag(str, str2, i), serviceConnection, str3);
    }

    public final boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zzag(componentName, 129), serviceConnection, str);
    }

    protected abstract boolean zza(zzag zzagVar, ServiceConnection serviceConnection, String str);

    public final void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zzag(componentName, 129), serviceConnection, str);
    }

    protected abstract void zzb(zzag zzagVar, ServiceConnection serviceConnection, String str);
}
