package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbed;

/* loaded from: classes.dex */
public class zzp {
    private static zzp zzfgb;
    private final Context mContext;

    private zzp(Context context) {
        this.mContext = context.getApplicationContext();
    }

    static zzg zza(PackageInfo packageInfo, zzg... zzgVarArr) {
        if (packageInfo.signatures == null) {
            return null;
        }
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        zzh zzhVar = new zzh(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < zzgVarArr.length; i++) {
            if (zzgVarArr[i].equals(zzhVar)) {
                return zzgVarArr[i];
            }
        }
        return null;
    }

    private static boolean zza(PackageInfo packageInfo, boolean z) {
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z ? zza(packageInfo, zzj.zzffr) : zza(packageInfo, zzj.zzffr[0])) != null) {
                return true;
            }
        }
        return false;
    }

    private static boolean zzb(PackageInfo packageInfo, boolean z) {
        boolean zZzb = false;
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
        } else {
            zzh zzhVar = new zzh(packageInfo.signatures[0].toByteArray());
            String str = packageInfo.packageName;
            zZzb = z ? zzf.zzb(str, zzhVar) : zzf.zza(str, zzhVar);
            if (!zZzb) {
                Log.d("GoogleSignatureVerifier", new StringBuilder(27).append("Cert not in list. atk=").append(z).toString());
            }
        }
        return zZzb;
    }

    public static zzp zzbz(Context context) {
        zzbp.zzu(context);
        synchronized (zzp.class) {
            if (zzfgb == null) {
                zzf.zzbx(context);
                zzfgb = new zzp(context);
            }
        }
        return zzfgb;
    }

    private final boolean zzfs(String str) {
        try {
            PackageInfo packageInfo = zzbed.zzcr(this.mContext).getPackageInfo(str, 64);
            if (packageInfo == null) {
                return false;
            }
            if (zzo.zzby(this.mContext)) {
                return zzb(packageInfo, true);
            }
            boolean zZzb = zzb(packageInfo, false);
            if (!zZzb && zzb(packageInfo, true)) {
                Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
            }
            return zZzb;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Deprecated
    public final boolean zza(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo != null) {
            if (zza(packageInfo, false)) {
                return true;
            }
            if (zza(packageInfo, true)) {
                if (zzo.zzby(this.mContext)) {
                    return true;
                }
                Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
            }
        }
        return false;
    }

    public final boolean zzbo(int i) {
        String[] packagesForUid = zzbed.zzcr(this.mContext).getPackagesForUid(i);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return false;
        }
        for (String str : packagesForUid) {
            if (zzfs(str)) {
                return true;
            }
        }
        return false;
    }
}
