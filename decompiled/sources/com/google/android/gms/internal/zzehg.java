package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public abstract class zzehg {
    protected volatile int zzngp = -1;

    public static final <T extends zzehg> T zza(T t, byte[] bArr) throws zzehf {
        return (T) zza(t, bArr, 0, bArr.length);
    }

    private static <T extends zzehg> T zza(T t, byte[] bArr, int i, int i2) throws zzehf {
        try {
            zzegx zzegxVarZzh = zzegx.zzh(bArr, 0, i2);
            t.zza(zzegxVarZzh);
            zzegxVarZzh.zzgm(0);
            return t;
        } catch (zzehf e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public static final byte[] zzc(zzehg zzehgVar) {
        byte[] bArr = new byte[zzehgVar.zzhi()];
        try {
            zzegy zzegyVarZzi = zzegy.zzi(bArr, 0, bArr.length);
            zzehgVar.zza(zzegyVarZzi);
            zzegyVarZzi.zzccm();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return zzehh.zzd(this);
    }

    public abstract zzehg zza(zzegx zzegxVar) throws IOException;

    public void zza(zzegy zzegyVar) throws IOException {
    }

    @Override // 
    /* renamed from: zzcei, reason: merged with bridge method [inline-methods] */
    public zzehg clone() throws CloneNotSupportedException {
        return (zzehg) super.clone();
    }

    public final int zzceo() {
        if (this.zzngp < 0) {
            zzhi();
        }
        return this.zzngp;
    }

    public final int zzhi() {
        int iZzn = zzn();
        this.zzngp = iZzn;
        return iZzn;
    }

    protected int zzn() {
        return 0;
    }
}
