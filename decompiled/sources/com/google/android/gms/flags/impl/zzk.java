package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzk implements Callable<SharedPreferences> {
    private /* synthetic */ Context zzanz;

    zzk(Context context) {
        this.zzanz = context;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ SharedPreferences call() throws Exception {
        return this.zzanz.getSharedPreferences("google_sdk_flags", 0);
    }
}
