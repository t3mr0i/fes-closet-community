package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzamb extends com.google.android.gms.analytics.zzh<zzamb> {
    private ProductAction zzdkd;
    private final List<Product> zzdkg = new ArrayList();
    private final List<Promotion> zzdkf = new ArrayList();
    private final Map<String, List<Product>> zzdke = new HashMap();

    public final String toString() {
        HashMap map = new HashMap();
        if (!this.zzdkg.isEmpty()) {
            map.put("products", this.zzdkg);
        }
        if (!this.zzdkf.isEmpty()) {
            map.put("promotions", this.zzdkf);
        }
        if (!this.zzdke.isEmpty()) {
            map.put("impressions", this.zzdke);
        }
        map.put("productAction", this.zzdkd);
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzamb zzambVar = (zzamb) zzhVar;
        zzambVar.zzdkg.addAll(this.zzdkg);
        zzambVar.zzdkf.addAll(this.zzdkf);
        for (Map.Entry<String, List<Product>> entry : this.zzdke.entrySet()) {
            String key = entry.getKey();
            for (Product product : entry.getValue()) {
                if (product != null) {
                    String str = key == null ? "" : key;
                    if (!zzambVar.zzdke.containsKey(str)) {
                        zzambVar.zzdke.put(str, new ArrayList());
                    }
                    zzambVar.zzdke.get(str).add(product);
                }
            }
        }
        if (this.zzdkd != null) {
            zzambVar.zzdkd = this.zzdkd;
        }
    }

    public final ProductAction zzuz() {
        return this.zzdkd;
    }

    public final List<Product> zzva() {
        return Collections.unmodifiableList(this.zzdkg);
    }

    public final Map<String, List<Product>> zzvb() {
        return this.zzdke;
    }

    public final List<Promotion> zzvc() {
        return Collections.unmodifiableList(this.zzdkf);
    }
}
