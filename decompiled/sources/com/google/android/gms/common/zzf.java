package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzaz;
import com.google.android.gms.common.internal.zzba;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzf {
    private static zzaz zzffk;
    private static final Object zzffl = new Object();
    private static Context zzffm;

    static boolean zza(String str, zzg zzgVar) {
        return zza(str, zzgVar, false);
    }

    private static boolean zza(String str, zzg zzgVar, boolean z) {
        if (!zzaey()) {
            return false;
        }
        zzbp.zzu(zzffm);
        try {
            return zzffk.zza(new zzm(str, zzgVar, z), com.google.android.gms.dynamic.zzn.zzw(zzffm.getPackageManager()));
        } catch (RemoteException e) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            return false;
        }
    }

    private static boolean zzaey() {
        boolean z = true;
        if (zzffk == null) {
            zzbp.zzu(zzffm);
            synchronized (zzffl) {
                if (zzffk == null) {
                    try {
                        zzffk = zzba.zzal(DynamiteModule.zza(zzffm, DynamiteModule.zzgpq, "com.google.android.gms.googlecertificates").zzgv("com.google.android.gms.common.GoogleCertificatesImpl"));
                    } catch (DynamiteModule.zzc e) {
                        Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", e);
                        z = false;
                    }
                }
            }
        }
        return z;
    }

    static boolean zzb(String str, zzg zzgVar) {
        return zza(str, zzgVar, true);
    }

    static synchronized void zzbx(Context context) {
        if (zzffm != null) {
            Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
        } else if (context != null) {
            zzffm = context.getApplicationContext();
        }
    }
}
