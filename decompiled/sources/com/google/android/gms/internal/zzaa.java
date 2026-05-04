package com.google.android.gms.internal;

/* loaded from: classes.dex */
public class zzaa extends Exception {
    private long zzaa;
    private zzn zzbh;

    public zzaa() {
        this.zzbh = null;
    }

    public zzaa(zzn zznVar) {
        this.zzbh = zznVar;
    }

    public zzaa(Throwable th) {
        super(th);
        this.zzbh = null;
    }

    final void zza(long j) {
        this.zzaa = j;
    }
}
