package com.google.android.gms.tagmanager;

import java.util.Map;

/* loaded from: classes.dex */
final class zzak extends zzga {
    private static final String ID = com.google.android.gms.internal.zzbd.CONTAINS.toString();

    public zzak() {
        super(ID);
    }

    @Override // com.google.android.gms.tagmanager.zzga
    protected final boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.zzbp> map) {
        return str.contains(str2);
    }
}
