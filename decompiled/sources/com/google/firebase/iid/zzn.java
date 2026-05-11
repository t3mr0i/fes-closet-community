package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: classes.dex */
final class zzn extends BroadcastReceiver {
    private /* synthetic */ zzl zzmlx;

    zzn(zzl zzlVar) {
        this.zzmlx = zzlVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            String strValueOf = String.valueOf(intent.getExtras());
            Log.d("InstanceID/Rpc", new StringBuilder(String.valueOf(strValueOf).length() + 44).append("Received GSF callback via dynamic receiver: ").append(strValueOf).toString());
        }
        this.zzmlx.zzi(intent);
    }
}
