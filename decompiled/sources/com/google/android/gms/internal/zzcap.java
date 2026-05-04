package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcap implements Runnable {
    private /* synthetic */ String zzanx;
    private /* synthetic */ long zzikw;
    private /* synthetic */ zzcan zzikx;

    zzcap(zzcan zzcanVar, String str, long j) {
        this.zzikx = zzcanVar;
        this.zzanx = str;
        this.zzikw = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzikx.zze(this.zzanx, this.zzikw);
    }
}
