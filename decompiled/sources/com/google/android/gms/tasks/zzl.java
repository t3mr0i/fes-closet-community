package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes.dex */
final class zzl<TResult> {
    private final Object mLock = new Object();
    private Queue<zzk<TResult>> zzkgf;
    private boolean zzkgg;

    zzl() {
    }

    public final void zza(@NonNull zzk<TResult> zzkVar) {
        synchronized (this.mLock) {
            if (this.zzkgf == null) {
                this.zzkgf = new ArrayDeque();
            }
            this.zzkgf.add(zzkVar);
        }
    }

    public final void zzb(@NonNull Task<TResult> task) {
        zzk<TResult> zzkVarPoll;
        synchronized (this.mLock) {
            if (this.zzkgf == null || this.zzkgg) {
                return;
            }
            this.zzkgg = true;
            while (true) {
                synchronized (this.mLock) {
                    zzkVarPoll = this.zzkgf.poll();
                    if (zzkVarPoll == null) {
                        this.zzkgg = false;
                        return;
                    }
                }
                zzkVarPoll.onComplete(task);
            }
        }
    }
}
