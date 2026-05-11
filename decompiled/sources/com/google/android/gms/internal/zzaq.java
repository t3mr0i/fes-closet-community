package com.google.android.gms.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzaq extends ByteArrayOutputStream {
    private final zzae zzbp;

    public zzaq(zzae zzaeVar, int i) {
        this.zzbp = zzaeVar;
        this.buf = this.zzbp.zzb(Math.max(i, 256));
    }

    private final void zzc(int i) {
        if (this.count + i <= this.buf.length) {
            return;
        }
        byte[] bArrZzb = this.zzbp.zzb((this.count + i) << 1);
        System.arraycopy(this.buf, 0, bArrZzb, 0, this.count);
        this.zzbp.zza(this.buf);
        this.buf = bArrZzb;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.zzbp.zza(this.buf);
        this.buf = null;
        super.close();
    }

    public final void finalize() {
        this.zzbp.zza(this.buf);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public final synchronized void write(int i) {
        zzc(1);
        super.write(i);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public final synchronized void write(byte[] bArr, int i, int i2) {
        zzc(i2);
        super.write(bArr, i, i2);
    }
}
