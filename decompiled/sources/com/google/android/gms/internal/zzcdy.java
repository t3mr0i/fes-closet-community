package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement;

/* loaded from: classes.dex */
final class zzcdy implements Runnable {
    private /* synthetic */ zzcdw zziux;
    private /* synthetic */ AppMeasurement.ConditionalUserProperty zziuy;

    zzcdy(zzcdw zzcdwVar, AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        this.zziux = zzcdwVar;
        this.zziuy = conditionalUserProperty;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziux.zzb(this.zziuy);
    }
}
