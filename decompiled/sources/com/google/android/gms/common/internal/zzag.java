package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Intent;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzag {
    private final String zzdmq;
    private final String zzfuu;
    private final ComponentName zzfuv;
    private final int zzfuw;

    public zzag(ComponentName componentName, int i) {
        this.zzdmq = null;
        this.zzfuu = null;
        this.zzfuv = (ComponentName) zzbp.zzu(componentName);
        this.zzfuw = 129;
    }

    public zzag(String str, String str2, int i) {
        this.zzdmq = zzbp.zzgg(str);
        this.zzfuu = zzbp.zzgg(str2);
        this.zzfuv = null;
        this.zzfuw = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzag)) {
            return false;
        }
        zzag zzagVar = (zzag) obj;
        return zzbf.equal(this.zzdmq, zzagVar.zzdmq) && zzbf.equal(this.zzfuu, zzagVar.zzfuu) && zzbf.equal(this.zzfuv, zzagVar.zzfuv) && this.zzfuw == zzagVar.zzfuw;
    }

    public final ComponentName getComponentName() {
        return this.zzfuv;
    }

    public final String getPackage() {
        return this.zzfuu;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzdmq, this.zzfuu, this.zzfuv, Integer.valueOf(this.zzfuw)});
    }

    public final String toString() {
        return this.zzdmq == null ? this.zzfuv.flattenToString() : this.zzdmq;
    }

    public final int zzakh() {
        return this.zzfuw;
    }

    public final Intent zzaki() {
        return this.zzdmq != null ? new Intent(this.zzdmq).setPackage(this.zzfuu) : new Intent().setComponent(this.zzfuv);
    }
}
