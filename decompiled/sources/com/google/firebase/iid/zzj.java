package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;

/* loaded from: classes.dex */
public final class zzj {
    private static Map<String, zzj> zzhtm = new ArrayMap();
    static String zzhts;
    private static zzr zzmlt;
    private static zzl zzmlu;
    private Context mContext;
    private KeyPair zzhtp;
    private String zzhtq;

    private zzj(Context context, String str, Bundle bundle) {
        this.zzhtq = "";
        this.mContext = context.getApplicationContext();
        this.zzhtq = str;
    }

    public static synchronized zzj zza(Context context, Bundle bundle) {
        zzj zzjVar;
        String string = bundle == null ? "" : bundle.getString("subtype");
        String str = string == null ? "" : string;
        Context applicationContext = context.getApplicationContext();
        if (zzmlt == null) {
            zzmlt = new zzr(applicationContext);
            zzmlu = new zzl(applicationContext);
        }
        zzhts = Integer.toString(FirebaseInstanceId.zzej(applicationContext));
        zzjVar = zzhtm.get(str);
        if (zzjVar == null) {
            zzjVar = new zzj(applicationContext, str, bundle);
            zzhtm.put(str, zzjVar);
        }
        return zzjVar;
    }

    public static zzr zzbyo() {
        return zzmlt;
    }

    public static zzl zzbyp() {
        return zzmlu;
    }

    public final long getCreationTime() {
        return zzmlt.zzqf(this.zzhtq);
    }

    public final String getToken(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        boolean z = true;
        if (bundle.getString("ttl") != null || "jwt".equals(bundle.getString("type"))) {
            z = false;
        } else {
            zzs zzsVarZzo = zzmlt.zzo(this.zzhtq, str, str2);
            if (zzsVarZzo != null && !zzsVarZzo.zzqk(zzhts)) {
                return zzsVarZzo.zzkoo;
            }
        }
        String strZzb = zzb(str, str2, bundle);
        if (strZzb == null || !z) {
            return strZzb;
        }
        zzmlt.zza(this.zzhtq, str, str2, strZzb, zzhts);
        return strZzb;
    }

    public final void zza(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        zzmlt.zzf(this.zzhtq, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("delete", "1");
        zzb(str, str2, bundle);
    }

    final KeyPair zzasr() {
        if (this.zzhtp == null) {
            this.zzhtp = zzmlt.zzqi(this.zzhtq);
        }
        if (this.zzhtp == null) {
            this.zzhtp = zzmlt.zzqg(this.zzhtq);
        }
        return this.zzhtp;
    }

    public final void zzass() {
        zzmlt.zzqh(this.zzhtq);
        this.zzhtp = null;
    }

    public final String zzb(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        if (!"".equals(this.zzhtq)) {
            str = this.zzhtq;
        }
        bundle.putString("subtype", str);
        bundle.putString("X-subtype", str);
        Intent intentZza = zzmlu.zza(bundle, zzasr());
        if (intentZza == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String stringExtra = intentZza.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intentZza.getStringExtra("unregistered");
        }
        if (stringExtra != null) {
            return stringExtra;
        }
        String stringExtra2 = intentZza.getStringExtra("error");
        if (stringExtra2 != null) {
            throw new IOException(stringExtra2);
        }
        String strValueOf = String.valueOf(intentZza.getExtras());
        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(strValueOf).length() + 29).append("Unexpected response from GCM ").append(strValueOf).toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
}
