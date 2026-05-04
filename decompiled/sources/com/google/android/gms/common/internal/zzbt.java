package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzbt implements Parcelable.Creator<zzbs> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbs createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        boolean zZzc = false;
        boolean zZzc2 = false;
        ConnectionResult connectionResult = null;
        IBinder iBinderZzr = null;
        int iZzg = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    iBinderZzr = zzbcl.zzr(parcel, i);
                    break;
                case 3:
                    connectionResult = (ConnectionResult) zzbcl.zza(parcel, i, ConnectionResult.CREATOR);
                    break;
                case 4:
                    zZzc2 = zzbcl.zzc(parcel, i);
                    break;
                case 5:
                    zZzc = zzbcl.zzc(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new zzbs(iZzg, iBinderZzr, connectionResult, zZzc2, zZzc);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbs[] newArray(int i) {
        return new zzbs[i];
    }
}
