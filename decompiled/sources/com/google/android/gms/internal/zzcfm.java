package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.WorkerThread;

/* loaded from: classes.dex */
final class zzcfm extends zzcbc {
    private /* synthetic */ zzcfl zziwv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcfm(zzcfl zzcflVar, zzccw zzccwVar) {
        super(zzccwVar);
        this.zziwv = zzcflVar;
    }

    @Override // com.google.android.gms.internal.zzcbc
    @WorkerThread
    public final void run() {
        zzcfl zzcflVar = this.zziwv;
        zzcflVar.zzuj();
        zzcflVar.zzaum().zzayk().zzj("Session started, time", Long.valueOf(zzcflVar.zzvx().elapsedRealtime()));
        zzcflVar.zzaun().zzird.set(false);
        zzcflVar.zzaua().zzc("auto", "_s", new Bundle());
        zzcflVar.zzaun().zzire.set(zzcflVar.zzvx().currentTimeMillis());
    }
}
