package com.google.android.gms.internal;

import android.os.RemoteException;

/* loaded from: classes.dex */
final class zzcer implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzceo zziwe;

    zzcer(zzceo zzceoVar, zzcas zzcasVar) {
        this.zziwe = zzceoVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzcbo zzcboVar = this.zziwe.zzivy;
        if (zzcboVar == null) {
            this.zziwe.zzaum().zzaye().log("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzcboVar.zza(this.zziui);
            this.zziwe.zza(zzcboVar, null, this.zziui);
            this.zziwe.zzww();
        } catch (RemoteException e) {
            this.zziwe.zzaum().zzaye().zzj("Failed to send app launch to the service", e);
        }
    }
}
