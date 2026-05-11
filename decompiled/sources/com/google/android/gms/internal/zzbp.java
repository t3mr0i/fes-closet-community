package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbp extends zzeha<zzbp> {
    private static volatile zzbp[] zzxy;
    public int type = 1;
    public String string = "";
    public zzbp[] zzxz = zzu();
    public zzbp[] zzya = zzu();
    public zzbp[] zzyb = zzu();
    public String zzyc = "";
    public String zzyd = "";
    public long zzye = 0;
    public boolean zzyf = false;
    public zzbp[] zzyg = zzu();
    public int[] zzyh = zzehj.zzngu;
    public boolean zzyi = false;

    public zzbp() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzbp[] zzu() {
        if (zzxy == null) {
            synchronized (zzehe.zzngo) {
                if (zzxy == null) {
                    zzxy = new zzbp[0];
                }
            }
        }
        return zzxy;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbp)) {
            return false;
        }
        zzbp zzbpVar = (zzbp) obj;
        if (this.type != zzbpVar.type) {
            return false;
        }
        if (this.string == null) {
            if (zzbpVar.string != null) {
                return false;
            }
        } else if (!this.string.equals(zzbpVar.string)) {
            return false;
        }
        if (zzehe.equals(this.zzxz, zzbpVar.zzxz) && zzehe.equals(this.zzya, zzbpVar.zzya) && zzehe.equals(this.zzyb, zzbpVar.zzyb)) {
            if (this.zzyc == null) {
                if (zzbpVar.zzyc != null) {
                    return false;
                }
            } else if (!this.zzyc.equals(zzbpVar.zzyc)) {
                return false;
            }
            if (this.zzyd == null) {
                if (zzbpVar.zzyd != null) {
                    return false;
                }
            } else if (!this.zzyd.equals(zzbpVar.zzyd)) {
                return false;
            }
            if (this.zzye == zzbpVar.zzye && this.zzyf == zzbpVar.zzyf && zzehe.equals(this.zzyg, zzbpVar.zzyg) && zzehe.equals(this.zzyh, zzbpVar.zzyh) && this.zzyi == zzbpVar.zzyi) {
                return (this.zzngg == null || this.zzngg.isEmpty()) ? zzbpVar.zzngg == null || zzbpVar.zzngg.isEmpty() : this.zzngg.equals(zzbpVar.zzngg);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((((((this.zzyf ? 1231 : 1237) + (((((this.zzyd == null ? 0 : this.zzyd.hashCode()) + (((this.zzyc == null ? 0 : this.zzyc.hashCode()) + (((((((((this.string == null ? 0 : this.string.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31)) * 31) + zzehe.hashCode(this.zzxz)) * 31) + zzehe.hashCode(this.zzya)) * 31) + zzehe.hashCode(this.zzyb)) * 31)) * 31)) * 31) + ((int) (this.zzye ^ (this.zzye >>> 32)))) * 31)) * 31) + zzehe.hashCode(this.zzyg)) * 31) + zzehe.hashCode(this.zzyh)) * 31) + (this.zzyi ? 1231 : 1237)) * 31;
        if (this.zzngg != null && !this.zzngg.isEmpty()) {
            iHashCode = this.zzngg.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        int i;
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 8:
                    int position = zzegxVar.getPosition();
                    int iZzccj = zzegxVar.zzccj();
                    switch (iZzccj) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            this.type = iZzccj;
                            break;
                        default:
                            zzegxVar.zzhb(position);
                            zza(zzegxVar, iZzcby);
                            break;
                    }
                case 18:
                    this.string = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzb = zzehj.zzb(zzegxVar, 26);
                    int length = this.zzxz == null ? 0 : this.zzxz.length;
                    zzbp[] zzbpVarArr = new zzbp[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzxz, 0, zzbpVarArr, 0, length);
                    }
                    while (length < zzbpVarArr.length - 1) {
                        zzbpVarArr[length] = new zzbp();
                        zzegxVar.zza(zzbpVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzbpVarArr[length] = new zzbp();
                    zzegxVar.zza(zzbpVarArr[length]);
                    this.zzxz = zzbpVarArr;
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    int iZzb2 = zzehj.zzb(zzegxVar, 34);
                    int length2 = this.zzya == null ? 0 : this.zzya.length;
                    zzbp[] zzbpVarArr2 = new zzbp[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzya, 0, zzbpVarArr2, 0, length2);
                    }
                    while (length2 < zzbpVarArr2.length - 1) {
                        zzbpVarArr2[length2] = new zzbp();
                        zzegxVar.zza(zzbpVarArr2[length2]);
                        zzegxVar.zzcby();
                        length2++;
                    }
                    zzbpVarArr2[length2] = new zzbp();
                    zzegxVar.zza(zzbpVarArr2[length2]);
                    this.zzya = zzbpVarArr2;
                    break;
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                    int iZzb3 = zzehj.zzb(zzegxVar, 42);
                    int length3 = this.zzyb == null ? 0 : this.zzyb.length;
                    zzbp[] zzbpVarArr3 = new zzbp[iZzb3 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzyb, 0, zzbpVarArr3, 0, length3);
                    }
                    while (length3 < zzbpVarArr3.length - 1) {
                        zzbpVarArr3[length3] = new zzbp();
                        zzegxVar.zza(zzbpVarArr3[length3]);
                        zzegxVar.zzcby();
                        length3++;
                    }
                    zzbpVarArr3[length3] = new zzbp();
                    zzegxVar.zza(zzbpVarArr3[length3]);
                    this.zzyb = zzbpVarArr3;
                    break;
                case 50:
                    this.zzyc = zzegxVar.readString();
                    break;
                case 58:
                    this.zzyd = zzegxVar.readString();
                    break;
                case 64:
                    this.zzye = zzegxVar.zzcec();
                    break;
                case 72:
                    this.zzyi = zzegxVar.zzcea();
                    break;
                case 80:
                    int iZzb4 = zzehj.zzb(zzegxVar, 80);
                    int[] iArr = new int[iZzb4];
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < iZzb4) {
                        if (i2 != 0) {
                            zzegxVar.zzcby();
                        }
                        int position2 = zzegxVar.getPosition();
                        int iZzccj2 = zzegxVar.zzccj();
                        switch (iZzccj2) {
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
                                i = i3 + 1;
                                iArr[i3] = iZzccj2;
                                break;
                            default:
                                zzegxVar.zzhb(position2);
                                zza(zzegxVar, iZzcby);
                                i = i3;
                                break;
                        }
                        i2++;
                        i3 = i;
                    }
                    if (i3 != 0) {
                        int length4 = this.zzyh == null ? 0 : this.zzyh.length;
                        if (length4 != 0 || i3 != iArr.length) {
                            int[] iArr2 = new int[length4 + i3];
                            if (length4 != 0) {
                                System.arraycopy(this.zzyh, 0, iArr2, 0, length4);
                            }
                            System.arraycopy(iArr, 0, iArr2, length4, i3);
                            this.zzyh = iArr2;
                            break;
                        } else {
                            this.zzyh = iArr;
                            break;
                        }
                    } else {
                        break;
                    }
                case 82:
                    int iZzgn = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position3 = zzegxVar.getPosition();
                    int i4 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        switch (zzegxVar.zzccj()) {
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
                                i4++;
                                break;
                        }
                    }
                    if (i4 != 0) {
                        zzegxVar.zzhb(position3);
                        int length5 = this.zzyh == null ? 0 : this.zzyh.length;
                        int[] iArr3 = new int[i4 + length5];
                        if (length5 != 0) {
                            System.arraycopy(this.zzyh, 0, iArr3, 0, length5);
                        }
                        while (zzegxVar.zzcef() > 0) {
                            int position4 = zzegxVar.getPosition();
                            int iZzccj3 = zzegxVar.zzccj();
                            switch (iZzccj3) {
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
                                    iArr3[length5] = iZzccj3;
                                    length5++;
                                    break;
                                default:
                                    zzegxVar.zzhb(position4);
                                    zza(zzegxVar, 80);
                                    break;
                            }
                        }
                        this.zzyh = iArr3;
                    }
                    zzegxVar.zzgo(iZzgn);
                    break;
                case 90:
                    int iZzb5 = zzehj.zzb(zzegxVar, 90);
                    int length6 = this.zzyg == null ? 0 : this.zzyg.length;
                    zzbp[] zzbpVarArr4 = new zzbp[iZzb5 + length6];
                    if (length6 != 0) {
                        System.arraycopy(this.zzyg, 0, zzbpVarArr4, 0, length6);
                    }
                    while (length6 < zzbpVarArr4.length - 1) {
                        zzbpVarArr4[length6] = new zzbp();
                        zzegxVar.zza(zzbpVarArr4[length6]);
                        zzegxVar.zzcby();
                        length6++;
                    }
                    zzbpVarArr4[length6] = new zzbp();
                    zzegxVar.zza(zzbpVarArr4[length6]);
                    this.zzyg = zzbpVarArr4;
                    break;
                case 96:
                    this.zzyf = zzegxVar.zzcea();
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
        zzegyVar.zzv(1, this.type);
        if (this.string != null && !this.string.equals("")) {
            zzegyVar.zzl(2, this.string);
        }
        if (this.zzxz != null && this.zzxz.length > 0) {
            for (int i = 0; i < this.zzxz.length; i++) {
                zzbp zzbpVar = this.zzxz[i];
                if (zzbpVar != null) {
                    zzegyVar.zza(3, zzbpVar);
                }
            }
        }
        if (this.zzya != null && this.zzya.length > 0) {
            for (int i2 = 0; i2 < this.zzya.length; i2++) {
                zzbp zzbpVar2 = this.zzya[i2];
                if (zzbpVar2 != null) {
                    zzegyVar.zza(4, zzbpVar2);
                }
            }
        }
        if (this.zzyb != null && this.zzyb.length > 0) {
            for (int i3 = 0; i3 < this.zzyb.length; i3++) {
                zzbp zzbpVar3 = this.zzyb[i3];
                if (zzbpVar3 != null) {
                    zzegyVar.zza(5, zzbpVar3);
                }
            }
        }
        if (this.zzyc != null && !this.zzyc.equals("")) {
            zzegyVar.zzl(6, this.zzyc);
        }
        if (this.zzyd != null && !this.zzyd.equals("")) {
            zzegyVar.zzl(7, this.zzyd);
        }
        if (this.zzye != 0) {
            zzegyVar.zze(8, this.zzye);
        }
        if (this.zzyi) {
            zzegyVar.zzl(9, this.zzyi);
        }
        if (this.zzyh != null && this.zzyh.length > 0) {
            for (int i4 = 0; i4 < this.zzyh.length; i4++) {
                zzegyVar.zzv(10, this.zzyh[i4]);
            }
        }
        if (this.zzyg != null && this.zzyg.length > 0) {
            for (int i5 = 0; i5 < this.zzyg.length; i5++) {
                zzbp zzbpVar4 = this.zzyg[i5];
                if (zzbpVar4 != null) {
                    zzegyVar.zza(11, zzbpVar4);
                }
            }
        }
        if (this.zzyf) {
            zzegyVar.zzl(12, this.zzyf);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn() + zzegy.zzaf(1, this.type);
        if (this.string != null && !this.string.equals("")) {
            iZzn += zzegy.zzm(2, this.string);
        }
        if (this.zzxz != null && this.zzxz.length > 0) {
            int iZzb = iZzn;
            for (int i = 0; i < this.zzxz.length; i++) {
                zzbp zzbpVar = this.zzxz[i];
                if (zzbpVar != null) {
                    iZzb += zzegy.zzb(3, zzbpVar);
                }
            }
            iZzn = iZzb;
        }
        if (this.zzya != null && this.zzya.length > 0) {
            int iZzb2 = iZzn;
            for (int i2 = 0; i2 < this.zzya.length; i2++) {
                zzbp zzbpVar2 = this.zzya[i2];
                if (zzbpVar2 != null) {
                    iZzb2 += zzegy.zzb(4, zzbpVar2);
                }
            }
            iZzn = iZzb2;
        }
        if (this.zzyb != null && this.zzyb.length > 0) {
            int iZzb3 = iZzn;
            for (int i3 = 0; i3 < this.zzyb.length; i3++) {
                zzbp zzbpVar3 = this.zzyb[i3];
                if (zzbpVar3 != null) {
                    iZzb3 += zzegy.zzb(5, zzbpVar3);
                }
            }
            iZzn = iZzb3;
        }
        if (this.zzyc != null && !this.zzyc.equals("")) {
            iZzn += zzegy.zzm(6, this.zzyc);
        }
        if (this.zzyd != null && !this.zzyd.equals("")) {
            iZzn += zzegy.zzm(7, this.zzyd);
        }
        if (this.zzye != 0) {
            iZzn += zzegy.zzg(8, this.zzye);
        }
        if (this.zzyi) {
            iZzn += zzegy.zzgs(9) + 1;
        }
        if (this.zzyh != null && this.zzyh.length > 0) {
            int iZzhd = 0;
            for (int i4 = 0; i4 < this.zzyh.length; i4++) {
                iZzhd += zzegy.zzhd(this.zzyh[i4]);
            }
            iZzn = iZzn + iZzhd + (this.zzyh.length * 1);
        }
        if (this.zzyg != null && this.zzyg.length > 0) {
            for (int i5 = 0; i5 < this.zzyg.length; i5++) {
                zzbp zzbpVar4 = this.zzyg[i5];
                if (zzbpVar4 != null) {
                    iZzn += zzegy.zzb(11, zzbpVar4);
                }
            }
        }
        return this.zzyf ? iZzn + zzegy.zzgs(12) + 1 : iZzn;
    }
}
