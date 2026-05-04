package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class zzanq implements Parcelable.Creator<zzanp> {
    zzanq() {
    }

    @Override // android.os.Parcelable.Creator
    @Deprecated
    public final /* synthetic */ zzanp createFromParcel(Parcel parcel) {
        return new zzanp(parcel);
    }

    @Override // android.os.Parcelable.Creator
    @Deprecated
    public final /* synthetic */ zzanp[] newArray(int i) {
        return new zzanp[i];
    }
}
