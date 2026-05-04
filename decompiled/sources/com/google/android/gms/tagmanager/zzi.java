package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.Map;

/* loaded from: classes.dex */
final class zzi extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.APP_ID.toString();
    private final Context mContext;

    public zzi(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return zzgk.zzah(this.mContext.getPackageName());
    }
}
