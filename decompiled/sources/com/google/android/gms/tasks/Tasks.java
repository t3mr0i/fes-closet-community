package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class Tasks {

    static final class zza implements zzb {
        private final CountDownLatch zzaoe;

        private zza() {
            this.zzaoe = new CountDownLatch(1);
        }

        /* synthetic */ zza(zzo zzoVar) {
            this();
        }

        public final void await() throws InterruptedException {
            this.zzaoe.await();
        }

        public final boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.zzaoe.await(j, timeUnit);
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public final void onFailure(@NonNull Exception exc) {
            this.zzaoe.countDown();
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public final void onSuccess(Object obj) {
            this.zzaoe.countDown();
        }
    }

    interface zzb extends OnFailureListener, OnSuccessListener<Object> {
    }

    static final class zzc implements zzb {
        private final Object mLock = new Object();
        private final zzn<Void> zzkgh;
        private Exception zzkgm;
        private final int zzkgo;
        private int zzkgp;
        private int zzkgq;

        public zzc(int i, zzn<Void> zznVar) {
            this.zzkgo = i;
            this.zzkgh = zznVar;
        }

        private final void zzbii() {
            if (this.zzkgp + this.zzkgq == this.zzkgo) {
                if (this.zzkgm == null) {
                    this.zzkgh.setResult(null);
                    return;
                }
                zzn<Void> zznVar = this.zzkgh;
                int i = this.zzkgq;
                zznVar.setException(new ExecutionException(new StringBuilder(54).append(i).append(" out of ").append(this.zzkgo).append(" underlying tasks failed").toString(), this.zzkgm));
            }
        }

        @Override // com.google.android.gms.tasks.OnFailureListener
        public final void onFailure(@NonNull Exception exc) {
            synchronized (this.mLock) {
                this.zzkgq++;
                this.zzkgm = exc;
                zzbii();
            }
        }

        @Override // com.google.android.gms.tasks.OnSuccessListener
        public final void onSuccess(Object obj) {
            synchronized (this.mLock) {
                this.zzkgp++;
                zzbii();
            }
        }
    }

    private Tasks() {
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task) throws ExecutionException, InterruptedException {
        zzbp.zzgh("Must not be called on the main application thread");
        zzbp.zzb(task, "Task must not be null");
        if (task.isComplete()) {
            return (TResult) zzc(task);
        }
        zza zzaVar = new zza(null);
        zza(task, zzaVar);
        zzaVar.await();
        return (TResult) zzc(task);
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        zzbp.zzgh("Must not be called on the main application thread");
        zzbp.zzb(task, "Task must not be null");
        zzbp.zzb(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return (TResult) zzc(task);
        }
        zza zzaVar = new zza(null);
        zza(task, zzaVar);
        if (zzaVar.await(j, timeUnit)) {
            return (TResult) zzc(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static <TResult> Task<TResult> call(@NonNull Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> call(@NonNull Executor executor, @NonNull Callable<TResult> callable) {
        zzbp.zzb(executor, "Executor must not be null");
        zzbp.zzb(callable, "Callback must not be null");
        zzn zznVar = new zzn();
        executor.execute(new zzo(zznVar, callable));
        return zznVar;
    }

    public static <TResult> Task<TResult> forException(@NonNull Exception exc) {
        zzn zznVar = new zzn();
        zznVar.setException(exc);
        return zznVar;
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        zzn zznVar = new zzn();
        zznVar.setResult(tresult);
        return zznVar;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult(null);
        }
        Iterator<? extends Task<?>> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        zzn zznVar = new zzn();
        zzc zzcVar = new zzc(collection.size(), zznVar);
        Iterator<? extends Task<?>> it2 = collection.iterator();
        while (it2.hasNext()) {
            zza(it2.next(), zzcVar);
        }
        return zznVar;
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        return taskArr.length == 0 ? forResult(null) : whenAll(Arrays.asList(taskArr));
    }

    private static void zza(Task<?> task, zzb zzbVar) {
        task.addOnSuccessListener(TaskExecutors.zzkgi, zzbVar);
        task.addOnFailureListener(TaskExecutors.zzkgi, zzbVar);
    }

    private static <TResult> TResult zzc(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }
}
