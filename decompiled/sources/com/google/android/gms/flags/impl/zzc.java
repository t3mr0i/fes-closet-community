package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzc implements Callable<Boolean> {
    private /* synthetic */ SharedPreferences zzhbb;
    private /* synthetic */ String zzhbc;
    private /* synthetic */ Boolean zzhbd;

    zzc(SharedPreferences sharedPreferences, String str, Boolean bool) {
        this.zzhbb = sharedPreferences;
        this.zzhbc = str;
        this.zzhbd = bool;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Boolean call() throws Exception {
        return Boolean.valueOf(this.zzhbb.getBoolean(this.zzhbc, this.zzhbd.booleanValue()));
    }
}
