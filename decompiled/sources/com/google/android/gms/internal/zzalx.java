package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzalx extends com.google.android.gms.analytics.zzh<zzalx> {
    private Map<Integer, String> zzdmk = new HashMap(4);

    public final String toString() {
        HashMap map = new HashMap();
        for (Map.Entry<Integer, String> entry : this.zzdmk.entrySet()) {
            String strValueOf = String.valueOf(entry.getKey());
            map.put(new StringBuilder(String.valueOf(strValueOf).length() + 9).append("dimension").append(strValueOf).toString(), entry.getValue());
        }
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        ((zzalx) zzhVar).zzdmk.putAll(this.zzdmk);
    }

    public final Map<Integer, String> zzuw() {
        return Collections.unmodifiableMap(this.zzdmk);
    }
}
