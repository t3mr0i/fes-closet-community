package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* loaded from: classes.dex */
final class zze extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.ADVERTISER_ID.toString();
    private final zza zzjoq;

    public zze(Context context) {
        this(zza.zzdp(context));
    }

    private zze(zza zzaVar) {
        super(ID, new String[0]);
        this.zzjoq = zzaVar;
        this.zzjoq.zzbce();
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        String strZzbce = this.zzjoq.zzbce();
        return strZzbce == null ? zzgk.zzbfm() : zzgk.zzah(strZzbce);
    }
}
