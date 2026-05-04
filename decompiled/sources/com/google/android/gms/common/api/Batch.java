package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.zzs;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class Batch extends zzs<BatchResult> {
    private final Object mLock;
    private int zzfgj;
    private boolean zzfgk;
    private boolean zzfgl;
    private final PendingResult<?>[] zzfgm;

    public static final class Builder {
        private GoogleApiClient zzepa;
        private List<PendingResult<?>> zzfgo = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.zzepa = googleApiClient;
        }

        public final <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zzfgo.size());
            this.zzfgo.add(pendingResult);
            return batchResultToken;
        }

        public final Batch build() {
            return new Batch(this.zzfgo, this.zzepa, null);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.mLock = new Object();
        this.zzfgj = list.size();
        this.zzfgm = new PendingResult[this.zzfgj];
        if (list.isEmpty()) {
            setResult(new BatchResult(Status.zzfhu, this.zzfgm));
            return;
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return;
            }
            PendingResult<?> pendingResult = list.get(i2);
            this.zzfgm[i2] = pendingResult;
            pendingResult.zza(new zza(this));
            i = i2 + 1;
        }
    }

    /* synthetic */ Batch(List list, GoogleApiClient googleApiClient, zza zzaVar) {
        this(list, googleApiClient);
    }

    static /* synthetic */ boolean zza(Batch batch, boolean z) {
        batch.zzfgl = true;
        return true;
    }

    static /* synthetic */ int zzb(Batch batch) {
        int i = batch.zzfgj;
        batch.zzfgj = i - 1;
        return i;
    }

    static /* synthetic */ boolean zzb(Batch batch, boolean z) {
        batch.zzfgk = true;
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zzs, com.google.android.gms.common.api.PendingResult
    public final void cancel() {
        super.cancel();
        for (PendingResult<?> pendingResult : this.zzfgm) {
            pendingResult.cancel();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzs
    /* renamed from: createFailedResult, reason: merged with bridge method [inline-methods] */
    public final BatchResult zzb(Status status) {
        return new BatchResult(status, this.zzfgm);
    }
}
