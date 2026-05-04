package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzbp;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Promotion {
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_VIEW = "view";
    private Map<String, String> zzdnn = new HashMap();

    private final void put(String str, String str2) {
        zzbp.zzb(str, "Name should be non-null");
        this.zzdnn.put(str, str2);
    }

    public Promotion setCreative(String str) {
        put("cr", str);
        return this;
    }

    public Promotion setId(String str) {
        put("id", str);
        return this;
    }

    public Promotion setName(String str) {
        put("nm", str);
        return this;
    }

    public Promotion setPosition(String str) {
        put("ps", str);
        return this;
    }

    public String toString() {
        return zzh.zzl(this.zzdnn);
    }

    public final Map<String, String> zzdj(String str) {
        HashMap map = new HashMap();
        for (Map.Entry<String, String> entry : this.zzdnn.entrySet()) {
            String strValueOf = String.valueOf(str);
            String strValueOf2 = String.valueOf(entry.getKey());
            map.put(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf), entry.getValue());
        }
        return map;
    }
}
