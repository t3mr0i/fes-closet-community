package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzed;

/* loaded from: classes.dex */
public final class zzbb extends zzeb implements zzaz {
    zzbb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    @Override // com.google.android.gms.common.internal.zzaz
    public final boolean zza(com.google.android.gms.common.zzm zzmVar, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzmVar);
        zzed.zza(parcelZzax, iObjectWrapper);
        Parcel parcelZza = zza(5, parcelZzax);
        boolean zZza = zzed.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }
}
