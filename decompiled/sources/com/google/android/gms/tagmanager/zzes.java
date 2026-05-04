package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.internal.zzdbo;
import com.google.android.gms.internal.zzdbz;
import com.google.android.gms.internal.zzdca;
import com.google.android.gms.internal.zzdcb;
import com.google.android.gms.internal.zzehg;
import com.google.android.gms.tagmanager.zzei;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
final class zzes implements Runnable {
    private final Context mContext;
    private final String zzjoz;
    private volatile String zzjpx;
    private final zzdca zzjtw;
    private final String zzjtx;
    private zzdi<com.google.android.gms.internal.zzbo> zzjty;
    private volatile zzal zzjtz;
    private volatile String zzjua;

    private zzes(Context context, String str, zzdca zzdcaVar, zzal zzalVar) {
        this.mContext = context;
        this.zzjtw = zzdcaVar;
        this.zzjoz = str;
        this.zzjtz = zzalVar;
        String strValueOf = String.valueOf("/r?id=");
        String strValueOf2 = String.valueOf(str);
        this.zzjtx = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        this.zzjpx = this.zzjtx;
        this.zzjua = null;
    }

    public zzes(Context context, String str, zzal zzalVar) {
        this(context, str, new zzdca(), zzalVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        String strConcat;
        if (this.zzjty == null) {
            throw new IllegalStateException("callback must be set before execute");
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            zzdj.v("...no network connectivity");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            this.zzjty.zzed(zzda.zzjsk);
            return;
        }
        zzdj.v("Start loading resource from network ...");
        String strZzbdd = this.zzjtz.zzbdd();
        String str = this.zzjpx;
        String string = new StringBuilder(String.valueOf(strZzbdd).length() + String.valueOf(str).length() + String.valueOf("&v=a65833898").length()).append(strZzbdd).append(str).append("&v=a65833898").toString();
        if (this.zzjua != null && !this.zzjua.trim().equals("")) {
            String strValueOf = String.valueOf(string);
            String str2 = this.zzjua;
            string = new StringBuilder(String.valueOf(strValueOf).length() + String.valueOf("&pv=").length() + String.valueOf(str2).length()).append(strValueOf).append("&pv=").append(str2).toString();
        }
        if (zzei.zzbei().zzbej().equals(zzei.zza.CONTAINER_DEBUG)) {
            String strValueOf2 = String.valueOf(string);
            String strValueOf3 = String.valueOf("&gtm_debug=x");
            strConcat = strValueOf3.length() != 0 ? strValueOf2.concat(strValueOf3) : new String(strValueOf2);
        } else {
            strConcat = string;
        }
        zzdbz zzdbzVarZzbie = zzdca.zzbie();
        InputStream inputStreamZzna = null;
        try {
            try {
                inputStreamZzna = zzdbzVarZzbie.zzna(strConcat);
            } catch (zzdcb e) {
                String strValueOf4 = String.valueOf(strConcat);
                zzdj.zzcr(strValueOf4.length() != 0 ? "Error when loading resource for url: ".concat(strValueOf4) : new String("Error when loading resource for url: "));
                this.zzjty.zzed(zzda.zzjsn);
            } catch (FileNotFoundException e2) {
                String str3 = this.zzjoz;
                zzdj.zzcr(new StringBuilder(String.valueOf(strConcat).length() + 79 + String.valueOf(str3).length()).append("No data is retrieved from the given url: ").append(strConcat).append(". Make sure container_id: ").append(str3).append(" is correct.").toString());
                this.zzjty.zzed(zzda.zzjsm);
                zzdbzVarZzbie.close();
                return;
            } catch (IOException e3) {
                String message = e3.getMessage();
                zzdj.zzc(new StringBuilder(String.valueOf(strConcat).length() + 40 + String.valueOf(message).length()).append("Error when loading resources from url: ").append(strConcat).append(" ").append(message).toString(), e3);
                this.zzjty.zzed(zzda.zzjsl);
                zzdbzVarZzbie.close();
                return;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzdbo.zzb(inputStreamZzna, byteArrayOutputStream);
                com.google.android.gms.internal.zzbo zzboVar = (com.google.android.gms.internal.zzbo) zzehg.zza(new com.google.android.gms.internal.zzbo(), byteArrayOutputStream.toByteArray());
                String strValueOf5 = String.valueOf(zzboVar);
                zzdj.v(new StringBuilder(String.valueOf(strValueOf5).length() + 43).append("Successfully loaded supplemented resource: ").append(strValueOf5).toString());
                if (zzboVar.zzxw == null && zzboVar.zzxv.length == 0) {
                    String strValueOf6 = String.valueOf(this.zzjoz);
                    zzdj.v(strValueOf6.length() != 0 ? "No change for container: ".concat(strValueOf6) : new String("No change for container: "));
                }
                this.zzjty.onSuccess(zzboVar);
                zzdbzVarZzbie.close();
                zzdj.v("Load resource from network finished.");
            } catch (IOException e4) {
                String message2 = e4.getMessage();
                zzdj.zzc(new StringBuilder(String.valueOf(strConcat).length() + 51 + String.valueOf(message2).length()).append("Error when parsing downloaded resources from url: ").append(strConcat).append(" ").append(message2).toString(), e4);
                this.zzjty.zzed(zzda.zzjsm);
                zzdbzVarZzbie.close();
            }
        } catch (Throwable th) {
            zzdbzVarZzbie.close();
            throw th;
        }
    }

    final void zza(zzdi<com.google.android.gms.internal.zzbo> zzdiVar) {
        this.zzjty = zzdiVar;
    }

    final void zzlh(String str) {
        if (str == null) {
            this.zzjpx = this.zzjtx;
            return;
        }
        String strValueOf = String.valueOf(str);
        zzdj.zzca(strValueOf.length() != 0 ? "Setting CTFE URL path: ".concat(strValueOf) : new String("Setting CTFE URL path: "));
        this.zzjpx = str;
    }

    final void zzlx(String str) {
        String strValueOf = String.valueOf(str);
        zzdj.zzca(strValueOf.length() != 0 ? "Setting previous container version: ".concat(strValueOf) : new String("Setting previous container version: "));
        this.zzjua = str;
    }
}
