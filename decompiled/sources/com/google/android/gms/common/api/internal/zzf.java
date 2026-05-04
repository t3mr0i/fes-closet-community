package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final class zzf extends zzb<Boolean> {
    private zzcl<?> zzfig;

    public zzf(zzcl<?> zzclVar, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zzfig = zzclVar;
    }

    @Override // com.google.android.gms.common.api.internal.zzb, com.google.android.gms.common.api.internal.zza
    public final /* bridge */ /* synthetic */ void zza(@NonNull zzah zzahVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zzb
    public final void zzb(zzbr<?> zzbrVar) throws RemoteException {
        zzcs zzcsVarRemove = zzbrVar.zzahw().remove(this.zzfig);
        if (zzcsVarRemove == null) {
            this.zzdzb.trySetResult(false);
        } else {
            zzcsVarRemove.zzfid.zzc(zzbrVar.zzagn(), this.zzdzb);
            zzcsVarRemove.zzfic.zzaim();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzb, com.google.android.gms.common.api.internal.zza
    public final /* bridge */ /* synthetic */ void zzr(@NonNull Status status) {
        super.zzr(status);
    }
}
