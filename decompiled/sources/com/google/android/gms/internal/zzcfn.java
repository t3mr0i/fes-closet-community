package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;

/* loaded from: classes.dex */
final class zzcfn extends zzcbc {
    private /* synthetic */ zzcfl zziwv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcfn(zzcfl zzcflVar, zzccw zzccwVar) {
        super(zzccwVar);
        this.zziwv = zzcflVar;
    }

    @Override // com.google.android.gms.internal.zzcbc
    @WorkerThread
    public final void run() {
        this.zziwv.zzazv();
    }
}
