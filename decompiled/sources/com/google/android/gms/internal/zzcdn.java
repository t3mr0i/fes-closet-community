package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzcdn implements Callable<byte[]> {
    private /* synthetic */ String zziag;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ zzcbk zziun;

    zzcdn(zzcdb zzcdbVar, zzcbk zzcbkVar, String str) {
        this.zziuj = zzcdbVar;
        this.zziun = zzcbkVar;
        this.zziag = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() throws Exception {
        this.zziuj.zzikh.zzazl();
        return this.zziuj.zzikh.zza(this.zziun, this.zziag);
    }
}
