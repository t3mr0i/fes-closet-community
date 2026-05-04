package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
final class zzaa implements zzce {
    private /* synthetic */ zzy zzfkf;

    private zzaa(zzy zzyVar) {
        this.zzfkf = zzyVar;
    }

    /* synthetic */ zzaa(zzy zzyVar, zzz zzzVar) {
        this(zzyVar);
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzc(@NonNull ConnectionResult connectionResult) {
        this.zzfkf.zzfkd.lock();
        try {
            this.zzfkf.zzfka = connectionResult;
            this.zzfkf.zzagj();
        } finally {
            this.zzfkf.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzf(int i, boolean z) {
        this.zzfkf.zzfkd.lock();
        try {
            if (this.zzfkf.zzfkc || this.zzfkf.zzfkb == null || !this.zzfkf.zzfkb.isSuccess()) {
                this.zzfkf.zzfkc = false;
                this.zzfkf.zze(i, z);
            } else {
                this.zzfkf.zzfkc = true;
                this.zzfkf.zzfjv.onConnectionSuspended(i);
            }
        } finally {
            this.zzfkf.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzj(@Nullable Bundle bundle) {
        this.zzfkf.zzfkd.lock();
        try {
            this.zzfkf.zzi(bundle);
            this.zzfkf.zzfka = ConnectionResult.zzffe;
            this.zzfkf.zzagj();
        } finally {
            this.zzfkf.zzfkd.unlock();
        }
    }
}
