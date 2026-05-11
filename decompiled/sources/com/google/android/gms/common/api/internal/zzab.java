package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
final class zzab implements zzce {
    private /* synthetic */ zzy zzfkf;

    private zzab(zzy zzyVar) {
        this.zzfkf = zzyVar;
    }

    /* synthetic */ zzab(zzy zzyVar, zzz zzzVar) {
        this(zzyVar);
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzc(@NonNull ConnectionResult connectionResult) {
        this.zzfkf.zzfkd.lock();
        try {
            this.zzfkf.zzfkb = connectionResult;
            this.zzfkf.zzagj();
        } finally {
            this.zzfkf.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzf(int i, boolean z) {
        this.zzfkf.zzfkd.lock();
        try {
            if (this.zzfkf.zzfkc) {
                this.zzfkf.zzfkc = false;
                this.zzfkf.zze(i, z);
            } else {
                this.zzfkf.zzfkc = true;
                this.zzfkf.zzfju.onConnectionSuspended(i);
            }
        } finally {
            this.zzfkf.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzj(@Nullable Bundle bundle) {
        this.zzfkf.zzfkd.lock();
        try {
            this.zzfkf.zzfkb = ConnectionResult.zzffe;
            this.zzfkf.zzagj();
        } finally {
            this.zzfkf.zzfkd.unlock();
        }
    }
}
