package com.google.android.gms.analytics;

import android.util.Log;
import com.google.android.gms.analytics.zzj;
import java.lang.Thread;
import java.util.concurrent.FutureTask;

/* JADX INFO: Add missing generic type declarations: [T] */
/* loaded from: classes.dex */
final class zzl<T> extends FutureTask<T> {
    private /* synthetic */ zzj.zza zzdle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzl(zzj.zza zzaVar, Runnable runnable, Object obj) {
        super(runnable, obj);
        this.zzdle = zzaVar;
    }

    @Override // java.util.concurrent.FutureTask
    protected final void setException(Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = zzj.this.zzdlb;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        } else if (Log.isLoggable("GAv4", 6)) {
            String strValueOf = String.valueOf(th);
            Log.e("GAv4", new StringBuilder(String.valueOf(strValueOf).length() + 37).append("MeasurementExecutor: job failed with ").append(strValueOf).toString());
        }
        super.setException(th);
    }
}
