package com.google.android.gms.tagmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes.dex */
class zzdo extends BroadcastReceiver {
    private static String zzdti = zzdo.class.getName();
    private final zzfn zzjsu;

    zzdo(zzfn zzfnVar) {
        this.zzjsu = zzfnVar;
    }

    public static void zzdx(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzdti, true);
        context.sendBroadcast(intent);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            if (!"com.google.analytics.RADIO_POWERED".equals(action) || intent.hasExtra(zzdti)) {
                return;
            }
            this.zzjsu.zzbez();
            return;
        }
        Bundle extras = intent.getExtras();
        Boolean boolValueOf = Boolean.FALSE;
        if (extras != null) {
            boolValueOf = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
        }
        this.zzjsu.zzbv(!boolValueOf.booleanValue());
    }
}
