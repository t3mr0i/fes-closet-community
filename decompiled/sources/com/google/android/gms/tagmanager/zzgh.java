package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.Logger;

/* loaded from: classes.dex */
final class zzgh implements Logger {
    zzgh() {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void error(Exception exc) {
        zzdj.zzb("", exc);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void error(String str) {
        zzdj.e(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final int getLogLevel() {
        switch (zzdj.zzjst) {
            case 2:
                return 0;
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
            default:
                return 3;
        }
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void info(String str) {
        zzdj.zzcq(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void setLogLevel(int i) {
        zzdj.zzcr("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void verbose(String str) {
        zzdj.v(str);
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void warn(String str) {
        zzdj.zzcr(str);
    }
}
