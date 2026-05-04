package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes.dex */
final class zzbcs extends Api.zza<zzbcz, Api.ApiOptions.NoOptions> {
    zzbcs() {
    }

    @Override // com.google.android.gms.common.api.Api.zza
    public final /* synthetic */ Api.zze zza(Context context, Looper looper, com.google.android.gms.common.internal.zzq zzqVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbcz(context, looper, zzqVar, connectionCallbacks, onConnectionFailedListener);
    }
}
