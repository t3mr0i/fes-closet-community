package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes.dex */
final class zzgo {
    private static zzea<com.google.android.gms.internal.zzbp> zza(zzea<com.google.android.gms.internal.zzbp> zzeaVar) {
        try {
            return new zzea<>(zzgk.zzah(zzmi(zzgk.zzb(zzeaVar.getObject()))), zzeaVar.zzbee());
        } catch (UnsupportedEncodingException e) {
            zzdj.zzb("Escape URI: unsupported encoding", e);
            return zzeaVar;
        }
    }

    static zzea<com.google.android.gms.internal.zzbp> zza(zzea<com.google.android.gms.internal.zzbp> zzeaVar, int... iArr) {
        zzea<com.google.android.gms.internal.zzbp> zzeaVarZza;
        int length = iArr.length;
        int i = 0;
        zzea<com.google.android.gms.internal.zzbp> zzeaVar2 = zzeaVar;
        while (i < length) {
            int i2 = iArr[i];
            if (zzgk.zzg(zzeaVar2.getObject()) instanceof String) {
                switch (i2) {
                    case 12:
                        zzeaVarZza = zza(zzeaVar2);
                        break;
                    default:
                        zzdj.e(new StringBuilder(39).append("Unsupported Value Escaping: ").append(i2).toString());
                        zzeaVarZza = zzeaVar2;
                        break;
                }
            } else {
                zzdj.e("Escaping can only be applied to strings.");
                zzeaVarZza = zzeaVar2;
            }
            i++;
            zzeaVar2 = zzeaVarZza;
        }
        return zzeaVar2;
    }

    static String zzmi(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }
}
