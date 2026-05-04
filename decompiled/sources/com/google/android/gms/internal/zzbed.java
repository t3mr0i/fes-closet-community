package com.google.android.gms.internal;

import android.content.Context;

/* loaded from: classes.dex */
public final class zzbed {
    private static zzbed zzfzs = new zzbed();
    private zzbec zzfzr = null;

    private final synchronized zzbec zzcq(Context context) {
        if (this.zzfzr == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzfzr = new zzbec(context);
        }
        return this.zzfzr;
    }

    public static zzbec zzcr(Context context) {
        return zzfzs.zzcq(context);
    }
}
