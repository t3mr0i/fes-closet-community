package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzced implements Runnable {
    private /* synthetic */ zzcdw zziux;
    private /* synthetic */ long zzivb;

    zzced(zzcdw zzcdwVar, long j) {
        this.zziux = zzcdwVar;
        this.zzivb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziux.zzaun().zzirc.set(this.zzivb);
        this.zziux.zzaum().zzayj().zzj("Session timeout duration set", Long.valueOf(this.zzivb));
    }
}
