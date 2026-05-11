package com.google.android.gms.analytics;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzalv;
import com.google.android.gms.internal.zzama;
import com.google.android.gms.internal.zzapd;
import java.lang.Thread;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class zzj {
    private static volatile zzj zzdkw;
    private final Context mContext;
    private final List<Object> zzdkx;
    private final zze zzdky;
    private final zza zzdkz;
    private volatile zzalv zzdla;
    private Thread.UncaughtExceptionHandler zzdlb;

    class zza extends ThreadPoolExecutor {
        public zza() {
            super(1, 1, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue());
            setThreadFactory(new zzb(null));
            allowCoreThreadTimeOut(true);
        }

        @Override // java.util.concurrent.AbstractExecutorService
        protected final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
            return new zzl(this, runnable, t);
        }
    }

    static class zzb implements ThreadFactory {
        private static final AtomicInteger zzdlf = new AtomicInteger();

        private zzb() {
        }

        /* synthetic */ zzb(zzk zzkVar) {
            this();
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            return new zzc(runnable, new StringBuilder(23).append("measurement-").append(zzdlf.incrementAndGet()).toString());
        }
    }

    static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() throws SecurityException, IllegalArgumentException {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    private zzj(Context context) {
        Context applicationContext = context.getApplicationContext();
        zzbp.zzu(applicationContext);
        this.mContext = applicationContext;
        this.zzdkz = new zza();
        this.zzdkx = new CopyOnWriteArrayList();
        this.zzdky = new zze();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(zzg zzgVar) {
        zzbp.zzgh("deliver should be called from worker thread");
        zzbp.zzb(zzgVar.zzub(), "Measurement must be submitted");
        List<zzm> transports = zzgVar.getTransports();
        if (transports.isEmpty()) {
            return;
        }
        HashSet hashSet = new HashSet();
        for (zzm zzmVar : transports) {
            Uri uriZztu = zzmVar.zztu();
            if (!hashSet.contains(uriZztu)) {
                hashSet.add(uriZztu);
                zzmVar.zzb(zzgVar);
            }
        }
    }

    public static zzj zzbf(Context context) {
        zzbp.zzu(context);
        if (zzdkw == null) {
            synchronized (zzj.class) {
                if (zzdkw == null) {
                    zzdkw = new zzj(context);
                }
            }
        }
        return zzdkw;
    }

    public static void zzuj() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final void zza(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzdlb = uncaughtExceptionHandler;
    }

    public final <V> Future<V> zzc(Callable<V> callable) {
        zzbp.zzu(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzdkz.submit(callable);
        }
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    public final void zzc(Runnable runnable) {
        zzbp.zzu(runnable);
        this.zzdkz.submit(runnable);
    }

    final void zze(zzg zzgVar) {
        if (zzgVar.zzue()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        }
        if (zzgVar.zzub()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        }
        zzg zzgVarZztx = zzgVar.zztx();
        zzgVarZztx.zzuc();
        this.zzdkz.execute(new zzk(this, zzgVarZztx));
    }

    public final zzalv zzuh() {
        if (this.zzdla == null) {
            synchronized (this) {
                if (this.zzdla == null) {
                    zzalv zzalvVar = new zzalv();
                    PackageManager packageManager = this.mContext.getPackageManager();
                    String packageName = this.mContext.getPackageName();
                    zzalvVar.setAppId(packageName);
                    zzalvVar.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        String strValueOf = String.valueOf(packageName);
                        Log.e("GAv4", strValueOf.length() != 0 ? "Error retrieving package info: appName set to ".concat(strValueOf) : new String("Error retrieving package info: appName set to "));
                    }
                    zzalvVar.setAppName(packageName);
                    zzalvVar.setAppVersion(str);
                    this.zzdla = zzalvVar;
                }
            }
        }
        return this.zzdla;
    }

    public final zzama zzui() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        zzama zzamaVar = new zzama();
        zzamaVar.setLanguage(zzapd.zza(Locale.getDefault()));
        zzamaVar.zzcet = displayMetrics.widthPixels;
        zzamaVar.zzceu = displayMetrics.heightPixels;
        return zzamaVar;
    }
}
