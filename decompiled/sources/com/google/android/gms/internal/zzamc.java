package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzamc extends com.google.android.gms.analytics.zzh<zzamc> {
    private String mCategory;
    private String zzdmq;
    private String zzdmr;
    private long zzdms;

    public final String getAction() {
        return this.zzdmq;
    }

    public final String getCategory() {
        return this.mCategory;
    }

    public final String getLabel() {
        return this.zzdmr;
    }

    public final long getValue() {
        return this.zzdms;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("category", this.mCategory);
        map.put("action", this.zzdmq);
        map.put("label", this.zzdmr);
        map.put(FirebaseAnalytics.Param.VALUE, Long.valueOf(this.zzdms));
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzamc zzamcVar = (zzamc) zzhVar;
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzamcVar.mCategory = this.mCategory;
        }
        if (!TextUtils.isEmpty(this.zzdmq)) {
            zzamcVar.zzdmq = this.zzdmq;
        }
        if (!TextUtils.isEmpty(this.zzdmr)) {
            zzamcVar.zzdmr = this.zzdmr;
        }
        if (this.zzdms != 0) {
            zzamcVar.zzdms = this.zzdms;
        }
    }
}
