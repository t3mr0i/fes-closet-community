package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* loaded from: classes.dex */
class zzei {
    private static zzei zzjti;
    private volatile zza zzjtj = zza.NONE;
    private volatile String zzjtk = null;
    private volatile String zzjoz = null;
    private volatile String zzjtl = null;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzei() {
    }

    static zzei zzbei() {
        zzei zzeiVar;
        synchronized (zzei.class) {
            if (zzjti == null) {
                zzjti = new zzei();
            }
            zzeiVar = zzjti;
        }
        return zzeiVar;
    }

    private static String zzlw(String str) {
        return str.split("&")[0].split("=")[1];
    }

    final String getContainerId() {
        return this.zzjoz;
    }

    final zza zzbej() {
        return this.zzjtj;
    }

    final String zzbek() {
        return this.zzjtk;
    }

    final synchronized boolean zzq(Uri uri) {
        boolean z = true;
        synchronized (this) {
            try {
                String strDecode = URLDecoder.decode(uri.toString(), "UTF-8");
                if (strDecode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                    String strValueOf = String.valueOf(strDecode);
                    zzdj.v(strValueOf.length() != 0 ? "Container preview url: ".concat(strValueOf) : new String("Container preview url: "));
                    if (strDecode.matches(".*?&gtm_debug=x$")) {
                        this.zzjtj = zza.CONTAINER_DEBUG;
                    } else {
                        this.zzjtj = zza.CONTAINER;
                    }
                    this.zzjtl = uri.getQuery().replace("&gtm_debug=x", "");
                    if (this.zzjtj == zza.CONTAINER || this.zzjtj == zza.CONTAINER_DEBUG) {
                        String strValueOf2 = String.valueOf("/r?");
                        String strValueOf3 = String.valueOf(this.zzjtl);
                        this.zzjtk = strValueOf3.length() != 0 ? strValueOf2.concat(strValueOf3) : new String(strValueOf2);
                    }
                    this.zzjoz = zzlw(this.zzjtl);
                } else if (!strDecode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    String strValueOf4 = String.valueOf(strDecode);
                    zzdj.zzcr(strValueOf4.length() != 0 ? "Invalid preview uri: ".concat(strValueOf4) : new String("Invalid preview uri: "));
                    z = false;
                } else if (zzlw(uri.getQuery()).equals(this.zzjoz)) {
                    String strValueOf5 = String.valueOf(this.zzjoz);
                    zzdj.v(strValueOf5.length() != 0 ? "Exit preview mode for container: ".concat(strValueOf5) : new String("Exit preview mode for container: "));
                    this.zzjtj = zza.NONE;
                    this.zzjtk = null;
                } else {
                    z = false;
                }
            } catch (UnsupportedEncodingException e) {
                z = false;
            }
        }
        return z;
    }
}
