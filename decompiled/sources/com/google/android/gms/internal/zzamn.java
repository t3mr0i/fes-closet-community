package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzamn implements Runnable {
    private /* synthetic */ zzamj zzdnw;
    private /* synthetic */ zzaoi zzdoa;

    zzamn(zzamj zzamjVar, zzaoi zzaoiVar) {
        this.zzdnw = zzamjVar;
        this.zzdoa = zzaoiVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        this.zzdnw.zzdnu.zza(this.zzdoa);
    }
}
