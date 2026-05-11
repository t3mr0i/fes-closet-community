package com.google.android.gms.tagmanager;

import android.util.Base64;
import java.util.Map;

/* loaded from: classes.dex */
final class zzbk extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.ENCODE.toString();
    private static final String zzjrk = com.google.android.gms.internal.zzbe.ARG0.toString();
    private static final String zzjrl = com.google.android.gms.internal.zzbe.NO_PADDING.toString();
    private static final String zzjrm = com.google.android.gms.internal.zzbe.INPUT_FORMAT.toString();
    private static final String zzjrn = com.google.android.gms.internal.zzbe.OUTPUT_FORMAT.toString();

    public zzbk() {
        super(ID, zzjrk);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        byte[] bArrDecode;
        String strEncodeToString;
        com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjrk);
        if (zzbpVar == null || zzbpVar == zzgk.zzbfm()) {
            return zzgk.zzbfm();
        }
        String strZzb = zzgk.zzb(zzbpVar);
        com.google.android.gms.internal.zzbp zzbpVar2 = map.get(zzjrm);
        String strZzb2 = zzbpVar2 == null ? "text" : zzgk.zzb(zzbpVar2);
        com.google.android.gms.internal.zzbp zzbpVar3 = map.get(zzjrn);
        String strZzb3 = zzbpVar3 == null ? "base16" : zzgk.zzb(zzbpVar3);
        int i = 2;
        com.google.android.gms.internal.zzbp zzbpVar4 = map.get(zzjrl);
        if (zzbpVar4 != null && zzgk.zzf(zzbpVar4).booleanValue()) {
            i = 3;
        }
        try {
            if ("text".equals(strZzb2)) {
                bArrDecode = strZzb.getBytes();
            } else if ("base16".equals(strZzb2)) {
                bArrDecode = zzo.decode(strZzb);
            } else if ("base64".equals(strZzb2)) {
                bArrDecode = Base64.decode(strZzb, i);
            } else {
                if (!"base64url".equals(strZzb2)) {
                    String strValueOf = String.valueOf(strZzb2);
                    zzdj.e(strValueOf.length() != 0 ? "Encode: unknown input format: ".concat(strValueOf) : new String("Encode: unknown input format: "));
                    return zzgk.zzbfm();
                }
                bArrDecode = Base64.decode(strZzb, i | 8);
            }
            if ("base16".equals(strZzb3)) {
                strEncodeToString = zzo.encode(bArrDecode);
            } else if ("base64".equals(strZzb3)) {
                strEncodeToString = Base64.encodeToString(bArrDecode, i);
            } else {
                if (!"base64url".equals(strZzb3)) {
                    String strValueOf2 = String.valueOf(strZzb3);
                    zzdj.e(strValueOf2.length() != 0 ? "Encode: unknown output format: ".concat(strValueOf2) : new String("Encode: unknown output format: "));
                    return zzgk.zzbfm();
                }
                strEncodeToString = Base64.encodeToString(bArrDecode, i | 8);
            }
            return zzgk.zzah(strEncodeToString);
        } catch (IllegalArgumentException e) {
            zzdj.e("Encode: invalid input:");
            return zzgk.zzbfm();
        }
    }
}
