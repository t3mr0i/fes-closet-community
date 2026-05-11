package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzeef extends zzeej {
    private final int zznbg;
    private final int zznbh;

    zzeef(byte[] bArr, int i, int i2) {
        super(bArr);
        zzg(i, i + i2, bArr.length);
        this.zznbg = i;
        this.zznbh = i2;
    }

    @Override // com.google.android.gms.internal.zzeej, com.google.android.gms.internal.zzeec
    public final int size() {
        return this.zznbh;
    }

    @Override // com.google.android.gms.internal.zzeej, com.google.android.gms.internal.zzeec
    protected final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzjaw, zzcbv(), bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.zzeej
    protected final int zzcbv() {
        return this.zznbg;
    }

    @Override // com.google.android.gms.internal.zzeej, com.google.android.gms.internal.zzeec
    public final byte zzgk(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzjaw[this.zznbg + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(22).append("Index < 0: ").append(i).toString());
        }
        throw new ArrayIndexOutOfBoundsException(new StringBuilder(40).append("Index > length: ").append(i).append(", ").append(size).toString());
    }
}
