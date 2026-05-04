package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.zzcp;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzs;
import com.google.android.gms.common.internal.zzbp;

/* loaded from: classes.dex */
public final class PendingResults {

    static final class zza<R extends Result> extends zzs<R> {
        private final R zzfhp;

        public zza(R r) {
            super(Looper.getMainLooper());
            this.zzfhp = r;
        }

        @Override // com.google.android.gms.common.api.internal.zzs
        protected final R zzb(Status status) {
            if (status.getStatusCode() != this.zzfhp.getStatus().getStatusCode()) {
                throw new UnsupportedOperationException("Creating failed results is not supported");
            }
            return this.zzfhp;
        }
    }

    static final class zzb<R extends Result> extends zzs<R> {
        private final R zzfhq;

        public zzb(GoogleApiClient googleApiClient, R r) {
            super(googleApiClient);
            this.zzfhq = r;
        }

        @Override // com.google.android.gms.common.api.internal.zzs
        protected final R zzb(Status status) {
            return this.zzfhq;
        }
    }

    static final class zzc<R extends Result> extends zzs<R> {
        public zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.internal.zzs
        protected final R zzb(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private PendingResults() {
    }

    public static PendingResult<Status> canceledPendingResult() {
        zzda zzdaVar = new zzda(Looper.getMainLooper());
        zzdaVar.cancel();
        return zzdaVar;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R r) {
        zzbp.zzb(r, "Result must not be null");
        zzbp.zzb(r.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
        zza zzaVar = new zza(r);
        zzaVar.cancel();
        return zzaVar;
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R r) {
        zzbp.zzb(r, "Result must not be null");
        zzc zzcVar = new zzc(null);
        zzcVar.setResult(r);
        return new zzcp(zzcVar);
    }

    public static PendingResult<Status> immediatePendingResult(Status status) {
        zzbp.zzb(status, "Result must not be null");
        zzda zzdaVar = new zzda(Looper.getMainLooper());
        zzdaVar.setResult(status);
        return zzdaVar;
    }

    public static <R extends Result> PendingResult<R> zza(R r, GoogleApiClient googleApiClient) {
        zzbp.zzb(r, "Result must not be null");
        zzbp.zzb(!r.getStatus().isSuccess(), "Status code must not be SUCCESS");
        zzb zzbVar = new zzb(googleApiClient, r);
        zzbVar.setResult(r);
        return zzbVar;
    }

    public static PendingResult<Status> zza(Status status, GoogleApiClient googleApiClient) {
        zzbp.zzb(status, "Result must not be null");
        zzda zzdaVar = new zzda(googleApiClient);
        zzdaVar.setResult(status);
        return zzdaVar;
    }

    public static <R extends Result> OptionalPendingResult<R> zzb(R r, GoogleApiClient googleApiClient) {
        zzbp.zzb(r, "Result must not be null");
        zzc zzcVar = new zzc(googleApiClient);
        zzcVar.setResult(r);
        return new zzcp(zzcVar);
    }
}
