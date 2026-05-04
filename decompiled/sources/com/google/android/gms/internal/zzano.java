package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzano implements Callable<String> {
    private /* synthetic */ zzanm zzdqh;

    zzano(zzanm zzanmVar) {
        this.zzdqh = zzanmVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        return this.zzdqh.zzxs();
    }
}
