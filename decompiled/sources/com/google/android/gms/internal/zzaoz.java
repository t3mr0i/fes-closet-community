package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzaoz {
    private long mStartTime;
    private final com.google.android.gms.common.util.zzd zzasb;

    public zzaoz(com.google.android.gms.common.util.zzd zzdVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzdVar);
        this.zzasb = zzdVar;
    }

    public zzaoz(com.google.android.gms.common.util.zzd zzdVar, long j) {
        com.google.android.gms.common.internal.zzbp.zzu(zzdVar);
        this.zzasb = zzdVar;
        this.mStartTime = j;
    }

    public final void clear() {
        this.mStartTime = 0L;
    }

    public final void start() {
        this.mStartTime = this.zzasb.elapsedRealtime();
    }

    public final boolean zzu(long j) {
        return this.mStartTime == 0 || this.zzasb.elapsedRealtime() - this.mStartTime > j;
    }
}
