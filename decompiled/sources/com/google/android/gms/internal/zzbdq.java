package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzbdq implements Parcelable.Creator<zzbdt> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdt createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        zzbdm zzbdmVar = null;
        String strZzq = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 3:
                    zzbdmVar = (zzbdm) zzbcl.zza(parcel, i, zzbdm.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbdt(iZzg, strZzq, zzbdmVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdt[] newArray(int i) {
        return new zzbdt[i];
    }
}
