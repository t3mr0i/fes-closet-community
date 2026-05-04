package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzamq implements Callable<Void> {
    private /* synthetic */ zzamj zzdnw;

    zzamq(zzamj zzamjVar) {
        this.zzdnw = zzamjVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() throws Exception {
        this.zzdnw.zzdnu.zzxj();
        return null;
    }
}
