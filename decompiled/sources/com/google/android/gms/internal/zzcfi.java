package com.google.android.gms.internal;

import android.app.job.JobParameters;
import java.io.IOException;

/* loaded from: classes.dex */
final class zzcfi implements Runnable {
    final /* synthetic */ Integer zzdty;
    final /* synthetic */ JobParameters zzdub;
    private /* synthetic */ zzccw zzirq;
    final /* synthetic */ zzcbw zzirt;
    final /* synthetic */ zzcfh zziwq;

    zzcfi(zzcfh zzcfhVar, zzccw zzccwVar, Integer num, zzcbw zzcbwVar, JobParameters jobParameters) {
        this.zziwq = zzcfhVar;
        this.zzirq = zzccwVar;
        this.zzdty = num;
        this.zzirt = zzcbwVar;
        this.zzdub = jobParameters;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, IOException {
        this.zzirq.zzazl();
        this.zzirq.zzi(new zzcfj(this));
        this.zzirq.zzazh();
    }
}
