package com.google.android.gms.common.api.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;

/* loaded from: classes.dex */
public abstract class zzcc extends zzec implements zzcb {
    public zzcc() {
        attachInterface(this, "com.google.android.gms.common.api.internal.IStatusCallback");
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        zzm((Status) zzed.zza(parcel, Status.CREATOR));
        return true;
    }
}
