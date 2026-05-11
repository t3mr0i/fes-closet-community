package com.google.android.gms.common.api.internal;

import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

/* loaded from: classes.dex */
final class zzq implements Runnable {
    private final zzp zzfiz;
    final /* synthetic */ zzo zzfja;

    zzq(zzo zzoVar, zzp zzpVar) {
        this.zzfja = zzoVar;
        this.zzfiz = zzpVar;
    }

    @Override // java.lang.Runnable
    @MainThread
    public final void run() {
        if (this.zzfja.mStarted) {
            ConnectionResult connectionResultZzagd = this.zzfiz.zzagd();
            if (connectionResultZzagd.hasResolution()) {
                this.zzfja.zzfon.startActivityForResult(GoogleApiActivity.zza(this.zzfja.getActivity(), connectionResultZzagd.getResolution(), this.zzfiz.zzagc(), false), 1);
                return;
            }
            if (this.zzfja.zzfhk.isUserResolvableError(connectionResultZzagd.getErrorCode())) {
                this.zzfja.zzfhk.zza(this.zzfja.getActivity(), this.zzfja.zzfon, connectionResultZzagd.getErrorCode(), 2, this.zzfja);
            } else if (connectionResultZzagd.getErrorCode() != 18) {
                this.zzfja.zza(connectionResultZzagd, this.zzfiz.zzagc());
            } else {
                GoogleApiAvailability.zza(this.zzfja.getActivity().getApplicationContext(), new zzr(this, GoogleApiAvailability.zza(this.zzfja.getActivity(), this.zzfja)));
            }
        }
    }
}
