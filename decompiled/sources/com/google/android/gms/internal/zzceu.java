package com.google.android.gms.internal;

import android.os.RemoteException;

/* loaded from: classes.dex */
final class zzceu implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzceo zziwe;

    zzceu(zzceo zzceoVar, zzcas zzcasVar) {
        this.zziwe = zzceoVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcbo zzcboVar = this.zziwe.zzivy;
        if (zzcboVar == null) {
            this.zziwe.zzaum().zzaye().log("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzcboVar.zzb(this.zziui);
            this.zziwe.zzww();
        } catch (RemoteException e) {
            this.zziwe.zzaum().zzaye().zzj("Failed to send measurementEnabled to the service", e);
        }
    }
}
