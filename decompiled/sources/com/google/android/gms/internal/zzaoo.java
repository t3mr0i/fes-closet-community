package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes.dex */
class zzaoo extends BroadcastReceiver {
    private static String zzdti = zzaoo.class.getName();
    private boolean mRegistered;
    private final zzamu zzdoc;
    private boolean zzdtj;

    zzaoo(zzamu zzamuVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzamuVar);
        this.zzdoc = zzamuVar;
    }

    private final void zzyv() {
        this.zzdoc.zzvy();
        this.zzdoc.zzwc();
    }

    private final boolean zzyx() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzdoc.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    return true;
                }
            }
            return false;
        } catch (SecurityException e) {
            return false;
        }
    }

    public final boolean isConnected() {
        if (!this.mRegistered) {
            this.zzdoc.zzvy().zzdp("Connectivity unknown. Receiver not registered");
        }
        return this.zzdtj;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        zzyv();
        String action = intent.getAction();
        this.zzdoc.zzvy().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zZzyx = zzyx();
            if (this.zzdtj != zZzyx) {
                this.zzdtj = zZzyx;
                zzamj zzamjVarZzwc = this.zzdoc.zzwc();
                zzamjVarZzwc.zza("Network connectivity status changed", Boolean.valueOf(zZzyx));
                zzamjVarZzwc.zzwa().zzc(new zzaml(zzamjVarZzwc, zZzyx));
                return;
            }
            return;
        }
        if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
            this.zzdoc.zzvy().zzd("NetworkBroadcastReceiver received unknown action", action);
        } else {
            if (intent.hasExtra(zzdti)) {
                return;
            }
            zzamj zzamjVarZzwc2 = this.zzdoc.zzwc();
            zzamjVarZzwc2.zzdm("Radio powered up");
            zzamjVarZzwc2.zzvs();
        }
    }

    public final void unregister() {
        if (this.mRegistered) {
            this.zzdoc.zzvy().zzdm("Unregistering connectivity change receiver");
            this.mRegistered = false;
            this.zzdtj = false;
            try {
                this.zzdoc.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zzdoc.zzvy().zze("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    public final void zzyu() {
        zzyv();
        if (this.mRegistered) {
            return;
        }
        Context context = this.zzdoc.getContext();
        context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
        intentFilter.addCategory(context.getPackageName());
        context.registerReceiver(this, intentFilter);
        this.zzdtj = zzyx();
        this.zzdoc.zzvy().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzdtj));
        this.mRegistered = true;
    }

    public final void zzyw() {
        Context context = this.zzdoc.getContext();
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzdti, true);
        context.sendOrderedBroadcast(intent, null);
    }
}
