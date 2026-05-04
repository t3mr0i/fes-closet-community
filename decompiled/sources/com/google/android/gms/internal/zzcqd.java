package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

/* loaded from: classes.dex */
public final class zzcqd extends zzbck {
    public static final Parcelable.Creator<zzcqd> CREATOR = new zzcqe();
    private int zzdxr;
    private zzbq zzjnx;

    zzcqd(int i, zzbq zzbqVar) {
        this.zzdxr = i;
        this.zzjnx = zzbqVar;
    }

    public zzcqd(zzbq zzbqVar) {
        this(1, zzbqVar);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        zzbcn.zza(parcel, 2, (Parcelable) this.zzjnx, i, false);
        zzbcn.zzai(parcel, iZze);
    }
}
