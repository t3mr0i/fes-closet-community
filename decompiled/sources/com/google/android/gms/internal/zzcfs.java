package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcfs extends zzcbc {
    private /* synthetic */ zzcfr zziwx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcfs(zzcfr zzcfrVar, zzccw zzccwVar) {
        super(zzccwVar);
        this.zziwx = zzcfrVar;
    }

    @Override // com.google.android.gms.internal.zzcbc
    public final void run() {
        this.zziwx.zzaum().zzayk().log("Sending upload intent from DelayedRunnable");
        this.zziwx.zzazx();
    }
}
