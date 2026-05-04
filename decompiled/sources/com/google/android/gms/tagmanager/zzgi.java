package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
abstract class zzgi extends zzbr {
    public zzgi(String str, String... strArr) {
        super(str, strArr);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public boolean zzbck() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        zzr(map);
        return zzgk.zzbfm();
    }

    public abstract void zzr(Map<String, com.google.android.gms.internal.zzbp> map);
}
