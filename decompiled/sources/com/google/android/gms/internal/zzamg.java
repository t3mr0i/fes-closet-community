package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzamg extends com.google.android.gms.analytics.zzh<zzamg> {
    public String zzdmq;
    public String zzdnj;
    public String zzdnk;

    public final String toString() {
        HashMap map = new HashMap();
        map.put("network", this.zzdnj);
        map.put("action", this.zzdmq);
        map.put("target", this.zzdnk);
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzamg zzamgVar = (zzamg) zzhVar;
        if (!TextUtils.isEmpty(this.zzdnj)) {
            zzamgVar.zzdnj = this.zzdnj;
        }
        if (!TextUtils.isEmpty(this.zzdmq)) {
            zzamgVar.zzdmq = this.zzdmq;
        }
        if (TextUtils.isEmpty(this.zzdnk)) {
            return;
        }
        zzamgVar.zzdnk = this.zzdnk;
    }
}
