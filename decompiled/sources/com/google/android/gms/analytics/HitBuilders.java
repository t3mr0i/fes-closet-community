package com.google.android.gms.analytics;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.internal.zzaom;
import com.google.android.gms.internal.zzapd;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HitBuilders {

    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            set("&t", "screenview");
        }
    }

    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            set("&t", "event");
        }

        public EventBuilder(String str, String str2) {
            this();
            setCategory(str);
            setAction(str2);
        }

        public EventBuilder setAction(String str) {
            set("&ea", str);
            return this;
        }

        public EventBuilder setCategory(String str) {
            set("&ec", str);
            return this;
        }

        public EventBuilder setLabel(String str) {
            set("&el", str);
            return this;
        }

        public EventBuilder setValue(long j) {
            set("&ev", Long.toString(j));
            return this;
        }
    }

    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder> {
        public ExceptionBuilder() {
            set("&t", "exception");
        }

        public ExceptionBuilder setDescription(String str) {
            set("&exd", str);
            return this;
        }

        public ExceptionBuilder setFatal(boolean z) {
            set("&exf", zzapd.zzaj(z));
            return this;
        }
    }

    public static class HitBuilder<T extends HitBuilder> {
        private ProductAction zzdkd;
        private Map<String, String> zzdkc = new HashMap();
        private Map<String, List<Product>> zzdke = new HashMap();
        private List<Promotion> zzdkf = new ArrayList();
        private List<Product> zzdkg = new ArrayList();

        protected HitBuilder() {
        }

        private final T zzi(String str, String str2) {
            if (str2 != null) {
                this.zzdkc.put(str, str2);
            }
            return this;
        }

        public T addImpression(Product product, String str) {
            if (product == null) {
                zzaom.zzcr("product should be non-null");
            } else {
                if (str == null) {
                    str = "";
                }
                if (!this.zzdke.containsKey(str)) {
                    this.zzdke.put(str, new ArrayList());
                }
                this.zzdke.get(str).add(product);
            }
            return this;
        }

        public T addProduct(Product product) {
            if (product == null) {
                zzaom.zzcr("product should be non-null");
            } else {
                this.zzdkg.add(product);
            }
            return this;
        }

        public T addPromotion(Promotion promotion) {
            if (promotion == null) {
                zzaom.zzcr("promotion should be non-null");
            } else {
                this.zzdkf.add(promotion);
            }
            return this;
        }

        public Map<String, String> build() {
            HashMap map = new HashMap(this.zzdkc);
            if (this.zzdkd != null) {
                map.putAll(this.zzdkd.build());
            }
            Iterator<Promotion> it = this.zzdkf.iterator();
            int i = 1;
            while (it.hasNext()) {
                map.putAll(it.next().zzdj(zzd.zzam(i)));
                i++;
            }
            Iterator<Product> it2 = this.zzdkg.iterator();
            int i2 = 1;
            while (it2.hasNext()) {
                map.putAll(it2.next().zzdj(zzd.zzak(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry<String, List<Product>> entry : this.zzdke.entrySet()) {
                List<Product> value = entry.getValue();
                String strZzap = zzd.zzap(i3);
                int i4 = 1;
                for (Product product : value) {
                    String strValueOf = String.valueOf(strZzap);
                    String strValueOf2 = String.valueOf(zzd.zzao(i4));
                    map.putAll(product.zzdj(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf)));
                    i4++;
                }
                if (!TextUtils.isEmpty(entry.getKey())) {
                    String strValueOf3 = String.valueOf(strZzap);
                    String strValueOf4 = String.valueOf("nm");
                    map.put(strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3), entry.getKey());
                }
                i3++;
            }
            return map;
        }

        protected String get(String str) {
            return this.zzdkc.get(str);
        }

        public final T set(String str, String str2) {
            if (str != null) {
                this.zzdkc.put(str, str2);
            } else {
                zzaom.zzcr("HitBuilder.set() called with a null paramName.");
            }
            return this;
        }

        public final T setAll(Map<String, String> map) {
            if (map != null) {
                this.zzdkc.putAll(new HashMap(map));
            }
            return this;
        }

        public T setCampaignParamsFromUrl(String str) throws UnsupportedEncodingException {
            String strZzeb = zzapd.zzeb(str);
            if (!TextUtils.isEmpty(strZzeb)) {
                Map<String, String> mapZzdz = zzapd.zzdz(strZzeb);
                zzi("&cc", mapZzdz.get("utm_content"));
                zzi("&cm", mapZzdz.get("utm_medium"));
                zzi("&cn", mapZzdz.get("utm_campaign"));
                zzi("&cs", mapZzdz.get("utm_source"));
                zzi("&ck", mapZzdz.get("utm_term"));
                zzi("&ci", mapZzdz.get("utm_id"));
                zzi("&anid", mapZzdz.get("anid"));
                zzi("&gclid", mapZzdz.get("gclid"));
                zzi("&dclid", mapZzdz.get("dclid"));
                zzi("&aclid", mapZzdz.get(FirebaseAnalytics.Param.ACLID));
                zzi("&gmob_t", mapZzdz.get("gmob_t"));
            }
            return this;
        }

        public T setCustomDimension(int i, String str) {
            set(zzd.zzag(i), str);
            return this;
        }

        public T setCustomMetric(int i, float f) {
            set(zzd.zzai(i), Float.toString(f));
            return this;
        }

        protected T setHitType(String str) {
            set("&t", str);
            return this;
        }

        public T setNewSession() {
            set("&sc", "start");
            return this;
        }

        public T setNonInteraction(boolean z) {
            set("&ni", zzapd.zzaj(z));
            return this;
        }

        public T setProductAction(ProductAction productAction) {
            this.zzdkd = productAction;
            return this;
        }

        public T setPromotionAction(String str) {
            this.zzdkc.put("&promoa", str);
            return this;
        }
    }

    @Deprecated
    public static class ItemBuilder extends HitBuilder<ItemBuilder> {
        public ItemBuilder() {
            set("&t", "item");
        }

        public ItemBuilder setCategory(String str) {
            set("&iv", str);
            return this;
        }

        public ItemBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public ItemBuilder setName(String str) {
            set("&in", str);
            return this;
        }

        public ItemBuilder setPrice(double d) {
            set("&ip", Double.toString(d));
            return this;
        }

        public ItemBuilder setQuantity(long j) {
            set("&iq", Long.toString(j));
            return this;
        }

        public ItemBuilder setSku(String str) {
            set("&ic", str);
            return this;
        }

        public ItemBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }

    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder> {
        public ScreenViewBuilder() {
            set("&t", "screenview");
        }
    }

    public static class SocialBuilder extends HitBuilder<SocialBuilder> {
        public SocialBuilder() {
            set("&t", NotificationCompat.CATEGORY_SOCIAL);
        }

        public SocialBuilder setAction(String str) {
            set("&sa", str);
            return this;
        }

        public SocialBuilder setNetwork(String str) {
            set("&sn", str);
            return this;
        }

        public SocialBuilder setTarget(String str) {
            set("&st", str);
            return this;
        }
    }

    public static class TimingBuilder extends HitBuilder<TimingBuilder> {
        public TimingBuilder() {
            set("&t", "timing");
        }

        public TimingBuilder(String str, String str2, long j) {
            this();
            setVariable(str2);
            setValue(j);
            setCategory(str);
        }

        public TimingBuilder setCategory(String str) {
            set("&utc", str);
            return this;
        }

        public TimingBuilder setLabel(String str) {
            set("&utl", str);
            return this;
        }

        public TimingBuilder setValue(long j) {
            set("&utt", Long.toString(j));
            return this;
        }

        public TimingBuilder setVariable(String str) {
            set("&utv", str);
            return this;
        }
    }

    @Deprecated
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder> {
        public TransactionBuilder() {
            set("&t", "transaction");
        }

        public TransactionBuilder setAffiliation(String str) {
            set("&ta", str);
            return this;
        }

        public TransactionBuilder setCurrencyCode(String str) {
            set("&cu", str);
            return this;
        }

        public TransactionBuilder setRevenue(double d) {
            set("&tr", Double.toString(d));
            return this;
        }

        public TransactionBuilder setShipping(double d) {
            set("&ts", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTax(double d) {
            set("&tt", Double.toString(d));
            return this;
        }

        public TransactionBuilder setTransactionId(String str) {
            set("&ti", str);
            return this;
        }
    }
}
