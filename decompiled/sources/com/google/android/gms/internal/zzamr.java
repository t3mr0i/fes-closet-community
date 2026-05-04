package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;

/* loaded from: classes.dex */
public class zzamr {
    private final zzamu zzdoc;

    protected zzamr(zzamu zzamuVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzamuVar);
        this.zzdoc = zzamuVar;
    }

    private final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zzaon zzaonVarZzwm = this.zzdoc != null ? this.zzdoc.zzwm() : null;
        if (zzaonVarZzwm == null) {
            String str2 = zzaod.zzdra.get();
            if (Log.isLoggable(str2, i)) {
                Log.println(i, str2, zzc(str, obj, obj2, obj3));
                return;
            }
            return;
        }
        String str3 = zzaod.zzdra.get();
        if (Log.isLoggable(str3, i)) {
            Log.println(i, str3, zzaon.zzc(str, obj, obj2, obj3));
        }
        if (i >= 5) {
            zzaonVarZzwm.zzb(i, str, obj, obj2, obj3);
        }
    }

    protected static String zzc(String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String strZzi = zzi(obj);
        String strZzi2 = zzi(obj2);
        String strZzi3 = zzi(obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(strZzi)) {
            sb.append(str2);
            sb.append(strZzi);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZzi2)) {
            sb.append(str2);
            sb.append(strZzi2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZzi3)) {
            sb.append(str2);
            sb.append(strZzi3);
        }
        return sb.toString();
    }

    private static String zzi(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj instanceof Boolean ? obj == Boolean.TRUE ? "true" : "false" : obj instanceof Throwable ? ((Throwable) obj).toString() : obj.toString();
    }

    public static boolean zzqu() {
        return Log.isLoggable(zzaod.zzdra.get(), 2);
    }

    protected final Context getContext() {
        return this.zzdoc.getContext();
    }

    public final void zza(String str, Object obj) {
        zza(2, str, obj, null, null);
    }

    public final void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, null);
    }

    public final void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public final void zzb(String str, Object obj) {
        zza(3, str, obj, null, null);
    }

    public final void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, null);
    }

    public final void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    public final void zzc(String str, Object obj) {
        zza(4, str, obj, null, null);
    }

    public final void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, null);
    }

    public final void zzd(String str, Object obj) {
        zza(5, str, obj, null, null);
    }

    public final void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, null);
    }

    public final void zzdm(String str) {
        zza(2, str, null, null, null);
    }

    public final void zzdn(String str) {
        zza(3, str, null, null, null);
    }

    public final void zzdo(String str) {
        zza(4, str, null, null, null);
    }

    public final void zzdp(String str) {
        zza(5, str, null, null, null);
    }

    public final void zzdq(String str) {
        zza(6, str, null, null, null);
    }

    public final void zze(String str, Object obj) {
        zza(6, str, obj, null, null);
    }

    public final zzamu zzvw() {
        return this.zzdoc;
    }

    protected final com.google.android.gms.common.util.zzd zzvx() {
        return this.zzdoc.zzvx();
    }

    protected final zzaon zzvy() {
        return this.zzdoc.zzvy();
    }

    protected final zzanv zzvz() {
        return this.zzdoc.zzvz();
    }

    protected final com.google.android.gms.analytics.zzj zzwa() {
        return this.zzdoc.zzwa();
    }

    public final GoogleAnalytics zzwb() {
        return this.zzdoc.zzwn();
    }

    protected final zzamj zzwc() {
        return this.zzdoc.zzwc();
    }

    protected final zzaoa zzwd() {
        return this.zzdoc.zzwd();
    }

    protected final zzape zzwe() {
        return this.zzdoc.zzwe();
    }

    protected final zzaor zzwf() {
        return this.zzdoc.zzwf();
    }

    protected final zzanm zzwg() {
        return this.zzdoc.zzwq();
    }

    protected final zzami zzwh() {
        return this.zzdoc.zzwp();
    }

    protected final zzanf zzwi() {
        return this.zzdoc.zzwi();
    }

    protected final zzanz zzwj() {
        return this.zzdoc.zzwj();
    }
}
