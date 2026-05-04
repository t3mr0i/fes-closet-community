package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcez implements Runnable {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcft zziuo;
    private /* synthetic */ zzceo zziwe;
    private /* synthetic */ boolean zziwi;

    zzcez(zzceo zzceoVar, boolean z, zzcft zzcftVar, zzcas zzcasVar) {
        this.zziwe = zzceoVar;
        this.zziwi = z;
        this.zziuo = zzcftVar;
        this.zziui = zzcasVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        zzcbo zzcboVar = this.zziwe.zzivy;
        if (zzcboVar == null) {
            this.zziwe.zzaum().zzaye().log("Discarding data. Failed to set user attribute");
        } else {
            this.zziwe.zza(zzcboVar, this.zziwi ? null : this.zziuo, this.zziui);
            this.zziwe.zzww();
        }
    }
}
