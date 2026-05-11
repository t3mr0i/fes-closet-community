package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcfu implements Parcelable.Creator<zzcft> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcft createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        long jZzi = 0;
        Double dZzo = null;
        String strZzq = null;
        String strZzq2 = null;
        Float fZzm = null;
        Long lZzj = null;
        String strZzq3 = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    strZzq3 = zzbcl.zzq(parcel, i);
                    break;
                case 3:
                    jZzi = zzbcl.zzi(parcel, i);
                    break;
                case 4:
                    lZzj = zzbcl.zzj(parcel, i);
                    break;
                case 5:
                    fZzm = zzbcl.zzm(parcel, i);
                    break;
                case 6:
                    strZzq2 = zzbcl.zzq(parcel, i);
                    break;
                case 7:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 8:
                    dZzo = zzbcl.zzo(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcft(iZzg, strZzq3, jZzi, lZzj, fZzm, strZzq2, strZzq, dZzo);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcft[] newArray(int i) {
        return new zzcft[i];
    }
}
