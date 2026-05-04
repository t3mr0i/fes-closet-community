package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public interface zzbf {

    public static final class zza extends zzeha<zza> {
        public static final zzehb<zzbp, zza> zzxj = zzehb.zza(11, zza.class, 810);
        private static final zza[] zzxk = new zza[0];
        public int[] zzxl = zzehj.zzngu;
        public int[] zzxm = zzehj.zzngu;
        public int[] zzxn = zzehj.zzngu;
        private int zzxo = 0;
        public int[] zzxp = zzehj.zzngu;
        public int zzxq = 0;
        private int zzxr = 0;

        public zza() {
            this.zzngg = null;
            this.zzngp = -1;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            if (zzehe.equals(this.zzxl, zzaVar.zzxl) && zzehe.equals(this.zzxm, zzaVar.zzxm) && zzehe.equals(this.zzxn, zzaVar.zzxn) && this.zzxo == zzaVar.zzxo && zzehe.equals(this.zzxp, zzaVar.zzxp) && this.zzxq == zzaVar.zzxq && this.zzxr == zzaVar.zzxr) {
                return (this.zzngg == null || this.zzngg.isEmpty()) ? zzaVar.zzngg == null || zzaVar.zzngg.isEmpty() : this.zzngg.equals(zzaVar.zzngg);
            }
            return false;
        }

        public final int hashCode() {
            return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zzxl)) * 31) + zzehe.hashCode(this.zzxm)) * 31) + zzehe.hashCode(this.zzxn)) * 31) + this.zzxo) * 31) + zzehe.hashCode(this.zzxp)) * 31) + this.zzxq) * 31) + this.zzxr) * 31);
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
                        int length = this.zzxl == null ? 0 : this.zzxl.length;
                        int[] iArr = new int[iZzb + length];
                        if (length != 0) {
                            System.arraycopy(this.zzxl, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = zzegxVar.zzccj();
                            zzegxVar.zzcby();
                            length++;
                        }
                        iArr[length] = zzegxVar.zzccj();
                        this.zzxl = iArr;
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
                        int length2 = this.zzxl == null ? 0 : this.zzxl.length;
                        int[] iArr2 = new int[i + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.zzxl, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = zzegxVar.zzccj();
                            length2++;
                        }
                        this.zzxl = iArr2;
                        zzegxVar.zzgo(iZzgn);
                        break;
                    case 16:
                        int iZzb2 = zzehj.zzb(zzegxVar, 16);
                        int length3 = this.zzxm == null ? 0 : this.zzxm.length;
                        int[] iArr3 = new int[iZzb2 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.zzxm, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = zzegxVar.zzccj();
                            zzegxVar.zzcby();
                            length3++;
                        }
                        iArr3[length3] = zzegxVar.zzccj();
                        this.zzxm = iArr3;
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
                        int length4 = this.zzxm == null ? 0 : this.zzxm.length;
                        int[] iArr4 = new int[i2 + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.zzxm, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = zzegxVar.zzccj();
                            length4++;
                        }
                        this.zzxm = iArr4;
                        zzegxVar.zzgo(iZzgn2);
                        break;
                    case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                        int iZzb3 = zzehj.zzb(zzegxVar, 24);
                        int length5 = this.zzxn == null ? 0 : this.zzxn.length;
                        int[] iArr5 = new int[iZzb3 + length5];
                        if (length5 != 0) {
                            System.arraycopy(this.zzxn, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = zzegxVar.zzccj();
                            zzegxVar.zzcby();
                            length5++;
                        }
                        iArr5[length5] = zzegxVar.zzccj();
                        this.zzxn = iArr5;
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
                        int length6 = this.zzxn == null ? 0 : this.zzxn.length;
                        int[] iArr6 = new int[i3 + length6];
                        if (length6 != 0) {
                            System.arraycopy(this.zzxn, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = zzegxVar.zzccj();
                            length6++;
                        }
                        this.zzxn = iArr6;
                        zzegxVar.zzgo(iZzgn3);
                        break;
                    case 32:
                        this.zzxo = zzegxVar.zzccj();
                        break;
                    case MotionEventCompat.AXIS_GENERIC_9 /* 40 */:
                        int iZzb4 = zzehj.zzb(zzegxVar, 40);
                        int length7 = this.zzxp == null ? 0 : this.zzxp.length;
                        int[] iArr7 = new int[iZzb4 + length7];
                        if (length7 != 0) {
                            System.arraycopy(this.zzxp, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = zzegxVar.zzccj();
                            zzegxVar.zzcby();
                            length7++;
                        }
                        iArr7[length7] = zzegxVar.zzccj();
                        this.zzxp = iArr7;
                        break;
                    case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                        int iZzgn4 = zzegxVar.zzgn(zzegxVar.zzccj());
                        int position4 = zzegxVar.getPosition();
                        int i4 = 0;
                        while (zzegxVar.zzcef() > 0) {
                            zzegxVar.zzccj();
                            i4++;
                        }
                        zzegxVar.zzhb(position4);
                        int length8 = this.zzxp == null ? 0 : this.zzxp.length;
                        int[] iArr8 = new int[i4 + length8];
                        if (length8 != 0) {
                            System.arraycopy(this.zzxp, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = zzegxVar.zzccj();
                            length8++;
                        }
                        this.zzxp = iArr8;
                        zzegxVar.zzgo(iZzgn4);
                        break;
                    case 48:
                        this.zzxq = zzegxVar.zzccj();
                        break;
                    case 56:
                        this.zzxr = zzegxVar.zzccj();
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
            if (this.zzxl != null && this.zzxl.length > 0) {
                for (int i = 0; i < this.zzxl.length; i++) {
                    zzegyVar.zzv(1, this.zzxl[i]);
                }
            }
            if (this.zzxm != null && this.zzxm.length > 0) {
                for (int i2 = 0; i2 < this.zzxm.length; i2++) {
                    zzegyVar.zzv(2, this.zzxm[i2]);
                }
            }
            if (this.zzxn != null && this.zzxn.length > 0) {
                for (int i3 = 0; i3 < this.zzxn.length; i3++) {
                    zzegyVar.zzv(3, this.zzxn[i3]);
                }
            }
            if (this.zzxo != 0) {
                zzegyVar.zzv(4, this.zzxo);
            }
            if (this.zzxp != null && this.zzxp.length > 0) {
                for (int i4 = 0; i4 < this.zzxp.length; i4++) {
                    zzegyVar.zzv(5, this.zzxp[i4]);
                }
            }
            if (this.zzxq != 0) {
                zzegyVar.zzv(6, this.zzxq);
            }
            if (this.zzxr != 0) {
                zzegyVar.zzv(7, this.zzxr);
            }
            super.zza(zzegyVar);
        }

        @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
        protected final int zzn() {
            int iZzaf;
            int iZzn = super.zzn();
            if (this.zzxl == null || this.zzxl.length <= 0) {
                iZzaf = iZzn;
            } else {
                int iZzhd = 0;
                for (int i = 0; i < this.zzxl.length; i++) {
                    iZzhd += zzegy.zzhd(this.zzxl[i]);
                }
                iZzaf = iZzn + iZzhd + (this.zzxl.length * 1);
            }
            if (this.zzxm != null && this.zzxm.length > 0) {
                int iZzhd2 = 0;
                for (int i2 = 0; i2 < this.zzxm.length; i2++) {
                    iZzhd2 += zzegy.zzhd(this.zzxm[i2]);
                }
                iZzaf = iZzaf + iZzhd2 + (this.zzxm.length * 1);
            }
            if (this.zzxn != null && this.zzxn.length > 0) {
                int iZzhd3 = 0;
                for (int i3 = 0; i3 < this.zzxn.length; i3++) {
                    iZzhd3 += zzegy.zzhd(this.zzxn[i3]);
                }
                iZzaf = iZzaf + iZzhd3 + (this.zzxn.length * 1);
            }
            if (this.zzxo != 0) {
                iZzaf += zzegy.zzaf(4, this.zzxo);
            }
            if (this.zzxp != null && this.zzxp.length > 0) {
                int iZzhd4 = 0;
                for (int i4 = 0; i4 < this.zzxp.length; i4++) {
                    iZzhd4 += zzegy.zzhd(this.zzxp[i4]);
                }
                iZzaf = iZzaf + iZzhd4 + (this.zzxp.length * 1);
            }
            if (this.zzxq != 0) {
                iZzaf += zzegy.zzaf(6, this.zzxq);
            }
            return this.zzxr != 0 ? iZzaf + zzegy.zzaf(7, this.zzxr) : iZzaf;
        }
    }
}
