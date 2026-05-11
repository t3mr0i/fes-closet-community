package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
final class zzay implements zzax {
    private final IBinder zzajw;

    zzay(IBinder iBinder) {
        this.zzajw = iBinder;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.zzajw;
    }

    @Override // com.google.android.gms.common.internal.zzax
    public final void zza(zzav zzavVar, zzy zzyVar) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            parcelObtain.writeStrongBinder(zzavVar.asBinder());
            parcelObtain.writeInt(1);
            zzyVar.writeToParcel(parcelObtain, 0);
            this.zzajw.transact(46, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
