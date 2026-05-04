package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzegp extends zzegm {
    zzegp() {
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzegl.zzgy(i);
            case 1:
                return zzegl.zzab(i, zzegj.zzb(bArr, j));
            case 2:
                return zzegl.zzh(i, zzegj.zzb(bArr, j), zzegj.zzb(bArr, 1 + j));
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x004e, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0085, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int zza(byte[] r9, long r10, int r12) {
        /*
            Method dump skipped, instructions count: 190
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzegp.zza(byte[], long, int):int");
    }

    @Override // com.google.android.gms.internal.zzegm
    final int zzb(int i, byte[] bArr, int i2, int i3) {
        if ((i2 | i3 | (bArr.length - i3)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        long j = i2;
        return zza(bArr, j, (int) (i3 - j));
    }

    @Override // com.google.android.gms.internal.zzegm
    final int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j;
        long j2 = i;
        long j3 = j2 + i2;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            throw new ArrayIndexOutOfBoundsException(new StringBuilder(37).append("Failed writing ").append(charSequence.charAt(length - 1)).append(" at index ").append(i + i2).toString());
        }
        int i3 = 0;
        while (i3 < length) {
            char cCharAt = charSequence.charAt(i3);
            if (cCharAt >= 128) {
                break;
            }
            zzegj.zza(bArr, j2, (byte) cCharAt);
            i3++;
            j2 = 1 + j2;
        }
        if (i3 == length) {
            return (int) j2;
        }
        long j4 = j2;
        while (i3 < length) {
            char cCharAt2 = charSequence.charAt(i3);
            if (cCharAt2 < 128 && j4 < j3) {
                j = 1 + j4;
                zzegj.zza(bArr, j4, (byte) cCharAt2);
            } else if (cCharAt2 < 2048 && j4 <= j3 - 2) {
                long j5 = j4 + 1;
                zzegj.zza(bArr, j4, (byte) ((cCharAt2 >>> 6) | 960));
                j = 1 + j5;
                zzegj.zza(bArr, j5, (byte) ((cCharAt2 & '?') | 128));
            } else {
                if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || j4 > j3 - 3) {
                    if (j4 > j3 - 4) {
                        if (55296 > cCharAt2 || cCharAt2 > 57343 || (i3 + 1 != length && Character.isSurrogatePair(cCharAt2, charSequence.charAt(i3 + 1)))) {
                            throw new ArrayIndexOutOfBoundsException(new StringBuilder(46).append("Failed writing ").append(cCharAt2).append(" at index ").append(j4).toString());
                        }
                        throw new zzego(i3, length);
                    }
                    if (i3 + 1 != length) {
                        i3++;
                        char cCharAt3 = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                            int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                            long j6 = 1 + j4;
                            zzegj.zza(bArr, j4, (byte) ((codePoint >>> 18) | 240));
                            long j7 = 1 + j6;
                            zzegj.zza(bArr, j6, (byte) (((codePoint >>> 12) & 63) | 128));
                            long j8 = j7 + 1;
                            zzegj.zza(bArr, j7, (byte) (((codePoint >>> 6) & 63) | 128));
                            j = 1 + j8;
                            zzegj.zza(bArr, j8, (byte) ((codePoint & 63) | 128));
                        }
                    }
                    throw new zzego(i3 - 1, length);
                }
                long j9 = 1 + j4;
                zzegj.zza(bArr, j4, (byte) ((cCharAt2 >>> '\f') | 480));
                long j10 = 1 + j9;
                zzegj.zza(bArr, j9, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                j = 1 + j10;
                zzegj.zza(bArr, j10, (byte) ((cCharAt2 & '?') | 128));
            }
            i3++;
            j4 = j;
        }
        return (int) j4;
    }
}
