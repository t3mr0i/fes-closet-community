package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzalv extends com.google.android.gms.analytics.zzh<zzalv> {
    private String mAppId;
    private String zzdma;
    private String zzdmb;
    private String zzdmc;

    public final String getAppId() {
        return this.mAppId;
    }

    public final void setAppId(String str) {
        this.mAppId = str;
    }

    public final void setAppInstallerId(String str) {
        this.zzdmc = str;
    }

    public final void setAppName(String str) {
        this.zzdma = str;
    }

    public final void setAppVersion(String str) {
        this.zzdmb = str;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("appName", this.zzdma);
        map.put("appVersion", this.zzdmb);
        map.put("appId", this.mAppId);
        map.put("appInstallerId", this.zzdmc);
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final void zzb(zzalv zzalvVar) {
        if (!TextUtils.isEmpty(this.zzdma)) {
            zzalvVar.zzdma = this.zzdma;
        }
        if (!TextUtils.isEmpty(this.zzdmb)) {
            zzalvVar.zzdmb = this.zzdmb;
        }
        if (!TextUtils.isEmpty(this.mAppId)) {
            zzalvVar.mAppId = this.mAppId;
        }
        if (TextUtils.isEmpty(this.zzdmc)) {
            return;
        }
        zzalvVar.zzdmc = this.zzdmc;
    }

    public final String zzun() {
        return this.zzdma;
    }

    public final String zzuo() {
        return this.zzdmb;
    }

    public final String zzup() {
        return this.zzdmc;
    }
}
