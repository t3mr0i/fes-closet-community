package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class zzama extends com.google.android.gms.analytics.zzh<zzama> {
    public int zzcet;
    public int zzceu;
    private String zzdmm;
    public int zzdmn;
    public int zzdmo;
    public int zzdmp;

    public final String getLanguage() {
        return this.zzdmm;
    }

    public final void setLanguage(String str) {
        this.zzdmm = str;
    }

    public final String toString() {
        HashMap map = new HashMap();
        map.put("language", this.zzdmm);
        map.put("screenColors", Integer.valueOf(this.zzdmn));
        map.put("screenWidth", Integer.valueOf(this.zzcet));
        map.put("screenHeight", Integer.valueOf(this.zzceu));
        map.put("viewportWidth", Integer.valueOf(this.zzdmo));
        map.put("viewportHeight", Integer.valueOf(this.zzdmp));
        return zzh(map);
    }

    @Override // com.google.android.gms.analytics.zzh
    public final /* synthetic */ void zzb(com.google.android.gms.analytics.zzh zzhVar) {
        zzama zzamaVar = (zzama) zzhVar;
        if (this.zzdmn != 0) {
            zzamaVar.zzdmn = this.zzdmn;
        }
        if (this.zzcet != 0) {
            zzamaVar.zzcet = this.zzcet;
        }
        if (this.zzceu != 0) {
            zzamaVar.zzceu = this.zzceu;
        }
        if (this.zzdmo != 0) {
            zzamaVar.zzdmo = this.zzdmo;
        }
        if (this.zzdmp != 0) {
            zzamaVar.zzdmp = this.zzdmp;
        }
        if (TextUtils.isEmpty(this.zzdmm)) {
            return;
        }
        zzamaVar.zzdmm = this.zzdmm;
    }
}
