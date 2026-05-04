package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzbdp implements Parcelable.Creator<zzbdm> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdm createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        zzbdf zzbdfVar = null;
        String strZzq = null;
        int iZzg = 0;
        String strZzq2 = null;
        boolean zZzc = false;
        int iZzg2 = 0;
        boolean zZzc2 = false;
        int iZzg3 = 0;
        int iZzg4 = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg4 = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    iZzg3 = zzbcl.zzg(parcel, i);
                    break;
                case 3:
                    zZzc2 = zzbcl.zzc(parcel, i);
                    break;
                case 4:
                    iZzg2 = zzbcl.zzg(parcel, i);
                    break;
                case 5:
                    zZzc = zzbcl.zzc(parcel, i);
                    break;
                case 6:
                    strZzq2 = zzbcl.zzq(parcel, i);
                    break;
                case 7:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 8:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 9:
                    zzbdfVar = (zzbdf) zzbcl.zza(parcel, i, zzbdf.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbdm(iZzg4, iZzg3, zZzc2, iZzg2, zZzc, strZzq2, iZzg, strZzq, zzbdfVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdm[] newArray(int i) {
        return new zzbdm[i];
    }
}
