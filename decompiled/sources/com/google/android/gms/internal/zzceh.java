package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
final class zzceh implements Callable<String> {
    private /* synthetic */ zzcdw zziux;

    zzceh(zzcdw zzcdwVar) {
        this.zziux = zzcdwVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        String strZzayo = this.zziux.zzaun().zzayo();
        if (strZzayo == null) {
            zzcdw zzcdwVarZzaua = this.zziux.zzaua();
            if (zzcdwVarZzaua.zzaul().zzayt()) {
                zzcdwVarZzaua.zzaum().zzaye().log("Cannot retrieve app instance id from analytics worker thread");
                strZzayo = null;
            } else {
                zzcdwVarZzaua.zzaul();
                if (zzccr.zzaq()) {
                    zzcdwVarZzaua.zzaum().zzaye().log("Cannot retrieve app instance id from main thread");
                    strZzayo = null;
                } else {
                    long jElapsedRealtime = zzcdwVarZzaua.zzvx().elapsedRealtime();
                    strZzayo = zzcdwVarZzaua.zzbc(120000L);
                    long jElapsedRealtime2 = zzcdwVarZzaua.zzvx().elapsedRealtime() - jElapsedRealtime;
                    if (strZzayo == null && jElapsedRealtime2 < 120000) {
                        strZzayo = zzcdwVarZzaua.zzbc(120000 - jElapsedRealtime2);
                    }
                }
            }
            if (strZzayo == null) {
                throw new TimeoutException();
            }
            this.zziux.zzaun().zzjk(strZzayo);
        }
        return strZzayo;
    }
}
