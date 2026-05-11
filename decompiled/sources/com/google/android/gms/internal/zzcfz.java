package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcfz extends zzeha<zzcfz> {
    private static volatile zzcfz[] zzixr;
    public zzcgc zzixs = null;
    public zzcga zzixt = null;
    public Boolean zzixu = null;
    public String zzixv = null;

    public zzcfz() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcfz[] zzbac() {
        if (zzixr == null) {
            synchronized (zzehe.zzngo) {
                if (zzixr == null) {
                    zzixr = new zzcfz[0];
                }
            }
        }
        return zzixr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcfz)) {
            return false;
        }
        zzcfz zzcfzVar = (zzcfz) obj;
        if (this.zzixs == null) {
            if (zzcfzVar.zzixs != null) {
                return false;
            }
        } else if (!this.zzixs.equals(zzcfzVar.zzixs)) {
            return false;
        }
        if (this.zzixt == null) {
            if (zzcfzVar.zzixt != null) {
                return false;
            }
        } else if (!this.zzixt.equals(zzcfzVar.zzixt)) {
            return false;
        }
        if (this.zzixu == null) {
            if (zzcfzVar.zzixu != null) {
                return false;
            }
        } else if (!this.zzixu.equals(zzcfzVar.zzixu)) {
            return false;
        }
        if (this.zzixv == null) {
            if (zzcfzVar.zzixv != null) {
                return false;
            }
        } else if (!this.zzixv.equals(zzcfzVar.zzixv)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcfzVar.zzngg == null || zzcfzVar.zzngg.isEmpty() : this.zzngg.equals(zzcfzVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = getClass().getName().hashCode() + 527;
        zzcgc zzcgcVar = this.zzixs;
        int i = iHashCode2 * 31;
        int iHashCode3 = zzcgcVar == null ? 0 : zzcgcVar.hashCode();
        zzcga zzcgaVar = this.zzixt;
        int iHashCode4 = ((this.zzixv == null ? 0 : this.zzixv.hashCode()) + (((this.zzixu == null ? 0 : this.zzixu.hashCode()) + (((zzcgaVar == null ? 0 : zzcgaVar.hashCode()) + ((iHashCode3 + i) * 31)) * 31)) * 31)) * 31;
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
                case 10:
                    if (this.zzixs == null) {
                        this.zzixs = new zzcgc();
                    }
                    zzegxVar.zza(this.zzixs);
                    break;
                case 18:
                    if (this.zzixt == null) {
                        this.zzixt = new zzcga();
                    }
                    zzegxVar.zza(this.zzixt);
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zzixu = Boolean.valueOf(zzegxVar.zzcea());
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    this.zzixv = zzegxVar.readString();
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
        if (this.zzixs != null) {
            zzegyVar.zza(1, this.zzixs);
        }
        if (this.zzixt != null) {
            zzegyVar.zza(2, this.zzixt);
        }
        if (this.zzixu != null) {
            zzegyVar.zzl(3, this.zzixu.booleanValue());
        }
        if (this.zzixv != null) {
            zzegyVar.zzl(4, this.zzixv);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzixs != null) {
            iZzn += zzegy.zzb(1, this.zzixs);
        }
        if (this.zzixt != null) {
            iZzn += zzegy.zzb(2, this.zzixt);
        }
        if (this.zzixu != null) {
            this.zzixu.booleanValue();
            iZzn += zzegy.zzgs(3) + 1;
        }
        return this.zzixv != null ? iZzn + zzegy.zzm(4, this.zzixv) : iZzn;
    }
}
