package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Map;

/* loaded from: classes.dex */
final class zzk extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.APP_VERSION.toString();
    private final Context mContext;

    public zzk(Context context) {
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
            return zzgk.zzah(Integer.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            String packageName = this.mContext.getPackageName();
            String message = e.getMessage();
            zzdj.e(new StringBuilder(String.valueOf(packageName).length() + 25 + String.valueOf(message).length()).append("Package name ").append(packageName).append(" not found. ").append(message).toString());
            return zzgk.zzbfm();
        }
    }
}
