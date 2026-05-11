package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;

/* loaded from: classes.dex */
public class zzamu {
    private static volatile zzamu zzdof;
    private final Context mContext;
    private final com.google.android.gms.common.util.zzd zzasb;
    private final Context zzdog;
    private final zzanv zzdoh;
    private final zzaon zzdoi;
    private final com.google.android.gms.analytics.zzj zzdoj;
    private final zzamj zzdok;
    private final zzaoa zzdol;
    private final zzape zzdom;
    private final zzaor zzdon;
    private final GoogleAnalytics zzdoo;
    private final zzanm zzdop;
    private final zzami zzdoq;
    private final zzanf zzdor;
    private final zzanz zzdos;

    private zzamu(zzamw zzamwVar) {
        Context applicationContext = zzamwVar.getApplicationContext();
        com.google.android.gms.common.internal.zzbp.zzb(applicationContext, "Application context can't be null");
        Context contextZzwl = zzamwVar.zzwl();
        com.google.android.gms.common.internal.zzbp.zzu(contextZzwl);
        this.mContext = applicationContext;
        this.zzdog = contextZzwl;
        this.zzasb = com.google.android.gms.common.util.zzh.zzald();
        this.zzdoh = new zzanv(this);
        zzaon zzaonVar = new zzaon(this);
        zzaonVar.initialize();
        this.zzdoi = zzaonVar;
        zzaon zzaonVarZzvy = zzvy();
        String str = zzamt.VERSION;
        zzaonVarZzvy.zzdo(new StringBuilder(String.valueOf(str).length() + 134).append("Google Analytics ").append(str).append(" is starting up. To enable debug logging on a device run:\n  adb shell setprop log.tag.GAv4 DEBUG\n  adb logcat -s GAv4").toString());
        zzaor zzaorVar = new zzaor(this);
        zzaorVar.initialize();
        this.zzdon = zzaorVar;
        zzape zzapeVar = new zzape(this);
        zzapeVar.initialize();
        this.zzdom = zzapeVar;
        zzamj zzamjVar = new zzamj(this, zzamwVar);
        zzanm zzanmVar = new zzanm(this);
        zzami zzamiVar = new zzami(this);
        zzanf zzanfVar = new zzanf(this);
        zzanz zzanzVar = new zzanz(this);
        com.google.android.gms.analytics.zzj zzjVarZzbf = com.google.android.gms.analytics.zzj.zzbf(applicationContext);
        zzjVarZzbf.zza(new zzamv(this));
        this.zzdoj = zzjVarZzbf;
        GoogleAnalytics googleAnalytics = new GoogleAnalytics(this);
        zzanmVar.initialize();
        this.zzdop = zzanmVar;
        zzamiVar.initialize();
        this.zzdoq = zzamiVar;
        zzanfVar.initialize();
        this.zzdor = zzanfVar;
        zzanzVar.initialize();
        this.zzdos = zzanzVar;
        zzaoa zzaoaVar = new zzaoa(this);
        zzaoaVar.initialize();
        this.zzdol = zzaoaVar;
        zzamjVar.initialize();
        this.zzdok = zzamjVar;
        googleAnalytics.initialize();
        this.zzdoo = googleAnalytics;
        zzamjVar.start();
    }

    private static void zza(zzams zzamsVar) {
        com.google.android.gms.common.internal.zzbp.zzb(zzamsVar, "Analytics service not created/initialized");
        com.google.android.gms.common.internal.zzbp.zzb(zzamsVar.isInitialized(), "Analytics service not initialized");
    }

    public static zzamu zzbg(Context context) {
        com.google.android.gms.common.internal.zzbp.zzu(context);
        if (zzdof == null) {
            synchronized (zzamu.class) {
                if (zzdof == null) {
                    com.google.android.gms.common.util.zzd zzdVarZzald = com.google.android.gms.common.util.zzh.zzald();
                    long jElapsedRealtime = zzdVarZzald.elapsedRealtime();
                    zzamu zzamuVar = new zzamu(new zzamw(context));
                    zzdof = zzamuVar;
                    GoogleAnalytics.zztw();
                    long jElapsedRealtime2 = zzdVarZzald.elapsedRealtime() - jElapsedRealtime;
                    long jLongValue = zzaod.zzdso.get().longValue();
                    if (jElapsedRealtime2 > jLongValue) {
                        zzamuVar.zzvy().zzc("Slow initialization (ms)", Long.valueOf(jElapsedRealtime2), Long.valueOf(jLongValue));
                    }
                }
            }
        }
        return zzdof;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final com.google.android.gms.common.util.zzd zzvx() {
        return this.zzasb;
    }

    public final zzaon zzvy() {
        zza(this.zzdoi);
        return this.zzdoi;
    }

    public final zzanv zzvz() {
        return this.zzdoh;
    }

    public final com.google.android.gms.analytics.zzj zzwa() {
        com.google.android.gms.common.internal.zzbp.zzu(this.zzdoj);
        return this.zzdoj;
    }

    public final zzamj zzwc() {
        zza(this.zzdok);
        return this.zzdok;
    }

    public final zzaoa zzwd() {
        zza(this.zzdol);
        return this.zzdol;
    }

    public final zzape zzwe() {
        zza(this.zzdom);
        return this.zzdom;
    }

    public final zzaor zzwf() {
        zza(this.zzdon);
        return this.zzdon;
    }

    public final zzanf zzwi() {
        zza(this.zzdor);
        return this.zzdor;
    }

    public final zzanz zzwj() {
        return this.zzdos;
    }

    public final Context zzwl() {
        return this.zzdog;
    }

    public final zzaon zzwm() {
        return this.zzdoi;
    }

    public final GoogleAnalytics zzwn() {
        com.google.android.gms.common.internal.zzbp.zzu(this.zzdoo);
        com.google.android.gms.common.internal.zzbp.zzb(this.zzdoo.isInitialized(), "Analytics instance not initialized");
        return this.zzdoo;
    }

    public final zzaor zzwo() {
        if (this.zzdon == null || !this.zzdon.isInitialized()) {
            return null;
        }
        return this.zzdon;
    }

    public final zzami zzwp() {
        zza(this.zzdoq);
        return this.zzdoq;
    }

    public final zzanm zzwq() {
        zza(this.zzdop);
        return this.zzdop;
    }
}
