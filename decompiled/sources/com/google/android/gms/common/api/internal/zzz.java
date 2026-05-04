package com.google.android.gms.common.api.internal;

/* loaded from: classes.dex */
final class zzz implements Runnable {
    private /* synthetic */ zzy zzfkf;

    zzz(zzy zzyVar) {
        this.zzfkf = zzyVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzfkf.zzfkd.lock();
        try {
            this.zzfkf.zzagj();
        } finally {
            this.zzfkf.zzfkd.unlock();
        }
    }
}
