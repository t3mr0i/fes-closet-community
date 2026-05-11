package com.google.android.gms.tagmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.VisibleForTesting;
import java.util.Random;

/* loaded from: classes.dex */
public final class zzai {
    private final Context mContext;
    private final Random zzbdr;
    private final String zzjoz;

    public zzai(Context context, String str) {
        this(context, str, new Random());
    }

    @VisibleForTesting
    private zzai(Context context, String str, Random random) {
        this.mContext = (Context) com.google.android.gms.common.internal.zzbp.zzu(context);
        this.zzjoz = (String) com.google.android.gms.common.internal.zzbp.zzu(str);
        this.zzbdr = random;
    }

    private final SharedPreferences zzbdc() {
        Context context = this.mContext;
        String strValueOf = String.valueOf("_gtmContainerRefreshPolicy_");
        String strValueOf2 = String.valueOf(this.zzjoz);
        return context.getSharedPreferences(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf), 0);
    }

    private final long zzg(long j, long j2) {
        long jMax = Math.max(0L, zzbdc().getLong("FORBIDDEN_COUNT", 0L));
        return (long) ((((long) ((jMax / ((Math.max(0L, r0.getLong("SUCCESSFUL_COUNT", 0L)) + jMax) + 1)) * (j2 - j))) + j) * this.zzbdr.nextFloat());
    }

    public final long zzbcy() {
        return 43200000 + zzg(7200000L, 259200000L);
    }

    public final long zzbcz() {
        return 3600000 + zzg(600000L, 86400000L);
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzbda() {
        SharedPreferences sharedPreferencesZzbdc = zzbdc();
        long j = sharedPreferencesZzbdc.getLong("FORBIDDEN_COUNT", 0L);
        long j2 = sharedPreferencesZzbdc.getLong("SUCCESSFUL_COUNT", 0L);
        SharedPreferences.Editor editorEdit = sharedPreferencesZzbdc.edit();
        long jMin = j == 0 ? 3L : Math.min(10L, 1 + j);
        long jMax = Math.max(0L, Math.min(j2, 10 - jMin));
        editorEdit.putLong("FORBIDDEN_COUNT", jMin);
        editorEdit.putLong("SUCCESSFUL_COUNT", jMax);
        editorEdit.apply();
    }

    @SuppressLint({"CommitPrefEdits"})
    public final void zzbdb() {
        SharedPreferences sharedPreferencesZzbdc = zzbdc();
        long j = sharedPreferencesZzbdc.getLong("SUCCESSFUL_COUNT", 0L);
        long j2 = sharedPreferencesZzbdc.getLong("FORBIDDEN_COUNT", 0L);
        long jMin = Math.min(10L, j + 1);
        long jMax = Math.max(0L, Math.min(j2, 10 - jMin));
        SharedPreferences.Editor editorEdit = sharedPreferencesZzbdc.edit();
        editorEdit.putLong("SUCCESSFUL_COUNT", jMin);
        editorEdit.putLong("FORBIDDEN_COUNT", jMax);
        editorEdit.apply();
    }
}
