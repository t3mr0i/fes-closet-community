package com.google.android.gms.internal;

import android.os.Build;

/* loaded from: classes.dex */
public final class zzaoc {
    public static int version() {
        try {
            return Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            zzaom.zzf("Invalid version number", Build.VERSION.SDK);
            return 0;
        }
    }
}
