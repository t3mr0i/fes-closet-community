package com.google.android.gms.tagmanager;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes.dex */
final class zzem extends zzga {
    private static final String ID = com.google.android.gms.internal.zzbd.REGEX.toString();
    private static final String zzjtu = com.google.android.gms.internal.zzbe.IGNORE_CASE.toString();

    public zzem() {
        super(ID);
    }

    @Override // com.google.android.gms.tagmanager.zzga
    protected final boolean zza(String str, String str2, Map<String, com.google.android.gms.internal.zzbp> map) {
        try {
            return Pattern.compile(str2, zzgk.zzf(map.get(zzjtu)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}
