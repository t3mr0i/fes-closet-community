package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzbp extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.EVENT.toString();
    private final zzfc zzjpb;

    public zzbp(zzfc zzfcVar) {
        super(ID, new String[0]);
        this.zzjpb = zzfcVar;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        String strZzbep = this.zzjpb.zzbep();
        return strZzbep == null ? zzgk.zzbfm() : zzgk.zzah(strZzbep);
    }
}
