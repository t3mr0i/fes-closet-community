package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
final class zzfs implements zzfr {
    private Handler handler;
    final /* synthetic */ zzfo zzjvs;

    private zzfs(zzfo zzfoVar) {
        this.zzjvs = zzfoVar;
        this.handler = new Handler(this.zzjvs.zzjvg.getMainLooper(), new zzft(this));
    }

    /* synthetic */ zzfs(zzfo zzfoVar, zzfp zzfpVar) {
        this(zzfoVar);
    }

    private final Message obtainMessage() {
        return this.handler.obtainMessage(1, zzfo.zzjvf);
    }

    @Override // com.google.android.gms.tagmanager.zzfr
    public final void cancel() {
        this.handler.removeMessages(1, zzfo.zzjvf);
    }

    @Override // com.google.android.gms.tagmanager.zzfr
    public final void zzbfd() {
        this.handler.removeMessages(1, zzfo.zzjvf);
        this.handler.sendMessage(obtainMessage());
    }

    @Override // com.google.android.gms.tagmanager.zzfr
    public final void zzs(long j) {
        this.handler.removeMessages(1, zzfo.zzjvf);
        this.handler.sendMessageDelayed(obtainMessage(), j);
    }
}
