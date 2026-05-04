package com.google.android.gms.internal;

/* loaded from: classes.dex */
public final class zzehc implements Cloneable {
    private static final zzehd zzngi = new zzehd();
    private int mSize;
    private boolean zzngj;
    private int[] zzngk;
    private zzehd[] zzngl;

    zzehc() {
        this(10);
    }

    private zzehc(int i) {
        this.zzngj = false;
        int iIdealIntArraySize = idealIntArraySize(i);
        this.zzngk = new int[iIdealIntArraySize];
        this.zzngl = new zzehd[iIdealIntArraySize];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            if (i2 <= (1 << i3) - 12) {
                i2 = (1 << i3) - 12;
                break;
            }
            i3++;
        }
        return i2 / 4;
    }

    private final int zzhk(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzngk[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else {
                if (i5 <= i) {
                    return i4;
                }
                i3 = i4 - 1;
            }
        }
        return i2 ^ (-1);
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzehc zzehcVar = new zzehc(i);
        System.arraycopy(this.zzngk, 0, zzehcVar.zzngk, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzngl[i2] != null) {
                zzehcVar.zzngl[i2] = (zzehd) this.zzngl[i2].clone();
            }
        }
        zzehcVar.mSize = i;
        return zzehcVar;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzehc)) {
            return false;
        }
        zzehc zzehcVar = (zzehc) obj;
        if (this.mSize != zzehcVar.mSize) {
            return false;
        }
        int[] iArr = this.zzngk;
        int[] iArr2 = zzehcVar.zzngk;
        int i = this.mSize;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                z = true;
                break;
            }
            if (iArr[i2] != iArr2[i2]) {
                z = false;
                break;
            }
            i2++;
        }
        if (z) {
            zzehd[] zzehdVarArr = this.zzngl;
            zzehd[] zzehdVarArr2 = zzehcVar.zzngl;
            int i3 = this.mSize;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                }
                if (!zzehdVarArr[i4].equals(zzehdVarArr2[i4])) {
                    z2 = false;
                    break;
                }
                i4++;
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = 17;
        for (int i = 0; i < this.mSize; i++) {
            iHashCode = (((iHashCode * 31) + this.zzngk[i]) * 31) + this.zzngl[i].hashCode();
        }
        return iHashCode;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int i, zzehd zzehdVar) {
        int iZzhk = zzhk(i);
        if (iZzhk >= 0) {
            this.zzngl[iZzhk] = zzehdVar;
            return;
        }
        int i2 = iZzhk ^ (-1);
        if (i2 < this.mSize && this.zzngl[i2] == zzngi) {
            this.zzngk[i2] = i;
            this.zzngl[i2] = zzehdVar;
            return;
        }
        if (this.mSize >= this.zzngk.length) {
            int iIdealIntArraySize = idealIntArraySize(this.mSize + 1);
            int[] iArr = new int[iIdealIntArraySize];
            zzehd[] zzehdVarArr = new zzehd[iIdealIntArraySize];
            System.arraycopy(this.zzngk, 0, iArr, 0, this.zzngk.length);
            System.arraycopy(this.zzngl, 0, zzehdVarArr, 0, this.zzngl.length);
            this.zzngk = iArr;
            this.zzngl = zzehdVarArr;
        }
        if (this.mSize - i2 != 0) {
            System.arraycopy(this.zzngk, i2, this.zzngk, i2 + 1, this.mSize - i2);
            System.arraycopy(this.zzngl, i2, this.zzngl, i2 + 1, this.mSize - i2);
        }
        this.zzngk[i2] = i;
        this.zzngl[i2] = zzehdVar;
        this.mSize++;
    }

    final zzehd zzhi(int i) {
        int iZzhk = zzhk(i);
        if (iZzhk < 0 || this.zzngl[iZzhk] == zzngi) {
            return null;
        }
        return this.zzngl[iZzhk];
    }

    final zzehd zzhj(int i) {
        return this.zzngl[i];
    }
}
