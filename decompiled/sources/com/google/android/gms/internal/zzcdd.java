package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
final class zzcdd implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ zzcav zziuk;

    zzcdd(zzcdb zzcdbVar, zzcav zzcavVar, zzcas zzcasVar) {
        this.zziuj = zzcdbVar;
        this.zziuk = zzcavVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, IOException {
        this.zziuj.zzikh.zzazl();
        this.zziuj.zzikh.zzc(this.zziuk, this.zziui);
    }
}
