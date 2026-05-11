package com.google.android.gms.internal;

/* loaded from: classes.dex */
public final class zzbvo {
    private static zzbvo zzhay;
    private final zzbvj zzhaz = new zzbvj();
    private final zzbvk zzhba = new zzbvk();

    static {
        zzbvo zzbvoVar = new zzbvo();
        synchronized (zzbvo.class) {
            zzhay = zzbvoVar;
        }
    }

    private zzbvo() {
    }

    private static zzbvo zzapf() {
        zzbvo zzbvoVar;
        synchronized (zzbvo.class) {
            zzbvoVar = zzhay;
        }
        return zzbvoVar;
    }

    public static zzbvj zzapg() {
        return zzapf().zzhaz;
    }

    public static zzbvk zzaph() {
        return zzapf().zzhba;
    }
}
