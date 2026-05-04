package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
public abstract class zza {
    private int zzecy;

    public zza(int i) {
        this.zzecy = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status zza(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        if (com.google.android.gms.common.util.zzp.zzale() && (remoteException instanceof TransactionTooLargeException)) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }

    public abstract void zza(@NonNull zzah zzahVar, boolean z);

    public abstract void zza(zzbr<?> zzbrVar) throws DeadObjectException;

    public abstract void zzr(@NonNull Status status);
}
