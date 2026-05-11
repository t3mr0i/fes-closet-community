package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

/* loaded from: classes.dex */
abstract class zzbb implements Runnable {
    private /* synthetic */ zzar zzflw;

    private zzbb(zzar zzarVar) {
        this.zzflw = zzarVar;
    }

    /* synthetic */ zzbb(zzar zzarVar, zzas zzasVar) {
        this(zzarVar);
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public void run() {
        this.zzflw.zzfkd.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            zzagz();
        } catch (RuntimeException e) {
            this.zzflw.zzflg.zza(e);
        } finally {
            this.zzflw.zzfkd.unlock();
        }
    }

    @WorkerThread
    protected abstract void zzagz();
}
