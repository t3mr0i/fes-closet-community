package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzgk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzdbt {
    private String zzezz;
    private final List<zzdbu> zzkdz;
    private final Map<String, List<zzdbq>> zzkea;
    private int zzkeb;

    private zzdbt() {
        this.zzkdz = new ArrayList();
        this.zzkea = new HashMap();
        this.zzezz = "";
        this.zzkeb = 0;
    }

    public final zzdbt zzb(zzdbu zzdbuVar) {
        this.zzkdz.add(zzdbuVar);
        return this;
    }

    public final zzdbs zzbia() {
        return new zzdbs(this.zzkdz, this.zzkea, this.zzezz, this.zzkeb);
    }

    public final zzdbt zzc(zzdbq zzdbqVar) {
        String strZzb = zzgk.zzb(zzdbqVar.zzbhe().get(zzbe.INSTANCE_NAME.toString()));
        List<zzdbq> arrayList = this.zzkea.get(strZzb);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.zzkea.put(strZzb, arrayList);
        }
        arrayList.add(zzdbqVar);
        return this;
    }

    public final zzdbt zzeq(int i) {
        this.zzkeb = i;
        return this;
    }

    public final zzdbt zznj(String str) {
        this.zzezz = str;
        return this;
    }
}
