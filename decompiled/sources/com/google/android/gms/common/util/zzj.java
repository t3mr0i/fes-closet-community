package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: classes.dex */
public final class zzj {
    private static long zzfyx;
    private static IntentFilter zzfyw = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static float zzfyy = Float.NaN;

    @TargetApi(20)
    public static int zzcm(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent intentRegisterReceiver = context.getApplicationContext().registerReceiver(null, zzfyw);
        boolean z = ((intentRegisterReceiver == null ? 0 : intentRegisterReceiver.getIntExtra("plugged", 0)) & 7) != 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        return ((zzp.zzalj() ? powerManager.isInteractive() : powerManager.isScreenOn() ? 1 : 0) << 1) | (z ? 1 : 0);
    }

    public static synchronized float zzcn(Context context) {
        float f;
        if (SystemClock.elapsedRealtime() - zzfyx >= 60000 || Float.isNaN(zzfyy)) {
            if (context.getApplicationContext().registerReceiver(null, zzfyw) != null) {
                zzfyy = r0.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1) / r0.getIntExtra("scale", -1);
            }
            zzfyx = SystemClock.elapsedRealtime();
            f = zzfyy;
        } else {
            f = zzfyy;
        }
        return f;
    }
}
