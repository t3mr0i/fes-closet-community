package com.google.android.gms.tagmanager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* loaded from: classes.dex */
final class zzbw extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.HASH.toString();
    private static final String zzjrk = com.google.android.gms.internal.zzbe.ARG0.toString();
    private static final String zzjrq = com.google.android.gms.internal.zzbe.ALGORITHM.toString();
    private static final String zzjrm = com.google.android.gms.internal.zzbe.INPUT_FORMAT.toString();

    public zzbw() {
        super(ID, zzjrk);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) throws NoSuchAlgorithmException {
        byte[] bArrDecode;
        com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjrk);
        if (zzbpVar == null || zzbpVar == zzgk.zzbfm()) {
            return zzgk.zzbfm();
        }
        String strZzb = zzgk.zzb(zzbpVar);
        com.google.android.gms.internal.zzbp zzbpVar2 = map.get(zzjrq);
        String strZzb2 = zzbpVar2 == null ? "MD5" : zzgk.zzb(zzbpVar2);
        com.google.android.gms.internal.zzbp zzbpVar3 = map.get(zzjrm);
        String strZzb3 = zzbpVar3 == null ? "text" : zzgk.zzb(zzbpVar3);
        if ("text".equals(strZzb3)) {
            bArrDecode = strZzb.getBytes();
        } else {
            if (!"base16".equals(strZzb3)) {
                String strValueOf = String.valueOf(strZzb3);
                zzdj.e(strValueOf.length() != 0 ? "Hash: unknown input format: ".concat(strValueOf) : new String("Hash: unknown input format: "));
                return zzgk.zzbfm();
            }
            bArrDecode = zzo.decode(strZzb);
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(strZzb2);
            messageDigest.update(bArrDecode);
            return zzgk.zzah(zzo.encode(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            String strValueOf2 = String.valueOf(strZzb2);
            zzdj.e(strValueOf2.length() != 0 ? "Hash: unknown algorithm: ".concat(strValueOf2) : new String("Hash: unknown algorithm: "));
            return zzgk.zzbfm();
        }
    }
}
