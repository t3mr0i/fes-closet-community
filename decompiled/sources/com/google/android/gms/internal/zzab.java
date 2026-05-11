package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class zzab {
    private static String TAG = "Volley";
    public static boolean DEBUG = Log.isLoggable("Volley", 2);

    static class zza {
        public static final boolean zzbi = zzab.DEBUG;
        private final List<zzac> zzbj = new ArrayList();
        private boolean zzbk = false;

        zza() {
        }

        protected final void finalize() throws Throwable {
            if (this.zzbk) {
                return;
            }
            zzc("Request on the loose");
            zzab.zzc("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }

        public final synchronized void zza(String str, long j) {
            if (this.zzbk) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.zzbj.add(new zzac(str, j, SystemClock.elapsedRealtime()));
        }

        public final synchronized void zzc(String str) {
            this.zzbk = true;
            long j = this.zzbj.size() == 0 ? 0L : this.zzbj.get(this.zzbj.size() - 1).time - this.zzbj.get(0).time;
            if (j > 0) {
                long j2 = this.zzbj.get(0).time;
                zzab.zzb("(%-4d ms) %s", Long.valueOf(j), str);
                long j3 = j2;
                for (zzac zzacVar : this.zzbj) {
                    long j4 = zzacVar.time;
                    zzab.zzb("(+%-4d) [%2d] %s", Long.valueOf(j4 - j3), Long.valueOf(zzacVar.zzbl), zzacVar.name);
                    j3 = j4;
                }
            }
        }
    }

    public static void zza(String str, Object... objArr) {
        if (DEBUG) {
            Log.v(TAG, zzd(str, objArr));
        }
    }

    public static void zza(Throwable th, String str, Object... objArr) {
        Log.e(TAG, zzd(str, objArr), th);
    }

    public static void zzb(String str, Object... objArr) {
        Log.d(TAG, zzd(str, objArr));
    }

    public static void zzc(String str, Object... objArr) {
        Log.e(TAG, zzd(str, objArr));
    }

    private static String zzd(String str, Object... objArr) {
        String string;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                string = "<unknown>";
                break;
            }
            if (!stackTrace[i].getClass().equals(zzab.class)) {
                String className = stackTrace[i].getClassName();
                String strSubstring = className.substring(className.lastIndexOf(46) + 1);
                String strSubstring2 = strSubstring.substring(strSubstring.lastIndexOf(36) + 1);
                String methodName = stackTrace[i].getMethodName();
                string = new StringBuilder(String.valueOf(strSubstring2).length() + 1 + String.valueOf(methodName).length()).append(strSubstring2).append(".").append(methodName).toString();
                break;
            }
            i++;
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), string, str);
    }
}
