package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgb extends zzeha<zzcgb> {
    private static volatile zzcgb[] zziyb;
    public Integer zzixm = null;
    public String zziyc = null;
    public zzcfz zziyd = null;

    public zzcgb() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcgb[] zzbad() {
        if (zziyb == null) {
            synchronized (zzehe.zzngo) {
                if (zziyb == null) {
                    zziyb = new zzcgb[0];
                }
            }
        }
        return zziyb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgb)) {
            return false;
        }
        zzcgb zzcgbVar = (zzcgb) obj;
        if (this.zzixm == null) {
            if (zzcgbVar.zzixm != null) {
                return false;
            }
        } else if (!this.zzixm.equals(zzcgbVar.zzixm)) {
            return false;
        }
        if (this.zziyc == null) {
            if (zzcgbVar.zziyc != null) {
                return false;
            }
        } else if (!this.zziyc.equals(zzcgbVar.zziyc)) {
            return false;
        }
        if (this.zziyd == null) {
            if (zzcgbVar.zziyd != null) {
                return false;
            }
        } else if (!this.zziyd.equals(zzcgbVar.zziyd)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgbVar.zzngg == null || zzcgbVar.zzngg.isEmpty() : this.zzngg.equals(zzcgbVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (this.zziyc == null ? 0 : this.zziyc.hashCode()) + (((this.zzixm == null ? 0 : this.zzixm.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31);
        zzcfz zzcfzVar = this.zziyd;
        int iHashCode3 = ((zzcfzVar == null ? 0 : zzcfzVar.hashCode()) + (iHashCode2 * 31)) * 31;
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
                case 8:
                    this.zzixm = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 18:
                    this.zziyc = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    if (this.zziyd == null) {
                        this.zziyd = new zzcfz();
                    }
                    zzegxVar.zza(this.zziyd);
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
        if (this.zzixm != null) {
            zzegyVar.zzv(1, this.zzixm.intValue());
        }
        if (this.zziyc != null) {
            zzegyVar.zzl(2, this.zziyc);
        }
        if (this.zziyd != null) {
            zzegyVar.zza(3, this.zziyd);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzixm != null) {
            iZzn += zzegy.zzaf(1, this.zzixm.intValue());
        }
        if (this.zziyc != null) {
            iZzn += zzegy.zzm(2, this.zziyc);
        }
        return this.zziyd != null ? iZzn + zzegy.zzb(3, this.zziyd) : iZzn;
    }
}
