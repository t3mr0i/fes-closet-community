package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class zzs {
    private static final Pattern zzfzg = Pattern.compile("\\$\\{(.*?)\\}");

    public static boolean zzgm(@Nullable String str) {
        return str == null || str.trim().isEmpty();
    }
}
