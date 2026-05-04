package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

/* loaded from: classes.dex */
public final class zzn extends zzeb implements zzm {
    zzn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    @Override // com.google.android.gms.dynamite.zzm
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, iObjectWrapper);
        parcelZzax.writeString(str);
        parcelZzax.writeInt(i);
        zzed.zza(parcelZzax, iObjectWrapper2);
        Parcel parcelZza = zza(2, parcelZzax);
        IObjectWrapper iObjectWrapperZzao = IObjectWrapper.zza.zzao(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZzao;
    }
}
