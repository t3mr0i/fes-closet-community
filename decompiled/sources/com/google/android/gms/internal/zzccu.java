package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* loaded from: classes.dex */
final class zzccu<V> extends FutureTask<V> implements Comparable<zzccu> {
    private final String zzisk;
    private /* synthetic */ zzccr zzisl;
    private final long zzism;
    private final boolean zzisn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzccu(zzccr zzccrVar, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        this.zzisl = zzccrVar;
        com.google.android.gms.common.internal.zzbp.zzu(str);
        this.zzism = zzccr.zzisj.getAndIncrement();
        this.zzisk = str;
        this.zzisn = false;
        if (this.zzism == Long.MAX_VALUE) {
            zzccrVar.zzaum().zzaye().log("Tasks index overflow");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzccu(zzccr zzccrVar, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zzisl = zzccrVar;
        com.google.android.gms.common.internal.zzbp.zzu(str);
        this.zzism = zzccr.zzisj.getAndIncrement();
        this.zzisk = str;
        this.zzisn = z;
        if (this.zzism == Long.MAX_VALUE) {
            zzccrVar.zzaum().zzaye().log("Tasks index overflow");
        }
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(@NonNull zzccu zzccuVar) {
        zzccu zzccuVar2 = zzccuVar;
        if (this.zzisn != zzccuVar2.zzisn) {
            return this.zzisn ? -1 : 1;
        }
        if (this.zzism < zzccuVar2.zzism) {
            return -1;
        }
        if (this.zzism > zzccuVar2.zzism) {
            return 1;
        }
        this.zzisl.zzaum().zzayf().zzj("Two tasks share the same index. index", Long.valueOf(this.zzism));
        return 0;
    }

    @Override // java.util.concurrent.FutureTask
    protected final void setException(Throwable th) {
        this.zzisl.zzaum().zzaye().zzj(this.zzisk, th);
        if (th instanceof zzccs) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }
}
