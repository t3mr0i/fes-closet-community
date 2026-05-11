package com.google.android.gms.internal;

import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
final class zzcda implements zzccc {
    private /* synthetic */ zzccw zziuc;

    zzcda(zzccw zzccwVar) {
        this.zziuc = zzccwVar;
    }

    @Override // com.google.android.gms.internal.zzccc
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) throws IllegalStateException {
        this.zziuc.zzb(str, i, th, bArr, map);
    }
}
