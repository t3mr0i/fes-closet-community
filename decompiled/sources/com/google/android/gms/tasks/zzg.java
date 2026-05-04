package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzg<TResult> implements zzk<TResult> {
    private final Object mLock = new Object();
    private final Executor zzjqr;
    private OnFailureListener zzkgb;

    public zzg(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzjqr = executor;
        this.zzkgb = onFailureListener;
    }

    @Override // com.google.android.gms.tasks.zzk
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzkgb = null;
        }
    }

    @Override // com.google.android.gms.tasks.zzk
    public final void onComplete(@NonNull Task<TResult> task) {
        if (task.isSuccessful()) {
            return;
        }
        synchronized (this.mLock) {
            if (this.zzkgb != null) {
                this.zzjqr.execute(new zzh(this, task));
            }
        }
    }
}
