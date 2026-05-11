package com.google.android.gms.tagmanager;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zzaz extends zzgi {
    private static final String ID = com.google.android.gms.internal.zzbd.DATA_LAYER_WRITE.toString();
    private static final String VALUE = com.google.android.gms.internal.zzbe.VALUE.toString();
    private static final String zzjra = com.google.android.gms.internal.zzbe.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzjpa;

    public zzaz(DataLayer dataLayer) {
        super(ID, VALUE);
        this.zzjpa = dataLayer;
    }

    @Override // com.google.android.gms.tagmanager.zzgi
    public final void zzr(Map<String, com.google.android.gms.internal.zzbp> map) throws InterruptedException {
        String strZzb;
        com.google.android.gms.internal.zzbp zzbpVar = map.get(VALUE);
        if (zzbpVar != null && zzbpVar != zzgk.zzbfg()) {
            Object objZzg = zzgk.zzg(zzbpVar);
            if (objZzg instanceof List) {
                for (Object obj : (List) objZzg) {
                    if (obj instanceof Map) {
                        this.zzjpa.push((Map) obj);
                    }
                }
            }
        }
        com.google.android.gms.internal.zzbp zzbpVar2 = map.get(zzjra);
        if (zzbpVar2 == null || zzbpVar2 == zzgk.zzbfg() || (strZzb = zzgk.zzb(zzbpVar2)) == zzgk.zzbfl()) {
            return;
        }
        this.zzjpa.zzli(strZzb);
    }
}
