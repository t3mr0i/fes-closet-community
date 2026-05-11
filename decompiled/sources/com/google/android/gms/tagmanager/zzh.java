package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/* loaded from: classes.dex */
final class zzh extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzjor = com.google.android.gms.internal.zzbe.COMPONENT.toString();
    private static final String zzjos = com.google.android.gms.internal.zzbe.CONVERSION_ID.toString();
    private final Context zzahy;

    public zzh(Context context) {
        super(ID, zzjos);
        this.zzahy = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjos);
        if (zzbpVar == null) {
            return zzgk.zzbfm();
        }
        String strZzb = zzgk.zzb(zzbpVar);
        com.google.android.gms.internal.zzbp zzbpVar2 = map.get(zzjor);
        String strZzb2 = zzbpVar2 != null ? zzgk.zzb(zzbpVar2) : null;
        Context context = this.zzahy;
        String string = zzcx.zzjsb.get(strZzb);
        if (string == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            string = sharedPreferences != null ? sharedPreferences.getString(strZzb, "") : "";
            zzcx.zzjsb.put(strZzb, string);
        }
        String strZzaw = zzcx.zzaw(string, strZzb2);
        return strZzaw != null ? zzgk.zzah(strZzaw) : zzgk.zzbfm();
    }
}
