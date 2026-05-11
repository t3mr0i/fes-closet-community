package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzalv;
import com.google.android.gms.internal.zzalw;
import com.google.android.gms.internal.zzalx;
import com.google.android.gms.internal.zzaly;
import com.google.android.gms.internal.zzalz;
import com.google.android.gms.internal.zzama;
import com.google.android.gms.internal.zzamb;
import com.google.android.gms.internal.zzamc;
import com.google.android.gms.internal.zzamd;
import com.google.android.gms.internal.zzame;
import com.google.android.gms.internal.zzamf;
import com.google.android.gms.internal.zzamg;
import com.google.android.gms.internal.zzamh;
import com.google.android.gms.internal.zzamr;
import com.google.android.gms.internal.zzamt;
import com.google.android.gms.internal.zzamu;
import com.google.android.gms.internal.zzamx;
import com.google.android.gms.internal.zzaoi;
import com.google.android.gms.internal.zzapd;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzb extends zzamr implements zzm {
    private static DecimalFormat zzdjm;
    private final zzamu zzdji;
    private final String zzdjn;
    private final Uri zzdjo;

    public zzb(zzamu zzamuVar, String str) {
        this(zzamuVar, str, true, false);
    }

    private zzb(zzamu zzamuVar, String str, boolean z, boolean z2) {
        super(zzamuVar);
        zzbp.zzgg(str);
        this.zzdji = zzamuVar;
        this.zzdjn = str;
        this.zzdjo = zzcx(this.zzdjn);
    }

    private static void zza(Map<String, String> map, String str, double d) {
        if (d != 0.0d) {
            map.put(str, zzb(d));
        }
    }

    private static void zza(Map<String, String> map, String str, int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        map.put(str, new StringBuilder(23).append(i).append("x").append(i2).toString());
    }

    private static void zza(Map<String, String> map, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        map.put(str, str2);
    }

    private static String zzb(double d) {
        if (zzdjm == null) {
            zzdjm = new DecimalFormat("0.######");
        }
        return zzdjm.format(d);
    }

    private static void zzb(Map<String, String> map, String str, boolean z) {
        if (z) {
            map.put(str, "1");
        }
    }

    private static Map<String, String> zzc(zzg zzgVar) {
        String strValueOf;
        HashMap map = new HashMap();
        zzalz zzalzVar = (zzalz) zzgVar.zza(zzalz.class);
        if (zzalzVar != null) {
            for (Map.Entry<String, Object> entry : zzalzVar.zzuy().entrySet()) {
                Object value = entry.getValue();
                if (value == null) {
                    strValueOf = null;
                } else if (value instanceof String) {
                    strValueOf = (String) value;
                    if (TextUtils.isEmpty(strValueOf)) {
                        strValueOf = null;
                    }
                } else if (value instanceof Double) {
                    Double d = (Double) value;
                    strValueOf = d.doubleValue() != 0.0d ? zzb(d.doubleValue()) : null;
                } else {
                    strValueOf = value instanceof Boolean ? value != Boolean.FALSE ? "1" : null : String.valueOf(value);
                }
                if (strValueOf != null) {
                    map.put(entry.getKey(), strValueOf);
                }
            }
        }
        zzame zzameVar = (zzame) zzgVar.zza(zzame.class);
        if (zzameVar != null) {
            zza(map, "t", zzameVar.zzvd());
            zza(map, "cid", zzameVar.zzve());
            zza(map, "uid", zzameVar.getUserId());
            zza(map, "sc", zzameVar.zzvh());
            zza(map, "sf", zzameVar.zzvj());
            zzb(map, "ni", zzameVar.zzvi());
            zza(map, "adid", zzameVar.zzvf());
            zzb(map, "ate", zzameVar.zzvg());
        }
        zzamf zzamfVar = (zzamf) zzgVar.zza(zzamf.class);
        if (zzamfVar != null) {
            zza(map, "cd", zzamfVar.zzvk());
            zza(map, "a", zzamfVar.zzvl());
            zza(map, "dr", zzamfVar.zzvm());
        }
        zzamc zzamcVar = (zzamc) zzgVar.zza(zzamc.class);
        if (zzamcVar != null) {
            zza(map, "ec", zzamcVar.getCategory());
            zza(map, "ea", zzamcVar.getAction());
            zza(map, "el", zzamcVar.getLabel());
            zza(map, "ev", zzamcVar.getValue());
        }
        zzalw zzalwVar = (zzalw) zzgVar.zza(zzalw.class);
        if (zzalwVar != null) {
            zza(map, "cn", zzalwVar.getName());
            zza(map, "cs", zzalwVar.getSource());
            zza(map, "cm", zzalwVar.zzuq());
            zza(map, "ck", zzalwVar.zzur());
            zza(map, "cc", zzalwVar.getContent());
            zza(map, "ci", zzalwVar.getId());
            zza(map, "anid", zzalwVar.zzus());
            zza(map, "gclid", zzalwVar.zzut());
            zza(map, "dclid", zzalwVar.zzuu());
            zza(map, FirebaseAnalytics.Param.ACLID, zzalwVar.zzuv());
        }
        zzamd zzamdVar = (zzamd) zzgVar.zza(zzamd.class);
        if (zzamdVar != null) {
            zza(map, "exd", zzamdVar.zzdmt);
            zzb(map, "exf", zzamdVar.zzdmu);
        }
        zzamg zzamgVar = (zzamg) zzgVar.zza(zzamg.class);
        if (zzamgVar != null) {
            zza(map, "sn", zzamgVar.zzdnj);
            zza(map, "sa", zzamgVar.zzdmq);
            zza(map, "st", zzamgVar.zzdnk);
        }
        zzamh zzamhVar = (zzamh) zzgVar.zza(zzamh.class);
        if (zzamhVar != null) {
            zza(map, "utv", zzamhVar.zzdnl);
            zza(map, "utt", zzamhVar.zzdnm);
            zza(map, "utc", zzamhVar.mCategory);
            zza(map, "utl", zzamhVar.zzdmr);
        }
        zzalx zzalxVar = (zzalx) zzgVar.zza(zzalx.class);
        if (zzalxVar != null) {
            for (Map.Entry<Integer, String> entry2 : zzalxVar.zzuw().entrySet()) {
                String strZzah = zzd.zzah(entry2.getKey().intValue());
                if (!TextUtils.isEmpty(strZzah)) {
                    map.put(strZzah, entry2.getValue());
                }
            }
        }
        zzaly zzalyVar = (zzaly) zzgVar.zza(zzaly.class);
        if (zzalyVar != null) {
            for (Map.Entry<Integer, Double> entry3 : zzalyVar.zzux().entrySet()) {
                String strZzaj = zzd.zzaj(entry3.getKey().intValue());
                if (!TextUtils.isEmpty(strZzaj)) {
                    map.put(strZzaj, zzb(entry3.getValue().doubleValue()));
                }
            }
        }
        zzamb zzambVar = (zzamb) zzgVar.zza(zzamb.class);
        if (zzambVar != null) {
            ProductAction productActionZzuz = zzambVar.zzuz();
            if (productActionZzuz != null) {
                for (Map.Entry<String, String> entry4 : productActionZzuz.build().entrySet()) {
                    if (entry4.getKey().startsWith("&")) {
                        map.put(entry4.getKey().substring(1), entry4.getValue());
                    } else {
                        map.put(entry4.getKey(), entry4.getValue());
                    }
                }
            }
            Iterator<Promotion> it = zzambVar.zzvc().iterator();
            int i = 1;
            while (it.hasNext()) {
                map.putAll(it.next().zzdj(zzd.zzan(i)));
                i++;
            }
            Iterator<Product> it2 = zzambVar.zzva().iterator();
            int i2 = 1;
            while (it2.hasNext()) {
                map.putAll(it2.next().zzdj(zzd.zzal(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry<String, List<Product>> entry5 : zzambVar.zzvb().entrySet()) {
                List<Product> value2 = entry5.getValue();
                String strZzaq = zzd.zzaq(i3);
                int i4 = 1;
                for (Product product : value2) {
                    String strValueOf2 = String.valueOf(strZzaq);
                    String strValueOf3 = String.valueOf(zzd.zzao(i4));
                    map.putAll(product.zzdj(strValueOf3.length() != 0 ? strValueOf2.concat(strValueOf3) : new String(strValueOf2)));
                    i4++;
                }
                if (!TextUtils.isEmpty(entry5.getKey())) {
                    String strValueOf4 = String.valueOf(strZzaq);
                    String strValueOf5 = String.valueOf("nm");
                    map.put(strValueOf5.length() != 0 ? strValueOf4.concat(strValueOf5) : new String(strValueOf4), entry5.getKey());
                }
                i3++;
            }
        }
        zzama zzamaVar = (zzama) zzgVar.zza(zzama.class);
        if (zzamaVar != null) {
            zza(map, "ul", zzamaVar.getLanguage());
            zza(map, "sd", zzamaVar.zzdmn);
            zza(map, "sr", zzamaVar.zzcet, zzamaVar.zzceu);
            zza(map, "vp", zzamaVar.zzdmo, zzamaVar.zzdmp);
        }
        zzalv zzalvVar = (zzalv) zzgVar.zza(zzalv.class);
        if (zzalvVar != null) {
            zza(map, "an", zzalvVar.zzun());
            zza(map, "aid", zzalvVar.getAppId());
            zza(map, "aiid", zzalvVar.zzup());
            zza(map, "av", zzalvVar.zzuo());
        }
        return map;
    }

    static Uri zzcx(String str) {
        zzbp.zzgg(str);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority("google-analytics.com");
        builder.path(str);
        return builder.build();
    }

    @Override // com.google.android.gms.analytics.zzm
    public final void zzb(zzg zzgVar) {
        zzbp.zzu(zzgVar);
        zzbp.zzb(zzgVar.zzub(), "Can't deliver not submitted measurement");
        zzbp.zzgh("deliver should be called on worker thread");
        zzg zzgVarZztx = zzgVar.zztx();
        zzame zzameVar = (zzame) zzgVarZztx.zzb(zzame.class);
        if (TextUtils.isEmpty(zzameVar.zzvd())) {
            zzvy().zze(zzc(zzgVarZztx), "Ignoring measurement without type");
            return;
        }
        if (TextUtils.isEmpty(zzameVar.zzve())) {
            zzvy().zze(zzc(zzgVarZztx), "Ignoring measurement without client id");
            return;
        }
        if (this.zzdji.zzwn().getAppOptOut()) {
            return;
        }
        double dZzvj = zzameVar.zzvj();
        if (zzapd.zza(dZzvj, zzameVar.zzve())) {
            zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(dZzvj));
            return;
        }
        Map<String, String> mapZzc = zzc(zzgVarZztx);
        mapZzc.put("v", "1");
        mapZzc.put("_v", zzamt.zzdoe);
        mapZzc.put("tid", this.zzdjn);
        if (this.zzdji.zzwn().isDryRunEnabled()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : mapZzc.entrySet()) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
            }
            zzc("Dry run is enabled. GoogleAnalytics would have sent", sb.toString());
            return;
        }
        HashMap map = new HashMap();
        zzapd.zzb(map, "uid", zzameVar.getUserId());
        zzalv zzalvVar = (zzalv) zzgVar.zza(zzalv.class);
        if (zzalvVar != null) {
            zzapd.zzb(map, "an", zzalvVar.zzun());
            zzapd.zzb(map, "aid", zzalvVar.getAppId());
            zzapd.zzb(map, "av", zzalvVar.zzuo());
            zzapd.zzb(map, "aiid", zzalvVar.zzup());
        }
        mapZzc.put("_s", String.valueOf(zzwc().zza(new zzamx(0L, zzameVar.zzve(), this.zzdjn, !TextUtils.isEmpty(zzameVar.zzvf()), 0L, map))));
        zzwc().zza(new zzaoi(zzvy(), mapZzc, zzgVar.zztz(), true));
    }

    @Override // com.google.android.gms.analytics.zzm
    public final Uri zztu() {
        return this.zzdjo;
    }
}
