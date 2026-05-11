package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbg extends zzeha<zzbg> {
    private int level = 1;
    private int zzvs = 0;
    private int zzvt = 0;

    public zzbg() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbg)) {
            return false;
        }
        zzbg zzbgVar = (zzbg) obj;
        if (this.level == zzbgVar.level && this.zzvs == zzbgVar.zzvs && this.zzvt == zzbgVar.zzvt) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzbgVar.zzngg == null || zzbgVar.zzngg.isEmpty() : this.zzngg.equals(zzbgVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + this.level) * 31) + this.zzvs) * 31) + this.zzvt) * 31);
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
                        case 1:
                        case 2:
                        case 3:
                            this.level = iZzccj;
                            break;
                        default:
                            zzegxVar.zzhb(position);
                            zza(zzegxVar, iZzcby);
                            break;
                    }
                case 16:
                    this.zzvs = zzegxVar.zzccj();
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zzvt = zzegxVar.zzccj();
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
        if (this.level != 1) {
            zzegyVar.zzv(1, this.level);
        }
        if (this.zzvs != 0) {
            zzegyVar.zzv(2, this.zzvs);
        }
        if (this.zzvt != 0) {
            zzegyVar.zzv(3, this.zzvt);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.level != 1) {
            iZzn += zzegy.zzaf(1, this.level);
        }
        if (this.zzvs != 0) {
            iZzn += zzegy.zzaf(2, this.zzvs);
        }
        return this.zzvt != 0 ? iZzn + zzegy.zzaf(3, this.zzvt) : iZzn;
    }
}
