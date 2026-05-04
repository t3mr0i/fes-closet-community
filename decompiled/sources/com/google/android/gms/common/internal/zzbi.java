package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final class zzbi {
    private static final zzbo zzfvq = new zzbj();

    public static <R extends Result, T extends Response<R>> Task<T> zza(PendingResult<R> pendingResult, T t) {
        return zza(pendingResult, new zzbl(t));
    }

    public static <R extends Result, T> Task<T> zza(PendingResult<R> pendingResult, zzbn<R, T> zzbnVar) {
        zzbo zzboVar = zzfvq;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.zza(new zzbk(pendingResult, taskCompletionSource, zzbnVar, zzboVar));
        return taskCompletionSource.getTask();
    }

    public static <R extends Result> Task<Void> zzb(PendingResult<R> pendingResult) {
        return zza(pendingResult, new zzbm());
    }
}
