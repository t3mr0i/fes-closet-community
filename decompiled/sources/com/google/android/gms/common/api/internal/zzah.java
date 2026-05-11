package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class zzah {
    private final Map<zzs<?>, Boolean> zzfkz = Collections.synchronizedMap(new WeakHashMap());
    private final Map<TaskCompletionSource<?>, Boolean> zzfla = Collections.synchronizedMap(new WeakHashMap());

    private final void zza(boolean z, Status status) {
        HashMap map;
        HashMap map2;
        synchronized (this.zzfkz) {
            map = new HashMap(this.zzfkz);
        }
        synchronized (this.zzfla) {
            map2 = new HashMap(this.zzfla);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((zzs) entry.getKey()).zzu(status);
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }

    final void zza(zzs<? extends Result> zzsVar, boolean z) {
        this.zzfkz.put(zzsVar, Boolean.valueOf(z));
        zzsVar.zza(new zzai(this, zzsVar));
    }

    final <TResult> void zza(TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        this.zzfla.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new zzaj(this, taskCompletionSource));
    }

    final boolean zzags() {
        return (this.zzfkz.isEmpty() && this.zzfla.isEmpty()) ? false : true;
    }

    public final void zzagt() {
        zza(false, zzbp.zzfnj);
    }

    public final void zzagu() {
        zza(true, zzdj.zzfpp);
    }
}
