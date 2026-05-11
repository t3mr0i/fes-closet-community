package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class zzft implements Handler.Callback {
    private /* synthetic */ zzfs zzjvt;

    zzft(zzfs zzfsVar) {
        this.zzjvt = zzfsVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (1 == message.what && zzfo.zzjvf.equals(message.obj)) {
            this.zzjvt.zzjvs.dispatch();
            if (!this.zzjvt.zzjvs.isPowerSaveMode()) {
                this.zzjvt.zzs(this.zzjvt.zzjvs.zzjvj);
            }
        }
        return true;
    }
}
