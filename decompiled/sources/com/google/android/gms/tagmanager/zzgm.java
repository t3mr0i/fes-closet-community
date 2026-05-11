package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzgm extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.UPPERCASE_STRING.toString();
    private static final String zzjrk = com.google.android.gms.internal.zzbe.ARG0.toString();

    public zzgm() {
        super(ID, zzjrk);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return zzgk.zzah(zzgk.zzb(map.get(zzjrk)).toUpperCase());
    }
}
