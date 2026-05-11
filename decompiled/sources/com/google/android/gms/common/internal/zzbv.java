package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzbv implements Parcelable.Creator<zzbu> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbu createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        Scope[] scopeArr = null;
        int iZzg = 0;
        int iZzg2 = 0;
        int iZzg3 = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg3 = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    iZzg2 = zzbcl.zzg(parcel, i);
                    break;
                case 3:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 4:
                    scopeArr = (Scope[]) zzbcl.zzb(parcel, i, Scope.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbu(iZzg3, iZzg2, iZzg, scopeArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbu[] newArray(int i) {
        return new zzbu[i];
    }
}
