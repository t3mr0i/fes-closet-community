package com.google.android.gms.internal;

import android.content.Context;

/* loaded from: classes.dex */
public final class zzamw {
    private final Context mApplicationContext;
    private final Context zzdou;

    public zzamw(Context context) {
        com.google.android.gms.common.internal.zzbp.zzu(context);
        Context applicationContext = context.getApplicationContext();
        com.google.android.gms.common.internal.zzbp.zzb(applicationContext, "Application context can't be null");
        this.mApplicationContext = applicationContext;
        this.zzdou = applicationContext;
    }

    public final Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public final Context zzwl() {
        return this.zzdou;
    }
}
