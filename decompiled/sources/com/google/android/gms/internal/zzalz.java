package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzalz extends com.google.android.gms.analytics.zzh<zzalz> {
    private final Map<String, Object> zzbql = new HashMap();

    public final void set(String str, String str2) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        if (str != null && str.startsWith("&")) {
            str = str.substring(1);
        }
        com.google.android.gms.common.internal.zzbp.zzh(str, "Name can not be empty or \"&\"");
        this.zzbql.put(str, str2);
    }

    public final String toString() {
        return zzh(this.zzbql);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzalz zzalzVar = (zzalz) zzhVar;
        com.google.android.gms.common.internal.zzbp.zzu(zzalzVar);
        zzalzVar.zzbql.putAll(this.zzbql);
    }

    public final Map<String, Object> zzuy() {
        return Collections.unmodifiableMap(this.zzbql);
    }
}
