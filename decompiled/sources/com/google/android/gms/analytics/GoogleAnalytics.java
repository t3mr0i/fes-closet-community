package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.internal.zzamu;
import com.google.android.gms.internal.zzaod;
import com.google.android.gms.internal.zzaom;
import com.google.android.gms.internal.zzapa;
import com.google.android.gms.internal.zzapc;
import com.google.android.gms.internal.zzape;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public final class GoogleAnalytics extends com.google.android.gms.analytics.zza {
    private static List<Runnable> zzdjv = new ArrayList();
    private boolean zzaqe;
    private Set<zza> zzdjw;
    private boolean zzdjx;
    private boolean zzdjy;
    private volatile boolean zzdjz;
    private boolean zzdka;

    interface zza {
        void zzl(Activity activity);

        void zzm(Activity activity);
    }

    @TargetApi(14)
    class zzb implements Application.ActivityLifecycleCallbacks {
        zzb() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            GoogleAnalytics.this.zzj(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            GoogleAnalytics.this.zzk(activity);
        }
    }

    public GoogleAnalytics(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzdjw = new HashSet();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static GoogleAnalytics getInstance(Context context) {
        return zzamu.zzbg(context).zzwn();
    }

    public static void zztw() {
        synchronized (GoogleAnalytics.class) {
            if (zzdjv != null) {
                Iterator<Runnable> it = zzdjv.iterator();
                while (it.hasNext()) {
                    it.next().run();
                }
                zzdjv = null;
            }
        }
    }

    public final void dispatchLocalHits() {
        zztr().zzwc().zzvs();
    }

    @TargetApi(14)
    public final void enableAutoActivityReports(Application application) {
        if (this.zzdjx) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new zzb());
        this.zzdjx = true;
    }

    public final boolean getAppOptOut() {
        return this.zzdjz;
    }

    @Deprecated
    public final Logger getLogger() {
        return zzaom.getLogger();
    }

    public final void initialize() {
        zzape zzapeVarZzwe = zztr().zzwe();
        zzapeVarZzwe.zzzn();
        if (zzapeVarZzwe.zzzo()) {
            setDryRun(zzapeVarZzwe.zzzp());
        }
        zzapeVarZzwe.zzzn();
        this.zzaqe = true;
    }

    public final boolean isDryRunEnabled() {
        return this.zzdjy;
    }

    public final boolean isInitialized() {
        return this.zzaqe;
    }

    public final Tracker newTracker(int i) {
        Tracker tracker;
        zzapc zzapcVarZzat;
        synchronized (this) {
            tracker = new Tracker(zztr(), null, null);
            if (i > 0 && (zzapcVarZzat = new zzapa(zztr()).zzat(i)) != null) {
                tracker.zza(zzapcVarZzat);
            }
            tracker.initialize();
        }
        return tracker;
    }

    public final Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zztr(), str, null);
            tracker.initialize();
        }
        return tracker;
    }

    public final void reportActivityStart(Activity activity) {
        if (this.zzdjx) {
            return;
        }
        zzj(activity);
    }

    public final void reportActivityStop(Activity activity) {
        if (this.zzdjx) {
            return;
        }
        zzk(activity);
    }

    public final void setAppOptOut(boolean z) {
        this.zzdjz = z;
        if (this.zzdjz) {
            zztr().zzwc().zzvr();
        }
    }

    public final void setDryRun(boolean z) {
        this.zzdjy = z;
    }

    public final void setLocalDispatchPeriod(int i) {
        zztr().zzwc().setLocalDispatchPeriod(i);
    }

    @Deprecated
    public final void setLogger(Logger logger) {
        zzaom.setLogger(logger);
        if (this.zzdka) {
            return;
        }
        String str = zzaod.zzdra.get();
        String str2 = zzaod.zzdra.get();
        Log.i(str, new StringBuilder(String.valueOf(str2).length() + 112).append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(str2).append(" DEBUG").toString());
        this.zzdka = true;
    }

    final void zza(zza zzaVar) {
        this.zzdjw.add(zzaVar);
        Context context = zztr().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    final void zzb(zza zzaVar) {
        this.zzdjw.remove(zzaVar);
    }

    final void zzj(Activity activity) {
        Iterator<zza> it = this.zzdjw.iterator();
        while (it.hasNext()) {
            it.next().zzl(activity);
        }
    }

    final void zzk(Activity activity) {
        Iterator<zza> it = this.zzdjw.iterator();
        while (it.hasNext()) {
            it.next().zzm(activity);
        }
    }
}
