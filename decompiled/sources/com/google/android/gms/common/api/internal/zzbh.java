package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zzbh implements ResultCallback<Status> {
    private /* synthetic */ GoogleApiClient zzemr;
    private /* synthetic */ zzbd zzfmu;
    private /* synthetic */ zzda zzfmw;
    private /* synthetic */ boolean zzfmx;

    zzbh(zzbd zzbdVar, zzda zzdaVar, boolean z, GoogleApiClient googleApiClient) {
        this.zzfmu = zzbdVar;
        this.zzfmw = zzdaVar;
        this.zzfmx = z;
        this.zzemr = googleApiClient;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(@NonNull Result result) {
        Status status = (Status) result;
        com.google.android.gms.auth.api.signin.internal.zzy.zzbl(this.zzfmu.mContext).zzaau();
        if (status.isSuccess() && this.zzfmu.isConnected()) {
            this.zzfmu.reconnect();
        }
        this.zzfmw.setResult(status);
        if (this.zzfmx) {
            this.zzemr.disconnect();
        }
    }
}
