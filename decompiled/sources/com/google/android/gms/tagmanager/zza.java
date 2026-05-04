package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* loaded from: classes.dex */
public final class zza {
    private static Object zzjon = new Object();
    private static zza zzjoo;
    private volatile boolean mClosed;
    private final Context mContext;
    private final com.google.android.gms.common.util.zzd zzasb;
    private final Thread zzdbd;
    private volatile AdvertisingIdClient.Info zzdnp;
    private volatile long zzjoh;
    private volatile long zzjoi;
    private volatile long zzjoj;
    private volatile long zzjok;
    private final Object zzjol;
    private zzd zzjom;

    private zza(Context context) {
        this(context, null, com.google.android.gms.common.util.zzh.zzald());
    }

    private zza(Context context, zzd zzdVar, com.google.android.gms.common.util.zzd zzdVar2) {
        this.zzjoh = 900000L;
        this.zzjoi = 30000L;
        this.mClosed = false;
        this.zzjol = new Object();
        this.zzjom = new zzb(this);
        this.zzasb = zzdVar2;
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        this.zzjoj = this.zzasb.currentTimeMillis();
        this.zzdbd = new Thread(new zzc(this));
    }

    private final void zzbcf() {
        synchronized (this) {
            try {
                if (!this.mClosed) {
                    zzbcg();
                    wait(500L);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    private final void zzbcg() {
        if (this.zzasb.currentTimeMillis() - this.zzjoj > this.zzjoi) {
            synchronized (this.zzjol) {
                this.zzjol.notify();
            }
            this.zzjoj = this.zzasb.currentTimeMillis();
        }
    }

    private final void zzbch() {
        if (this.zzasb.currentTimeMillis() - this.zzjok > 3600000) {
            this.zzdnp = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbci() throws SecurityException, IllegalArgumentException {
        Process.setThreadPriority(10);
        while (!this.mClosed) {
            AdvertisingIdClient.Info infoZzbcj = this.zzjom.zzbcj();
            if (infoZzbcj != null) {
                this.zzdnp = infoZzbcj;
                this.zzjok = this.zzasb.currentTimeMillis();
                zzdj.zzcq("Obtained fresh AdvertisingId info from GmsCore.");
            }
            synchronized (this) {
                notifyAll();
            }
            try {
                synchronized (this.zzjol) {
                    this.zzjol.wait(this.zzjoh);
                }
            } catch (InterruptedException e) {
                zzdj.zzcq("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    public static zza zzdp(Context context) {
        if (zzjoo == null) {
            synchronized (zzjon) {
                if (zzjoo == null) {
                    zza zzaVar = new zza(context);
                    zzjoo = zzaVar;
                    zzaVar.zzdbd.start();
                }
            }
        }
        return zzjoo;
    }

    public final void close() {
        this.mClosed = true;
        this.zzdbd.interrupt();
    }

    public final boolean isLimitAdTrackingEnabled() {
        if (this.zzdnp == null) {
            zzbcf();
        } else {
            zzbcg();
        }
        zzbch();
        if (this.zzdnp == null) {
            return true;
        }
        return this.zzdnp.isLimitAdTrackingEnabled();
    }

    public final String zzbce() {
        if (this.zzdnp == null) {
            zzbcf();
        } else {
            zzbcg();
        }
        zzbch();
        if (this.zzdnp == null) {
            return null;
        }
        return this.zzdnp.getId();
    }
}
