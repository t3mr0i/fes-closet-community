package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* loaded from: classes.dex */
final class zzcw extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.INSTALL_REFERRER.toString();
    private static final String zzjor = com.google.android.gms.internal.zzbe.COMPONENT.toString();
    private final Context zzahy;

    public zzcw(Context context) {
        super(ID, new String[0]);
        this.zzahy = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        String strZzal = zzcx.zzal(this.zzahy, map.get(zzjor) != null ? zzgk.zzb(map.get(zzjor)) : null);
        return strZzal != null ? zzgk.zzah(strZzal) : zzgk.zzbfm();
    }
}
