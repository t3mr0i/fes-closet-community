package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class zzdg<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final WeakReference<GoogleApiClient> zzfjg;
    private final zzdi zzfpl;
    private ResultTransform<? super R, ? extends Result> zzfpg = null;
    private zzdg<? extends Result> zzfph = null;
    private volatile ResultCallbacks<? super R> zzfpi = null;
    private PendingResult<R> zzfpj = null;
    private final Object zzfje = new Object();
    private Status zzfpk = null;
    private boolean zzfpm = false;

    public zzdg(WeakReference<GoogleApiClient> weakReference) {
        com.google.android.gms.common.internal.zzbp.zzb(weakReference, "GoogleApiClient reference must not be null");
        this.zzfjg = weakReference;
        GoogleApiClient googleApiClient = this.zzfjg.get();
        this.zzfpl = new zzdi(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    private final void zzaio() {
        if (this.zzfpg == null && this.zzfpi == null) {
            return;
        }
        GoogleApiClient googleApiClient = this.zzfjg.get();
        if (!this.zzfpm && this.zzfpg != null && googleApiClient != null) {
            googleApiClient.zza(this);
            this.zzfpm = true;
        }
        if (this.zzfpk != null) {
            zzw(this.zzfpk);
        } else if (this.zzfpj != null) {
            this.zzfpj.setResultCallback(this);
        }
    }

    private final boolean zzaiq() {
        return (this.zzfpi == null || this.zzfjg.get() == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String strValueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(strValueOf).length() + 18).append("Unable to release ").append(strValueOf).toString(), e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(Status status) {
        synchronized (this.zzfje) {
            this.zzfpk = status;
            zzw(this.zzfpk);
        }
    }

    private final void zzw(Status status) {
        synchronized (this.zzfje) {
            if (this.zzfpg != null) {
                Status statusOnFailure = this.zzfpg.onFailure(status);
                com.google.android.gms.common.internal.zzbp.zzb(statusOnFailure, "onFailure must not return null");
                this.zzfph.zzd(statusOnFailure);
            } else if (zzaiq()) {
                this.zzfpi.onFailure(status);
            }
        }
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        synchronized (this.zzfje) {
            com.google.android.gms.common.internal.zzbp.zza(this.zzfpi == null, "Cannot call andFinally() twice.");
            com.google.android.gms.common.internal.zzbp.zza(this.zzfpg == null, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzfpi = resultCallbacks;
            zzaio();
        }
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final void onResult(R r) {
        synchronized (this.zzfje) {
            if (!r.getStatus().isSuccess()) {
                zzd(r.getStatus());
                zzd(r);
            } else if (this.zzfpg != null) {
                zzct.zzahn().submit(new zzdh(this, r));
            } else if (zzaiq()) {
                this.zzfpi.onSuccess(r);
            }
        }
    }

    @Override // com.google.android.gms.common.api.TransformedResult
    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        zzdg<? extends Result> zzdgVar;
        synchronized (this.zzfje) {
            com.google.android.gms.common.internal.zzbp.zza(this.zzfpg == null, "Cannot call then() twice.");
            com.google.android.gms.common.internal.zzbp.zza(this.zzfpi == null, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzfpg = resultTransform;
            zzdgVar = new zzdg<>(this.zzfjg);
            this.zzfph = zzdgVar;
            zzaio();
        }
        return zzdgVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzfje) {
            this.zzfpj = pendingResult;
            zzaio();
        }
    }

    final void zzaip() {
        this.zzfpi = null;
    }
}
