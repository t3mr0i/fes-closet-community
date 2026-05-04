package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzbdt extends zzbck {
    public static final Parcelable.Creator<zzbdt> CREATOR = new zzbdq();
    final String key;
    private int versionCode;
    final zzbdm<?, ?> zzfxd;

    zzbdt(int i, String str, zzbdm<?, ?> zzbdmVar) {
        this.versionCode = i;
        this.key = str;
        this.zzfxd = zzbdmVar;
    }

    zzbdt(String str, zzbdm<?, ?> zzbdmVar) {
        this.versionCode = 1;
        this.key = str;
        this.zzfxd = zzbdmVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zza(parcel, 2, this.key, false);
        zzbcn.zza(parcel, 3, (Parcelable) this.zzfxd, i, false);
        zzbcn.zzai(parcel, iZze);
    }
}
