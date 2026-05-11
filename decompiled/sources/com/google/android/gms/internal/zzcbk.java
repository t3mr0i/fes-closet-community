package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzcbk extends zzbck {
    public static final Parcelable.Creator<zzcbk> CREATOR = new zzcbl();
    public final String name;
    public final String zzimf;
    public final zzcbh zzinq;
    public final long zzinr;

    zzcbk(zzcbk zzcbkVar, long j) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcbkVar);
        this.name = zzcbkVar.name;
        this.zzinq = zzcbkVar.zzinq;
        this.zzimf = zzcbkVar.zzimf;
        this.zzinr = j;
    }

    public zzcbk(String str, zzcbh zzcbhVar, String str2, long j) {
        this.name = str;
        this.zzinq = zzcbhVar;
        this.zzimf = str2;
        this.zzinr = j;
    }

    public final String toString() {
        String str = this.zzimf;
        String str2 = this.name;
        String strValueOf = String.valueOf(this.zzinq);
        return new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length() + String.valueOf(strValueOf).length()).append("origin=").append(str).append(",name=").append(str2).append(",params=").append(strValueOf).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 2, this.name, false);
        zzbcn.zza(parcel, 3, (Parcelable) this.zzinq, i, false);
        zzbcn.zza(parcel, 4, this.zzimf, false);
        zzbcn.zza(parcel, 5, this.zzinr);
        zzbcn.zzai(parcel, iZze);
    }
}
