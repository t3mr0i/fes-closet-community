package com.google.android.gms.tagmanager;

import java.util.Arrays;

/* loaded from: classes.dex */
final class zzay {
    final String zzbfe;
    final byte[] zzjqz;

    zzay(String str, byte[] bArr) {
        this.zzbfe = str;
        this.zzjqz = bArr;
    }

    public final String toString() {
        String str = this.zzbfe;
        return new StringBuilder(String.valueOf(str).length() + 54).append("KeyAndSerialized: key = ").append(str).append(" serialized hash = ").append(Arrays.hashCode(this.zzjqz)).toString();
    }
}
