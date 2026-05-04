package com.google.android.gms.common.internal;

import com.google.android.gms.internal.zzbck;

/* loaded from: classes.dex */
public abstract class DowngradeableSafeParcel extends zzbck implements ReflectedParcelable {
    private static final Object zzftx = new Object();
    private static ClassLoader zzfty = null;
    private static Integer zzftz = null;
    private boolean zzfua = false;

    private static ClassLoader zzakc() {
        synchronized (zzftx) {
        }
        return null;
    }

    protected static Integer zzakd() {
        synchronized (zzftx) {
        }
        return null;
    }

    protected static boolean zzgb(String str) {
        zzakc();
        return true;
    }
}
