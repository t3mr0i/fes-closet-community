package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzcet extends zzcbc {
    private /* synthetic */ zzceo zziwe;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcet(zzceo zzceoVar, zzccw zzccwVar) {
        super(zzccwVar);
        this.zziwe = zzceoVar;
    }

    @Override // com.google.android.gms.internal.zzcbc
    public final void run() {
        this.zziwe.zzaum().zzayg().log("Tasks have been queued for a long time");
    }
}
