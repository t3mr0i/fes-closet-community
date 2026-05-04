package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzdbq {
    private final zzbp zzjuy;
    private final Map<String, zzbp> zzkec;

    private zzdbq(Map<String, zzbp> map, zzbp zzbpVar) {
        this.zzkec = map;
        this.zzjuy = zzbpVar;
    }

    public static zzdbr zzbhw() {
        return new zzdbr();
    }

    public final String toString() {
        String strValueOf = String.valueOf(Collections.unmodifiableMap(this.zzkec));
        String strValueOf2 = String.valueOf(this.zzjuy);
        return new StringBuilder(String.valueOf(strValueOf).length() + 32 + String.valueOf(strValueOf2).length()).append("Properties: ").append(strValueOf).append(" pushAfterEvaluate: ").append(strValueOf2).toString();
    }

    public final void zza(String str, zzbp zzbpVar) {
        this.zzkec.put(str, zzbpVar);
    }

    public final zzbp zzbes() {
        return this.zzjuy;
    }

    public final Map<String, zzbp> zzbhe() {
        return Collections.unmodifiableMap(this.zzkec);
    }
}
