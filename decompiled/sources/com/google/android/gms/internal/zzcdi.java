package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzcdi implements Callable<List<zzcfv>> {
    private /* synthetic */ String zziag;
    private /* synthetic */ zzcdb zziuj;
    private /* synthetic */ String zziul;
    private /* synthetic */ String zzium;

    zzcdi(zzcdb zzcdbVar, String str, String str2, String str3) {
        this.zziuj = zzcdbVar;
        this.zziag = str;
        this.zziul = str2;
        this.zzium = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzcfv> call() throws Exception {
        this.zziuj.zzikh.zzazl();
        return this.zziuj.zzikh.zzaug().zzg(this.zziag, this.zziul, this.zzium);
    }
}
