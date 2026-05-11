package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import android.util.LogPrinter;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes.dex */
public final class zze implements zzm {
    private static final Uri zzdkh;
    private final LogPrinter zzdki = new LogPrinter(4, "GA/LogCatTransport");

    static {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority("local");
        zzdkh = builder.build();
    }

    @Override // com.google.android.gms.analytics.zzm
    public final void zzb(zzg zzgVar) {
        ArrayList arrayList = new ArrayList(zzgVar.zzty());
        Collections.sort(arrayList, new zzf(this));
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            String string = ((zzh) obj).toString();
            if (!TextUtils.isEmpty(string)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(string);
            }
        }
        this.zzdki.println(sb.toString());
    }

    @Override // com.google.android.gms.analytics.zzm
    public final Uri zztu() {
        return zzdkh;
    }
}
