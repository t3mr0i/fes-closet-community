package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
abstract class zzbcy extends zzbcx<Status> {
    public zzbcy(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.zzs
    public final /* synthetic */ Result zzb(Status status) {
        return status;
    }
}
