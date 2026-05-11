package com.google.android.gms.internal;

import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

/* loaded from: classes.dex */
public final class zzam {
    public static String zza(Map<String, String> map) {
        String str = map.get("Content-Type");
        if (str != null) {
            String[] strArrSplit = str.split(";");
            for (int i = 1; i < strArrSplit.length; i++) {
                String[] strArrSplit2 = strArrSplit[i].trim().split("=");
                if (strArrSplit2.length == 2 && strArrSplit2[0].equals("charset")) {
                    return strArrSplit2[1];
                }
            }
        }
        return "ISO-8859-1";
    }

    public static zzc zzb(zzn zznVar) throws NumberFormatException {
        boolean z;
        long j;
        long j2;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = zznVar.zzy;
        long j3 = 0;
        long j4 = 0;
        boolean z2 = false;
        String str = map.get("Date");
        long jZzf = str != null ? zzf(str) : 0L;
        String str2 = map.get("Cache-Control");
        if (str2 != null) {
            String[] strArrSplit = str2.split(",");
            int i = 0;
            z = false;
            while (i < strArrSplit.length) {
                String strTrim = strArrSplit[i].trim();
                if (strTrim.equals("no-cache") || strTrim.equals("no-store")) {
                    return null;
                }
                if (strTrim.startsWith("max-age=")) {
                    try {
                        j3 = Long.parseLong(strTrim.substring(8));
                    } catch (Exception e) {
                    }
                } else if (strTrim.startsWith("stale-while-revalidate=")) {
                    try {
                        j4 = Long.parseLong(strTrim.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (strTrim.equals("must-revalidate") || strTrim.equals("proxy-revalidate")) {
                    z = true;
                }
                i++;
                j4 = j4;
            }
            z2 = true;
        } else {
            z = false;
        }
        String str3 = map.get("Expires");
        long jZzf2 = str3 != null ? zzf(str3) : 0L;
        String str4 = map.get("Last-Modified");
        long jZzf3 = str4 != null ? zzf(str4) : 0L;
        String str5 = map.get("ETag");
        if (z2) {
            j2 = jCurrentTimeMillis + (1000 * j3);
            j = z ? j2 : (1000 * j4) + j2;
        } else if (jZzf <= 0 || jZzf2 < jZzf) {
            j = 0;
            j2 = 0;
        } else {
            long j5 = jCurrentTimeMillis + (jZzf2 - jZzf);
            j = j5;
            j2 = j5;
        }
        zzc zzcVar = new zzc();
        zzcVar.data = zznVar.data;
        zzcVar.zza = str5;
        zzcVar.zze = j2;
        zzcVar.zzd = j;
        zzcVar.zzb = jZzf;
        zzcVar.zzc = jZzf3;
        zzcVar.zzf = map;
        return zzcVar;
    }

    private static long zzf(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0L;
        }
    }
}
