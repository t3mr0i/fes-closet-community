package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

/* loaded from: classes.dex */
public final class zzcqe implements Parcelable.Creator<zzcqd> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcqd createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        zzbq zzbqVar = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    zzbqVar = (zzbq) zzbcl.zza(parcel, i, zzbq.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcqd(iZzg, zzbqVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcqd[] newArray(int i) {
        return new zzcqd[i];
    }
}
