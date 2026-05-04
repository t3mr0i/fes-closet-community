package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
public final class zzda extends zzs<Status> {
    @Deprecated
    public zzda(Looper looper) {
        super(looper);
    }

    public zzda(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.zzs
    protected final /* synthetic */ Result zzb(Status status) {
        return status;
    }
}
