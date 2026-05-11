package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.internal.zzbed;

/* loaded from: classes.dex */
public final class zzbe {
    private static Object zzaqc = new Object();
    private static boolean zzcli;
    private static String zzfvn;
    private static int zzfvo;

    public static String zzcf(Context context) {
        zzch(context);
        return zzfvn;
    }

    public static int zzcg(Context context) {
        zzch(context);
        return zzfvo;
    }

    private static void zzch(Context context) {
        Bundle bundle;
        synchronized (zzaqc) {
            if (zzcli) {
                return;
            }
            zzcli = true;
            try {
                bundle = zzbed.zzcr(context).getApplicationInfo(context.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf("MetadataValueReader", "This should never happen.", e);
            }
            if (bundle == null) {
                return;
            }
            zzfvn = bundle.getString("com.google.app.id");
            zzfvo = bundle.getInt("com.google.android.gms.version");
        }
    }
}
