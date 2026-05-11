package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzeie extends zzeha<zzeie> {
    public String zznkh = "";
    public String zznki = "";
    public long zznkj = 0;
    public String zznkk = "";
    public long zznkl = 0;
    public long zzgcb = 0;
    public String zznkm = "";
    public String zznkn = "";
    public String zznko = "";
    public String zznkp = "";
    public String zznkq = "";
    public int zznkr = 0;
    public zzeid[] zznks = zzeid.zzcfa();

    public zzeie() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    public static zzeie zzaz(byte[] bArr) throws zzehf {
        return (zzeie) zzehg.zza(new zzeie(), bArr);
    }

    @Override // com.google.android.gms.internal.zzehg
    public final /* synthetic */ zzehg zza(zzegx zzegxVar) throws IOException {
        while (true) {
            int iZzcby = zzegxVar.zzcby();
            switch (iZzcby) {
                case 0:
                    break;
                case 10:
                    this.zznkh = zzegxVar.readString();
                    break;
                case 18:
                    this.zznki = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zznkj = zzegxVar.zzcbz();
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    this.zznkk = zzegxVar.readString();
                    break;
                case MotionEventCompat.AXIS_GENERIC_9 /* 40 */:
                    this.zznkl = zzegxVar.zzcbz();
                    break;
                case 48:
                    this.zzgcb = zzegxVar.zzcbz();
                    break;
                case 58:
                    this.zznkm = zzegxVar.readString();
                    break;
                case 66:
                    this.zznkn = zzegxVar.readString();
                    break;
                case 74:
                    this.zznko = zzegxVar.readString();
                    break;
                case 82:
                    this.zznkp = zzegxVar.readString();
                    break;
                case 90:
                    this.zznkq = zzegxVar.readString();
                    break;
                case 96:
                    this.zznkr = zzegxVar.zzcdz();
                    break;
                case 106:
                    int iZzb = zzehj.zzb(zzegxVar, 106);
                    int length = this.zznks == null ? 0 : this.zznks.length;
                    zzeid[] zzeidVarArr = new zzeid[iZzb + length];
                    if (length != 0) {
                        System.arraycopy(this.zznks, 0, zzeidVarArr, 0, length);
                    }
                    while (length < zzeidVarArr.length - 1) {
                        zzeidVarArr[length] = new zzeid();
                        zzegxVar.zza(zzeidVarArr[length]);
                        zzegxVar.zzcby();
                        length++;
                    }
                    zzeidVarArr[length] = new zzeid();
                    zzegxVar.zza(zzeidVarArr[length]);
                    this.zznks = zzeidVarArr;
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
        if (this.zznkh != null && !this.zznkh.equals("")) {
            zzegyVar.zzl(1, this.zznkh);
        }
        if (this.zznki != null && !this.zznki.equals("")) {
            zzegyVar.zzl(2, this.zznki);
        }
        if (this.zznkj != 0) {
            zzegyVar.zze(3, this.zznkj);
        }
        if (this.zznkk != null && !this.zznkk.equals("")) {
            zzegyVar.zzl(4, this.zznkk);
        }
        if (this.zznkl != 0) {
            zzegyVar.zze(5, this.zznkl);
        }
        if (this.zzgcb != 0) {
            zzegyVar.zze(6, this.zzgcb);
        }
        if (this.zznkm != null && !this.zznkm.equals("")) {
            zzegyVar.zzl(7, this.zznkm);
        }
        if (this.zznkn != null && !this.zznkn.equals("")) {
            zzegyVar.zzl(8, this.zznkn);
        }
        if (this.zznko != null && !this.zznko.equals("")) {
            zzegyVar.zzl(9, this.zznko);
        }
        if (this.zznkp != null && !this.zznkp.equals("")) {
            zzegyVar.zzl(10, this.zznkp);
        }
        if (this.zznkq != null && !this.zznkq.equals("")) {
            zzegyVar.zzl(11, this.zznkq);
        }
        if (this.zznkr != 0) {
            zzegyVar.zzv(12, this.zznkr);
        }
        if (this.zznks != null && this.zznks.length > 0) {
            for (int i = 0; i < this.zznks.length; i++) {
                zzeid zzeidVar = this.zznks[i];
                if (zzeidVar != null) {
                    zzegyVar.zza(13, zzeidVar);
                }
            }
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzn = super.zzn();
        if (this.zznkh != null && !this.zznkh.equals("")) {
            iZzn += zzegy.zzm(1, this.zznkh);
        }
        if (this.zznki != null && !this.zznki.equals("")) {
            iZzn += zzegy.zzm(2, this.zznki);
        }
        if (this.zznkj != 0) {
            iZzn += zzegy.zzg(3, this.zznkj);
        }
        if (this.zznkk != null && !this.zznkk.equals("")) {
            iZzn += zzegy.zzm(4, this.zznkk);
        }
        if (this.zznkl != 0) {
            iZzn += zzegy.zzg(5, this.zznkl);
        }
        if (this.zzgcb != 0) {
            iZzn += zzegy.zzg(6, this.zzgcb);
        }
        if (this.zznkm != null && !this.zznkm.equals("")) {
            iZzn += zzegy.zzm(7, this.zznkm);
        }
        if (this.zznkn != null && !this.zznkn.equals("")) {
            iZzn += zzegy.zzm(8, this.zznkn);
        }
        if (this.zznko != null && !this.zznko.equals("")) {
            iZzn += zzegy.zzm(9, this.zznko);
        }
        if (this.zznkp != null && !this.zznkp.equals("")) {
            iZzn += zzegy.zzm(10, this.zznkp);
        }
        if (this.zznkq != null && !this.zznkq.equals("")) {
            iZzn += zzegy.zzm(11, this.zznkq);
        }
        if (this.zznkr != 0) {
            iZzn += zzegy.zzaf(12, this.zznkr);
        }
        if (this.zznks == null || this.zznks.length <= 0) {
            return iZzn;
        }
        int iZzb = iZzn;
        for (int i = 0; i < this.zznks.length; i++) {
            zzeid zzeidVar = this.zznks[i];
            if (zzeidVar != null) {
                iZzb += zzegy.zzb(13, zzeidVar);
            }
        }
        return iZzb;
    }
}
