package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final class zzd extends zzb<Void> {
    private zzcr<Api.zzb, ?> zzfic;
    private zzdn<Api.zzb, ?> zzfid;

    public zzd(zzcs zzcsVar, TaskCompletionSource<Void> taskCompletionSource) {
        super(3, taskCompletionSource);
        this.zzfic = zzcsVar.zzfic;
        this.zzfid = zzcsVar.zzfid;
    }

    @Override // com.google.android.gms.common.api.internal.zzb, com.google.android.gms.common.api.internal.zza
    public final /* bridge */ /* synthetic */ void zza(@NonNull zzah zzahVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zzb
    public final void zzb(zzbr<?> zzbrVar) throws RemoteException {
        this.zzfic.zzb(zzbrVar.zzagn(), this.zzdzb);
        if (this.zzfic.zzail() != null) {
            zzbrVar.zzahw().put(this.zzfic.zzail(), new zzcs(this.zzfic, this.zzfid));
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzb, com.google.android.gms.common.api.internal.zza
    public final /* bridge */ /* synthetic */ void zzr(@NonNull Status status) {
        super.zzr(status);
    }
}
