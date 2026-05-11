package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzdbs {
    private final String zzezz;
    private final List<zzdbu> zzkdz;
    private final Map<String, List<zzdbq>> zzkea;
    private final int zzkeb;

    private zzdbs(List<zzdbu> list, Map<String, List<zzdbq>> map, String str, int i) {
        this.zzkdz = Collections.unmodifiableList(list);
        this.zzkea = Collections.unmodifiableMap(map);
        this.zzezz = str;
        this.zzkeb = i;
    }

    public static zzdbt zzbhy() {
        return new zzdbt();
    }

    public final String getVersion() {
        return this.zzezz;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzkdz);
        String strValueOf2 = String.valueOf(this.zzkea);
        return new StringBuilder(String.valueOf(strValueOf).length() + 17 + String.valueOf(strValueOf2).length()).append("Rules: ").append(strValueOf).append("  Macros: ").append(strValueOf2).toString();
    }

    public final List<zzdbu> zzbhc() {
        return this.zzkdz;
    }

    public final Map<String, List<zzdbq>> zzbhz() {
        return this.zzkea;
    }
}
