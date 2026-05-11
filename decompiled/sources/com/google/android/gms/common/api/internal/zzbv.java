package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import java.util.Set;

/* loaded from: classes.dex */
final class zzbv implements zzcy, com.google.android.gms.common.internal.zzj {
    private final zzh<?> zzfgr;
    private final Api.zze zzfkg;
    final /* synthetic */ zzbp zzfnt;
    private com.google.android.gms.common.internal.zzam zzfls = null;
    private Set<Scope> zzecl = null;
    private boolean zzfoe = false;

    public zzbv(zzbp zzbpVar, Api.zze zzeVar, zzh<?> zzhVar) {
        this.zzfnt = zzbpVar;
        this.zzfkg = zzeVar;
        this.zzfgr = zzhVar;
    }

    static /* synthetic */ boolean zza(zzbv zzbvVar, boolean z) {
        zzbvVar.zzfoe = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzaid() {
        if (!this.zzfoe || this.zzfls == null) {
            return;
        }
        this.zzfkg.zza(this.zzfls, this.zzecl);
    }

    @Override // com.google.android.gms.common.api.internal.zzcy
    @WorkerThread
    public final void zzb(com.google.android.gms.common.internal.zzam zzamVar, Set<Scope> set) {
        if (zzamVar == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            zzh(new ConnectionResult(4));
        } else {
            this.zzfls = zzamVar;
            this.zzecl = set;
            zzaid();
        }
    }

    @Override // com.google.android.gms.common.internal.zzj
    public final void zzf(@NonNull ConnectionResult connectionResult) {
        this.zzfnt.mHandler.post(new zzbw(this, connectionResult));
    }

    @Override // com.google.android.gms.common.api.internal.zzcy
    @WorkerThread
    public final void zzh(ConnectionResult connectionResult) {
        ((zzbr) this.zzfnt.zzfkj.get(this.zzfgr)).zzh(connectionResult);
    }
}
