package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzed implements zzfx {
    private /* synthetic */ zzec zzjtd;

    zzed(zzec zzecVar) {
        this.zzjtd = zzecVar;
    }

    @Override // com.google.android.gms.tagmanager.zzfx
    public final void zza(zzbx zzbxVar) {
        this.zzjtd.zzp(zzbxVar.zzbds());
    }

    @Override // com.google.android.gms.tagmanager.zzfx
    public final void zzb(zzbx zzbxVar) {
        this.zzjtd.zzp(zzbxVar.zzbds());
        zzdj.v(new StringBuilder(57).append("Permanent failure dispatching hitId: ").append(zzbxVar.zzbds()).toString());
    }

    @Override // com.google.android.gms.tagmanager.zzfx
    public final void zzc(zzbx zzbxVar) {
        long jZzbdt = zzbxVar.zzbdt();
        if (jZzbdt == 0) {
            this.zzjtd.zzh(zzbxVar.zzbds(), this.zzjtd.zzasb.currentTimeMillis());
        } else if (jZzbdt + 14400000 < this.zzjtd.zzasb.currentTimeMillis()) {
            this.zzjtd.zzp(zzbxVar.zzbds());
            zzdj.v(new StringBuilder(47).append("Giving up on failed hitId: ").append(zzbxVar.zzbds()).toString());
        }
    }
}
