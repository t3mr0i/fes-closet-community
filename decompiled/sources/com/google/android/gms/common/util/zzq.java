package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes.dex */
public final class zzq {
    private static String zzfze = null;
    private static final int zzfzf = Process.myPid();

    public static String zzall() {
        if (zzfze == null) {
            zzfze = zzch(zzfzf);
        }
        return zzfze;
    }

    private static String zzch(int i) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads;
        String strTrim = null;
        if (i > 0) {
            try {
                threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    bufferedReader2 = new BufferedReader(new FileReader(new StringBuilder(25).append("/proc/").append(i).append("/cmdline").toString()));
                } finally {
                }
            } catch (IOException e) {
                bufferedReader2 = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
            try {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                strTrim = bufferedReader2.readLine().trim();
                zzm.closeQuietly(bufferedReader2);
            } catch (IOException e2) {
                zzm.closeQuietly(bufferedReader2);
                return strTrim;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = bufferedReader2;
                zzm.closeQuietly(bufferedReader);
                throw th;
            }
        }
        return strTrim;
    }
}
