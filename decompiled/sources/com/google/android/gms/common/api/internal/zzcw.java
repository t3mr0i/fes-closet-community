package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcpp;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import com.google.android.gms.internal.zzcpx;
import com.google.android.gms.internal.zzcqf;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzcw extends zzcpx implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static Api.zza<? extends zzcps, zzcpt> zzfpc = zzcpp.zzdwp;
    private final Context mContext;
    private final Handler mHandler;
    private Set<Scope> zzecl;
    private final Api.zza<? extends zzcps, zzcpt> zzfge;
    private com.google.android.gms.common.internal.zzq zzfki;
    private zzcps zzflo;
    private zzcy zzfpd;

    @WorkerThread
    public zzcw(Context context, Handler handler, @NonNull com.google.android.gms.common.internal.zzq zzqVar) {
        this(context, handler, zzqVar, zzfpc);
    }

    @WorkerThread
    public zzcw(Context context, Handler handler, @NonNull com.google.android.gms.common.internal.zzq zzqVar, Api.zza<? extends zzcps, zzcpt> zzaVar) {
        this.mContext = context;
        this.mHandler = handler;
        this.zzfki = (com.google.android.gms.common.internal.zzq) com.google.android.gms.common.internal.zzbp.zzb(zzqVar, "ClientSettings must not be null");
        this.zzecl = zzqVar.zzajs();
        this.zzfge = zzaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzc(zzcqf zzcqfVar) {
        ConnectionResult connectionResultZzagd = zzcqfVar.zzagd();
        if (connectionResultZzagd.isSuccess()) {
            com.google.android.gms.common.internal.zzbs zzbsVarZzbcd = zzcqfVar.zzbcd();
            ConnectionResult connectionResultZzagd2 = zzbsVarZzbcd.zzagd();
            if (!connectionResultZzagd2.isSuccess()) {
                String strValueOf = String.valueOf(connectionResultZzagd2);
                Log.wtf("SignInCoordinator", new StringBuilder(String.valueOf(strValueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(strValueOf).toString(), new Exception());
                this.zzfpd.zzh(connectionResultZzagd2);
                this.zzflo.disconnect();
                return;
            }
            this.zzfpd.zzb(zzbsVarZzbcd.zzakm(), this.zzecl);
        } else {
            this.zzfpd.zzh(connectionResultZzagd);
        }
        this.zzflo.disconnect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    @WorkerThread
    public final void onConnected(@Nullable Bundle bundle) {
        this.zzflo.zza(this);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzfpd.zzh(connectionResult);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    @WorkerThread
    public final void onConnectionSuspended(int i) {
        this.zzflo.disconnect();
    }

    @WorkerThread
    public final void zza(zzcy zzcyVar) {
        if (this.zzflo != null) {
            this.zzflo.disconnect();
        }
        this.zzfki.zzc(Integer.valueOf(System.identityHashCode(this)));
        this.zzflo = (zzcps) this.zzfge.zza(this.mContext, this.mHandler.getLooper(), this.zzfki, this.zzfki.zzajy(), this, this);
        this.zzfpd = zzcyVar;
        this.zzflo.connect();
    }

    public final zzcps zzaic() {
        return this.zzflo;
    }

    public final void zzain() {
        if (this.zzflo != null) {
            this.zzflo.disconnect();
        }
    }

    @Override // com.google.android.gms.internal.zzcpx, com.google.android.gms.internal.zzcpy
    @BinderThread
    public final void zzb(zzcqf zzcqfVar) {
        this.mHandler.post(new zzcx(this, zzcqfVar));
    }
}
