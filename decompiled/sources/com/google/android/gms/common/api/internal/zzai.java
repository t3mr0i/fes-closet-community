package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zzai implements PendingResult.zza {
    private /* synthetic */ zzs zzflb;
    private /* synthetic */ zzah zzflc;

    zzai(zzah zzahVar, zzs zzsVar) {
        this.zzflc = zzahVar;
        this.zzflb = zzsVar;
    }

    @Override // com.google.android.gms.common.api.PendingResult.zza
    public final void zzq(Status status) {
        this.zzflc.zzfkz.remove(this.zzflb);
    }
}
