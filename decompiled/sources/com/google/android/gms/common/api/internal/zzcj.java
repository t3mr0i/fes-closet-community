package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;

/* loaded from: classes.dex */
public final class zzcj<L> {
    private volatile L mListener;
    private final zzck zzfot;
    private final zzcl<L> zzfou;

    zzcj(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        this.zzfot = new zzck(this, looper);
        this.mListener = (L) com.google.android.gms.common.internal.zzbp.zzb(l, "Listener must not be null");
        this.zzfou = new zzcl<>(l, com.google.android.gms.common.internal.zzbp.zzgg(str));
    }

    public final void clear() {
        this.mListener = null;
    }

    public final void zza(zzcm<? super L> zzcmVar) {
        com.google.android.gms.common.internal.zzbp.zzb(zzcmVar, "Notifier must not be null");
        this.zzfot.sendMessage(this.zzfot.obtainMessage(1, zzcmVar));
    }

    @NonNull
    public final zzcl<L> zzail() {
        return this.zzfou;
    }

    final void zzb(zzcm<? super L> zzcmVar) {
        L l = this.mListener;
        if (l == null) {
            zzcmVar.zzagx();
            return;
        }
        try {
            zzcmVar.zzq(l);
        } catch (RuntimeException e) {
            zzcmVar.zzagx();
            throw e;
        }
    }
}
