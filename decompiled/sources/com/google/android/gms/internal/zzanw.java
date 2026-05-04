package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

/* loaded from: classes.dex */
final class zzanw implements Logger {
    private boolean zzdka;
    private int zzdqq = 2;

    zzanw() {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void error(Exception exc) {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void error(String str) {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final int getLogLevel() {
        return this.zzdqq;
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void info(String str) {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void setLogLevel(int i) {
        this.zzdqq = i;
        if (this.zzdka) {
            return;
        }
        String str = zzaod.zzdra.get();
        String str2 = zzaod.zzdra.get();
        Log.i(str, new StringBuilder(String.valueOf(str2).length() + 91).append("Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(str2).append(" DEBUG").toString());
        this.zzdka = true;
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void verbose(String str) {
    }

    @Override // com.google.android.gms.analytics.Logger
    public final void warn(String str) {
    }
}
