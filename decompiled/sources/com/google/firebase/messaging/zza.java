package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.common.util.zzp;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes.dex */
final class zza {
    private static zza zzmmg;
    private final Context mContext;
    private Bundle zzfqn;
    private Method zzhqr;
    private Method zzhqs;
    private final AtomicInteger zzmmh = new AtomicInteger((int) SystemClock.elapsedRealtime());

    private zza(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @TargetApi(MotionEventCompat.AXIS_SCROLL)
    private final Notification zza(CharSequence charSequence, String str, int i, Integer num, Uri uri, PendingIntent pendingIntent, PendingIntent pendingIntent2, String str2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Notification.Builder smallIcon = new Notification.Builder(this.mContext).setAutoCancel(true).setSmallIcon(i);
        if (!TextUtils.isEmpty(charSequence)) {
            smallIcon.setContentTitle(charSequence);
        }
        if (!TextUtils.isEmpty(str)) {
            smallIcon.setContentText(str);
            smallIcon.setStyle(new Notification.BigTextStyle().bigText(str));
        }
        if (num != null) {
            smallIcon.setColor(num.intValue());
        }
        if (uri != null) {
            smallIcon.setSound(uri);
        }
        if (pendingIntent != null) {
            smallIcon.setContentIntent(pendingIntent);
        }
        if (pendingIntent2 != null) {
            smallIcon.setDeleteIntent(pendingIntent2);
        }
        if (str2 != null) {
            if (this.zzhqr == null) {
                this.zzhqr = zzhq("setChannelId");
            }
            if (this.zzhqr == null) {
                this.zzhqr = zzhq("setChannel");
            }
            if (this.zzhqr == null) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel");
            } else {
                try {
                    this.zzhqr.invoke(smallIcon, str2);
                } catch (IllegalAccessException e) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e);
                } catch (IllegalArgumentException e2) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e2);
                } catch (SecurityException e3) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e3);
                } catch (InvocationTargetException e4) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", e4);
                }
            }
        }
        return smallIcon.build();
    }

    private static void zza(Intent intent, Bundle bundle) {
        for (String str : bundle.keySet()) {
            if (str.startsWith("google.c.a.") || str.equals("from")) {
                intent.putExtra(str, bundle.getString(str));
            }
        }
    }

    static boolean zzad(Bundle bundle) {
        return "1".equals(zze(bundle, "gcm.n.e")) || zze(bundle, "gcm.n.icon") != null;
    }

    @Nullable
    static Uri zzae(@NonNull Bundle bundle) {
        String strZze = zze(bundle, "gcm.n.link_android");
        if (TextUtils.isEmpty(strZze)) {
            strZze = zze(bundle, "gcm.n.link");
        }
        if (TextUtils.isEmpty(strZze)) {
            return null;
        }
        return Uri.parse(strZze);
    }

    static String zzaf(Bundle bundle) {
        String strZze = zze(bundle, "gcm.n.sound2");
        return TextUtils.isEmpty(strZze) ? zze(bundle, "gcm.n.sound") : strZze;
    }

    private final Bundle zzasi() throws PackageManager.NameNotFoundException {
        if (this.zzfqn != null) {
            return this.zzfqn;
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return Bundle.EMPTY;
        }
        this.zzfqn = applicationInfo.metaData;
        return this.zzfqn;
    }

    static String zze(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    static synchronized zza zzeq(Context context) {
        if (zzmmg == null) {
            zzmmg = new zza(context);
        }
        return zzmmg;
    }

    static String zzh(Bundle bundle, String str) {
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf("_loc_key");
        return zze(bundle, strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
    }

    @TargetApi(MotionEventCompat.AXIS_SCROLL)
    private static Method zzhq(String str) {
        try {
            return Notification.Builder.class.getMethod(str, String.class);
        } catch (NoSuchMethodException | SecurityException e) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static Object[] zzi(Bundle bundle, String str) {
        String strValueOf = String.valueOf(str);
        String strValueOf2 = String.valueOf("_loc_args");
        String strZze = zze(bundle, strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf));
        if (TextUtils.isEmpty(strZze)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(strZze);
            String[] strArr = new String[jSONArray.length()];
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = jSONArray.opt(i);
            }
            return strArr;
        } catch (JSONException e) {
            String strValueOf3 = String.valueOf(str);
            String strValueOf4 = String.valueOf("_loc_args");
            String strSubstring = (strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3)).substring(6);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strSubstring).length() + 41 + String.valueOf(strZze).length()).append("Malformed ").append(strSubstring).append(": ").append(strZze).append("  Default value will be used.").toString());
            return null;
        }
    }

    private final String zzj(Bundle bundle, String str) {
        String strZze = zze(bundle, str);
        if (!TextUtils.isEmpty(strZze)) {
            return strZze;
        }
        String strZzh = zzh(bundle, str);
        if (TextUtils.isEmpty(strZzh)) {
            return null;
        }
        Resources resources = this.mContext.getResources();
        int identifier = resources.getIdentifier(strZzh, "string", this.mContext.getPackageName());
        if (identifier == 0) {
            String strValueOf = String.valueOf(str);
            String strValueOf2 = String.valueOf("_loc_key");
            String strSubstring = (strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf)).substring(6);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strSubstring).length() + 49 + String.valueOf(strZzh).length()).append(strSubstring).append(" resource not found: ").append(strZzh).append(" Default value will be used.").toString());
            return null;
        }
        Object[] objArrZzi = zzi(bundle, str);
        if (objArrZzi == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, objArrZzi);
        } catch (MissingFormatArgumentException e) {
            String string = Arrays.toString(objArrZzi);
            Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(strZzh).length() + 58 + String.valueOf(string).length()).append("Missing format argument for ").append(strZzh).append(": ").append(string).append(" Default value will be used.").toString(), e);
            return null;
        }
    }

    private final Integer zzqm(String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.valueOf(Color.parseColor(str));
            } catch (IllegalArgumentException e) {
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 54).append("Color ").append(str).append(" not valid. Notification will use default color.").toString());
            }
        }
        int i = zzasi().getInt("com.google.firebase.messaging.default_notification_color", 0);
        if (i == 0) {
            return null;
        }
        try {
            return Integer.valueOf(ContextCompat.getColor(this.mContext, i));
        } catch (Resources.NotFoundException e2) {
            Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            return null;
        }
    }

    @TargetApi(MotionEventCompat.AXIS_SCROLL)
    private final String zzqn(String str) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (!zzp.isAtLeastO()) {
            return null;
        }
        NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService(NotificationManager.class);
        try {
            if (this.zzhqs == null) {
                this.zzhqs = notificationManager.getClass().getMethod("getNotificationChannel", String.class);
            }
            if (!TextUtils.isEmpty(str)) {
                if (this.zzhqs.invoke(notificationManager, str) != null) {
                    return str;
                }
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(str).length() + 122).append("Notification Channel requested (").append(str).append(") has not been created by the app. Manifest configuration, or default, value will be used.").toString());
            }
            String string = zzasi().getString("com.google.firebase.messaging.default_notification_channel_id");
            if (TextUtils.isEmpty(string)) {
                Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
            } else {
                if (this.zzhqs.invoke(notificationManager, string) != null) {
                    return string;
                }
                Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
            }
            if (this.zzhqs.invoke(notificationManager, "fcm_fallback_notification_channel") == null) {
                Class<?> cls = Class.forName("android.app.NotificationChannel");
                notificationManager.getClass().getMethod("createNotificationChannel", cls).invoke(notificationManager, cls.getConstructor(String.class, CharSequence.class, Integer.TYPE).newInstance("fcm_fallback_notification_channel", this.mContext.getString(R.string.fcm_fallback_notification_channel_label), 3));
            }
            return "fcm_fallback_notification_channel";
        } catch (ClassNotFoundException e) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e);
            return null;
        } catch (IllegalAccessException e2) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e2);
            return null;
        } catch (IllegalArgumentException e3) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e3);
            return null;
        } catch (InstantiationException e4) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e4);
            return null;
        } catch (LinkageError e5) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e5);
            return null;
        } catch (NoSuchMethodException e6) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e6);
            return null;
        } catch (SecurityException e7) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e7);
            return null;
        } catch (InvocationTargetException e8) {
            Log.e("FirebaseMessaging", "Error while setting the notification channel", e8);
            return null;
        }
    }

    private final PendingIntent zzt(Bundle bundle) {
        Intent intent;
        String strZze = zze(bundle, "gcm.n.click_action");
        if (TextUtils.isEmpty(strZze)) {
            Uri uriZzae = zzae(bundle);
            if (uriZzae != null) {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setPackage(this.mContext.getPackageName());
                intent2.setData(uriZzae);
                intent = intent2;
            } else {
                Intent launchIntentForPackage = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
                if (launchIntentForPackage == null) {
                    Log.w("FirebaseMessaging", "No activity found to launch app");
                }
                intent = launchIntentForPackage;
            }
        } else {
            Intent intent3 = new Intent(strZze);
            intent3.setPackage(this.mContext.getPackageName());
            intent3.setFlags(268435456);
            intent = intent3;
        }
        if (intent == null) {
            return null;
        }
        intent.addFlags(67108864);
        Bundle bundle2 = new Bundle(bundle);
        FirebaseMessagingService.zzq(bundle2);
        intent.putExtras(bundle2);
        for (String str : bundle2.keySet()) {
            if (str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                intent.removeExtra(str);
            }
        }
        return PendingIntent.getActivity(this.mContext, this.zzmmh.incrementAndGet(), intent, 1073741824);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final boolean zzs(android.os.Bundle r14) throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instructions count: 614
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.zza.zzs(android.os.Bundle):boolean");
    }
}
