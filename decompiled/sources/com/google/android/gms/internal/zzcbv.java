package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;

/* loaded from: classes.dex */
public final class zzcbv extends com.google.android.gms.common.internal.zzd<zzcbo> {
    public zzcbv(Context context, Looper looper, com.google.android.gms.common.internal.zzf zzfVar, com.google.android.gms.common.internal.zzg zzgVar) {
        super(context, looper, 93, zzfVar, zzgVar, null);
    }

    @Override // com.google.android.gms.common.internal.zzd
    public final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
        return iInterfaceQueryLocalInterface instanceof zzcbo ? (zzcbo) iInterfaceQueryLocalInterface : new zzcbq(iBinder);
    }

    @Override // com.google.android.gms.common.internal.zzd
    @NonNull
    protected final String zzhc() {
        return "com.google.android.gms.measurement.START";
    }

    @Override // com.google.android.gms.common.internal.zzd
    @NonNull
    protected final String zzhd() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }
}
