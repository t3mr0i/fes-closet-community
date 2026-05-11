package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzi<TResult> implements zzk<TResult> {
    private final Object mLock = new Object();
    private final Executor zzjqr;
    private OnSuccessListener<? super TResult> zzkgd;

    public zzi(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzjqr = executor;
        this.zzkgd = onSuccessListener;
    }

    @Override // com.google.android.gms.tasks.zzk
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzkgd = null;
        }
    }

    @Override // com.google.android.gms.tasks.zzk
    public final void onComplete(@NonNull Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.mLock) {
                if (this.zzkgd != null) {
                    this.zzjqr.execute(new zzj(this, task));
                }
            }
        }
    }
}
