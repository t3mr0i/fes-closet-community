package com.google.android.gms.internal;

import android.os.RemoteException;

/* loaded from: classes.dex */
public final class zzbvf extends zzbvd<Boolean> {
    public zzbvf(int i, String str, Boolean bool) {
        super(0, str, bool);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzbvd
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final Boolean zza(zzbvl zzbvlVar) {
        try {
            return Boolean.valueOf(zzbvlVar.getBooleanFlagValue(getKey(), zzil().booleanValue(), getSource()));
        } catch (RemoteException e) {
            return zzil();
        }
    }
}
