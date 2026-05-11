package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes.dex */
public final class zzbvn extends zzeb implements zzbvl {
    zzbvn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.flags.IFlagProvider");
    }

    @Override // com.google.android.gms.internal.zzbvl
    public final boolean getBooleanFlagValue(String str, boolean z, int i) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeString(str);
        zzed.zza(parcelZzax, z);
        parcelZzax.writeInt(i);
        Parcel parcelZza = zza(2, parcelZzax);
        boolean zZza = zzed.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.internal.zzbvl
    public final int getIntFlagValue(String str, int i, int i2) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeString(str);
        parcelZzax.writeInt(i);
        parcelZzax.writeInt(i2);
        Parcel parcelZza = zza(3, parcelZzax);
        int i3 = parcelZza.readInt();
        parcelZza.recycle();
        return i3;
    }

    @Override // com.google.android.gms.internal.zzbvl
    public final long getLongFlagValue(String str, long j, int i) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeString(str);
        parcelZzax.writeLong(j);
        parcelZzax.writeInt(i);
        Parcel parcelZza = zza(4, parcelZzax);
        long j2 = parcelZza.readLong();
        parcelZza.recycle();
        return j2;
    }

    @Override // com.google.android.gms.internal.zzbvl
    public final String getStringFlagValue(String str, String str2, int i) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeString(str);
        parcelZzax.writeString(str2);
        parcelZzax.writeInt(i);
        Parcel parcelZza = zza(5, parcelZzax);
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzbvl
    public final void init(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, iObjectWrapper);
        zzb(1, parcelZzax);
    }
}
