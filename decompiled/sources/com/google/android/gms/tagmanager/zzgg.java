package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/* loaded from: classes.dex */
public final class zzgg {
    private Context mContext;
    private Tracker zzdjs;
    private GoogleAnalytics zzdju;

    public zzgg(Context context) {
        this.mContext = context;
    }

    private final synchronized void zzmc(String str) {
        if (this.zzdju == null) {
            this.zzdju = GoogleAnalytics.getInstance(this.mContext);
            this.zzdju.setLogger(new zzgh());
            this.zzdjs = this.zzdju.newTracker(str);
        }
    }

    public final Tracker zzmb(String str) {
        zzmc(str);
        return this.zzdjs;
    }
}
