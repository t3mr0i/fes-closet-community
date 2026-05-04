package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Map;

/* loaded from: classes.dex */
final class zzj extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.APP_NAME.toString();
    private final Context mContext;

    public zzj(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return zzgk.zzah(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        } catch (PackageManager.NameNotFoundException e) {
            zzdj.zzb("App name is not found.", e);
            return zzgk.zzbfm();
        }
    }
}
