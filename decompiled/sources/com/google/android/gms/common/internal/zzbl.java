package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;

/* JADX INFO: Add missing generic type declarations: [R, T] */
/* loaded from: classes.dex */
final class zzbl<R, T> implements zzbn<R, T> {
    private /* synthetic */ Response zzfvv;

    zzbl(Response response) {
        this.zzfvv = response;
    }

    @Override // com.google.android.gms.common.internal.zzbn
    public final /* synthetic */ Object zzb(Result result) {
        this.zzfvv.setResult(result);
        return this.zzfvv;
    }
}
