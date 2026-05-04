package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes.dex */
final class zzcpq extends Api.zza<zzcqc, zzcpt> {
    zzcpq() {
    }

    @Override // com.google.android.gms.common.api.Api.zza
    public final /* synthetic */ Api.zze zza(Context context, Looper looper, com.google.android.gms.common.internal.zzq zzqVar, zzcpt zzcptVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzcpt zzcptVar2 = zzcptVar;
        return new zzcqc(context, looper, true, zzqVar, zzcptVar2 == null ? zzcpt.zzjno : zzcptVar2, connectionCallbacks, onConnectionFailedListener);
    }
}
