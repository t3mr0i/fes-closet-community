package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;

/* loaded from: classes.dex */
final class zzg implements DataLayer.zzb {
    private final Context zzahy;

    public zzg(Context context) {
        this.zzahy = context;
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.zzb
    public final void zzq(Map<String, Object> map) {
        String queryParameter;
        Object obj;
        Object obj2 = map.get("gtm.url");
        Object obj3 = (obj2 == null && (obj = map.get("gtm")) != null && (obj instanceof Map)) ? ((Map) obj).get("url") : obj2;
        if (obj3 == null || !(obj3 instanceof String) || (queryParameter = Uri.parse((String) obj3).getQueryParameter("referrer")) == null) {
            return;
        }
        zzcx.zzam(this.zzahy, queryParameter);
    }
}
