package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.internal.zzbed;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class zzw {
    private static final Method zzfzh = zzalm();
    private static final Method zzfzi = zzaln();
    private static final Method zzfzj = zzalo();
    private static final Method zzfzk = zzalp();
    private static final Method zzfzl = zzalq();

    private static int zza(WorkSource workSource) {
        if (zzfzj != null) {
            try {
                return ((Integer) zzfzj.invoke(workSource, new Object[0])).intValue();
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return 0;
    }

    @Nullable
    private static String zza(WorkSource workSource, int i) {
        if (zzfzl != null) {
            try {
                return (String) zzfzl.invoke(workSource, Integer.valueOf(i));
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        }
        return null;
    }

    @Nullable
    public static WorkSource zzac(Context context, @Nullable String str) {
        if (context == null || context.getPackageManager() == null || str == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = zzbed.zzcr(context).getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return zze(applicationInfo.uid, str);
            }
            String strValueOf = String.valueOf(str);
            Log.e("WorkSourceUtil", strValueOf.length() != 0 ? "Could not get applicationInfo from package: ".concat(strValueOf) : new String("Could not get applicationInfo from package: "));
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf2 = String.valueOf(str);
            Log.e("WorkSourceUtil", strValueOf2.length() != 0 ? "Could not find package: ".concat(strValueOf2) : new String("Could not find package: "));
            return null;
        }
    }

    private static Method zzalm() {
        try {
            return WorkSource.class.getMethod(ProductAction.ACTION_ADD, Integer.TYPE);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method zzaln() {
        if (!zzp.zzalh()) {
            return null;
        }
        try {
            return WorkSource.class.getMethod(ProductAction.ACTION_ADD, Integer.TYPE, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method zzalo() {
        try {
            return WorkSource.class.getMethod("size", new Class[0]);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method zzalp() {
        try {
            return WorkSource.class.getMethod("get", Integer.TYPE);
        } catch (Exception e) {
            return null;
        }
    }

    private static Method zzalq() {
        if (!zzp.zzalh()) {
            return null;
        }
        try {
            return WorkSource.class.getMethod("getName", Integer.TYPE);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> zzb(@Nullable WorkSource workSource) {
        int iZza = workSource == null ? 0 : zza(workSource);
        if (iZza == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iZza; i++) {
            String strZza = zza(workSource, i);
            if (!zzs.zzgm(strZza)) {
                arrayList.add(strZza);
            }
        }
        return arrayList;
    }

    public static boolean zzco(Context context) {
        return (context == null || context.getPackageManager() == null || zzbed.zzcr(context).checkPermission("android.permission.UPDATE_DEVICE_STATS", context.getPackageName()) != 0) ? false : true;
    }

    private static WorkSource zze(int i, String str) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        WorkSource workSource = new WorkSource();
        if (zzfzi != null) {
            if (str == null) {
                str = "";
            }
            try {
                zzfzi.invoke(workSource, Integer.valueOf(i), str);
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        } else if (zzfzh != null) {
            try {
                zzfzh.invoke(workSource, Integer.valueOf(i));
            } catch (Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        }
        return workSource;
    }
}
