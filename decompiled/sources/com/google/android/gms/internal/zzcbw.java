package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.measurement.AppMeasurement;

/* loaded from: classes.dex */
public final class zzcbw extends zzcdu {
    private final String zzfvj;
    private final long zzilf;
    private final char zzipq;
    private final zzcby zzipr;
    private final zzcby zzips;
    private final zzcby zzipt;
    private final zzcby zzipu;
    private final zzcby zzipv;
    private final zzcby zzipw;
    private final zzcby zzipx;
    private final zzcby zzipy;
    private final zzcby zzipz;

    zzcbw(zzccw zzccwVar) {
        super(zzccwVar);
        this.zzfvj = zzcax.zzavm();
        this.zzilf = zzcax.zzauw();
        if (zzauo().zzxu()) {
            zzcax.zzawl();
            this.zzipq = 'C';
        } else {
            zzcax.zzawl();
            this.zzipq = 'c';
        }
        this.zzipr = new zzcby(this, 6, false, false);
        this.zzips = new zzcby(this, 6, true, false);
        this.zzipt = new zzcby(this, 6, false, true);
        this.zzipu = new zzcby(this, 5, false, false);
        this.zzipv = new zzcby(this, 5, true, false);
        this.zzipw = new zzcby(this, 5, false, true);
        this.zzipx = new zzcby(this, 4, false, false);
        this.zzipy = new zzcby(this, 3, false, false);
        this.zzipz = new zzcby(this, 2, false, false);
    }

    private static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String strZzc = zzc(z, obj);
        String strZzc2 = zzc(z, obj2);
        String strZzc3 = zzc(z, obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(strZzc)) {
            sb.append(str2);
            sb.append(strZzc);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZzc2)) {
            sb.append(str2);
            sb.append(strZzc2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(strZzc3)) {
            sb.append(str2);
            sb.append(strZzc3);
        }
        return sb.toString();
    }

    private static String zzc(boolean z, Object obj) {
        String className;
        if (obj == null) {
            return "";
        }
        Object objValueOf = obj instanceof Integer ? Long.valueOf(((Integer) obj).intValue()) : obj;
        if (objValueOf instanceof Long) {
            if (z && Math.abs(((Long) objValueOf).longValue()) >= 100) {
                String str = String.valueOf(objValueOf).charAt(0) == '-' ? "-" : "";
                String strValueOf = String.valueOf(Math.abs(((Long) objValueOf).longValue()));
                return new StringBuilder(String.valueOf(str).length() + 43 + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, strValueOf.length() - 1))).append("...").append(str).append(Math.round(Math.pow(10.0d, strValueOf.length()) - 1.0d)).toString();
            }
            return String.valueOf(objValueOf);
        }
        if (objValueOf instanceof Boolean) {
            return String.valueOf(objValueOf);
        }
        if (!(objValueOf instanceof Throwable)) {
            return objValueOf instanceof zzcbz ? ((zzcbz) objValueOf).zzgqk : z ? "-" : String.valueOf(objValueOf);
        }
        Throwable th = (Throwable) objValueOf;
        StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
        String strZzjg = zzjg(AppMeasurement.class.getCanonicalName());
        String strZzjg2 = zzjg(zzccw.class.getCanonicalName());
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null) {
                String strZzjg3 = zzjg(className);
                if (strZzjg3.equals(strZzjg) || strZzjg3.equals(strZzjg2)) {
                    sb.append(": ");
                    sb.append(stackTraceElement);
                    break;
                }
            }
        }
        return sb.toString();
    }

    protected static Object zzjf(String str) {
        if (str == null) {
            return null;
        }
        return new zzcbz(str);
    }

    private static String zzjg(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf != -1 ? str.substring(0, iLastIndexOf) : str;
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    protected final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) throws IllegalStateException {
        if (!z && zzad(i)) {
            zzk(i, zza(false, str, obj, obj2, obj3));
        }
        if (z2 || i < 5) {
            return;
        }
        com.google.android.gms.common.internal.zzbp.zzu(str);
        zzccr zzccrVarZzayy = this.zzikh.zzayy();
        if (zzccrVarZzayy == null) {
            zzk(6, "Scheduler not set. Not logging error/warn");
            return;
        }
        if (!zzccrVarZzayy.isInitialized()) {
            zzk(6, "Scheduler not initialized. Not logging error/warn");
            return;
        }
        int i2 = i < 0 ? 0 : i;
        if (i2 >= 9) {
            i2 = 8;
        }
        char cCharAt = "01VDIWEA?".charAt(i2);
        char c = this.zzipq;
        long j = this.zzilf;
        String strZza = zza(true, str, obj, obj2, obj3);
        String string = new StringBuilder(String.valueOf("2").length() + 23 + String.valueOf(strZza).length()).append("2").append(cCharAt).append(c).append(j).append(":").append(strZza).toString();
        if (string.length() > 1024) {
            string = str.substring(0, 1024);
        }
        zzccrVarZzayy.zzg(new zzcbx(this, string));
    }

    protected final boolean zzad(int i) {
        return Log.isLoggable(this.zzfvj, i);
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

    public final zzcby zzaye() {
        return this.zzipr;
    }

    public final zzcby zzayf() {
        return this.zzips;
    }

    public final zzcby zzayg() {
        return this.zzipu;
    }

    public final zzcby zzayh() {
        return this.zzipw;
    }

    public final zzcby zzayi() {
        return this.zzipx;
    }

    public final zzcby zzayj() {
        return this.zzipy;
    }

    public final zzcby zzayk() {
        return this.zzipz;
    }

    public final String zzayl() {
        Pair<String, Long> pairZzzi = zzaun().zziqn.zzzi();
        if (pairZzzi == null || pairZzzi == zzcch.zziqm) {
            return null;
        }
        String strValueOf = String.valueOf(pairZzzi.second);
        String str = (String) pairZzzi.first;
        return new StringBuilder(String.valueOf(strValueOf).length() + 1 + String.valueOf(str).length()).append(strValueOf).append(":").append(str).toString();
    }

    protected final void zzk(int i, String str) {
        Log.println(i, this.zzfvj, str);
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
}
