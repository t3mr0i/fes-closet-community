package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzh<O extends Api.ApiOptions> {
    private final Api<O> zzfdf;
    private final O zzfgq;
    private final boolean zzfih;
    private final int zzfii;

    private zzh(Api<O> api) {
        this.zzfih = true;
        this.zzfdf = api;
        this.zzfgq = null;
        this.zzfii = System.identityHashCode(this);
    }

    private zzh(Api<O> api, O o) {
        this.zzfih = false;
        this.zzfdf = api;
        this.zzfgq = o;
        this.zzfii = Arrays.hashCode(new Object[]{this.zzfdf, this.zzfgq});
    }

    public static <O extends Api.ApiOptions> zzh<O> zza(Api<O> api, O o) {
        return new zzh<>(api, o);
    }

    public static <O extends Api.ApiOptions> zzh<O> zzb(Api<O> api) {
        return new zzh<>(api);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh zzhVar = (zzh) obj;
        return !this.zzfih && !zzhVar.zzfih && com.google.android.gms.common.internal.zzbf.equal(this.zzfdf, zzhVar.zzfdf) && com.google.android.gms.common.internal.zzbf.equal(this.zzfgq, zzhVar.zzfgq);
    }

    public final int hashCode() {
        return this.zzfii;
    }

    public final String zzafv() {
        return this.zzfdf.getName();
    }
}
