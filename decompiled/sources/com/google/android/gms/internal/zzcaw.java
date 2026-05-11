package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcaw implements Parcelable.Creator<zzcav> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcav createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        String strZzq = null;
        String strZzq2 = null;
        zzcft zzcftVar = null;
        long jZzi = 0;
        boolean zZzc = false;
        String strZzq3 = null;
        zzcbk zzcbkVar = null;
        long jZzi2 = 0;
        zzcbk zzcbkVar2 = null;
        long jZzi3 = 0;
        zzcbk zzcbkVar3 = null;
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
                    strZzq2 = zzbcl.zzq(parcel, i);
                    break;
                case 4:
                    zzcftVar = (zzcft) zzbcl.zza(parcel, i, zzcft.CREATOR);
                    break;
                case 5:
                    jZzi = zzbcl.zzi(parcel, i);
                    break;
                case 6:
                    zZzc = zzbcl.zzc(parcel, i);
                    break;
                case 7:
                    strZzq3 = zzbcl.zzq(parcel, i);
                    break;
                case 8:
                    zzcbkVar = (zzcbk) zzbcl.zza(parcel, i, zzcbk.CREATOR);
                    break;
                case 9:
                    jZzi2 = zzbcl.zzi(parcel, i);
                    break;
                case 10:
                    zzcbkVar2 = (zzcbk) zzbcl.zza(parcel, i, zzcbk.CREATOR);
                    break;
                case 11:
                    jZzi3 = zzbcl.zzi(parcel, i);
                    break;
                case 12:
                    zzcbkVar3 = (zzcbk) zzbcl.zza(parcel, i, zzcbk.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcav(iZzg, strZzq, strZzq2, zzcftVar, jZzi, zZzc, strZzq3, zzcbkVar, jZzi2, zzcbkVar2, jZzi3, zzcbkVar3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcav[] newArray(int i) {
        return new zzcav[i];
    }
}
