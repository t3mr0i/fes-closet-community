package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgm extends zzeha<zzcgm> {
    private static volatile zzcgm[] zzjah;
    public Long zzjai = null;
    public String name = null;
    public String zzfwn = null;
    public Long zziza = null;
    private Float zzixa = null;
    public Double zzixb = null;

    public zzcgm() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcgm[] zzbak() {
        if (zzjah == null) {
            synchronized (zzehe.zzngo) {
                if (zzjah == null) {
                    zzjah = new zzcgm[0];
                }
            }
        }
        return zzjah;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgm)) {
            return false;
        }
        zzcgm zzcgmVar = (zzcgm) obj;
        if (this.zzjai == null) {
            if (zzcgmVar.zzjai != null) {
                return false;
            }
        } else if (!this.zzjai.equals(zzcgmVar.zzjai)) {
            return false;
        }
        if (this.name == null) {
            if (zzcgmVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzcgmVar.name)) {
            return false;
        }
        if (this.zzfwn == null) {
            if (zzcgmVar.zzfwn != null) {
                return false;
            }
        } else if (!this.zzfwn.equals(zzcgmVar.zzfwn)) {
            return false;
        }
        if (this.zziza == null) {
            if (zzcgmVar.zziza != null) {
                return false;
            }
        } else if (!this.zziza.equals(zzcgmVar.zziza)) {
            return false;
        }
        if (this.zzixa == null) {
            if (zzcgmVar.zzixa != null) {
                return false;
            }
        } else if (!this.zzixa.equals(zzcgmVar.zzixa)) {
            return false;
        }
        if (this.zzixb == null) {
            if (zzcgmVar.zzixb != null) {
                return false;
            }
        } else if (!this.zzixb.equals(zzcgmVar.zzixb)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgmVar.zzngg == null || zzcgmVar.zzngg.isEmpty() : this.zzngg.equals(zzcgmVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.zzixb == null ? 0 : this.zzixb.hashCode()) + (((this.zzixa == null ? 0 : this.zzixa.hashCode()) + (((this.zziza == null ? 0 : this.zziza.hashCode()) + (((this.zzfwn == null ? 0 : this.zzfwn.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + (((this.zzjai == null ? 0 : this.zzjai.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    this.zzjai = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 18:
                    this.name = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    this.zzfwn = zzegxVar.readString();
                    break;
                case 32:
                    this.zziza = Long.valueOf(zzegxVar.zzcec());
                    break;
                case MotionEventCompat.AXIS_GENERIC_14 /* 45 */:
                    this.zzixa = Float.valueOf(Float.intBitsToFloat(zzegxVar.zzced()));
                    break;
                case 49:
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
        if (this.zzjai != null) {
            zzegyVar.zze(1, this.zzjai.longValue());
        }
        if (this.name != null) {
            zzegyVar.zzl(2, this.name);
        }
        if (this.zzfwn != null) {
            zzegyVar.zzl(3, this.zzfwn);
        }
        if (this.zziza != null) {
            zzegyVar.zze(4, this.zziza.longValue());
        }
        if (this.zzixa != null) {
            zzegyVar.zzc(5, this.zzixa.floatValue());
        }
        if (this.zzixb != null) {
            zzegyVar.zza(6, this.zzixb.doubleValue());
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzjai != null) {
            iZzn += zzegy.zzg(1, this.zzjai.longValue());
        }
        if (this.name != null) {
            iZzn += zzegy.zzm(2, this.name);
        }
        if (this.zzfwn != null) {
            iZzn += zzegy.zzm(3, this.zzfwn);
        }
        if (this.zziza != null) {
            iZzn += zzegy.zzg(4, this.zziza.longValue());
        }
        if (this.zzixa != null) {
            this.zzixa.floatValue();
            iZzn += zzegy.zzgs(5) + 4;
        }
        if (this.zzixb == null) {
            return iZzn;
        }
        this.zzixb.doubleValue();
        return iZzn + zzegy.zzgs(6) + 8;
    }
}
