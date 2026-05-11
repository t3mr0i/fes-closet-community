package com.google.android.gms.internal;

import android.app.job.JobParameters;

/* loaded from: classes.dex */
final class zzaow implements zzaob {
    final /* synthetic */ Integer zzdty;
    private /* synthetic */ zzamu zzdtz;
    final /* synthetic */ zzaon zzdua;
    final /* synthetic */ JobParameters zzdub;
    final /* synthetic */ zzaov zzduc;

    zzaow(zzaov zzaovVar, Integer num, zzamu zzamuVar, zzaon zzaonVar, JobParameters jobParameters) {
        this.zzduc = zzaovVar;
        this.zzdty = num;
        this.zzdtz = zzamuVar;
        this.zzdua = zzaonVar;
        this.zzdub = jobParameters;
    }

    @Override // com.google.android.gms.internal.zzaob
    public final void zzb(Throwable th) {
        this.zzduc.mHandler.post(new zzaox(this));
    }
}
