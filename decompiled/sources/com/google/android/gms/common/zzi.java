package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
abstract class zzi extends zzg {
    private static final WeakReference<byte[]> zzffq = new WeakReference<>(null);
    private WeakReference<byte[]> zzffp;

    zzi(byte[] bArr) {
        super(bArr);
        this.zzffp = zzffq;
    }

    @Override // com.google.android.gms.common.zzg
    final byte[] getBytes() {
        byte[] bArrZzafb;
        synchronized (this) {
            bArrZzafb = this.zzffp.get();
            if (bArrZzafb == null) {
                bArrZzafb = zzafb();
                this.zzffp = new WeakReference<>(bArrZzafb);
            }
        }
        return bArrZzafb;
    }

    protected abstract byte[] zzafb();
}
