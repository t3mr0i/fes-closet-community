package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
abstract class zzdz extends zzeg {
    public zzdz(String str) {
        super(str);
    }

    @Override // com.google.android.gms.tagmanager.zzeg
    protected final boolean zza(com.google.android.gms.internal.zzbp zzbpVar, com.google.android.gms.internal.zzbp zzbpVar2, Map<String, com.google.android.gms.internal.zzbp> map) {
        zzgj zzgjVarZzc = zzgk.zzc(zzbpVar);
        zzgj zzgjVarZzc2 = zzgk.zzc(zzbpVar2);
        if (zzgjVarZzc == zzgk.zzbfk() || zzgjVarZzc2 == zzgk.zzbfk()) {
            return false;
        }
        return zza(zzgjVarZzc, zzgjVarZzc2, map);
    }

    protected abstract boolean zza(zzgj zzgjVar, zzgj zzgjVar2, Map<String, com.google.android.gms.internal.zzbp> map);
}
