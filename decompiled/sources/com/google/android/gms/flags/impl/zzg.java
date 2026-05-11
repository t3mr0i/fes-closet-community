package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzg implements Callable<Long> {
    private /* synthetic */ SharedPreferences zzhbb;
    private /* synthetic */ String zzhbc;
    private /* synthetic */ Long zzhbf;

    zzg(SharedPreferences sharedPreferences, String str, Long l) {
        this.zzhbb = sharedPreferences;
        this.zzhbc = str;
        this.zzhbf = l;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Long call() throws Exception {
        return Long.valueOf(this.zzhbb.getLong(this.zzhbc, this.zzhbf.longValue()));
    }
}
