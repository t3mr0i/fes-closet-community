package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzann implements Callable<String> {
    private /* synthetic */ zzanm zzdqh;

    zzann(zzanm zzanmVar) {
        this.zzdqh = zzanmVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        return this.zzdqh.zzxr();
    }
}
