package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;

/* loaded from: classes.dex */
public final class zzcqh {
    private final Context mContext;
    private final String zzfxy;
    private final String zzfya;
    private final PowerManager.WakeLock zzjoa;
    private WorkSource zzjob;
    private final int zzjoc;
    private final String zzjod;
    private boolean zzjoe;
    private int zzjof;
    private int zzjog;
    private static String TAG = "WakeLock";
    private static String zzjnz = "*gcore*:";
    private static boolean DEBUG = false;

    public zzcqh(Context context, int i, String str) {
        this(context, 1, str, null, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    private zzcqh(Context context, int i, String str, String str2, String str3) {
        this(context, 1, str, null, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    private zzcqh(Context context, int i, String str, String str2, String str3, String str4) {
        this.zzjoe = true;
        com.google.android.gms.common.internal.zzbp.zzh(str, "Wake lock name can NOT be empty");
        this.zzjoc = i;
        this.zzjod = null;
        this.zzfya = null;
        this.mContext = context.getApplicationContext();
        if ("com.google.android.gms".equals(context.getPackageName())) {
            this.zzfxy = str;
        } else {
            String strValueOf = String.valueOf(zzjnz);
            String strValueOf2 = String.valueOf(str);
            this.zzfxy = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        }
        this.zzjoa = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (com.google.android.gms.common.util.zzw.zzco(this.mContext)) {
            this.zzjob = com.google.android.gms.common.util.zzw.zzac(context, com.google.android.gms.common.util.zzs.zzgm(str3) ? context.getPackageName() : str3);
            WorkSource workSource = this.zzjob;
            if (workSource == null || !com.google.android.gms.common.util.zzw.zzco(this.mContext)) {
                return;
            }
            if (this.zzjob != null) {
                this.zzjob.add(workSource);
            } else {
                this.zzjob = workSource;
            }
            try {
                this.zzjoa.setWorkSource(this.zzjob);
            } catch (IllegalArgumentException e) {
                Log.wtf(TAG, e.toString());
            }
        }
    }

    private final String zzg(String str, boolean z) {
        if (this.zzjoe && z) {
            return null;
        }
        return this.zzjod;
    }

    private final boolean zzlb(String str) {
        String str2 = null;
        return (TextUtils.isEmpty(null) || str2.equals(this.zzjod)) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0030 A[Catch: all -> 0x0061, TryCatch #0 {, blocks: (B:4:0x000c, B:6:0x0010, B:11:0x0022, B:13:0x0026, B:20:0x0038, B:21:0x005a, B:16:0x0030, B:18:0x0034, B:8:0x0014, B:10:0x001c), top: B:27:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038 A[Catch: all -> 0x0061, TryCatch #0 {, blocks: (B:4:0x000c, B:6:0x0010, B:11:0x0022, B:13:0x0026, B:20:0x0038, B:21:0x005a, B:16:0x0030, B:18:0x0034, B:8:0x0014, B:10:0x001c), top: B:27:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void acquire(long r13) {
        /*
            r12 = this;
            r10 = 1000(0x3e8, double:4.94E-321)
            r1 = 0
            boolean r0 = r12.zzlb(r1)
            java.lang.String r4 = r12.zzg(r1, r0)
            monitor-enter(r12)
            int r1 = r12.zzjof     // Catch: java.lang.Throwable -> L61
            if (r1 > 0) goto L14
            int r1 = r12.zzjog     // Catch: java.lang.Throwable -> L61
            if (r1 <= 0) goto L22
        L14:
            android.os.PowerManager$WakeLock r1 = r12.zzjoa     // Catch: java.lang.Throwable -> L61
            boolean r1 = r1.isHeld()     // Catch: java.lang.Throwable -> L61
            if (r1 != 0) goto L22
            r1 = 0
            r12.zzjof = r1     // Catch: java.lang.Throwable -> L61
            r1 = 0
            r12.zzjog = r1     // Catch: java.lang.Throwable -> L61
        L22:
            boolean r1 = r12.zzjoe     // Catch: java.lang.Throwable -> L61
            if (r1 == 0) goto L30
            int r1 = r12.zzjof     // Catch: java.lang.Throwable -> L61
            int r2 = r1 + 1
            r12.zzjof = r2     // Catch: java.lang.Throwable -> L61
            if (r1 == 0) goto L38
            if (r0 != 0) goto L38
        L30:
            boolean r0 = r12.zzjoe     // Catch: java.lang.Throwable -> L61
            if (r0 != 0) goto L5a
            int r0 = r12.zzjog     // Catch: java.lang.Throwable -> L61
            if (r0 != 0) goto L5a
        L38:
            com.google.android.gms.common.stats.zze.zzalc()     // Catch: java.lang.Throwable -> L61
            android.content.Context r0 = r12.mContext     // Catch: java.lang.Throwable -> L61
            android.os.PowerManager$WakeLock r1 = r12.zzjoa     // Catch: java.lang.Throwable -> L61
            java.lang.String r1 = com.google.android.gms.common.stats.zzc.zza(r1, r4)     // Catch: java.lang.Throwable -> L61
            r2 = 7
            java.lang.String r3 = r12.zzfxy     // Catch: java.lang.Throwable -> L61
            r5 = 0
            int r6 = r12.zzjoc     // Catch: java.lang.Throwable -> L61
            android.os.WorkSource r7 = r12.zzjob     // Catch: java.lang.Throwable -> L61
            java.util.List r7 = com.google.android.gms.common.util.zzw.zzb(r7)     // Catch: java.lang.Throwable -> L61
            r8 = 1000(0x3e8, double:4.94E-321)
            com.google.android.gms.common.stats.zze.zza(r0, r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L61
            int r0 = r12.zzjog     // Catch: java.lang.Throwable -> L61
            int r0 = r0 + 1
            r12.zzjog = r0     // Catch: java.lang.Throwable -> L61
        L5a:
            monitor-exit(r12)     // Catch: java.lang.Throwable -> L61
            android.os.PowerManager$WakeLock r0 = r12.zzjoa
            r0.acquire(r10)
            return
        L61:
            r0 = move-exception
            monitor-exit(r12)     // Catch: java.lang.Throwable -> L61
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcqh.acquire(long):void");
    }

    public final boolean isHeld() {
        return this.zzjoa.isHeld();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0021 A[Catch: all -> 0x0049, TryCatch #0 {, blocks: (B:4:0x000a, B:6:0x000e, B:13:0x0021, B:14:0x0042, B:9:0x0018, B:11:0x001c), top: B:20:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018 A[Catch: all -> 0x0049, TryCatch #0 {, blocks: (B:4:0x000a, B:6:0x000e, B:13:0x0021, B:14:0x0042, B:9:0x0018, B:11:0x001c), top: B:20:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void release() {
        /*
            r8 = this;
            r1 = 0
            boolean r0 = r8.zzlb(r1)
            java.lang.String r4 = r8.zzg(r1, r0)
            monitor-enter(r8)
            boolean r1 = r8.zzjoe     // Catch: java.lang.Throwable -> L49
            if (r1 == 0) goto L18
            int r1 = r8.zzjof     // Catch: java.lang.Throwable -> L49
            int r1 = r1 + (-1)
            r8.zzjof = r1     // Catch: java.lang.Throwable -> L49
            if (r1 == 0) goto L21
            if (r0 != 0) goto L21
        L18:
            boolean r0 = r8.zzjoe     // Catch: java.lang.Throwable -> L49
            if (r0 != 0) goto L42
            int r0 = r8.zzjog     // Catch: java.lang.Throwable -> L49
            r1 = 1
            if (r0 != r1) goto L42
        L21:
            com.google.android.gms.common.stats.zze.zzalc()     // Catch: java.lang.Throwable -> L49
            android.content.Context r0 = r8.mContext     // Catch: java.lang.Throwable -> L49
            android.os.PowerManager$WakeLock r1 = r8.zzjoa     // Catch: java.lang.Throwable -> L49
            java.lang.String r1 = com.google.android.gms.common.stats.zzc.zza(r1, r4)     // Catch: java.lang.Throwable -> L49
            r2 = 8
            java.lang.String r3 = r8.zzfxy     // Catch: java.lang.Throwable -> L49
            r5 = 0
            int r6 = r8.zzjoc     // Catch: java.lang.Throwable -> L49
            android.os.WorkSource r7 = r8.zzjob     // Catch: java.lang.Throwable -> L49
            java.util.List r7 = com.google.android.gms.common.util.zzw.zzb(r7)     // Catch: java.lang.Throwable -> L49
            com.google.android.gms.common.stats.zze.zza(r0, r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L49
            int r0 = r8.zzjog     // Catch: java.lang.Throwable -> L49
            int r0 = r0 + (-1)
            r8.zzjog = r0     // Catch: java.lang.Throwable -> L49
        L42:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L49
            android.os.PowerManager$WakeLock r0 = r8.zzjoa
            r0.release()
            return
        L49:
            r0 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L49
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcqh.release():void");
    }

    public final void setReferenceCounted(boolean z) {
        this.zzjoa.setReferenceCounted(false);
        this.zzjoe = false;
    }
}
