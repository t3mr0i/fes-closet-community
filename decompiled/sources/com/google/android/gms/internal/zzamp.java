package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzamp implements Runnable {
    private /* synthetic */ zzamj zzdnw;
    private /* synthetic */ zzaob zzdob;

    zzamp(zzamj zzamjVar, zzaob zzaobVar) {
        this.zzdnw = zzamjVar;
        this.zzdob = zzaobVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdnw.zzdnu.zzb(this.zzdob);
    }
}
