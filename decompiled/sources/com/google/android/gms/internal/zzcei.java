package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zzcei implements Runnable {
    private /* synthetic */ zzcdw zziux;
    private /* synthetic */ AtomicReference zziuz;

    zzcei(zzcdw zzcdwVar, AtomicReference atomicReference) {
        this.zziux = zzcdwVar;
        this.zziuz = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziux.zzaud().zza(this.zziuz);
    }
}
