package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zzcfa implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ boolean zziva;
    private /* synthetic */ zzceo zziwe;
    private /* synthetic */ AtomicReference zziwf;

    zzcfa(zzceo zzceoVar, AtomicReference atomicReference, zzcas zzcasVar, boolean z) {
        this.zziwe = zzceoVar;
        this.zziwf = atomicReference;
        this.zziui = zzcasVar;
        this.zziva = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcbo zzcboVar;
        synchronized (this.zziwf) {
            try {
                try {
                    zzcboVar = this.zziwe.zzivy;
                } catch (RemoteException e) {
                    this.zziwe.zzaum().zzaye().zzj("Failed to get user properties", e);
                    this.zziwf.notify();
                }
                if (zzcboVar == null) {
                    this.zziwe.zzaum().zzaye().log("Failed to get user properties");
                } else {
                    this.zziwf.set(zzcboVar.zza(this.zziui, this.zziva));
                    this.zziwe.zzww();
                }
            } finally {
                this.zziwf.notify();
            }
        }
    }
}
