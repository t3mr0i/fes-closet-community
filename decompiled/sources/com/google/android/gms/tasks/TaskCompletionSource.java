package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

/* loaded from: classes.dex */
public class TaskCompletionSource<TResult> {
    private final zzn<TResult> zzkgh = new zzn<>();

    @NonNull
    public Task<TResult> getTask() {
        return this.zzkgh;
    }

    public void setException(@NonNull Exception exc) {
        this.zzkgh.setException(exc);
    }

    public void setResult(TResult tresult) {
        this.zzkgh.setResult(tresult);
    }

    public boolean trySetException(@NonNull Exception exc) {
        return this.zzkgh.trySetException(exc);
    }

    public boolean trySetResult(TResult tresult) {
        return this.zzkgh.trySetResult(tresult);
    }
}
