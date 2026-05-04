package com.google.android.gms.dynamic;

import android.os.Bundle;
import java.util.Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
final class zzb<T> implements zzo<T> {
    private /* synthetic */ zza zzgox;

    zzb(zza zzaVar) {
        this.zzgox = zzaVar;
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)V */
    @Override // com.google.android.gms.dynamic.zzo
    public final void zza(LifecycleDelegate lifecycleDelegate) {
        this.zzgox.zzgot = lifecycleDelegate;
        Iterator it = this.zzgox.zzgov.iterator();
        while (it.hasNext()) {
            ((zzi) it.next()).zzb(this.zzgox.zzgot);
        }
        this.zzgox.zzgov.clear();
        zza.zza(this.zzgox, (Bundle) null);
    }
}
