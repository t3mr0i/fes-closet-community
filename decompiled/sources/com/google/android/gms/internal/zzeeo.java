package com.google.android.gms.internal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class zzeeo extends zzeeb {
    private static final Logger logger = Logger.getLogger(zzeeo.class.getName());
    private static final boolean zznbt = zzegj.zzcds();

    static class zza extends zzeeo {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            if ((i | i2 | (bArr.length - (i + i2))) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            this.buffer = bArr;
            this.offset = i;
            this.position = i;
            this.limit = i + i2;
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zza(int i, long j) throws IOException {
            zzu(i, 0);
            zzcn(j);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zza(int i, zzeec zzeecVar) throws IOException {
            zzu(i, 2);
            zzab(zzeecVar);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zza(int i, zzefq zzefqVar) throws IOException {
            zzu(i, 2);
            zzd(zzefqVar);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzab(zzeec zzeecVar) throws IOException {
            zzgq(zzeecVar.size());
            zzeecVar.zza(this);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzb(int i, long j) throws IOException {
            zzu(i, 1);
            zzco(j);
        }

        @Override // com.google.android.gms.internal.zzeeb
        public final void zzb(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final int zzccl() {
            return this.limit - this.position;
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzcn(long j) throws IOException {
            if (zzeeo.zznbt && zzccl() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    zzegj.zza(bArr, i, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                zzegj.zza(bArr2, i2, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr3[i3] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr4[i4] = (byte) j;
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzco(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) j;
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) (j >> 8);
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) (j >> 16);
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) (j >> 24);
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) (j >> 32);
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) (j >> 40);
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) (j >> 48);
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzd(zzefq zzefqVar) throws IOException {
            zzgq(zzefqVar.zzhi());
            zzefqVar.zza(this);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzgp(int i) throws IOException {
            if (i >= 0) {
                zzgq(i);
            } else {
                zzcn(i);
            }
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzgq(int i) throws IOException {
            if (zzeeo.zznbt && zzccl() >= 10) {
                while ((i & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    zzegj.zza(bArr, i2, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                zzegj.zza(bArr2, i3, (byte) i);
                return;
            }
            while ((i & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    bArr3[i4] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 1;
            bArr4[i5] = (byte) i;
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzgr(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = i >> 24;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzl(int i, String str) throws IOException {
            zzu(1, 2);
            zzrj(str);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzrj(String str) throws IOException {
            int i = this.position;
            try {
                int iZzgt = zzgt(str.length() * 3);
                int iZzgt2 = zzgt(str.length());
                if (iZzgt2 == iZzgt) {
                    this.position = i + iZzgt2;
                    int iZza = zzegl.zza(str, this.buffer, this.position, zzccl());
                    this.position = i;
                    zzgq((iZza - i) - iZzgt2);
                    this.position = iZza;
                } else {
                    zzgq(zzegl.zzb(str));
                    this.position = zzegl.zza(str, this.buffer, this.position, zzccl());
                }
            } catch (zzego e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzu(int i, int i2) throws IOException {
            zzgq((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzv(int i, int i2) throws IOException {
            zzu(i, 0);
            zzgp(i2);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzw(int i, int i2) throws IOException {
            zzu(i, 0);
            zzgq(i2);
        }

        @Override // com.google.android.gms.internal.zzeeo
        public final void zzx(int i, int i2) throws IOException {
            zzu(i, 5);
            zzgr(i2);
        }
    }

    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARN: Illegal instructions before constructor call */
        zzb(String str, Throwable th) {
            String strValueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            String strValueOf2 = String.valueOf(str);
            super(strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf), th);
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    private zzeeo() {
    }

    public static int zzaa(int i, int i2) {
        return (i2 >= 0 ? zzgt(i2) : 10) + zzgs(i);
    }

    public static zzeeo zzau(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzb(int i, zzeec zzeecVar) {
        int iZzgs = zzgs(i);
        int size = zzeecVar.size();
        return iZzgs + size + zzgt(size);
    }

    public static int zzb(int i, zzefq zzefqVar) {
        int iZzgs = zzgs(i);
        int iZzhi = zzefqVar.zzhi();
        return iZzgs + iZzhi + zzgt(iZzhi);
    }

    public static int zzc(int i, long j) {
        int i2;
        long j2;
        int iZzgs = zzgs(i);
        if (((-128) & j) == 0) {
            i2 = 1;
        } else if (j < 0) {
            i2 = 10;
        } else {
            i2 = 2;
            if (((-34359738368L) & j) != 0) {
                i2 = 6;
                j2 = j >>> 28;
            } else {
                j2 = j;
            }
            if (((-2097152) & j2) != 0) {
                i2 += 2;
                j2 >>>= 14;
            }
            if ((j2 & (-16384)) != 0) {
                i2++;
            }
        }
        return i2 + iZzgs;
    }

    public static int zzd(int i, long j) {
        return zzgs(i) + 8;
    }

    public static int zzgs(int i) {
        return zzgt(i << 3);
    }

    public static int zzgt(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return ((-268435456) & i) == 0 ? 4 : 5;
    }

    public static int zzm(int i, String str) {
        return zzgs(1) + zzrk(str);
    }

    private static int zzrk(String str) {
        int length;
        try {
            length = zzegl.zzb(str);
        } catch (zzego e) {
            length = str.getBytes(zzeff.UTF_8).length;
        }
        return length + zzgt(length);
    }

    public static int zzy(int i, int i2) {
        return zzgs(i) + zzgt(i2);
    }

    public static int zzz(int i, int i2) {
        return zzgs(i) + 4;
    }

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzeec zzeecVar) throws IOException;

    public abstract void zza(int i, zzefq zzefqVar) throws IOException;

    final void zza(String str, zzego zzegoVar) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzegoVar);
        byte[] bytes = str.getBytes(zzeff.UTF_8);
        try {
            zzgq(bytes.length);
            zzb(bytes, 0, bytes.length);
        } catch (zzb e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzb(e2);
        }
    }

    public abstract void zzab(zzeec zzeecVar) throws IOException;

    public abstract void zzb(int i, long j) throws IOException;

    public abstract int zzccl();

    public final void zzccm() {
        if (zzccl() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzcn(long j) throws IOException;

    public abstract void zzco(long j) throws IOException;

    public abstract void zzd(zzefq zzefqVar) throws IOException;

    public abstract void zzgp(int i) throws IOException;

    public abstract void zzgq(int i) throws IOException;

    public abstract void zzgr(int i) throws IOException;

    public abstract void zzl(int i, String str) throws IOException;

    public abstract void zzrj(String str) throws IOException;

    public abstract void zzu(int i, int i2) throws IOException;

    public abstract void zzv(int i, int i2) throws IOException;

    public abstract void zzw(int i, int i2) throws IOException;

    public abstract void zzx(int i, int i2) throws IOException;
}
