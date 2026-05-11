package com.google.android.gms.internal;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzdbn {
    private final Context mContext;
    private final com.google.android.gms.common.util.zzd zzasb;
    private String zzjqd;
    private Map<String, Object> zzkcz;
    private final Map<String, Object> zzkda;
    private final zzdbx zzkfl;

    public zzdbn(Context context) {
        this(context, new HashMap(), new zzdbx(context), com.google.android.gms.common.util.zzh.zzald());
    }

    private zzdbn(Context context, Map<String, Object> map, zzdbx zzdbxVar, com.google.android.gms.common.util.zzd zzdVar) {
        this.zzjqd = null;
        this.zzkcz = new HashMap();
        this.mContext = context;
        this.zzasb = zzdVar;
        this.zzkfl = zzdbxVar;
        this.zzkda = map;
    }

    public final void zzni(String str) {
        this.zzjqd = str;
    }
}
