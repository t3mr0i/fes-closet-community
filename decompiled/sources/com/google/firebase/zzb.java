package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzcz;

/* loaded from: classes.dex */
public final class zzb implements zzcz {
    @Override // com.google.android.gms.common.api.internal.zzcz
    public final Exception zzs(Status status) {
        return status.getStatusCode() == 8 ? new FirebaseException(status.zzafu()) : new FirebaseApiNotAvailableException(status.zzafu());
    }
}
