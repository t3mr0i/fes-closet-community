package com.google.android.gms.internal;

import java.lang.Thread;

/* loaded from: classes.dex */
final class zzamv implements Thread.UncaughtExceptionHandler {
    private /* synthetic */ zzamu zzdot;

    zzamv(zzamu zzamuVar) {
        this.zzdot = zzamuVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        zzaon zzaonVarZzwm = this.zzdot.zzwm();
        if (zzaonVarZzwm != null) {
            zzaonVarZzwm.zze("Job execution failed", th);
        }
    }
}
