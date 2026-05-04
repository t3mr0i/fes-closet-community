package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbcl;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class zzd implements Parcelable.Creator<WakeLockEvent> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WakeLockEvent createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        int iZzg = 0;
        long jZzi = 0;
        int iZzg2 = 0;
        String strZzq = null;
        int iZzg3 = 0;
        ArrayList<String> arrayListZzac = null;
        String strZzq2 = null;
        long jZzi2 = 0;
        int iZzg4 = 0;
        String strZzq3 = null;
        String strZzq4 = null;
        float fZzl = 0.0f;
        long jZzi3 = 0;
        String strZzq5 = null;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 2:
                    jZzi = zzbcl.zzi(parcel, i);
                    break;
                case 3:
                case 7:
                case 9:
                default:
                    zzbcl.zzb(parcel, i);
                    break;
                case 4:
                    strZzq = zzbcl.zzq(parcel, i);
                    break;
                case 5:
                    iZzg3 = zzbcl.zzg(parcel, i);
                    break;
                case 6:
                    arrayListZzac = zzbcl.zzac(parcel, i);
                    break;
                case 8:
                    jZzi2 = zzbcl.zzi(parcel, i);
                    break;
                case 10:
                    strZzq3 = zzbcl.zzq(parcel, i);
                    break;
                case 11:
                    iZzg2 = zzbcl.zzg(parcel, i);
                    break;
                case 12:
                    strZzq2 = zzbcl.zzq(parcel, i);
                    break;
                case 13:
                    strZzq4 = zzbcl.zzq(parcel, i);
                    break;
                case 14:
                    iZzg4 = zzbcl.zzg(parcel, i);
                    break;
                case 15:
                    fZzl = zzbcl.zzl(parcel, i);
                    break;
                case 16:
                    jZzi3 = zzbcl.zzi(parcel, i);
                    break;
                case 17:
                    strZzq5 = zzbcl.zzq(parcel, i);
                    break;
            }
        }
        zzbcl.zzaf(parcel, iZzd);
        return new WakeLockEvent(iZzg, jZzi, iZzg2, strZzq, iZzg3, arrayListZzac, strZzq2, jZzi2, iZzg4, strZzq3, strZzq4, fZzl, jZzi3, strZzq5);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WakeLockEvent[] newArray(int i) {
        return new WakeLockEvent[i];
    }
}
