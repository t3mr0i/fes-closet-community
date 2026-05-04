package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgc extends zzeha<zzcgc> {
    public Integer zziye = null;
    public String zziyf = null;
    public Boolean zziyg = null;
    public String[] zziyh = zzehj.EMPTY_STRING_ARRAY;

    public zzcgc() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgc)) {
            return false;
        }
        zzcgc zzcgcVar = (zzcgc) obj;
        if (this.zziye == null) {
            if (zzcgcVar.zziye != null) {
                return false;
            }
        } else if (!this.zziye.equals(zzcgcVar.zziye)) {
            return false;
        }
        if (this.zziyf == null) {
            if (zzcgcVar.zziyf != null) {
                return false;
            }
        } else if (!this.zziyf.equals(zzcgcVar.zziyf)) {
            return false;
        }
        if (this.zziyg == null) {
            if (zzcgcVar.zziyg != null) {
                return false;
            }
        } else if (!this.zziyg.equals(zzcgcVar.zziyg)) {
            return false;
        }
        if (zzehe.equals(this.zziyh, zzcgcVar.zziyh)) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgcVar.zzngg == null || zzcgcVar.zzngg.isEmpty() : this.zzngg.equals(zzcgcVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((this.zziyg == null ? 0 : this.zziyg.hashCode()) + (((this.zziyf == null ? 0 : this.zziyf.hashCode()) + (((this.zziye == null ? 0 : this.zziye.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + zzehe.hashCode(this.zziyh)) * 31;
        if (this.zzngg != null && !this.zzngg.isEmpty()) {
            iHashCode = this.zzngg.hashCode();
        }
        return iHashCode2 + iHashCode;
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
                    int iZzccj = zzegxVar.zzccj();
                    switch (iZzccj) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            this.zziye = Integer.valueOf(iZzccj);
                            break;
                        default:
                            zzegxVar.zzhb(position);
                            zza(zzegxVar, iZzcby);
                            break;
                    }
                case 18:
                    this.zziyf = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zziyg = Boolean.valueOf(zzegxVar.zzcea());
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    int iZzb = zzehj.zzb(zzegxVar, 34);
                    int length = this.zziyh == null ? 0 : this.zziyh.length;
                    String[] strArr = new String[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zziyh, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = zzegxVar.readString();
                        zzegxVar.zzcby();
                        length++;
                    }
                    strArr[length] = zzegxVar.readString();
                    this.zziyh = strArr;
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
        if (this.zziye != null) {
            zzegyVar.zzv(1, this.zziye.intValue());
        }
        if (this.zziyf != null) {
            zzegyVar.zzl(2, this.zziyf);
        }
        if (this.zziyg != null) {
            zzegyVar.zzl(3, this.zziyg.booleanValue());
        }
        if (this.zziyh != null && this.zziyh.length > 0) {
            for (int i = 0; i < this.zziyh.length; i++) {
                String str = this.zziyh[i];
                if (str != null) {
                    zzegyVar.zzl(4, str);
                }
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzrk;
        int iZzn = super.zzn();
        if (this.zziye != null) {
            iZzn += zzegy.zzaf(1, this.zziye.intValue());
        }
        if (this.zziyf != null) {
            iZzn += zzegy.zzm(2, this.zziyf);
        }
        if (this.zziyg != null) {
            this.zziyg.booleanValue();
            iZzn += zzegy.zzgs(3) + 1;
        }
        if (this.zziyh == null || this.zziyh.length <= 0) {
            return iZzn;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < this.zziyh.length) {
            String str = this.zziyh[i];
            if (str != null) {
                i3++;
                iZzrk = zzegy.zzrk(str) + i2;
            } else {
                iZzrk = i2;
            }
            i++;
            i2 = iZzrk;
        }
        return iZzn + i2 + (i3 * 1);
    }
}
