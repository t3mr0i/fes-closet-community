package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
abstract class zze extends zzi<Boolean> {
    private int statusCode;
    private Bundle zzftj;
    private /* synthetic */ zzd zzftk;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @BinderThread
    protected zze(zzd zzdVar, int i, Bundle bundle) {
        super(zzdVar, true);
        this.zzftk = zzdVar;
        this.statusCode = i;
        this.zzftj = bundle;
    }

    protected abstract boolean zzajo();

    protected abstract void zzj(ConnectionResult connectionResult);

    @Override // com.google.android.gms.common.internal.zzi
    protected final /* synthetic */ void zzs(Boolean bool) {
        if (bool == null) {
            this.zzftk.zza(1, (int) null);
            return;
        }
        switch (this.statusCode) {
            case 0:
                if (zzajo()) {
                    return;
                }
                this.zzftk.zza(1, (int) null);
                zzj(new ConnectionResult(8, null));
                return;
            case 10:
                this.zzftk.zza(1, (int) null);
                throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
            default:
                this.zzftk.zza(1, (int) null);
                zzj(new ConnectionResult(this.statusCode, this.zzftj != null ? (PendingIntent) this.zzftj.getParcelable("pendingIntent") : null));
                return;
        }
    }
}
