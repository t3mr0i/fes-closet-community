package com.google.android.gms.internal;

import android.os.RemoteException;

/* loaded from: classes.dex */
public final class zzbvg extends zzbvd<Integer> {
    public zzbvg(int i, String str, Integer num) {
        super(0, str, num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.zzbvd
    /* renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final Integer zza(zzbvl zzbvlVar) {
        try {
            return Integer.valueOf(zzbvlVar.getIntFlagValue(getKey(), zzil().intValue(), getSource()));
        } catch (RemoteException e) {
            return zzil();
        }
    }
}
