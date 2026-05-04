package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzg implements Parcelable.Creator<Status> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Status createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        PendingIntent pendingIntent = null;
        String strZzq = null;
        int iZzg = 0;
        int iZzg2 = 0;
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
                    pendingIntent = (PendingIntent) zzbcl.zza(parcel, i, PendingIntent.CREATOR);
                    break;
                case 1000:
                    iZzg2 = zzbcl.zzg(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new Status(iZzg2, iZzg, strZzq, pendingIntent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Status[] newArray(int i) {
        return new Status[i];
    }
}
