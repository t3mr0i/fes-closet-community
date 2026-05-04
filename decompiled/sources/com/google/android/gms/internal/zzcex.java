package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zzcex implements Runnable {
    private /* synthetic */ String zziag;
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ String zziul;
    private /* synthetic */ String zzium;
    private /* synthetic */ zzceo zziwe;
    private /* synthetic */ AtomicReference zziwf;

    zzcex(zzceo zzceoVar, AtomicReference atomicReference, String str, String str2, String str3, zzcas zzcasVar) {
        this.zziwe = zzceoVar;
        this.zziwf = atomicReference;
        this.zziag = str;
        this.zziul = str2;
        this.zzium = str3;
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
                    this.zziwe.zzaum().zzaye().zzd("Failed to get conditional properties", zzcbw.zzjf(this.zziag), this.zziul, e);
                    this.zziwf.set(Collections.emptyList());
                    this.zziwf.notify();
                }
                if (zzcboVar == null) {
                    this.zziwe.zzaum().zzaye().zzd("Failed to get conditional properties", zzcbw.zzjf(this.zziag), this.zziul, this.zzium);
                    this.zziwf.set(Collections.emptyList());
                } else {
                    if (TextUtils.isEmpty(this.zziag)) {
                        this.zziwf.set(zzcboVar.zza(this.zziul, this.zzium, this.zziui));
                    } else {
                        this.zziwf.set(zzcboVar.zzj(this.zziag, this.zziul, this.zzium));
                    }
                    this.zziwe.zzww();
                }
            } finally {
                this.zziwf.notify();
            }
        }
    }
}
