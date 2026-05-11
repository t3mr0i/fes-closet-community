package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzamu;
import com.google.android.gms.internal.zzanv;
import com.google.android.gms.internal.zzaon;
import com.google.android.gms.internal.zzapd;

/* loaded from: classes.dex */
public class CampaignTrackingReceiver extends BroadcastReceiver {
    private static Boolean zzdjp;

    public static boolean zzbe(Context context) throws PackageManager.NameNotFoundException {
        zzbp.zzu(context);
        if (zzdjp != null) {
            return zzdjp.booleanValue();
        }
        boolean zZza = zzapd.zza(context, "com.google.android.gms.analytics.CampaignTrackingReceiver", true);
        zzdjp = Boolean.valueOf(zZza);
        return zZza;
    }

    @Override // android.content.BroadcastReceiver
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public void onReceive(Context context, Intent intent) {
        zzamu zzamuVarZzbg = zzamu.zzbg(context);
        zzaon zzaonVarZzvy = zzamuVarZzbg.zzvy();
        if (intent == null) {
            zzaonVarZzvy.zzdp("CampaignTrackingReceiver received null intent");
            return;
        }
        String stringExtra = intent.getStringExtra("referrer");
        String action = intent.getAction();
        zzaonVarZzvy.zza("CampaignTrackingReceiver received", action);
        if (!"com.android.vending.INSTALL_REFERRER".equals(action) || TextUtils.isEmpty(stringExtra)) {
            zzaonVarZzvy.zzdp("CampaignTrackingReceiver received unexpected intent without referrer extra");
            return;
        }
        zzt(context, stringExtra);
        int iZzxw = zzanv.zzxw();
        if (stringExtra.length() > iZzxw) {
            zzaonVarZzvy.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", Integer.valueOf(stringExtra.length()), Integer.valueOf(iZzxw));
            stringExtra = stringExtra.substring(0, iZzxw);
        }
        zzamuVarZzbg.zzwc().zza(stringExtra, (Runnable) new zzc(this, goAsync()));
    }

    protected void zzt(Context context, String str) {
    }
}
