package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdbm;

/* loaded from: classes.dex */
final class zzfa implements Runnable {
    private /* synthetic */ zzey zzjuf;
    private /* synthetic */ zzdbm zzjug;

    zzfa(zzey zzeyVar, zzdbm zzdbmVar) {
        this.zzjuf = zzeyVar;
        this.zzjug = zzdbmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzjuf.zzb(this.zzjug);
    }
}
