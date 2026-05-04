package com.google.android.gms.internal;

import android.os.Handler;

/* loaded from: classes.dex */
abstract class zzanx {
    private static volatile Handler zzdqr;
    private final zzamu zzdoc;
    private volatile long zzdqs;
    private final Runnable zzv;

    zzanx(zzamu zzamuVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzamuVar);
        this.zzdoc = zzamuVar;
        this.zzv = new zzany(this);
    }

    private final Handler getHandler() {
        Handler handler;
        if (zzdqr != null) {
            return zzdqr;
        }
        synchronized (zzanx.class) {
            if (zzdqr == null) {
                zzdqr = new Handler(this.zzdoc.getContext().getMainLooper());
            }
            handler = zzdqr;
        }
        return handler;
    }

    static /* synthetic */ long zza(zzanx zzanxVar, long j) {
        zzanxVar.zzdqs = 0L;
        return 0L;
    }

    public final void cancel() {
        this.zzdqs = 0L;
        getHandler().removeCallbacks(this.zzv);
    }

    public abstract void run();

    public final boolean zzdp() {
        return this.zzdqs != 0;
    }

    public final void zzs(long j) {
        cancel();
        if (j >= 0) {
            this.zzdqs = this.zzdoc.zzvx().currentTimeMillis();
            if (getHandler().postDelayed(this.zzv, j)) {
                return;
            }
            this.zzdoc.zzvy().zze("Failed to schedule delayed post. time", Long.valueOf(j));
        }
    }

    public final void zzt(long j) {
        if (zzdp()) {
            if (j < 0) {
                cancel();
                return;
            }
            long jAbs = j - Math.abs(this.zzdoc.zzvx().currentTimeMillis() - this.zzdqs);
            long j2 = jAbs >= 0 ? jAbs : 0L;
            getHandler().removeCallbacks(this.zzv);
            if (getHandler().postDelayed(this.zzv, j2)) {
                return;
            }
            this.zzdoc.zzvy().zze("Failed to adjust delayed post. time", Long.valueOf(j2));
        }
    }

    public final long zzyg() {
        if (this.zzdqs == 0) {
            return 0L;
        }
        return Math.abs(this.zzdoc.zzvx().currentTimeMillis() - this.zzdqs);
    }
}
