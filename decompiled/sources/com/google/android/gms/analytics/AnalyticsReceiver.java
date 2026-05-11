package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.internal.zzaou;

/* loaded from: classes.dex */
public final class AnalyticsReceiver extends BroadcastReceiver {
    private zzaou zzdjl;

    @Override // android.content.BroadcastReceiver
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public final void onReceive(Context context, Intent intent) throws PackageManager.NameNotFoundException {
        if (this.zzdjl == null) {
            this.zzdjl = new zzaou();
        }
        zzaou.onReceive(context, intent);
    }
}
