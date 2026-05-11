package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.data.DataHolder;

/* loaded from: classes.dex */
public abstract class zzal<L> implements zzcm<L> {
    private final DataHolder zzfle;

    protected zzal(DataHolder dataHolder) {
        this.zzfle = dataHolder;
    }

    protected abstract void zza(L l, DataHolder dataHolder);

    @Override // com.google.android.gms.common.api.internal.zzcm
    public final void zzagx() {
        if (this.zzfle != null) {
            this.zzfle.close();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcm
    public final void zzq(L l) {
        zza(l, this.zzfle);
    }
}
