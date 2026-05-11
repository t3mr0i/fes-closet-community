package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzeap {
    private String zzdxs;

    public zzeap(@Nullable String str) {
        this.zzdxs = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzeap) {
            return com.google.android.gms.common.internal.zzbf.equal(this.zzdxs, ((zzeap) obj).zzdxs);
        }
        return false;
    }

    @Nullable
    public final String getToken() {
        return this.zzdxs;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzdxs});
    }

    public final String toString() {
        return com.google.android.gms.common.internal.zzbf.zzt(this).zzg("token", this.zzdxs).toString();
    }
}
