package com.google.android.gms.dynamic;

import android.os.Bundle;

/* loaded from: classes.dex */
final class zzd implements zzi {
    private /* synthetic */ Bundle zzaxb;
    private /* synthetic */ zza zzgox;

    zzd(zza zzaVar, Bundle bundle) {
        this.zzgox = zzaVar;
        this.zzaxb = bundle;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final int getState() {
        return 1;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final void zzb(LifecycleDelegate lifecycleDelegate) {
        this.zzgox.zzgot.onCreate(this.zzaxb);
    }
}
