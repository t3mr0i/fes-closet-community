package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class zzam extends zzbr {
    private final zzan zzjqf;
    private static final String ID = com.google.android.gms.internal.zzbd.FUNCTION_CALL.toString();
    private static final String zzjqe = com.google.android.gms.internal.zzbe.FUNCTION_CALL_NAME.toString();
    private static final String zzjot = com.google.android.gms.internal.zzbe.ADDITIONAL_PARAMS.toString();

    public zzam(zzan zzanVar) {
        super(ID, zzjqe);
        this.zzjqf = zzanVar;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final boolean zzbck() {
        return false;
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final com.google.android.gms.internal.zzbp zzp(Map<String, com.google.android.gms.internal.zzbp> map) {
        String strZzb = zzgk.zzb(map.get(zzjqe));
        HashMap map2 = new HashMap();
        com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjot);
        if (zzbpVar != null) {
            Object objZzg = zzgk.zzg(zzbpVar);
            if (!(objZzg instanceof Map)) {
                zzdj.zzcr("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzgk.zzbfm();
            }
            for (Map.Entry entry : ((Map) objZzg).entrySet()) {
                map2.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzgk.zzah(this.zzjqf.zzd(strZzb, map2));
        } catch (Exception e) {
            String message = e.getMessage();
            zzdj.zzcr(new StringBuilder(String.valueOf(strZzb).length() + 34 + String.valueOf(message).length()).append("Custom macro/tag ").append(strZzb).append(" threw exception ").append(message).toString());
            return zzgk.zzbfm();
        }
    }
}
