package com.google.android.gms.tagmanager;

import java.util.List;

/* loaded from: classes.dex */
final class zzau implements Runnable {
    private /* synthetic */ List zzjqu;
    private /* synthetic */ long zzjqv;
    private /* synthetic */ zzat zzjqw;

    zzau(zzat zzatVar, List list, long j) {
        this.zzjqw = zzatVar;
        this.zzjqu = list;
        this.zzjqv = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzjqw.zzb(this.zzjqu, this.zzjqv);
    }
}
