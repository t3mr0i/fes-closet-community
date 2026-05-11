package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgg extends zzeha<zzcgg> {
    private static volatile zzcgg[] zziyr;
    public Integer zzixi = null;
    public zzcgl zziys = null;
    public zzcgl zziyt = null;
    public Boolean zziyu = null;

    public zzcgg() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcgg[] zzbag() {
        if (zziyr == null) {
            synchronized (zzehe.zzngo) {
                if (zziyr == null) {
                    zziyr = new zzcgg[0];
                }
            }
        }
        return zziyr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgg)) {
            return false;
        }
        zzcgg zzcggVar = (zzcgg) obj;
        if (this.zzixi == null) {
            if (zzcggVar.zzixi != null) {
                return false;
            }
        } else if (!this.zzixi.equals(zzcggVar.zzixi)) {
            return false;
        }
        if (this.zziys == null) {
            if (zzcggVar.zziys != null) {
                return false;
            }
        } else if (!this.zziys.equals(zzcggVar.zziys)) {
            return false;
        }
        if (this.zziyt == null) {
            if (zzcggVar.zziyt != null) {
                return false;
            }
        } else if (!this.zziyt.equals(zzcggVar.zziyt)) {
            return false;
        }
        if (this.zziyu == null) {
            if (zzcggVar.zziyu != null) {
                return false;
            }
        } else if (!this.zziyu.equals(zzcggVar.zziyu)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcggVar.zzngg == null || zzcggVar.zzngg.isEmpty() : this.zzngg.equals(zzcggVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (this.zzixi == null ? 0 : this.zzixi.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        zzcgl zzcglVar = this.zziys;
        int i = iHashCode2 * 31;
        int iHashCode3 = zzcglVar == null ? 0 : zzcglVar.hashCode();
        zzcgl zzcglVar2 = this.zziyt;
        int iHashCode4 = ((this.zziyu == null ? 0 : this.zziyu.hashCode()) + (((zzcglVar2 == null ? 0 : zzcglVar2.hashCode()) + ((iHashCode3 + i) * 31)) * 31)) * 31;
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
                    this.zzixi = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 18:
                    if (this.zziys == null) {
                        this.zziys = new zzcgl();
                    }
                    zzegxVar.zza(this.zziys);
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    if (this.zziyt == null) {
                        this.zziyt = new zzcgl();
                    }
                    zzegxVar.zza(this.zziyt);
                    break;
                case 32:
                    this.zziyu = Boolean.valueOf(zzegxVar.zzcea());
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
        if (this.zzixi != null) {
            zzegyVar.zzv(1, this.zzixi.intValue());
        }
        if (this.zziys != null) {
            zzegyVar.zza(2, this.zziys);
        }
        if (this.zziyt != null) {
            zzegyVar.zza(3, this.zziyt);
        }
        if (this.zziyu != null) {
            zzegyVar.zzl(4, this.zziyu.booleanValue());
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzixi != null) {
            iZzn += zzegy.zzaf(1, this.zzixi.intValue());
        }
        if (this.zziys != null) {
            iZzn += zzegy.zzb(2, this.zziys);
        }
        if (this.zziyt != null) {
            iZzn += zzegy.zzb(3, this.zziyt);
        }
        if (this.zziyu == null) {
            return iZzn;
        }
        this.zziyu.booleanValue();
        return iZzn + zzegy.zzgs(4) + 1;
    }
}
