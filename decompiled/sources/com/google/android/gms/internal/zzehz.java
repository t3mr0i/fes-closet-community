package com.google.android.gms.internal;

import android.support.v4.view.MotionEventCompat;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzehz extends zzeha<zzehz> implements Cloneable {
    private byte[] zznjg = zzehj.zznha;
    private String zznjh = "";
    private byte[][] zznji = zzehj.zzngz;
    private boolean zznjj = false;

    public zzehz() {
        this.zzngg = null;
        this.zzngp = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcev, reason: merged with bridge method [inline-methods] */
    public zzehz clone() {
        try {
            zzehz zzehzVar = (zzehz) super.clone();
            if (this.zznji != null && this.zznji.length > 0) {
                zzehzVar.zznji = (byte[][]) this.zznji.clone();
            }
            return zzehzVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzehz)) {
            return false;
        }
        zzehz zzehzVar = (zzehz) obj;
        if (!Arrays.equals(this.zznjg, zzehzVar.zznjg)) {
            return false;
        }
        if (this.zznjh == null) {
            if (zzehzVar.zznjh != null) {
                return false;
            }
        } else if (!this.zznjh.equals(zzehzVar.zznjh)) {
            return false;
        }
        if (zzehe.zza(this.zznji, zzehzVar.zznji) && this.zznjj == zzehzVar.zznjj) {
            return (this.zzngg == null || this.zzngg.isEmpty()) ? zzehzVar.zzngg == null || zzehzVar.zzngg.isEmpty() : this.zzngg.equals(zzehzVar.zzngg);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 0;
        int iHashCode2 = ((this.zznjj ? 1231 : 1237) + (((((this.zznjh == null ? 0 : this.zznjh.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zznjg)) * 31)) * 31) + zzehe.zzd(this.zznji)) * 31)) * 31;
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
                    this.zznjg = zzegxVar.readBytes();
                    break;
                case 18:
                    int iZzb = zzehj.zzb(zzegxVar, 18);
                    int length = this.zznji == null ? 0 : this.zznji.length;
                    byte[][] bArr = new byte[iZzb + length][];
                    if (length != 0) {
                        System.arraycopy(this.zznji, 0, bArr, 0, length);
                    }
                    while (length < bArr.length - 1) {
                        bArr[length] = zzegxVar.readBytes();
                        zzegxVar.zzcby();
                        length++;
                    }
                    bArr[length] = zzegxVar.readBytes();
                    this.zznji = bArr;
                    break;
                case MotionEventCompat.AXIS_DISTANCE /* 24 */:
                    this.zznjj = zzegxVar.zzcea();
                    break;
                case MotionEventCompat.AXIS_GENERIC_3 /* 34 */:
                    this.zznjh = zzegxVar.readString();
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
        if (!Arrays.equals(this.zznjg, zzehj.zznha)) {
            zzegyVar.zzc(1, this.zznjg);
        }
        if (this.zznji != null && this.zznji.length > 0) {
            for (int i = 0; i < this.zznji.length; i++) {
                byte[] bArr = this.zznji[i];
                if (bArr != null) {
                    zzegyVar.zzc(2, bArr);
                }
            }
        }
        if (this.zznjj) {
            zzegyVar.zzl(3, this.zznjj);
        }
        if (this.zznjh != null && !this.zznjh.equals("")) {
            zzegyVar.zzl(4, this.zznjh);
        }
        super.zza(zzegyVar);
    }

    @Override // com.google.android.gms.internal.zzeha
    /* renamed from: zzceh */
    public final /* synthetic */ zzeha clone() throws CloneNotSupportedException {
        return (zzehz) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    /* renamed from: zzcei */
    public final /* synthetic */ zzehg clone() throws CloneNotSupportedException {
        return (zzehz) clone();
    }

    @Override // com.google.android.gms.internal.zzeha, com.google.android.gms.internal.zzehg
    protected final int zzn() {
        int iZzax;
        int iZzn = super.zzn();
        if (!Arrays.equals(this.zznjg, zzehj.zznha)) {
            iZzn += zzegy.zzd(1, this.zznjg);
        }
        if (this.zznji != null && this.zznji.length > 0) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < this.zznji.length) {
                byte[] bArr = this.zznji[i];
                if (bArr != null) {
                    i3++;
                    iZzax = zzegy.zzax(bArr) + i2;
                } else {
                    iZzax = i2;
                }
                i++;
                i2 = iZzax;
            }
            iZzn = iZzn + i2 + (i3 * 1);
        }
        if (this.zznjj) {
            iZzn += zzegy.zzgs(3) + 1;
        }
        return (this.zznjh == null || this.zznjh.equals("")) ? iZzn : iZzn + zzegy.zzm(4, this.zznjh);
    }
}
