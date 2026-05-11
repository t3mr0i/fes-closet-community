package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
final class zzav extends zzbm {
    private /* synthetic */ ConnectionResult zzflz;
    private /* synthetic */ zzau zzfma;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzav(zzau zzauVar, zzbk zzbkVar, ConnectionResult connectionResult) {
        super(zzbkVar);
        this.zzfma = zzauVar;
        this.zzflz = connectionResult;
    }

    @Override // com.google.android.gms.common.api.internal.zzbm
    public final void zzagz() {
        this.zzfma.zzflw.zze(this.zzflz);
    }
}
