package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbh extends zzeha<zzbh> {
    private static volatile zzbh[] zzvu;
    public int[] zzvv = zzehj.zzngu;
    private int zzvw = 0;
    private int name = 0;
    private boolean zzvx = false;
    private boolean zzvy = false;

    public zzbh() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzbh[] zzp() {
        if (zzvu == null) {
            synchronized (zzehe.zzngo) {
                if (zzvu == null) {
                    zzvu = new zzbh[0];
                }
            }
        }
        return zzvu;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbh)) {
            return false;
        }
        zzbh zzbhVar = (zzbh) obj;
        if (zzehe.equals(this.zzvv, zzbhVar.zzvv) && this.zzvw == zzbhVar.zzvw && this.name == zzbhVar.name && this.zzvx == zzbhVar.zzvx && this.zzvy == zzbhVar.zzvy) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzbhVar.zzngg == null || zzbhVar.zzngg.isEmpty() : this.zzngg.equals(zzbhVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + (((((this.zzvx ? 1231 : 1237) + ((((((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zzvv)) * 31) + this.zzvw) * 31) + this.name) * 31)) * 31) + (this.zzvy ? 1231 : 1237)) * 31);
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 8:
                    this.zzvy = zzegxVar.zzcea();
                    break;
                case 16:
                    this.zzvw = zzegxVar.zzccj();
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    int iZzb = zzehj.zzb(zzegxVar, 24);
                    int length = this.zzvv == null ? 0 : this.zzvv.length;
                    int[] iArr = new int[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzvv, 0, iArr, 0, length);
                    }
                    while (length < iArr.length - 1) {
                        iArr[length] = zzegxVar.zzccj();
                        zzegxVar.zzcby();
                        length++;
                    }
                    iArr[length] = zzegxVar.zzccj();
                    this.zzvv = iArr;
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzgn = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position = zzegxVar.getPosition();
                    int i = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzccj();
                        i++;
                    }
                    zzegxVar.zzhb(position);
                    int length2 = this.zzvv == null ? 0 : this.zzvv.length;
                    int[] iArr2 = new int[i + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzvv, 0, iArr2, 0, length2);
                    }
                    while (length2 < iArr2.length) {
                        iArr2[length2] = zzegxVar.zzccj();
                        length2++;
                    }
                    this.zzvv = iArr2;
                    zzegxVar.zzgo(iZzgn);
                    break;
                case 32:
                    this.name = zzegxVar.zzccj();
                    break;
                case 48:
                    this.zzvx = zzegxVar.zzcea();
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
        if (this.zzvy) {
            zzegyVar.zzl(1, this.zzvy);
        }
        zzegyVar.zzv(2, this.zzvw);
        if (this.zzvv != null && this.zzvv.length > 0) {
            for (int i = 0; i < this.zzvv.length; i++) {
                zzegyVar.zzv(3, this.zzvv[i]);
            }
        }
        if (this.name != 0) {
            zzegyVar.zzv(4, this.name);
        }
        if (this.zzvx) {
            zzegyVar.zzl(6, this.zzvx);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzaf;
        int i;
        int iZzhd = 0;
        int iZzn = super.zzn();
        if (this.zzvy) {
            iZzn += zzegy.zzgs(1) + 1;
        }
        int iZzaf2 = iZzn + zzegy.zzaf(2, this.zzvw);
        if (this.zzvv == null || this.zzvv.length <= 0) {
            iZzaf = iZzaf2;
        } else {
            int i2 = 0;
            while (true) {
                i = iZzhd;
                if (i2 >= this.zzvv.length) {
                    break;
                }
                iZzhd = zzegy.zzhd(this.zzvv[i2]) + i;
                i2++;
            }
            iZzaf = iZzaf2 + i + (this.zzvv.length * 1);
        }
        if (this.name != 0) {
            iZzaf += zzegy.zzaf(4, this.name);
        }
        return this.zzvx ? iZzaf + zzegy.zzgs(6) + 1 : iZzaf;
    }
}
