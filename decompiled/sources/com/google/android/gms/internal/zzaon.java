package com.google.android.gms.internal;

import java.util.Map;

/* loaded from: classes.dex */
public class zzaon extends zzams {
    private static zzaon zzdth;

    public zzaon(zzamu zzamuVar) {
        super(zzamuVar);
    }

    private static String zzk(Object obj) {
        if (obj == null) {
            return null;
        }
        Object objValueOf = obj instanceof Integer ? Long.valueOf(((Integer) obj).intValue()) : obj;
        if (!(objValueOf instanceof Long)) {
            return objValueOf instanceof Boolean ? String.valueOf(objValueOf) : objValueOf instanceof Throwable ? objValueOf.getClass().getCanonicalName() : "-";
        }
        if (Math.abs(((Long) objValueOf).longValue()) < 100) {
            return String.valueOf(objValueOf);
        }
        String str = String.valueOf(objValueOf).charAt(0) == '-' ? "-" : "";
        String strValueOf = String.valueOf(Math.abs(((Long) objValueOf).longValue()));
        return str + Math.round(Math.pow(10.0d, strValueOf.length() - 1)) + "..." + str + Math.round(Math.pow(10.0d, strValueOf.length()) - 1.0d);
    }

    public static zzaon zzyt() {
        return zzdth;
    }

    public final void zza(zzaoi zzaoiVar, String str) {
        String string = zzaoiVar != null ? zzaoiVar.toString() : "no hit data";
        String strValueOf = String.valueOf(str);
        zzd(strValueOf.length() != 0 ? "Discarding hit. ".concat(strValueOf) : new String("Discarding hit. "), string);
    }

    public final synchronized void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        synchronized (this) {
            com.google.android.gms.common.internal.zzbp.zzu(str);
            int i2 = i >= 0 ? i : 0;
            int i3 = i2 >= 9 ? 8 : i2;
            char c = zzvz().zzxu() ? 'C' : 'c';
            char cCharAt = "01VDIWEA?".charAt(i3);
            String str2 = zzamt.VERSION;
            String strZzc = zzc(str, zzk(obj), zzk(obj2), zzk(obj3));
            String string = new StringBuilder(String.valueOf("3").length() + 3 + String.valueOf(str2).length() + String.valueOf(strZzc).length()).append("3").append(cCharAt).append(c).append(str2).append(":").append(strZzc).toString();
            if (string.length() > 1024) {
                string = string.substring(0, 1024);
            }
            zzaor zzaorVarZzwo = zzvw().zzwo();
            if (zzaorVarZzwo != null) {
                zzaorVarZzwo.zzzg().zzdy(string);
            }
        }
    }

    public final void zze(Map<String, String> map, String str) {
        String string;
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            string = sb.toString();
        } else {
            string = "no hit data";
        }
        String strValueOf = String.valueOf(str);
        zzd(strValueOf.length() != 0 ? "Discarding hit. ".concat(strValueOf) : new String("Discarding hit. "), string);
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
        synchronized (zzaon.class) {
            zzdth = this;
        }
    }
}
