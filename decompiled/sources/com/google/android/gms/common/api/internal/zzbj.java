package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class zzbj extends zzbz {
    private WeakReference<zzbd> zzfmy;

    zzbj(zzbd zzbdVar) {
        this.zzfmy = new WeakReference<>(zzbdVar);
    }

    @Override // com.google.android.gms.common.api.internal.zzbz
    public final void zzage() {
        zzbd zzbdVar = this.zzfmy.get();
        if (zzbdVar == null) {
            return;
        }
        zzbdVar.resume();
    }
}
