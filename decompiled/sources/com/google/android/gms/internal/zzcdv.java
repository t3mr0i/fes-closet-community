package com.google.android.gms.internal;

import android.content.Context;

/* loaded from: classes.dex */
public final class zzcdv {
    final Context mContext;

    public zzcdv(Context context) {
        com.google.android.gms.common.internal.zzbp.zzu(context);
        Context applicationContext = context.getApplicationContext();
        com.google.android.gms.common.internal.zzbp.zzu(applicationContext);
        this.mContext = applicationContext;
    }
}
