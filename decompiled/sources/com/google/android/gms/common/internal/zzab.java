package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes.dex */
final class zzab implements zzf {
    private /* synthetic */ GoogleApiClient.ConnectionCallbacks zzfuj;

    zzab(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zzfuj = connectionCallbacks;
    }

    @Override // com.google.android.gms.common.internal.zzf
    public final void onConnected(@Nullable Bundle bundle) {
        this.zzfuj.onConnected(bundle);
    }

    @Override // com.google.android.gms.common.internal.zzf
    public final void onConnectionSuspended(int i) {
        this.zzfuj.onConnectionSuspended(i);
    }
}
