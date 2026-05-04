package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
final class zzcdf implements Runnable {
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ zzcav zziuk;

    zzcdf(zzcdb zzcdbVar, zzcav zzcavVar) {
        this.zziuj = zzcdbVar;
        this.zziuk = zzcavVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, IOException {
        this.zziuj.zzikh.zzazl();
        this.zziuj.zzikh.zze(this.zziuk);
    }
}
