package com.google.android.gms.dynamic;

/* loaded from: classes.dex */
final class zzg implements zzi {
    private /* synthetic */ zza zzgox;

    zzg(zza zzaVar) {
        this.zzgox = zzaVar;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final int getState() {
        return 4;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final void zzb(LifecycleDelegate lifecycleDelegate) {
        this.zzgox.zzgot.onStart();
    }
}
