package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgh extends zzeha<zzcgh> {
    private static volatile zzcgh[] zziyv;
    public zzcgi[] zziyw = zzcgi.zzbai();
    public String name = null;
    public Long zziyx = null;
    public Long zziyy = null;
    public Integer count = null;

    public zzcgh() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcgh[] zzbah() {
        if (zziyv == null) {
            synchronized (zzehe.zzngo) {
                if (zziyv == null) {
                    zziyv = new zzcgh[0];
                }
            }
        }
        return zziyv;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgh)) {
            return false;
        }
        zzcgh zzcghVar = (zzcgh) obj;
        if (!zzehe.equals(this.zziyw, zzcghVar.zziyw)) {
            return false;
        }
        if (this.name == null) {
            if (zzcghVar.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzcghVar.name)) {
            return false;
        }
        if (this.zziyx == null) {
            if (zzcghVar.zziyx != null) {
                return false;
            }
        } else if (!this.zziyx.equals(zzcghVar.zziyx)) {
            return false;
        }
        if (this.zziyy == null) {
            if (zzcghVar.zziyy != null) {
                return false;
            }
        } else if (!this.zziyy.equals(zzcghVar.zziyy)) {
            return false;
        }
        if (this.count == null) {
            if (zzcghVar.count != null) {
                return false;
            }
        } else if (!this.count.equals(zzcghVar.count)) {
            return false;
        }
        return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcghVar.zzngg == null || zzcghVar.zzngg.isEmpty() : this.zzngg.equals(zzcghVar.zzngg);
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.count == null ? 0 : this.count.hashCode()) + (((this.zziyy == null ? 0 : this.zziyy.hashCode()) + (((this.zziyx == null ? 0 : this.zziyx.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zziyw)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    int iZzb = zzehj.zzb(zzegxVar, 10);
                    int length = this.zziyw == null ? 0 : this.zziyw.length;
                    zzcgi[] zzcgiVarArr = new zzcgi[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zziyw, 0, zzcgiVarArr, 0, length);
                    }
                    while (length < zzcgiVarArr.length - 1) {
                        zzcgiVarArr[length] = new zzcgi();
                        zzegxVar.zza(zzcgiVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzcgiVarArr[length] = new zzcgi();
                    zzegxVar.zza(zzcgiVarArr[length]);
                    this.zziyw = zzcgiVarArr;
                    break;
                case 18:
                    this.name = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zziyx = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 32:
                    this.zziyy = Long.valueOf(zzegxVar.zzcec());
                    break;
                case MotionEventCompat.AXIS_GENERIC_9 /* 40 */:
                    this.count = Integer.valueOf(zzegxVar.zzccj());
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
        if (this.zziyw != null && this.zziyw.length > 0) {
            for (int i = 0; i < this.zziyw.length; i++) {
                zzcgi zzcgiVar = this.zziyw[i];
                if (zzcgiVar != null) {
                    zzegyVar.zza(1, zzcgiVar);
                }
            }
        }
        if (this.name != null) {
            zzegyVar.zzl(2, this.name);
        }
        if (this.zziyx != null) {
            zzegyVar.zze(3, this.zziyx.longValue());
        }
        if (this.zziyy != null) {
            zzegyVar.zze(4, this.zziyy.longValue());
        }
        if (this.count != null) {
            zzegyVar.zzv(5, this.count.intValue());
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zziyw != null && this.zziyw.length > 0) {
            for (int i = 0; i < this.zziyw.length; i++) {
                zzcgi zzcgiVar = this.zziyw[i];
                if (zzcgiVar != null) {
                    iZzn += zzegy.zzb(1, zzcgiVar);
                }
            }
        }
        if (this.name != null) {
            iZzn += zzegy.zzm(2, this.name);
        }
        if (this.zziyx != null) {
            iZzn += zzegy.zzg(3, this.zziyx.longValue());
        }
        if (this.zziyy != null) {
            iZzn += zzegy.zzg(4, this.zziyy.longValue());
        }
        return this.count != null ? iZzn + zzegy.zzaf(5, this.count.intValue()) : iZzn;
    }
}
