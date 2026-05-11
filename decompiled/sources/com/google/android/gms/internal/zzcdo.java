package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
final class zzcdo implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ zzcft zziuo;

    zzcdo(zzcdb zzcdbVar, zzcft zzcftVar, zzcas zzcasVar) {
        this.zziuj = zzcdbVar;
        this.zziuo = zzcftVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, IOException {
        this.zziuj.zzikh.zzazl();
        this.zziuj.zzikh.zzc(this.zziuo, this.zziui);
    }
}
