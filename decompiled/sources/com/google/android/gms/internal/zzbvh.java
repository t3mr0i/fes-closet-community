package com.google.android.gms.internal;

import android.os.RemoteException;

/* loaded from: classes.dex */
public final class zzbvh extends zzbvd<Long> {
    public zzbvh(int i, String str, Long l) {
        super(0, str, l);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzbvd
    /* renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final Long zza(zzbvl zzbvlVar) {
        try {
            return Long.valueOf(zzbvlVar.getLongFlagValue(getKey(), zzil().longValue(), getSource()));
        } catch (RemoteException e) {
            return zzil();
        }
    }
}
