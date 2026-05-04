package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbcl;

/* loaded from: classes.dex */
public final class zzf implements Parcelable.Creator<DataHolder> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataHolder createFromParcel(Parcel parcel) {
        int iZzd = zzbcl.zzd(parcel);
        Bundle bundleZzs = null;
        int iZzg = 0;
        CursorWindow[] cursorWindowArr = null;
        String[] strArrZzaa = null;
        int iZzg2 = 0;
        while (parcel.dataPosition() < iZzd) {
            int i = parcel.readInt();
            switch (65535 & i) {
                case 1:
                    strArrZzaa = zzbcl.zzaa(parcel, i);
                    break;
                case 2:
                    cursorWindowArr = (CursorWindow[]) zzbcl.zzb(parcel, i, CursorWindow.CREATOR);
                    break;
                case 3:
                    iZzg = zzbcl.zzg(parcel, i);
                    break;
                case 4:
                    bundleZzs = zzbcl.zzs(parcel, i);
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
        DataHolder dataHolder = new DataHolder(iZzg2, strArrZzaa, cursorWindowArr, iZzg, bundleZzs);
        dataHolder.zzaiw();
        return dataHolder;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataHolder[] newArray(int i) {
        return new DataHolder[i];
    }
}
