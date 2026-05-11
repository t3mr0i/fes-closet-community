package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class zzeec implements Serializable, Iterable<Byte> {
    public static final zzeec zznbd = new zzeej(zzeff.EMPTY_BYTE_ARRAY);
    private static final zzeeg zznbe;
    private int zzlfx = 0;

    static {
        zzeed zzeedVar = null;
        boolean z = true;
        try {
            Class.forName("android.content.Context");
        } catch (ClassNotFoundException e) {
            z = false;
        }
        zznbe = z ? new zzeek(zzeedVar) : new zzeee(zzeedVar);
    }

    zzeec() {
    }

    public static zzeec zzar(byte[] bArr) {
        return zzc(bArr, 0, bArr.length);
    }

    static zzeec zzas(byte[] bArr) {
        return new zzeej(bArr);
    }

    public static zzeec zzc(byte[] bArr, int i, int i2) {
        return new zzeej(zznbe.zzd(bArr, i, i2));
    }

    static int zzg(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException(new StringBuilder(32).append("Beginning index: ").append(i).append(" < 0").toString());
        }
        if (i2 < i) {
            throw new IndexOutOfBoundsException(new StringBuilder(66).append("Beginning index larger than ending index: ").append(i).append(", ").append(i2).toString());
        }
        throw new IndexOutOfBoundsException(new StringBuilder(37).append("End index: ").append(i2).append(" >= ").append(i3).toString());
    }

    static zzeeh zzgl(int i) {
        return new zzeeh(i, null);
    }

    public static zzeec zzri(String str) {
        return new zzeej(str.getBytes(zzeff.UTF_8));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int iZzf = this.zzlfx;
        if (iZzf == 0) {
            int size = size();
            iZzf = zzf(size, 0, size);
            if (iZzf == 0) {
                iZzf = 1;
            }
            this.zzlfx = iZzf;
        }
        return iZzf;
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzeed(this);
    }

    public abstract int size();

    public final byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return zzeff.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[size];
        zza(bArr, 0, 0, size);
        return bArr;
    }

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    abstract void zza(zzeeb zzeebVar) throws IOException;

    protected abstract void zza(byte[] bArr, int i, int i2, int i3);

    public abstract zzeel zzcbt();

    protected final int zzcbu() {
        return this.zzlfx;
    }

    protected abstract int zzf(int i, int i2, int i3);

    public abstract byte zzgk(int i);

    public abstract zzeec zzt(int i, int i2);
}
