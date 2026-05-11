package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement;

/* loaded from: classes.dex */
final class zzcem implements Runnable {
    private /* synthetic */ zzcek zzivu;
    private /* synthetic */ zzcen zzivv;

    zzcem(zzcek zzcekVar, zzcen zzcenVar) {
        this.zzivu = zzcekVar;
        this.zzivv = zzcenVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zzivu.zza(this.zzivv);
        this.zzivu.zzivi = null;
        this.zzivu.zzaud().zza((AppMeasurement.zzb) null);
    }
}
