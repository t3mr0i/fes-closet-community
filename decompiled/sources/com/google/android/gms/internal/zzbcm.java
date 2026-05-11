package com.google.android.gms.internal;

import android.os.Parcel;

/* loaded from: classes.dex */
public final class zzbcm extends RuntimeException {
    /* JADX WARN: Illegal instructions before constructor call */
    public zzbcm(String str, Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        super(new StringBuilder(String.valueOf(str).length() + 41).append(str).append(" Parcel: pos=").append(iDataPosition).append(" size=").append(parcel.dataSize()).toString());
    }
}
