package com.google.android.gms.internal;

/* loaded from: classes.dex */
public enum zzanr {
    NONE,
    GZIP;

    public static zzanr zzdw(String str) {
        return "GZIP".equalsIgnoreCase(str) ? GZIP : NONE;
    }
}
