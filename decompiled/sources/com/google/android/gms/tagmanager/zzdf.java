package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzdf extends zzdz {
    private static final String ID = com.google.android.gms.internal.zzbd.LESS_EQUALS.toString();

    public zzdf() {
        super(ID);
    }

    @Override // com.google.android.gms.tagmanager.zzdz
    protected final boolean zza(zzgj zzgjVar, zzgj zzgjVar2, Map<String, com.google.android.gms.internal.zzbp> map) {
        return zzgjVar.compareTo(zzgjVar2) <= 0;
    }
}
