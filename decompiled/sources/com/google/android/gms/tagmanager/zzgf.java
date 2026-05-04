package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzgf extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.TIME.toString();

    public zzgf() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return zzgk.zzah(Long.valueOf(System.currentTimeMillis()));
    }
}
