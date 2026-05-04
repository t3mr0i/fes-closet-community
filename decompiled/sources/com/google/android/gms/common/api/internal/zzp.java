package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
final class zzp {
    private final int zzfix;
    private final ConnectionResult zzfiy;

    zzp(ConnectionResult connectionResult, int i) {
        com.google.android.gms.common.internal.zzbp.zzu(connectionResult);
        this.zzfiy = connectionResult;
        this.zzfix = i;
    }

    final int zzagc() {
        return this.zzfix;
    }

    final ConnectionResult zzagd() {
        return this.zzfiy;
    }
}
