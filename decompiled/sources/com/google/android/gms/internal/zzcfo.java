package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcfo implements Runnable {
    private /* synthetic */ long zzikw;
    private /* synthetic */ zzcfl zziwv;

    zzcfo(zzcfl zzcflVar, long j) {
        this.zziwv = zzcflVar;
        this.zzikw = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziwv.zzbd(this.zzikw);
    }
}
