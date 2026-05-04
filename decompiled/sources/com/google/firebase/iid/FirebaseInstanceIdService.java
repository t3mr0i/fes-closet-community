package com.google.firebase.iid;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import java.io.IOException;

/* loaded from: classes.dex */
public class FirebaseInstanceIdService extends zzb {

    @VisibleForTesting
    private static Object zzmlp = new Object();

    @VisibleForTesting
    private static boolean zzmlq = false;
    private boolean zzmlr = false;

    static class zza extends BroadcastReceiver {

        @Nullable
        private static BroadcastReceiver receiver;
        private int zzmls;

        private zza(int i) {
            this.zzmls = i;
        }

        static synchronized void zzl(Context context, int i) {
            if (receiver == null) {
                receiver = new zza(i);
                context.getApplicationContext().registerReceiver(receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            synchronized (zza.class) {
                if (receiver != this) {
                    return;
                }
                if (FirebaseInstanceIdService.zzen(context)) {
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        Log.d("FirebaseInstanceId", "connectivity changed. starting background sync.");
                    }
                    context.getApplicationContext().unregisterReceiver(this);
                    receiver = null;
                    zzq.zzbys().zze(context, FirebaseInstanceIdService.zzfy(this.zzmls));
                }
            }
        }
    }

    static void zza(Context context, FirebaseInstanceId firebaseInstanceId) {
        synchronized (zzmlp) {
            if (zzmlq) {
                return;
            }
            zzs zzsVarZzbyl = firebaseInstanceId.zzbyl();
            if (zzsVarZzbyl == null || zzsVarZzbyl.zzqk(zzj.zzhts) || FirebaseInstanceId.zzbyn().zzbyq() != null) {
                zzem(context);
            }
        }
    }

