package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzaly extends com.google.android.gms.analytics.zzh<zzaly> {
    private Map<Integer, Double> zzdml = new HashMap(4);

    public final String toString() {
        HashMap map = new HashMap();
        for (Map.Entry<Integer, Double> entry : this.zzdml.entrySet()) {
            String strValueOf = String.valueOf(entry.getKey());
            map.put(new StringBuilder(String.valueOf(strValueOf).length() + 6).append("metric").append(strValueOf).toString(), entry.getValue());
        }
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        ((zzaly) zzhVar).zzdml.putAll(this.zzdml);
    }

    public final Map<Integer, Double> zzux() {
        return Collections.unmodifiableMap(this.zzdml);
    }
}
