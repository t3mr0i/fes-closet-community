package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzbdx implements Parcelable.Creator<zzbdw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdw createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        zzbdr zzbdrVar = null;
        Parcel parcelZzad = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    parcelZzad = zzbcl.zzad(parcel, i);
                    break;
                case 3:
                    zzbdrVar = (zzbdr) zzbcl.zza(parcel, i, zzbdr.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbdw(iZzg, parcelZzad, zzbdrVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdw[] newArray(int i) {
        return new zzbdw[i];
    }
}
