package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
final class zzck extends Handler {
    private /* synthetic */ zzcj zzfov;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzck(zzcj zzcjVar, Looper looper) {
        super(looper);
        this.zzfov = zzcjVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        com.google.android.gms.common.internal.zzbp.zzbh(message.what == 1);
        this.zzfov.zzb((zzcm) message.obj);
    }
}
