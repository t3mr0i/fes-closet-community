package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

/* loaded from: classes.dex */
public final class zzbu extends zzbck {
    public static final Parcelable.Creator<zzbu> CREATOR = new zzbv();
    private int zzdxr;
    private final int zzfwa;
    private final int zzfwb;

    @Deprecated
    private final Scope[] zzfwc;

    zzbu(int i, int i2, int i3, Scope[] scopeArr) {
        this.zzdxr = i;
        this.zzfwa = i2;
        this.zzfwb = i3;
        this.zzfwc = scopeArr;
    }

    public zzbu(int i, int i2, Scope[] scopeArr) {
        this(1, i, i2, null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        zzbcn.zzc(parcel, 2, this.zzfwa);
        zzbcn.zzc(parcel, 3, this.zzfwb);
        zzbcn.zza(parcel, 4, (Parcelable[]) this.zzfwc, i, false);
        zzbcn.zzai(parcel, iZze);
    }
}
