package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzapd {
    private static final char[] zzdul = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static double zza(String str, double d) {
        if (str == null) {
            return 100.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 100.0d;
        }
    }

    public static zzalw zza(zzaon zzaonVar, String str) {
        com.google.android.gms.common.internal.zzbp.zzu(zzaonVar);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        new HashMap();
        try {
            String strValueOf = String.valueOf(str);
            Map<String, String> mapZza = com.google.android.gms.common.util.zzl.zza(new URI(strValueOf.length() != 0 ? "?".concat(strValueOf) : new String("?")), "UTF-8");
            zzalw zzalwVar = new zzalw();
            zzalwVar.zzdb(mapZza.get("utm_content"));
            zzalwVar.zzcz(mapZza.get("utm_medium"));
            zzalwVar.setName(mapZza.get("utm_campaign"));
            zzalwVar.zzcy(mapZza.get("utm_source"));
            zzalwVar.zzda(mapZza.get("utm_term"));
            zzalwVar.zzdc(mapZza.get("utm_id"));
            zzalwVar.zzdd(mapZza.get("anid"));
            zzalwVar.zzde(mapZza.get("gclid"));
            zzalwVar.zzdf(mapZza.get("dclid"));
            zzalwVar.zzdg(mapZza.get(FirebaseAnalytics.Param.ACLID));
            return zzalwVar;
        } catch (URISyntaxException e) {
            zzaonVar.zzd("No valid campaign data found", e);
            return null;
        }
    }

    public static String zza(Locale locale) {
        if (locale == null) {
            return null;
        }
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(language)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(language.toLowerCase());
        if (!TextUtils.isEmpty(locale.getCountry())) {
            sb.append("-").append(locale.getCountry().toLowerCase());
        }
        return sb.toString();
    }

    public static void zza(Map<String, String> map, String str, Map<String, String> map2) {
        zzb(map, str, map2.get(str));
    }

    public static boolean zza(double d, String str) {
        int i;
        if (d <= 0.0d || d >= 100.0d) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            i = 1;
        } else {
            i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char cCharAt = str.charAt(length);
                i = ((i << 6) & 268435455) + cCharAt + (cCharAt << 14);
                int i2 = 266338304 & i;
                if (i2 != 0) {
                    i ^= i2 >> 21;
                }
            }
        }
        return ((double) (i % 10000)) >= 100.0d * d;
    }

    public static boolean zza(Context context, String str, boolean z) throws PackageManager.NameNotFoundException {
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, str), 2);
            if (receiverInfo != null && receiverInfo.enabled) {
                if (z) {
                    if (receiverInfo.exported) {
                    }
                }
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }

    public static String zzaj(boolean z) {
        return z ? "1" : "0";
    }

    public static void zzb(Map<String, String> map, String str, String str2) {
        if (str2 == null || map.containsKey(str)) {
            return;
        }
        map.put(str, str2);
    }

    public static void zzc(Map<String, String> map, String str, String str2) {
        if (str2 == null || !TextUtils.isEmpty(map.get(str))) {
            return;
        }
        map.put(str, str2);
    }

    public static void zzc(Map<String, String> map, String str, boolean z) {
        if (map.containsKey(str)) {
            return;
        }
        map.put(str, z ? "1" : "0");
    }

    public static boolean zzd(String str, boolean z) {
        if (str == null || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("1")) {
            return true;
        }
        return (str.equalsIgnoreCase("false") || str.equalsIgnoreCase("no") || str.equalsIgnoreCase("0")) ? false : true;
    }

    public static Map<String, String> zzdz(String str) {
        HashMap map = new HashMap();
        for (String str2 : str.split("&")) {
            String[] strArrSplit = str2.split("=", 3);
            if (strArrSplit.length > 1) {
                map.put(strArrSplit[0], TextUtils.isEmpty(strArrSplit[1]) ? null : strArrSplit[1]);
                if (strArrSplit.length == 3 && !TextUtils.isEmpty(strArrSplit[1]) && !map.containsKey(strArrSplit[1])) {
                    map.put(strArrSplit[1], TextUtils.isEmpty(strArrSplit[2]) ? null : strArrSplit[2]);
                }
            } else if (strArrSplit.length == 1 && strArrSplit[0].length() != 0) {
                map.put(strArrSplit[0], null);
            }
        }
        return map;
    }

    public static long zzea(String str) {
        if (str == null) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    public static String zzeb(String str) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains("?")) {
            String[] strArrSplit = str.split("[\\?]");
            if (strArrSplit.length > 1) {
                str = strArrSplit[1];
            }
        }
        if (str.contains("%3D")) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else if (!str.contains("=")) {
            return null;
        }
        Map<String, String> mapZzdz = zzdz(str);
        String[] strArr = {"dclid", "utm_source", "gclid", FirebaseAnalytics.Param.ACLID, "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "anid", "gmob_t"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            if (!TextUtils.isEmpty(mapZzdz.get(strArr[i]))) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(strArr[i]).append("=").append(mapZzdz.get(strArr[i]));
            }
        }
        return sb.toString();
    }

    public static MessageDigest zzec(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 2) {
                return null;
            }
            try {
                messageDigest = MessageDigest.getInstance(str);
            } catch (NoSuchAlgorithmException e) {
            }
            if (messageDigest != null) {
                return messageDigest;
            }
            i = i2 + 1;
        }
    }

    public static boolean zzed(String str) {
        return TextUtils.isEmpty(str) || !str.startsWith("http:");
    }

    public static boolean zzv(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, str), 4);
            if (serviceInfo != null) {
                if (serviceInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }
}
