package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
final class zzfv implements zzbe {
    private final Context mContext;
    private final String zzbwh;
    private final zzfy zzjvu;
    private final zzfx zzjvv;

    zzfv(Context context, zzfx zzfxVar) {
        this(new zzfw(), context, zzfxVar);
    }

    private zzfv(zzfy zzfyVar, Context context, zzfx zzfxVar) {
        String string = null;
        this.zzjvu = zzfyVar;
        this.mContext = context.getApplicationContext();
        this.zzjvv = zzfxVar;
        String str = Build.VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        if (locale != null && locale.getLanguage() != null && locale.getLanguage().length() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (locale.getCountry() != null && locale.getCountry().length() != 0) {
                sb.append("-").append(locale.getCountry().toLowerCase());
            }
            string = sb.toString();
        }
        this.zzbwh = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", "GoogleTagManager", "4.00", str, string, Build.MODEL, Build.ID);
    }

    private static URL zzd(zzbx zzbxVar) {
        try {
            return new URL(zzbxVar.zzbdu());
        } catch (MalformedURLException e) {
            zzdj.e("Error trying to parse the GTM url.");
            return null;
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbe
    public final void zzai(List<zzbx> list) throws IOException {
        boolean z;
        int iMin = Math.min(list.size(), 40);
        boolean z2 = true;
        int i = 0;
        while (i < iMin) {
            zzbx zzbxVar = list.get(i);
            URL urlZzd = zzd(zzbxVar);
            if (urlZzd == null) {
                zzdj.zzcr("No destination: discarding hit.");
                this.zzjvv.zzb(zzbxVar);
                z = z2;
            } else {
                InputStream inputStream = null;
                try {
                    HttpURLConnection httpURLConnectionZzc = this.zzjvu.zzc(urlZzd);
                    if (z2) {
                        try {
                            zzdo.zzdx(this.mContext);
                            z2 = false;
                        } catch (Throwable th) {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            httpURLConnectionZzc.disconnect();
                            throw th;
                        }
                    }
                    httpURLConnectionZzc.setRequestProperty("User-Agent", this.zzbwh);
                    int responseCode = httpURLConnectionZzc.getResponseCode();
                    inputStream = httpURLConnectionZzc.getInputStream();
                    if (responseCode != 200) {
                        zzdj.zzcr(new StringBuilder(25).append("Bad response: ").append(responseCode).toString());
                        this.zzjvv.zzc(zzbxVar);
                    } else {
                        this.zzjvv.zza(zzbxVar);
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    httpURLConnectionZzc.disconnect();
                    z = z2;
                } catch (IOException e) {
                    boolean z3 = z2;
                    String strValueOf = String.valueOf(e.getClass().getSimpleName());
                    zzdj.zzcr(strValueOf.length() != 0 ? "Exception sending hit: ".concat(strValueOf) : new String("Exception sending hit: "));
                    zzdj.zzcr(e.getMessage());
                    this.zzjvv.zzc(zzbxVar);
                    z = z3;
                }
            }
            i++;
            z2 = z;
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbe
    public final boolean zzbdl() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdj.v("...no network connectivity");
        return false;
    }
}
