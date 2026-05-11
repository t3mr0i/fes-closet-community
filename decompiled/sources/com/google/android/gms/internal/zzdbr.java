package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzdbr {
    private zzbp zzjuy;
    private final Map<String, zzbp> zzkec;

    private zzdbr() {
        this.zzkec = new HashMap();
    }

    public final zzdbr zzb(String str, zzbp zzbpVar) {
        this.zzkec.put(str, zzbpVar);
        return this;
    }

    public final zzdbq zzbhx() {
        return new zzdbq(this.zzkec, this.zzjuy);
    }

    public final zzdbr zzl(zzbp zzbpVar) {
        this.zzjuy = zzbpVar;
        return this;
    }
}
