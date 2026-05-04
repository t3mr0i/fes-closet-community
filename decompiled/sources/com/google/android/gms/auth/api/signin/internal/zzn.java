package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;

/* loaded from: classes.dex */
public final class zzn extends zzbck {
    public static final Parcelable.Creator<zzn> CREATOR = new zzm();
    private Bundle mBundle;
    private int versionCode;
    private int zzecy;

    zzn(int i, int i2, Bundle bundle) {
        this.versionCode = i;
        this.zzecy = i2;
        this.mBundle = bundle;
    }

    public zzn(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        this(1, 1, googleSignInOptionsExtension.toBundle());
    }

    public final int getType() {
        return this.zzecy;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iZze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zzc(parcel, 2, this.zzecy);
        zzbcn.zza(parcel, 3, this.mBundle, false);
        zzbcn.zzai(parcel, iZze);
    }
}
