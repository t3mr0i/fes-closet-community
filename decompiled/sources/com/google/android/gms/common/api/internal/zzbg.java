package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zzbg implements GoogleApiClient.OnConnectionFailedListener {
    private /* synthetic */ zzda zzfmw;

    zzbg(zzbd zzbdVar, zzda zzdaVar) {
        this.zzfmw = zzdaVar;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzfmw.setResult(new Status(8));
    }
}
