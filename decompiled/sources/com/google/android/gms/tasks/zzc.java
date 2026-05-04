package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzc<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzk<TResult> {
    private final Executor zzjqr;
    private final Continuation<TResult, Task<TContinuationResult>> zzkfu;
    private final zzn<TContinuationResult> zzkfv;

    public zzc(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzn<TContinuationResult> zznVar) {
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
        this.zzjqr.execute(new zzd(this, task));
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(@NonNull Exception exc) {
        this.zzkfv.setException(exc);
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzkfv.setResult(tcontinuationresult);
    }
}
