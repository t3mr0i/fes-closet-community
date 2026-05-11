package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import com.google.android.gms.internal.zzcpx;
import com.google.android.gms.internal.zzcqf;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class zzay extends zzcpx {
    private final WeakReference<zzar> zzflx;

    zzay(zzar zzarVar) {
        this.zzflx = new WeakReference<>(zzarVar);
    }

    @Override // com.google.android.gms.internal.zzcpx, com.google.android.gms.internal.zzcpy
    @BinderThread
    public final void zzb(zzcqf zzcqfVar) {
        zzar zzarVar = this.zzflx.get();
        if (zzarVar == null) {
            return;
        }
        zzarVar.zzflg.zza(new zzaz(this, zzarVar, zzarVar, zzcqfVar));
    }
}
