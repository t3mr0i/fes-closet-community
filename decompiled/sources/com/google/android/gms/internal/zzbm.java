package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbm extends zzeha<zzbm> {
    private static volatile zzbm[] zzwy;
    public int[] zzwz = zzehj.zzngu;
    public int[] zzxa = zzehj.zzngu;
    public int[] zzxb = zzehj.zzngu;
    public int[] zzxc = zzehj.zzngu;
    public int[] zzxd = zzehj.zzngu;
    public int[] zzxe = zzehj.zzngu;
    public int[] zzxf = zzehj.zzngu;
    public int[] zzxg = zzehj.zzngu;
    public int[] zzxh = zzehj.zzngu;
    public int[] zzxi = zzehj.zzngu;

    public zzbm() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzbm[] zzs() {
        if (zzwy == null) {
            synchronized (zzehe.zzngo) {
                if (zzwy == null) {
                    zzwy = new zzbm[0];
                }
            }
        }
        return zzwy;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbm)) {
            return false;
        }
        zzbm zzbmVar = (zzbm) obj;
        if (zzehe.equals(this.zzwz, zzbmVar.zzwz) && zzehe.equals(this.zzxa, zzbmVar.zzxa) && zzehe.equals(this.zzxb, zzbmVar.zzxb) && zzehe.equals(this.zzxc, zzbmVar.zzxc) && zzehe.equals(this.zzxd, zzbmVar.zzxd) && zzehe.equals(this.zzxe, zzbmVar.zzxe) && zzehe.equals(this.zzxf, zzbmVar.zzxf) && zzehe.equals(this.zzxg, zzbmVar.zzxg) && zzehe.equals(this.zzxh, zzbmVar.zzxh) && zzehe.equals(this.zzxi, zzbmVar.zzxi)) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzbmVar.zzngg == null || zzbmVar.zzngg.isEmpty() : this.zzngg.equals(zzbmVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zzwz)) * 31) + zzehe.hashCode(this.zzxa)) * 31) + zzehe.hashCode(this.zzxb)) * 31) + zzehe.hashCode(this.zzxc)) * 31) + zzehe.hashCode(this.zzxd)) * 31) + zzehe.hashCode(this.zzxe)) * 31) + zzehe.hashCode(this.zzxf)) * 31) + zzehe.hashCode(this.zzxg)) * 31) + zzehe.hashCode(this.zzxh)) * 31) + zzehe.hashCode(this.zzxi)) * 31);
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 8:
                    int iZzb = zzehj.zzb(zzegxVar, 8);
                    int length = this.zzwz == null ? 0 : this.zzwz.length;
                    int[] iArr = new int[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzwz, 0, iArr, 0, length);
                    }
                    while (length < iArr.length - 1) {
                        iArr[length] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length++;
                    }
                    iArr[length] = zzegxVar.zzccj();
                    this.zzwz = iArr;
                    break;
                case 10:
                    int iZzgn = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position = zzegxVar.getPosition();
                    int i = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i++;
                    }
                    zzegxVar.zzhb(position);
                    int length2 = this.zzwz == null ? 0 : this.zzwz.length;
                    int[] iArr2 = new int[i + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzwz, 0, iArr2, 0, length2);
                    }
                    while (length2 < iArr2.length) {
                        iArr2[length2] = zzegxVar.zzccj();
                        length2++;
                    }
                    this.zzwz = iArr2;
                    zzegxVar.zzgo(iZzgn);
                    break;
                case 16:
                    int iZzb2 = zzehj.zzb(zzegxVar, 16);
                    int length3 = this.zzxa == null ? 0 : this.zzxa.length;
                    int[] iArr3 = new int[iZzb2 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzxa, 0, iArr3, 0, length3);
                    }
                    while (length3 < iArr3.length - 1) {
                        iArr3[length3] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length3++;
                    }
                    iArr3[length3] = zzegxVar.zzccj();
                    this.zzxa = iArr3;
                    break;
                case 18:
                    int iZzgn2 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position2 = zzegxVar.getPosition();
                    int i2 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i2++;
                    }
                    zzegxVar.zzhb(position2);
                    int length4 = this.zzxa == null ? 0 : this.zzxa.length;
                    int[] iArr4 = new int[i2 + length4];
                    if (length4 != 0) {
                        System.arraycopy(this.zzxa, 0, iArr4, 0, length4);
                    }
                    while (length4 < iArr4.length) {
                        iArr4[length4] = zzegxVar.zzccj();
                        length4++;
                    }
                    this.zzxa = iArr4;
                    zzegxVar.zzgo(iZzgn2);
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    int iZzb3 = zzehj.zzb(zzegxVar, 24);
                    int length5 = this.zzxb == null ? 0 : this.zzxb.length;
                    int[] iArr5 = new int[iZzb3 + length5];
                    if (length5 != 0) {
                        System.arraycopy(this.zzxb, 0, iArr5, 0, length5);
                    }
                    while (length5 < iArr5.length - 1) {
                        iArr5[length5] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length5++;
                    }
                    iArr5[length5] = zzegxVar.zzccj();
                    this.zzxb = iArr5;
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzgn3 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position3 = zzegxVar.getPosition();
                    int i3 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i3++;
                    }
                    zzegxVar.zzhb(position3);
                    int length6 = this.zzxb == null ? 0 : this.zzxb.length;
                    int[] iArr6 = new int[i3 + length6];
                    if (length6 != 0) {
                        System.arraycopy(this.zzxb, 0, iArr6, 0, length6);
                    }
                    while (length6 < iArr6.length) {
                        iArr6[length6] = zzegxVar.zzccj();
                        length6++;
                    }
                    this.zzxb = iArr6;
                    zzegxVar.zzgo(iZzgn3);
                    break;
                case 32:
                    int iZzb4 = zzehj.zzb(zzegxVar, 32);
                    int length7 = this.zzxc == null ? 0 : this.zzxc.length;
                    int[] iArr7 = new int[iZzb4 + length7];
                    if (length7 != 0) {
                        System.arraycopy(this.zzxc, 0, iArr7, 0, length7);
                    }
                    while (length7 < iArr7.length - 1) {
                        iArr7[length7] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length7++;
                    }
                    iArr7[length7] = zzegxVar.zzccj();
                    this.zzxc = iArr7;
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    int iZzgn4 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position4 = zzegxVar.getPosition();
                    int i4 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i4++;
                    }
                    zzegxVar.zzhb(position4);
                    int length8 = this.zzxc == null ? 0 : this.zzxc.length;
                    int[] iArr8 = new int[i4 + length8];
                    if (length8 != 0) {
                        System.arraycopy(this.zzxc, 0, iArr8, 0, length8);
                    }
                    while (length8 < iArr8.length) {
                        iArr8[length8] = zzegxVar.zzccj();
                        length8++;
                    }
                    this.zzxc = iArr8;
                    zzegxVar.zzgo(iZzgn4);
                    break;
                case MotionEventCompat.AXIS_GENERIC_9 /* 40 */:
                    int iZzb5 = zzehj.zzb(zzegxVar, 40);
                    int length9 = this.zzxd == null ? 0 : this.zzxd.length;
                    int[] iArr9 = new int[iZzb5 + length9];
                    if (length9 != 0) {
                        System.arraycopy(this.zzxd, 0, iArr9, 0, length9);
                    }
                    while (length9 < iArr9.length - 1) {
                        iArr9[length9] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length9++;
                    }
                    iArr9[length9] = zzegxVar.zzccj();
                    this.zzxd = iArr9;
                    break;
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                    int iZzgn5 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position5 = zzegxVar.getPosition();
                    int i5 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i5++;
                    }
                    zzegxVar.zzhb(position5);
                    int length10 = this.zzxd == null ? 0 : this.zzxd.length;
                    int[] iArr10 = new int[i5 + length10];
                    if (length10 != 0) {
                        System.arraycopy(this.zzxd, 0, iArr10, 0, length10);
                    }
                    while (length10 < iArr10.length) {
                        iArr10[length10] = zzegxVar.zzccj();
                        length10++;
                    }
                    this.zzxd = iArr10;
                    zzegxVar.zzgo(iZzgn5);
                    break;
                case 48:
                    int iZzb6 = zzehj.zzb(zzegxVar, 48);
                    int length11 = this.zzxe == null ? 0 : this.zzxe.length;
                    int[] iArr11 = new int[iZzb6 + length11];
                    if (length11 != 0) {
                        System.arraycopy(this.zzxe, 0, iArr11, 0, length11);
                    }
                    while (length11 < iArr11.length - 1) {
                        iArr11[length11] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length11++;
                    }
                    iArr11[length11] = zzegxVar.zzccj();
                    this.zzxe = iArr11;
                    break;
                case 50:
                    int iZzgn6 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position6 = zzegxVar.getPosition();
                    int i6 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i6++;
                    }
                    zzegxVar.zzhb(position6);
                    int length12 = this.zzxe == null ? 0 : this.zzxe.length;
                    int[] iArr12 = new int[i6 + length12];
                    if (length12 != 0) {
                        System.arraycopy(this.zzxe, 0, iArr12, 0, length12);
                    }
                    while (length12 < iArr12.length) {
                        iArr12[length12] = zzegxVar.zzccj();
                        length12++;
                    }
                    this.zzxe = iArr12;
                    zzegxVar.zzgo(iZzgn6);
                    break;
                case 56:
                    int iZzb7 = zzehj.zzb(zzegxVar, 56);
                    int length13 = this.zzxf == null ? 0 : this.zzxf.length;
                    int[] iArr13 = new int[iZzb7 + length13];
                    if (length13 != 0) {
                        System.arraycopy(this.zzxf, 0, iArr13, 0, length13);
                    }
                    while (length13 < iArr13.length - 1) {
                        iArr13[length13] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length13++;
                    }
                    iArr13[length13] = zzegxVar.zzccj();
                    this.zzxf = iArr13;
                    break;
                case 58:
                    int iZzgn7 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position7 = zzegxVar.getPosition();
                    int i7 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i7++;
                    }
                    zzegxVar.zzhb(position7);
                    int length14 = this.zzxf == null ? 0 : this.zzxf.length;
                    int[] iArr14 = new int[i7 + length14];
                    if (length14 != 0) {
                        System.arraycopy(this.zzxf, 0, iArr14, 0, length14);
                    }
                    while (length14 < iArr14.length) {
                        iArr14[length14] = zzegxVar.zzccj();
                        length14++;
                    }
                    this.zzxf = iArr14;
                    zzegxVar.zzgo(iZzgn7);
                    break;
                case 64:
                    int iZzb8 = zzehj.zzb(zzegxVar, 64);
                    int length15 = this.zzxg == null ? 0 : this.zzxg.length;
                    int[] iArr15 = new int[iZzb8 + length15];
                    if (length15 != 0) {
                        System.arraycopy(this.zzxg, 0, iArr15, 0, length15);
                    }
                    while (length15 < iArr15.length - 1) {
                        iArr15[length15] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length15++;
                    }
                    iArr15[length15] = zzegxVar.zzccj();
                    this.zzxg = iArr15;
                    break;
                case 66:
                    int iZzgn8 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position8 = zzegxVar.getPosition();
                    int i8 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i8++;
                    }
                    zzegxVar.zzhb(position8);
                    int length16 = this.zzxg == null ? 0 : this.zzxg.length;
                    int[] iArr16 = new int[i8 + length16];
                    if (length16 != 0) {
                        System.arraycopy(this.zzxg, 0, iArr16, 0, length16);
                    }
                    while (length16 < iArr16.length) {
                        iArr16[length16] = zzegxVar.zzccj();
                        length16++;
                    }
                    this.zzxg = iArr16;
                    zzegxVar.zzgo(iZzgn8);
                    break;
                case 72:
                    int iZzb9 = zzehj.zzb(zzegxVar, 72);
                    int length17 = this.zzxh == null ? 0 : this.zzxh.length;
                    int[] iArr17 = new int[iZzb9 + length17];
                    if (length17 != 0) {
                        System.arraycopy(this.zzxh, 0, iArr17, 0, length17);
                    }
                    while (length17 < iArr17.length - 1) {
                        iArr17[length17] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length17++;
                    }
                    iArr17[length17] = zzegxVar.zzccj();
                    this.zzxh = iArr17;
                    break;
                case 74:
                    int iZzgn9 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position9 = zzegxVar.getPosition();
                    int i9 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i9++;
                    }
                    zzegxVar.zzhb(position9);
                    int length18 = this.zzxh == null ? 0 : this.zzxh.length;
                    int[] iArr18 = new int[i9 + length18];
                    if (length18 != 0) {
                        System.arraycopy(this.zzxh, 0, iArr18, 0, length18);
                    }
                    while (length18 < iArr18.length) {
                        iArr18[length18] = zzegxVar.zzccj();
                        length18++;
                    }
                    this.zzxh = iArr18;
                    zzegxVar.zzgo(iZzgn9);
                    break;
                case 80:
                    int iZzb10 = zzehj.zzb(zzegxVar, 80);
                    int length19 = this.zzxi == null ? 0 : this.zzxi.length;
                    int[] iArr19 = new int[iZzb10 + length19];
                    if (length19 != 0) {
                        System.arraycopy(this.zzxi, 0, iArr19, 0, length19);
                    }
                    while (length19 < iArr19.length - 1) {
                        iArr19[length19] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length19++;
                    }
                    iArr19[length19] = zzegxVar.zzccj();
                    this.zzxi = iArr19;
                    break;
                case 82:
                    int iZzgn10 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position10 = zzegxVar.getPosition();
                    int i10 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i10++;
                    }
                    zzegxVar.zzhb(position10);
                    int length20 = this.zzxi == null ? 0 : this.zzxi.length;
                    int[] iArr20 = new int[i10 + length20];
                    if (length20 != 0) {
                        System.arraycopy(this.zzxi, 0, iArr20, 0, length20);
                    }
                    while (length20 < iArr20.length) {
                        iArr20[length20] = zzegxVar.zzccj();
                        length20++;
                    }
                    this.zzxi = iArr20;
                    zzegxVar.zzgo(iZzgn10);
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
        if (this.zzwz != null && this.zzwz.length > 0) {
            for (int i = 0; i < this.zzwz.length; i++) {
                zzegyVar.zzv(1, this.zzwz[i]);
            }
        }
        if (this.zzxa != null && this.zzxa.length > 0) {
            for (int i2 = 0; i2 < this.zzxa.length; i2++) {
                zzegyVar.zzv(2, this.zzxa[i2]);
            }
        }
        if (this.zzxb != null && this.zzxb.length > 0) {
            for (int i3 = 0; i3 < this.zzxb.length; i3++) {
                zzegyVar.zzv(3, this.zzxb[i3]);
            }
        }
        if (this.zzxc != null && this.zzxc.length > 0) {
            for (int i4 = 0; i4 < this.zzxc.length; i4++) {
                zzegyVar.zzv(4, this.zzxc[i4]);
            }
        }
        if (this.zzxd != null && this.zzxd.length > 0) {
            for (int i5 = 0; i5 < this.zzxd.length; i5++) {
                zzegyVar.zzv(5, this.zzxd[i5]);
            }
        }
        if (this.zzxe != null && this.zzxe.length > 0) {
            for (int i6 = 0; i6 < this.zzxe.length; i6++) {
                zzegyVar.zzv(6, this.zzxe[i6]);
            }
        }
        if (this.zzxf != null && this.zzxf.length > 0) {
            for (int i7 = 0; i7 < this.zzxf.length; i7++) {
                zzegyVar.zzv(7, this.zzxf[i7]);
            }
        }
        if (this.zzxg != null && this.zzxg.length > 0) {
            for (int i8 = 0; i8 < this.zzxg.length; i8++) {
                zzegyVar.zzv(8, this.zzxg[i8]);
            }
        }
        if (this.zzxh != null && this.zzxh.length > 0) {
            for (int i9 = 0; i9 < this.zzxh.length; i9++) {
                zzegyVar.zzv(9, this.zzxh[i9]);
            }
        }
        if (this.zzxi != null && this.zzxi.length > 0) {
            for (int i10 = 0; i10 < this.zzxi.length; i10++) {
                zzegyVar.zzv(10, this.zzxi[i10]);
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int length;
        int iZzn = super.zzn();
        if (this.zzwz == null || this.zzwz.length <= 0) {
            length = iZzn;
        } else {
            int iZzhd = 0;
            for (int i = 0; i < this.zzwz.length; i++) {
                iZzhd += zzegy.zzhd(this.zzwz[i]);
            }
            length = iZzn + iZzhd + (this.zzwz.length * 1);
        }
        if (this.zzxa != null && this.zzxa.length > 0) {
            int iZzhd2 = 0;
            for (int i2 = 0; i2 < this.zzxa.length; i2++) {
                iZzhd2 += zzegy.zzhd(this.zzxa[i2]);
            }
            length = length + iZzhd2 + (this.zzxa.length * 1);
        }
        if (this.zzxb != null && this.zzxb.length > 0) {
            int iZzhd3 = 0;
            for (int i3 = 0; i3 < this.zzxb.length; i3++) {
                iZzhd3 += zzegy.zzhd(this.zzxb[i3]);
            }
            length = length + iZzhd3 + (this.zzxb.length * 1);
        }
        if (this.zzxc != null && this.zzxc.length > 0) {
            int iZzhd4 = 0;
            for (int i4 = 0; i4 < this.zzxc.length; i4++) {
                iZzhd4 += zzegy.zzhd(this.zzxc[i4]);
            }
            length = length + iZzhd4 + (this.zzxc.length * 1);
        }
        if (this.zzxd != null && this.zzxd.length > 0) {
            int iZzhd5 = 0;
            for (int i5 = 0; i5 < this.zzxd.length; i5++) {
                iZzhd5 += zzegy.zzhd(this.zzxd[i5]);
            }
            length = length + iZzhd5 + (this.zzxd.length * 1);
        }
        if (this.zzxe != null && this.zzxe.length > 0) {
            int iZzhd6 = 0;
            for (int i6 = 0; i6 < this.zzxe.length; i6++) {
                iZzhd6 += zzegy.zzhd(this.zzxe[i6]);
            }
            length = length + iZzhd6 + (this.zzxe.length * 1);
        }
        if (this.zzxf != null && this.zzxf.length > 0) {
            int iZzhd7 = 0;
            for (int i7 = 0; i7 < this.zzxf.length; i7++) {
                iZzhd7 += zzegy.zzhd(this.zzxf[i7]);
            }
            length = length + iZzhd7 + (this.zzxf.length * 1);
        }
        if (this.zzxg != null && this.zzxg.length > 0) {
            int iZzhd8 = 0;
            for (int i8 = 0; i8 < this.zzxg.length; i8++) {
                iZzhd8 += zzegy.zzhd(this.zzxg[i8]);
            }
            length = length + iZzhd8 + (this.zzxg.length * 1);
        }
        if (this.zzxh != null && this.zzxh.length > 0) {
            int iZzhd9 = 0;
            for (int i9 = 0; i9 < this.zzxh.length; i9++) {
                iZzhd9 += zzegy.zzhd(this.zzxh[i9]);
            }
            length = length + iZzhd9 + (this.zzxh.length * 1);
        }
        if (this.zzxi == null || this.zzxi.length <= 0) {
            return length;
        }
        int iZzhd10 = 0;
        for (int i10 = 0; i10 < this.zzxi.length; i10++) {
            iZzhd10 += zzegy.zzhd(this.zzxi[i10]);
        }
        return length + iZzhd10 + (this.zzxi.length * 1);
    }
}
