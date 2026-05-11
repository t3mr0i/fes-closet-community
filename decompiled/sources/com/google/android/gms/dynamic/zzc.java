package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

/* loaded from: classes.dex */
final class zzc implements zzi {
    private /* synthetic */ Activity val$activity;
    private /* synthetic */ Bundle zzaxb;
    private /* synthetic */ zza zzgox;
    private /* synthetic */ Bundle zzgoy;

    zzc(zza zzaVar, Activity activity, Bundle bundle, Bundle bundle2) {
        this.zzgox = zzaVar;
        this.val$activity = activity;
        this.zzgoy = bundle;
        this.zzaxb = bundle2;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final int getState() {
        return 0;
    }

    @Override // com.google.android.gms.dynamic.zzi
    public final void zzb(LifecycleDelegate lifecycleDelegate) {
        this.zzgox.zzgot.onInflate(this.val$activity, this.zzgoy, this.zzaxb);
    }
}
