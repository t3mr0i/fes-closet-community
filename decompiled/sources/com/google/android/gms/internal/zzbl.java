package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbl extends zzeha<zzbl> {
    private String[] zzwi = zzehj.EMPTY_STRING_ARRAY;
    public String[] zzwj = zzehj.EMPTY_STRING_ARRAY;
    public zzbp[] zzwk = zzbp.zzu();
    public zzbk[] zzwl = zzbk.zzr();
    public zzbh[] zzwm = zzbh.zzp();
    public zzbh[] zzwn = zzbh.zzp();
    public zzbh[] zzwo = zzbh.zzp();
    public zzbm[] zzwp = zzbm.zzs();
    private String zzwq = "";
    private String zzwr = "";
    private String zzws = "0";
    public String version = "";
    private zzbg zzwt = null;
    private float zzwu = 0.0f;
    private boolean zzwv = false;
    private String[] zzww = zzehj.EMPTY_STRING_ARRAY;
    public int zzwx = 0;

    public zzbl() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbl)) {
            return false;
        }
        zzbl zzblVar = (zzbl) obj;
        if (zzehe.equals(this.zzwi, zzblVar.zzwi) && zzehe.equals(this.zzwj, zzblVar.zzwj) && zzehe.equals(this.zzwk, zzblVar.zzwk) && zzehe.equals(this.zzwl, zzblVar.zzwl) && zzehe.equals(this.zzwm, zzblVar.zzwm) && zzehe.equals(this.zzwn, zzblVar.zzwn) && zzehe.equals(this.zzwo, zzblVar.zzwo) && zzehe.equals(this.zzwp, zzblVar.zzwp)) {
            if (this.zzwq == null) {
                if (zzblVar.zzwq != null) {
                    return false;
                }
            } else if (!this.zzwq.equals(zzblVar.zzwq)) {
                return false;
            }
            if (this.zzwr == null) {
                if (zzblVar.zzwr != null) {
                    return false;
                }
            } else if (!this.zzwr.equals(zzblVar.zzwr)) {
                return false;
            }
            if (this.zzws == null) {
                if (zzblVar.zzws != null) {
                    return false;
                }
            } else if (!this.zzws.equals(zzblVar.zzws)) {
                return false;
            }
            if (this.version == null) {
                if (zzblVar.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzblVar.version)) {
                return false;
            }
            if (this.zzwt == null) {
                if (zzblVar.zzwt != null) {
                    return false;
                }
            } else if (!this.zzwt.equals(zzblVar.zzwt)) {
                return false;
            }
            if (Float.floatToIntBits(this.zzwu) == Float.floatToIntBits(zzblVar.zzwu) && this.zzwv == zzblVar.zzwv && zzehe.equals(this.zzww, zzblVar.zzww) && this.zzwx == zzblVar.zzwx) {
                return (this.zzngg == null || this.zzngg.isEmpty()) ? zzblVar.zzngg == null || zzblVar.zzngg.isEmpty() : this.zzngg.equals(zzblVar.zzngg);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (this.version == null ? 0 : this.version.hashCode()) + (((this.zzws == null ? 0 : this.zzws.hashCode()) + (((this.zzwr == null ? 0 : this.zzwr.hashCode()) + (((this.zzwq == null ? 0 : this.zzwq.hashCode()) + ((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zzwi)) * 31) + zzehe.hashCode(this.zzwj)) * 31) + zzehe.hashCode(this.zzwk)) * 31) + zzehe.hashCode(this.zzwl)) * 31) + zzehe.hashCode(this.zzwm)) * 31) + zzehe.hashCode(this.zzwn)) * 31) + zzehe.hashCode(this.zzwo)) * 31) + zzehe.hashCode(this.zzwp)) * 31)) * 31)) * 31)) * 31);
        zzbg zzbgVar = this.zzwt;
        int iHashCode3 = ((((((this.zzwv ? 1231 : 1237) + (((((zzbgVar == null ? 0 : zzbgVar.hashCode()) + (iHashCode2 * 31)) * 31) + Float.floatToIntBits(this.zzwu)) * 31)) * 31) + zzehe.hashCode(this.zzww)) * 31) + this.zzwx) * 31;
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
                case 10:
                    int iZzb = zzehj.zzb(zzegxVar, 10);
                    int length = this.zzwj == null ? 0 : this.zzwj.length;
                    String[] strArr = new String[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzwj, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzegxVar.readString();
                        zzegxVar.zzcby();
                        length++;
                    }
                    strArr[length] = zzegxVar.readString();
                    this.zzwj = strArr;
                    break;
                case 18:
                    int iZzb2 = zzehj.zzb(zzegxVar, 18);
                    int length2 = this.zzwk == null ? 0 : this.zzwk.length;
                    zzbp[] zzbpVarArr = new zzbp[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzwk, 0, zzbpVarArr, 0, length2);
                    }
                    while (length2 < zzbpVarArr.length - 1) {
                        zzbpVarArr[length2] = new zzbp();
                        zzegxVar.zza(zzbpVarArr[length2]);
                        zzegxVar.zzcby();
                        length2++;
                    }
                    zzbpVarArr[length2] = new zzbp();
                    zzegxVar.zza(zzbpVarArr[length2]);
                    this.zzwk = zzbpVarArr;
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzb3 = zzehj.zzb(zzegxVar, 26);
                    int length3 = this.zzwl == null ? 0 : this.zzwl.length;
                    zzbk[] zzbkVarArr = new zzbk[iZzb3 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzwl, 0, zzbkVarArr, 0, length3);
                    }
                    while (length3 < zzbkVarArr.length - 1) {
                        zzbkVarArr[length3] = new zzbk();
                        zzegxVar.zza(zzbkVarArr[length3]);
                        zzegxVar.zzcby();
                        length3++;
                    }
                    zzbkVarArr[length3] = new zzbk();
                    zzegxVar.zza(zzbkVarArr[length3]);
                    this.zzwl = zzbkVarArr;
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    int iZzb4 = zzehj.zzb(zzegxVar, 34);
                    int length4 = this.zzwm == null ? 0 : this.zzwm.length;
                    zzbh[] zzbhVarArr = new zzbh[iZzb4 + length4];
                    if (length4 != 0) {
                        System.arraycopy(this.zzwm, 0, zzbhVarArr, 0, length4);
                    }
                    while (length4 < zzbhVarArr.length - 1) {
                        zzbhVarArr[length4] = new zzbh();
                        zzegxVar.zza(zzbhVarArr[length4]);
                        zzegxVar.zzcby();
                        length4++;
                    }
                    zzbhVarArr[length4] = new zzbh();
                    zzegxVar.zza(zzbhVarArr[length4]);
                    this.zzwm = zzbhVarArr;
                    break;
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                    int iZzb5 = zzehj.zzb(zzegxVar, 42);
                    int length5 = this.zzwn == null ? 0 : this.zzwn.length;
                    zzbh[] zzbhVarArr2 = new zzbh[iZzb5 + length5];
                    if (length5 != 0) {
                        System.arraycopy(this.zzwn, 0, zzbhVarArr2, 0, length5);
                    }
                    while (length5 < zzbhVarArr2.length - 1) {
                        zzbhVarArr2[length5] = new zzbh();
                        zzegxVar.zza(zzbhVarArr2[length5]);
                        zzegxVar.zzcby();
                        length5++;
                    }
                    zzbhVarArr2[length5] = new zzbh();
                    zzegxVar.zza(zzbhVarArr2[length5]);
                    this.zzwn = zzbhVarArr2;
                    break;
                case 50:
                    int iZzb6 = zzehj.zzb(zzegxVar, 50);
                    int length6 = this.zzwo == null ? 0 : this.zzwo.length;
                    zzbh[] zzbhVarArr3 = new zzbh[iZzb6 + length6];
                    if (length6 != 0) {
                        System.arraycopy(this.zzwo, 0, zzbhVarArr3, 0, length6);
                    }
                    while (length6 < zzbhVarArr3.length - 1) {
                        zzbhVarArr3[length6] = new zzbh();
                        zzegxVar.zza(zzbhVarArr3[length6]);
                        zzegxVar.zzcby();
                        length6++;
                    }
                    zzbhVarArr3[length6] = new zzbh();
                    zzegxVar.zza(zzbhVarArr3[length6]);
                    this.zzwo = zzbhVarArr3;
                    break;
                case 58:
                    int iZzb7 = zzehj.zzb(zzegxVar, 58);
                    int length7 = this.zzwp == null ? 0 : this.zzwp.length;
                    zzbm[] zzbmVarArr = new zzbm[iZzb7 + length7];
                    if (length7 != 0) {
                        System.arraycopy(this.zzwp, 0, zzbmVarArr, 0, length7);
                    }
                    while (length7 < zzbmVarArr.length - 1) {
                        zzbmVarArr[length7] = new zzbm();
                        zzegxVar.zza(zzbmVarArr[length7]);
                        zzegxVar.zzcby();
                        length7++;
                    }
                    zzbmVarArr[length7] = new zzbm();
                    zzegxVar.zza(zzbmVarArr[length7]);
                    this.zzwp = zzbmVarArr;
                    break;
                case 74:
                    this.zzwq = zzegxVar.readString();
                    break;
                case 82:
                    this.zzwr = zzegxVar.readString();
                    break;
                case 98:
                    this.zzws = zzegxVar.readString();
                    break;
                case 106:
                    this.version = zzegxVar.readString();
                    break;
                case 114:
                    if (this.zzwt == null) {
                        this.zzwt = new zzbg();
                    }
                    zzegxVar.zza(this.zzwt);
                    break;
                case 125:
                    this.zzwu = Float.intBitsToFloat(zzegxVar.zzced());
                    break;
                case 130:
                    int iZzb8 = zzehj.zzb(zzegxVar, 130);
                    int length8 = this.zzww == null ? 0 : this.zzww.length;
                    String[] strArr2 = new String[iZzb8 + length8];
                    if (length8 != 0) {
                        System.arraycopy(this.zzww, 0, strArr2, 0, length8);
                    }
                    while (length8 < strArr2.length - 1) {
                        strArr2[length8] = zzegxVar.readString();
                        zzegxVar.zzcby();
                        length8++;
                    }
                    strArr2[length8] = zzegxVar.readString();
                    this.zzww = strArr2;
                    break;
                case 136:
                    this.zzwx = zzegxVar.zzccj();
                    break;
                case 144:
                    this.zzwv = zzegxVar.zzcea();
                    break;
                case 154:
                    int iZzb9 = zzehj.zzb(zzegxVar, 154);
                    int length9 = this.zzwi == null ? 0 : this.zzwi.length;
                    String[] strArr3 = new String[iZzb9 + length9];
                    if (length9 != 0) {
                        System.arraycopy(this.zzwi, 0, strArr3, 0, length9);
                    }
                    while (length9 < strArr3.length - 1) {
                        strArr3[length9] = zzegxVar.readString();
                        zzegxVar.zzcby();
                        length9++;
                    }
                    strArr3[length9] = zzegxVar.readString();
                    this.zzwi = strArr3;
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
        if (this.zzwj != null && this.zzwj.length > 0) {
            for (int i = 0; i < this.zzwj.length; i++) {
                String str = this.zzwj[i];
                if (str != null) {
                    zzegyVar.zzl(1, str);
                }
            }
        }
        if (this.zzwk != null && this.zzwk.length > 0) {
            for (int i2 = 0; i2 < this.zzwk.length; i2++) {
                zzbp zzbpVar = this.zzwk[i2];
                if (zzbpVar != null) {
                    zzegyVar.zza(2, zzbpVar);
                }
            }
        }
        if (this.zzwl != null && this.zzwl.length > 0) {
            for (int i3 = 0; i3 < this.zzwl.length; i3++) {
                zzbk zzbkVar = this.zzwl[i3];
                if (zzbkVar != null) {
                    zzegyVar.zza(3, zzbkVar);
                }
            }
        }
        if (this.zzwm != null && this.zzwm.length > 0) {
            for (int i4 = 0; i4 < this.zzwm.length; i4++) {
                zzbh zzbhVar = this.zzwm[i4];
                if (zzbhVar != null) {
                    zzegyVar.zza(4, zzbhVar);
                }
            }
        }
        if (this.zzwn != null && this.zzwn.length > 0) {
            for (int i5 = 0; i5 < this.zzwn.length; i5++) {
                zzbh zzbhVar2 = this.zzwn[i5];
                if (zzbhVar2 != null) {
                    zzegyVar.zza(5, zzbhVar2);
                }
            }
        }
        if (this.zzwo != null && this.zzwo.length > 0) {
            for (int i6 = 0; i6 < this.zzwo.length; i6++) {
                zzbh zzbhVar3 = this.zzwo[i6];
                if (zzbhVar3 != null) {
                    zzegyVar.zza(6, zzbhVar3);
                }
            }
        }
        if (this.zzwp != null && this.zzwp.length > 0) {
            for (int i7 = 0; i7 < this.zzwp.length; i7++) {
                zzbm zzbmVar = this.zzwp[i7];
                if (zzbmVar != null) {
                    zzegyVar.zza(7, zzbmVar);
                }
            }
        }
        if (this.zzwq != null && !this.zzwq.equals("")) {
            zzegyVar.zzl(9, this.zzwq);
        }
        if (this.zzwr != null && !this.zzwr.equals("")) {
            zzegyVar.zzl(10, this.zzwr);
        }
        if (this.zzws != null && !this.zzws.equals("0")) {
            zzegyVar.zzl(12, this.zzws);
        }
        if (this.version != null && !this.version.equals("")) {
            zzegyVar.zzl(13, this.version);
        }
        if (this.zzwt != null) {
            zzegyVar.zza(14, this.zzwt);
        }
        if (Float.floatToIntBits(this.zzwu) != Float.floatToIntBits(0.0f)) {
            zzegyVar.zzc(15, this.zzwu);
        }
        if (this.zzww != null && this.zzww.length > 0) {
            for (int i8 = 0; i8 < this.zzww.length; i8++) {
                String str2 = this.zzww[i8];
                if (str2 != null) {
                    zzegyVar.zzl(16, str2);
                }
            }
        }
        if (this.zzwx != 0) {
            zzegyVar.zzv(17, this.zzwx);
        }
        if (this.zzwv) {
            zzegyVar.zzl(18, this.zzwv);
        }
        if (this.zzwi != null && this.zzwi.length > 0) {
            for (int i9 = 0; i9 < this.zzwi.length; i9++) {
                String str3 = this.zzwi[i9];
                if (str3 != null) {
                    zzegyVar.zzl(19, str3);
                }
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzgs;
        int iZzn = super.zzn();
        if (this.zzwj == null || this.zzwj.length <= 0) {
            iZzgs = iZzn;
        } else {
            int iZzrk = 0;
            int i = 0;
            for (int i2 = 0; i2 < this.zzwj.length; i2++) {
                String str = this.zzwj[i2];
                if (str != null) {
                    i++;
                    iZzrk += zzegy.zzrk(str);
                }
            }
            iZzgs = iZzn + iZzrk + (i * 1);
        }
        if (this.zzwk != null && this.zzwk.length > 0) {
            int iZzb = iZzgs;
            for (int i3 = 0; i3 < this.zzwk.length; i3++) {
                zzbp zzbpVar = this.zzwk[i3];
                if (zzbpVar != null) {
                    iZzb += zzegy.zzb(2, zzbpVar);
                }
            }
            iZzgs = iZzb;
        }
        if (this.zzwl != null && this.zzwl.length > 0) {
            int iZzb2 = iZzgs;
            for (int i4 = 0; i4 < this.zzwl.length; i4++) {
                zzbk zzbkVar = this.zzwl[i4];
                if (zzbkVar != null) {
                    iZzb2 += zzegy.zzb(3, zzbkVar);
                }
            }
            iZzgs = iZzb2;
        }
        if (this.zzwm != null && this.zzwm.length > 0) {
            int iZzb3 = iZzgs;
            for (int i5 = 0; i5 < this.zzwm.length; i5++) {
                zzbh zzbhVar = this.zzwm[i5];
                if (zzbhVar != null) {
                    iZzb3 += zzegy.zzb(4, zzbhVar);
                }
            }
            iZzgs = iZzb3;
        }
        if (this.zzwn != null && this.zzwn.length > 0) {
            int iZzb4 = iZzgs;
            for (int i6 = 0; i6 < this.zzwn.length; i6++) {
                zzbh zzbhVar2 = this.zzwn[i6];
                if (zzbhVar2 != null) {
                    iZzb4 += zzegy.zzb(5, zzbhVar2);
                }
            }
            iZzgs = iZzb4;
        }
        if (this.zzwo != null && this.zzwo.length > 0) {
            int iZzb5 = iZzgs;
            for (int i7 = 0; i7 < this.zzwo.length; i7++) {
                zzbh zzbhVar3 = this.zzwo[i7];
                if (zzbhVar3 != null) {
                    iZzb5 += zzegy.zzb(6, zzbhVar3);
                }
            }
            iZzgs = iZzb5;
        }
        if (this.zzwp != null && this.zzwp.length > 0) {
            int iZzb6 = iZzgs;
            for (int i8 = 0; i8 < this.zzwp.length; i8++) {
                zzbm zzbmVar = this.zzwp[i8];
                if (zzbmVar != null) {
                    iZzb6 += zzegy.zzb(7, zzbmVar);
                }
            }
            iZzgs = iZzb6;
        }
        if (this.zzwq != null && !this.zzwq.equals("")) {
            iZzgs += zzegy.zzm(9, this.zzwq);
        }
        if (this.zzwr != null && !this.zzwr.equals("")) {
            iZzgs += zzegy.zzm(10, this.zzwr);
        }
        if (this.zzws != null && !this.zzws.equals("0")) {
            iZzgs += zzegy.zzm(12, this.zzws);
        }
        if (this.version != null && !this.version.equals("")) {
            iZzgs += zzegy.zzm(13, this.version);
        }
        if (this.zzwt != null) {
            iZzgs += zzegy.zzb(14, this.zzwt);
        }
        if (Float.floatToIntBits(this.zzwu) != Float.floatToIntBits(0.0f)) {
            iZzgs += zzegy.zzgs(15) + 4;
        }
        if (this.zzww != null && this.zzww.length > 0) {
            int iZzrk2 = 0;
            int i9 = 0;
            for (int i10 = 0; i10 < this.zzww.length; i10++) {
                String str2 = this.zzww[i10];
                if (str2 != null) {
                    i9++;
                    iZzrk2 += zzegy.zzrk(str2);
                }
            }
            iZzgs = iZzgs + iZzrk2 + (i9 * 2);
        }
        if (this.zzwx != 0) {
            iZzgs += zzegy.zzaf(17, this.zzwx);
        }
        if (this.zzwv) {
            iZzgs += zzegy.zzgs(18) + 1;
        }
        if (this.zzwi == null || this.zzwi.length <= 0) {
            return iZzgs;
        }
        int iZzrk3 = 0;
        int i11 = 0;
        for (int i12 = 0; i12 < this.zzwi.length; i12++) {
            String str3 = this.zzwi[i12];
            if (str3 != null) {
                i11++;
                iZzrk3 += zzegy.zzrk(str3);
            }
        }
        return iZzgs + iZzrk3 + (i11 * 2);
    }
}
