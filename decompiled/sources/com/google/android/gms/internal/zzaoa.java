package com.google.android.gms.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;

/* loaded from: classes.dex */
public final class zzaoa extends zzams {
    private boolean zzdqu;
    private boolean zzdqv;
    private final AlarmManager zzdqw;
    private Integer zzdqx;

    protected zzaoa(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzdqw = (AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private final int getJobId() {
        if (this.zzdqx == null) {
            String strValueOf = String.valueOf(getContext().getPackageName());
            this.zzdqx = Integer.valueOf((strValueOf.length() != 0 ? "analytics".concat(strValueOf) : new String("analytics")).hashCode());
        }
        return this.zzdqx.intValue();
    }

    private final PendingIntent zzyk() {
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"));
        return PendingIntent.getBroadcast(getContext(), 0, intent, 0);
    }

    public final void cancel() {
        this.zzdqv = false;
        this.zzdqw.cancel(zzyk());
        if (Build.VERSION.SDK_INT >= 24) {
            JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
            zza("Cancelling job. JobID", Integer.valueOf(getJobId()));
            jobScheduler.cancel(getJobId());
        }
    }

    public final void schedule() {
        zzwk();
        com.google.android.gms.common.internal.zzbp.zza(this.zzdqu, "Receiver not registered");
        long jZzxy = zzanv.zzxy();
        if (jZzxy > 0) {
            cancel();
            long jElapsedRealtime = zzvx().elapsedRealtime() + jZzxy;
            this.zzdqv = true;
            if (Build.VERSION.SDK_INT < 24) {
                zzdm("Scheduling upload with AlarmManager");
                this.zzdqw.setInexactRepeating(2, jElapsedRealtime, jZzxy, zzyk());
                return;
            }
            zzdm("Scheduling upload with JobScheduler");
            ComponentName componentName = new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsJobService");
            JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
            JobInfo.Builder builder = new JobInfo.Builder(getJobId(), componentName);
            builder.setMinimumLatency(jZzxy);
            builder.setOverrideDeadline(jZzxy << 1);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            builder.setExtras(persistableBundle);
            JobInfo jobInfoBuild = builder.build();
            zza("Scheduling job. JobID", Integer.valueOf(getJobId()));
            jobScheduler.schedule(jobInfoBuild);
        }
    }

    public final boolean zzdp() {
        return this.zzdqv;
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
        ActivityInfo receiverInfo;
        try {
            cancel();
            if (zzanv.zzxy() <= 0 || (receiverInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), "com.google.android.gms.analytics.AnalyticsReceiver"), 2)) == null || !receiverInfo.enabled) {
                return;
            }
            zzdm("Receiver registered for local dispatch.");
            this.zzdqu = true;
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    public final boolean zzyj() {
        return this.zzdqu;
    }
}
