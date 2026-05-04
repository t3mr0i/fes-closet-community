package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public final class zzegx {
    private final byte[] buffer;
    private int zznbj;
    private int zznbp;
    private int zznbr;
    private int zzngc;
    private int zzngd;
    private int zznge;
    private int zznbs = Integer.MAX_VALUE;
    private int zznbk = 64;
    private int zznbl = 67108864;

    private zzegx(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzngc = i;
        this.zzngd = i + i2;
        this.zznge = i;
    }

    public static zzegx zzav(byte[] bArr) {
        return zzh(bArr, 0, bArr.length);
    }

    private final void zzcck() {
        this.zzngd += this.zznbp;
        int i = this.zzngd;
        if (i <= this.zznbs) {
            this.zznbp = 0;
        } else {
            this.zznbp = i - this.zznbs;
            this.zzngd -= this.zznbp;
        }
    }

    private final byte zzceg() throws IOException {
        if (this.zznge == this.zzngd) {
            throw zzehf.zzcek();
        }
        byte[] bArr = this.buffer;
        int i = this.zznge;
        this.zznge = i + 1;
        return bArr[i];
    }

    public static zzegx zzh(byte[] bArr, int i, int i2) {
        return new zzegx(bArr, 0, i2);
    }

    private final void zzhc(int i) throws IOException {
        if (i < 0) {
            throw zzehf.zzcel();
        }
        if (this.zznge + i > this.zznbs) {
            zzhc(this.zznbs - this.zznge);
            throw zzehf.zzcek();
        }
        if (i > this.zzngd - this.zznge) {
            throw zzehf.zzcek();
        }
        this.zznge += i;
    }

    public final int getPosition() {
        return this.zznge - this.zzngc;
    }

    public final byte[] readBytes() throws IOException {
        int iZzccj = zzccj();
        if (iZzccj < 0) {
            throw zzehf.zzcel();
        }
        if (iZzccj == 0) {
            return zzehj.zznha;
        }
        if (iZzccj > this.zzngd - this.zznge) {
            throw zzehf.zzcek();
        }
        byte[] bArr = new byte[iZzccj];
        System.arraycopy(this.buffer, this.zznge, bArr, 0, iZzccj);
        this.zznge = iZzccj + this.zznge;
        return bArr;
    }

    public final String readString() throws IOException {
        int iZzccj = zzccj();
        if (iZzccj < 0) {
            throw zzehf.zzcel();
        }
        if (iZzccj > this.zzngd - this.zznge) {
            throw zzehf.zzcek();
        }
        String str = new String(this.buffer, this.zznge, iZzccj, zzehe.UTF_8);
        this.zznge = iZzccj + this.zznge;
        return str;
    }

    public final void zza(zzehg zzehgVar) throws IOException {
        int iZzccj = zzccj();
        if (this.zznbj >= this.zznbk) {
            throw zzehf.zzcen();
        }
        int iZzgn = zzgn(iZzccj);
        this.zznbj++;
        zzehgVar.zza(this);
        zzgm(0);
        this.zznbj--;
        zzgo(iZzgn);
    }

    public final void zza(zzehg zzehgVar, int i) throws IOException {
        if (this.zznbj >= this.zznbk) {
            throw zzehf.zzcen();
        }
        this.zznbj++;
        zzehgVar.zza(this);
        zzgm((i << 3) | 4);
        this.zznbj--;
    }

    public final byte[] zzad(int i, int i2) {
        if (i2 == 0) {
            return zzehj.zznha;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzngc + i, bArr, 0, i2);
        return bArr;
    }

    final void zzae(int i, int i2) {
        if (i > this.zznge - this.zzngc) {
            throw new IllegalArgumentException(new StringBuilder(50).append("Position ").append(i).append(" is beyond current ").append(this.zznge - this.zzngc).toString());
        }
        if (i < 0) {
            throw new IllegalArgumentException(new StringBuilder(24).append("Bad position ").append(i).toString());
        }
        this.zznge = this.zzngc + i;
        this.zznbr = i2;
    }

    public final int zzcby() throws IOException {
        if (this.zznge == this.zzngd) {
            this.zznbr = 0;
            return 0;
        }
        this.zznbr = zzccj();
        if (this.zznbr == 0) {
            throw new zzehf("Protocol message contained an invalid tag (zero).");
        }
        return this.zznbr;
    }

    public final long zzcbz() throws IOException {
        return zzcec();
    }

    public final int zzccj() throws IOException {
        byte bZzceg = zzceg();
        if (bZzceg >= 0) {
            return bZzceg;
        }
        int i = bZzceg & Byte.MAX_VALUE;
        byte bZzceg2 = zzceg();
        if (bZzceg2 >= 0) {
            return i | (bZzceg2 << 7);
        }
        int i2 = i | ((bZzceg2 & Byte.MAX_VALUE) << 7);
        byte bZzceg3 = zzceg();
        if (bZzceg3 >= 0) {
            return i2 | (bZzceg3 << 14);
        }
        int i3 = i2 | ((bZzceg3 & Byte.MAX_VALUE) << 14);
        byte bZzceg4 = zzceg();
        if (bZzceg4 >= 0) {
            return i3 | (bZzceg4 << 21);
        }
        int i4 = i3 | ((bZzceg4 & Byte.MAX_VALUE) << 21);
        byte bZzceg5 = zzceg();
        int i5 = i4 | (bZzceg5 << 28);
        if (bZzceg5 >= 0) {
            return i5;
        }
        for (int i6 = 0; i6 < 5; i6++) {
            if (zzceg() >= 0) {
                return i5;
            }
        }
        throw zzehf.zzcem();
    }

    public final int zzcdz() throws IOException {
        return zzccj();
    }

    public final boolean zzcea() throws IOException {
        return zzccj() != 0;
    }

    public final long zzceb() throws IOException {
        long jZzcec = zzcec();
        return (-(jZzcec & 1)) ^ (jZzcec >>> 1);
    }

    public final long zzcec() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            j |= (r3 & Byte.MAX_VALUE) << i;
            if ((zzceg() & 128) == 0) {
                return j;
            }
        }
        throw zzehf.zzcem();
    }

    public final int zzced() throws IOException {
        return (zzceg() & 255) | ((zzceg() & 255) << 8) | ((zzceg() & 255) << 16) | ((zzceg() & 255) << 24);
    }

    public final long zzcee() throws IOException {
        return ((zzceg() & 255) << 8) | (zzceg() & 255) | ((zzceg() & 255) << 16) | ((zzceg() & 255) << 24) | ((zzceg() & 255) << 32) | ((zzceg() & 255) << 40) | ((zzceg() & 255) << 48) | ((zzceg() & 255) << 56);
    }

    public final int zzcef() {
        if (this.zznbs == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zznbs - this.zznge;
    }

    public final void zzgm(int i) throws zzehf {
        if (this.zznbr != i) {
            throw new zzehf("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final int zzgn(int i) throws zzehf {
        if (i < 0) {
            throw zzehf.zzcel();
        }
        int i2 = this.zznge + i;
        int i3 = this.zznbs;
        if (i2 > i3) {
            throw zzehf.zzcek();
        }
        this.zznbs = i2;
        zzcck();
        return i3;
    }

    public final void zzgo(int i) {
        this.zznbs = i;
        zzcck();
    }

    public final boolean zzha(int i) throws IOException {
        int iZzcby;
        switch (i & 7) {
            case 0:
                zzccj();
                return true;
            case 1:
                zzcee();
                return true;
            case 2:
                zzhc(zzccj());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzced();
                return true;
            default:
                throw new zzehf("Protocol message tag had invalid wire type.");
        }
        do {
            iZzcby = zzcby();
            if (iZzcby != 0) {
            }
            zzgm(((i >>> 3) << 3) | 4);
            return true;
        } while (zzha(iZzcby));
        zzgm(((i >>> 3) << 3) | 4);
        return true;
    }

    public final void zzhb(int i) {
        zzae(i, this.zznbr);
    }
}
