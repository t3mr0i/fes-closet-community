package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import java.io.File;

/* loaded from: classes.dex */
public final class zzt {
    @TargetApi(MotionEventCompat.AXIS_WHEEL)
    public static File getNoBackupFilesDir(Context context) {
        return zzp.zzalk() ? context.getNoBackupFilesDir() : zzd(new File(context.getApplicationInfo().dataDir, "no_backup"));
    }

    private static synchronized File zzd(File file) {
        if (!file.exists() && !file.mkdirs() && !file.exists()) {
            String strValueOf = String.valueOf(file.getPath());
            Log.w("SupportV4Utils", strValueOf.length() != 0 ? "Unable to create no-backup dir ".concat(strValueOf) : new String("Unable to create no-backup dir "));
            file = null;
        }
        return file;
    }
}
