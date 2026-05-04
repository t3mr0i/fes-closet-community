package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class zzao implements zzbk {
    private final zzbl zzflg;
    private boolean zzflh = false;

    public zzao(zzbl zzblVar) {
        this.zzflg = zzblVar;
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void begin() {
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void connect() {
        if (this.zzflh) {
            this.zzflh = false;
            this.zzflg.zza(new zzaq(this, this));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final boolean disconnect() {
        if (this.zzflh) {
            return false;
        }
        if (!this.zzflg.zzfjt.zzahj()) {
            this.zzflg.zzg(null);
            return true;
        }
        this.zzflh = true;
        Iterator<zzdg> it = this.zzflg.zzfjt.zzfmr.iterator();
        while (it.hasNext()) {
            it.next().zzaip();
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void onConnected(Bundle bundle) {
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void onConnectionSuspended(int i) {
        this.zzflg.zzg(null);
        this.zzflg.zzfnf.zzf(i, this.zzflh);
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    final void zzagy() {
        if (this.zzflh) {
            this.zzflh = false;
            this.zzflg.zzfjt.zzfms.release();
            disconnect();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        return (T) zze(t);
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        try {
            this.zzflg.zzfjt.zzfms.zzb(t);
            zzbd zzbdVar = this.zzflg.zzfjt;
            Api.zze zzeVar = zzbdVar.zzfmm.get(t.zzafe());
            com.google.android.gms.common.internal.zzbp.zzb(zzeVar, "Appropriate Api was not requested.");
            if (zzeVar.isConnected() || !this.zzflg.zzfnb.containsKey(t.zzafe())) {
                boolean z = zzeVar instanceof com.google.android.gms.common.internal.zzby;
                A aZzakp = zzeVar;
                if (z) {
                    aZzakp = com.google.android.gms.common.internal.zzby.zzakp();
                }
                t.zzb(aZzakp);
            } else {
                t.zzt(new Status(17));
            }
        } catch (DeadObjectException e) {
            this.zzflg.zza(new zzap(this, this));
        }
        return t;
    }
}
