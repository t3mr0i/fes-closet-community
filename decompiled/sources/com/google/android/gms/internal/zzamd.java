package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzamd extends com.google.android.gms.analytics.zzh<zzamd> {
    public String zzdmt;
    public boolean zzdmu;

    public final String toString() {
        HashMap map = new HashMap();
        map.put("description", this.zzdmt);
        map.put(AppMeasurement.Param.FATAL, Boolean.valueOf(this.zzdmu));
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzamd zzamdVar = (zzamd) zzhVar;
        if (!TextUtils.isEmpty(this.zzdmt)) {
            zzamdVar.zzdmt = this.zzdmt;
        }
        if (this.zzdmu) {
            zzamdVar.zzdmu = this.zzdmu;
        }
    }
}
