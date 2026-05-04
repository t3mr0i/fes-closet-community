package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzq implements Runnable {
    private /* synthetic */ String zzao;
    private /* synthetic */ long zzap;
    private /* synthetic */ zzp zzaq;

    zzq(zzp zzpVar, String str, long j) {
        this.zzaq = zzpVar;
        this.zzao = str;
        this.zzap = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzaq.zzab.zza(this.zzao, this.zzap);
        this.zzaq.zzab.zzc(toString());
    }
}
