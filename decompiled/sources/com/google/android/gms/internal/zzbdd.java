package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public final class zzbdd extends zzeb implements zzbdc {
    zzbdd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    @Override // com.google.android.gms.internal.zzbdc
    public final void zza(zzbda zzbdaVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzbdaVar);
        zzc(1, parcelZzax);
    }
}
