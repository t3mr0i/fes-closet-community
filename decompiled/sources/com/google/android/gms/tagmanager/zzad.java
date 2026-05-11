package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdbm;

/* loaded from: classes.dex */
final class zzad implements zzdi<zzdbm> {
    private /* synthetic */ zzy zzjqa;

    private zzad(zzy zzyVar) {
        this.zzjqa = zzyVar;
    }

    /* synthetic */ zzad(zzy zzyVar, zzz zzzVar) {
        this(zzyVar);
    }

    @Override // com.google.android.gms.tagmanager.zzdi
    public final /* synthetic */ void onSuccess(zzdbm zzdbmVar) {
        com.google.android.gms.internal.zzbo zzboVar;
        zzdbm zzdbmVar2 = zzdbmVar;
        if (zzdbmVar2.zzkfk != null) {
            zzboVar = zzdbmVar2.zzkfk;
        } else {
            com.google.android.gms.internal.zzbl zzblVar = zzdbmVar2.zzxw;
            zzboVar = new com.google.android.gms.internal.zzbo();
            zzboVar.zzxw = zzblVar;
            zzboVar.zzxv = null;
            zzboVar.zzxx = zzblVar.version;
        }
        this.zzjqa.zza(zzboVar, zzdbmVar2.zzkfj, true);
    }

    @Override // com.google.android.gms.tagmanager.zzdi
    public final void zzed(int i) {
        if (this.zzjqa.zzjpv) {
            return;
        }
        this.zzjqa.zzbf(0L);
    }
}
