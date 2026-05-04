package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcaq implements Runnable {
    private /* synthetic */ long zzikw;
    private /* synthetic */ zzcan zzikx;

    zzcaq(zzcan zzcanVar, long j) {
        this.zzikx = zzcanVar;
        this.zzikw = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzikx.zzak(this.zzikw);
    }
}
