package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzf implements Runnable {
    private /* synthetic */ Task zzkfw;
    private /* synthetic */ zze zzkga;

    zzf(zze zzeVar, Task task) {
        this.zzkga = zzeVar;
        this.zzkfw = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzkga.mLock) {
            if (this.zzkga.zzkfz != null) {
                this.zzkga.zzkfz.onComplete(this.zzkfw);
            }
        }
    }
}
