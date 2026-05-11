package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgi extends zzeha<zzcgi> {
    private static volatile zzcgi[] zziyz;
    public String name = null;
    public String zzfwn = null;
    public Long zziza = null;
    private Float zzixa = null;
    public Double zzixb = null;

    public zzcgi() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcgi[] zzbai() {
        if (zziyz == null) {
            synchronized (zzehe.zzngo) {
                if (zziyz == null) {
                    zziyz = new zzcgi[0];
                }
            }
        }
        return zziyz;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgi)) {
            return false;
        }
        zzcgi zzcgiVar = (zzcgi) obj;
        if (this.name == null) {
            if (zzcgiVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzcgiVar.name)) {
            return false;
        }
        if (this.zzfwn == null) {
            if (zzcgiVar.zzfwn != null) {
                return false;
            }
        } else if (!this.zzfwn.equals(zzcgiVar.zzfwn)) {
            return false;
        }
        if (this.zziza == null) {
            if (zzcgiVar.zziza != null) {
                return false;
            }
        } else if (!this.zziza.equals(zzcgiVar.zziza)) {
            return false;
        }
        if (this.zzixa == null) {
            if (zzcgiVar.zzixa != null) {
                return false;
            }
        } else if (!this.zzixa.equals(zzcgiVar.zzixa)) {
            return false;
        }
        if (this.zzixb == null) {
            if (zzcgiVar.zzixb != null) {
                return false;
            }
        } else if (!this.zzixb.equals(zzcgiVar.zzixb)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgiVar.zzngg == null || zzcgiVar.zzngg.isEmpty() : this.zzngg.equals(zzcgiVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.zzixb == null ? 0 : this.zzixb.hashCode()) + (((this.zzixa == null ? 0 : this.zzixa.hashCode()) + (((this.zziza == null ? 0 : this.zziza.hashCode()) + (((this.zzfwn == null ? 0 : this.zzfwn.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    this.name = zzegxVar.readString();
                    break;
                case 18:
                    this.zzfwn = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zziza = Long.valueOf(zzegxVar.zzcec());
                    break;
                case MotionEventCompat.AXIS_GENERIC_6 /* 37 */:
                    this.zzixa = Float.valueOf(Float.intBitsToFloat(zzegxVar.zzced()));
                    break;
                case MotionEventCompat.AXIS_GENERIC_10 /* 41 */:
                    this.zzixb = Double.valueOf(Double.longBitsToDouble(zzegxVar.zzcee()));
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
        if (this.name != null) {
            zzegyVar.zzl(1, this.name);
        }
        if (this.zzfwn != null) {
            zzegyVar.zzl(2, this.zzfwn);
        }
        if (this.zziza != null) {
            zzegyVar.zze(3, this.zziza.longValue());
        }
        if (this.zzixa != null) {
            zzegyVar.zzc(4, this.zzixa.floatValue());
        }
        if (this.zzixb != null) {
            zzegyVar.zza(5, this.zzixb.doubleValue());
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.name != null) {
            iZzn += zzegy.zzm(1, this.name);
        }
        if (this.zzfwn != null) {
            iZzn += zzegy.zzm(2, this.zzfwn);
        }
        if (this.zziza != null) {
            iZzn += zzegy.zzg(3, this.zziza.longValue());
        }
        if (this.zzixa != null) {
            this.zzixa.floatValue();
            iZzn += zzegy.zzgs(4) + 4;
        }
        if (this.zzixb == null) {
            return iZzn;
        }
        this.zzixb.doubleValue();
        return iZzn + zzegy.zzgs(5) + 8;
    }
}
