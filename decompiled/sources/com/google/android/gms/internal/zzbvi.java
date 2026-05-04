package com.google.android.gms.internal;

import android.os.RemoteException;

/* loaded from: classes.dex */
public final class zzbvi extends zzbvd<String> {
    public zzbvi(int i, String str, String str2) {
        super(0, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzbvd
    /* renamed from: zze, reason: merged with bridge method [inline-methods] */
    public final String zza(zzbvl zzbvlVar) {
        try {
            return zzbvlVar.getStringFlagValue(getKey(), zzil(), getSource());
        } catch (RemoteException e) {
            return zzil();
        }
    }
}
