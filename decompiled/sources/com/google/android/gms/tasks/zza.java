package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zza<TResult, TContinuationResult> implements zzk<TResult> {
    private final Executor zzjqr;
    private final Continuation<TResult, TContinuationResult> zzkfu;
    private final zzn<TContinuationResult> zzkfv;

    public zza(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzn<TContinuationResult> zznVar) {
        this.zzjqr = executor;
        this.zzkfu = continuation;
        this.zzkfv = zznVar;
    }

    @Override // com.google.android.gms.tasks.zzk
    public final void cancel() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.tasks.zzk
    public final void onComplete(@NonNull Task<TResult> task) {
        this.zzjqr.execute(new zzb(this, task));
    }
}
