package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.common.api.Status;

@Deprecated
/* loaded from: classes.dex */
public final class zzca {
    private static final Object zzaqc = new Object();
    private static zzca zzfoi;
    private final String mAppId;
    private final Status zzfoj;
    private final boolean zzfok;
    private final boolean zzfol;

    private zzca(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        if (identifier != 0) {
            boolean z = resources.getInteger(identifier) != 0;
            this.zzfol = z ? false : true;
            z = z;
        } else {
            this.zzfol = false;
        }
        this.zzfok = z;
        String strZzcf = com.google.android.gms.common.internal.zzbe.zzcf(context);
        strZzcf = strZzcf == null ? new com.google.android.gms.common.internal.zzbz(context).getString("google_app_id") : strZzcf;
        if (TextUtils.isEmpty(strZzcf)) {
            this.zzfoj = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.mAppId = null;
        } else {
            this.mAppId = strZzcf;
            this.zzfoj = Status.zzfhu;
        }
    }

    public static String zzaie() {
        return zzft("getGoogleAppId").mAppId;
    }

    public static boolean zzaif() {
        return zzft("isMeasurementExplicitlyDisabled").zzfol;
    }

    public static Status zzcb(Context context) {
        Status status;
        com.google.android.gms.common.internal.zzbp.zzb(context, "Context must not be null.");
        synchronized (zzaqc) {
            if (zzfoi == null) {
                zzfoi = new zzca(context);
            }
            status = zzfoi.zzfoj;
        }
        return status;
    }

    private static zzca zzft(String str) {
        zzca zzcaVar;
        synchronized (zzaqc) {
            if (zzfoi == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(str).length() + 34).append("Initialize must be called before ").append(str).append(".").toString());
            }
            zzcaVar = zzfoi;
        }
        return zzcaVar;
    }
}
