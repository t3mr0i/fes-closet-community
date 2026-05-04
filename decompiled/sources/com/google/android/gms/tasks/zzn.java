package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.zzcg;
import com.google.android.gms.common.internal.zzbp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzn<TResult> extends Task<TResult> {
    private final Object mLock = new Object();
    private final zzl<TResult> zzkgj = new zzl<>();
    private boolean zzkgk;
    private TResult zzkgl;
    private Exception zzkgm;

    static class zza extends LifecycleCallback {
        private final List<WeakReference<zzk<?>>> mListeners;

        private zza(zzcg zzcgVar) {
            super(zzcgVar);
            this.mListeners = new ArrayList();
            this.zzfon.zza("TaskOnStopCallback", this);
        }

        public static zza zzr(Activity activity) {
            zzcg zzcgVarZzn = zzn(activity);
            zza zzaVar = (zza) zzcgVarZzn.zza("TaskOnStopCallback", zza.class);
            return zzaVar == null ? new zza(zzcgVarZzn) : zzaVar;
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public final void onStop() {
            synchronized (this.mListeners) {
                Iterator<WeakReference<zzk<?>>> it = this.mListeners.iterator();
                while (it.hasNext()) {
                    zzk<?> zzkVar = it.next().get();
                    if (zzkVar != null) {
                        zzkVar.cancel();
                    }
                }
                this.mListeners.clear();
            }
        }

        public final <T> void zzb(zzk<T> zzkVar) {
            synchronized (this.mListeners) {
                this.mListeners.add(new WeakReference<>(zzkVar));
            }
        }
    }

    zzn() {
    }

    private final void zzbif() {
        zzbp.zza(this.zzkgk, "Task is not yet complete");
    }

    private final void zzbig() {
        zzbp.zza(!this.zzkgk, "Task is already complete");
    }

    private final void zzbih() {
        synchronized (this.mLock) {
            if (this.zzkgk) {
                this.zzkgj.zzb(this);
            }
        }
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        zze zzeVar = new zze(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.zzkgj.zza(zzeVar);
        zza.zzr(activity).zzb(zzeVar);
        zzbih();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzkgj.zza(new zze(executor, onCompleteListener));
        zzbih();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        zzg zzgVar = new zzg(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.zzkgj.zza(zzgVar);
        zza.zzr(activity).zzb(zzgVar);
        zzbih();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzkgj.zza(new zzg(executor, onFailureListener));
        zzbih();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        zzi zziVar = new zzi(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.zzkgj.zza(zziVar);
        zza.zzr(activity).zzb(zziVar);
        zzbih();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final Task<TResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzkgj.zza(new zzi(executor, onSuccessListener));
        zzbih();
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation) {
        zzn zznVar = new zzn();
        this.zzkgj.zza(new com.google.android.gms.tasks.zza(executor, continuation, zznVar));
        zzbih();
        return zznVar;
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    @Override // com.google.android.gms.tasks.Task
    @NonNull
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzn zznVar = new zzn();
        this.zzkgj.zza(new zzc(executor, continuation, zznVar));
        zzbih();
        return zznVar;
    }

    @Override // com.google.android.gms.tasks.Task
    @Nullable
    public final Exception getException() {
        Exception exc;
        synchronized (this.mLock) {
            exc = this.zzkgm;
        }
        return exc;
    }

    @Override // com.google.android.gms.tasks.Task
    public final TResult getResult() {
        TResult tresult;
        synchronized (this.mLock) {
            zzbif();
            if (this.zzkgm != null) {
                throw new RuntimeExecutionException(this.zzkgm);
            }
            tresult = this.zzkgl;
        }
        return tresult;
    }

    @Override // com.google.android.gms.tasks.Task
    public final <X extends Throwable> TResult getResult(@NonNull Class<X> cls) throws Throwable {
        TResult tresult;
        synchronized (this.mLock) {
            zzbif();
            if (cls.isInstance(this.zzkgm)) {
                throw cls.cast(this.zzkgm);
            }
            if (this.zzkgm != null) {
                throw new RuntimeExecutionException(this.zzkgm);
            }
            tresult = this.zzkgl;
        }
        return tresult;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isComplete() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzkgk;
        }
        return z;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzkgk && this.zzkgm == null;
        }
        return z;
    }

    public final void setException(@NonNull Exception exc) {
        zzbp.zzb(exc, "Exception must not be null");
        synchronized (this.mLock) {
            zzbig();
            this.zzkgk = true;
            this.zzkgm = exc;
        }
        this.zzkgj.zzb(this);
    }

    public final void setResult(TResult tresult) {
        synchronized (this.mLock) {
            zzbig();
            this.zzkgk = true;
            this.zzkgl = tresult;
        }
        this.zzkgj.zzb(this);
    }

    public final boolean trySetException(@NonNull Exception exc) {
        boolean z = true;
        zzbp.zzb(exc, "Exception must not be null");
        synchronized (this.mLock) {
            if (this.zzkgk) {
                z = false;
            } else {
                this.zzkgk = true;
                this.zzkgm = exc;
                this.zzkgj.zzb(this);
            }
        }
        return z;
    }

    public final boolean trySetResult(TResult tresult) {
        boolean z = true;
        synchronized (this.mLock) {
            if (this.zzkgk) {
                z = false;
            } else {
                this.zzkgk = true;
                this.zzkgl = tresult;
                this.zzkgj.zzb(this);
            }
        }
        return z;
    }
}
