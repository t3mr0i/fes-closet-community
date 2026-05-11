package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgj extends zzeha<zzcgj> {
    public zzcgk[] zzizb = zzcgk.zzbaj();

    public zzcgj() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgj)) {
            return false;
        }
        zzcgj zzcgjVar = (zzcgj) obj;
        if (zzehe.equals(this.zzizb, zzcgjVar.zzizb)) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgjVar.zzngg == null || zzcgjVar.zzngg.isEmpty() : this.zzngg.equals(zzcgjVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zzizb)) * 31);
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 10:
                    int iZzb = zzehj.zzb(zzegxVar, 10);
                    int length = this.zzizb == null ? 0 : this.zzizb.length;
                    zzcgk[] zzcgkVarArr = new zzcgk[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzizb, 0, zzcgkVarArr, 0, length);
                    }
                    while (length < zzcgkVarArr.length - 1) {
                        zzcgkVarArr[length] = new zzcgk();
                        zzegxVar.zza(zzcgkVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzcgkVarArr[length] = new zzcgk();
                    zzegxVar.zza(zzcgkVarArr[length]);
                    this.zzizb = zzcgkVarArr;
                    break;
                default:
                    if (!super.zza(zzegxVar, iZzcby)) {
                        break;
                    } else {
                        break;
                    }
            }
        }
        return this;
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    public final void zza(zzegy zzegyVar) throws IOException {
        if (this.zzizb != null && this.zzizb.length > 0) {
            for (int i = 0; i < this.zzizb.length; i++) {
                zzcgk zzcgkVar = this.zzizb[i];
                if (zzcgkVar != null) {
                    zzegyVar.zza(1, zzcgkVar);
                }
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzizb != null && this.zzizb.length > 0) {
            for (int i = 0; i < this.zzizb.length; i++) {
                zzcgk zzcgkVar = this.zzizb[i];
                if (zzcgkVar != null) {
                    iZzn += zzegy.zzb(1, zzcgkVar);
                }
            }
        }
        return iZzn;
    }
}
