package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

/* loaded from: classes.dex */
class zzccf extends BroadcastReceiver {
    private static String zzdti = zzccf.class.getName();
    private boolean mRegistered;
    private boolean zzdtj;
    private final zzccw zzikh;

    zzccf(zzccw zzccwVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzccwVar);
        this.zzikh = zzccwVar;
    }

    @Override // android.content.BroadcastReceiver
    @MainThread
    public void onReceive(Context context, Intent intent) throws IllegalStateException {
        this.zzikh.zzwk();
        String action = intent.getAction();
        this.zzikh.zzaum().zzayk().zzj("NetworkBroadcastReceiver received action", action);
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            this.zzikh.zzaum().zzayg().zzj("NetworkBroadcastReceiver received unknown action", action);
            return;
        }
        boolean zZzyx = this.zzikh.zzazb().zzyx();
        if (this.zzdtj != zZzyx) {
            this.zzdtj = zZzyx;
            this.zzikh.zzaul().zzg(new zzccg(this, zZzyx));
        }
    }

    @WorkerThread
    public final void unregister() {
        this.zzikh.zzwk();
        this.zzikh.zzaul().zzuj();
        this.zzikh.zzaul().zzuj();
        if (this.mRegistered) {
            this.zzikh.zzaum().zzayk().log("Unregistering connectivity change receiver");
            this.mRegistered = false;
            this.zzdtj = false;
            try {
                this.zzikh.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zzikh.zzaum().zzaye().zzj("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @WorkerThread
    public final void zzyu() {
        this.zzikh.zzwk();
        this.zzikh.zzaul().zzuj();
        if (this.mRegistered) {
            return;
        }
        this.zzikh.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.zzdtj = this.zzikh.zzazb().zzyx();
        this.zzikh.zzaum().zzayk().zzj("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzdtj));
        this.mRegistered = true;
    }
}
