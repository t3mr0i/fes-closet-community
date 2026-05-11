package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public abstract class zzcr<A extends Api.zzb, L> {
    private final zzcj<L> zzfpb;

    protected zzcr(zzcj<L> zzcjVar) {
        this.zzfpb = zzcjVar;
    }

    public final zzcl<L> zzail() {
        return this.zzfpb.zzail();
    }

    public final void zzaim() {
        this.zzfpb.clear();
    }

    protected abstract void zzb(A a, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException;
}
