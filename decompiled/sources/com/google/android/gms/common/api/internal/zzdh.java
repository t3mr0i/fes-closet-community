package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

/* loaded from: classes.dex */
final class zzdh implements Runnable {
    private /* synthetic */ Result zzfpn;
    private /* synthetic */ zzdg zzfpo;

    zzdh(zzdg zzdgVar, Result result) {
        this.zzfpo = zzdgVar;
        this.zzfpn = result;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        try {
            try {
                zzs.zzfjd.set(true);
                this.zzfpo.zzfpl.sendMessage(this.zzfpo.zzfpl.obtainMessage(0, this.zzfpo.zzfpg.onSuccess(this.zzfpn)));
                zzs.zzfjd.set(false);
                zzdg zzdgVar = this.zzfpo;
                zzdg.zzd(this.zzfpn);
                GoogleApiClient googleApiClient = (GoogleApiClient) this.zzfpo.zzfjg.get();
                if (googleApiClient != null) {
                    googleApiClient.zzb(this.zzfpo);
                }
            } catch (RuntimeException e) {
                this.zzfpo.zzfpl.sendMessage(this.zzfpo.zzfpl.obtainMessage(1, e));
                zzs.zzfjd.set(false);
                zzdg zzdgVar2 = this.zzfpo;
                zzdg.zzd(this.zzfpn);
                GoogleApiClient googleApiClient2 = (GoogleApiClient) this.zzfpo.zzfjg.get();
                if (googleApiClient2 != null) {
                    googleApiClient2.zzb(this.zzfpo);
                }
            }
        } finally {
        }
    }
}
