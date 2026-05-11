package com.google.firebase.iid;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
final class zzm extends Handler {
    private /* synthetic */ zzl zzmlx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzm(zzl zzlVar, Looper looper) {
        super(looper);
        this.zzmlx = zzlVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        this.zzmlx.zzc(message);
    }
}
