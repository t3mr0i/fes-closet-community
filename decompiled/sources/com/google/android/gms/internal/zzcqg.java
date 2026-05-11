package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbs;

/* loaded from: classes.dex */
public final class zzcqg implements Parcelable.Creator<zzcqf> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcqf createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        zzbs zzbsVar = null;
        ConnectionResult connectionResult = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    connectionResult = (ConnectionResult) zzbcl.zza(parcel, i, ConnectionResult.CREATOR);
                    break;
                case 3:
                    zzbsVar = (zzbs) zzbcl.zza(parcel, i, zzbs.CREATOR);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzcqf(iZzg, connectionResult, zzbsVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzcqf[] newArray(int i) {
        return new zzcqf[i];
    }
}
