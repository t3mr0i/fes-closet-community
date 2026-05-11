package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzbdk implements Parcelable.Creator<zzbdi> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdi createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        String strZzq = null;
        int iZzg = 0;
        int iZzg2 = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg2 = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 3:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbdi(iZzg2, strZzq, iZzg);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdi[] newArray(int i) {
        return new zzbdi[i];
    }
}
