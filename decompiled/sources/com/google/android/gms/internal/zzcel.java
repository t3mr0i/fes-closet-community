package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;

/* loaded from: classes.dex */
final class zzcel implements Runnable {
    private /* synthetic */ boolean zzivr;
    private /* synthetic */ AppMeasurement.zzb zzivs;
    private /* synthetic */ zzcen zzivt;
    private /* synthetic */ zzcek zzivu;

    zzcel(zzcek zzcekVar, boolean z, AppMeasurement.zzb zzbVar, zzcen zzcenVar) {
        this.zzivu = zzcekVar;
        this.zzivr = z;
        this.zzivs = zzbVar;
        this.zzivt = zzcenVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        if (this.zzivr && this.zzivu.zzivi != null) {
            this.zzivu.zza(this.zzivu.zzivi);
        }
        if ((this.zzivs != null && this.zzivs.zziko == this.zzivt.zziko && zzcfw.zzas(this.zzivs.zzikn, this.zzivt.zzikn) && zzcfw.zzas(this.zzivs.zzikm, this.zzivt.zzikm)) ? false : true) {
            Bundle bundle = new Bundle();
            zzcek.zza(this.zzivt, bundle);
            if (this.zzivs != null) {
                if (this.zzivs.zzikm != null) {
                    bundle.putString("_pn", this.zzivs.zzikm);
                }
                bundle.putString("_pc", this.zzivs.zzikn);
                bundle.putLong("_pi", this.zzivs.zziko);
            }
            this.zzivu.zzaua().zzc("auto", "_vs", bundle);
        }
        this.zzivu.zzivi = this.zzivt;
        this.zzivu.zzaud().zza(this.zzivt);
    }
}
