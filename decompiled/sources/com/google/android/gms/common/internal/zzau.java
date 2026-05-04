package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;

/* loaded from: classes.dex */
public final class zzau extends zzeb implements zzas {
    zzau(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICertData");
    }

    @Override // com.google.android.gms.common.internal.zzas
    public final IObjectWrapper zzaez() throws RemoteException {
        Parcel parcelZza = zza(1, zzax());
        IObjectWrapper iObjectWrapperZzao = IObjectWrapper.zza.zzao(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZzao;
    }

    @Override // com.google.android.gms.common.internal.zzas
    public final int zzafa() throws RemoteException {
        Parcel parcelZza = zza(2, zzax());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }
}
