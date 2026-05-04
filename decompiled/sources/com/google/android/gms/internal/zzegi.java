package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzegi {
    private static final zzegi zzndv = new zzegi(0, new int[0], new Object[0], false);
    private int count;
    private boolean zznbc;
    private int zzncf;
    private int[] zzndw;
    private Object[] zzndx;

    private zzegi() {
        this(0, new int[8], new Object[8], true);
    }

    private zzegi(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzncf = -1;
        this.count = i;
        this.zzndw = iArr;
        this.zzndx = objArr;
        this.zznbc = z;
    }

    private final zzegi zza(zzeel zzeelVar) throws IOException {
        int iZzcby;
        do {
            iZzcby = zzeelVar.zzcby();
            if (iZzcby == 0) {
                break;
            }
        } while (zzb(iZzcby, zzeelVar));
        return this;
    }

    private void zza(int i, Object obj) {
        if (this.count == this.zzndw.length) {
            int i2 = (this.count < 4 ? 8 : this.count >> 1) + this.count;
            this.zzndw = Arrays.copyOf(this.zzndw, i2);
            this.zzndx = Arrays.copyOf(this.zzndx, i2);
        }
        this.zzndw[this.count] = i;
        this.zzndx[this.count] = obj;
        this.count++;
    }

    static zzegi zzb(zzegi zzegiVar, zzegi zzegiVar2) {
        int i = zzegiVar.count + zzegiVar2.count;
        int[] iArrCopyOf = Arrays.copyOf(zzegiVar.zzndw, i);
        System.arraycopy(zzegiVar2.zzndw, 0, iArrCopyOf, zzegiVar.count, zzegiVar2.count);
        Object[] objArrCopyOf = Arrays.copyOf(zzegiVar.zzndx, i);
        System.arraycopy(zzegiVar2.zzndx, 0, objArrCopyOf, zzegiVar.count, zzegiVar2.count);
        return new zzegi(i, iArrCopyOf, objArrCopyOf, true);
    }

    public static zzegi zzcdq() {
        return zzndv;
    }

    static zzegi zzcdr() {
        return new zzegi();
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof zzegi)) {
            zzegi zzegiVar = (zzegi) obj;
            if (this.count == zzegiVar.count) {
                int[] iArr = this.zzndw;
                int[] iArr2 = zzegiVar.zzndw;
                int i = this.count;
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
                    Object[] objArr = this.zzndx;
                    Object[] objArr2 = zzegiVar.zzndx;
                    int i3 = this.count;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= i3) {
                            z2 = true;
                            break;
                        }
                        if (!objArr[i4].equals(objArr2[i4])) {
                            z2 = false;
                            break;
                        }
                        i4++;
                    }
                    if (z2) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.count + 527) * 31) + Arrays.hashCode(this.zzndw)) * 31) + Arrays.deepHashCode(this.zzndx);
    }

    public final void zza(zzeeo zzeeoVar) throws IOException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.count) {
                return;
            }
            int i3 = this.zzndw[i2];
            int i4 = i3 >>> 3;
            switch (i3 & 7) {
                case 0:
                    zzeeoVar.zza(i4, ((Long) this.zzndx[i2]).longValue());
                    break;
                case 1:
                    zzeeoVar.zzb(i4, ((Long) this.zzndx[i2]).longValue());
                    break;
                case 2:
                    zzeeoVar.zza(i4, (zzeec) this.zzndx[i2]);
                    break;
                case 3:
                    zzeeoVar.zzu(i4, 3);
                    ((zzegi) this.zzndx[i2]).zza(zzeeoVar);
                    zzeeoVar.zzu(i4, 4);
                    break;
                case 4:
                default:
                    throw zzefj.zzcde();
                case 5:
                    zzeeoVar.zzx(i4, ((Integer) this.zzndx[i2]).intValue());
                    break;
            }
            i = i2 + 1;
        }
    }

    final boolean zzb(int i, zzeel zzeelVar) throws IOException {
        if (!this.zznbc) {
            throw new UnsupportedOperationException();
        }
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zza(i, Long.valueOf(zzeelVar.zzcbz()));
                return true;
            case 1:
                zza(i, Long.valueOf(zzeelVar.zzcca()));
                return true;
            case 2:
                zza(i, zzeelVar.zzccd());
                return true;
            case 3:
                zzegi zzegiVar = new zzegi();
                zzegiVar.zza(zzeelVar);
                zzeelVar.zzgm((i2 << 3) | 4);
                zza(i, zzegiVar);
                return true;
            case 4:
                return false;
            case 5:
                zza(i, Integer.valueOf(zzeelVar.zzccb()));
                return true;
            default:
                throw zzefj.zzcde();
        }
    }

    public final void zzbht() {
        this.zznbc = false;
    }

    final void zzd(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzeft.zzb(sb, i, String.valueOf(this.zzndw[i2] >>> 3), this.zzndx[i2]);
        }
    }

    public final int zzhi() {
        int iZzhi;
        int i = this.zzncf;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                int i3 = this.zzndw[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        iZzhi = zzeeo.zzc(i4, ((Long) this.zzndx[i2]).longValue());
                        break;
                    case 1:
                        iZzhi = zzeeo.zzd(i4, ((Long) this.zzndx[i2]).longValue());
                        break;
                    case 2:
                        iZzhi = zzeeo.zzb(i4, (zzeec) this.zzndx[i2]);
                        break;
                    case 3:
                        iZzhi = ((zzegi) this.zzndx[i2]).zzhi() + (zzeeo.zzgs(i4) << 1);
                        break;
                    case 4:
                    default:
                        throw new IllegalStateException(zzefj.zzcde());
                    case 5:
                        iZzhi = zzeeo.zzz(i4, ((Integer) this.zzndx[i2]).intValue());
                        break;
                }
                i += iZzhi;
            }
            this.zzncf = i;
        }
        return i;
    }
}
