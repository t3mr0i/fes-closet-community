package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class zzcx {
    private static String zzjsa;
    static Map<String, String> zzjsb = new HashMap();

    static void zzak(Context context, String str) {
        zzfu.zzd(context, "gtm_install_referrer", "referrer", str);
        zzam(context, str);
    }

    public static String zzal(Context context, String str) {
        if (zzjsa == null) {
            synchronized (zzcx.class) {
                if (zzjsa == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        zzjsa = sharedPreferences.getString("referrer", "");
                    } else {
                        zzjsa = "";
                    }
                }
            }
        }
        return zzaw(zzjsa, str);
    }

    public static void zzam(Context context, String str) {
        String strZzaw = zzaw(str, "conv");
        if (strZzaw == null || strZzaw.length() <= 0) {
            return;
        }
        zzjsb.put(strZzaw, str);
        zzfu.zzd(context, "gtm_click_referrers", strZzaw, str);
    }

    public static String zzaw(String str, String str2) {
        if (str2 != null) {
            String strValueOf = String.valueOf(str);
            return Uri.parse(strValueOf.length() != 0 ? "http://hostname/?".concat(strValueOf) : new String("http://hostname/?")).getQueryParameter(str2);
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }

    public static void zzlt(String str) {
        synchronized (zzcx.class) {
            zzjsa = str;
        }
    }
}
