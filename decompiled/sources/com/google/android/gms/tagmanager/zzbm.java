package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
public final class zzbm extends zzga {
    private static final String ID = com.google.android.gms.internal.zzbd.EQUALS.toString();

    public zzbm() {
        super(ID);
    }

    @Override // com.google.android.gms.tagmanager.zzga
    protected final boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.zzbp> map) {
        return str.equals(str2);
    }
}
