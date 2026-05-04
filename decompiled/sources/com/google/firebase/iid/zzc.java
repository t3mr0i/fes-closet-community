package com.google.firebase.iid;

import android.content.Intent;

/* loaded from: classes.dex */
final class zzc implements Runnable {
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ Intent zzmku;
    private /* synthetic */ zzb zzmkv;

    zzc(zzb zzbVar, Intent intent, Intent intent2) {
        this.zzmkv = zzbVar;
        this.val$intent = intent;
        this.zzmku = intent2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzmkv.handleIntent(this.val$intent);
        this.zzmkv.zzm(this.zzmku);
    }
}
