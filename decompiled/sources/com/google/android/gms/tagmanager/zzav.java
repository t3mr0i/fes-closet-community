package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzav implements Runnable {
    private /* synthetic */ zzat zzjqw;
    private /* synthetic */ zzaq zzjqx;

    zzav(zzat zzatVar, zzaq zzaqVar) {
        this.zzjqw = zzatVar;
        this.zzjqx = zzaqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzjqx.zzah(this.zzjqw.zzbdg());
    }
}
