package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzeeh {
    private final byte[] buffer;
    private final zzeeo zznbi;

    private zzeeh(int i) {
        this.buffer = new byte[i];
        this.zznbi = zzeeo.zzau(this.buffer);
    }

    /* synthetic */ zzeeh(int i, zzeed zzeedVar) {
        this(i);
    }

    public final zzeec zzcbw() {
        this.zznbi.zzccm();
        return new zzeej(this.buffer);
    }

    public final zzeeo zzcbx() {
        return this.zznbi;
    }
}
