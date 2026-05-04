package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.iid.MessengerCompat;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.util.Iterator;
import java.util.Random;

/* loaded from: classes.dex */
public final class zzl {
    private static PendingIntent zzhrl;
    private static String zzhtv = null;
    private static boolean zzhtw = false;
    private static int zzhtx = 0;
    private static int zzhty = 0;
    private static int zzhtz = 0;
    private static BroadcastReceiver zzhua = null;
    private Context zzahy;
    private Messenger zzhrp;
    private Messenger zzhuc;
    private MessengerCompat zzhud;
    private long zzhue;
    private long zzhuf;
    private int zzhug;
    private int zzhuh;
    private long zzhui;
    private final SimpleArrayMap<String, zzp> zzmlw = new SimpleArrayMap<>();

    public zzl(Context context) {
        this.zzahy = context;
    }

    private static String zza(KeyPair keyPair, String... strArr) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        try {
            byte[] bytes = TextUtils.join("\n", strArr).getBytes("UTF-8");
            try {
                PrivateKey privateKey = keyPair.getPrivate();
                Signature signature = Signature.getInstance(privateKey instanceof RSAPrivateKey ? "SHA256withRSA" : "SHA256withECDSA");
                signature.initSign(privateKey);
                signature.update(bytes);
                return FirebaseInstanceId.zzm(signature.sign());
            } catch (GeneralSecurityException e) {
                Log.e("InstanceID/Rpc", "Unable to sign registration request", e);
                return null;
            }
        } catch (UnsupportedEncodingException e2) {
            Log.e("InstanceID/Rpc", "Unable to encode string", e2);
            return null;
        }
    }

    private static boolean zza(PackageManager packageManager) {
        Iterator<ResolveInfo> it = packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
        while (it.hasNext()) {
            if (zza(packageManager, it.next().activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                zzhtw = true;
                return true;
            }
        }
        return false;
    }

    private static boolean zza(PackageManager packageManager, String str, String str2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", str) == 0) {
            return zzb(packageManager, str);
        }
        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(str).length() + 56 + String.valueOf(str2).length()).append("Possible malicious package ").append(str).append(" declares ").append(str2).append(" without permission").toString());
        return false;
    }

    private final void zzasu() {
        if (this.zzhrp != null) {
            return;
        }
        zzdf(this.zzahy);
        this.zzhrp = new Messenger(new zzm(this, Looper.getMainLooper()));
    }

    public static synchronized String zzasv() {
        int i;
        i = zzhtz;
        zzhtz = i + 1;
        return Integer.toString(i);
    }

    private final Intent zzb(Bundle bundle, KeyPair keyPair) throws IOException, RemoteException {
        String strZzasv = zzasv();
        zzo zzoVar = new zzo(null);
        synchronized (this.zzmlw) {
            this.zzmlw.put(strZzasv, zzoVar);
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzhui != 0 && jElapsedRealtime <= this.zzhui) {
            Log.w("InstanceID/Rpc", new StringBuilder(78).append("Backoff mode, next request attempt: ").append(this.zzhui - jElapsedRealtime).append(" interval: ").append(this.zzhuh).toString());
            throw new IOException("RETRY_LATER");
        }
        zzasu();
        if (zzhtv == null) {
            throw new IOException("MISSING_INSTANCEID_SERVICE");
        }
        this.zzhue = SystemClock.elapsedRealtime();
        Intent intent = new Intent(zzhtw ? "com.google.iid.TOKEN_REQUEST" : "com.google.android.c2dm.intent.REGISTER");
        intent.setPackage(zzhtv);
        bundle.putString("gmsv", Integer.toString(FirebaseInstanceId.zzao(this.zzahy, zzdf(this.zzahy))));
        bundle.putString("osv", Integer.toString(Build.VERSION.SDK_INT));
        bundle.putString("app_ver", Integer.toString(FirebaseInstanceId.zzej(this.zzahy)));
        bundle.putString("app_ver_name", FirebaseInstanceId.zzdd(this.zzahy));
        bundle.putString("cliv", "fiid-11400000");
        bundle.putString("appid", FirebaseInstanceId.zza(keyPair));
        String strZzm = FirebaseInstanceId.zzm(keyPair.getPublic().getEncoded());
        bundle.putString("pub2", strZzm);
        bundle.putString("sig", zza(keyPair, this.zzahy.getPackageName(), strZzm));
        intent.putExtras(bundle);
        zzd(this.zzahy, intent);
        this.zzhue = SystemClock.elapsedRealtime();
        intent.putExtra("kid", new StringBuilder(String.valueOf(strZzasv).length() + 5).append("|ID|").append(strZzasv).append("|").toString());
        intent.putExtra("X-kid", new StringBuilder(String.valueOf(strZzasv).length() + 5).append("|ID|").append(strZzasv).append("|").toString());
        boolean zEquals = "com.google.android.gsf".equals(zzhtv);
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            String strValueOf = String.valueOf(intent.getExtras());
            Log.d("InstanceID/Rpc", new StringBuilder(String.valueOf(strValueOf).length() + 8).append("Sending ").append(strValueOf).toString());
        }
        if (zEquals) {
            synchronized (this) {
                if (zzhua == null) {
                    zzhua = new zzn(this);
                    if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        Log.d("InstanceID/Rpc", "Registered GSF callback receiver");
                    }
                    IntentFilter intentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
                    intentFilter.addCategory(this.zzahy.getPackageName());
                    this.zzahy.registerReceiver(zzhua, intentFilter, "com.google.android.c2dm.permission.SEND", null);
                }
            }
            this.zzahy.startService(intent);
        } else {
            intent.putExtra("google.messenger", this.zzhrp);
            if (this.zzhuc != null || this.zzhud != null) {
                Message messageObtain = Message.obtain();
                messageObtain.obj = intent;
                try {
                    if (this.zzhuc != null) {
                        this.zzhuc.send(messageObtain);
                    } else {
                        this.zzhud.send(messageObtain);
                    }
                } catch (RemoteException e) {
                    if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        Log.d("InstanceID/Rpc", "Messenger failed, fallback to startService");
                    }
                }
            } else if (zzhtw) {
                this.zzahy.sendBroadcast(intent);
            } else {
                this.zzahy.startService(intent);
            }
        }
        try {
            Intent intentZzbyr = zzoVar.zzbyr();
            synchronized (this.zzmlw) {
                this.zzmlw.remove(strZzasv);
            }
            return intentZzbyr;
        } catch (Throwable th) {
            synchronized (this.zzmlw) {
                this.zzmlw.remove(strZzasv);
                throw th;
            }
        }
    }

    private final void zzb(String str, Intent intent) {
        synchronized (this.zzmlw) {
            zzp zzpVarRemove = this.zzmlw.remove(str);
            if (zzpVarRemove != null) {
                zzpVarRemove.zzq(intent);
            } else {
                String strValueOf = String.valueOf(str);
                Log.w("InstanceID/Rpc", strValueOf.length() != 0 ? "Missing callback for ".concat(strValueOf) : new String("Missing callback for "));
            }
        }
    }

    private static boolean zzb(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            zzhtv = applicationInfo.packageName;
            zzhty = applicationInfo.uid;
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private final void zzbj(String str, String str2) {
        synchronized (this.zzmlw) {
            if (str == null) {
                for (int i = 0; i < this.zzmlw.size(); i++) {
                    this.zzmlw.valueAt(i).onError(str2);
                }
                this.zzmlw.clear();
            } else {
                zzp zzpVarRemove = this.zzmlw.remove(str);
                if (zzpVarRemove == null) {
                    String strValueOf = String.valueOf(str);
                    Log.w("InstanceID/Rpc", strValueOf.length() != 0 ? "Missing callback for ".concat(strValueOf) : new String("Missing callback for "));
                    return;
                }
                zzpVarRemove.onError(str2);
            }
        }
    }

    public static synchronized void zzd(Context context, Intent intent) {
        if (zzhrl == null) {
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            zzhrl = PendingIntent.getBroadcast(context, 0, intent2, 0);
        }
        intent.putExtra("app", zzhrl);
    }

    public static String zzdf(Context context) {
        boolean z;
        if (zzhtv != null) {
            return zzhtv;
        }
        zzhtx = Process.myUid();
        PackageManager packageManager = context.getPackageManager();
        if (!com.google.android.gms.common.util.zzp.isAtLeastO()) {
            Iterator<ResolveInfo> it = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (zza(packageManager, it.next().serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                    zzhtw = false;
                    z = true;
                    break;
                }
            }
            if (z) {
                return zzhtv;
            }
        }
        if (zza(packageManager)) {
            return zzhtv;
        }
        Log.w("InstanceID/Rpc", "Failed to resolve IID implementation package, falling back");
        if (zzb(packageManager, "com.google.android.gms")) {
            zzhtw = com.google.android.gms.common.util.zzp.isAtLeastO();
            return zzhtv;
        }
        if (com.google.android.gms.common.util.zzp.zzalk() || !zzb(packageManager, "com.google.android.gsf")) {
            Log.w("InstanceID/Rpc", "Google Play services is missing, unable to get tokens");
            return null;
        }
        zzhtw = false;
        return zzhtv;
    }

    final Intent zza(Bundle bundle, KeyPair keyPair) throws IOException, RemoteException {
        Intent intentZzb = zzb(bundle, keyPair);
        if (intentZzb == null || !intentZzb.hasExtra("google.messenger")) {
            return intentZzb;
        }
        Intent intentZzb2 = zzb(bundle, keyPair);
        if (intentZzb2 == null || !intentZzb2.hasExtra("google.messenger")) {
            return intentZzb2;
        }
        return null;
    }

    final void zzc(Message message) {
        if (message == null) {
            return;
        }
        if (!(message.obj instanceof Intent)) {
            Log.w("InstanceID/Rpc", "Dropping invalid message");
            return;
        }
        Intent intent = (Intent) message.obj;
        intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
        if (intent.hasExtra("google.messenger")) {
            Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
            if (parcelableExtra instanceof MessengerCompat) {
                this.zzhud = (MessengerCompat) parcelableExtra;
            }
            if (parcelableExtra instanceof Messenger) {
                this.zzhuc = (Messenger) parcelableExtra;
            }
        }
        zzi((Intent) message.obj);
    }

    final void zzi(Intent intent) {
        String str;
        String str2 = null;
        if (intent == null) {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                Log.d("InstanceID/Rpc", "Unexpected response: null");
                return;
            }
            return;
        }
        if (!"com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                String strValueOf = String.valueOf(intent.getAction());
                Log.d("InstanceID/Rpc", strValueOf.length() != 0 ? "Unexpected response ".concat(strValueOf) : new String("Unexpected response "));
                return;
            }
            return;
        }
        String stringExtra = intent.getStringExtra("registration_id");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("unregistered");
        }
        if (stringExtra != null) {
            this.zzhue = SystemClock.elapsedRealtime();
            this.zzhui = 0L;
            this.zzhug = 0;
            this.zzhuh = 0;
            if (stringExtra.startsWith("|")) {
                String[] strArrSplit = stringExtra.split("\\|");
                if (!"ID".equals(strArrSplit[1])) {
                    String strValueOf2 = String.valueOf(stringExtra);
                    Log.w("InstanceID/Rpc", strValueOf2.length() != 0 ? "Unexpected structured response ".concat(strValueOf2) : new String("Unexpected structured response "));
                }
                String str3 = strArrSplit[2];
                if (strArrSplit.length > 4) {
                    if ("SYNC".equals(strArrSplit[3])) {
                        FirebaseInstanceId.zzek(this.zzahy);
                    } else if ("RST".equals(strArrSplit[3])) {
                        Context context = this.zzahy;
                        zzj.zza(this.zzahy, null);
                        FirebaseInstanceId.zza(context, zzj.zzbyo());
                        intent.removeExtra("registration_id");
                        zzb(str3, intent);
                        return;
                    }
                }
                String strSubstring = strArrSplit[strArrSplit.length - 1];
                if (strSubstring.startsWith(":")) {
                    strSubstring = strSubstring.substring(1);
                }
                intent.putExtra("registration_id", strSubstring);
                str2 = str3;
            }
            if (str2 != null) {
                zzb(str2, intent);
                return;
            } else {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    Log.d("InstanceID/Rpc", "Ignoring response without a request ID");
                    return;
                }
                return;
            }
        }
        String stringExtra2 = intent.getStringExtra("error");
        if (stringExtra2 == null) {
            String strValueOf3 = String.valueOf(intent.getExtras());
            Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(strValueOf3).length() + 49).append("Unexpected response, no error or registration id ").append(strValueOf3).toString());
            return;
        }
        if (Log.isLoggable("InstanceID/Rpc", 3)) {
            String strValueOf4 = String.valueOf(stringExtra2);
            Log.d("InstanceID/Rpc", strValueOf4.length() != 0 ? "Received InstanceID error ".concat(strValueOf4) : new String("Received InstanceID error "));
        }
        if (stringExtra2.startsWith("|")) {
            String[] strArrSplit2 = stringExtra2.split("\\|");
            if (!"ID".equals(strArrSplit2[1])) {
                String strValueOf5 = String.valueOf(stringExtra2);
                Log.w("InstanceID/Rpc", strValueOf5.length() != 0 ? "Unexpected structured response ".concat(strValueOf5) : new String("Unexpected structured response "));
            }
            if (strArrSplit2.length > 2) {
                str = strArrSplit2[2];
                String str4 = strArrSplit2[3];
                stringExtra2 = str4.startsWith(":") ? str4.substring(1) : str4;
            } else {
                stringExtra2 = "UNKNOWN";
                str = null;
            }
            intent.putExtra("error", stringExtra2);
        } else {
            str = null;
        }
        zzbj(str, stringExtra2);
        long longExtra = intent.getLongExtra("Retry-After", 0L);
        if (longExtra > 0) {
            this.zzhuf = SystemClock.elapsedRealtime();
            this.zzhuh = ((int) longExtra) * 1000;
            this.zzhui = SystemClock.elapsedRealtime() + this.zzhuh;
            Log.w("InstanceID/Rpc", new StringBuilder(52).append("Explicit request from server to backoff: ").append(this.zzhuh).toString());
            return;
        }
        if (("SERVICE_NOT_AVAILABLE".equals(stringExtra2) || "AUTHENTICATION_FAILED".equals(stringExtra2)) && "com.google.android.gsf".equals(zzhtv)) {
            this.zzhug++;
            if (this.zzhug >= 3) {
                if (this.zzhug == 3) {
                    this.zzhuh = new Random().nextInt(1000) + 1000;
                }
                this.zzhuh <<= 1;
                this.zzhui = SystemClock.elapsedRealtime() + this.zzhuh;
                Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(stringExtra2).length() + 31).append("Backoff due to ").append(stringExtra2).append(" for ").append(this.zzhuh).toString());
            }
        }
    }
}
