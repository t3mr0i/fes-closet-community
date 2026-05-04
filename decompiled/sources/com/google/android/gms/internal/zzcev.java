package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;

/* loaded from: classes.dex */
final class zzcev implements Runnable {
    private /* synthetic */ String zziag;
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcbk zziun;
    private /* synthetic */ zzceo zziwe;
    private /* synthetic */ boolean zziwh = true;
    private /* synthetic */ boolean zziwi;

    zzcev(zzceo zzceoVar, boolean z, boolean z2, zzcbk zzcbkVar, zzcas zzcasVar, String str) {
        this.zziwe = zzceoVar;
        this.zziwi = z2;
        this.zziun = zzcbkVar;
        this.zziui = zzcasVar;
        this.zziag = str;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzcbo zzcboVar = this.zziwe.zzivy;
        if (zzcboVar == null) {
            this.zziwe.zzaum().zzaye().log("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zziwh) {
            this.zziwe.zza(zzcboVar, this.zziwi ? null : this.zziun, this.zziui);
        } else {
            try {
                if (TextUtils.isEmpty(this.zziag)) {
                    zzcboVar.zza(this.zziun, this.zziui);
                } else {
                    zzcboVar.zza(this.zziun, this.zziag, this.zziwe.zzaum().zzayl());
                }
            } catch (RemoteException e) {
                this.zziwe.zzaum().zzaye().zzj("Failed to send event to the service", e);
            }
        }
        this.zziwe.zzww();
    }
}
