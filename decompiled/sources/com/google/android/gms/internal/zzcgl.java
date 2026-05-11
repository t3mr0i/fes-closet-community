package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgl extends zzeha<zzcgl> {
    public long[] zzjaf = zzehj.zzngv;
    public long[] zzjag = zzehj.zzngv;

    public zzcgl() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgl)) {
            return false;
        }
        zzcgl zzcglVar = (zzcgl) obj;
        if (zzehe.equals(this.zzjaf, zzcglVar.zzjaf) && zzehe.equals(this.zzjag, zzcglVar.zzjag)) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcglVar.zzngg == null || zzcglVar.zzngg.isEmpty() : this.zzngg.equals(zzcglVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zzjaf)) * 31) + zzehe.hashCode(this.zzjag)) * 31);
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
                    int length = this.zzjaf == null ? 0 : this.zzjaf.length;
                    long[] jArr = new long[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzjaf, 0, jArr, 0, length);
                    }
                    while (length < jArr.length - 1) {
                        jArr[length] = zzegxVar.zzcec();
                        zzegxVar.zzcby();
                        length++;
                    }
                    jArr[length] = zzegxVar.zzcec();
                    this.zzjaf = jArr;
                    break;
                case 10:
                    int iZzgn = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position = zzegxVar.getPosition();
                    int i = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzcec();
                        i++;
                    }
                    zzegxVar.zzhb(position);
                    int length2 = this.zzjaf == null ? 0 : this.zzjaf.length;
                    long[] jArr2 = new long[i + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzjaf, 0, jArr2, 0, length2);
                    }
                    while (length2 < jArr2.length) {
                        jArr2[length2] = zzegxVar.zzcec();
                        length2++;
                    }
                    this.zzjaf = jArr2;
                    zzegxVar.zzgo(iZzgn);
                    break;
                case 16:
                    int iZzb2 = zzehj.zzb(zzegxVar, 16);
                    int length3 = this.zzjag == null ? 0 : this.zzjag.length;
                    long[] jArr3 = new long[iZzb2 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzjag, 0, jArr3, 0, length3);
                    }
                    while (length3 < jArr3.length - 1) {
                        jArr3[length3] = zzegxVar.zzcec();
                        zzegxVar.zzcby();
                        length3++;
                    }
                    jArr3[length3] = zzegxVar.zzcec();
                    this.zzjag = jArr3;
                    break;
                case 18:
                    int iZzgn2 = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position2 = zzegxVar.getPosition();
                    int i2 = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzcec();
                        i2++;
                    }
                    zzegxVar.zzhb(position2);
                    int length4 = this.zzjag == null ? 0 : this.zzjag.length;
                    long[] jArr4 = new long[i2 + length4];
                    if (length4 != 0) {
                        System.arraycopy(this.zzjag, 0, jArr4, 0, length4);
                    }
                    while (length4 < jArr4.length) {
                        jArr4[length4] = zzegxVar.zzcec();
                        length4++;
                    }
                    this.zzjag = jArr4;
                    zzegxVar.zzgo(iZzgn2);
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
        if (this.zzjaf != null && this.zzjaf.length > 0) {
            for (int i = 0; i < this.zzjaf.length; i++) {
                zzegyVar.zza(1, this.zzjaf[i]);
            }
        }
        if (this.zzjag != null && this.zzjag.length > 0) {
            for (int i2 = 0; i2 < this.zzjag.length; i2++) {
                zzegyVar.zza(2, this.zzjag[i2]);
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int length;
        int iZzn = super.zzn();
        if (this.zzjaf == null || this.zzjaf.length <= 0) {
            length = iZzn;
        } else {
            int iZzcq = 0;
            for (int i = 0; i < this.zzjaf.length; i++) {
                iZzcq += zzegy.zzcq(this.zzjaf[i]);
            }
            length = iZzn + iZzcq + (this.zzjaf.length * 1);
        }
        if (this.zzjag == null || this.zzjag.length <= 0) {
            return length;
        }
        int iZzcq2 = 0;
        for (int i2 = 0; i2 < this.zzjag.length; i2++) {
            iZzcq2 += zzegy.zzcq(this.zzjag[i2]);
        }
        return length + iZzcq2 + (this.zzjag.length * 1);
    }
}
