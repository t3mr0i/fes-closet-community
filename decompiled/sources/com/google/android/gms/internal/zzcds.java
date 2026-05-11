package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement;

/* loaded from: classes.dex */
final class zzcds implements Runnable {
    private /* synthetic */ String zziag;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ String zziup;
    private /* synthetic */ String zziuq;
    private /* synthetic */ long zziur;

    zzcds(zzcdb zzcdbVar, String str, String str2, String str3, long j) {
        this.zziuj = zzcdbVar;
        this.zziup = str;
        this.zziag = str2;
        this.zziuq = str3;
        this.zziur = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zziup == null) {
            this.zziuj.zzikh.zzaue().zza(this.zziag, (AppMeasurement.zzb) null);
            return;
        }
        AppMeasurement.zzb zzbVar = new AppMeasurement.zzb();
        zzbVar.zzikm = this.zziuq;
        zzbVar.zzikn = this.zziup;
        zzbVar.zziko = this.zziur;
        this.zziuj.zzikh.zzaue().zza(this.zziag, zzbVar);
    }
}
