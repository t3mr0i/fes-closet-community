package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import java.util.List;
import java.util.Map;

@WorkerThread
/* loaded from: classes.dex */
final class zzccd implements Runnable {
    private final String mPackageName;
    private final int zzbyy;
    private final Throwable zzdfo;
    private final zzccc zziqe;
    private final byte[] zziqf;
    private final Map<String, List<String>> zziqg;

    private zzccd(String str, zzccc zzcccVar, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcccVar);
        this.zziqe = zzcccVar;
        this.zzbyy = i;
        this.zzdfo = th;
        this.zziqf = bArr;
        this.mPackageName = str;
        this.zziqg = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziqe.zza(this.mPackageName, this.zzbyy, this.zzdfo, this.zziqf, this.zziqg);
    }
}
