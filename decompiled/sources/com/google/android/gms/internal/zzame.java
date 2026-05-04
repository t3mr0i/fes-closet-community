package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzame extends com.google.android.gms.analytics.zzh<zzame> {
    private String zzdmv;
    private String zzdmw;
    private String zzdmx;
    private String zzdmy;
    private boolean zzdmz;
    private String zzdna;
    private boolean zzdnb;
    private double zzdnc;

    public final String getUserId() {
        return this.zzdmx;
    }

    public final void setClientId(String str) {
        this.zzdmw = str;
    }

    public final void setUserId(String str) {
        this.zzdmx = str;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("hitType", this.zzdmv);
        map.put("clientId", this.zzdmw);
        map.put("userId", this.zzdmx);
        map.put("androidAdId", this.zzdmy);
        map.put("AdTargetingEnabled", Boolean.valueOf(this.zzdmz));
        map.put("sessionControl", this.zzdna);
        map.put("nonInteraction", Boolean.valueOf(this.zzdnb));
        map.put("sampleRate", Double.valueOf(this.zzdnc));
        return zzh(map);
    }

    public final void zzah(boolean z) {
        this.zzdmz = z;
    }

    public final void zzai(boolean z) {
        this.zzdnb = true;
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzame zzameVar = (zzame) zzhVar;
        if (!TextUtils.isEmpty(this.zzdmv)) {
            zzameVar.zzdmv = this.zzdmv;
        }
        if (!TextUtils.isEmpty(this.zzdmw)) {
            zzameVar.zzdmw = this.zzdmw;
        }
        if (!TextUtils.isEmpty(this.zzdmx)) {
            zzameVar.zzdmx = this.zzdmx;
        }
        if (!TextUtils.isEmpty(this.zzdmy)) {
            zzameVar.zzdmy = this.zzdmy;
        }
        if (this.zzdmz) {
            zzameVar.zzdmz = true;
        }
        if (!TextUtils.isEmpty(this.zzdna)) {
            zzameVar.zzdna = this.zzdna;
        }
        if (this.zzdnb) {
            zzameVar.zzdnb = this.zzdnb;
        }
        if (this.zzdnc != 0.0d) {
            double d = this.zzdnc;
            com.google.android.gms.common.internal.zzbp.zzb(d >= 0.0d && d <= 100.0d, "Sample rate must be between 0% and 100%");
            zzameVar.zzdnc = d;
        }
    }

    public final void zzdh(String str) {
        this.zzdmv = str;
    }

    public final void zzdi(String str) {
        this.zzdmy = str;
    }

    public final String zzvd() {
        return this.zzdmv;
    }

    public final String zzve() {
        return this.zzdmw;
    }

    public final String zzvf() {
        return this.zzdmy;
    }

    public final boolean zzvg() {
        return this.zzdmz;
    }

    public final String zzvh() {
        return this.zzdna;
    }

    public final boolean zzvi() {
        return this.zzdnb;
    }

    public final double zzvj() {
        return this.zzdnc;
    }
}
