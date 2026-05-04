package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zzcea implements Runnable {
    private /* synthetic */ String zziag;
    private /* synthetic */ String zziul;
    private /* synthetic */ String zzium;
    private /* synthetic */ zzcdw zziux;
    private /* synthetic */ AtomicReference zziuz;

    zzcea(zzcdw zzcdwVar, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zziux = zzcdwVar;
        this.zziuz = atomicReference;
        this.zziag = str;
        this.zziul = str2;
        this.zzium = str3;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException {
        this.zziux.zzikh.zzaud().zza(this.zziuz, this.zziag, this.zziul, this.zzium);
    }
}
