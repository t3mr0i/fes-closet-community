package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingReceiver;

/* loaded from: classes.dex */
public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
    @Override // com.google.android.gms.analytics.CampaignTrackingReceiver
    protected final void zzt(Context context, String str) {
        zzcx.zzlt(str);
        zzcx.zzak(context, str);
    }
}
