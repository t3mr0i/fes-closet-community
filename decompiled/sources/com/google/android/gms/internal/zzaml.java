package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzaml implements Runnable {
    private /* synthetic */ zzamj zzdnw;
    private /* synthetic */ boolean zzdnx;

    zzaml(zzamj zzamjVar, boolean z) {
        this.zzdnw = zzamjVar;
        this.zzdnx = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdnw.zzdnu.zzxk();
    }
}
