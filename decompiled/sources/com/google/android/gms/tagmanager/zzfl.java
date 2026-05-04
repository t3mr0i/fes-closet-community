package com.google.android.gms.tagmanager;

import android.os.Build;
import java.util.Map;

/* loaded from: classes.dex */
final class zzfl extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.SDK_VERSION.toString();

    public zzfl() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return zzgk.zzah(Integer.valueOf(Build.VERSION.SDK_INT));
    }
}
