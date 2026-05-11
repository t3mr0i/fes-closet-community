package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import java.lang.Thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public final class zzccr extends zzcdu {
    private static final AtomicLong zzisj = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzirz;
    private zzccv zzisa;
    private zzccv zzisb;
    private final PriorityBlockingQueue<FutureTask<?>> zzisc;
    private final BlockingQueue<FutureTask<?>> zzisd;
    private final Thread.UncaughtExceptionHandler zzise;
    private final Thread.UncaughtExceptionHandler zzisf;
    private final Object zzisg;
    private final Semaphore zzish;
    private volatile boolean zzisi;

    zzccr(zzccw zzccwVar) {
        super(zzccwVar);
        this.zzisg = new Object();
        this.zzish = new Semaphore(2);
        this.zzisc = new PriorityBlockingQueue<>();
        this.zzisd = new LinkedBlockingQueue();
        this.zzise = new zzcct(this, "Thread death: Uncaught exception on worker thread");
        this.zzisf = new zzcct(this, "Thread death: Uncaught exception on network thread");
    }

    static /* synthetic */ zzccv zza(zzccr zzccrVar, zzccv zzccvVar) {
        zzccrVar.zzisa = null;
        return null;
    }

    private final void zza(zzccu<?> zzccuVar) {
        synchronized (this.zzisg) {
            this.zzisc.add(zzccuVar);
            if (this.zzisa == null) {
                this.zzisa = new zzccv(this, "Measurement Worker", this.zzisc);
                this.zzisa.setUncaughtExceptionHandler(this.zzise);
                this.zzisa.start();
            } else {
                this.zzisa.zzml();
            }
        }
    }

    public static boolean zzaq() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    static /* synthetic */ zzccv zzb(zzccr zzccrVar, zzccv zzccvVar) {
        zzccrVar.zzisb = null;
        return null;
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final void zzatx() {
        if (Thread.currentThread() != this.zzisb) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcan zzaty() {
        return super.zzaty();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcau zzatz() {
        return super.zzatz();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcdw zzaua() {
        return super.zzaua();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbr zzaub() {
        return super.zzaub();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbe zzauc() {
        return super.zzauc();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzceo zzaud() {
        return super.zzaud();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcek zzaue() {
        return super.zzaue();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbs zzauf() {
        return super.zzauf();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcay zzaug() {
        return super.zzaug();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbu zzauh() {
        return super.zzauh();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfw zzaui() {
        return super.zzaui();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccq zzauj() {
        return super.zzauj();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfl zzauk() {
        return super.zzauk();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccr zzaul() {
        return super.zzaul();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbw zzaum() {
        return super.zzaum();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcch zzaun() {
        return super.zzaun();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcax zzauo() {
        return super.zzauo();
    }

    public final boolean zzayt() {
        return Thread.currentThread() == this.zzisa;
    }

    final ExecutorService zzayu() {
        ExecutorService executorService;
        synchronized (this.zzisg) {
            if (this.zzirz == null) {
                this.zzirz = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.zzirz;
        }
        return executorService;
    }

    public final <V> Future<V> zzd(Callable<V> callable) throws IllegalStateException {
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(callable);
        zzccu<?> zzccuVar = new zzccu<>(this, (Callable<?>) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzisa) {
            if (!this.zzisc.isEmpty()) {
                zzaum().zzayg().log("Callable skipped the worker queue.");
            }
            zzccuVar.run();
        } else {
            zza(zzccuVar);
        }
        return zzccuVar;
    }

    public final <V> Future<V> zze(Callable<V> callable) throws IllegalStateException {
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(callable);
        zzccu<?> zzccuVar = new zzccu<>(this, (Callable<?>) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzisa) {
            zzccuVar.run();
        } else {
            zza(zzccuVar);
        }
        return zzccuVar;
    }

    public final void zzg(Runnable runnable) throws IllegalStateException {
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(runnable);
        zza(new zzccu<>(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzh(Runnable runnable) throws IllegalStateException {
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(runnable);
        zzccu zzccuVar = new zzccu(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzisg) {
            this.zzisd.add(zzccuVar);
            if (this.zzisb == null) {
                this.zzisb = new zzccv(this, "Measurement Network", this.zzisd);
                this.zzisb.setUncaughtExceptionHandler(this.zzisf);
                this.zzisb.start();
            } else {
                this.zzisb.zzml();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final void zzuj() {
        if (Thread.currentThread() != this.zzisa) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ com.google.android.gms.common.util.zzd zzvx() {
        return super.zzvx();
    }
}
