package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* loaded from: classes.dex */
final class zzbn extends Handler {
    private /* synthetic */ zzbl zzfnh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbn(zzbl zzblVar, Looper looper) {
        super(looper);
        this.zzfnh = zzblVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                ((zzbm) message.obj).zzc(this.zzfnh);
                return;
            case 2:
                throw ((RuntimeException) message.obj);
            default:
                Log.w("GACStateManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                return;
        }
    }
}
