package com.google.android.gms.internal;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zzccz implements zzccc {
    private /* synthetic */ zzccw zziuc;

    zzccz(zzccw zzccwVar) {
        this.zziuc = zzccwVar;
    }

    @Override // com.google.android.gms.internal.zzccc
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) throws IllegalStateException {
        this.zziuc.zza(i, th, bArr);
    }
}
