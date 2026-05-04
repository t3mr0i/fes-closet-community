package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbi extends zzeha<zzbi> {
    private static volatile zzbi[] zzvz;
    public String key = "";
    public long zzwa = 0;
    public long zzwb = 2147483647L;
    public boolean zzwc = false;
    public long zzwd = 0;

    public zzbi() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzbi[] zzq() {
        if (zzvz == null) {
            synchronized (zzehe.zzngo) {
                if (zzvz == null) {
                    zzvz = new zzbi[0];
                }
            }
        }
        return zzvz;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbi)) {
            return false;
        }
        zzbi zzbiVar = (zzbi) obj;
        if (this.key == null) {
            if (zzbiVar.key != null) {
                return false;
            }
        } else if (!this.key.equals(zzbiVar.key)) {
            return false;
        }
        if (this.zzwa == zzbiVar.zzwa && this.zzwb == zzbiVar.zzwb && this.zzwc == zzbiVar.zzwc && this.zzwd == zzbiVar.zzwd) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzbiVar.zzngg == null || zzbiVar.zzngg.isEmpty() : this.zzngg.equals(zzbiVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((this.zzwc ? 1231 : 1237) + (((((((this.key == null ? 0 : this.key.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + ((int) (this.zzwa ^ (this.zzwa >>> 32)))) * 31) + ((int) (this.zzwb ^ (this.zzwb >>> 32)))) * 31)) * 31) + ((int) (this.zzwd ^ (this.zzwd >>> 32)))) * 31;
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
                case 10:
                    this.key = zzegxVar.readString();
                    break;
                case 16:
                    this.zzwa = zzegxVar.zzcec();
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zzwb = zzegxVar.zzcec();
                    break;
                case 32:
                    this.zzwc = zzegxVar.zzcea();
                    break;
                case MotionEventCompat.AXIS_GENERIC_9 /* 40 */:
                    this.zzwd = zzegxVar.zzcec();
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
        if (this.key != null && !this.key.equals("")) {
            zzegyVar.zzl(1, this.key);
        }
        if (this.zzwa != 0) {
            zzegyVar.zze(2, this.zzwa);
        }
        if (this.zzwb != 2147483647L) {
            zzegyVar.zze(3, this.zzwb);
        }
        if (this.zzwc) {
            zzegyVar.zzl(4, this.zzwc);
        }
        if (this.zzwd != 0) {
            zzegyVar.zze(5, this.zzwd);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.key != null && !this.key.equals("")) {
            iZzn += zzegy.zzm(1, this.key);
        }
        if (this.zzwa != 0) {
            iZzn += zzegy.zzg(2, this.zzwa);
        }
        if (this.zzwb != 2147483647L) {
            iZzn += zzegy.zzg(3, this.zzwb);
        }
        if (this.zzwc) {
            iZzn += zzegy.zzgs(4) + 1;
        }
        return this.zzwd != 0 ? iZzn + zzegy.zzg(5, this.zzwd) : iZzn;
    }
}
