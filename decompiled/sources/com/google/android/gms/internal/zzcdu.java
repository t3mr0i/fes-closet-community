package com.google.android.gms.internal;

/* loaded from: classes.dex */
abstract class zzcdu extends zzcdt {
    private boolean zzdod;

    zzcdu(zzccw zzccwVar) {
        super(zzccwVar);
        this.zzikh.zzb(this);
    }

    public final void initialize() {
        if (this.zzdod) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzuk();
        this.zzikh.zzazk();
        this.zzdod = true;
    }

    final boolean isInitialized() {
        return this.zzdod;
    }

    protected abstract void zzuk();

    protected final void zzwk() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
