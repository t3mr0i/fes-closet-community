package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;

/* loaded from: classes.dex */
final class zzbw implements Runnable {
    private /* synthetic */ ConnectionResult zzfod;
    private /* synthetic */ zzbv zzfof;

    zzbw(zzbv zzbvVar, ConnectionResult connectionResult) {
        this.zzfof = zzbvVar;
        this.zzfod = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.zzfod.isSuccess()) {
            ((zzbr) this.zzfof.zzfnt.zzfkj.get(this.zzfof.zzfgr)).onConnectionFailed(this.zzfod);
            return;
        }
        zzbv.zza(this.zzfof, true);
        if (this.zzfof.zzfkg.zzaac()) {
            this.zzfof.zzaid();
        } else {
            this.zzfof.zzfkg.zza(null, Collections.emptySet());
        }
    }
}
