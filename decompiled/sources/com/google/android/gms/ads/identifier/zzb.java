package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.zzo;
import java.util.HashMap;

/* loaded from: classes.dex */
public class zzb {
    private static zzb zzalx;
    private final Context zzahy;

    private zzb(Context context) {
        this.zzahy = context;
    }

    private final void zza(final AdvertisingIdClient.Info info, final boolean z, final long j) {
        if (Math.random() > new zzd(this.zzahy).getFloat("gads:ad_id_use_shared_preference:ping_ratio", 0.0f)) {
            return;
        }
        new Thread(new Runnable(info, z, j) { // from class: com.google.android.gms.ads.identifier.zzc
            private final AdvertisingIdClient.Info zzaly;
            private final boolean zzalz;
            private final long zzama;

            {
                this.zzaly = info;
                this.zzalz = z;
                this.zzama = j;
            }

            @Override // java.lang.Runnable
            public final void run() {
                AdvertisingIdClient.Info info2 = this.zzaly;
                boolean z2 = this.zzalz;
                long j2 = this.zzama;
                HashMap map = new HashMap();
                map.put("ad_id_size", Integer.toString(info2 == null ? -1 : info2.getId().length()));
                map.put("has_gmscore", z2 ? "1" : "0");
                map.put("tag", "AdvertisingIdLightClient");
                map.put("time_spent", Long.toString(j2));
                new zze().zzb(map);
            }
        }).start();
    }

    public static zzb zzd(Context context) {
        zzb zzbVar;
        synchronized (zzb.class) {
            if (zzalx == null) {
                zzalx = new zzb(context);
            }
            zzbVar = zzalx;
        }
        return zzbVar;
    }

    public final AdvertisingIdClient.Info getInfo() {
        SharedPreferences sharedPreferences;
        AdvertisingIdClient.Info info = null;
        Context remoteContext = zzo.getRemoteContext(this.zzahy);
        if (remoteContext == null || (sharedPreferences = remoteContext.getSharedPreferences("adid_settings", 0)) == null) {
            zza(null, false, -1L);
        } else {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (sharedPreferences.contains("adid_key") && sharedPreferences.contains("enable_limit_ad_tracking")) {
                info = new AdvertisingIdClient.Info(sharedPreferences.getString("adid_key", ""), sharedPreferences.getBoolean("enable_limit_ad_tracking", false));
            }
            zza(info, true, SystemClock.elapsedRealtime() - jElapsedRealtime);
        }
        return info;
    }
}
