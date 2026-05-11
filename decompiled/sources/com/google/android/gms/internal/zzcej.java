package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.text.TextUtils;

@TargetApi(14)
@MainThread
/* loaded from: classes.dex */
final class zzcej implements Application.ActivityLifecycleCallbacks {
    private /* synthetic */ zzcdw zziux;

    private zzcej(zzcdw zzcdwVar) {
        this.zziux = zzcdwVar;
    }

    /* synthetic */ zzcej(zzcdw zzcdwVar, zzcdx zzcdxVar) {
        this(zzcdwVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Bundle bundle2;
        Uri data;
        try {
            this.zziux.zzaum().zzayk().log("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null && (data = intent.getData()) != null && data.isHierarchical()) {
                if (bundle == null) {
                    Bundle bundleZzp = this.zziux.zzaui().zzp(data);
                    this.zziux.zzaui();
                    String str = zzcfw.zzl(intent) ? "gs" : "auto";
                    if (bundleZzp != null) {
                        this.zziux.zzc(str, "_cmp", bundleZzp);
                    }
                }
                String queryParameter = data.getQueryParameter("referrer");
                if (TextUtils.isEmpty(queryParameter)) {
                    return;
                }
                if (!(queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content")))) {
                    this.zziux.zzaum().zzayj().log("Activity created with data 'referrer' param without gclid and at least one utm field");
                    return;
                } else {
                    this.zziux.zzaum().zzayj().zzj("Activity created with referrer", queryParameter);
                    if (!TextUtils.isEmpty(queryParameter)) {
                        this.zziux.zzb("auto", "_ldl", queryParameter);
                    }
                }
            }
        } catch (Throwable th) {
            this.zziux.zzaum().zzaye().zzj("Throwable caught in onActivityCreated", th);
        }
        zzcek zzcekVarZzaue = this.zziux.zzaue();
        if (bundle == null || (bundle2 = bundle.getBundle("com.google.firebase.analytics.screen_service")) == null) {
            return;
        }
        zzcen zzcenVarZzq = zzcekVarZzaue.zzq(activity);
        zzcenVarZzq.zziko = bundle2.getLong("id");
        zzcenVarZzq.zzikm = bundle2.getString("name");
        zzcenVarZzq.zzikn = bundle2.getString("referrer_name");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zziux.zzaue().onActivityDestroyed(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityPaused(Activity activity) throws IllegalStateException {
        this.zziux.zzaue().onActivityPaused(activity);
        zzcfl zzcflVarZzauk = this.zziux.zzauk();
        zzcflVarZzauk.zzaul().zzg(new zzcfp(zzcflVarZzauk, zzcflVarZzauk.zzvx().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityResumed(Activity activity) throws IllegalStateException {
        this.zziux.zzaue().onActivityResumed(activity);
        zzcfl zzcflVarZzauk = this.zziux.zzauk();
        zzcflVarZzauk.zzaul().zzg(new zzcfo(zzcflVarZzauk, zzcflVarZzauk.zzvx().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zziux.zzaue().onActivitySaveInstanceState(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
