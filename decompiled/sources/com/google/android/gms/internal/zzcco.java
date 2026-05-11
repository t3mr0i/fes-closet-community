package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;

/* loaded from: classes.dex */
final class zzcco implements Runnable {
    private /* synthetic */ Context zzanz;
    private /* synthetic */ zzccw zzirq;
    private /* synthetic */ long zzirr;
    private /* synthetic */ Bundle zzirs;
    private /* synthetic */ zzcbw zzirt;

    zzcco(zzccn zzccnVar, zzccw zzccwVar, long j, Bundle bundle, Context context, zzcbw zzcbwVar) {
        this.zzirq = zzccwVar;
        this.zzirr = j;
        this.zzirs = bundle;
        this.zzanz = context;
        this.zzirt = zzcbwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcfv zzcfvVarZzah = this.zzirq.zzaug().zzah(this.zzirq.zzaub().getAppId(), "_fot");
        long jLongValue = (zzcfvVarZzah == null || !(zzcfvVarZzah.mValue instanceof Long)) ? 0L : ((Long) zzcfvVarZzah.mValue).longValue();
        long j = this.zzirr;
        long j2 = (jLongValue <= 0 || (j < jLongValue && j > 0)) ? j : jLongValue - 1;
        if (j2 > 0) {
            this.zzirs.putLong("click_timestamp", j2);
        }
        AppMeasurement.getInstance(this.zzanz).logEventInternal("auto", "_cmp", this.zzirs);
        this.zzirt.zzayk().log("Install campaign recorded");
    }
}
