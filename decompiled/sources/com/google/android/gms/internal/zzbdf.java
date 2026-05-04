package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class zzbdf extends zzbck {
    public static final Parcelable.Creator<zzbdf> CREATOR = new zzbdg();
    private int zzdxr;
    private final zzbdh zzfwj;

    zzbdf(int i, zzbdh zzbdhVar) {
        this.zzdxr = i;
        this.zzfwj = zzbdhVar;
    }

    private zzbdf(zzbdh zzbdhVar) {
        this.zzdxr = 1;
        this.zzfwj = zzbdhVar;
    }

    public static zzbdf zza(zzbdn<?, ?> zzbdnVar) {
        if (zzbdnVar instanceof zzbdh) {
            return new zzbdf((zzbdh) zzbdnVar);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.zzdxr);
        zzbcn.zza(parcel, 2, (Parcelable) this.zzfwj, i, false);
        zzbcn.zzai(parcel, iZze);
    }

    public final zzbdn<?, ?> zzakq() {
        if (this.zzfwj != null) {
            return this.zzfwj;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
