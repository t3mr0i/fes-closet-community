package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzi implements Executor {
    private /* synthetic */ Handler zzs;

    zzi(zzh zzhVar, Handler handler) {
        this.zzs = handler;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.zzs.post(runnable);
    }
}
