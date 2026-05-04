package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzbdi extends zzbck {
    public static final Parcelable.Creator<zzbdi> CREATOR = new zzbdk();
    private int versionCode;
    final String zzfwn;
    final int zzfwo;

    zzbdi(int i, String str, int i2) {
        this.versionCode = i;
        this.zzfwn = str;
        this.zzfwo = i2;
    }

    zzbdi(String str, int i) {
        this.versionCode = 1;
        this.zzfwn = str;
        this.zzfwo = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zza(parcel, 2, this.zzfwn, false);
        zzbcn.zzc(parcel, 3, this.zzfwo);
        zzbcn.zzai(parcel, iZze);
    }
}
