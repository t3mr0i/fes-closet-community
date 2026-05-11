package com.google.android.gms.internal;

import android.os.Looper;

/* loaded from: classes.dex */
final class zzany implements Runnable {
    private /* synthetic */ zzanx zzdqt;

    zzany(zzanx zzanxVar) {
        this.zzdqt = zzanxVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zzdqt.zzdoc.zzwa().zzc(this);
            return;
        }
        boolean zZzdp = this.zzdqt.zzdp();
        zzanx.zza(this.zzdqt, 0L);
        if (zZzdp) {
            this.zzdqt.run();
        }
    }
}
