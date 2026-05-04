package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzaoi {
    private final Map<String, String> zzbql;
    private final List<zzanp> zzdsv;
    private final long zzdsw;
    private final long zzdsx;
    private final int zzdsy;
    private final boolean zzdsz;
    private final String zzdta;

    public zzaoi(zzamr zzamrVar, Map<String, String> map, long j, boolean z) {
        this(zzamrVar, map, j, z, 0L, 0, null);
    }

    public zzaoi(zzamr zzamrVar, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(zzamrVar, map, j, z, j2, i, null);
    }

    public zzaoi(zzamr zzamrVar, Map<String, String> map, long j, boolean z, long j2, int i, List<zzanp> list) {
        String strZza;
        String strZza2;
        com.google.android.gms.common.internal.zzbp.zzu(zzamrVar);
        com.google.android.gms.common.internal.zzbp.zzu(map);
        this.zzdsx = j;
        this.zzdsz = z;
        this.zzdsw = j2;
        this.zzdsy = i;
        this.zzdsv = list != null ? list : Collections.emptyList();
        this.zzdta = zzr(list);
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (zzj(entry.getKey()) && (strZza2 = zza(zzamrVar, entry.getKey())) != null) {
                map2.put(strZza2, zzb(zzamrVar, entry.getValue()));
            }
        }
        for (Map.Entry<String, String> entry2 : map.entrySet()) {
            if (!zzj(entry2.getKey()) && (strZza = zza(zzamrVar, entry2.getKey())) != null) {
                map2.put(strZza, zzb(zzamrVar, entry2.getValue()));
            }
        }
        if (!TextUtils.isEmpty(this.zzdta)) {
            zzapd.zzb(map2, "_v", this.zzdta);
            if (this.zzdta.equals("ma4.0.0") || this.zzdta.equals("ma4.0.1")) {
                map2.remove("adid");
            }
        }
        this.zzbql = Collections.unmodifiableMap(map2);
    }

    private static String zza(zzamr zzamrVar, Object obj) {
        if (obj == null) {
            return null;
        }
        String string = obj.toString();
        if (string.startsWith("&")) {
            string = string.substring(1);
        }
        int length = string.length();
        if (length > 256) {
            string = string.substring(0, 256);
            zzamrVar.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), string);
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    private static String zzb(zzamr zzamrVar, Object obj) {
        String string = obj == null ? "" : obj.toString();
        int length = string.length();
        if (length <= 8192) {
            return string;
        }
        String strSubstring = string.substring(0, 8192);
        zzamrVar.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), strSubstring);
        return strSubstring;
    }

    private static boolean zzj(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.toString().startsWith("&");
    }

    private final String zzl(String str, String str2) {
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        com.google.android.gms.common.internal.zzbp.zzb(!str.startsWith("&"), "Short param name required");
        String str3 = this.zzbql.get(str);
        return str3 != null ? str3 : str2;
    }

    private static String zzr(List<zzanp> list) {
        String value;
        if (list != null) {
            for (zzanp zzanpVar : list) {
                if ("appendVersion".equals(zzanpVar.getId())) {
                    value = zzanpVar.getValue();
                    break;
                }
            }
            value = null;
        } else {
            value = null;
        }
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        return value;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ht=").append(this.zzdsx);
        if (this.zzdsw != 0) {
            stringBuffer.append(", dbId=").append(this.zzdsw);
        }
        if (this.zzdsy != 0) {
            stringBuffer.append(", appUID=").append(this.zzdsy);
        }
        ArrayList arrayList = new ArrayList(this.zzbql.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            String str = (String) obj;
            stringBuffer.append(", ");
            stringBuffer.append(str);
            stringBuffer.append("=");
            stringBuffer.append(this.zzbql.get(str));
        }
        return stringBuffer.toString();
    }

    public final Map<String, String> zziy() {
        return this.zzbql;
    }

    public final int zzyl() {
        return this.zzdsy;
    }

    public final long zzym() {
        return this.zzdsw;
    }

    public final long zzyn() {
        return this.zzdsx;
    }

    public final List<zzanp> zzyo() {
        return this.zzdsv;
    }

    public final boolean zzyp() {
        return this.zzdsz;
    }

    public final long zzyq() {
        return zzapd.zzea(zzl("_s", "0"));
    }

    public final String zzyr() {
        return zzl("_m", "");
    }
}
