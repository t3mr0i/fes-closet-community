package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
final class zzeen extends zzeel {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zznbo;
    private int zznbp;
    private int zznbq;
    private int zznbr;
    private int zznbs;

    private zzeen(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zznbs = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i + i2;
        this.pos = i;
        this.zznbq = this.pos;
        this.zznbo = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
    
        if (r3[r2] < 0) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0072 A[PHI: r2
      0x0072: PHI (r2v7 int) = (r2v6 int), (r2v9 int), (r2v11 int) binds: [B:21:0x004c, B:25:0x0058, B:29:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final int zzccj() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L6c
            byte[] r3 = r5.buffer
            int r2 = r0 + 1
            r0 = r3[r0]
            if (r0 < 0) goto L11
            r5.pos = r2
        L10:
            return r0
        L11:
            int r1 = r5.limit
            int r1 = r1 - r2
            r4 = 9
            if (r1 < r4) goto L6c
            int r1 = r2 + 1
            r2 = r3[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L26
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
        L23:
            r5.pos = r1
            goto L10
        L26:
            int r2 = r1 + 1
            r1 = r3[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L33
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            r1 = r2
            goto L23
        L33:
            int r1 = r2 + 1
            r2 = r3[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L41
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L23
        L41:
            int r2 = r1 + 1
            r1 = r3[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L72
            int r1 = r2 + 1
            r2 = r3[r2]
            if (r2 >= 0) goto L23
            int r2 = r1 + 1
            r1 = r3[r1]
            if (r1 >= 0) goto L72
            int r1 = r2 + 1
            r2 = r3[r2]
            if (r2 >= 0) goto L23
            int r2 = r1 + 1
            r1 = r3[r1]
            if (r1 >= 0) goto L72
            int r1 = r2 + 1
            r2 = r3[r2]
            if (r2 >= 0) goto L23
        L6c:
            long r0 = r5.zzccg()
            int r0 = (int) r0
            goto L10
        L72:
            r1 = r2
            goto L23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzeen.zzccj():int");
    }

    private final void zzcck() {
        this.limit += this.zznbp;
        int i = this.limit - this.zznbq;
        if (i <= this.zznbs) {
            this.zznbp = 0;
        } else {
            this.zznbp = i - this.zznbs;
            this.limit -= this.zznbp;
        }
    }

    @Override // com.google.android.gms.internal.zzeel
    public final <T extends zzeev<T, ?>> T zza(T t, zzeer zzeerVar) throws IOException {
        int iZzccj = zzccj();
        if (this.zznbj >= this.zznbk) {
            throw zzefj.zzcdf();
        }
        int iZzgn = zzgn(iZzccj);
        this.zznbj++;
        T t2 = (T) zzeev.zza(t, this, zzeerVar);
        zzgm(0);
        this.zznbj--;
        zzgo(iZzgn);
        return t2;
    }

    @Override // com.google.android.gms.internal.zzeel
    public final int zzcby() throws IOException {
        if (zzcch()) {
            this.zznbr = 0;
            return 0;
        }
        this.zznbr = zzccj();
        if ((this.zznbr >>> 3) == 0) {
            throw new zzefj("Protocol message contained an invalid tag (zero).");
        }
        return this.zznbr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b2, code lost:
    
        if (r4[r3] < 0) goto L36;
     */
    @Override // com.google.android.gms.internal.zzeel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long zzcbz() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 189
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzeen.zzcbz():long");
    }

    @Override // com.google.android.gms.internal.zzeel
    public final long zzcca() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzefj.zzcdc();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    @Override // com.google.android.gms.internal.zzeel
    public final int zzccb() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzefj.zzcdc();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    @Override // com.google.android.gms.internal.zzeel
    public final String zzccc() throws IOException {
        int iZzccj = zzccj();
        if (iZzccj <= 0 || iZzccj > this.limit - this.pos) {
            if (iZzccj == 0) {
                return "";
            }
            if (iZzccj <= 0) {
                throw zzefj.zzcdd();
            }
            throw zzefj.zzcdc();
        }
        if (!zzegl.zze(this.buffer, this.pos, this.pos + iZzccj)) {
            throw new zzefj("Protocol message had invalid UTF-8.");
        }
        int i = this.pos;
        this.pos += iZzccj;
        return new String(this.buffer, i, iZzccj, zzeff.UTF_8);
    }

    @Override // com.google.android.gms.internal.zzeel
    public final zzeec zzccd() throws IOException {
        byte[] bArrCopyOfRange;
        int iZzccj = zzccj();
        if (iZzccj > 0 && iZzccj <= this.limit - this.pos) {
            zzeec zzeecVarZzc = zzeec.zzc(this.buffer, this.pos, iZzccj);
            this.pos = iZzccj + this.pos;
            return zzeecVarZzc;
        }
        if (iZzccj == 0) {
            return zzeec.zznbd;
        }
        if (iZzccj > 0 && iZzccj <= this.limit - this.pos) {
            int i = this.pos;
            this.pos = iZzccj + this.pos;
            bArrCopyOfRange = Arrays.copyOfRange(this.buffer, i, this.pos);
        } else {
            if (iZzccj > 0) {
                throw zzefj.zzcdc();
            }
            if (iZzccj != 0) {
                throw zzefj.zzcdd();
            }
            bArrCopyOfRange = zzeff.EMPTY_BYTE_ARRAY;
        }
        return zzeec.zzas(bArrCopyOfRange);
    }

    @Override // com.google.android.gms.internal.zzeel
    public final int zzcce() throws IOException {
        return zzccj();
    }

    @Override // com.google.android.gms.internal.zzeel
    public final int zzccf() throws IOException {
        return zzccj();
    }

    @Override // com.google.android.gms.internal.zzeel
    final long zzccg() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            if (this.pos == this.limit) {
                throw zzefj.zzcdc();
            }
            byte[] bArr = this.buffer;
            int i2 = this.pos;
            this.pos = i2 + 1;
            j |= (r1 & Byte.MAX_VALUE) << i;
            if ((bArr[i2] & 128) == 0) {
                return j;
            }
        }
        throw new zzefj("CodedInputStream encountered a malformed varint.");
    }

    @Override // com.google.android.gms.internal.zzeel
    public final boolean zzcch() throws IOException {
        return this.pos == this.limit;
    }

    @Override // com.google.android.gms.internal.zzeel
    public final int zzcci() {
        return this.pos - this.zznbq;
    }

    @Override // com.google.android.gms.internal.zzeel
    public final void zzgm(int i) throws zzefj {
        if (this.zznbr != i) {
            throw new zzefj("Protocol message end-group tag did not match expected tag.");
        }
    }

    @Override // com.google.android.gms.internal.zzeel
    public final int zzgn(int i) throws zzefj {
        if (i < 0) {
            throw zzefj.zzcdd();
        }
        int iZzcci = zzcci() + i;
        int i2 = this.zznbs;
        if (iZzcci > i2) {
            throw zzefj.zzcdc();
        }
        this.zznbs = iZzcci;
        zzcck();
        return i2;
    }

    @Override // com.google.android.gms.internal.zzeel
    public final void zzgo(int i) {
        this.zznbs = i;
        zzcck();
    }
}
