package com.google.android.gms.common;

import java.util.Arrays;

/* loaded from: classes.dex */
final class zzh extends zzg {
    private final byte[] zzffo;

    zzh(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zzffo = bArr;
    }

    @Override // com.google.android.gms.common.zzg
    final byte[] getBytes() {
        return this.zzffo;
    }
}
