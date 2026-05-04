package com.google.android.gms.tagmanager;

import android.text.TextUtils;

/* loaded from: classes.dex */
final class zzbx {
    private final long zzdsx;
    private final long zzjrr;
    private final long zzjrs;
    private String zzjrt;

    zzbx(long j, long j2, long j3) {
        this.zzjrr = j;
        this.zzdsx = j2;
        this.zzjrs = j3;
    }

    final long zzbds() {
        return this.zzjrr;
    }

    final long zzbdt() {
        return this.zzjrs;
    }

    final String zzbdu() {
        return this.zzjrt;
    }

    final void zzlr(String str) {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return;
        }
        this.zzjrt = str;
    }
}
