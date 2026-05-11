package com.google.android.gms.tagmanager;

import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import java.util.Map;

/* loaded from: classes.dex */
final class zzbd extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.DEVICE_NAME.toString();

    public zzbd() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        String str = Build.MANUFACTURER;
        String string = Build.MODEL;
        if (!string.startsWith(str) && !str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
            string = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(string).length()).append(str).append(" ").append(string).toString();
        }
        return zzgk.zzah(string);
    }
}
