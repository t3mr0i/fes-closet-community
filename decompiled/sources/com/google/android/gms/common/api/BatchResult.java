package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class BatchResult implements Result {
    private final Status mStatus;
    private final PendingResult<?>[] zzfgm;

    BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.mStatus = status;
        this.zzfgm = pendingResultArr;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.mStatus;
    }

    public final <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        zzbp.zzb(batchResultToken.mId < this.zzfgm.length, "The result token does not belong to this batch");
        return (R) this.zzfgm[batchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}
