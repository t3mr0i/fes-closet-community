package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzj implements Runnable {
    private /* synthetic */ Task zzkfw;
    private /* synthetic */ zzi zzkge;

    zzj(zzi zziVar, Task task) {
        this.zzkge = zziVar;
        this.zzkfw = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzkge.mLock) {
            if (this.zzkge.zzkgd != null) {
                this.zzkge.zzkgd.onSuccess(this.zzkfw.getResult());
            }
        }
    }
}
