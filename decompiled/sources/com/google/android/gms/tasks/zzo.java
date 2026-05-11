package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzo implements Runnable {
    private /* synthetic */ Callable zzdbw;
    private /* synthetic */ zzn zzkgn;

    zzo(zzn zznVar, Callable callable) {
        this.zzkgn = zznVar;
        this.zzdbw = callable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzkgn.setResult(this.zzdbw.call());
        } catch (Exception e) {
            this.zzkgn.setException(e);
        }
    }
}
