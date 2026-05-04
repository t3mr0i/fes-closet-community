package com.google.android.gms.tagmanager;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes.dex */
final class zzel extends zzbr {
    private static final String ID = com.google.android.gms.internal.zzbd.REGEX_GROUP.toString();
    private static final String zzjts = com.google.android.gms.internal.zzbe.ARG0.toString();
    private static final String zzjtt = com.google.android.gms.internal.zzbe.ARG1.toString();
    private static final String zzjtu = com.google.android.gms.internal.zzbe.IGNORE_CASE.toString();
    private static final String zzjtv = com.google.android.gms.internal.zzbe.GROUP.toString();

    public zzel() {
        super(ID, zzjts, zzjtt);
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        int iIntValue;
        com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjts);
        com.google.android.gms.internal.zzbp zzbpVar2 = map.get(zzjtt);
        if (zzbpVar == null || zzbpVar == zzgk.zzbfm() || zzbpVar2 == null || zzbpVar2 == zzgk.zzbfm()) {
            return zzgk.zzbfm();
        }
        int i = zzgk.zzf(map.get(zzjtu)).booleanValue() ? 66 : 64;
        com.google.android.gms.internal.zzbp zzbpVar3 = map.get(zzjtv);
        if (zzbpVar3 != null) {
            Long lZzd = zzgk.zzd(zzbpVar3);
            if (lZzd == zzgk.zzbfh()) {
                return zzgk.zzbfm();
            }
            iIntValue = lZzd.intValue();
            if (iIntValue < 0) {
                return zzgk.zzbfm();
            }
        } else {
            iIntValue = 1;
        }
        try {
            String strZzb = zzgk.zzb(zzbpVar);
            String strGroup = null;
            Matcher matcher = Pattern.compile(zzgk.zzb(zzbpVar2), i).matcher(strZzb);
            if (matcher.find() && matcher.groupCount() >= iIntValue) {
                strGroup = matcher.group(iIntValue);
            }
            return strGroup == null ? zzgk.zzbfm() : zzgk.zzah(strGroup);
        } catch (PatternSyntaxException e) {
            return zzgk.zzbfm();
        }
    }
}
