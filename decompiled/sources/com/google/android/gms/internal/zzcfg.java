package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcfg implements Runnable {
    private /* synthetic */ zzcfb zziwo;

    zzcfg(zzcfb zzcfbVar) {
        this.zziwo = zzcfbVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzceo.zza(this.zziwo.zziwe, (zzcbo) null);
        this.zziwo.zziwe.zzazs();
    }
}
