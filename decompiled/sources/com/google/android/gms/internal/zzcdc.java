package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
final class zzcdc implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcdb zziuj;

    zzcdc(zzcdb zzcdbVar, zzcas zzcasVar) {
        this.zziuj = zzcdbVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, IOException {
        this.zziuj.zzikh.zzazl();
        this.zziuj.zzikh.zzd(this.zziui);
    }
}
