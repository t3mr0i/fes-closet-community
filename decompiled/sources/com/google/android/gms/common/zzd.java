package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzd implements Parcelable.Creator<zzc> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzc createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        String strZzq = null;
        int iZzg = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 2:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzc(strZzq, iZzg);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzc[] newArray(int i) {
        return new zzc[i];
    }
}
