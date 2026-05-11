package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
final class zzcdm implements Runnable {
    private /* synthetic */ String zziag;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ zzcbk zziun;

    zzcdm(zzcdb zzcdbVar, zzcbk zzcbkVar, String str) {
        this.zziuj = zzcdbVar;
        this.zziun = zzcbkVar;
        this.zziag = str;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalStateException, IOException {
        this.zziuj.zzikh.zzazl();
        this.zziuj.zzikh.zzb(this.zziun, this.zziag);
    }
}
