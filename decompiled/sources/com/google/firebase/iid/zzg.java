package com.google.firebase.iid;

import android.util.Log;

/* loaded from: classes.dex */
final class zzg implements Runnable {
    private /* synthetic */ zzd zzmlb;
    private /* synthetic */ zzf zzmlc;

    zzg(zzf zzfVar, zzd zzdVar) {
        this.zzmlc = zzfVar;
        this.zzmlb = zzdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.zzmlc.zzmla.handleIntent(this.zzmlb.intent);
        this.zzmlb.finish();
    }
}
