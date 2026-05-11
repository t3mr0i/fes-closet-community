package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.measurement.AppMeasurement;

/* loaded from: classes.dex */
public final class zzcbu extends zzcdu {
    private static String[] zzipn = new String[AppMeasurement.Event.zziki.length];
    private static String[] zzipo = new String[AppMeasurement.Param.zzikk.length];
    private static String[] zzipp = new String[AppMeasurement.UserProperty.zzikp.length];

    zzcbu(zzccw zzccwVar) {
        super(zzccwVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
    
        return r4;
     */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String zza(java.lang.String r4, java.lang.String[] r5, java.lang.String[] r6, java.lang.String[] r7) {
        /*
            r1 = 1
            r2 = 0
            com.google.android.gms.common.internal.zzbp.zzu(r5)
            com.google.android.gms.common.internal.zzbp.zzu(r6)
            com.google.android.gms.common.internal.zzbp.zzu(r7)
            int r0 = r5.length
            int r3 = r6.length
            if (r0 != r3) goto L4d
            r0 = r1
        L10:
            com.google.android.gms.common.internal.zzbp.zzbh(r0)
            int r0 = r5.length
            int r3 = r7.length
            if (r0 != r3) goto L4f
        L17:
            com.google.android.gms.common.internal.zzbp.zzbh(r1)
        L1a:
            int r0 = r5.length
            if (r2 >= r0) goto L4c
            r0 = r5[r2]
            boolean r0 = com.google.android.gms.internal.zzcfw.zzas(r4, r0)
            if (r0 == 0) goto L54
            monitor-enter(r7)
            r0 = r7[r2]     // Catch: java.lang.Throwable -> L51
            if (r0 != 0) goto L49
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L51
            r0.<init>()     // Catch: java.lang.Throwable -> L51
            r1 = r6[r2]     // Catch: java.lang.Throwable -> L51
            r0.append(r1)     // Catch: java.lang.Throwable -> L51
            java.lang.String r1 = "("
            r0.append(r1)     // Catch: java.lang.Throwable -> L51
            r1 = r5[r2]     // Catch: java.lang.Throwable -> L51
            r0.append(r1)     // Catch: java.lang.Throwable -> L51
            java.lang.String r1 = ")"
            r0.append(r1)     // Catch: java.lang.Throwable -> L51
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L51
            r7[r2] = r0     // Catch: java.lang.Throwable -> L51
        L49:
            r4 = r7[r2]     // Catch: java.lang.Throwable -> L51
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L51
        L4c:
            return r4
        L4d:
            r0 = r2
            goto L10
        L4f:
            r1 = r2
            goto L17
        L51:
            r0 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L51
            throw r0
        L54:
            int r2 = r2 + 1
            goto L1a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcbu.zza(java.lang.String, java.lang.String[], java.lang.String[], java.lang.String[]):java.lang.String");
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private final void zza(StringBuilder sb, int i, zzcfz zzcfzVar) {
        if (zzcfzVar == null) {
            return;
        }
        zza(sb, i);
        sb.append("filter {\n");
        zza(sb, i, "complement", zzcfzVar.zzixu);
        zza(sb, i, "param_name", zzjd(zzcfzVar.zzixv));
        int i2 = i + 1;
        zzcgc zzcgcVar = zzcfzVar.zzixs;
        if (zzcgcVar != null) {
            zza(sb, i2);
            sb.append("string_filter");
            sb.append(" {\n");
            if (zzcgcVar.zziye != null) {
                String str = "UNKNOWN_MATCH_TYPE";
                switch (zzcgcVar.zziye.intValue()) {
                    case 1:
                        str = "REGEXP";
                        break;
                    case 2:
                        str = "BEGINS_WITH";
                        break;
                    case 3:
                        str = "ENDS_WITH";
                        break;
                    case 4:
                        str = "PARTIAL";
                        break;
                    case 5:
                        str = "EXACT";
                        break;
                    case 6:
                        str = "IN_LIST";
                        break;
                }
                zza(sb, i2, "match_type", str);
            }
            zza(sb, i2, "expression", zzcgcVar.zziyf);
            zza(sb, i2, "case_sensitive", zzcgcVar.zziyg);
            if (zzcgcVar.zziyh.length > 0) {
                zza(sb, i2 + 1);
                sb.append("expression_list {\n");
                for (String str2 : zzcgcVar.zziyh) {
                    zza(sb, i2 + 2);
                    sb.append(str2);
                    sb.append("\n");
                }
                sb.append("}\n");
            }
            zza(sb, i2);
            sb.append("}\n");
        }
        zza(sb, i + 1, "number_filter", zzcfzVar.zzixt);
        zza(sb, i);
        sb.append("}\n");
    }

    private final void zza(StringBuilder sb, int i, String str, zzcga zzcgaVar) {
        if (zzcgaVar == null) {
            return;
        }
        zza(sb, i);
        sb.append(str);
        sb.append(" {\n");
        if (zzcgaVar.zzixw != null) {
            String str2 = "UNKNOWN_COMPARISON_TYPE";
            switch (zzcgaVar.zzixw.intValue()) {
                case 1:
                    str2 = "LESS_THAN";
                    break;
                case 2:
                    str2 = "GREATER_THAN";
                    break;
                case 3:
                    str2 = "EQUAL";
                    break;
                case 4:
                    str2 = "BETWEEN";
                    break;
            }
            zza(sb, i, "comparison_type", str2);
        }
        zza(sb, i, "match_as_float", zzcgaVar.zzixx);
        zza(sb, i, "comparison_value", zzcgaVar.zzixy);
        zza(sb, i, "min_comparison_value", zzcgaVar.zzixz);
        zza(sb, i, "max_comparison_value", zzcgaVar.zziya);
        zza(sb, i);
        sb.append("}\n");
    }

    private static void zza(StringBuilder sb, int i, String str, zzcgl zzcglVar) {
        if (zzcglVar == null) {
            return;
        }
        int i2 = i + 1;
        zza(sb, i2);
        sb.append(str);
        sb.append(" {\n");
        if (zzcglVar.zzjag != null) {
            zza(sb, i2 + 1);
            sb.append("results: ");
            long[] jArr = zzcglVar.zzjag;
            int length = jArr.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                Long lValueOf = Long.valueOf(jArr[i3]);
                int i5 = i4 + 1;
                if (i4 != 0) {
                    sb.append(", ");
                }
                sb.append(lValueOf);
                i3++;
                i4 = i5;
            }
            sb.append('\n');
        }
        if (zzcglVar.zzjaf != null) {
            zza(sb, i2 + 1);
            sb.append("status: ");
            long[] jArr2 = zzcglVar.zzjaf;
            int length2 = jArr2.length;
            int i6 = 0;
            int i7 = 0;
            while (i6 < length2) {
                Long lValueOf2 = Long.valueOf(jArr2[i6]);
                int i8 = i7 + 1;
                if (i7 != 0) {
                    sb.append(", ");
                }
                sb.append(lValueOf2);
                i6++;
                i7 = i8;
            }
            sb.append('\n');
        }
        zza(sb, i2);
        sb.append("}\n");
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj == null) {
            return;
        }
        zza(sb, i + 1);
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        sb.append('\n');
    }

    private final void zza(StringBuilder sb, int i, zzcgg[] zzcggVarArr) {
        if (zzcggVarArr == null) {
            return;
        }
        for (zzcgg zzcggVar : zzcggVarArr) {
            if (zzcggVar != null) {
                zza(sb, 2);
                sb.append("audience_membership {\n");
                zza(sb, 2, "audience_id", zzcggVar.zzixi);
                zza(sb, 2, "new_audience", zzcggVar.zziyu);
                zza(sb, 2, "current_data", zzcggVar.zziys);
                zza(sb, 2, "previous_data", zzcggVar.zziyt);
                zza(sb, 2);
                sb.append("}\n");
            }
        }
    }

    private final void zza(StringBuilder sb, int i, zzcgh[] zzcghVarArr) {
        if (zzcghVarArr == null) {
            return;
        }
        for (zzcgh zzcghVar : zzcghVarArr) {
            if (zzcghVar != null) {
                zza(sb, 2);
                sb.append("event {\n");
                zza(sb, 2, "name", zzjc(zzcghVar.name));
                zza(sb, 2, "timestamp_millis", zzcghVar.zziyx);
                zza(sb, 2, "previous_timestamp_millis", zzcghVar.zziyy);
                zza(sb, 2, "count", zzcghVar.count);
                zzcgi[] zzcgiVarArr = zzcghVar.zziyw;
                if (zzcgiVarArr != null) {
                    for (zzcgi zzcgiVar : zzcgiVarArr) {
                        if (zzcgiVar != null) {
                            zza(sb, 3);
                            sb.append("param {\n");
                            zza(sb, 3, "name", zzjd(zzcgiVar.name));
                            zza(sb, 3, "string_value", zzcgiVar.zzfwn);
                            zza(sb, 3, "int_value", zzcgiVar.zziza);
                            zza(sb, 3, "double_value", zzcgiVar.zzixb);
                            zza(sb, 3);
                            sb.append("}\n");
                        }
                    }
                }
                zza(sb, 2);
                sb.append("}\n");
            }
        }
    }

    private final void zza(StringBuilder sb, int i, zzcgm[] zzcgmVarArr) {
        if (zzcgmVarArr == null) {
            return;
        }
        for (zzcgm zzcgmVar : zzcgmVarArr) {
            if (zzcgmVar != null) {
                zza(sb, 2);
                sb.append("user_property {\n");
                zza(sb, 2, "set_timestamp_millis", zzcgmVar.zzjai);
                zza(sb, 2, "name", zzje(zzcgmVar.name));
                zza(sb, 2, "string_value", zzcgmVar.zzfwn);
                zza(sb, 2, "int_value", zzcgmVar.zziza);
                zza(sb, 2, "double_value", zzcgmVar.zzixb);
                zza(sb, 2);
                sb.append("}\n");
            }
        }
    }

    private final boolean zzayd() {
        return this.zzikh.zzaum().zzad(3);
    }

    @Nullable
    private final String zzb(zzcbh zzcbhVar) {
        if (zzcbhVar == null) {
            return null;
        }
        return !zzayd() ? zzcbhVar.toString() : zzx(zzcbhVar.zzaya());
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Nullable
    protected final String zza(zzcbf zzcbfVar) {
        if (zzcbfVar == null) {
            return null;
        }
        if (!zzayd()) {
            return zzcbfVar.toString();
        }
        return "Event{appId='" + zzcbfVar.mAppId + "', name='" + zzjc(zzcbfVar.mName) + "', params=" + zzb(zzcbfVar.zzinj) + "}";
    }

    protected final String zza(zzcfy zzcfyVar) {
        if (zzcfyVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", zzcfyVar.zzixm);
        zza(sb, 0, "event_name", zzjc(zzcfyVar.zzixn));
        zza(sb, 1, "event_count_filter", zzcfyVar.zzixq);
        sb.append("  filters {\n");
        for (zzcfz zzcfzVar : zzcfyVar.zzixo) {
            zza(sb, 2, zzcfzVar);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    protected final String zza(zzcgb zzcgbVar) {
        if (zzcgbVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", zzcgbVar.zzixm);
        zza(sb, 0, "property_name", zzje(zzcgbVar.zziyc));
        zza(sb, 1, zzcgbVar.zziyd);
        sb.append("}\n");
        return sb.toString();
    }

    protected final String zza(zzcgj zzcgjVar) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzcgjVar.zzizb != null) {
            for (zzcgk zzcgkVar : zzcgjVar.zzizb) {
                if (zzcgkVar != null && zzcgkVar != null) {
                    zza(sb, 1);
                    sb.append("bundle {\n");
                    zza(sb, 1, "protocol_version", zzcgkVar.zzizd);
                    zza(sb, 1, "platform", zzcgkVar.zzizl);
                    zza(sb, 1, "gmp_version", zzcgkVar.zzizp);
                    zza(sb, 1, "uploading_gmp_version", zzcgkVar.zzizq);
                    zza(sb, 1, "config_version", zzcgkVar.zzjac);
                    zza(sb, 1, "gmp_app_id", zzcgkVar.zzilt);
                    zza(sb, 1, "app_id", zzcgkVar.zzch);
                    zza(sb, 1, "app_version", zzcgkVar.zzhts);
                    zza(sb, 1, "app_version_major", zzcgkVar.zzizy);
                    zza(sb, 1, "firebase_instance_id", zzcgkVar.zzimb);
                    zza(sb, 1, "dev_cert_hash", zzcgkVar.zzizu);
                    zza(sb, 1, "app_store", zzcgkVar.zzilu);
                    zza(sb, 1, "upload_timestamp_millis", zzcgkVar.zzizg);
                    zza(sb, 1, "start_timestamp_millis", zzcgkVar.zzizh);
                    zza(sb, 1, "end_timestamp_millis", zzcgkVar.zzizi);
                    zza(sb, 1, "previous_bundle_start_timestamp_millis", zzcgkVar.zzizj);
                    zza(sb, 1, "previous_bundle_end_timestamp_millis", zzcgkVar.zzizk);
                    zza(sb, 1, "app_instance_id", zzcgkVar.zzizt);
                    zza(sb, 1, "resettable_device_id", zzcgkVar.zzizr);
                    zza(sb, 1, "device_id", zzcgkVar.zzjab);
                    zza(sb, 1, "limited_ad_tracking", zzcgkVar.zzizs);
                    zza(sb, 1, "os_version", zzcgkVar.zzcv);
                    zza(sb, 1, "device_model", zzcgkVar.zzizm);
                    zza(sb, 1, "user_default_language", zzcgkVar.zzizn);
                    zza(sb, 1, "time_zone_offset_minutes", zzcgkVar.zzizo);
                    zza(sb, 1, "bundle_sequential_index", zzcgkVar.zzizv);
                    zza(sb, 1, "service_upload", zzcgkVar.zzizw);
                    zza(sb, 1, "health_monitor", zzcgkVar.zzilx);
                    if (zzcgkVar.zzjad.longValue() != 0) {
                        zza(sb, 1, "android_id", zzcgkVar.zzjad);
                    }
                    zza(sb, 1, zzcgkVar.zzizf);
                    zza(sb, 1, zzcgkVar.zzizx);
                    zza(sb, 1, zzcgkVar.zzize);
                    zza(sb, 1);
                    sb.append("}\n");
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatx() {
        super.zzatx();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcan zzaty() {
        return super.zzaty();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcau zzatz() {
        return super.zzatz();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcdw zzaua() {
        return super.zzaua();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbr zzaub() {
        return super.zzaub();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbe zzauc() {
        return super.zzauc();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzceo zzaud() {
        return super.zzaud();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcek zzaue() {
        return super.zzaue();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbs zzauf() {
        return super.zzauf();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcay zzaug() {
        return super.zzaug();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbu zzauh() {
        return super.zzauh();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfw zzaui() {
        return super.zzaui();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccq zzauj() {
        return super.zzauj();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfl zzauk() {
        return super.zzauk();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccr zzaul() {
        return super.zzaul();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbw zzaum() {
        return super.zzaum();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcch zzaun() {
        return super.zzaun();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcax zzauo() {
        return super.zzauo();
    }

    @Nullable
    protected final String zzb(zzcbk zzcbkVar) {
        if (zzcbkVar == null) {
            return null;
        }
        if (!zzayd()) {
            return zzcbkVar.toString();
        }
        return "origin=" + zzcbkVar.zzimf + ",name=" + zzjc(zzcbkVar.name) + ",params=" + zzb(zzcbkVar.zzinq);
    }

    @Nullable
    protected final String zzjc(String str) {
        if (str == null) {
            return null;
        }
        return zzayd() ? zza(str, AppMeasurement.Event.zzikj, AppMeasurement.Event.zziki, zzipn) : str;
    }

    @Nullable
    protected final String zzjd(String str) {
        if (str == null) {
            return null;
        }
        return zzayd() ? zza(str, AppMeasurement.Param.zzikl, AppMeasurement.Param.zzikk, zzipo) : str;
    }

    @Nullable
    protected final String zzje(String str) {
        if (str == null) {
            return null;
        }
        if (!zzayd()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, AppMeasurement.UserProperty.zzikq, AppMeasurement.UserProperty.zzikp, zzipp);
        }
        return "experiment_id(" + str + ")";
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ com.google.android.gms.common.util.zzd zzvx() {
        return super.zzvx();
    }

    @Nullable
    protected final String zzx(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzayd()) {
            return bundle.toString();
        }
        StringBuilder sb = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (sb.length() != 0) {
                sb.append(", ");
            } else {
                sb.append("Bundle[{");
            }
            sb.append(zzjd(str));
            sb.append("=");
            sb.append(bundle.get(str));
        }
        sb.append("}]");
        return sb.toString();
    }
}
