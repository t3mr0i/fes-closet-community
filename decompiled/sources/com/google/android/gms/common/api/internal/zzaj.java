package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Add missing generic type declarations: [TResult] */
/* loaded from: classes.dex */
final class zzaj<TResult> implements OnCompleteListener<TResult> {
    private /* synthetic */ TaskCompletionSource zzeji;
    private /* synthetic */ zzah zzflc;

    zzaj(zzah zzahVar, TaskCompletionSource taskCompletionSource) {
        this.zzflc = zzahVar;
        this.zzeji = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<TResult> task) {
        this.zzflc.zzfla.remove(this.zzeji);
    }
}
