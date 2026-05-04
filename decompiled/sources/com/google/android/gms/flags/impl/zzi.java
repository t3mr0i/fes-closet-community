package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzi implements Callable<String> {
    private /* synthetic */ SharedPreferences zzhbb;
    private /* synthetic */ String zzhbc;
    private /* synthetic */ String zzhbg;

    zzi(SharedPreferences sharedPreferences, String str, String str2) {
        this.zzhbb = sharedPreferences;
        this.zzhbc = str;
        this.zzhbg = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        return this.zzhbb.getString(this.zzhbc, this.zzhbg);
    }
}
