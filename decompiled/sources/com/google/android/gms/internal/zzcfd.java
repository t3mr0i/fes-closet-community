package com.google.android.gms.internal;

import android.content.ComponentName;

/* loaded from: classes.dex */
final class zzcfd implements Runnable {
    private /* synthetic */ ComponentName val$name;
    private /* synthetic */ zzcfb zziwo;

    zzcfd(zzcfb zzcfbVar, ComponentName componentName) {
        this.zziwo = zzcfbVar;
        this.val$name = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zziwo.zziwe.onServiceDisconnected(this.val$name);
    }
}
