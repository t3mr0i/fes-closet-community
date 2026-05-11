package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
final class zzdl implements IBinder.DeathRecipient, zzdm {
    private final WeakReference<zzs<?>> zzfpu;
    private final WeakReference<com.google.android.gms.common.api.zze> zzfpv;
    private final WeakReference<IBinder> zzfpw;

    private zzdl(zzs<?> zzsVar, com.google.android.gms.common.api.zze zzeVar, IBinder iBinder) {
        this.zzfpv = new WeakReference<>(zzeVar);
        this.zzfpu = new WeakReference<>(zzsVar);
        this.zzfpw = new WeakReference<>(iBinder);
    }

    /* synthetic */ zzdl(zzs zzsVar, com.google.android.gms.common.api.zze zzeVar, IBinder iBinder, zzdk zzdkVar) {
        this(zzsVar, null, iBinder);
    }

    private final void zzais() {
        zzs<?> zzsVar = this.zzfpu.get();
        com.google.android.gms.common.api.zze zzeVar = this.zzfpv.get();
        if (zzeVar != null && zzsVar != null) {
            zzeVar.remove(zzsVar.zzafs().intValue());
        }
        IBinder iBinder = this.zzfpw.get();
        if (iBinder != null) {
            try {
                iBinder.unlinkToDeath(this, 0);
            } catch (NoSuchElementException e) {
            }
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        zzais();
    }

    @Override // com.google.android.gms.common.api.internal.zzdm
    public final void zzc(zzs<?> zzsVar) {
        zzais();
    }
}
