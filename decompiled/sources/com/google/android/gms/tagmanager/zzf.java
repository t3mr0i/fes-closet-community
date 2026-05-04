package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* loaded from: classes.dex */
final class zzf extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzjoq;

    public zzf(Context context) {
        this(zza.zzdp(context));
    }

    private zzf(zza zzaVar) {
        super(ID, new String[0]);
        this.zzjoq = zzaVar;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return zzgk.zzah(Boolean.valueOf(!this.zzjoq.isLimitAdTrackingEnabled()));
    }
}
