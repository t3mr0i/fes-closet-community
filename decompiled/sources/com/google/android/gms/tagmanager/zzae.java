package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zzae implements zzdi<com.google.android.gms.internal.zzbo> {
    private /* synthetic */ zzy zzjqa;

    private zzae(zzy zzyVar) {
        this.zzjqa = zzyVar;
    }

    /* synthetic */ zzae(zzy zzyVar, zzz zzzVar) {
        this(zzyVar);
    }

    @Override // com.google.android.gms.tagmanager.zzdi
    public final /* synthetic */ void onSuccess(com.google.android.gms.internal.zzbo zzboVar) {
        com.google.android.gms.internal.zzbo zzboVar2 = zzboVar;
        this.zzjqa.zzjpr.zzbdb();
        synchronized (this.zzjqa) {
            if (zzboVar2.zzxw == null) {
                if (this.zzjqa.zzjpw.zzxw == null) {
                    zzdj.e("Current resource is null; network resource is also null");
                    this.zzjqa.zzbf(this.zzjqa.zzjpr.zzbcz());
                    return;
                }
                zzboVar2.zzxw = this.zzjqa.zzjpw.zzxw;
            }
            this.zzjqa.zza(zzboVar2, this.zzjqa.zzasb.currentTimeMillis(), false);
            zzdj.v(new StringBuilder(58).append("setting refresh time to current time: ").append(this.zzjqa.zzjpe).toString());
            if (!this.zzjqa.zzbcw()) {
                this.zzjqa.zza(zzboVar2);
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzdi
    public final void zzed(int i) {
        if (i == zzda.zzjsn) {
            this.zzjqa.zzjpr.zzbda();
        }
        synchronized (this.zzjqa) {
            if (!this.zzjqa.isReady()) {
                if (this.zzjqa.zzjpu != null) {
                    this.zzjqa.setResult(this.zzjqa.zzjpu);
                } else {
                    this.zzjqa.setResult(this.zzjqa.zzb(Status.zzfhx));
                }
            }
        }
        this.zzjqa.zzbf(this.zzjqa.zzjpr.zzbcz());
    }
}
