package com.google.android.gms.dynamic;

/* loaded from: classes.dex */
final class zzh implements zzi {
    private /* synthetic */ zza zzgox;

    zzh(zza zzaVar) {
        this.zzgox = zzaVar;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final int getState() {
        return 5;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final void zzb(LifecycleDelegate lifecycleDelegate) {
        this.zzgox.zzgot.onResume();
    }
}
