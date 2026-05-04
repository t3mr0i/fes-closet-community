package jp.co.sony.fes.nativebridge;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sony.cdp.plugin.nativebridge.Gate;

/* loaded from: classes.dex */
public class BatteryStatus extends Gate {
    private static final String TAG = "[jp.co.sony.fes.nativebridge][BatteryStatus] ";
    private Intent battery = null;

    public void getLevel() {
        Context context = this.cordova.getActivity().getApplicationContext();
        IntentFilter ifilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        this.battery = context.registerReceiver(null, ifilter);
        int level = this.battery.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1);
        int scale = this.battery.getIntExtra("scale", -1);
        float batteryPct = (level / scale) * 100.0f;
        returnParams(Float.valueOf(batteryPct));
    }

    public void isPlugged() {
        Context context = this.cordova.getActivity().getApplicationContext();
        IntentFilter ifilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        this.battery = context.registerReceiver(null, ifilter);
        int status = this.battery.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        boolean isCharging = status == 2 || status == 5;
        returnParams(Boolean.valueOf(isCharging));
    }
}
