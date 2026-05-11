package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
public final class zzm implements zzj {
    private /* synthetic */ zzd zzftk;

    public zzm(zzd zzdVar) {
        this.zzftk = zzdVar;
    }

    @Override // com.google.android.gms.common.internal.zzj
    public final void zzf(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            this.zzftk.zza((zzam) null, this.zzftk.zzajm());
        } else if (this.zzftk.zzftc != null) {
            this.zzftk.zzftc.onConnectionFailed(connectionResult);
        }
    }
}
