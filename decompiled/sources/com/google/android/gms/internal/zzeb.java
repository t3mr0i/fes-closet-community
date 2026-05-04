package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public class zzeb implements IInterface {
    private final IBinder zzajw;
    private final String zzajx;

    protected zzeb(IBinder iBinder, String str) {
        this.zzajw = iBinder;
        this.zzajx = str;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.zzajw;
    }

    protected final Parcel zza(int i, Parcel parcel) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        try {
            try {
                this.zzajw.transact(i, parcel, parcelObtain, 0);
                parcelObtain.readException();
                return parcelObtain;
            } catch (RuntimeException e) {
                parcelObtain.recycle();
                throw e;
            }
        } finally {
            parcel.recycle();
        }
    }

    protected final Parcel zzax() {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeInterfaceToken(this.zzajx);
        return parcelObtain;
    }

    protected final void zzb(int i, Parcel parcel) throws RemoteException {
        Parcel parcelObtain = Parcel.obtain();
        try {
            this.zzajw.transact(i, parcel, parcelObtain, 0);
            parcelObtain.readException();
        } finally {
            parcel.recycle();
            parcelObtain.recycle();
        }
    }

    protected final void zzc(int i, Parcel parcel) throws RemoteException {
        try {
            this.zzajw.transact(i, parcel, null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
