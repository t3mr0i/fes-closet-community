package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzau extends zzbb {
    final /* synthetic */ zzar zzflw;
    private final Map<Api.zze, zzat> zzfly;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzau(zzar zzarVar, Map<Api.zze, zzat> map) {
        super(zzarVar, null);
        this.zzflw = zzarVar;
        this.zzfly = map;
    }

    @Override // com.google.android.gms.common.api.internal.zzbb
    @WorkerThread
    public final void zzagz() {
        boolean z;
        boolean z2;
        Iterator<Api.zze> it = this.zzfly.keySet().iterator();
        boolean z3 = true;
        boolean z4 = false;
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            Api.zze next = it.next();
            if (!next.zzaff()) {
                z2 = false;
            } else if (!this.zzfly.get(next).zzfjr) {
                z4 = true;
                z = true;
                break;
            } else {
                z2 = z3;
                z4 = true;
            }
            z3 = z2;
        }
        int iIsGooglePlayServicesAvailable = z4 ? this.zzflw.zzfkn.isGooglePlayServicesAvailable(this.zzflw.mContext) : 0;
        if (iIsGooglePlayServicesAvailable != 0 && (z || z3)) {
            this.zzflw.zzflg.zza(new zzav(this, this.zzflw, new ConnectionResult(iIsGooglePlayServicesAvailable, null)));
            return;
        }
        if (this.zzflw.zzflq) {
            this.zzflw.zzflo.connect();
        }
        for (Api.zze zzeVar : this.zzfly.keySet()) {
            zzat zzatVar = this.zzfly.get(zzeVar);
            if (!zzeVar.zzaff() || iIsGooglePlayServicesAvailable == 0) {
                zzeVar.zza(zzatVar);
            } else {
                this.zzflw.zzflg.zza(new zzaw(this, this.zzflw, zzatVar));
            }
        }
    }
}
