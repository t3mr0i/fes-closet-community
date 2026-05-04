package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcfp implements Runnable {
    private /* synthetic */ long zzikw;
    private /* synthetic */ zzcfl zziwv;

    zzcfp(zzcfl zzcflVar, long j) {
        this.zziwv = zzcflVar;
        this.zzikw = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziwv.zzbe(this.zzikw);
    }
}
