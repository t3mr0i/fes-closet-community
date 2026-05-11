package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzfq implements Runnable {
    private /* synthetic */ zzfo zzjvs;

    zzfq(zzfo zzfoVar) {
        this.zzjvs = zzfoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzjvs.zzjvh.dispatch();
    }
}
