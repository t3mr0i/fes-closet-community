package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbo extends zzeha<zzbo> {
    public zzbn[] zzxv = zzbn.zzt();
    public zzbl zzxw = null;
    public String zzxx = "";

    public zzbo() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbo)) {
            return false;
        }
        zzbo zzboVar = (zzbo) obj;
        if (!zzehe.equals(this.zzxv, zzboVar.zzxv)) {
            return false;
        }
        if (this.zzxw == null) {
            if (zzboVar.zzxw != null) {
                return false;
            }
        } else if (!this.zzxw.equals(zzboVar.zzxw)) {
            return false;
        }
        if (this.zzxx == null) {
            if (zzboVar.zzxx != null) {
                return false;
            }
        } else if (!this.zzxx.equals(zzboVar.zzxx)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzboVar.zzngg == null || zzboVar.zzngg.isEmpty() : this.zzngg.equals(zzboVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zzxv);
        zzbl zzblVar = this.zzxw;
        int iHashCode3 = ((this.zzxx == null ? 0 : this.zzxx.hashCode()) + (((zzblVar == null ? 0 : zzblVar.hashCode()) + (iHashCode2 * 31)) * 31)) * 31;
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
                    int length = this.zzxv == null ? 0 : this.zzxv.length;
                    zzbn[] zzbnVarArr = new zzbn[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzxv, 0, zzbnVarArr, 0, length);
                    }
                    while (length < zzbnVarArr.length - 1) {
                        zzbnVarArr[length] = new zzbn();
                        zzegxVar.zza(zzbnVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzbnVarArr[length] = new zzbn();
                    zzegxVar.zza(zzbnVarArr[length]);
                    this.zzxv = zzbnVarArr;
                    break;
                case 18:
                    if (this.zzxw == null) {
                        this.zzxw = new zzbl();
                    }
                    zzegxVar.zza(this.zzxw);
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    this.zzxx = zzegxVar.readString();
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
        if (this.zzxv != null && this.zzxv.length > 0) {
            for (int i = 0; i < this.zzxv.length; i++) {
                zzbn zzbnVar = this.zzxv[i];
                if (zzbnVar != null) {
                    zzegyVar.zza(1, zzbnVar);
                }
            }
        }
        if (this.zzxw != null) {
            zzegyVar.zza(2, this.zzxw);
        }
        if (this.zzxx != null && !this.zzxx.equals("")) {
            zzegyVar.zzl(3, this.zzxx);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzxv != null && this.zzxv.length > 0) {
            for (int i = 0; i < this.zzxv.length; i++) {
                zzbn zzbnVar = this.zzxv[i];
                if (zzbnVar != null) {
                    iZzn += zzegy.zzb(1, zzbnVar);
                }
            }
        }
        if (this.zzxw != null) {
            iZzn += zzegy.zzb(2, this.zzxw);
        }
        return (this.zzxx == null || this.zzxx.equals("")) ? iZzn : iZzn + zzegy.zzm(3, this.zzxx);
    }
}
