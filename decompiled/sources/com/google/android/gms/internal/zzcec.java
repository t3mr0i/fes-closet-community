package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcec implements Runnable {
    private /* synthetic */ zzcdw zziux;
    private /* synthetic */ long zzivb;

    zzcec(zzcdw zzcdwVar, long j) {
        this.zziux = zzcdwVar;
        this.zzivb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziux.zzaun().zzirb.set(this.zzivb);
        this.zziux.zzaum().zzayj().zzj("Minimum session duration set", Long.valueOf(this.zzivb));
    }
}
