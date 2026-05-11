package com.google.android.gms.internal;

/* loaded from: classes.dex */
public abstract class zzams extends zzamr {
    private boolean zzdod;

    protected zzams(zzamu zzamuVar) {
        super(zzamuVar);
    }

    public final void initialize() {
        zzuk();
        this.zzdod = true;
    }

    public final boolean isInitialized() {
        return this.zzdod;
    }

    protected abstract void zzuk();

    protected final void zzwk() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
