package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

/* loaded from: classes.dex */
public final class zzl extends zzeb implements zzk {
    zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    @Override // com.google.android.gms.dynamite.zzk
    public final int zza(IObjectWrapper iObjectWrapper, String str, boolean z) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, iObjectWrapper);
        parcelZzax.writeString(str);
        zzed.zza(parcelZzax, z);
        Parcel parcelZza = zza(3, parcelZzax);
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }

    @Override // com.google.android.gms.dynamite.zzk
    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, iObjectWrapper);
        parcelZzax.writeString(str);
        parcelZzax.writeInt(i);
        Parcel parcelZza = zza(2, parcelZzax);
        IObjectWrapper iObjectWrapperZzao = IObjectWrapper.zza.zzao(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperZzao;
    }
}
