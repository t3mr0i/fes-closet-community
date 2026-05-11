package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzcdh implements Callable<List<zzcfv>> {
    private /* synthetic */ zzcas zziui;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ String zziul;
    private /* synthetic */ String zzium;

    zzcdh(zzcdb zzcdbVar, zzcas zzcasVar, String str, String str2) {
        this.zziuj = zzcdbVar;
        this.zziui = zzcasVar;
        this.zziul = str;
        this.zzium = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzcfv> call() throws Exception {
        this.zziuj.zzikh.zzazl();
        return this.zziuj.zzikh.zzaug().zzg(this.zziui.packageName, this.zziul, this.zzium);
    }
}
