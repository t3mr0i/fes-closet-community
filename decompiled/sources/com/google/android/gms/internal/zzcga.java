package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcga extends zzeha<zzcga> {
    public Integer zzixw = null;
    public Boolean zzixx = null;
    public String zzixy = null;
    public String zzixz = null;
    public String zziya = null;

    public zzcga() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcga)) {
            return false;
        }
        zzcga zzcgaVar = (zzcga) obj;
        if (this.zzixw == null) {
            if (zzcgaVar.zzixw != null) {
                return false;
            }
        } else if (!this.zzixw.equals(zzcgaVar.zzixw)) {
            return false;
        }
        if (this.zzixx == null) {
            if (zzcgaVar.zzixx != null) {
                return false;
            }
        } else if (!this.zzixx.equals(zzcgaVar.zzixx)) {
            return false;
        }
        if (this.zzixy == null) {
            if (zzcgaVar.zzixy != null) {
                return false;
            }
        } else if (!this.zzixy.equals(zzcgaVar.zzixy)) {
            return false;
        }
        if (this.zzixz == null) {
            if (zzcgaVar.zzixz != null) {
                return false;
            }
        } else if (!this.zzixz.equals(zzcgaVar.zzixz)) {
            return false;
        }
        if (this.zziya == null) {
            if (zzcgaVar.zziya != null) {
                return false;
            }
        } else if (!this.zziya.equals(zzcgaVar.zziya)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgaVar.zzngg == null || zzcgaVar.zzngg.isEmpty() : this.zzngg.equals(zzcgaVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.zziya == null ? 0 : this.zziya.hashCode()) + (((this.zzixz == null ? 0 : this.zzixz.hashCode()) + (((this.zzixy == null ? 0 : this.zzixy.hashCode()) + (((this.zzixx == null ? 0 : this.zzixx.hashCode()) + (((this.zzixw == null ? 0 : this.zzixw.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                            this.zzixw = Integer.valueOf(iZzccj);
                            break;
                        default:
                            zzegxVar.zzhb(position);
                            zza(zzegxVar, iZzcby);
                            break;
                    }
                case 16:
                    this.zzixx = Boolean.valueOf(zzegxVar.zzcea());
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    this.zzixy = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    this.zzixz = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                    this.zziya = zzegxVar.readString();
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
        if (this.zzixw != null) {
            zzegyVar.zzv(1, this.zzixw.intValue());
        }
        if (this.zzixx != null) {
            zzegyVar.zzl(2, this.zzixx.booleanValue());
        }
        if (this.zzixy != null) {
            zzegyVar.zzl(3, this.zzixy);
        }
        if (this.zzixz != null) {
            zzegyVar.zzl(4, this.zzixz);
        }
        if (this.zziya != null) {
            zzegyVar.zzl(5, this.zziya);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzixw != null) {
            iZzn += zzegy.zzaf(1, this.zzixw.intValue());
        }
        if (this.zzixx != null) {
            this.zzixx.booleanValue();
            iZzn += zzegy.zzgs(2) + 1;
        }
        if (this.zzixy != null) {
            iZzn += zzegy.zzm(3, this.zzixy);
        }
        if (this.zzixz != null) {
            iZzn += zzegy.zzm(4, this.zzixz);
        }
        return this.zziya != null ? iZzn + zzegy.zzm(5, this.zziya) : iZzn;
    }
}
