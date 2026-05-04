package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcbx implements Runnable {
    private /* synthetic */ String zziqa;
    private /* synthetic */ zzcbw zziqb;

    zzcbx(zzcbw zzcbwVar, String str) {
        this.zziqb = zzcbwVar;
        this.zziqa = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcch zzcchVarZzaun = this.zziqb.zzikh.zzaun();
        if (zzcchVarZzaun.isInitialized()) {
            zzcchVarZzaun.zziqn.zzf(this.zziqa, 1L);
        } else {
            this.zziqb.zzk(6, "Persisted config not initialized. Not logging error/warn");
        }
    }
}
