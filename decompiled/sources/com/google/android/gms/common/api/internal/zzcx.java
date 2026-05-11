package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzcqf;

/* loaded from: classes.dex */
final class zzcx implements Runnable {
    private /* synthetic */ zzcqf zzfme;
    private /* synthetic */ zzcw zzfpe;

    zzcx(zzcw zzcwVar, zzcqf zzcqfVar) {
        this.zzfpe = zzcwVar;
        this.zzfme = zzcqfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzfpe.zzc(this.zzfme);
    }
}
