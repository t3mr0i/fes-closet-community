package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public final class zzcqb extends zzeb implements zzcqa {
    zzcqb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    @Override // com.google.android.gms.internal.zzcqa
    public final void zza(com.google.android.gms.common.internal.zzam zzamVar, int i, boolean z) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzamVar);
        parcelZzax.writeInt(i);
        zzed.zza(parcelZzax, z);
        zzb(9, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcqa
    public final void zza(zzcqd zzcqdVar, zzcpy zzcpyVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcqdVar);
        zzed.zza(parcelZzax, zzcpyVar);
        zzb(12, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcqa
    public final void zzec(int i) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeInt(i);
        zzb(7, parcelZzax);
    }
}
