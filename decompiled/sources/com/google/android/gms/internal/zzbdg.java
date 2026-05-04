package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzbdg implements Parcelable.Creator<zzbdf> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdf createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        zzbdh zzbdhVar = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    zzbdhVar = (zzbdh) zzbcl.zza(parcel, i, zzbdh.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbdf(iZzg, zzbdhVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdf[] newArray(int i) {
        return new zzbdf[i];
    }
}
