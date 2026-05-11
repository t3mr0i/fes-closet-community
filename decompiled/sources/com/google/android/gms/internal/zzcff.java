package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;

/* loaded from: classes.dex */
final class zzcff implements Runnable {
    private /* synthetic */ zzcfb zziwo;

    zzcff(zzcfb zzcfbVar) {
        this.zziwo = zzcfbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzceo zzceoVar = this.zziwo.zziwe;
        Context context = this.zziwo.zziwe.getContext();
        zzcax.zzawl();
        zzceoVar.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
