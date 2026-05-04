package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class zzcbq extends zzeb implements zzcbo {
    zzcbq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final List<zzcft> zza(zzcas zzcasVar, boolean z) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcasVar);
        zzed.zza(parcelZzax, z);
        Parcel parcelZza = zza(7, parcelZzax);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzcft.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final List<zzcav> zza(String str, String str2, zzcas zzcasVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeString(str);
        parcelZzax.writeString(str2);
        zzed.zza(parcelZzax, zzcasVar);
        Parcel parcelZza = zza(16, parcelZzax);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzcav.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final List<zzcft> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeString(str);
        parcelZzax.writeString(str2);
        parcelZzax.writeString(str3);
        zzed.zza(parcelZzax, z);
        Parcel parcelZza = zza(15, parcelZzax);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzcft.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final List<zzcft> zza(String str, String str2, boolean z, zzcas zzcasVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeString(str);
        parcelZzax.writeString(str2);
        zzed.zza(parcelZzax, z);
        zzed.zza(parcelZzax, zzcasVar);
        Parcel parcelZza = zza(14, parcelZzax);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzcft.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeLong(j);
        parcelZzax.writeString(str);
        parcelZzax.writeString(str2);
        parcelZzax.writeString(str3);
        zzb(10, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final void zza(zzcas zzcasVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcasVar);
        zzb(4, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final void zza(zzcav zzcavVar, zzcas zzcasVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcavVar);
        zzed.zza(parcelZzax, zzcasVar);
        zzb(12, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final void zza(zzcbk zzcbkVar, zzcas zzcasVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcbkVar);
        zzed.zza(parcelZzax, zzcasVar);
        zzb(1, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final void zza(zzcbk zzcbkVar, String str, String str2) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcbkVar);
        parcelZzax.writeString(str);
        parcelZzax.writeString(str2);
        zzb(5, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final void zza(zzcft zzcftVar, zzcas zzcasVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcftVar);
        zzed.zza(parcelZzax, zzcasVar);
        zzb(2, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final byte[] zza(zzcbk zzcbkVar, String str) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcbkVar);
        parcelZzax.writeString(str);
        Parcel parcelZza = zza(9, parcelZzax);
        byte[] bArrCreateByteArray = parcelZza.createByteArray();
        parcelZza.recycle();
        return bArrCreateByteArray;
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final void zzb(zzcas zzcasVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcasVar);
        zzb(6, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final void zzb(zzcav zzcavVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcavVar);
        zzb(13, parcelZzax);
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final String zzc(zzcas zzcasVar) throws RemoteException {
        Parcel parcelZzax = zzax();
        zzed.zza(parcelZzax, zzcasVar);
        Parcel parcelZza = zza(11, parcelZzax);
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.internal.zzcbo
    public final List<zzcav> zzj(String str, String str2, String str3) throws RemoteException {
        Parcel parcelZzax = zzax();
        parcelZzax.writeString(str);
        parcelZzax.writeString(str2);
        parcelZzax.writeString(str3);
        Parcel parcelZza = zza(17, parcelZzax);
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(zzcav.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }
}
