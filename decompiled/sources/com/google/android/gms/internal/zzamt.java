package com.google.android.gms.internal;

/* loaded from: classes.dex */
public final class zzamt {
    public static final String VERSION = String.valueOf(com.google.android.gms.common.zze.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    public static final String zzdoe;

    static {
        String strValueOf = String.valueOf(VERSION);
        zzdoe = strValueOf.length() != 0 ? "ma".concat(strValueOf) : new String("ma");
    }
}
