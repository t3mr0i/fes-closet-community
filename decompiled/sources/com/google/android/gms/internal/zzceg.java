package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zzceg implements Runnable {
    private /* synthetic */ zzcdw zziux;
    private /* synthetic */ AtomicReference zziuz;
    private /* synthetic */ boolean zziva;

    zzceg(zzcdw zzcdwVar, AtomicReference atomicReference, boolean z) {
        this.zziux = zzcdwVar;
        this.zziuz = atomicReference;
        this.zziva = z;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zziux.zzaud().zza(this.zziuz, this.zziva);
    }
}
