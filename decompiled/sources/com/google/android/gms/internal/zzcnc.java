package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcnc implements Parcelable.Creator<zzcmw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcmw createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        byte[][] bArrZzu = null;
        int[] iArrZzw = null;
        byte[][] bArrZzu2 = null;
        byte[][] bArrZzu3 = null;
        byte[][] bArrZzu4 = null;
        byte[][] bArrZzu5 = null;
        byte[] bArrZzt = null;
        String strZzq = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 3:
                    bArrZzt = zzbcl.zzt(parcel, i);
                    break;
                case 4:
                    bArrZzu5 = zzbcl.zzu(parcel, i);
                    break;
                case 5:
                    bArrZzu4 = zzbcl.zzu(parcel, i);
                    break;
                case 6:
                    bArrZzu3 = zzbcl.zzu(parcel, i);
                    break;
                case 7:
                    bArrZzu2 = zzbcl.zzu(parcel, i);
                    break;
                case 8:
                    iArrZzw = zzbcl.zzw(parcel, i);
                    break;
                case 9:
                    bArrZzu = zzbcl.zzu(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcmw(strZzq, bArrZzt, bArrZzu5, bArrZzu4, bArrZzu3, bArrZzu2, iArrZzw, bArrZzu);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcmw[] newArray(int i) {
        return new zzcmw[i];
    }
}
