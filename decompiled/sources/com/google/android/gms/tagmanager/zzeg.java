package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class zzeg extends zzbr {
    private static final String zzjrk = com.google.android.gms.internal.zzbe.ARG0.toString();
    private static final String zzjth = com.google.android.gms.internal.zzbe.ARG1.toString();

    public zzeg(String str) {
        super(str, zzjrk, zzjth);
    }

    protected abstract boolean zza(com.google.android.gms.internal.zzbp zzbpVar, com.google.android.gms.internal.zzbp zzbpVar2, Map<String, com.google.android.gms.internal.zzbp> map);

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final /* bridge */ /* synthetic */ String zzbdq() {
        return super.zzbdq();
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final /* bridge */ /* synthetic */ Set zzbdr() {
        return super.zzbdr();
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        Iterator<com.google.android.gms.internal.zzbp> it = map.values().iterator();
        while (it.hasNext()) {
            if (it.next() == zzgk.zzbfm()) {
                return zzgk.zzah(false);
            }
        }
        com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjrk);
        com.google.android.gms.internal.zzbp zzbpVar2 = map.get(zzjth);
        return zzgk.zzah(Boolean.valueOf((zzbpVar == null || zzbpVar2 == null) ? false : zza(zzbpVar, zzbpVar2, map)));
    }
}
