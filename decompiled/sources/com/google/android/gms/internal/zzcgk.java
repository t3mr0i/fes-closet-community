package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzcgk extends zzeha<zzcgk> {
    private static volatile zzcgk[] zzizc;
    public Integer zzizd = null;
    public zzcgh[] zzize = zzcgh.zzbah();
    public zzcgm[] zzizf = zzcgm.zzbak();
    public Long zzizg = null;
    public Long zzizh = null;
    public Long zzizi = null;
    public Long zzizj = null;
    public Long zzizk = null;
    public String zzizl = null;
    public String zzcv = null;
    public String zzizm = null;
    public String zzizn = null;
    public Integer zzizo = null;
    public String zzilu = null;
    public String zzch = null;
    public String zzhts = null;
    public Long zzizp = null;
    public Long zzizq = null;
    public String zzizr = null;
    public Boolean zzizs = null;
    public String zzizt = null;
    public Long zzizu = null;
    public Integer zzizv = null;
    public String zzilx = null;
    public String zzilt = null;
    public Boolean zzizw = null;
    public zzcgg[] zzizx = zzcgg.zzbag();
    public String zzimb = null;
    public Integer zzizy = null;
    private Integer zzizz = null;
    private Integer zzjaa = null;
    public String zzjab = null;
    public Long zzjac = null;
    public Long zzjad = null;
    public String zzjae = null;

    public zzcgk() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzcgk[] zzbaj() {
        if (zzizc == null) {
            synchronized (zzehe.zzngo) {
                if (zzizc == null) {
                    zzizc = new zzcgk[0];
                }
            }
        }
        return zzizc;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcgk)) {
            return false;
        }
        zzcgk zzcgkVar = (zzcgk) obj;
        if (this.zzizd == null) {
            if (zzcgkVar.zzizd != null) {
                return false;
            }
        } else if (!this.zzizd.equals(zzcgkVar.zzizd)) {
            return false;
        }
        if (zzehe.equals(this.zzize, zzcgkVar.zzize) && zzehe.equals(this.zzizf, zzcgkVar.zzizf)) {
            if (this.zzizg == null) {
                if (zzcgkVar.zzizg != null) {
                    return false;
                }
            } else if (!this.zzizg.equals(zzcgkVar.zzizg)) {
                return false;
            }
            if (this.zzizh == null) {
                if (zzcgkVar.zzizh != null) {
                    return false;
                }
            } else if (!this.zzizh.equals(zzcgkVar.zzizh)) {
                return false;
            }
            if (this.zzizi == null) {
                if (zzcgkVar.zzizi != null) {
                    return false;
                }
            } else if (!this.zzizi.equals(zzcgkVar.zzizi)) {
                return false;
            }
            if (this.zzizj == null) {
                if (zzcgkVar.zzizj != null) {
                    return false;
                }
            } else if (!this.zzizj.equals(zzcgkVar.zzizj)) {
                return false;
            }
            if (this.zzizk == null) {
                if (zzcgkVar.zzizk != null) {
                    return false;
                }
            } else if (!this.zzizk.equals(zzcgkVar.zzizk)) {
                return false;
            }
            if (this.zzizl == null) {
                if (zzcgkVar.zzizl != null) {
                    return false;
                }
            } else if (!this.zzizl.equals(zzcgkVar.zzizl)) {
                return false;
            }
            if (this.zzcv == null) {
                if (zzcgkVar.zzcv != null) {
                    return false;
                }
            } else if (!this.zzcv.equals(zzcgkVar.zzcv)) {
                return false;
            }
            if (this.zzizm == null) {
                if (zzcgkVar.zzizm != null) {
                    return false;
                }
            } else if (!this.zzizm.equals(zzcgkVar.zzizm)) {
                return false;
            }
            if (this.zzizn == null) {
                if (zzcgkVar.zzizn != null) {
                    return false;
                }
            } else if (!this.zzizn.equals(zzcgkVar.zzizn)) {
                return false;
            }
            if (this.zzizo == null) {
                if (zzcgkVar.zzizo != null) {
                    return false;
                }
            } else if (!this.zzizo.equals(zzcgkVar.zzizo)) {
                return false;
            }
            if (this.zzilu == null) {
                if (zzcgkVar.zzilu != null) {
                    return false;
                }
            } else if (!this.zzilu.equals(zzcgkVar.zzilu)) {
                return false;
            }
            if (this.zzch == null) {
                if (zzcgkVar.zzch != null) {
                    return false;
                }
            } else if (!this.zzch.equals(zzcgkVar.zzch)) {
                return false;
            }
            if (this.zzhts == null) {
                if (zzcgkVar.zzhts != null) {
                    return false;
                }
            } else if (!this.zzhts.equals(zzcgkVar.zzhts)) {
                return false;
            }
            if (this.zzizp == null) {
                if (zzcgkVar.zzizp != null) {
                    return false;
                }
            } else if (!this.zzizp.equals(zzcgkVar.zzizp)) {
                return false;
            }
            if (this.zzizq == null) {
                if (zzcgkVar.zzizq != null) {
                    return false;
                }
            } else if (!this.zzizq.equals(zzcgkVar.zzizq)) {
                return false;
            }
            if (this.zzizr == null) {
                if (zzcgkVar.zzizr != null) {
                    return false;
                }
            } else if (!this.zzizr.equals(zzcgkVar.zzizr)) {
                return false;
            }
            if (this.zzizs == null) {
                if (zzcgkVar.zzizs != null) {
                    return false;
                }
            } else if (!this.zzizs.equals(zzcgkVar.zzizs)) {
                return false;
            }
            if (this.zzizt == null) {
                if (zzcgkVar.zzizt != null) {
                    return false;
                }
            } else if (!this.zzizt.equals(zzcgkVar.zzizt)) {
                return false;
            }
            if (this.zzizu == null) {
                if (zzcgkVar.zzizu != null) {
                    return false;
                }
            } else if (!this.zzizu.equals(zzcgkVar.zzizu)) {
                return false;
            }
            if (this.zzizv == null) {
                if (zzcgkVar.zzizv != null) {
                    return false;
                }
            } else if (!this.zzizv.equals(zzcgkVar.zzizv)) {
                return false;
            }
            if (this.zzilx == null) {
                if (zzcgkVar.zzilx != null) {
                    return false;
                }
            } else if (!this.zzilx.equals(zzcgkVar.zzilx)) {
                return false;
            }
            if (this.zzilt == null) {
                if (zzcgkVar.zzilt != null) {
                    return false;
                }
            } else if (!this.zzilt.equals(zzcgkVar.zzilt)) {
                return false;
            }
            if (this.zzizw == null) {
                if (zzcgkVar.zzizw != null) {
                    return false;
                }
            } else if (!this.zzizw.equals(zzcgkVar.zzizw)) {
                return false;
            }
            if (!zzehe.equals(this.zzizx, zzcgkVar.zzizx)) {
                return false;
            }
            if (this.zzimb == null) {
                if (zzcgkVar.zzimb != null) {
                    return false;
                }
            } else if (!this.zzimb.equals(zzcgkVar.zzimb)) {
                return false;
            }
            if (this.zzizy == null) {
                if (zzcgkVar.zzizy != null) {
                    return false;
                }
            } else if (!this.zzizy.equals(zzcgkVar.zzizy)) {
                return false;
            }
            if (this.zzizz == null) {
                if (zzcgkVar.zzizz != null) {
                    return false;
                }
            } else if (!this.zzizz.equals(zzcgkVar.zzizz)) {
                return false;
            }
            if (this.zzjaa == null) {
                if (zzcgkVar.zzjaa != null) {
                    return false;
                }
            } else if (!this.zzjaa.equals(zzcgkVar.zzjaa)) {
                return false;
            }
            if (this.zzjab == null) {
                if (zzcgkVar.zzjab != null) {
                    return false;
                }
            } else if (!this.zzjab.equals(zzcgkVar.zzjab)) {
                return false;
            }
            if (this.zzjac == null) {
                if (zzcgkVar.zzjac != null) {
                    return false;
                }
            } else if (!this.zzjac.equals(zzcgkVar.zzjac)) {
                return false;
            }
            if (this.zzjad == null) {
                if (zzcgkVar.zzjad != null) {
                    return false;
                }
            } else if (!this.zzjad.equals(zzcgkVar.zzjad)) {
                return false;
            }
            if (this.zzjae == null) {
                if (zzcgkVar.zzjae != null) {
                    return false;
                }
            } else if (!this.zzjae.equals(zzcgkVar.zzjae)) {
                return false;
            }
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzcgkVar.zzngg == null || zzcgkVar.zzngg.isEmpty() : this.zzngg.equals(zzcgkVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.zzjae == null ? 0 : this.zzjae.hashCode()) + (((this.zzjad == null ? 0 : this.zzjad.hashCode()) + (((this.zzjac == null ? 0 : this.zzjac.hashCode()) + (((this.zzjab == null ? 0 : this.zzjab.hashCode()) + (((this.zzjaa == null ? 0 : this.zzjaa.hashCode()) + (((this.zzizz == null ? 0 : this.zzizz.hashCode()) + (((this.zzizy == null ? 0 : this.zzizy.hashCode()) + (((this.zzimb == null ? 0 : this.zzimb.hashCode()) + (((((this.zzizw == null ? 0 : this.zzizw.hashCode()) + (((this.zzilt == null ? 0 : this.zzilt.hashCode()) + (((this.zzilx == null ? 0 : this.zzilx.hashCode()) + (((this.zzizv == null ? 0 : this.zzizv.hashCode()) + (((this.zzizu == null ? 0 : this.zzizu.hashCode()) + (((this.zzizt == null ? 0 : this.zzizt.hashCode()) + (((this.zzizs == null ? 0 : this.zzizs.hashCode()) + (((this.zzizr == null ? 0 : this.zzizr.hashCode()) + (((this.zzizq == null ? 0 : this.zzizq.hashCode()) + (((this.zzizp == null ? 0 : this.zzizp.hashCode()) + (((this.zzhts == null ? 0 : this.zzhts.hashCode()) + (((this.zzch == null ? 0 : this.zzch.hashCode()) + (((this.zzilu == null ? 0 : this.zzilu.hashCode()) + (((this.zzizo == null ? 0 : this.zzizo.hashCode()) + (((this.zzizn == null ? 0 : this.zzizn.hashCode()) + (((this.zzizm == null ? 0 : this.zzizm.hashCode()) + (((this.zzcv == null ? 0 : this.zzcv.hashCode()) + (((this.zzizl == null ? 0 : this.zzizl.hashCode()) + (((this.zzizk == null ? 0 : this.zzizk.hashCode()) + (((this.zzizj == null ? 0 : this.zzizj.hashCode()) + (((this.zzizi == null ? 0 : this.zzizi.hashCode()) + (((this.zzizh == null ? 0 : this.zzizh.hashCode()) + (((this.zzizg == null ? 0 : this.zzizg.hashCode()) + (((((((this.zzizd == null ? 0 : this.zzizd.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzehe.hashCode(this.zzize)) * 31) + zzehe.hashCode(this.zzizf)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + zzehe.hashCode(this.zzizx)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    this.zzizd = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 18:
                    int iZzb = zzehj.zzb(zzegxVar, 18);
                    int length = this.zzize == null ? 0 : this.zzize.length;
                    zzcgh[] zzcghVarArr = new zzcgh[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zzize, 0, zzcghVarArr, 0, length);
                    }
                    while (length < zzcghVarArr.length - 1) {
                        zzcghVarArr[length] = new zzcgh();
                        zzegxVar.zza(zzcghVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzcghVarArr[length] = new zzcgh();
                    zzegxVar.zza(zzcghVarArr[length]);
                    this.zzize = zzcghVarArr;
                    break;
                case MotionEventCompat.AXIS_SCROLL /* 26 */:
                    int iZzb2 = zzehj.zzb(zzegxVar, 26);
                    int length2 = this.zzizf == null ? 0 : this.zzizf.length;
                    zzcgm[] zzcgmVarArr = new zzcgm[iZzb2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzizf, 0, zzcgmVarArr, 0, length2);
                    }
                    while (length2 < zzcgmVarArr.length - 1) {
                        zzcgmVarArr[length2] = new zzcgm();
                        zzegxVar.zza(zzcgmVarArr[length2]);
                        zzegxVar.zzcby();
                        length2++;
                    }
                    zzcgmVarArr[length2] = new zzcgm();
                    zzegxVar.zza(zzcgmVarArr[length2]);
                    this.zzizf = zzcgmVarArr;
                    break;
                case 32:
                    this.zzizg = Long.valueOf(zzegxVar.zzcec());
                    break;
                case MotionEventCompat.AXIS_GENERIC_9 /* 40 */:
                    this.zzizh = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 48:
                    this.zzizi = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 56:
                    this.zzizk = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 66:
                    this.zzizl = zzegxVar.readString();
                    break;
                case 74:
                    this.zzcv = zzegxVar.readString();
                    break;
                case 82:
                    this.zzizm = zzegxVar.readString();
                    break;
                case 90:
                    this.zzizn = zzegxVar.readString();
                    break;
                case 96:
                    this.zzizo = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 106:
                    this.zzilu = zzegxVar.readString();
                    break;
                case 114:
                    this.zzch = zzegxVar.readString();
                    break;
                case 130:
                    this.zzhts = zzegxVar.readString();
                    break;
                case 136:
                    this.zzizp = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 144:
                    this.zzizq = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 154:
                    this.zzizr = zzegxVar.readString();
                    break;
                case 160:
                    this.zzizs = Boolean.valueOf(zzegxVar.zzcea());
                    break;
                case 170:
                    this.zzizt = zzegxVar.readString();
                    break;
                case 176:
                    this.zzizu = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 184:
                    this.zzizv = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 194:
                    this.zzilx = zzegxVar.readString();
                    break;
                case 202:
                    this.zzilt = zzegxVar.readString();
                    break;
                case 208:
                    this.zzizj = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 224:
                    this.zzizw = Boolean.valueOf(zzegxVar.zzcea());
                    break;
                case 234:
                    int iZzb3 = zzehj.zzb(zzegxVar, 234);
                    int length3 = this.zzizx == null ? 0 : this.zzizx.length;
                    zzcgg[] zzcggVarArr = new zzcgg[iZzb3 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzizx, 0, zzcggVarArr, 0, length3);
                    }
                    while (length3 < zzcggVarArr.length - 1) {
                        zzcggVarArr[length3] = new zzcgg();
                        zzegxVar.zza(zzcggVarArr[length3]);
                        zzegxVar.zzcby();
                        length3++;
                    }
                    zzcggVarArr[length3] = new zzcgg();
                    zzegxVar.zza(zzcggVarArr[length3]);
                    this.zzizx = zzcggVarArr;
                    break;
                case 242:
                    this.zzimb = zzegxVar.readString();
                    break;
                case 248:
                    this.zzizy = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 256:
                    this.zzizz = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 264:
                    this.zzjaa = Integer.valueOf(zzegxVar.zzccj());
                    break;
                case 274:
                    this.zzjab = zzegxVar.readString();
                    break;
                case 280:
                    this.zzjac = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 288:
                    this.zzjad = Long.valueOf(zzegxVar.zzcec());
                    break;
                case 298:
                    this.zzjae = zzegxVar.readString();
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
        if (this.zzizd != null) {
            zzegyVar.zzv(1, this.zzizd.intValue());
        }
        if (this.zzize != null && this.zzize.length > 0) {
            for (int i = 0; i < this.zzize.length; i++) {
                zzcgh zzcghVar = this.zzize[i];
                if (zzcghVar != null) {
                    zzegyVar.zza(2, zzcghVar);
                }
            }
        }
        if (this.zzizf != null && this.zzizf.length > 0) {
            for (int i2 = 0; i2 < this.zzizf.length; i2++) {
                zzcgm zzcgmVar = this.zzizf[i2];
                if (zzcgmVar != null) {
                    zzegyVar.zza(3, zzcgmVar);
                }
            }
        }
        if (this.zzizg != null) {
            zzegyVar.zze(4, this.zzizg.longValue());
        }
        if (this.zzizh != null) {
            zzegyVar.zze(5, this.zzizh.longValue());
        }
        if (this.zzizi != null) {
            zzegyVar.zze(6, this.zzizi.longValue());
        }
        if (this.zzizk != null) {
            zzegyVar.zze(7, this.zzizk.longValue());
        }
        if (this.zzizl != null) {
            zzegyVar.zzl(8, this.zzizl);
        }
        if (this.zzcv != null) {
            zzegyVar.zzl(9, this.zzcv);
        }
        if (this.zzizm != null) {
            zzegyVar.zzl(10, this.zzizm);
        }
        if (this.zzizn != null) {
            zzegyVar.zzl(11, this.zzizn);
        }
        if (this.zzizo != null) {
            zzegyVar.zzv(12, this.zzizo.intValue());
        }
        if (this.zzilu != null) {
            zzegyVar.zzl(13, this.zzilu);
        }
        if (this.zzch != null) {
            zzegyVar.zzl(14, this.zzch);
        }
        if (this.zzhts != null) {
            zzegyVar.zzl(16, this.zzhts);
        }
        if (this.zzizp != null) {
            zzegyVar.zze(17, this.zzizp.longValue());
        }
        if (this.zzizq != null) {
            zzegyVar.zze(18, this.zzizq.longValue());
        }
        if (this.zzizr != null) {
            zzegyVar.zzl(19, this.zzizr);
        }
        if (this.zzizs != null) {
            zzegyVar.zzl(20, this.zzizs.booleanValue());
        }
        if (this.zzizt != null) {
            zzegyVar.zzl(21, this.zzizt);
        }
        if (this.zzizu != null) {
            zzegyVar.zze(22, this.zzizu.longValue());
        }
        if (this.zzizv != null) {
            zzegyVar.zzv(23, this.zzizv.intValue());
        }
        if (this.zzilx != null) {
            zzegyVar.zzl(24, this.zzilx);
        }
        if (this.zzilt != null) {
            zzegyVar.zzl(25, this.zzilt);
        }
        if (this.zzizj != null) {
            zzegyVar.zze(26, this.zzizj.longValue());
        }
        if (this.zzizw != null) {
            zzegyVar.zzl(28, this.zzizw.booleanValue());
        }
        if (this.zzizx != null && this.zzizx.length > 0) {
            for (int i3 = 0; i3 < this.zzizx.length; i3++) {
                zzcgg zzcggVar = this.zzizx[i3];
                if (zzcggVar != null) {
                    zzegyVar.zza(29, zzcggVar);
                }
            }
        }
        if (this.zzimb != null) {
            zzegyVar.zzl(30, this.zzimb);
        }
        if (this.zzizy != null) {
            zzegyVar.zzv(31, this.zzizy.intValue());
        }
        if (this.zzizz != null) {
            zzegyVar.zzv(32, this.zzizz.intValue());
        }
        if (this.zzjaa != null) {
            zzegyVar.zzv(33, this.zzjaa.intValue());
        }
        if (this.zzjab != null) {
            zzegyVar.zzl(34, this.zzjab);
        }
        if (this.zzjac != null) {
            zzegyVar.zze(35, this.zzjac.longValue());
        }
        if (this.zzjad != null) {
            zzegyVar.zze(36, this.zzjad.longValue());
        }
        if (this.zzjae != null) {
            zzegyVar.zzl(37, this.zzjae);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zzizd != null) {
            iZzn += zzegy.zzaf(1, this.zzizd.intValue());
        }
        if (this.zzize != null && this.zzize.length > 0) {
            int iZzb = iZzn;
            for (int i = 0; i < this.zzize.length; i++) {
                zzcgh zzcghVar = this.zzize[i];
                if (zzcghVar != null) {
                    iZzb += zzegy.zzb(2, zzcghVar);
                }
            }
            iZzn = iZzb;
        }
        if (this.zzizf != null && this.zzizf.length > 0) {
            int iZzb2 = iZzn;
            for (int i2 = 0; i2 < this.zzizf.length; i2++) {
                zzcgm zzcgmVar = this.zzizf[i2];
                if (zzcgmVar != null) {
                    iZzb2 += zzegy.zzb(3, zzcgmVar);
                }
            }
            iZzn = iZzb2;
        }
        if (this.zzizg != null) {
            iZzn += zzegy.zzg(4, this.zzizg.longValue());
        }
        if (this.zzizh != null) {
            iZzn += zzegy.zzg(5, this.zzizh.longValue());
        }
        if (this.zzizi != null) {
            iZzn += zzegy.zzg(6, this.zzizi.longValue());
        }
        if (this.zzizk != null) {
            iZzn += zzegy.zzg(7, this.zzizk.longValue());
        }
        if (this.zzizl != null) {
            iZzn += zzegy.zzm(8, this.zzizl);
        }
        if (this.zzcv != null) {
            iZzn += zzegy.zzm(9, this.zzcv);
        }
        if (this.zzizm != null) {
            iZzn += zzegy.zzm(10, this.zzizm);
        }
        if (this.zzizn != null) {
            iZzn += zzegy.zzm(11, this.zzizn);
        }
        if (this.zzizo != null) {
            iZzn += zzegy.zzaf(12, this.zzizo.intValue());
        }
        if (this.zzilu != null) {
            iZzn += zzegy.zzm(13, this.zzilu);
        }
        if (this.zzch != null) {
            iZzn += zzegy.zzm(14, this.zzch);
        }
        if (this.zzhts != null) {
            iZzn += zzegy.zzm(16, this.zzhts);
        }
        if (this.zzizp != null) {
            iZzn += zzegy.zzg(17, this.zzizp.longValue());
        }
        if (this.zzizq != null) {
            iZzn += zzegy.zzg(18, this.zzizq.longValue());
        }
        if (this.zzizr != null) {
            iZzn += zzegy.zzm(19, this.zzizr);
        }
        if (this.zzizs != null) {
            this.zzizs.booleanValue();
            iZzn += zzegy.zzgs(20) + 1;
        }
        if (this.zzizt != null) {
            iZzn += zzegy.zzm(21, this.zzizt);
        }
        if (this.zzizu != null) {
            iZzn += zzegy.zzg(22, this.zzizu.longValue());
        }
        if (this.zzizv != null) {
            iZzn += zzegy.zzaf(23, this.zzizv.intValue());
        }
        if (this.zzilx != null) {
            iZzn += zzegy.zzm(24, this.zzilx);
        }
        if (this.zzilt != null) {
            iZzn += zzegy.zzm(25, this.zzilt);
        }
        if (this.zzizj != null) {
            iZzn += zzegy.zzg(26, this.zzizj.longValue());
        }
        if (this.zzizw != null) {
            this.zzizw.booleanValue();
            iZzn += zzegy.zzgs(28) + 1;
        }
        if (this.zzizx != null && this.zzizx.length > 0) {
            for (int i3 = 0; i3 < this.zzizx.length; i3++) {
                zzcgg zzcggVar = this.zzizx[i3];
                if (zzcggVar != null) {
                    iZzn += zzegy.zzb(29, zzcggVar);
                }
            }
        }
        if (this.zzimb != null) {
            iZzn += zzegy.zzm(30, this.zzimb);
        }
        if (this.zzizy != null) {
            iZzn += zzegy.zzaf(31, this.zzizy.intValue());
        }
        if (this.zzizz != null) {
            iZzn += zzegy.zzaf(32, this.zzizz.intValue());
        }
        if (this.zzjaa != null) {
            iZzn += zzegy.zzaf(33, this.zzjaa.intValue());
        }
        if (this.zzjab != null) {
            iZzn += zzegy.zzm(34, this.zzjab);
        }
        if (this.zzjac != null) {
            iZzn += zzegy.zzg(35, this.zzjac.longValue());
        }
        if (this.zzjad != null) {
            iZzn += zzegy.zzg(36, this.zzjad.longValue());
        }
        return this.zzjae != null ? iZzn + zzegy.zzm(37, this.zzjae) : iZzn;
    }
}
