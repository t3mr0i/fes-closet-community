package com.google.android.gms.common.api.internal;

/* loaded from: classes.dex */
public final class zzcl<L> {
    private final L mListener;
    private final String zzfow;

    zzcl(L l, String str) {
        this.mListener = l;
        this.zzfow = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcl)) {
            return false;
        }
        zzcl zzclVar = (zzcl) obj;
        return this.mListener == zzclVar.mListener && this.zzfow.equals(zzclVar.zzfow);
    }

    public final int hashCode() {
        return (System.identityHashCode(this.mListener) * 31) + this.zzfow.hashCode();
    }
}
