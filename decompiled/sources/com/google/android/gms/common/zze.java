package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaj;
import com.google.android.gms.internal.zzbed;

/* loaded from: classes.dex */
public class zze {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzo.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zze zzffj = new zze();

    zze() {
    }

    @Nullable
    public static Intent zza(Context context, int i, @Nullable String str) {
        switch (i) {
            case 1:
            case 2:
                return (context == null || !com.google.android.gms.common.util.zzi.zzcj(context)) ? zzaj.zzu("com.google.android.gms", zzw(context, str)) : zzaj.zzakk();
            case 3:
                return zzaj.zzge("com.google.android.gms");
            default:
                return null;
        }
    }

    public static zze zzaex() {
        return zzffj;
    }

    public static void zzbu(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzo.zzbj(context);
    }

    public static void zzbv(Context context) {
        zzo.zzbv(context);
    }

    public static int zzbw(Context context) {
        return zzo.zzbw(context);
    }

    public static boolean zze(Context context, int i) {
        return zzo.zze(context, i);
    }

    private static String zzw(@Nullable Context context, @Nullable String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        sb.append("-");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        if (context != null) {
            try {
                sb.append(zzbed.zzcr(context).getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
        return sb.toString();
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return zza(context, i, i2, null);
    }

    public String getErrorString(int i) {
        return zzo.getErrorString(i);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        int iIsGooglePlayServicesAvailable = zzo.isGooglePlayServicesAvailable(context);
        if (zzo.zze(context, iIsGooglePlayServicesAvailable)) {
            return 18;
        }
        return iIsGooglePlayServicesAvailable;
    }

    public boolean isUserResolvableError(int i) {
        return zzo.isUserRecoverableError(i);
    }

    @Nullable
    public final PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        Intent intentZza = zza(context, i, str);
        if (intentZza == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i2, intentZza, 268435456);
    }

    @Nullable
    @Deprecated
    public final Intent zzbn(int i) {
        return zza(null, i, null);
    }
}
