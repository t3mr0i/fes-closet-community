package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzeia extends zzeha<zzeia> implements Cloneable {
    public long zznjk = 0;
    public long zznjl = 0;
    private long zznjm = 0;
    private String tag = "";
    private int zznjn = 0;
    private int zzaju = 0;
    private boolean zzlwn = false;
    private zzeib[] zznjo = zzeib.zzcex();
    private byte[] zznjp = zzehj.zznha;
    private zzehy zznjq = null;
    public byte[] zznjr = zzehj.zznha;
    private String zznjs = "";
    private String zznjt = "";
    private zzehx zznju = null;
    private String zznjv = "";
    public long zznjw = 180000;
    private zzehz zznjx = null;
    public byte[] zznjy = zzehj.zznha;
    private String zznjz = "";
    private int zznka = 0;
    private int[] zznkb = zzehj.zzngu;
    private long zznkc = 0;
    private zzeic zzmqy = null;

    public zzeia() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcew, reason: merged with bridge method [inline-methods] */
    public final zzeia clone() {
        try {
            zzeia zzeiaVar = (zzeia) super.clone();
            if (this.zznjo != null && this.zznjo.length > 0) {
                zzeiaVar.zznjo = new zzeib[this.zznjo.length];
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= this.zznjo.length) {
                        break;
                    }
                    if (this.zznjo[i2] != null) {
                        zzeiaVar.zznjo[i2] = (zzeib) this.zznjo[i2].clone();
                    }
                    i = i2 + 1;
                }
            }
            if (this.zznjq != null) {
                zzeiaVar.zznjq = (zzehy) this.zznjq.clone();
            }
            if (this.zznju != null) {
                zzeiaVar.zznju = (zzehx) this.zznju.clone();
            }
            if (this.zznjx != null) {
                zzeiaVar.zznjx = (zzehz) this.zznjx.clone();
            }
            if (this.zznkb != null && this.zznkb.length > 0) {
                zzeiaVar.zznkb = (int[]) this.zznkb.clone();
            }
            if (this.zzmqy != null) {
                zzeiaVar.zzmqy = (zzeic) this.zzmqy.clone();
            }
            return zzeiaVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzeia)) {
            return false;
        }
        zzeia zzeiaVar = (zzeia) obj;
        if (this.zznjk == zzeiaVar.zznjk && this.zznjl == zzeiaVar.zznjl && this.zznjm == zzeiaVar.zznjm) {
            if (this.tag == null) {
                if (zzeiaVar.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(zzeiaVar.tag)) {
                return false;
            }
            if (this.zznjn == zzeiaVar.zznjn && this.zzaju == zzeiaVar.zzaju && this.zzlwn == zzeiaVar.zzlwn && zzehe.equals(this.zznjo, zzeiaVar.zznjo) && Arrays.equals(this.zznjp, zzeiaVar.zznjp)) {
                if (this.zznjq == null) {
                    if (zzeiaVar.zznjq != null) {
                        return false;
                    }
                } else if (!this.zznjq.equals(zzeiaVar.zznjq)) {
                    return false;
                }
                if (!Arrays.equals(this.zznjr, zzeiaVar.zznjr)) {
                    return false;
                }
                if (this.zznjs == null) {
                    if (zzeiaVar.zznjs != null) {
                        return false;
                    }
                } else if (!this.zznjs.equals(zzeiaVar.zznjs)) {
                    return false;
                }
                if (this.zznjt == null) {
                    if (zzeiaVar.zznjt != null) {
                        return false;
                    }
                } else if (!this.zznjt.equals(zzeiaVar.zznjt)) {
                    return false;
                }
                if (this.zznju == null) {
                    if (zzeiaVar.zznju != null) {
                        return false;
                    }
                } else if (!this.zznju.equals(zzeiaVar.zznju)) {
                    return false;
                }
                if (this.zznjv == null) {
                    if (zzeiaVar.zznjv != null) {
                        return false;
                    }
                } else if (!this.zznjv.equals(zzeiaVar.zznjv)) {
                    return false;
                }
                if (this.zznjw != zzeiaVar.zznjw) {
                    return false;
                }
                if (this.zznjx == null) {
                    if (zzeiaVar.zznjx != null) {
                        return false;
                    }
                } else if (!this.zznjx.equals(zzeiaVar.zznjx)) {
                    return false;
                }
                if (!Arrays.equals(this.zznjy, zzeiaVar.zznjy)) {
                    return false;
                }
                if (this.zznjz == null) {
                    if (zzeiaVar.zznjz != null) {
                        return false;
                    }
                } else if (!this.zznjz.equals(zzeiaVar.zznjz)) {
                    return false;
                }
                if (this.zznka == zzeiaVar.zznka && zzehe.equals(this.zznkb, zzeiaVar.zznkb) && this.zznkc == zzeiaVar.zznkc) {
                    if (this.zzmqy == null) {
                        if (zzeiaVar.zzmqy != null) {
                            return false;
                        }
                    } else if (!this.zzmqy.equals(zzeiaVar.zzmqy)) {
                        return false;
                    }
                    return (this.zzngg == null || this.zzngg.isEmpty()) ? zzeiaVar.zzngg == null || zzeiaVar.zzngg.isEmpty() : this.zzngg.equals(zzeiaVar.zzngg);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = (((((this.zzlwn ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zznjk ^ (this.zznjk >>> 32)))) * 31) + ((int) (this.zznjl ^ (this.zznjl >>> 32)))) * 31) + ((int) (this.zznjm ^ (this.zznjm >>> 32)))) * 31)) * 31) + this.zznjn) * 31) + this.zzaju) * 31)) * 31) + zzehe.hashCode(this.zznjo)) * 31) + Arrays.hashCode(this.zznjp);
        zzehy zzehyVar = this.zznjq;
        int iHashCode3 = (this.zznjt == null ? 0 : this.zznjt.hashCode()) + (((this.zznjs == null ? 0 : this.zznjs.hashCode()) + (((((zzehyVar == null ? 0 : zzehyVar.hashCode()) + (iHashCode2 * 31)) * 31) + Arrays.hashCode(this.zznjr)) * 31)) * 31);
        zzehx zzehxVar = this.zznju;
        int iHashCode4 = (((this.zznjv == null ? 0 : this.zznjv.hashCode()) + (((zzehxVar == null ? 0 : zzehxVar.hashCode()) + (iHashCode3 * 31)) * 31)) * 31) + ((int) (this.zznjw ^ (this.zznjw >>> 32)));
        zzehz zzehzVar = this.zznjx;
        int iHashCode5 = (((((((this.zznjz == null ? 0 : this.zznjz.hashCode()) + (((((zzehzVar == null ? 0 : zzehzVar.hashCode()) + (iHashCode4 * 31)) * 31) + Arrays.hashCode(this.zznjy)) * 31)) * 31) + this.zznka) * 31) + zzehe.hashCode(this.zznkb)) * 31) + ((int) (this.zznkc ^ (this.zznkc >>> 32)));
        zzeic zzeicVar = this.zzmqy;
        int iHashCode6 = ((zzeicVar == null ? 0 : zzeicVar.hashCode()) + (iHashCode5 * 31)) * 31;
        if (this.zzngg != null && !this.zzngg.isEmpty()) {
            iHashCode = this.zzngg.hashCode();
        }
        return iHashCode6 + iHashCode;
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 8:
                    this.zznjk = zzegxVar.zzcbz();
                    break;
                case 18:
                    this.tag = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzb = zzehj.zzb(zzegxVar, 26);
                    int length = this.zznjo == null ? 0 : this.zznjo.length;
                    zzeib[] zzeibVarArr = new zzeib[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zznjo, 0, zzeibVarArr, 0, length);
                    }
                    while (length < zzeibVarArr.length - 1) {
                        zzeibVarArr[length] = new zzeib();
                        zzegxVar.zza(zzeibVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzeibVarArr[length] = new zzeib();
                    zzegxVar.zza(zzeibVarArr[length]);
                    this.zznjo = zzeibVarArr;
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    this.zznjp = zzegxVar.readBytes();
                    break;
                case 50:
                    this.zznjr = zzegxVar.readBytes();
                    break;
                case 58:
                    if (this.zznju == null) {
                        this.zznju = new zzehx();
                    }
                    zzegxVar.zza(this.zznju);
                    break;
                case 66:
                    this.zznjs = zzegxVar.readString();
                    break;
                case 74:
                    if (this.zznjq == null) {
                        this.zznjq = new zzehy();
                    }
                    zzegxVar.zza(this.zznjq);
                    break;
                case 80:
                    this.zzlwn = zzegxVar.zzcea();
                    break;
                case 88:
                    this.zznjn = zzegxVar.zzcdz();
                    break;
                case 96:
                    this.zzaju = zzegxVar.zzcdz();
                    break;
                case 106:
                    this.zznjt = zzegxVar.readString();
                    break;
                case 114:
                    this.zznjv = zzegxVar.readString();
                    break;
                case 120:
                    this.zznjw = zzegxVar.zzceb();
                    break;
                case 130:
                    if (this.zznjx == null) {
                        this.zznjx = new zzehz();
                    }
                    zzegxVar.zza(this.zznjx);
                    break;
                case 136:
                    this.zznjl = zzegxVar.zzcbz();
                    break;
                case 146:
                    this.zznjy = zzegxVar.readBytes();
                    break;
                case 152:
                    int position = zzegxVar.getPosition();
                    int iZzcdz = zzegxVar.zzcdz();
                    switch (iZzcdz) {
                        case 0:
                        case 1:
                        case 2:
                            this.zznka = iZzcdz;
                            break;
                        default:
                            zzegxVar.zzhb(position);
                            zza(zzegxVar, iZzcby);
                            break;
                    }
                case 160:
                    int iZzb2 = zzehj.zzb(zzegxVar, 160);
                    int length2 = this.zznkb == null ? 0 : this.zznkb.length;
                    int[] iArr = new int[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zznkb, 0, iArr, 0, length2);
                    }
                    while (length2 < iArr.length - 1) {
                        iArr[length2] = zzegxVar.zzcdz();
                        zzegxVar.zzcby();
                        length2++;
                    }
                    iArr[length2] = zzegxVar.zzcdz();
                    this.zznkb = iArr;
                    break;
                case 162:
                    int iZzgn = zzegxVar.zzgn(zzegxVar.zzccj());
                    int position2 = zzegxVar.getPosition();
                    int i = 0;
                    while (zzegxVar.zzcef() > 0) {
                        zzegxVar.zzcdz();
                        i++;
                    }
                    zzegxVar.zzhb(position2);
                    int length3 = this.zznkb == null ? 0 : this.zznkb.length;
                    int[] iArr2 = new int[i + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zznkb, 0, iArr2, 0, length3);
                    }
                    while (length3 < iArr2.length) {
                        iArr2[length3] = zzegxVar.zzcdz();
                        length3++;
                    }
                    this.zznkb = iArr2;
                    zzegxVar.zzgo(iZzgn);
                    break;
                case 168:
                    this.zznjm = zzegxVar.zzcbz();
                    break;
                case 176:
                    this.zznkc = zzegxVar.zzcbz();
                    break;
                case 186:
                    if (this.zzmqy == null) {
                        this.zzmqy = new zzeic();
                    }
                    zzegxVar.zza(this.zzmqy);
                    break;
                case 194:
                    this.zznjz = zzegxVar.readString();
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
        if (this.zznjk != 0) {
            zzegyVar.zze(1, this.zznjk);
        }
        if (this.tag != null && !this.tag.equals("")) {
            zzegyVar.zzl(2, this.tag);
        }
        if (this.zznjo != null && this.zznjo.length > 0) {
            for (int i = 0; i < this.zznjo.length; i++) {
                zzeib zzeibVar = this.zznjo[i];
                if (zzeibVar != null) {
                    zzegyVar.zza(3, zzeibVar);
                }
            }
        }
        if (!Arrays.equals(this.zznjp, zzehj.zznha)) {
            zzegyVar.zzc(4, this.zznjp);
        }
        if (!Arrays.equals(this.zznjr, zzehj.zznha)) {
            zzegyVar.zzc(6, this.zznjr);
        }
        if (this.zznju != null) {
            zzegyVar.zza(7, this.zznju);
        }
        if (this.zznjs != null && !this.zznjs.equals("")) {
            zzegyVar.zzl(8, this.zznjs);
        }
        if (this.zznjq != null) {
            zzegyVar.zza(9, this.zznjq);
        }
        if (this.zzlwn) {
            zzegyVar.zzl(10, this.zzlwn);
        }
        if (this.zznjn != 0) {
            zzegyVar.zzv(11, this.zznjn);
        }
        if (this.zzaju != 0) {
            zzegyVar.zzv(12, this.zzaju);
        }
        if (this.zznjt != null && !this.zznjt.equals("")) {
            zzegyVar.zzl(13, this.zznjt);
        }
        if (this.zznjv != null && !this.zznjv.equals("")) {
            zzegyVar.zzl(14, this.zznjv);
        }
        if (this.zznjw != 180000) {
            zzegyVar.zzf(15, this.zznjw);
        }
        if (this.zznjx != null) {
            zzegyVar.zza(16, this.zznjx);
        }
        if (this.zznjl != 0) {
            zzegyVar.zze(17, this.zznjl);
        }
        if (!Arrays.equals(this.zznjy, zzehj.zznha)) {
            zzegyVar.zzc(18, this.zznjy);
        }
        if (this.zznka != 0) {
            zzegyVar.zzv(19, this.zznka);
        }
        if (this.zznkb != null && this.zznkb.length > 0) {
            for (int i2 = 0; i2 < this.zznkb.length; i2++) {
                zzegyVar.zzv(20, this.zznkb[i2]);
            }
        }
        if (this.zznjm != 0) {
            zzegyVar.zze(21, this.zznjm);
        }
        if (this.zznkc != 0) {
            zzegyVar.zze(22, this.zznkc);
        }
        if (this.zzmqy != null) {
            zzegyVar.zza(23, this.zzmqy);
        }
        if (this.zznjz != null && !this.zznjz.equals("")) {
            zzegyVar.zzl(24, this.zznjz);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha
    /* renamed from: zzceh */
    public final /* synthetic */ zzeha clone() throws CloneNotSupportedException {
        return (zzeia) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcei */
    public final /* synthetic */ zzehg clone() throws CloneNotSupportedException {
        return (zzeia) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zznjk != 0) {
            iZzn += zzegy.zzg(1, this.zznjk);
        }
        if (this.tag != null && !this.tag.equals("")) {
            iZzn += zzegy.zzm(2, this.tag);
        }
        if (this.zznjo != null && this.zznjo.length > 0) {
            int iZzb = iZzn;
            for (int i = 0; i < this.zznjo.length; i++) {
                zzeib zzeibVar = this.zznjo[i];
                if (zzeibVar != null) {
                    iZzb += zzegy.zzb(3, zzeibVar);
                }
            }
            iZzn = iZzb;
        }
        if (!Arrays.equals(this.zznjp, zzehj.zznha)) {
            iZzn += zzegy.zzd(4, this.zznjp);
        }
        if (!Arrays.equals(this.zznjr, zzehj.zznha)) {
            iZzn += zzegy.zzd(6, this.zznjr);
        }
        if (this.zznju != null) {
            iZzn += zzegy.zzb(7, this.zznju);
        }
        if (this.zznjs != null && !this.zznjs.equals("")) {
            iZzn += zzegy.zzm(8, this.zznjs);
        }
        if (this.zznjq != null) {
            iZzn += zzegy.zzb(9, this.zznjq);
        }
        if (this.zzlwn) {
            iZzn += zzegy.zzgs(10) + 1;
        }
        if (this.zznjn != 0) {
            iZzn += zzegy.zzaf(11, this.zznjn);
        }
        if (this.zzaju != 0) {
            iZzn += zzegy.zzaf(12, this.zzaju);
        }
        if (this.zznjt != null && !this.zznjt.equals("")) {
            iZzn += zzegy.zzm(13, this.zznjt);
        }
        if (this.zznjv != null && !this.zznjv.equals("")) {
            iZzn += zzegy.zzm(14, this.zznjv);
        }
        if (this.zznjw != 180000) {
            iZzn += zzegy.zzh(15, this.zznjw);
        }
        if (this.zznjx != null) {
            iZzn += zzegy.zzb(16, this.zznjx);
        }
        if (this.zznjl != 0) {
            iZzn += zzegy.zzg(17, this.zznjl);
        }
        if (!Arrays.equals(this.zznjy, zzehj.zznha)) {
            iZzn += zzegy.zzd(18, this.zznjy);
        }
        if (this.zznka != 0) {
            iZzn += zzegy.zzaf(19, this.zznka);
        }
        if (this.zznkb != null && this.zznkb.length > 0) {
            int iZzhd = 0;
            for (int i2 = 0; i2 < this.zznkb.length; i2++) {
                iZzhd += zzegy.zzhd(this.zznkb[i2]);
            }
            iZzn = iZzn + iZzhd + (this.zznkb.length * 2);
        }
        if (this.zznjm != 0) {
            iZzn += zzegy.zzg(21, this.zznjm);
        }
        if (this.zznkc != 0) {
            iZzn += zzegy.zzg(22, this.zznkc);
        }
        if (this.zzmqy != null) {
            iZzn += zzegy.zzb(23, this.zzmqy);
        }
        return (this.zznjz == null || this.zznjz.equals("")) ? iZzn : iZzn + zzegy.zzm(24, this.zznjz);
    }
}
