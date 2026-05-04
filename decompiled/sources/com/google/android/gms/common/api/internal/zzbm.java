package com.google.android.gms.common.api.internal;

/* loaded from: classes.dex */
abstract class zzbm {
    private final zzbk zzfng;

    protected zzbm(zzbk zzbkVar) {
        this.zzfng = zzbkVar;
    }

    protected abstract void zzagz();

    public final void zzc(zzbl zzblVar) {
        zzblVar.zzfkd.lock();
        try {
            if (zzblVar.zzfnc != this.zzfng) {
                return;
            }
            zzagz();
        } finally {
            zzblVar.zzfkd.unlock();
        }
    }
}
