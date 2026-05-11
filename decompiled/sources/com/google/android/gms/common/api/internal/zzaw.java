package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
final class zzaw extends zzbm {
    private /* synthetic */ com.google.android.gms.common.internal.zzj zzfmb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaw(zzau zzauVar, zzbk zzbkVar, com.google.android.gms.common.internal.zzj zzjVar) {
        super(zzbkVar);
        this.zzfmb = zzjVar;
    }

    @Override // com.google.android.gms.common.api.internal.zzbm
    public final void zzagz() {
        this.zzfmb.zzf(new ConnectionResult(16, null));
    }
}
