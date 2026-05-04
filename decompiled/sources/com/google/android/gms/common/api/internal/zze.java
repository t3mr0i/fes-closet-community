package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final class zze<TResult> extends zza {
    private final TaskCompletionSource<TResult> zzdzb;
    private final zzdd<Api.zzb, TResult> zzfie;
    private final zzcz zzfif;

    public zze(int i, zzdd<Api.zzb, TResult> zzddVar, TaskCompletionSource<TResult> taskCompletionSource, zzcz zzczVar) {
        super(i);
        this.zzdzb = taskCompletionSource;
        this.zzfie = zzddVar;
        this.zzfif = zzczVar;
    }

    @Override // com.google.android.gms.common.api.internal.zza
    public final void zza(@NonNull zzah zzahVar, boolean z) {
        zzahVar.zza(this.zzdzb, z);
    }

    @Override // com.google.android.gms.common.api.internal.zza
    public final void zza(zzbr<?> zzbrVar) throws DeadObjectException {
        try {
            this.zzfie.zza(zzbrVar.zzagn(), this.zzdzb);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            zzr(zza.zza(e2));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zza
    public final void zzr(@NonNull Status status) {
        this.zzdzb.trySetException(this.zzfif.zzs(status));
    }
}
