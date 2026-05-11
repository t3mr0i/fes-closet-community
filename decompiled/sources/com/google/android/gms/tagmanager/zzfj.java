package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdbq;
import com.google.android.gms.internal.zzdbu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
final class zzfj {
    private zzdbq zzjvd;
    private final Set<zzdbu> zzjup = new HashSet();
    private final Map<zzdbu, List<zzdbq>> zzjuz = new HashMap();
    private final Map<zzdbu, List<String>> zzjvb = new HashMap();
    private final Map<zzdbu, List<zzdbq>> zzjva = new HashMap();
    private final Map<zzdbu, List<String>> zzjvc = new HashMap();

    public final void zza(zzdbu zzdbuVar) {
        this.zzjup.add(zzdbuVar);
    }

    public final void zza(zzdbu zzdbuVar, zzdbq zzdbqVar) {
        List<zzdbq> arrayList = this.zzjuz.get(zzdbuVar);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.zzjuz.put(zzdbuVar, arrayList);
        }
        arrayList.add(zzdbqVar);
    }

    public final void zza(zzdbu zzdbuVar, String str) {
        List<String> arrayList = this.zzjvb.get(zzdbuVar);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.zzjvb.put(zzdbuVar, arrayList);
        }
        arrayList.add(str);
    }

    public final void zzb(zzdbq zzdbqVar) {
        this.zzjvd = zzdbqVar;
    }

    public final void zzb(zzdbu zzdbuVar, zzdbq zzdbqVar) {
        List<zzdbq> arrayList = this.zzjva.get(zzdbuVar);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.zzjva.put(zzdbuVar, arrayList);
        }
        arrayList.add(zzdbqVar);
    }

    public final void zzb(zzdbu zzdbuVar, String str) {
        List<String> arrayList = this.zzjvc.get(zzdbuVar);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.zzjvc.put(zzdbuVar, arrayList);
        }
        arrayList.add(str);
    }

    public final Set<zzdbu> zzbet() {
        return this.zzjup;
    }

    public final Map<zzdbu, List<zzdbq>> zzbeu() {
        return this.zzjuz;
    }

    public final Map<zzdbu, List<String>> zzbev() {
        return this.zzjvb;
    }

    public final Map<zzdbu, List<String>> zzbew() {
        return this.zzjvc;
    }

    public final Map<zzdbu, List<zzdbq>> zzbex() {
        return this.zzjva;
    }

    public final zzdbq zzbey() {
        return this.zzjvd;
    }
}
