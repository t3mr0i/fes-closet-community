package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public final class zzex extends zzeb implements zzev {
    zzex(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    @Override // com.google.android.gms.internal.zzev
    public final String getId() throws RemoteException {
        Parcel parcelZza = zza(1, zzax());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzev
    public final boolean zzb(boolean z) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, true);
        Parcel parcelZza = zza(2, parcelZzax);
        boolean zZza = zzed.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }
}
