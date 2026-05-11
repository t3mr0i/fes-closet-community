package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
final class zzcde implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ zzcav zziuk;

    zzcde(zzcdb zzcdbVar, zzcav zzcavVar, zzcas zzcasVar) {
        this.zziuj = zzcdbVar;
        this.zziuk = zzcavVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, IOException {
        this.zziuj.zzikh.zzazl();
        this.zziuj.zzikh.zzb(this.zziuk, this.zziui);
    }
}
