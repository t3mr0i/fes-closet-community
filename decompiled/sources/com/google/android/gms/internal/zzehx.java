package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzehx extends zzeha<zzehx> implements Cloneable {
    private String[] zznja = zzehj.EMPTY_STRING_ARRAY;
    private String[] zznjb = zzehj.EMPTY_STRING_ARRAY;
    private int[] zznjc = zzehj.zzngu;
    private long[] zznjd = zzehj.zzngv;
    private long[] zznje = zzehj.zzngv;

    public zzehx() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcet, reason: merged with bridge method [inline-methods] */
    public zzehx clone() {
        try {
            zzehx zzehxVar = (zzehx) super.clone();
            if (this.zznja != null && this.zznja.length > 0) {
                zzehxVar.zznja = (String[]) this.zznja.clone();
            }
            if (this.zznjb != null && this.zznjb.length > 0) {
                zzehxVar.zznjb = (String[]) this.zznjb.clone();
            }
            if (this.zznjc != null && this.zznjc.length > 0) {
                zzehxVar.zznjc = (int[]) this.zznjc.clone();
            }
            if (this.zznjd != null && this.zznjd.length > 0) {
                zzehxVar.zznjd = (long[]) this.zznjd.clone();
            }
            if (this.zznje != null && this.zznje.length > 0) {
                zzehxVar.zznje = (long[]) this.zznje.clone();
            }
            return zzehxVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzehx)) {
            return false;
        }
        zzehx zzehxVar = (zzehx) obj;
        if (zzehe.equals(this.zznja, zzehxVar.zznja) && zzehe.equals(this.zznjb, zzehxVar.zznjb) && zzehe.equals(this.zznjc, zzehxVar.zznjc) && zzehe.equals(this.zznjd, zzehxVar.zznjd) && zzehe.equals(this.zznje, zzehxVar.zznje)) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzehxVar.zzngg == null || zzehxVar.zzngg.isEmpty() : this.zzngg.equals(zzehxVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((((((((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zznja)) * 31) + zzehe.hashCode(this.zznjb)) * 31) + zzehe.hashCode(this.zznjc)) * 31) + zzehe.hashCode(this.zznjd)) * 31) + zzehe.hashCode(this.zznje)) * 31);
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
                    int length = this.zznja == null ? 0 : this.zznja.length;
                    String[] strArr = new String[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zznja, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzegxVar.readString();
                        zzegxVar.zzcby();
                        length++;
                    }
                    strArr[length] = zzegxVar.readString();
                    this.zznja = strArr;
                    break;
                case 18:
                    int iZzb2 = zzehj.zzb(zzegxVar, 18);
                    int length2 = this.zznjb == null ? 0 : this.zznjb.length;
                    String[] strArr2 = new String[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zznjb, 0, strArr2, 0, length2);
                    }
                    while (length2 < strArr2.length - 1) {
                        strArr2[length2] = zzegxVar.readString();
                        zzegxVar.zzcby();
                        length2++;
                    }
                    strArr2[length2] = zzegxVar.readString();
                    this.zznjb = strArr2;
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    int iZzb3 = zzehj.zzb(zzegxVar, 24);
                    int length3 = this.zznjc == null ? 0 : this.zznjc.length;
                    int[] iArr = new int[iZzb3 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zznjc, 0, iArr, 0, length3);
                    }
                    while (length3 < iArr.length - 1) {
                        iArr[length3] = zzegxVar.zzcdz();
                        zzegxVar.zzcby();
                        length3++;
                    }
                    iArr[length3] = zzegxVar.zzcdz();
                    this.zznjc = iArr;
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzgn = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position = zzegxVar.getPosition();
                    int i = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzcdz();
                        i++;
                    }
                    zzegxVar.zzhb(position);
                    int length4 = this.zznjc == null ? 0 : this.zznjc.length;
                    int[] iArr2 = new int[i + length4];
                    if (length4 != 0) {
                        System.arraycopy(this.zznjc, 0, iArr2, 0, length4);
                    }
                    while (length4 < iArr2.length) {
                        iArr2[length4] = zzegxVar.zzcdz();
                        length4++;
                    }
                    this.zznjc = iArr2;
                    zzegxVar.zzgo(iZzgn);
                    break;
                case 32:
                    int iZzb4 = zzehj.zzb(zzegxVar, 32);
                    int length5 = this.zznjd == null ? 0 : this.zznjd.length;
                    long[] jArr = new long[iZzb4 + length5];
                    if (length5 != 0) {
                        System.arraycopy(this.zznjd, 0, jArr, 0, length5);
                    }
                    while (length5 < jArr.length - 1) {
                        jArr[length5] = zzegxVar.zzcbz();
                        zzegxVar.zzcby();
                        length5++;
                    }
                    jArr[length5] = zzegxVar.zzcbz();
                    this.zznjd = jArr;
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    int iZzgn2 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position2 = zzegxVar.getPosition();
                    int i2 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzcbz();
                        i2++;
                    }
                    zzegxVar.zzhb(position2);
                    int length6 = this.zznjd == null ? 0 : this.zznjd.length;
                    long[] jArr2 = new long[i2 + length6];
                    if (length6 != 0) {
                        System.arraycopy(this.zznjd, 0, jArr2, 0, length6);
                    }
                    while (length6 < jArr2.length) {
                        jArr2[length6] = zzegxVar.zzcbz();
                        length6++;
                    }
                    this.zznjd = jArr2;
                    zzegxVar.zzgo(iZzgn2);
                    break;
                case MotionEventCompat.AXIS_GENERIC_9 /* 40 */:
                    int iZzb5 = zzehj.zzb(zzegxVar, 40);
                    int length7 = this.zznje == null ? 0 : this.zznje.length;
                    long[] jArr3 = new long[iZzb5 + length7];
                    if (length7 != 0) {
                        System.arraycopy(this.zznje, 0, jArr3, 0, length7);
                    }
                    while (length7 < jArr3.length - 1) {
                        jArr3[length7] = zzegxVar.zzcbz();
                        zzegxVar.zzcby();
                        length7++;
                    }
                    jArr3[length7] = zzegxVar.zzcbz();
                    this.zznje = jArr3;
                    break;
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                    int iZzgn3 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position3 = zzegxVar.getPosition();
                    int i3 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzcbz();
                        i3++;
                    }
                    zzegxVar.zzhb(position3);
                    int length8 = this.zznje == null ? 0 : this.zznje.length;
                    long[] jArr4 = new long[i3 + length8];
                    if (length8 != 0) {
                        System.arraycopy(this.zznje, 0, jArr4, 0, length8);
                    }
                    while (length8 < jArr4.length) {
                        jArr4[length8] = zzegxVar.zzcbz();
                        length8++;
                    }
                    this.zznje = jArr4;
                    zzegxVar.zzgo(iZzgn3);
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
        if (this.zznja != null && this.zznja.length > 0) {
            for (int i = 0; i < this.zznja.length; i++) {
                String str = this.zznja[i];
                if (str != null) {
                    zzegyVar.zzl(1, str);
                }
            }
        }
        if (this.zznjb != null && this.zznjb.length > 0) {
            for (int i2 = 0; i2 < this.zznjb.length; i2++) {
                String str2 = this.zznjb[i2];
                if (str2 != null) {
                    zzegyVar.zzl(2, str2);
                }
            }
        }
        if (this.zznjc != null && this.zznjc.length > 0) {
            for (int i3 = 0; i3 < this.zznjc.length; i3++) {
                zzegyVar.zzv(3, this.zznjc[i3]);
            }
        }
        if (this.zznjd != null && this.zznjd.length > 0) {
            for (int i4 = 0; i4 < this.zznjd.length; i4++) {
                zzegyVar.zze(4, this.zznjd[i4]);
            }
        }
        if (this.zznje != null && this.zznje.length > 0) {
            for (int i5 = 0; i5 < this.zznje.length; i5++) {
                zzegyVar.zze(5, this.zznje[i5]);
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha
    /* renamed from: zzceh */
    public final /* synthetic */ zzeha clone() throws CloneNotSupportedException {
        return (zzehx) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcei */
    public final /* synthetic */ zzehg clone() throws CloneNotSupportedException {
        return (zzehx) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int length;
        int iZzn = super.zzn();
        if (this.zznja == null || this.zznja.length <= 0) {
            length = iZzn;
        } else {
            int iZzrk = 0;
            int i = 0;
            for (int i2 = 0; i2 < this.zznja.length; i2++) {
                String str = this.zznja[i2];
                if (str != null) {
                    i++;
                    iZzrk += zzegy.zzrk(str);
                }
            }
            length = iZzn + iZzrk + (i * 1);
        }
        if (this.zznjb != null && this.zznjb.length > 0) {
            int iZzrk2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < this.zznjb.length; i4++) {
                String str2 = this.zznjb[i4];
                if (str2 != null) {
                    i3++;
                    iZzrk2 += zzegy.zzrk(str2);
                }
            }
            length = length + iZzrk2 + (i3 * 1);
        }
        if (this.zznjc != null && this.zznjc.length > 0) {
            int iZzhd = 0;
            for (int i5 = 0; i5 < this.zznjc.length; i5++) {
                iZzhd += zzegy.zzhd(this.zznjc[i5]);
            }
            length = length + iZzhd + (this.zznjc.length * 1);
        }
        if (this.zznjd != null && this.zznjd.length > 0) {
            int iZzcq = 0;
            for (int i6 = 0; i6 < this.zznjd.length; i6++) {
                iZzcq += zzegy.zzcq(this.zznjd[i6]);
            }
            length = length + iZzcq + (this.zznjd.length * 1);
        }
        if (this.zznje == null || this.zznje.length <= 0) {
            return length;
        }
        int iZzcq2 = 0;
        for (int i7 = 0; i7 < this.zznje.length; i7++) {
            iZzcq2 += zzegy.zzcq(this.zznje[i7]);
        }
        return length + iZzcq2 + (this.zznje.length * 1);
    }
}
