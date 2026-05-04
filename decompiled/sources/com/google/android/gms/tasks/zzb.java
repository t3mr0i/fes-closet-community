package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzb implements Runnable {
    private /* synthetic */ Task zzkfw;
    private /* synthetic */ zza zzkfx;

    zzb(zza zzaVar, Task task) {
        this.zzkfx = zzaVar;
        this.zzkfw = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzkfx.zzkfv.setResult(this.zzkfx.zzkfu.then(this.zzkfw));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzkfx.zzkfv.setException((Exception) e.getCause());
            } else {
                this.zzkfx.zzkfv.setException(e);
            }
        } catch (Exception e2) {
            this.zzkfx.zzkfv.setException(e2);
        }
    }
}
