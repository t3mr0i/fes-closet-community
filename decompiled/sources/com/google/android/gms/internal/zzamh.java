package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzamh extends com.google.android.gms.analytics.zzh<zzamh> {
    public String mCategory;
    public String zzdmr;
    public String zzdnl;
    public long zzdnm;

    public final String toString() {
        HashMap map = new HashMap();
        map.put("variableName", this.zzdnl);
        map.put("timeInMillis", Long.valueOf(this.zzdnm));
        map.put("category", this.mCategory);
        map.put("label", this.zzdmr);
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzamh zzamhVar = (zzamh) zzhVar;
        if (!TextUtils.isEmpty(this.zzdnl)) {
            zzamhVar.zzdnl = this.zzdnl;
        }
        if (this.zzdnm != 0) {
            zzamhVar.zzdnm = this.zzdnm;
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzamhVar.mCategory = this.mCategory;
        }
        if (TextUtils.isEmpty(this.zzdmr)) {
            return;
        }
        zzamhVar.zzdmr = this.zzdmr;
    }
}
