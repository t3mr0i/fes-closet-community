package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzbp;

/* loaded from: classes.dex */
public final class zzf {
    private static final String[] zzfyl = {"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static DropBoxManager zzfym = null;
    private static boolean zzfyn = false;
    private static int zzfyo = -1;
    private static int zzfyp = 0;

    public static boolean zza(Context context, Throwable th) {
        return zza(context, th, 0);
    }

    private static boolean zza(Context context, Throwable th, int i) {
        try {
            zzbp.zzu(context);
            zzbp.zzu(th);
        } catch (Exception e) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
        }
        return false;
    }
}
