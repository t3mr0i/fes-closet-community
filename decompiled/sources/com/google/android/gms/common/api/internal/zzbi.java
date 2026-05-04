package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* loaded from: classes.dex */
final class zzbi extends Handler {
    private /* synthetic */ zzbd zzfmu;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbi(zzbd zzbdVar, Looper looper) {
        super(looper);
        this.zzfmu = zzbdVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.zzfmu.zzahh();
                break;
            case 2:
                this.zzfmu.resume();
                break;
            default:
                Log.w("GoogleApiClientImpl", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                break;
        }
    }
}
