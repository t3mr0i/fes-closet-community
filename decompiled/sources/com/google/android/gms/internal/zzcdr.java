package com.google.android.gms.internal;

import android.content.pm.PackageManager;
import java.io.IOException;

/* loaded from: classes.dex */
final class zzcdr implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcdb zziuj;

    zzcdr(zzcdb zzcdbVar, zzcas zzcasVar) {
        this.zziuj = zzcdbVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, PackageManager.NameNotFoundException, IOException {
        this.zziuj.zzikh.zzazl();
        this.zziuj.zzikh.zze(this.zziui);
    }
}
