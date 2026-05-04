package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public abstract class zzdn<A extends Api.zzb, L> {
    private final zzcl<L> zzfou;

    protected zzdn(zzcl<L> zzclVar) {
        this.zzfou = zzclVar;
    }

    public final zzcl<L> zzail() {
        return this.zzfou;
    }

    protected abstract void zzc(A a, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException;
}
