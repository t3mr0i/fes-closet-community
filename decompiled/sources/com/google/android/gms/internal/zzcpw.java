package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcpw implements Parcelable.Creator<zzcpv> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcpv createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        Intent intent = null;
        int iZzg = 0;
        int iZzg2 = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg2 = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 3:
                    intent = (Intent) zzbcl.zza(parcel, i, Intent.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcpv(iZzg2, iZzg, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcpv[] newArray(int i) {
        return new zzcpv[i];
    }
}
