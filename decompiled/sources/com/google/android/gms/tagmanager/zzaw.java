package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzaw implements Runnable {
    private /* synthetic */ zzat zzjqw;
    private /* synthetic */ String zzjqy;

    zzaw(zzat zzatVar, String str) {
        this.zzjqw = zzatVar;
        this.zzjqy = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzjqw.zzll(this.zzjqy);
    }
}
