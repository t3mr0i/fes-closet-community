package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public abstract class zzeel {
    private static volatile boolean zznbn = true;
    int zznbj;
    int zznbk;
    private int zznbl;
    private boolean zznbm;

    private zzeel() {
        this.zznbk = 100;
        this.zznbl = Integer.MAX_VALUE;
        this.zznbm = false;
    }

    public static zzeel zzat(byte[] bArr) {
        return zzb(bArr, 0, bArr.length, false);
    }

    static zzeel zzb(byte[] bArr, int i, int i2, boolean z) {
        zzeen zzeenVar = new zzeen(bArr, i, i2, z);
        try {
            zzeenVar.zzgn(i2);
            return zzeenVar;
        } catch (zzefj e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract <T extends zzeev<T, ?>> T zza(T t, zzeer zzeerVar) throws IOException;

    public abstract int zzcby() throws IOException;

    public abstract long zzcbz() throws IOException;

    public abstract long zzcca() throws IOException;

    public abstract int zzccb() throws IOException;

    public abstract String zzccc() throws IOException;

    public abstract zzeec zzccd() throws IOException;

    public abstract int zzcce() throws IOException;

    public abstract int zzccf() throws IOException;

    abstract long zzccg() throws IOException;

    public abstract boolean zzcch() throws IOException;

    public abstract int zzcci();

    public abstract void zzgm(int i) throws zzefj;

    public abstract int zzgn(int i) throws zzefj;

    public abstract void zzgo(int i);
}
