package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.RequiresPermission;
import android.support.v4.view.MotionEventCompat;
import com.google.android.gms.internal.zzaoy;

/* loaded from: classes.dex */
public final class zzaov<T extends Context & zzaoy> {
    private static Boolean zzdtx;
    private final Handler mHandler;
    private final T zzdtw;

    public zzaov(T t) {
        com.google.android.gms.common.internal.zzbp.zzu(t);
        this.zzdtw = t;
        this.mHandler = new Handler();
    }

    private final void zza(Integer num, JobParameters jobParameters) {
        zzamu zzamuVarZzbg = zzamu.zzbg(this.zzdtw);
        zzamuVarZzbg.zzwc().zza(new zzaow(this, num, zzamuVarZzbg, zzamuVarZzbg.zzvy(), jobParameters));
    }

    public static boolean zzbi(Context context) throws PackageManager.NameNotFoundException {
        com.google.android.gms.common.internal.zzbp.zzu(context);
        if (zzdtx != null) {
            return zzdtx.booleanValue();
        }
        boolean zZzv = zzapd.zzv(context, "com.google.android.gms.analytics.AnalyticsService");
        zzdtx = Boolean.valueOf(zZzv);
        return zZzv;
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public final void onCreate() {
        zzamu.zzbg(this.zzdtw).zzvy().zzdm("Local AnalyticsService is starting up");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public final void onDestroy() {
        zzamu.zzbg(this.zzdtw).zzvy().zzdm("Local AnalyticsService is shutting down");
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            synchronized (zzaou.zzaqc) {
                zzcqh zzcqhVar = zzaou.zzdtv;
                if (zzcqhVar != null && zzcqhVar.isHeld()) {
                    zzcqhVar.release();
                }
            }
        } catch (SecurityException e) {
        }
        zzaon zzaonVarZzvy = zzamu.zzbg(this.zzdtw).zzvy();
        if (intent == null) {
            zzaonVarZzvy.zzdp("AnalyticsService started with null intent");
        } else {
            String action = intent.getAction();
            zzaonVarZzvy.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
            if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
                zza(Integer.valueOf(i2), null);
            }
        }
        return 2;
    }

    @TargetApi(MotionEventCompat.AXIS_DISTANCE)
    public final boolean onStartJob(JobParameters jobParameters) {
        zzaon zzaonVarZzvy = zzamu.zzbg(this.zzdtw).zzvy();
        String string = jobParameters.getExtras().getString("action");
        zzaonVarZzvy.zza("Local AnalyticsJobService called. action", string);
        if (!"com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(string)) {
            return true;
        }
        zza(null, jobParameters);
        return true;
    }
}
