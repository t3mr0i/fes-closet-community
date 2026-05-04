package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzas extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.CUSTOM_VAR.toString();
    private static final String NAME = com.google.android.gms.internal.zzbe.NAME.toString();
    private static final String zzjqp = com.google.android.gms.internal.zzbe.DEFAULT_VALUE.toString();
    private final DataLayer zzjpa;

    public zzas(DataLayer dataLayer) {
        super(ID, NAME);
        this.zzjpa = dataLayer;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        Object obj = this.zzjpa.get(zzgk.zzb(map.get(NAME)));
        if (obj != null) {
            return zzgk.zzah(obj);
        }
        com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjqp);
        return zzbpVar != null ? zzbpVar : zzgk.zzbfm();
    }
}
