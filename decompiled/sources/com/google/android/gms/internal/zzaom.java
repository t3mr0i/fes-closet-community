package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
/* loaded from: classes.dex */
public final class zzaom {
    private static volatile Logger zzdtg = new zzanw();

    public static Logger getLogger() {
        return zzdtg;
    }

    public static void setLogger(Logger logger) {
        zzdtg = logger;
    }

    public static void v(String str) {
        zzaon zzaonVarZzyt = zzaon.zzyt();
        if (zzaonVarZzyt != null) {
            zzaonVarZzyt.zzdm(str);
        } else if (zzad(0)) {
            Log.v(zzaod.zzdra.get(), str);
        }
        Logger logger = zzdtg;
        if (logger != null) {
            logger.verbose(str);
        }
    }

    private static boolean zzad(int i) {
        return zzdtg != null && zzdtg.getLogLevel() <= i;
    }

    public static void zzcr(String str) {
        zzaon zzaonVarZzyt = zzaon.zzyt();
        if (zzaonVarZzyt != null) {
            zzaonVarZzyt.zzdp(str);
        } else if (zzad(2)) {
            Log.w(zzaod.zzdra.get(), str);
        }
        Logger logger = zzdtg;
        if (logger != null) {
            logger.warn(str);
        }
    }

    public static void zzf(String str, Object obj) {
        String string;
        zzaon zzaonVarZzyt = zzaon.zzyt();
        if (zzaonVarZzyt != null) {
            zzaonVarZzyt.zze(str, obj);
        } else if (zzad(3)) {
            if (obj != null) {
                String strValueOf = String.valueOf(obj);
                string = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(strValueOf).length()).append(str).append(":").append(strValueOf).toString();
            } else {
                string = str;
            }
            Log.e(zzaod.zzdra.get(), string);
        }
        Logger logger = zzdtg;
        if (logger != null) {
            logger.error(str);
        }
    }
}