    private final void zza(Intent intent, String str) {
        int i = 28800;
        boolean zZzen = zzen(this);
        int intExtra = intent == null ? 10 : intent.getIntExtra("next_retry_delay_in_seconds", 0);
        if (intExtra < 10 && !zZzen) {
            i = 30;
        } else if (intExtra < 10) {
            i = 10;
        } else if (intExtra <= 28800) {
            i = intExtra;
        }
        Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(str).length() + 47).append("background sync failed: ").append(str).append(", retry in ").append(i).append("s").toString());
        synchronized (zzmlp) {
            ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, SystemClock.elapsedRealtime() + (i * 1000), zzq.zza(this, 0, zzfy(i << 1), 134217728));
            zzmlq = true;
        }
        if (zZzen) {
            return;
        }
        if (this.zzmlr) {
            Log.d("FirebaseInstanceId", "device not connected. Connectivity change received registered");
        }
        zza.zzl(this, i);
    }

    private final void zza(Intent intent, boolean z, boolean z2) {
        synchronized (zzmlp) {
            zzmlq = false;
        }
        if (zzl.zzdf(this) == null) {
            return;
        }
        FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();
        zzs zzsVarZzbyl = firebaseInstanceId.zzbyl();
        if (zzsVarZzbyl == null || zzsVarZzbyl.zzqk(zzj.zzhts)) {
            try {
                String strZzbym = firebaseInstanceId.zzbym();
                if (strZzbym == null) {
                    zza(intent, "returned token is null");
                    return;
                }
                if (this.zzmlr) {
                    Log.d("FirebaseInstanceId", "get master token succeeded");
                }
                zza(this, firebaseInstanceId);
                if (z2 || zzsVarZzbyl == null || !(zzsVarZzbyl == null || strZzbym.equals(zzsVarZzbyl.zzkoo))) {
                    onTokenRefresh();
                    return;
                }
                return;
            } catch (IOException e) {
                zza(intent, e.getMessage());
                return;
            } catch (SecurityException e2) {
                Log.e("FirebaseInstanceId", "Unable to get master token", e2);
                return;
            }
        }
        zzk zzkVarZzbyn = FirebaseInstanceId.zzbyn();
        for (String strZzbyq = zzkVarZzbyn.zzbyq(); strZzbyq != null; strZzbyq = zzkVarZzbyn.zzbyq()) {
            String[] strArrSplit = strZzbyq.split("!");
            if (strArrSplit.length == 2) {
                String str = strArrSplit[0];
                String str2 = strArrSplit[1];
                try {
                    switch (str) {
                        case "S":
                            FirebaseInstanceId.getInstance().zzqb(str2);
                            if (this.zzmlr) {
                                Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                                break;
                            } else {
                                break;
                            }
                        case "U":
                            FirebaseInstanceId.getInstance().zzqc(str2);
                            if (this.zzmlr) {
                                Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                                break;
                            } else {
                                break;
                            }
                    }
                } catch (IOException e3) {
                    zza(intent, e3.getMessage());
                    return;
                }
            }
            zzkVarZzbyn.zzqe(strZzbyq);
        }
        Log.d("FirebaseInstanceId", "topic sync succeeded");
    }

    static void zzem(Context context) {
        if (zzl.zzdf(context) == null) {
            return;
        }
        synchronized (zzmlp) {
            if (!zzmlq) {
                zzq.zzbys().zze(context, zzfy(0));
                zzmlq = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzen(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Intent zzfy(int i) {
        Intent intent = new Intent("ACTION_TOKEN_REFRESH_RETRY");
        intent.putExtra("next_retry_delay_in_seconds", i);
        return intent;
    }

    private static String zzp(Intent intent) {
        String stringExtra = intent.getStringExtra("subtype");
        return stringExtra == null ? "" : stringExtra;
    }

    private final zzj zzqd(String str) {
        if (str == null) {
            return zzj.zza(this, null);
        }
        Bundle bundle = new Bundle();
        bundle.putString("subtype", str);
        return zzj.zza(this, bundle);
    }

    @Override // com.google.firebase.iid.zzb
    public void handleIntent(Intent intent) {
        boolean z;
        String action = intent.getAction();
        if (action == null) {
            action = "";
        }
        switch (action.hashCode()) {
            case -1737547627:
                if (action.equals("ACTION_TOKEN_REFRESH_RETRY")) {
                    z = false;
                    break;
                }
            default:
                z = -1;
                break;
        }
        switch (z) {
            case false:
                zza(intent, false, false);
                break;
            default:
                String strZzp = zzp(intent);
                zzj zzjVarZzqd = zzqd(strZzp);
                String stringExtra = intent.getStringExtra("CMD");
                if (this.zzmlr) {
                    String strValueOf = String.valueOf(intent.getExtras());
                    Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(strZzp).length() + 18 + String.valueOf(stringExtra).length() + String.valueOf(strValueOf).length()).append("Service command ").append(strZzp).append(" ").append(stringExtra).append(" ").append(strValueOf).toString());
                }
                if (intent.getStringExtra("unregistered") == null) {
                    if (!"gcm.googleapis.com/refresh".equals(intent.getStringExtra("from"))) {
                        if (!"RST".equals(stringExtra)) {
                            if (!"RST_FULL".equals(stringExtra)) {
                                if (!"SYNC".equals(stringExtra)) {
                                    if ("PING".equals(stringExtra)) {
                                        Bundle extras = intent.getExtras();
                                        String strZzdf = zzl.zzdf(this);
                                        if (strZzdf != null) {
                                            Intent intent2 = new Intent("com.google.android.gcm.intent.SEND");
                                            intent2.setPackage(strZzdf);
                                            intent2.putExtras(extras);
                                            zzl.zzd(this, intent2);
                                            intent2.putExtra("google.to", "google.com/iid");
                                            intent2.putExtra("google.message_id", zzl.zzasv());
                                            sendOrderedBroadcast(intent2, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
                                            break;
                                        } else {
                                            Log.w("FirebaseInstanceId", "Unable to respond to ping due to missing target package");
                                            break;
                                        }
                                    }
                                } else {
                                    zzj.zzbyo().zzhu(strZzp);
                                    zza(intent, false, true);
                                    break;
                                }
                            } else if (!zzj.zzbyo().isEmpty()) {
                                zzjVarZzqd.zzass();
                                zzj.zzbyo().zzasw();
                                zza(intent, true, true);
                                break;
                            }
                        } else {
                            zzjVarZzqd.zzass();
                            zza(intent, true, true);
                            break;
                        }
                    } else {
                        zzj.zzbyo().zzhu(strZzp);
                        zza(intent, false, true);
                        break;
                    }
                } else {
                    zzr zzrVarZzbyo = zzj.zzbyo();
                    if (strZzp == null) {
                        strZzp = "";
                    }
                    zzrVarZzbyo.zzhu(strZzp);
                    zzj.zzbyp().zzi(intent);
                    break;
                }
                break;
        }
    }

    @WorkerThread
    public void onTokenRefresh() {
    }

    @Override // com.google.firebase.iid.zzb
    protected final Intent zzn(Intent intent) {
        return zzq.zzbys().zzmmd.poll();
    }

    @Override // com.google.firebase.iid.zzb
    public final boolean zzo(Intent intent) {
        this.zzmlr = Log.isLoggable("FirebaseInstanceId", 3);
        if (intent.getStringExtra("error") == null && intent.getStringExtra("registration_id") == null) {
            return false;
        }
        String strZzp = zzp(intent);
        if (this.zzmlr) {
            String strValueOf = String.valueOf(strZzp);
            Log.d("FirebaseInstanceId", strValueOf.length() != 0 ? "Register result in service ".concat(strValueOf) : new String("Register result in service "));
        }
        zzqd(strZzp);
        zzj.zzbyp().zzi(intent);
        return true;
    }
}
