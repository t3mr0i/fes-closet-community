package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcfy extends zzeha<zzcfy> {
    private static volatile zzcfy[] zzixl;
    public Integer zzixm = null;
    public String zzixn = null;
    public zzcfz[] zzixo = zzcfz.zzbac();
    private Boolean zzixp = null;
    public zzcga zzixq = null;

    public zzcfy() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcfy[] zzbab() {
        if (zzixl == null) {
            synchronized (zzehe.zzngo) {
                if (zzixl == null) {
                    zzixl = new zzcfy[0];
                }
            }
        }
        return zzixl;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcfy)) {
            return false;
        }
        zzcfy zzcfyVar = (zzcfy) obj;
        if (this.zzixm == null) {
            if (zzcfyVar.zzixm != null) {
                return false;
            }
        } else if (!this.zzixm.equals(zzcfyVar.zzixm)) {
            return false;
        }
        if (this.zzixn == null) {
            if (zzcfyVar.zzixn != null) {
                return false;
            }
        } else if (!this.zzixn.equals(zzcfyVar.zzixn)) {
            return false;
        }
        if (!zzehe.equals(this.zzixo, zzcfyVar.zzixo)) {
            return false;
        }
        if (this.zzixp == null) {
            if (zzcfyVar.zzixp != null) {
                return false;
            }
        } else if (!this.zzixp.equals(zzcfyVar.zzixp)) {
            return false;
        }
        if (this.zzixq == null) {
            if (zzcfyVar.zzixq != null) {
                return false;
            }
        } else if (!this.zzixq.equals(zzcfyVar.zzixq)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcfyVar.zzngg == null || zzcfyVar.zzngg.isEmpty() : this.zzngg.equals(zzcfyVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (this.zzixp == null ? 0 : this.zzixp.hashCode()) + (((((this.zzixn == null ? 0 : this.zzixn.hashCode()) + (((this.zzixm == null ? 0 : this.zzixm.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + zzehe.hashCode(this.zzixo)) * 31);
        zzcga zzcgaVar = this.zzixq;
        int iHashCode3 = ((zzcgaVar == null ? 0 : zzcgaVar.hashCode()) + (iHashCode2 * 31)) * 31;
        if (this.zzngg != null && !this.zzngg.isEmpty()) {
            iHashCode = this.zzngg.hashCode();
        }
        return iHashCode3 + iHashCode;
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 8:
                    this.zzixm = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 18:
                    this.zzixn = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzb = zzehj.zzb(zzegxVar, 26);
                    int length = this.zzixo == null ? 0 : this.zzixo.length;
                    zzcfz[] zzcfzVarArr = new zzcfz[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzixo, 0, zzcfzVarArr, 0, length);
                    }
                    while (length < zzcfzVarArr.length - 1) {
                        zzcfzVarArr[length] = new zzcfz();
                        zzegxVar.zza(zzcfzVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzcfzVarArr[length] = new zzcfz();
                    zzegxVar.zza(zzcfzVarArr[length]);
                    this.zzixo = zzcfzVarArr;
                    break;
                case 32:
                    this.zzixp = Boolean.valueOf(zzegxVar.zzcea());
                    break;
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                    if (this.zzixq == null) {
                        this.zzixq = new zzcga();
                    }
                    zzegxVar.zza(this.zzixq);
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
        if (this.zzixm != null) {
            zzegyVar.zzv(1, this.zzixm.intValue());
        }
        if (this.zzixn != null) {
            zzegyVar.zzl(2, this.zzixn);
        }
        if (this.zzixo != null && this.zzixo.length > 0) {
            for (int i = 0; i < this.zzixo.length; i++) {
                zzcfz zzcfzVar = this.zzixo[i];
                if (zzcfzVar != null) {
                    zzegyVar.zza(3, zzcfzVar);
                }
            }
        }
        if (this.zzixp != null) {
            zzegyVar.zzl(4, this.zzixp.booleanValue());
        }
        if (this.zzixq != null) {
            zzegyVar.zza(5, this.zzixq);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzixm != null) {
            iZzn += zzegy.zzaf(1, this.zzixm.intValue());
        }
        if (this.zzixn != null) {
            iZzn += zzegy.zzm(2, this.zzixn);
        }
        if (this.zzixo != null && this.zzixo.length > 0) {
            int iZzb = iZzn;
            for (int i = 0; i < this.zzixo.length; i++) {
                zzcfz zzcfzVar = this.zzixo[i];
                if (zzcfzVar != null) {
                    iZzb += zzegy.zzb(3, zzcfzVar);
                }
            }
            iZzn = iZzb;
        }
        if (this.zzixp != null) {
            this.zzixp.booleanValue();
            iZzn += zzegy.zzgs(4) + 1;
        }
        return this.zzixq != null ? iZzn + zzegy.zzb(5, this.zzixq) : iZzn;
    }
}
