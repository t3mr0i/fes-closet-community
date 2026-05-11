package com.google.android.gms.tagmanager;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzde extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.LANGUAGE.toString();

    public zzde() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final /* bridge */ /* synthetic */ String zzbdq() {
        return super.zzbdq();
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final /* bridge */ /* synthetic */ Set zzbdr() {
        return super.zzbdr();
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        String language;
        Locale locale = Locale.getDefault();
        if (locale != null && (language = locale.getLanguage()) != null) {
            return zzgk.zzah(language.toLowerCase());
        }
        return zzgk.zzbfm();
    }
}
