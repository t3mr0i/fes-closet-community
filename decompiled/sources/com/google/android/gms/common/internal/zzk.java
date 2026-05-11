package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/* loaded from: classes.dex */
public final class zzk extends zzaw {
    private zzd zzftm;
    private final int zzftn;

    public zzk(@NonNull zzd zzdVar, int i) {
        this.zzftm = zzdVar;
        this.zzftn = i;
    }

    @Override // com.google.android.gms.common.internal.zzav
    @BinderThread
    public final void zza(int i, @Nullable Bundle bundle) {
        Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }

    @Override // com.google.android.gms.common.internal.zzav
    @BinderThread
    public final void zza(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
        zzbp.zzb(this.zzftm, "onPostInitComplete can be called only once per call to getRemoteService");
        this.zzftm.zza(i, iBinder, bundle, this.zzftn);
        this.zzftm = null;
    }
}
