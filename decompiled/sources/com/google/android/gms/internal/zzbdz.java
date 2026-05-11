package com.google.android.gms.internal;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class zzbdz implements ThreadFactory {
    private final int mPriority;
    private final String zzfzm;
    private final AtomicInteger zzfzn;
    private final ThreadFactory zzfzo;

    public zzbdz(String str) {
        this(str, 0);
    }

    private zzbdz(String str, int i) {
        this.zzfzn = new AtomicInteger();
        this.zzfzo = Executors.defaultThreadFactory();
        this.zzfzm = (String) com.google.android.gms.common.internal.zzbp.zzb(str, "Name must not be null");
        this.mPriority = 0;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.zzfzo.newThread(new zzbea(runnable, 0));
        String str = this.zzfzm;
        threadNewThread.setName(new StringBuilder(String.valueOf(str).length() + 13).append(str).append("[").append(this.zzfzn.getAndIncrement()).append("]").toString());
        return threadNewThread;
    }
}
