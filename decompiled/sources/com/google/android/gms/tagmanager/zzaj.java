package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzaj extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.CONTAINER_VERSION.toString();
    private final String zzezz;

    public zzaj(String str) {
        super(ID, new String[0]);
        this.zzezz = str;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return this.zzezz == null ? zzgk.zzbfm() : zzgk.zzah(this.zzezz);
    }
}
