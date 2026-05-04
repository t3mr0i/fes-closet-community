package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzbj extends zzeha<zzbj> {
    public zzbp[] zzwe = zzbp.zzu();
    public zzbp[] zzwf = zzbp.zzu();
    public zzbi[] zzwg = zzbi.zzq();

    public zzbj() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbj)) {
            return false;
        }
        zzbj zzbjVar = (zzbj) obj;
        if (zzehe.equals(this.zzwe, zzbjVar.zzwe) && zzehe.equals(this.zzwf, zzbjVar.zzwf) && zzehe.equals(this.zzwg, zzbjVar.zzwg)) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzbjVar.zzngg == null || zzbjVar.zzngg.isEmpty() : this.zzngg.equals(zzbjVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        return ((this.zzngg == null || this.zzngg.isEmpty()) ? 0 : this.zzngg.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + zzehe.hashCode(this.zzwe)) * 31) + zzehe.hashCode(this.zzwf)) * 31) + zzehe.hashCode(this.zzwg)) * 31);
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
                    int length = this.zzwe == null ? 0 : this.zzwe.length;
                    zzbp[] zzbpVarArr = new zzbp[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzwe, 0, zzbpVarArr, 0, length);
                    }
                    while (length < zzbpVarArr.length - 1) {
                        zzbpVarArr[length] = new zzbp();
                        zzegxVar.zza(zzbpVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzbpVarArr[length] = new zzbp();
                    zzegxVar.zza(zzbpVarArr[length]);
                    this.zzwe = zzbpVarArr;
                    break;
                case 18:
                    int iZzb2 = zzehj.zzb(zzegxVar, 18);
                    int length2 = this.zzwf == null ? 0 : this.zzwf.length;
                    zzbp[] zzbpVarArr2 = new zzbp[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzwf, 0, zzbpVarArr2, 0, length2);
                    }
                    while (length2 < zzbpVarArr2.length - 1) {
                        zzbpVarArr2[length2] = new zzbp();
                        zzegxVar.zza(zzbpVarArr2[length2]);
                        zzegxVar.zzcby();
                        length2++;
                    }
                    zzbpVarArr2[length2] = new zzbp();
                    zzegxVar.zza(zzbpVarArr2[length2]);
                    this.zzwf = zzbpVarArr2;
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzb3 = zzehj.zzb(zzegxVar, 26);
                    int length3 = this.zzwg == null ? 0 : this.zzwg.length;
                    zzbi[] zzbiVarArr = new zzbi[iZzb3 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzwg, 0, zzbiVarArr, 0, length3);
                    }
                    while (length3 < zzbiVarArr.length - 1) {
                        zzbiVarArr[length3] = new zzbi();
                        zzegxVar.zza(zzbiVarArr[length3]);
                        zzegxVar.zzcby();
                        length3++;
                    }
                    zzbiVarArr[length3] = new zzbi();
                    zzegxVar.zza(zzbiVarArr[length3]);
                    this.zzwg = zzbiVarArr;
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
        if (this.zzwe != null && this.zzwe.length > 0) {
            for (int i = 0; i < this.zzwe.length; i++) {
                zzbp zzbpVar = this.zzwe[i];
                if (zzbpVar != null) {
                    zzegyVar.zza(1, zzbpVar);
                }
            }
        }
        if (this.zzwf != null && this.zzwf.length > 0) {
            for (int i2 = 0; i2 < this.zzwf.length; i2++) {
                zzbp zzbpVar2 = this.zzwf[i2];
                if (zzbpVar2 != null) {
                    zzegyVar.zza(2, zzbpVar2);
                }
            }
        }
        if (this.zzwg != null && this.zzwg.length > 0) {
            for (int i3 = 0; i3 < this.zzwg.length; i3++) {
                zzbi zzbiVar = this.zzwg[i3];
                if (zzbiVar != null) {
                    zzegyVar.zza(3, zzbiVar);
                }
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzwe != null && this.zzwe.length > 0) {
            for (int i = 0; i < this.zzwe.length; i++) {
                zzbp zzbpVar = this.zzwe[i];
                if (zzbpVar != null) {
                    iZzn += zzegy.zzb(1, zzbpVar);
                }
            }
        }
        if (this.zzwf != null && this.zzwf.length > 0) {
            for (int i2 = 0; i2 < this.zzwf.length; i2++) {
                zzbp zzbpVar2 = this.zzwf[i2];
                if (zzbpVar2 != null) {
                    iZzn += zzegy.zzb(2, zzbpVar2);
                }
            }
        }
        if (this.zzwg != null && this.zzwg.length > 0) {
            for (int i3 = 0; i3 < this.zzwg.length; i3++) {
                zzbi zzbiVar = this.zzwg[i3];
                if (zzbiVar != null) {
                    iZzn += zzegy.zzb(3, zzbiVar);
                }
            }
        }
        return iZzn;
    }
}
