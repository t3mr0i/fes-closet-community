package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzb implements Parcelable.Creator<ConnectionResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ConnectionResult createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        String strZzq = null;
        PendingIntent pendingIntent = null;
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
                    pendingIntent = (PendingIntent) zzbcl.zza(parcel, i, PendingIntent.CREATOR);
                    break;
                case 4:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                default:
                    zzbcl.zzb(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new ConnectionResult(iZzg2, iZzg, pendingIntent, strZzq);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ConnectionResult[] newArray(int i) {
        return new ConnectionResult[i];
    }
}
