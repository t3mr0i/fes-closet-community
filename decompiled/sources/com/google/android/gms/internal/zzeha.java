package com.google.android.gms.internal;

import com.google.android.gms.internal.zzeha;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class zzeha<M extends zzeha<M>> extends zzehg {
    protected zzehc zzngg;

    public final <T> T zza(zzehb<M, T> zzehbVar) {
        zzehd zzehdVarZzhi;
        if (this.zzngg == null || (zzehdVarZzhi = this.zzngg.zzhi(zzehbVar.tag >>> 3)) == null) {
            return null;
        }
        return (T) zzehdVarZzhi.zzb(zzehbVar);
    }

    @Override // com.google.android.gms.internal.zzehg
    public void zza(zzegy zzegyVar) throws IOException {
        if (this.zzngg == null) {
            return;
        }
        for (int i = 0; i < this.zzngg.size(); i++) {
            this.zzngg.zzhj(i).zza(zzegyVar);
        }
    }

    protected final boolean zza(zzegx zzegxVar, int i) throws IOException {
        int position = zzegxVar.getPosition();
        if (!zzegxVar.zzha(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzehi zzehiVar = new zzehi(i, zzegxVar.zzad(position, zzegxVar.getPosition() - position));
        zzehd zzehdVarZzhi = null;
        if (this.zzngg == null) {
            this.zzngg = new zzehc();
        } else {
            zzehdVarZzhi = this.zzngg.zzhi(i2);
        }
        if (zzehdVarZzhi == null) {
            zzehdVarZzhi = new zzehd();
            this.zzngg.zza(i2, zzehdVarZzhi);
        }
        zzehdVarZzhi.zza(zzehiVar);
        return true;
    }

    @Override // com.google.android.gms.internal.zzehg
    /* renamed from: zzceh, reason: merged with bridge method [inline-methods] */
    public M clone() throws CloneNotSupportedException {
        M m = (M) super.clone();
        zzehe.zza(this, m);
        return m;
    }

    @Override // com.google.android.gms.internal.zzehg
    /* renamed from: zzcei */
    public /* synthetic */ zzehg clone() throws CloneNotSupportedException {
        return (zzeha) clone();
    }

    @Override // com.google.android.gms.internal.zzehg
    protected int zzn() {
        int iZzn = 0;
        if (this.zzngg == null) {
            return 0;
        }
        int i = 0;
        while (true) {
            int i2 = iZzn;
            if (i >= this.zzngg.size()) {
                return i2;
            }
            iZzn = this.zzngg.zzhj(i).zzn() + i2;
            i++;
        }
    }
}
