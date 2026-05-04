package com.google.android.gms.internal;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

/* loaded from: classes.dex */
public final class zzcpp {
    private static Api.zzf<zzcqc> zzdwo = new Api.zzf<>();
    private static Api.zzf<zzcqc> zzjnm = new Api.zzf<>();
    public static final Api.zza<zzcqc, zzcpt> zzdwp = new zzcpq();
    private static Api.zza<zzcqc, Object> zzjnn = new zzcpr();
    private static Scope zzecc = new Scope(Scopes.PROFILE);
    private static Scope zzecd = new Scope("email");
    public static final Api<zzcpt> API = new Api<>("SignIn.API", zzdwp, zzdwo);
    private static Api<Object> zzgdm = new Api<>("SignIn.INTERNAL_API", zzjnn, zzjnm);
}
