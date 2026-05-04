package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
final class zzbch extends Drawable.ConstantState {
    int mChangingConfigurations;
    int zzfsm;

    zzbch(zzbch zzbchVar) {
        if (zzbchVar != null) {
            this.mChangingConfigurations = zzbchVar.mChangingConfigurations;
            this.zzfsm = zzbchVar.zzfsm;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new zzbcd(this);
    }
}
