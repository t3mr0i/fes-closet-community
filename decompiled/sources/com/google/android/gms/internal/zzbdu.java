package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzbdu implements Parcelable.Creator<zzbdr> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdr createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        String strZzq = null;
        ArrayList arrayListZzc = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    arrayListZzc = zzbcl.zzc(parcel, i, zzbds.CREATOR);
                    break;
                case 3:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbdr(iZzg, arrayListZzc, strZzq);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbdr[] newArray(int i) {
        return new zzbdr[i];
    }
}
