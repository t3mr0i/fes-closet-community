package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.DataLayer;
import java.util.List;

/* loaded from: classes.dex */
final class zzap implements zzaq {
    private /* synthetic */ DataLayer zzjqo;

    zzap(DataLayer dataLayer) {
        this.zzjqo = dataLayer;
    }

    @Override // com.google.android.gms.tagmanager.zzaq
    public final void zzah(List<DataLayer.zza> list) {
        for (DataLayer.zza zzaVar : list) {
            this.zzjqo.zzs(DataLayer.zzn(zzaVar.zzbfe, zzaVar.mValue));
        }
        this.zzjqo.zzjqn.countDown();
    }
}
