package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

/* loaded from: classes.dex */
final class zza implements PendingResult.zza {
    private /* synthetic */ Batch zzfgn;

    zza(Batch batch) {
        this.zzfgn = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.zza
    public final void zzq(Status status) {
        synchronized (this.zzfgn.mLock) {
            if (this.zzfgn.isCanceled()) {
                return;
            }
            if (status.isCanceled()) {
                Batch.zza(this.zzfgn, true);
            } else if (!status.isSuccess()) {
                Batch.zzb(this.zzfgn, true);
            }
            Batch.zzb(this.zzfgn);
            if (this.zzfgn.zzfgj == 0) {
                if (this.zzfgn.zzfgl) {
                    super/*com.google.android.gms.common.api.internal.zzs*/.cancel();
                } else {
                    this.zzfgn.setResult(new BatchResult(this.zzfgn.zzfgk ? new Status(13) : Status.zzfhu, this.zzfgn.zzfgm));
                }
            }
        }
    }
}
