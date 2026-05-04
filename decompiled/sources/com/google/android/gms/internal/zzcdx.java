package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcdx implements Runnable {
    private /* synthetic */ boolean val$enabled;
    private /* synthetic */ zzcdw zziux;

    zzcdx(zzcdw zzcdwVar, boolean z) {
        this.zziux = zzcdwVar;
        this.val$enabled = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziux.zzbp(this.val$enabled);
    }
}
