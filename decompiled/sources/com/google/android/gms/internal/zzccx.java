package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzccx implements Runnable {
    private /* synthetic */ zzccw zziuc;

    zzccx(zzccw zzccwVar) {
        this.zziuc = zzccwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziuc.start();
    }
}
