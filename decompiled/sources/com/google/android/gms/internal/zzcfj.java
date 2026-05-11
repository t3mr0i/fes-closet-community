package com.google.android.gms.internal;

import android.os.Build;

/* loaded from: classes.dex */
final class zzcfj implements Runnable {
    private /* synthetic */ zzcfi zziwr;

    zzcfj(zzcfi zzcfiVar) {
        this.zziwr = zzcfiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zziwr.zzdty != null) {
            if (((zzcfk) this.zziwr.zziwq.zzdtw).callServiceStopSelfResult(this.zziwr.zzdty.intValue())) {
                zzcax.zzawl();
                this.zziwr.zzirt.zzayk().zzj("Local AppMeasurementService processed last upload request. StartId", this.zziwr.zzdty);
                return;
            }
            return;
        }
        zzcax.zzawl();
        if (Build.VERSION.SDK_INT >= 24) {
            this.zziwr.zzirt.zzayk().log("AppMeasurementJobService processed last upload request.");
            ((zzcfk) this.zziwr.zziwq.zzdtw).zza(this.zziwr.zzdub, false);
        }
    }
}
