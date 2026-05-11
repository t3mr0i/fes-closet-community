package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzccg implements Runnable {
    private /* synthetic */ boolean zziqk;
    private /* synthetic */ zzccf zziql;

    zzccg(zzccf zzccfVar, boolean z) {
        this.zziql = zzccfVar;
        this.zziqk = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziql.zzikh.zzbo(this.zziqk);
    }
}
