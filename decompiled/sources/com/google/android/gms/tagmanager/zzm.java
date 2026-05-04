package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
class zzm extends zzgi {
    private static final String ID = com.google.android.gms.internal.zzbd.ARBITRARY_PIXEL.toString();
    private static final String URL = com.google.android.gms.internal.zzbe.URL.toString();
    private static final String zzjot = com.google.android.gms.internal.zzbe.ADDITIONAL_PARAMS.toString();
    private static final String zzjou = com.google.android.gms.internal.zzbe.UNREPEATABLE.toString();
    private static String zzjov;
    private static final Set<String> zzjow;
    private final Context mContext;
    private final zza zzjox;

    public interface zza {
        zzby zzbcl();
    }

    static {
        String str = ID;
        zzjov = new StringBuilder(String.valueOf(str).length() + 17).append("gtm_").append(str).append("_unrepeatable").toString();
        zzjow = new HashSet();
    }

    public zzm(Context context) {
        this(context, new zzn(context));
    }

    private zzm(Context context, zza zzaVar) {
        super(ID, URL);
        this.zzjox = zzaVar;
        this.mContext = context;
    }

    private final synchronized boolean zzlc(String str) {
        boolean z = true;
        synchronized (this) {
            if (!zzjow.contains(str)) {
                if (this.mContext.getSharedPreferences(zzjov, 0).contains(str)) {
                    zzjow.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    @Override // com.google.android.gms.tagmanager.zzgi
    public final void zzr(Map<String, com.google.android.gms.internal.zzbp> map) {
        String strZzb = map.get(zzjou) != null ? zzgk.zzb(map.get(zzjou)) : null;
        if (strZzb == null || !zzlc(strZzb)) {
            Uri.Builder builderBuildUpon = Uri.parse(zzgk.zzb(map.get(URL))).buildUpon();
            com.google.android.gms.internal.zzbp zzbpVar = map.get(zzjot);
            if (zzbpVar != null) {
                Object objZzg = zzgk.zzg(zzbpVar);
                if (!(objZzg instanceof List)) {
                    String strValueOf = String.valueOf(builderBuildUpon.build().toString());
                    zzdj.e(strValueOf.length() != 0 ? "ArbitraryPixel: additional params not a list: not sending partial hit: ".concat(strValueOf) : new String("ArbitraryPixel: additional params not a list: not sending partial hit: "));
                    return;
                }
                for (Object obj : (List) objZzg) {
                    if (!(obj instanceof Map)) {
                        String strValueOf2 = String.valueOf(builderBuildUpon.build().toString());
                        zzdj.e(strValueOf2.length() != 0 ? "ArbitraryPixel: additional params contains non-map: not sending partial hit: ".concat(strValueOf2) : new String("ArbitraryPixel: additional params contains non-map: not sending partial hit: "));
                        return;
                    } else {
                        for (Map.Entry entry : ((Map) obj).entrySet()) {
                            builderBuildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                        }
                    }
                }
            }
            String string = builderBuildUpon.build().toString();
            this.zzjox.zzbcl().zzln(string);
            String strValueOf3 = String.valueOf(string);
            zzdj.v(strValueOf3.length() != 0 ? "ArbitraryPixel: url = ".concat(strValueOf3) : new String("ArbitraryPixel: url = "));
            if (strZzb != null) {
                synchronized (zzm.class) {
                    zzjow.add(strZzb);
                    zzfu.zzd(this.mContext, zzjov, strZzb, "true");
                }
            }
        }
    }
}
