package com.google.android.gms.analytics;

import java.util.Iterator;

/* loaded from: classes.dex */
final class zzk implements Runnable {
    private /* synthetic */ zzg zzdlc;
    private /* synthetic */ zzj zzdld;

    zzk(zzj zzjVar, zzg zzgVar) {
        this.zzdld = zzjVar;
        this.zzdlc = zzgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdlc.zzud().zza(this.zzdlc);
        Iterator it = this.zzdld.zzdkx.iterator();
        while (it.hasNext()) {
            it.next();
        }
        zzj zzjVar = this.zzdld;
        zzj.zzb(this.zzdlc);
    }
}
