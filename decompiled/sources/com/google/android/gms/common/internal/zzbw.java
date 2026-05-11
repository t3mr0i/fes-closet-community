package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.view.View;

/* loaded from: classes.dex */
public final class zzbw extends com.google.android.gms.dynamic.zzp<zzbc> {
    private static final zzbw zzfwd = new zzbw();

    private zzbw() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View zzc(Context context, int i, int i2) throws com.google.android.gms.dynamic.zzq {
        return zzfwd.zzd(context, i, i2);
    }

    private final View zzd(Context context, int i, int i2) throws com.google.android.gms.dynamic.zzq {
        try {
            zzbu zzbuVar = new zzbu(i, i2, null);
            return (View) com.google.android.gms.dynamic.zzn.zzx(zzcu(context).zza(com.google.android.gms.dynamic.zzn.zzw(context), zzbuVar));
        } catch (Exception e) {
            throw new com.google.android.gms.dynamic.zzq(new StringBuilder(64).append("Could not get button with size ").append(i).append(" and color ").append(i2).toString(), e);
        }
    }

    @Override // com.google.android.gms.dynamic.zzp
    public final /* synthetic */ zzbc zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        return iInterfaceQueryLocalInterface instanceof zzbc ? (zzbc) iInterfaceQueryLocalInterface : new zzbd(iBinder);
    }
}
