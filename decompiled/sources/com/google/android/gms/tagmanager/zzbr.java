package com.google.android.gms.tagmanager;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
abstract class zzbr {
    private final Set<String> zzjro;
    private final String zzjrp;

    public zzbr(String str, String... strArr) {
        this.zzjrp = str;
        this.zzjro = new HashSet(strArr.length);
        for (String str2 : strArr) {
            this.zzjro.add(str2);
        }
    }

    public abstract boolean zzbck();

    public String zzbdq() {
        return this.zzjrp;
    }

    public Set<String> zzbdr() {
        return this.zzjro;
    }

    final boolean zzd(Set<String> set) {
        return set.containsAll(this.zzjro);
    }

    public abstract com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map);
}
