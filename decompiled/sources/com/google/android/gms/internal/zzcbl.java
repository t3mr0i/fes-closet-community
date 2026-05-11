package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcbl implements Parcelable.Creator<zzcbk> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcbk createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        long jZzi = 0;
        String strZzq = null;
        zzcbh zzcbhVar = null;
        String strZzq2 = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    strZzq2 = zzbcl.zzq(parcel, i);
                    break;
                case 3:
                    zzcbhVar = (zzcbh) zzbcl.zza(parcel, i, zzcbh.CREATOR);
                    break;
                case 4:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 5:
                    jZzi = zzbcl.zzi(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcbk(strZzq2, zzcbhVar, strZzq, jZzi);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcbk[] newArray(int i) {
        return new zzcbk[i];
    }
}
