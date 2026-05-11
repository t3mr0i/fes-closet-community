package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzbdv implements Parcelable.Creator<zzbds> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbds createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        ArrayList arrayListZzc = null;
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
                    arrayListZzc = zzbcl.zzc(parcel, i, zzbdt.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbds(iZzg, strZzq, arrayListZzc);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbds[] newArray(int i) {
        return new zzbds[i];
    }
}
