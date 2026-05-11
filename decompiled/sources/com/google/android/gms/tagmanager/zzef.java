package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzef extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.PLATFORM.toString();
    private static final com.google.android.gms.internal.zzbp zzjtg = zzgk.zzah("Android");

    public zzef() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return zzjtg;
    }
}
