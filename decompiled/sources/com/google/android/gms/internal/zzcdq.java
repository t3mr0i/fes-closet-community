package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzcdq implements Callable<List<zzcfv>> {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcdb zziuj;

    zzcdq(zzcdb zzcdbVar, zzcas zzcasVar) {
        this.zziuj = zzcdbVar;
        this.zziui = zzcasVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzcfv> call() throws Exception {
        this.zziuj.zzikh.zzazl();
        return this.zziuj.zzikh.zzaug().zziv(this.zziui.packageName);
    }
}
