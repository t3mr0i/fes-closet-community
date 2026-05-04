package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.MainThread;
import android.support.v4.view.MotionEventCompat;
import com.google.android.gms.internal.zzcfk;

/* loaded from: classes.dex */
public final class zzcfh<T extends Context & zzcfk> {
    private final T zzdtw;

    public zzcfh(T t) {
        com.google.android.gms.common.internal.zzbp.zzu(t);
        this.zzdtw = t;
    }

    private final void zza(Integer num, JobParameters jobParameters) throws IllegalStateException {
        zzccw zzccwVarZzdn = zzccw.zzdn(this.zzdtw);
        zzccwVarZzdn.zzaul().zzg(new zzcfi(this, zzccwVarZzdn, num, zzccwVarZzdn.zzaum(), jobParameters));
    }

    private final zzcbw zzaum() {
        return zzccw.zzdn(this.zzdtw).zzaum();
    }

    public static boolean zzk(Context context, boolean z) {
        com.google.android.gms.common.internal.zzbp.zzu(context);
        return Build.VERSION.SDK_INT >= 24 ? zzcfw.zzv(context, "com.google.android.gms.measurement.AppMeasurementJobService") : zzcfw.zzv(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    @MainThread
    public final IBinder onBind(Intent intent) {
        if (intent == null) {
            zzaum().zzaye().log("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzcdb(zzccw.zzdn(this.zzdtw));
        }
        zzaum().zzayg().zzj("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public final void onCreate() {
        zzcbw zzcbwVarZzaum = zzccw.zzdn(this.zzdtw).zzaum();
        zzcax.zzawl();
        zzcbwVarZzaum.zzayk().log("Local AppMeasurementService is starting up");
    }

    @MainThread
    public final void onDestroy() {
        zzcbw zzcbwVarZzaum = zzccw.zzdn(this.zzdtw).zzaum();
        zzcax.zzawl();
        zzcbwVarZzaum.zzayk().log("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final void onRebind(Intent intent) {
        if (intent == null) {
            zzaum().zzaye().log("onRebind called with null intent");
        } else {
            zzaum().zzayk().zzj("onRebind called. action", intent.getAction());
        }
    }

    @MainThread
    public final int onStartCommand(Intent intent, int i, int i2) throws IllegalStateException {
        zzcbw zzcbwVarZzaum = zzccw.zzdn(this.zzdtw).zzaum();
        if (intent == null) {
            zzcbwVarZzaum.zzayg().log("AppMeasurementService started with null intent");
        } else {
            String action = intent.getAction();
            zzcax.zzawl();
            zzcbwVarZzaum.zzayk().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                zza(Integer.valueOf(i2), null);
            }
        }
        return 2;
    }

    @TargetApi(MotionEventCompat.AXIS_DISTANCE)
    @MainThread
    public final boolean onStartJob(JobParameters jobParameters) throws IllegalStateException {
        zzcbw zzcbwVarZzaum = zzccw.zzdn(this.zzdtw).zzaum();
        String string = jobParameters.getExtras().getString("action");
        zzcax.zzawl();
        zzcbwVarZzaum.zzayk().zzj("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zza(null, jobParameters);
        return true;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzaum().zzaye().log("onUnbind called with null intent");
        } else {
            zzaum().zzayk().zzj("onUnbind called for intent. action", intent.getAction());
        }
        return true;
    }
}
