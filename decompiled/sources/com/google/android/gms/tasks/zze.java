package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zze<TResult> implements zzk<TResult> {
    private final Object mLock = new Object();
    private final Executor zzjqr;
    private OnCompleteListener<TResult> zzkfz;

    public zze(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzjqr = executor;
        this.zzkfz = onCompleteListener;
    }

    @Override // com.google.android.gms.tasks.zzk
    public final void cancel() {
        synchronized (this.mLock) {
            this.zzkfz = null;
        }
    }

    @Override // com.google.android.gms.tasks.zzk
    public final void onComplete(@NonNull Task<TResult> task) {
        synchronized (this.mLock) {
            if (this.zzkfz == null) {
                return;
            }
            this.zzjqr.execute(new zzf(this, task));
        }
    }
}
