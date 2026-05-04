package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzdbm extends zzeha<zzdbm> {
    public long zzkfj = 0;
    public zzbl zzxw = null;
    public zzbo zzkfk = null;

    public zzdbm() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdbm)) {
            return false;
        }
        zzdbm zzdbmVar = (zzdbm) obj;
        if (this.zzkfj != zzdbmVar.zzkfj) {
            return false;
        }
        if (this.zzxw == null) {
            if (zzdbmVar.zzxw != null) {
                return false;
            }
        } else if (!this.zzxw.equals(zzdbmVar.zzxw)) {
            return false;
        }
        if (this.zzkfk == null) {
            if (zzdbmVar.zzkfk != null) {
                return false;
            }
        } else if (!this.zzkfk.equals(zzdbmVar.zzkfk)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzdbmVar.zzngg == null || zzdbmVar.zzngg.isEmpty() : this.zzngg.equals(zzdbmVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzkfj ^ (this.zzkfj >>> 32)));
        zzbl zzblVar = this.zzxw;
        int i = iHashCode2 * 31;
        int iHashCode3 = zzblVar == null ? 0 : zzblVar.hashCode();
        zzbo zzboVar = this.zzkfk;
        int iHashCode4 = ((zzboVar == null ? 0 : zzboVar.hashCode()) + ((iHashCode3 + i) * 31)) * 31;
        if (this.zzngg != null && !this.zzngg.isEmpty()) {
            iHashCode = this.zzngg.hashCode();
        }
        return iHashCode4 + iHashCode;
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 8:
                    this.zzkfj = zzegxVar.zzcec();
                    break;
                case 18:
                    if (this.zzxw == null) {
                        this.zzxw = new zzbl();
                    }
                    zzegxVar.zza(this.zzxw);
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    if (this.zzkfk == null) {
                        this.zzkfk = new zzbo();
                    }
                    zzegxVar.zza(this.zzkfk);
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
        zzegyVar.zze(1, this.zzkfj);
        if (this.zzxw != null) {
            zzegyVar.zza(2, this.zzxw);
        }
        if (this.zzkfk != null) {
            zzegyVar.zza(3, this.zzkfk);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn() + zzegy.zzg(1, this.zzkfj);
        if (this.zzxw != null) {
            iZzn += zzegy.zzb(2, this.zzxw);
        }
        return this.zzkfk != null ? iZzn + zzegy.zzb(3, this.zzkfk) : iZzn;
    }
}
