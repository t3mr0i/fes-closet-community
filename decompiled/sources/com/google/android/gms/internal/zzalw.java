package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzalw extends com.google.android.gms.analytics.zzh<zzalw> {
    private String mName;
    private String zzbqr;
    private String zzbsw;
    private String zzdmd;
    private String zzdme;
    private String zzdmf;
    private String zzdmg;
    private String zzdmh;
    private String zzdmi;
    private String zzdmj;

    public final String getContent() {
        return this.zzbqr;
    }

    public final String getId() {
        return this.zzbsw;
    }

    public final String getName() {
        return this.mName;
    }

    public final String getSource() {
        return this.zzdmd;
    }

    public final void setName(String str) {
        this.mName = str;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("name", this.mName);
        map.put(FirebaseAnalytics.Param.SOURCE, this.zzdmd);
        map.put(FirebaseAnalytics.Param.MEDIUM, this.zzdme);
        map.put("keyword", this.zzdmf);
        map.put(FirebaseAnalytics.Param.CONTENT, this.zzbqr);
        map.put("id", this.zzbsw);
        map.put("adNetworkId", this.zzdmg);
        map.put("gclid", this.zzdmh);
        map.put("dclid", this.zzdmi);
        map.put(FirebaseAnalytics.Param.ACLID, this.zzdmj);
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzalw zzalwVar = (zzalw) zzhVar;
        if (!TextUtils.isEmpty(this.mName)) {
            zzalwVar.mName = this.mName;
        }
        if (!TextUtils.isEmpty(this.zzdmd)) {
            zzalwVar.zzdmd = this.zzdmd;
        }
        if (!TextUtils.isEmpty(this.zzdme)) {
            zzalwVar.zzdme = this.zzdme;
        }
        if (!TextUtils.isEmpty(this.zzdmf)) {
            zzalwVar.zzdmf = this.zzdmf;
        }
        if (!TextUtils.isEmpty(this.zzbqr)) {
            zzalwVar.zzbqr = this.zzbqr;
        }
        if (!TextUtils.isEmpty(this.zzbsw)) {
            zzalwVar.zzbsw = this.zzbsw;
        }
        if (!TextUtils.isEmpty(this.zzdmg)) {
            zzalwVar.zzdmg = this.zzdmg;
        }
        if (!TextUtils.isEmpty(this.zzdmh)) {
            zzalwVar.zzdmh = this.zzdmh;
        }
        if (!TextUtils.isEmpty(this.zzdmi)) {
            zzalwVar.zzdmi = this.zzdmi;
        }
        if (TextUtils.isEmpty(this.zzdmj)) {
            return;
        }
        zzalwVar.zzdmj = this.zzdmj;
    }

    public final void zzcy(String str) {
        this.zzdmd = str;
    }

    public final void zzcz(String str) {
        this.zzdme = str;
    }

    public final void zzda(String str) {
        this.zzdmf = str;
    }

    public final void zzdb(String str) {
        this.zzbqr = str;
    }

    public final void zzdc(String str) {
        this.zzbsw = str;
    }

    public final void zzdd(String str) {
        this.zzdmg = str;
    }

    public final void zzde(String str) {
        this.zzdmh = str;
    }

    public final void zzdf(String str) {
        this.zzdmi = str;
    }

    public final void zzdg(String str) {
        this.zzdmj = str;
    }

    public final String zzuq() {
        return this.zzdme;
    }

    public final String zzur() {
        return this.zzdmf;
    }

    public final String zzus() {
        return this.zzdmg;
    }

    public final String zzut() {
        return this.zzdmh;
    }

    public final String zzuu() {
        return this.zzdmi;
    }

    public final String zzuv() {
        return this.zzdmj;
    }
}
