package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzfi {
    private zzea<com.google.android.gms.internal.zzbp> zzjux;
    private com.google.android.gms.internal.zzbp zzjuy;

    public zzfi(zzea<com.google.android.gms.internal.zzbp> zzeaVar, com.google.android.gms.internal.zzbp zzbpVar) {
        this.zzjux = zzeaVar;
        this.zzjuy = zzbpVar;
    }

    public final int getSize() {
        return (this.zzjuy == null ? 0 : this.zzjuy.zzceo()) + this.zzjux.getObject().zzceo();
    }

    public final zzea<com.google.android.gms.internal.zzbp> zzber() {
        return this.zzjux;
    }

    public final com.google.android.gms.internal.zzbp zzbes() {
        return this.zzjuy;
    }
}
