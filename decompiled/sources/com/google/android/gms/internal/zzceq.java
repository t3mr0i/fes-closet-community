package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zzceq implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzceo zziwe;
    private /* synthetic */ AtomicReference zziwf;

    zzceq(zzceo zzceoVar, AtomicReference atomicReference, zzcas zzcasVar) {
        this.zziwe = zzceoVar;
        this.zziwf = atomicReference;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcbo zzcboVar;
        synchronized (this.zziwf) {
            try {
                try {
                    zzcboVar = this.zziwe.zzivy;
                } catch (RemoteException e) {
                    this.zziwe.zzaum().zzaye().zzj("Failed to get app instance id", e);
                    this.zziwf.notify();
                }
                if (zzcboVar == null) {
                    this.zziwe.zzaum().zzaye().log("Failed to get app instance id");
                    return;
                }
                this.zziwf.set(zzcboVar.zzc(this.zziui));
                String str = (String) this.zziwf.get();
                if (str != null) {
                    this.zziwe.zzaua().zzjk(str);
                    this.zziwe.zzaun().zziqu.zzjl(str);
                }
                this.zziwe.zzww();
            } finally {
                this.zziwf.notify();
            }
        }
    }
}
