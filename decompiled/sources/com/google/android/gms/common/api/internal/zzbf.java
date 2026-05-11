package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zzbf implements GoogleApiClient.ConnectionCallbacks {
    private /* synthetic */ zzbd zzfmu;
    private /* synthetic */ AtomicReference zzfmv;
    private /* synthetic */ zzda zzfmw;

    zzbf(zzbd zzbdVar, AtomicReference atomicReference, zzda zzdaVar) {
        this.zzfmu = zzbdVar;
        this.zzfmv = atomicReference;
        this.zzfmw = zzdaVar;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        this.zzfmu.zza((GoogleApiClient) this.zzfmv.get(), this.zzfmw, true);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }
}
