package com.google.android.gms.internal;

/* loaded from: classes.dex */
public abstract class zzbvd<T> {
    private final int zzbfd;
    private final String zzbfe;
    private final T zzbff;

    private zzbvd(int i, String str, T t) {
        this.zzbfd = i;
        this.zzbfe = str;
        this.zzbff = t;
        zzbvo.zzapg().zza(this);
    }

    public static zzbvf zzb(int i, String str, Boolean bool) {
        return new zzbvf(0, str, bool);
    }

    public static zzbvg zzb(int i, String str, int i2) {
        return new zzbvg(0, str, Integer.valueOf(i2));
    }

    public static zzbvh zzb(int i, String str, long j) {
        return new zzbvh(0, str, Long.valueOf(j));
    }

    public final String getKey() {
        return this.zzbfe;
    }

    public final int getSource() {
        return this.zzbfd;
    }

    protected abstract T zza(zzbvl zzbvlVar);

    public final T zzil() {
        return this.zzbff;
    }
}
