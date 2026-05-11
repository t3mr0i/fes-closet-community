package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

/* loaded from: classes.dex */
public final class zzbd extends zzeb implements zzbc {
    zzbd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    @Override // com.google.android.gms.common.internal.zzbc
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, zzbu zzbuVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, iObjectWrapper);
        zzed.zza(parcelZzax, zzbuVar);
        Parcel parcelZza = zza(2, parcelZzax);
        IObjectWrapper iObjectWrapperZzao = IObjectWrapper.zza.zzao(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZzao;
    }
}
