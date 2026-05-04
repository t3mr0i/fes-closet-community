package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzbed;

/* loaded from: classes.dex */
public final class zzc {
    @Nullable
    private static PackageInfo zzaa(Context context, String str) {
        try {
            return zzbed.zzcr(context).getPackageInfo(str, 128);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static boolean zzab(Context context, String str) {
        "com.google.android.gms".equals(str);
        try {
            return (zzbed.zzcr(context).getApplicationInfo(str, 0).flags & 2097152) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static int zzz(Context context, String str) {
        Bundle bundle;
        PackageInfo packageInfoZzaa = zzaa(context, str);
        if (packageInfoZzaa == null || packageInfoZzaa.applicationInfo == null || (bundle = packageInfoZzaa.applicationInfo.metaData) == null) {
            return -1;
        }
        return bundle.getInt("com.google.android.gms.version", -1);
    }
}
