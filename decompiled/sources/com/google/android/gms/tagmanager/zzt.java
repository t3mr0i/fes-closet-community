package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzt extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.CONSTANT.toString();
    private static final String VALUE = com.google.android.gms.internal.zzbe.VALUE.toString();

    public zzt() {
        super(ID, VALUE);
    }

    public static String zzbcm() {
        return ID;
    }

    public static String zzbcn() {
        return VALUE;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        return map.get(VALUE);
    }
}
