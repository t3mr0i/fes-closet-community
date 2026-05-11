package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.tagmanager.ContainerHolder;

/* loaded from: classes.dex */
final class zzx extends Handler {
    private final ContainerHolder.ContainerAvailableListener zzjpm;
    private /* synthetic */ zzv zzjpn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzx(zzv zzvVar, ContainerHolder.ContainerAvailableListener containerAvailableListener, Looper looper) {
        super(looper);
        this.zzjpn = zzvVar;
        this.zzjpm = containerAvailableListener;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.zzjpm.onContainerAvailable(this.zzjpn, (String) message.obj);
                break;
            default:
                zzdj.e("Don't know how to handle this message.");
                break;
        }
    }
}
