package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzd implements Runnable {
    private /* synthetic */ Task zzkfw;
    private /* synthetic */ zzc zzkfy;

    zzd(zzc zzcVar, Task task) {
        this.zzkfy = zzcVar;
        this.zzkfw = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task task = (Task) this.zzkfy.zzkfu.then(this.zzkfw);
            if (task == null) {
                this.zzkfy.onFailure(new NullPointerException("Continuation returned null"));
            } else {
                task.addOnSuccessListener(TaskExecutors.zzkgi, this.zzkfy);
                task.addOnFailureListener(TaskExecutors.zzkgi, this.zzkfy);
            }
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzkfy.zzkfv.setException((Exception) e.getCause());
            } else {
                this.zzkfy.zzkfv.setException(e);
            }
        } catch (Exception e2) {
            this.zzkfy.zzkfv.setException(e2);
        }
    }
}
