package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes.dex */
final class zzba implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private /* synthetic */ zzar zzflw;

    private zzba(zzar zzarVar) {
        this.zzflw = zzarVar;
    }

    /* synthetic */ zzba(zzar zzarVar, zzas zzasVar) {
        this(zzarVar);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.zzflw.zzflo.zza(new zzay(this.zzflw));
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzflw.zzfkd.lock();
        try {
            if (this.zzflw.zzd(connectionResult)) {
                this.zzflw.zzahd();
                this.zzflw.zzahb();
            } else {
                this.zzflw.zze(connectionResult);
            }
        } finally {
            this.zzflw.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }
}
