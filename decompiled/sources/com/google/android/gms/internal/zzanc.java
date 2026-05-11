package com.google.android.gms.internal;

import android.content.ComponentName;

/* loaded from: classes.dex */
final class zzanc implements Runnable {
    private /* synthetic */ ComponentName val$name;
    private /* synthetic */ zzana zzdph;

    zzanc(zzana zzanaVar, ComponentName componentName) {
        this.zzdph = zzanaVar;
        this.val$name = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzdph.zzdpd.onServiceDisconnected(this.val$name);
    }
}
