package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzh implements Runnable {
    private /* synthetic */ Task zzkfw;
    private /* synthetic */ zzg zzkgc;

    zzh(zzg zzgVar, Task task) {
        this.zzkgc = zzgVar;
        this.zzkfw = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzkgc.mLock) {
            if (this.zzkgc.zzkgb != null) {
                this.zzkgc.zzkgb.onFailure(this.zzkfw.getException());
            }
        }
    }
}
