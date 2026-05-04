package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
class zzeej extends zzeei {
    protected final byte[] zzjaw;

    zzeej(byte[] bArr) {
        this.zzjaw = bArr;
    }

    @Override // com.google.android.gms.internal.zzeec
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzeec) && size() == ((zzeec) obj).size()) {
            if (size() == 0) {
                return true;
            }
            if (!(obj instanceof zzeej)) {
                return obj.equals(this);
            }
            int iZzcbu = zzcbu();
            int iZzcbu2 = ((zzeej) obj).zzcbu();
            if (iZzcbu == 0 || iZzcbu2 == 0 || iZzcbu == iZzcbu2) {
                return zza((zzeej) obj, 0, size());
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.zzeec
    public int size() {
        return this.zzjaw.length;
    }

    @Override // com.google.android.gms.internal.zzeec
    final void zza(zzeeb zzeebVar) throws IOException {
        zzeebVar.zzb(this.zzjaw, zzcbv(), size());
    }

    @Override // com.google.android.gms.internal.zzeec
    protected void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzjaw, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.zzeei
    final boolean zza(zzeec zzeecVar, int i, int i2) {
        if (i2 > zzeecVar.size()) {
            throw new IllegalArgumentException(new StringBuilder(40).append("Length too large: ").append(i2).append(size()).toString());
        }
        if (i2 > zzeecVar.size()) {
            throw new IllegalArgumentException(new StringBuilder(59).append("Ran off end of other: 0, ").append(i2).append(", ").append(zzeecVar.size()).toString());
        }
        if (!(zzeecVar instanceof zzeej)) {
            return zzeecVar.zzt(0, i2).equals(zzt(0, i2));
        }
        zzeej zzeejVar = (zzeej) zzeecVar;
        byte[] bArr = this.zzjaw;
        byte[] bArr2 = zzeejVar.zzjaw;
        int iZzcbv = zzcbv() + i2;
        int iZzcbv2 = zzcbv();
        int iZzcbv3 = zzeejVar.zzcbv();
        while (iZzcbv2 < iZzcbv) {
            if (bArr[iZzcbv2] != bArr2[iZzcbv3]) {
                return false;
            }
            iZzcbv2++;
            iZzcbv3++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.zzeec
    public final zzeel zzcbt() {
        return zzeel.zzb(this.zzjaw, zzcbv(), size(), true);
    }

    protected int zzcbv() {
        return 0;
    }

    @Override // com.google.android.gms.internal.zzeec
    protected final int zzf(int i, int i2, int i3) {
        return zzeff.zza(i, this.zzjaw, zzcbv(), i3);
    }

    @Override // com.google.android.gms.internal.zzeec
    public byte zzgk(int i) {
        return this.zzjaw[i];
    }

    @Override // com.google.android.gms.internal.zzeec
    public final zzeec zzt(int i, int i2) {
        int iZzg = zzg(0, i2, size());
        return iZzg == 0 ? zzeec.zznbd : new zzeef(this.zzjaw, zzcbv(), iZzg);
    }
}
