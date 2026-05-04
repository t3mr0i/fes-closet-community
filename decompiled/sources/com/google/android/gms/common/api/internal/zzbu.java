package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
final class zzbu implements Runnable {
    private /* synthetic */ zzbr zzfoc;
    private /* synthetic */ ConnectionResult zzfod;

    zzbu(zzbr zzbrVar, ConnectionResult connectionResult) {
        this.zzfoc = zzbrVar;
        this.zzfod = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzfoc.onConnectionFailed(this.zzfod);
    }
}
