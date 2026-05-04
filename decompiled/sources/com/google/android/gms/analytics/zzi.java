package com.google.android.gms.analytics;

import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzbp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class zzi<T extends zzi> {
    private final zzj zzdkt;
    protected final zzg zzdku;
    private final List<Object> zzdkv;

    protected zzi(zzj zzjVar, com.google.android.gms.common.util.zzd zzdVar) {
        zzbp.zzu(zzjVar);
        this.zzdkt = zzjVar;
        this.zzdkv = new ArrayList();
        zzg zzgVar = new zzg(this, zzdVar);
        zzgVar.zzuf();
        this.zzdku = zzgVar;
    }

    protected void zza(zzg zzgVar) {
    }

    protected final void zzd(zzg zzgVar) {
        Iterator<Object> it = this.zzdkv.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    public zzg zzts() {
        zzg zzgVarZztx = this.zzdku.zztx();
        zzd(zzgVarZztx);
        return zzgVarZztx;
    }

    protected final zzj zzug() {
        return this.zzdkt;
    }
}
