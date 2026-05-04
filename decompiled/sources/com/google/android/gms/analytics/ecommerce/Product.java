package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzd;
import com.google.android.gms.analytics.zzh;
import com.google.android.gms.common.internal.zzbp;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Product {
    private Map<String, String> zzdnn = new HashMap();

    private final void put(String str, String str2) {
        zzbp.zzb(str, "Name should be non-null");
        this.zzdnn.put(str, str2);
    }

    public Product setBrand(String str) {
        put("br", str);
        return this;
    }

    public Product setCategory(String str) {
        put("ca", str);
        return this;
    }

    public Product setCouponCode(String str) {
        put("cc", str);
        return this;
    }

    public Product setCustomDimension(int i, String str) {
        put(zzd.zzar(i), str);
        return this;
    }

    public Product setCustomMetric(int i, int i2) {
        put(zzd.zzas(i), Integer.toString(i2));
        return this;
    }

    public Product setId(String str) {
        put("id", str);
        return this;
    }

    public Product setName(String str) {
        put("nm", str);
        return this;
    }

    public Product setPosition(int i) {
        put("ps", Integer.toString(i));
        return this;
    }

    public Product setPrice(double d) {
        put("pr", Double.toString(d));
        return this;
    }

    public Product setQuantity(int i) {
        put("qt", Integer.toString(i));
        return this;
    }

    public Product setVariant(String str) {
        put("va", str);
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
