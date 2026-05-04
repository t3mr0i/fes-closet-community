package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class zzat implements com.google.android.gms.common.internal.zzj {
    private final Api<?> zzfdf;
    private final boolean zzfjr;
    private final WeakReference<zzar> zzflx;

    public zzat(zzar zzarVar, Api<?> api, boolean z) {
        this.zzflx = new WeakReference<>(zzarVar);
        this.zzfdf = api;
        this.zzfjr = z;
    }

    @Override // com.google.android.gms.common.internal.zzj
    public final void zzf(@NonNull ConnectionResult connectionResult) {
        zzar zzarVar = this.zzflx.get();
        if (zzarVar == null) {
            return;
        }
        com.google.android.gms.common.internal.zzbp.zza(Looper.myLooper() == zzarVar.zzflg.zzfjt.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zzarVar.zzfkd.lock();
        try {
            if (zzarVar.zzbr(0)) {
                if (!connectionResult.isSuccess()) {
                    zzarVar.zzb(connectionResult, this.zzfdf, this.zzfjr);
                }
                if (zzarVar.zzaha()) {
                    zzarVar.zzahb();
                }
            }
        } finally {
            zzarVar.zzfkd.unlock();
        }
    }
}
