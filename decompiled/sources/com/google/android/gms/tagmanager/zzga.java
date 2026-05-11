package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
abstract class zzga extends zzeg {
    public zzga(String str) {
        super(str);
    }

    @Override // com.google.android.gms.tagmanager.zzeg
    protected final boolean zza(com.google.android.gms.internal.zzbp zzbpVar, com.google.android.gms.internal.zzbp zzbpVar2, Map<String, com.google.android.gms.internal.zzbp> map) {
        String strZzb = zzgk.zzb(zzbpVar);
        String strZzb2 = zzgk.zzb(zzbpVar2);
        if (strZzb == zzgk.zzbfl() || strZzb2 == zzgk.zzbfl()) {
            return false;
        }
        return zza(strZzb, strZzb2, map);
    }

    protected abstract boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.zzbp> map);
}
