package com.google.android.gms.common.internal;

import android.util.Log;

/* loaded from: classes.dex */
public final class zzak {
    private static int zzfvh = 15;
    private static final String zzfvi = null;
    private final String zzfvj;
    private final String zzfvk;

    public zzak(String str) {
        this(str, null);
    }

    private zzak(String str, String str2) {
        zzbp.zzb(str, "log tag cannot be null");
        zzbp.zzb(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.zzfvj = str;
        this.zzfvk = null;
    }

    private final boolean zzcf(int i) {
        return Log.isLoggable(this.zzfvj, i);
    }

    private final String zzgf(String str) {
        return this.zzfvk == null ? str : this.zzfvk.concat(str);
    }

    public final void zzb(String str, String str2, Throwable th) {
        if (zzcf(4)) {
            Log.i(str, zzgf(str2), th);
        }
    }

    public final void zzc(String str, String str2, Throwable th) {
        if (zzcf(5)) {
            Log.w(str, zzgf(str2), th);
        }
    }

    public final void zzd(String str, String str2, Throwable th) {
        if (zzcf(6)) {
            Log.e(str, zzgf(str2), th);
        }
    }

    public final void zze(String str, String str2, Throwable th) {
        if (zzcf(7)) {
            Log.e(str, zzgf(str2), th);
            Log.wtf(str, zzgf(str2), th);
        }
    }

    public final void zzv(String str, String str2) {
        if (zzcf(3)) {
            Log.d(str, zzgf(str2));
        }
    }

    public final void zzw(String str, String str2) {
        if (zzcf(5)) {
            Log.w(str, zzgf(str2));
        }
    }

    public final void zzx(String str, String str2) {
        if (zzcf(6)) {
            Log.e(str, zzgf(str2));
        }
    }
}
