package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.util.zzv;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class zzo {

    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 11400000;
    private static boolean zzffv = false;
    private static boolean zzffw = false;
    private static boolean zzffx = false;
    private static boolean zzffy = false;
    static final AtomicBoolean zzffz = new AtomicBoolean();
    private static final AtomicBoolean zzfga = new AtomicBoolean();

    zzo() {
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zze.zzaex().getErrorResolutionPendingIntent(context, i, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.getStatusString(i);
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        try {
            context.getResources().getString(R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName()) && !zzfga.get()) {
            int iZzcg = zzbe.zzcg(context);
            if (iZzcg == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            }
            if (iZzcg != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                throw new IllegalStateException(new StringBuilder(String.valueOf("com.google.android.gms.version").length() + 290).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(GOOGLE_PLAY_SERVICES_VERSION_CODE).append(" but found ").append(iZzcg).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"").append("com.google.android.gms.version").append("\" android:value=\"@integer/google_play_services_version\" />").toString());
            }
        }
        boolean z = (com.google.android.gms.common.util.zzi.zzcj(context) || com.google.android.gms.common.util.zzi.zzcl(context)) ? false : true;
        PackageInfo packageInfo = null;
        if (z) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            zzp.zzbz(context);
            if (z) {
                zzg zzgVarZza = zzp.zza(packageInfo, zzj.zzffr);
                if (zzgVarZza == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                    return 9;
                }
                if (zzp.zza(packageInfo2, zzgVarZza) == null) {
                    Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                    return 9;
                }
            } else if (zzp.zza(packageInfo2, zzj.zzffr) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (packageInfo2.versionCode / 1000 < GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000) {
                Log.w("GooglePlayServicesUtil", new StringBuilder(77).append("Google Play services out of date.  Requires ").append(GOOGLE_PLAY_SERVICES_VERSION_CODE).append(" but found ").append(packageInfo2.versionCode).toString());
                return 2;
            }
            ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
            if (applicationInfo == null) {
                try {
                    applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", e2);
                    return 1;
                }
            }
            return !applicationInfo.enabled ? 3 : 0;
        } catch (PackageManager.NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            default:
                return false;
        }
    }

    @TargetApi(19)
    @Deprecated
    public static boolean zzb(Context context, int i, String str) {
        return zzv.zzb(context, i, str);
    }

    @Deprecated
    public static void zzbj(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int iIsGooglePlayServicesAvailable = zze.zzaex().isGooglePlayServicesAvailable(context);
        if (iIsGooglePlayServicesAvailable != 0) {
            zze.zzaex();
            Intent intentZza = zze.zza(context, iIsGooglePlayServicesAvailable, "e");
            Log.e("GooglePlayServicesUtil", new StringBuilder(57).append("GooglePlayServices not available due to error ").append(iIsGooglePlayServicesAvailable).toString());
            if (intentZza != null) {
                throw new GooglePlayServicesRepairableException(iIsGooglePlayServicesAvailable, "Google Play Services not available", intentZza);
            }
            throw new GooglePlayServicesNotAvailableException(iIsGooglePlayServicesAvailable);
        }
    }

    @Deprecated
    public static void zzbv(Context context) {
        if (zzffz.getAndSet(true)) {
            return;
        }
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(10436);
            }
        } catch (SecurityException e) {
        }
    }

    @Deprecated
    public static int zzbw(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean zzby(android.content.Context r7) {
        /*
            r0 = 0
            r1 = 1
            boolean r2 = com.google.android.gms.common.zzo.zzffy
            if (r2 != 0) goto L2d
            com.google.android.gms.internal.zzbec r2 = com.google.android.gms.internal.zzbed.zzcr(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            java.lang.String r3 = "com.google.android.gms"
            r4 = 64
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            if (r2 == 0) goto L3d
            com.google.android.gms.common.zzp.zzbz(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            r3 = 1
            com.google.android.gms.common.zzg[] r3 = new com.google.android.gms.common.zzg[r3]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            r4 = 0
            com.google.android.gms.common.zzg[] r5 = com.google.android.gms.common.zzj.zzffr     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            r6 = 1
            r5 = r5[r6]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            r3[r4] = r5     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            com.google.android.gms.common.zzg r2 = com.google.android.gms.common.zzp.zza(r2, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            if (r2 == 0) goto L3d
            r2 = 1
            com.google.android.gms.common.zzo.zzffx = r2     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
        L2b:
            com.google.android.gms.common.zzo.zzffy = r1
        L2d:
            boolean r2 = com.google.android.gms.common.zzo.zzffx
            if (r2 != 0) goto L3b
            java.lang.String r2 = "user"
            java.lang.String r3 = android.os.Build.TYPE
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L3c
        L3b:
            r0 = r1
        L3c:
            return r0
        L3d:
            r2 = 0
            com.google.android.gms.common.zzo.zzffx = r2     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L41 java.lang.Throwable -> L4c
            goto L2b
        L41:
            r2 = move-exception
            java.lang.String r3 = "GooglePlayServicesUtil"
            java.lang.String r4 = "Cannot find Google Play services package name."
            android.util.Log.w(r3, r4, r2)     // Catch: java.lang.Throwable -> L4c
            com.google.android.gms.common.zzo.zzffy = r1
            goto L2d
        L4c:
            r0 = move-exception
            com.google.android.gms.common.zzo.zzffy = r1
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zzo.zzby(android.content.Context):boolean");
    }

    @Deprecated
    public static boolean zze(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return zzx(context, "com.google.android.gms");
        }
        return false;
    }

    @Deprecated
    public static boolean zzf(Context context, int i) {
        return zzv.zzf(context, i);
    }

    @TargetApi(MotionEventCompat.AXIS_WHEEL)
    static boolean zzx(Context context, String str) throws PackageManager.NameNotFoundException {
        Bundle applicationRestrictions;
        boolean zEquals = str.equals("com.google.android.gms");
        if (com.google.android.gms.common.util.zzp.zzalk()) {
            try {
                Iterator<PackageInstaller.SessionInfo> it = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
                while (it.hasNext()) {
                    if (str.equals(it.next().getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (zEquals) {
                return applicationInfo.enabled;
            }
            if (applicationInfo.enabled) {
                if (!(com.google.android.gms.common.util.zzp.zzalh() && (applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName())) != null && "true".equals(applicationRestrictions.getString("restricted_profile")))) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }
}
