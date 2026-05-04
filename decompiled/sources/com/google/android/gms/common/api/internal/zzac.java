package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;

/* loaded from: classes.dex */
public final class zzac<O extends Api.ApiOptions> extends GoogleApi<O> {
    private final Api.zza<? extends zzcps, zzcpt> zzfhl;
    private final Api.zze zzfkg;
    private final zzw zzfkh;
    private final com.google.android.gms.common.internal.zzq zzfki;

    public zzac(@NonNull Context context, Api<O> api, Looper looper, @NonNull Api.zze zzeVar, @NonNull zzw zzwVar, com.google.android.gms.common.internal.zzq zzqVar, Api.zza<? extends zzcps, zzcpt> zzaVar) {
        super(context, api, looper);
        this.zzfkg = zzeVar;
        this.zzfkh = zzwVar;
        this.zzfki = zzqVar;
        this.zzfhl = zzaVar;
        this.zzfgu.zza(this);
    }

    @Override // com.google.android.gms.common.api.GoogleApi
    public final Api.zze zza(Looper looper, zzbr<O> zzbrVar) {
        this.zzfkh.zza(zzbrVar);
        return this.zzfkg;
    }

    @Override // com.google.android.gms.common.api.GoogleApi
    public final zzcw zza(Context context, Handler handler) {
        return new zzcw(context, handler, this.zzfki, this.zzfhl);
    }

    public final Api.zze zzagn() {
        return this.zzfkg;
    }
}
