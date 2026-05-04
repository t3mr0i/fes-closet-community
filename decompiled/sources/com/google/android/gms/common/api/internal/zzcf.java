package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

/* loaded from: classes.dex */
public final class zzcf {
    private final Object zzfom;

    public zzcf(Activity activity) {
        com.google.android.gms.common.internal.zzbp.zzb(activity, "Activity must not be null");
        this.zzfom = activity;
    }

    public final boolean zzaig() {
        return this.zzfom instanceof FragmentActivity;
    }

    public final boolean zzaih() {
        return this.zzfom instanceof Activity;
    }

    public final Activity zzaii() {
        return (Activity) this.zzfom;
    }

    public final FragmentActivity zzaij() {
        return (FragmentActivity) this.zzfom;
    }
}
