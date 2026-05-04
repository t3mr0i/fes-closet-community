package com.google.android.gms.common.api.internal;

/* loaded from: classes.dex */
final class zzap extends zzbm {
    private /* synthetic */ zzao zzfli;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzap(zzao zzaoVar, zzbk zzbkVar) {
        super(zzbkVar);
        this.zzfli = zzaoVar;
    }

    @Override // com.google.android.gms.common.api.internal.zzbm
    public final void zzagz() {
        this.zzfli.onConnectionSuspended(1);
    }
}
