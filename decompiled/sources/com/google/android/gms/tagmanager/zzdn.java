package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings;
import java.util.Map;

/* loaded from: classes.dex */
final class zzdn extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.MOBILE_ADWORDS_UNIQUE_ID.toString();
    private final Context mContext;

    public zzdn(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
        return string == null ? zzgk.zzbfm() : zzgk.zzah(string);
    }
}
