package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcfx extends zzeha<zzcfx> {
    private static volatile zzcfx[] zzixh;
    public Integer zzixi = null;
    public zzcgb[] zzixj = zzcgb.zzbad();
    public zzcfy[] zzixk = zzcfy.zzbab();

    public zzcfx() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcfx[] zzbaa() {
        if (zzixh == null) {
            synchronized (zzehe.zzngo) {
                if (zzixh == null) {
                    zzixh = new zzcfx[0];
                }
            }
        }
        return zzixh;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcfx)) {
            return false;
        }
        zzcfx zzcfxVar = (zzcfx) obj;
        if (this.zzixi == null) {
            if (zzcfxVar.zzixi != null) {
                return false;
            }
        } else if (!this.zzixi.equals(zzcfxVar.zzixi)) {
            return false;
        }
        if (zzehe.equals(this.zzixj, zzcfxVar.zzixj) && zzehe.equals(this.zzixk, zzcfxVar.zzixk)) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcfxVar.zzngg == null || zzcfxVar.zzngg.isEmpty() : this.zzngg.equals(zzcfxVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((((this.zzixi == null ? 0 : this.zzixi.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzehe.hashCode(this.zzixj)) * 31) + zzehe.hashCode(this.zzixk)) * 31;
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
                    this.zzixi = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 18:
                    int iZzb = zzehj.zzb(zzegxVar, 18);
                    int length = this.zzixj == null ? 0 : this.zzixj.length;
                    zzcgb[] zzcgbVarArr = new zzcgb[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzixj, 0, zzcgbVarArr, 0, length);
                    }
                    while (length < zzcgbVarArr.length - 1) {
                        zzcgbVarArr[length] = new zzcgb();
                        zzegxVar.zza(zzcgbVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzcgbVarArr[length] = new zzcgb();
                    zzegxVar.zza(zzcgbVarArr[length]);
                    this.zzixj = zzcgbVarArr;
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzb2 = zzehj.zzb(zzegxVar, 26);
                    int length2 = this.zzixk == null ? 0 : this.zzixk.length;
                    zzcfy[] zzcfyVarArr = new zzcfy[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzixk, 0, zzcfyVarArr, 0, length2);
                    }
                    while (length2 < zzcfyVarArr.length - 1) {
                        zzcfyVarArr[length2] = new zzcfy();
                        zzegxVar.zza(zzcfyVarArr[length2]);
                        zzegxVar.zzcby();
                        length2++;
                    }
                    zzcfyVarArr[length2] = new zzcfy();
                    zzegxVar.zza(zzcfyVarArr[length2]);
                    this.zzixk = zzcfyVarArr;
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
        if (this.zzixj != null && this.zzixj.length > 0) {
            for (int i = 0; i < this.zzixj.length; i++) {
                zzcgb zzcgbVar = this.zzixj[i];
                if (zzcgbVar != null) {
                    zzegyVar.zza(2, zzcgbVar);
                }
            }
        }
        if (this.zzixk != null && this.zzixk.length > 0) {
            for (int i2 = 0; i2 < this.zzixk.length; i2++) {
                zzcfy zzcfyVar = this.zzixk[i2];
                if (zzcfyVar != null) {
                    zzegyVar.zza(3, zzcfyVar);
                }
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzixi != null) {
            iZzn += zzegy.zzaf(1, this.zzixi.intValue());
        }
        if (this.zzixj != null && this.zzixj.length > 0) {
            int iZzb = iZzn;
            for (int i = 0; i < this.zzixj.length; i++) {
                zzcgb zzcgbVar = this.zzixj[i];
                if (zzcgbVar != null) {
                    iZzb += zzegy.zzb(2, zzcgbVar);
                }
            }
            iZzn = iZzb;
        }
        if (this.zzixk != null && this.zzixk.length > 0) {
            for (int i2 = 0; i2 < this.zzixk.length; i2++) {
                zzcfy zzcfyVar = this.zzixk[i2];
                if (zzcfyVar != null) {
                    iZzn += zzegy.zzb(3, zzcfyVar);
                }
            }
        }
        return iZzn;
    }
}
