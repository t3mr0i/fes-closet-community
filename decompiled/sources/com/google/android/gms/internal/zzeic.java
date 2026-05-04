package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzeic extends zzeha<zzeic> implements Cloneable {
    private int zznke = -1;
    private int zznkf = 0;

    public zzeic() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcez, reason: merged with bridge method [inline-methods] */
    public zzeic clone() {
        try {
            return (zzeic) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeic)) {
            return false;
        }
        zzeic zzeicVar = (zzeic) obj;
        if (this.zznke == zzeicVar.zznke && this.zznkf == zzeicVar.zznkf) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzeicVar.zzngg == null || zzeicVar.zzngg.isEmpty() : this.zzngg.equals(zzeicVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + this.zznke) * 31) + this.zznkf) * 31);
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 8:
                    int position = zzegxVar.getPosition();
                    int iZzcdz = zzegxVar.zzcdz();
                    switch (iZzcdz) {
                        case -1:
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                            this.zznke = iZzcdz;
                            break;
                        default:
                            zzegxVar.zzhb(position);
                            zza(zzegxVar, iZzcby);
                            break;
                    }
                case 16:
                    int position2 = zzegxVar.getPosition();
                    int iZzcdz2 = zzegxVar.zzcdz();
                    switch (iZzcdz2) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 100:
                            this.zznkf = iZzcdz2;
                            break;
                        default:
                            zzegxVar.zzhb(position2);
                            zza(zzegxVar, iZzcby);
                            break;
                    }
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
        if (this.zznke != -1) {
            zzegyVar.zzv(1, this.zznke);
        }
        if (this.zznkf != 0) {
            zzegyVar.zzv(2, this.zznkf);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha
    /* renamed from: zzceh */
    public final /* synthetic */ zzeha clone() throws CloneNotSupportedException {
        return (zzeic) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcei */
    public final /* synthetic */ zzehg clone() throws CloneNotSupportedException {
        return (zzeic) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zznke != -1) {
            iZzn += zzegy.zzaf(1, this.zznke);
        }
        return this.zznkf != 0 ? iZzn + zzegy.zzaf(2, this.zznkf) : iZzn;
    }
}
