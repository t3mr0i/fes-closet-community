package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* loaded from: classes.dex */
final class zzr extends zzbz {
    private /* synthetic */ Dialog zzfjb;
    private /* synthetic */ zzq zzfjc;

    zzr(zzq zzqVar, Dialog dialog) {
        this.zzfjc = zzqVar;
        this.zzfjb = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.zzbz
    public final void zzage() {
        this.zzfjc.zzfja.zzagb();
        if (this.zzfjb.isShowing()) {
            this.zzfjb.dismiss();
        }
    }
}
