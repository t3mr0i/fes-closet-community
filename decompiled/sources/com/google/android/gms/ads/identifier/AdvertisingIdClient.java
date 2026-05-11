package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzew;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@KeepForSdkWithMembers
/* loaded from: classes.dex */
public class AdvertisingIdClient {
    private final Context mContext;

    @Nullable
    private com.google.android.gms.common.zza zzalk;

    @Nullable
    private zzev zzall;
    private boolean zzalm;
    private Object zzaln;

    @Nullable
    private zza zzalo;
    private long zzalp;

    public static final class Info {
        private final String zzalv;
        private final boolean zzalw;

        public Info(String str, boolean z) {
            this.zzalv = str;
            this.zzalw = z;
        }

        public final String getId() {
            return this.zzalv;
        }

        public final boolean isLimitAdTrackingEnabled() {
            return this.zzalw;
        }

        public final String toString() {
            String str = this.zzalv;
            return new StringBuilder(String.valueOf(str).length() + 7).append("{").append(str).append("}").append(this.zzalw).toString();
        }
    }

    static class zza extends Thread {
        private WeakReference<AdvertisingIdClient> zzalr;
        private long zzals;
        CountDownLatch zzalt = new CountDownLatch(1);
        boolean zzalu = false;

        public zza(AdvertisingIdClient advertisingIdClient, long j) {
            this.zzalr = new WeakReference<>(advertisingIdClient);
            this.zzals = j;
            start();
        }

        private final void disconnect() {
            AdvertisingIdClient advertisingIdClient = this.zzalr.get();
            if (advertisingIdClient != null) {
                advertisingIdClient.finish();
                this.zzalu = true;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                if (this.zzalt.await(this.zzals, TimeUnit.MILLISECONDS)) {
                    return;
                }
                disconnect();
            } catch (InterruptedException e) {
                disconnect();
            }
        }
    }

    public AdvertisingIdClient(Context context) {
        this(context, 30000L, false);
    }

    public AdvertisingIdClient(Context context, long j, boolean z) {
        this.zzaln = new Object();
        zzbp.zzu(context);
        if (z) {
            Context applicationContext = context.getApplicationContext();
            this.mContext = applicationContext != null ? applicationContext : context;
        } else {
            this.mContext = context;
        }
        this.zzalm = false;
        this.zzalp = j;
    }

    public static Info getAdvertisingIdInfo(Context context) throws GooglePlayServicesRepairableException, IllegalStateException, GooglePlayServicesNotAvailableException, IOException {
        Info info;
        zzd zzdVar = new zzd(context);
        boolean z = zzdVar.getBoolean("gads:ad_id_app_context:enabled", false);
        float f = zzdVar.getFloat("gads:ad_id_app_context:ping_ratio", 0.0f);
        boolean z2 = zzdVar.getBoolean("gads:ad_id_use_shared_preference:enabled", false);
        String string = zzdVar.getString("gads:ad_id_use_shared_preference:experiment_id", "");
        if (!z2 || (info = zzb.zzd(context).getInfo()) == null) {
            AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(context, -1L, z);
            try {
                try {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    advertisingIdClient.start(false);
                    info = advertisingIdClient.getInfo();
                    advertisingIdClient.zza(info, z, f, SystemClock.elapsedRealtime() - jElapsedRealtime, string, null);
                } finally {
                }
            } finally {
                advertisingIdClient.finish();
            }
        }
        return info;
    }

    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    private final void start(boolean z) throws GooglePlayServicesRepairableException, IllegalStateException, GooglePlayServicesNotAvailableException, IOException {
        zzbp.zzgh("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzalm) {
                finish();
            }
            this.zzalk = zzc(this.mContext);
            this.zzall = zza(this.mContext, this.zzalk);
            this.zzalm = true;
            if (z) {
                zzbh();
            }
        }
    }

    private static zzev zza(Context context, com.google.android.gms.common.zza zzaVar) throws IOException {
        try {
            return zzew.zzc(zzaVar.zza(10000L, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new IOException("Interrupted exception");
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }

    private final boolean zza(Info info, boolean z, float f, long j, String str, Throwable th) {
        if (Math.random() > f) {
            return false;
        }
        HashMap map = new HashMap();
        map.put("app_context", z ? "1" : "0");
        if (info != null) {
            map.put("limit_ad_tracking", info.isLimitAdTrackingEnabled() ? "1" : "0");
        }
        if (info != null && info.getId() != null) {
            map.put("ad_id_size", Integer.toString(info.getId().length()));
        }
        if (th != null) {
            map.put("error", th.getClass().getName());
        }
        if (str != null && !str.isEmpty()) {
            map.put("experiment_id", str);
        }
        map.put("tag", "AdvertisingIdClient");
        map.put("time_spent", Long.toString(j));
        new com.google.android.gms.ads.identifier.zza(this, map).start();
        return true;
    }

    private final void zzbh() {
        synchronized (this.zzaln) {
            if (this.zzalo != null) {
                this.zzalo.zzalt.countDown();
                try {
                    this.zzalo.join();
                } catch (InterruptedException e) {
                }
            }
            if (this.zzalp > 0) {
                this.zzalo = new zza(this, this.zzalp);
            }
        }
    }

    private static com.google.android.gms.common.zza zzc(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException, PackageManager.NameNotFoundException, IOException {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            switch (com.google.android.gms.common.zze.zzaex().isGooglePlayServicesAvailable(context)) {
                case 0:
                case 2:
                    com.google.android.gms.common.zza zzaVar = new com.google.android.gms.common.zza();
                    Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                    intent.setPackage("com.google.android.gms");
                    try {
                        if (com.google.android.gms.common.stats.zza.zzakz().zza(context, intent, zzaVar, 1)) {
                            return zzaVar;
                        }
                        throw new IOException("Connection failure");
                    } catch (Throwable th) {
                        throw new IOException(th);
                    }
                case 1:
                default:
                    throw new IOException("Google Play services not available");
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new GooglePlayServicesNotAvailableException(9);
        }
    }

    protected void finalize() throws Throwable {
        finish();
        super.finalize();
    }

    public void finish() {
        zzbp.zzgh("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.mContext == null || this.zzalk == null) {
                return;
            }
            try {
                if (this.zzalm) {
                    com.google.android.gms.common.stats.zza.zzakz();
                    this.mContext.unbindService(this.zzalk);
                }
            } catch (Throwable th) {
                Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", th);
            }
            this.zzalm = false;
            this.zzall = null;
            this.zzalk = null;
        }
    }

    public Info getInfo() throws IOException {
        Info info;
        zzbp.zzgh("Calling this from your main thread can lead to deadlock");
        synchronized (this) {
            if (this.zzalm) {
                zzbp.zzu(this.zzalk);
                zzbp.zzu(this.zzall);
                info = new Info(this.zzall.getId(), this.zzall.zzb(true));
            } else {
                synchronized (this.zzaln) {
                    if (this.zzalo == null || !this.zzalo.zzalu) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    start(false);
                    if (!this.zzalm) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                    zzbp.zzu(this.zzalk);
                    zzbp.zzu(this.zzall);
                    try {
                        info = new Info(this.zzall.getId(), this.zzall.zzb(true));
                    } catch (RemoteException e) {
                        Log.i("AdvertisingIdClient", "GMS remote exception ", e);
                        throw new IOException("Remote exception");
                    }
                } catch (Exception e2) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", e2);
                }
            }
        }
        zzbh();
        return info;
    }

    public void start() throws GooglePlayServicesRepairableException, IllegalStateException, GooglePlayServicesNotAvailableException, IOException {
        start(true);
    }
}
