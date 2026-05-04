package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzf implements Parcelable.Creator<Scope> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Scope createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
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
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new Scope(iZzg, strZzq);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Scope[] newArray(int i) {
        return new Scope[i];
    }
}
