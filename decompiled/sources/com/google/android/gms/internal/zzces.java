package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.measurement.AppMeasurement;

/* loaded from: classes.dex */
final class zzces implements Runnable {
    private /* synthetic */ zzceo zziwe;
    private /* synthetic */ AppMeasurement.zzb zziwg;

    zzces(zzceo zzceoVar, AppMeasurement.zzb zzbVar) {
        this.zziwe = zzceoVar;
        this.zziwg = zzbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcbo zzcboVar = this.zziwe.zzivy;
        if (zzcboVar == null) {
            this.zziwe.zzaum().zzaye().log("Failed to send current screen to service");
            return;
        }
        try {
            if (this.zziwg == null) {
                zzcboVar.zza(0L, (String) null, (String) null, this.zziwe.getContext().getPackageName());
            } else {
                zzcboVar.zza(this.zziwg.zziko, this.zziwg.zzikm, this.zziwg.zzikn, this.zziwe.getContext().getPackageName());
            }
            this.zziwe.zzww();
        } catch (RemoteException e) {
            this.zziwe.zzaum().zzaye().zzj("Failed to send current screen to the service", e);
        }
    }
}
