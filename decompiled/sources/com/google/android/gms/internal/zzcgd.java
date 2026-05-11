package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgd extends zzeha<zzcgd> {
    private static volatile zzcgd[] zziyi;
    public String name = null;
    public Boolean zziyj = null;
    public Boolean zziyk = null;

    public zzcgd() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcgd[] zzbae() {
        if (zziyi == null) {
            synchronized (zzehe.zzngo) {
                if (zziyi == null) {
                    zziyi = new zzcgd[0];
                }
            }
        }
        return zziyi;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgd)) {
            return false;
        }
        zzcgd zzcgdVar = (zzcgd) obj;
        if (this.name == null) {
            if (zzcgdVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzcgdVar.name)) {
            return false;
        }
        if (this.zziyj == null) {
            if (zzcgdVar.zziyj != null) {
                return false;
            }
        } else if (!this.zziyj.equals(zzcgdVar.zziyj)) {
            return false;
        }
        if (this.zziyk == null) {
            if (zzcgdVar.zziyk != null) {
                return false;
            }
        } else if (!this.zziyk.equals(zzcgdVar.zziyk)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgdVar.zzngg == null || zzcgdVar.zzngg.isEmpty() : this.zzngg.equals(zzcgdVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.zziyk == null ? 0 : this.zziyk.hashCode()) + (((this.zziyj == null ? 0 : this.zziyj.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31;
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
                case 16:
                    this.zziyj = Boolean.valueOf(zzegxVar.zzcea());
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zziyk = Boolean.valueOf(zzegxVar.zzcea());
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
        if (this.zziyj != null) {
            zzegyVar.zzl(2, this.zziyj.booleanValue());
        }
        if (this.zziyk != null) {
            zzegyVar.zzl(3, this.zziyk.booleanValue());
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.name != null) {
            iZzn += zzegy.zzm(1, this.name);
        }
        if (this.zziyj != null) {
            this.zziyj.booleanValue();
            iZzn += zzegy.zzgs(2) + 1;
        }
        if (this.zziyk == null) {
            return iZzn;
        }
        this.zziyk.booleanValue();
        return iZzn + zzegy.zzgs(3) + 1;
    }
}
