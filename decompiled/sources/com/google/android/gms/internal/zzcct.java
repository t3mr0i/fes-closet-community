package com.google.android.gms.internal;

import java.lang.Thread;

/* loaded from: classes.dex */
final class zzcct implements Thread.UncaughtExceptionHandler {
    private final String zzisk;
    private /* synthetic */ zzccr zzisl;

    public zzcct(zzccr zzccrVar, String str) {
        this.zzisl = zzccrVar;
        com.google.android.gms.common.internal.zzbp.zzu(str);
        this.zzisk = str;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzisl.zzaum().zzaye().zzj(this.zzisk, th);
    }
}
