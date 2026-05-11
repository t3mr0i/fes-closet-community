package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

/* loaded from: classes.dex */
public final class zzc<A extends zzm<? extends Result, Api.zzb>> extends zza {
    private A zzfib;

    public zzc(int i, A a) {
        super(i);
        this.zzfib = a;
    }

    @Override // com.google.android.gms.common.api.internal.zza
    public final void zza(@NonNull zzah zzahVar, boolean z) {
        zzahVar.zza(this.zzfib, z);
    }

    @Override // com.google.android.gms.common.api.internal.zza
    public final void zza(zzbr<?> zzbrVar) throws DeadObjectException {
        this.zzfib.zzb(zzbrVar.zzagn());
    }

    @Override // com.google.android.gms.common.api.internal.zza
    public final void zzr(@NonNull Status status) {
        this.zzfib.zzt(status);
    }
}
