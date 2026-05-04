package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zzbcw extends zzbcq {
    private final com.google.android.gms.common.api.internal.zzn<Status> zzfwh;

    public zzbcw(com.google.android.gms.common.api.internal.zzn<Status> zznVar) {
        this.zzfwh = zznVar;
    }

    @Override // com.google.android.gms.internal.zzbcq, com.google.android.gms.internal.zzbda
    public final void zzcg(int i) throws RemoteException {
        this.zzfwh.setResult(new Status(i));
    }
}
