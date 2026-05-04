package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class zzbk implements PendingResult.zza {
    private /* synthetic */ PendingResult zzfvr;
    private /* synthetic */ TaskCompletionSource zzfvs;
    private /* synthetic */ zzbn zzfvt;
    private /* synthetic */ zzbo zzfvu;

    zzbk(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, zzbn zzbnVar, zzbo zzboVar) {
        this.zzfvr = pendingResult;
        this.zzfvs = taskCompletionSource;
        this.zzfvt = zzbnVar;
        this.zzfvu = zzboVar;
    }

    @Override // com.google.android.gms.common.api.PendingResult.zza
    public final void zzq(Status status) {
        if (!status.isSuccess()) {
            this.zzfvs.setException(this.zzfvu.zzy(status));
        } else {
            this.zzfvs.setResult(this.zzfvt.zzb(this.zzfvr.await(0L, TimeUnit.MILLISECONDS)));
        }
    }
}
