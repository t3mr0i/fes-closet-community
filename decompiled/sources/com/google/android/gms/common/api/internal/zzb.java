package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
abstract class zzb<T> extends zza {
    protected final TaskCompletionSource<T> zzdzb;

    public zzb(int i, TaskCompletionSource<T> taskCompletionSource) {
        super(i);
        this.zzdzb = taskCompletionSource;
    }

    @Override // com.google.android.gms.common.api.internal.zza
    public void zza(@NonNull zzah zzahVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zza
    public final void zza(zzbr<?> zzbrVar) throws DeadObjectException {
        try {
            zzb(zzbrVar);
        } catch (DeadObjectException e) {
            zzr(zza.zza(e));
            throw e;
        } catch (RemoteException e2) {
            zzr(zza.zza(e2));
        }
    }

    protected abstract void zzb(zzbr<?> zzbrVar) throws RemoteException;

    @Override // com.google.android.gms.common.api.internal.zza
    public void zzr(@NonNull Status status) {
        this.zzdzb.trySetException(new ApiException(status));
    }
}
