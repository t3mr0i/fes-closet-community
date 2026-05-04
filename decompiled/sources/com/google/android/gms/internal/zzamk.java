package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzamk implements Runnable {
    private /* synthetic */ int zzdnv;
    private /* synthetic */ zzamj zzdnw;

    zzamk(zzamj zzamjVar, int i) {
        this.zzdnw = zzamjVar;
        this.zzdnv = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdnw.zzdnu.zzr(this.zzdnv * 1000);
    }
}
