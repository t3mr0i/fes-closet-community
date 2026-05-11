package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcge extends zzeha<zzcge> {
    public Long zziyl = null;
    public String zzilt = null;
    private Integer zziym = null;
    public zzcgf[] zziyn = zzcgf.zzbaf();
    public zzcgd[] zziyo = zzcgd.zzbae();
    public zzcfx[] zziyp = zzcfx.zzbaa();

    public zzcge() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcge)) {
            return false;
        }
        zzcge zzcgeVar = (zzcge) obj;
        if (this.zziyl == null) {
            if (zzcgeVar.zziyl != null) {
                return false;
            }
        } else if (!this.zziyl.equals(zzcgeVar.zziyl)) {
            return false;
        }
        if (this.zzilt == null) {
            if (zzcgeVar.zzilt != null) {
                return false;
            }
        } else if (!this.zzilt.equals(zzcgeVar.zzilt)) {
            return false;
        }
        if (this.zziym == null) {
            if (zzcgeVar.zziym != null) {
                return false;
            }
        } else if (!this.zziym.equals(zzcgeVar.zziym)) {
            return false;
        }
        if (zzehe.equals(this.zziyn, zzcgeVar.zziyn) && zzehe.equals(this.zziyo, zzcgeVar.zziyo) && zzehe.equals(this.zziyp, zzcgeVar.zziyp)) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgeVar.zzngg == null || zzcgeVar.zzngg.isEmpty() : this.zzngg.equals(zzcgeVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((((((((this.zziym == null ? 0 : this.zziym.hashCode()) + (((this.zzilt == null ? 0 : this.zzilt.hashCode()) + (((this.zziyl == null ? 0 : this.zziyl.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + zzehe.hashCode(this.zziyn)) * 31) + zzehe.hashCode(this.zziyo)) * 31) + zzehe.hashCode(this.zziyp)) * 31;
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
                    this.zziyl = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 18:
                    this.zzilt = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zziym = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    int iZzb = zzehj.zzb(zzegxVar, 34);
                    int length = this.zziyn == null ? 0 : this.zziyn.length;
                    zzcgf[] zzcgfVarArr = new zzcgf[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zziyn, 0, zzcgfVarArr, 0, length);
                    }
                    while (length < zzcgfVarArr.length - 1) {
                        zzcgfVarArr[length] = new zzcgf();
                        zzegxVar.zza(zzcgfVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzcgfVarArr[length] = new zzcgf();
                    zzegxVar.zza(zzcgfVarArr[length]);
                    this.zziyn = zzcgfVarArr;
                    break;
                case MotionEventCompat.AXIS_GENERIC_11 /* 42 */:
                    int iZzb2 = zzehj.zzb(zzegxVar, 42);
                    int length2 = this.zziyo == null ? 0 : this.zziyo.length;
                    zzcgd[] zzcgdVarArr = new zzcgd[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zziyo, 0, zzcgdVarArr, 0, length2);
                    }
                    while (length2 < zzcgdVarArr.length - 1) {
                        zzcgdVarArr[length2] = new zzcgd();
                        zzegxVar.zza(zzcgdVarArr[length2]);
                        zzegxVar.zzcby();
                        length2++;
                    }
                    zzcgdVarArr[length2] = new zzcgd();
                    zzegxVar.zza(zzcgdVarArr[length2]);
                    this.zziyo = zzcgdVarArr;
                    break;
                case 50:
                    int iZzb3 = zzehj.zzb(zzegxVar, 50);
                    int length3 = this.zziyp == null ? 0 : this.zziyp.length;
                    zzcfx[] zzcfxVarArr = new zzcfx[iZzb3 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zziyp, 0, zzcfxVarArr, 0, length3);
                    }
                    while (length3 < zzcfxVarArr.length - 1) {
                        zzcfxVarArr[length3] = new zzcfx();
                        zzegxVar.zza(zzcfxVarArr[length3]);
                        zzegxVar.zzcby();
                        length3++;
                    }
                    zzcfxVarArr[length3] = new zzcfx();
                    zzegxVar.zza(zzcfxVarArr[length3]);
                    this.zziyp = zzcfxVarArr;
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
        if (this.zziyl != null) {
            zzegyVar.zze(1, this.zziyl.longValue());
        }
        if (this.zzilt != null) {
            zzegyVar.zzl(2, this.zzilt);
        }
        if (this.zziym != null) {
            zzegyVar.zzv(3, this.zziym.intValue());
        }
        if (this.zziyn != null && this.zziyn.length > 0) {
            for (int i = 0; i < this.zziyn.length; i++) {
                zzcgf zzcgfVar = this.zziyn[i];
                if (zzcgfVar != null) {
                    zzegyVar.zza(4, zzcgfVar);
                }
            }
        }
        if (this.zziyo != null && this.zziyo.length > 0) {
            for (int i2 = 0; i2 < this.zziyo.length; i2++) {
                zzcgd zzcgdVar = this.zziyo[i2];
                if (zzcgdVar != null) {
                    zzegyVar.zza(5, zzcgdVar);
                }
            }
        }
        if (this.zziyp != null && this.zziyp.length > 0) {
            for (int i3 = 0; i3 < this.zziyp.length; i3++) {
                zzcfx zzcfxVar = this.zziyp[i3];
                if (zzcfxVar != null) {
                    zzegyVar.zza(6, zzcfxVar);
                }
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zziyl != null) {
            iZzn += zzegy.zzg(1, this.zziyl.longValue());
        }
        if (this.zzilt != null) {
            iZzn += zzegy.zzm(2, this.zzilt);
        }
        if (this.zziym != null) {
            iZzn += zzegy.zzaf(3, this.zziym.intValue());
        }
        if (this.zziyn != null && this.zziyn.length > 0) {
            int iZzb = iZzn;
            for (int i = 0; i < this.zziyn.length; i++) {
                zzcgf zzcgfVar = this.zziyn[i];
                if (zzcgfVar != null) {
                    iZzb += zzegy.zzb(4, zzcgfVar);
                }
            }
            iZzn = iZzb;
        }
        if (this.zziyo != null && this.zziyo.length > 0) {
            int iZzb2 = iZzn;
            for (int i2 = 0; i2 < this.zziyo.length; i2++) {
                zzcgd zzcgdVar = this.zziyo[i2];
                if (zzcgdVar != null) {
                    iZzb2 += zzegy.zzb(5, zzcgdVar);
                }
            }
            iZzn = iZzb2;
        }
        if (this.zziyp != null && this.zziyp.length > 0) {
            for (int i3 = 0; i3 < this.zziyp.length; i3++) {
                zzcfx zzcfxVar = this.zziyp[i3];
                if (zzcfxVar != null) {
                    iZzn += zzegy.zzb(6, zzcfxVar);
                }
            }
        }
        return iZzn;
    }
}
