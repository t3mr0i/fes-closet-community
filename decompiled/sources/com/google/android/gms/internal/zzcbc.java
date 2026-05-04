package com.google.android.gms.internal;

import android.os.Handler;

/* loaded from: classes.dex */
abstract class zzcbc {
    private static volatile Handler zzdqr;
    private volatile long zzdqs;
    private final zzccw zzikh;
    private boolean zzind;
    private final Runnable zzv;

    zzcbc(zzccw zzccwVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzccwVar);
        this.zzikh = zzccwVar;
        this.zzind = true;
        this.zzv = new zzcbd(this);
    }

    private final Handler getHandler() {
        Handler handler;
        if (zzdqr != null) {
            return zzdqr;
        }
        synchronized (zzcbc.class) {
            if (zzdqr == null) {
                zzdqr = new Handler(this.zzikh.getContext().getMainLooper());
            }
            handler = zzdqr;
        }
        return handler;
    }

    static /* synthetic */ long zza(zzcbc zzcbcVar, long j) {
        zzcbcVar.zzdqs = 0L;
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
            this.zzdqs = this.zzikh.zzvx().currentTimeMillis();
            if (getHandler().postDelayed(this.zzv, j)) {
                return;
            }
            this.zzikh.zzaum().zzaye().zzj("Failed to schedule delayed post. time", Long.valueOf(j));
        }
    }
}
