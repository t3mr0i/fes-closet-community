package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcbj implements Parcelable.Creator<zzcbh> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcbh createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        Bundle bundleZzs = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 2:
                    bundleZzs = zzbcl.zzs(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcbh(bundleZzs);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcbh[] newArray(int i) {
        return new zzcbh[i];
    }
}
