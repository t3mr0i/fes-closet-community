package com.google.android.gms.internal;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzccy implements Callable<String> {
    private /* synthetic */ String zziag;
    private /* synthetic */ zzccw zziuc;

    zzccy(zzccw zzccwVar, String str) {
        this.zziuc = zzccwVar;
        this.zziag = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        zzcar zzcarVarZziw = this.zziuc.zzaug().zziw(this.zziag);
        if (zzcarVarZziw != null) {
            return zzcarVarZziw.getAppInstanceId();
        }
        this.zziuc.zzaum().zzayg().log("App info was null when attempting to get app instance id");
        return null;
    }
}
