package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;

/* loaded from: classes.dex */
final class zzcew implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzceo zziwe;
    private /* synthetic */ boolean zziwh = true;
    private /* synthetic */ boolean zziwi;
    private /* synthetic */ zzcav zziwj;
    private /* synthetic */ zzcav zziwk;

    zzcew(zzceo zzceoVar, boolean z, boolean z2, zzcav zzcavVar, zzcas zzcasVar, zzcav zzcavVar2) {
        this.zziwe = zzceoVar;
        this.zziwi = z2;
        this.zziwj = zzcavVar;
        this.zziui = zzcasVar;
        this.zziwk = zzcavVar2;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzcbo zzcboVar = this.zziwe.zzivy;
        if (zzcboVar == null) {
            this.zziwe.zzaum().zzaye().log("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zziwh) {
            this.zziwe.zza(zzcboVar, this.zziwi ? null : this.zziwj, this.zziui);
        } else {
            try {
                if (TextUtils.isEmpty(this.zziwk.packageName)) {
                    zzcboVar.zza(this.zziwj, this.zziui);
                } else {
                    zzcboVar.zzb(this.zziwj);
                }
            } catch (RemoteException e) {
                this.zziwe.zzaum().zzaye().zzj("Failed to send conditional user property to the service", e);
            }
        }
        this.zziwe.zzww();
    }
}
