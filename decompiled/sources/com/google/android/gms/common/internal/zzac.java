package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes.dex */
final class zzac implements zzg {
    private /* synthetic */ GoogleApiClient.OnConnectionFailedListener zzfuk;

    zzac(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfuk = onConnectionFailedListener;
    }

    @Override // com.google.android.gms.common.internal.zzg
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzfuk.onConnectionFailed(connectionResult);
    }
}
