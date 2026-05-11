package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public abstract class zzdd<A extends Api.zzb, TResult> {
    protected abstract void zza(A a, TaskCompletionSource<TResult> taskCompletionSource) throws RemoteException;
}
