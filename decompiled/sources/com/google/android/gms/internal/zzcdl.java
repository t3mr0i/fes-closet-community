package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
final class zzcdl implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ zzcbk zziun;

    zzcdl(zzcdb zzcdbVar, zzcbk zzcbkVar, zzcas zzcasVar) {
        this.zziuj = zzcdbVar;
        this.zziun = zzcbkVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, IOException {
        this.zziuj.zzikh.zzazl();
        this.zziuj.zzikh.zzb(this.zziun, this.zziui);
    }
}
