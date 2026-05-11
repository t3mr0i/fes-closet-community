package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class zzgl extends zzgi {
    private static final String ID = com.google.android.gms.internal.zzbd.UNIVERSAL_ANALYTICS.toString();
    private static final String zzjwq = com.google.android.gms.internal.zzbe.ACCOUNT.toString();
    private static final String zzjwr = com.google.android.gms.internal.zzbe.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzjws = com.google.android.gms.internal.zzbe.ENABLE_ECOMMERCE.toString();
    private static final String zzjwt = com.google.android.gms.internal.zzbe.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zzjwu = com.google.android.gms.internal.zzbe.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzjwv = com.google.android.gms.internal.zzbe.ANALYTICS_FIELDS.toString();
    private static final String zzjww = com.google.android.gms.internal.zzbe.TRACK_TRANSACTION.toString();
    private static final String zzjwx = com.google.android.gms.internal.zzbe.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzjwy = com.google.android.gms.internal.zzbe.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> zzjwz = Arrays.asList(ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, "checkout_option", "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND);
    private static final Pattern zzjxa = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzjxb = Pattern.compile("metric(\\d+)");
    private static Map<String, String> zzjxc;
    private static Map<String, String> zzjxd;
    private final DataLayer zzjpa;
    private final Set<String> zzjxe;
    private final zzgg zzjxf;

    public zzgl(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new zzgg(context));
    }

    private zzgl(Context context, DataLayer dataLayer, zzgg zzggVar) {
        super(ID, new String[0]);
        this.zzjpa = dataLayer;
        this.zzjxf = zzggVar;
        this.zzjxe = new HashSet();
        this.zzjxe.add("");
        this.zzjxe.add("0");
        this.zzjxe.add("false");
    }

    private final void zza(Tracker tracker, Map<String, com.google.android.gms.internal.zzbp> map) {
        Map<String, String> mapZzh;
        Map<String, String> mapZzh2;
        String strZzmg = zzmg("transactionId");
        if (strZzmg == null) {
            zzdj.e("Cannot find transactionId in data layer.");
            return;
        }
        LinkedList linkedList = new LinkedList();
        try {
            Map<String, String> mapZzi = zzi(map.get(zzjwv));
            mapZzi.put("&t", "transaction");
            com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjwx);
            if (zzbpVar != null) {
                mapZzh = zzh(zzbpVar);
            } else {
                if (zzjxc == null) {
                    HashMap map2 = new HashMap();
                    map2.put("transactionId", "&ti");
                    map2.put("transactionAffiliation", "&ta");
                    map2.put("transactionTax", "&tt");
                    map2.put("transactionShipping", "&ts");
                    map2.put("transactionTotal", "&tr");
                    map2.put("transactionCurrency", "&cu");
                    zzjxc = map2;
                }
                mapZzh = zzjxc;
            }
            for (Map.Entry<String, String> entry : mapZzh.entrySet()) {
                zzd(mapZzi, entry.getValue(), zzmg(entry.getKey()));
            }
            linkedList.add(mapZzi);
            List<Map<String, String>> listZzmh = zzmh("transactionProducts");
            if (listZzmh != null) {
                for (Map<String, String> map3 : listZzmh) {
                    if (map3.get("name") == null) {
                        zzdj.e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map<String, String> mapZzi2 = zzi(map.get(zzjwv));
                    mapZzi2.put("&t", "item");
                    mapZzi2.put("&ti", strZzmg);
                    com.google.android.gms.internal.zzbp zzbpVar2 = map.get(zzjwy);
                    if (zzbpVar2 != null) {
                        mapZzh2 = zzh(zzbpVar2);
                    } else {
                        if (zzjxd == null) {
                            HashMap map4 = new HashMap();
                            map4.put("name", "&in");
                            map4.put("sku", "&ic");
                            map4.put("category", "&iv");
                            map4.put(FirebaseAnalytics.Param.PRICE, "&ip");
                            map4.put(FirebaseAnalytics.Param.QUANTITY, "&iq");
                            map4.put(FirebaseAnalytics.Param.CURRENCY, "&cu");
                            zzjxd = map4;
                        }
                        mapZzh2 = zzjxd;
                    }
                    for (Map.Entry<String, String> entry2 : mapZzh2.entrySet()) {
                        zzd(mapZzi2, entry2.getValue(), map3.get(entry2.getKey()));
                    }
                    linkedList.add(mapZzi2);
                }
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                tracker.send((Map) it.next());
            }
        } catch (IllegalArgumentException e) {
            zzdj.zzb("Unable to send transaction", e);
        }
    }

    private static Double zzal(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(strValueOf.length() != 0 ? "Cannot convert the object to Double: ".concat(strValueOf) : new String("Cannot convert the object to Double: "));
            }
        }
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (obj instanceof Double) {
            return (Double) obj;
        }
        String strValueOf2 = String.valueOf(obj.toString());
        throw new RuntimeException(strValueOf2.length() != 0 ? "Cannot convert the object to Double: ".concat(strValueOf2) : new String("Cannot convert the object to Double: "));
    }

    private static Integer zzam(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                String strValueOf = String.valueOf(e.getMessage());
                throw new RuntimeException(strValueOf.length() != 0 ? "Cannot convert the object to Integer: ".concat(strValueOf) : new String("Cannot convert the object to Integer: "));
            }
        }
        if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        String strValueOf2 = String.valueOf(obj.toString());
        throw new RuntimeException(strValueOf2.length() != 0 ? "Cannot convert the object to Integer: ".concat(strValueOf2) : new String("Cannot convert the object to Integer: "));
    }

    private static void zzd(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private static boolean zzg(Map<String, com.google.android.gms.internal.zzbp> map, String str) {
        com.google.android.gms.internal.zzbp zzbpVar = map.get(str);
        if (zzbpVar == null) {
            return false;
        }
        return zzgk.zzf(zzbpVar).booleanValue();
    }

    private static Map<String, String> zzh(com.google.android.gms.internal.zzbp zzbpVar) {
        Object objZzg = zzgk.zzg(zzbpVar);
        if (!(objZzg instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) objZzg).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private final Map<String, String> zzi(com.google.android.gms.internal.zzbp zzbpVar) {
        Map<String, String> mapZzh;
        if (zzbpVar != null && (mapZzh = zzh(zzbpVar)) != null) {
            String str = mapZzh.get("&aip");
            if (str != null && this.zzjxe.contains(str.toLowerCase())) {
                mapZzh.remove("&aip");
            }
            return mapZzh;
        }
        return new HashMap();
    }

    private final String zzmg(String str) {
        Object obj = this.zzjpa.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private final List<Map<String, String>> zzmh(String str) {
        Object obj = this.zzjpa.get(str);
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof List)) {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            if (!(it.next() instanceof Map)) {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }
        return (List) obj;
    }

    private static Product zzu(Map<String, Object> map) {
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get("name");
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get("category");
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get(FirebaseAnalytics.Param.COUPON);
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get("position");
        if (obj7 != null) {
            product.setPosition(zzam(obj7).intValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.Param.PRICE);
        if (obj8 != null) {
            product.setPrice(zzal(obj8).doubleValue());
        }
        Object obj9 = map.get(FirebaseAnalytics.Param.QUANTITY);
        if (obj9 != null) {
            product.setQuantity(zzam(obj9).intValue());
        }
        for (String str : map.keySet()) {
            Matcher matcher = zzjxa.matcher(str);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str)));
                } catch (NumberFormatException e) {
                    String strValueOf = String.valueOf(str);
                    zzdj.zzcr(strValueOf.length() != 0 ? "illegal number in custom dimension value: ".concat(strValueOf) : new String("illegal number in custom dimension value: "));
                }
            } else {
                Matcher matcher2 = zzjxb.matcher(str);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzam(map.get(str)).intValue());
                    } catch (NumberFormatException e2) {
                        String strValueOf2 = String.valueOf(str);
                        zzdj.zzcr(strValueOf2.length() != 0 ? "illegal number in custom metric value: ".concat(strValueOf2) : new String("illegal number in custom metric value: "));
                    }
                }
            }
        }
        return product;
    }

    @Override // com.google.android.gms.tagmanager.zzgi, com.google.android.gms.tagmanager.zzbr
    public final /* bridge */ /* synthetic */ boolean zzbck() {
        return super.zzbck();
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final /* bridge */ /* synthetic */ String zzbdq() {
        return super.zzbdq();
    }

    @Override // com.google.android.gms.tagmanager.zzbr
    public final /* bridge */ /* synthetic */ Set zzbdr() {
        return super.zzbdr();
    }

    @Override // com.google.android.gms.tagmanager.zzgi, com.google.android.gms.tagmanager.zzbr
    public final /* bridge */ /* synthetic */ com.google.android.gms.internal.zzbp zzp(Map map) {
        return super.zzp(map);
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0182  */
    @Override // com.google.android.gms.tagmanager.zzgi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzr(java.util.Map<java.lang.String, com.google.android.gms.internal.zzbp> r9) {
        /*
            Method dump skipped, instructions count: 763
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzgl.zzr(java.util.Map):void");
    }
}
