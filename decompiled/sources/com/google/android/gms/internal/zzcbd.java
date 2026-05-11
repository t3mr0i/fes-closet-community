package com.google.android.gms.internal;

import android.os.Looper;

/* loaded from: classes.dex */
final class zzcbd implements Runnable {
    private /* synthetic */ zzcbc zzine;

    zzcbd(zzcbc zzcbcVar) {
        this.zzine = zzcbcVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zzine.zzikh.zzaul().zzg(this);
            return;
        }
        boolean zZzdp = this.zzine.zzdp();
        zzcbc.zza(this.zzine, 0L);
        if (zZzdp && this.zzine.zzind) {
            this.zzine.run();
        }
    }
}
