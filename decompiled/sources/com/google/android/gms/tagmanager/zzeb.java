package com.google.android.gms.tagmanager;

import android.os.Build;
import java.util.Map;

/* loaded from: classes.dex */
final class zzeb extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.OS_VERSION.toString();

    public zzeb() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return zzgk.zzah(Build.VERSION.RELEASE);
    }
}
