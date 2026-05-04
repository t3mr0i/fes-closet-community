package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdbq;
import com.google.android.gms.internal.zzdbu;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class zzff implements zzfh {
    private /* synthetic */ Map zzjut;
    private /* synthetic */ Map zzjuu;
    private /* synthetic */ Map zzjuv;
    private /* synthetic */ Map zzjuw;

    zzff(zzfc zzfcVar, Map map, Map map2, Map map3, Map map4) {
        this.zzjut = map;
        this.zzjuu = map2;
        this.zzjuv = map3;
        this.zzjuw = map4;
    }

    @Override // com.google.android.gms.tagmanager.zzfh
    public final void zza(zzdbu zzdbuVar, Set<zzdbq> set, Set<zzdbq> set2, zzer zzerVar) {
        List list = (List) this.zzjut.get(zzdbuVar);
        this.zzjuu.get(zzdbuVar);
        if (list != null) {
            set.addAll(list);
            zzerVar.zzbdy();
        }
        List list2 = (List) this.zzjuv.get(zzdbuVar);
        this.zzjuw.get(zzdbuVar);
        if (list2 != null) {
            set2.addAll(list2);
            zzerVar.zzbdz();
        }
    }
}
