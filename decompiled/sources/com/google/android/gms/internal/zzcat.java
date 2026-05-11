package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcat implements Parcelable.Creator<zzcas> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcas createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        String strZzq = null;
        String strZzq2 = null;
        String strZzq3 = null;
        String strZzq4 = null;
        long jZzi = 0;
        long jZzi2 = 0;
        String strZzq5 = null;
        boolean zZzc = true;
        boolean zZzc2 = false;
        long jZzi3 = -2147483648L;
        String strZzq6 = null;
        long jZzi4 = 0;
        long jZzi5 = 0;
        int iZzg = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 3:
                    strZzq2 = zzbcl.zzq(parcel, i);
                    break;
                case 4:
                    strZzq3 = zzbcl.zzq(parcel, i);
                    break;
                case 5:
                    strZzq4 = zzbcl.zzq(parcel, i);
                    break;
                case 6:
                    jZzi = zzbcl.zzi(parcel, i);
                    break;
                case 7:
                    jZzi2 = zzbcl.zzi(parcel, i);
                    break;
                case 8:
                    strZzq5 = zzbcl.zzq(parcel, i);
                    break;
                case 9:
                    zZzc = zzbcl.zzc(parcel, i);
                    break;
                case 10:
                    zZzc2 = zzbcl.zzc(parcel, i);
                    break;
                case 11:
                    jZzi3 = zzbcl.zzi(parcel, i);
                    break;
                case 12:
                    strZzq6 = zzbcl.zzq(parcel, i);
                    break;
                case 13:
                    jZzi4 = zzbcl.zzi(parcel, i);
                    break;
                case 14:
                    jZzi5 = zzbcl.zzi(parcel, i);
                    break;
                case 15:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcas(strZzq, strZzq2, strZzq3, strZzq4, jZzi, jZzi2, strZzq5, zZzc, zZzc2, jZzi3, strZzq6, jZzi4, jZzi5, iZzg);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcas[] newArray(int i) {
        return new zzcas[i];
    }
}
