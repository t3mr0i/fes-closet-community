package com.google.android.gms.internal;

import java.util.Arrays;

/* loaded from: classes.dex */
final class zzehi {
    final int tag;
    final byte[] zzjaw;

    zzehi(int i, byte[] bArr) {
        this.tag = i;
        this.zzjaw = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzehi)) {
            return false;
        }
        zzehi zzehiVar = (zzehi) obj;
        return this.tag == zzehiVar.tag && Arrays.equals(this.zzjaw, zzehiVar.zzjaw);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzjaw);
    }
}
