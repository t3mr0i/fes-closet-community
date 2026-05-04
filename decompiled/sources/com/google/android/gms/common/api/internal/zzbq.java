package com.google.android.gms.common.api.internal;

/* loaded from: classes.dex */
final class zzbq implements zzl {
    private /* synthetic */ zzbp zzfnt;

    zzbq(zzbp zzbpVar) {
        this.zzfnt = zzbpVar;
    }

    @Override // com.google.android.gms.common.api.internal.zzl
    public final void zzbe(boolean z) {
        this.zzfnt.mHandler.sendMessage(this.zzfnt.mHandler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
