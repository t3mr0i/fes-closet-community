package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R;

/* loaded from: classes.dex */
public final class zzbz {
    private final Resources zzfwe;
    private final String zzfwf;

    public zzbz(Context context) {
        zzbp.zzu(context);
        this.zzfwe = context.getResources();
        this.zzfwf = this.zzfwe.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
    }

    public final String getString(String str) {
        int identifier = this.zzfwe.getIdentifier(str, "string", this.zzfwf);
        if (identifier == 0) {
            return null;
        }
        return this.zzfwe.getString(identifier);
    }
}
