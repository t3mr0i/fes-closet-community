package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* loaded from: classes.dex */
public abstract class zzcbp extends zzec implements zzcbo {
    public zzcbp() {
        attachInterface(this, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza((zzcbk) zzed.zza(parcel, zzcbk.CREATOR), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                zza((zzcft) zzed.zza(parcel, zzcft.CREATOR), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 3:
            case 8:
            default:
                return false;
            case 4:
                zza((zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zza((zzcbk) zzed.zza(parcel, zzcbk.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zzb((zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                List<zzcft> listZza = zza((zzcas) zzed.zza(parcel, zzcas.CREATOR), zzed.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza);
                break;
            case 9:
                byte[] bArrZza = zza((zzcbk) zzed.zza(parcel, zzcbk.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(bArrZza);
                break;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String strZzc = zzc((zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(strZzc);
                break;
            case 12:
                zza((zzcav) zzed.zza(parcel, zzcav.CREATOR), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                zzb((zzcav) zzed.zza(parcel, zzcav.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                List<zzcft> listZza2 = zza(parcel.readString(), parcel.readString(), zzed.zza(parcel), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza2);
                break;
            case 15:
                List<zzcft> listZza3 = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzed.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza3);
                break;
            case 16:
                List<zzcav> listZza4 = zza(parcel.readString(), parcel.readString(), (zzcas) zzed.zza(parcel, zzcas.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(listZza4);
                break;
            case 17:
                List<zzcav> listZzj = zzj(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(listZzj);
                break;
        }
        return true;
    }
}
