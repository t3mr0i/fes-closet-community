package com.google.android.gms.internal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;

/* loaded from: classes.dex */
final class zzccv extends Thread {
    private /* synthetic */ zzccr zzisl;
    private final Object zziso;
    private final BlockingQueue<FutureTask<?>> zzisp;

    public zzccv(zzccr zzccrVar, String str, BlockingQueue<FutureTask<?>> blockingQueue) {
        this.zzisl = zzccrVar;
        com.google.android.gms.common.internal.zzbp.zzu(str);
        com.google.android.gms.common.internal.zzbp.zzu(blockingQueue);
        this.zziso = new Object();
        this.zzisp = blockingQueue;
        setName(str);
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzisl.zzaum().zzayg().zzj(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() throws InterruptedException {
        boolean z = false;
        while (!z) {
            try {
                this.zzisl.zzish.acquire();
                z = true;
            } catch (InterruptedException e) {
                zza(e);
            }
        }
        while (true) {
            try {
                FutureTask<?> futureTaskPoll = this.zzisp.poll();
                if (futureTaskPoll == null) {
                    synchronized (this.zziso) {
                        if (this.zzisp.peek() == null && !this.zzisl.zzisi) {
                            try {
                                this.zziso.wait(30000L);
                            } catch (InterruptedException e2) {
                                zza(e2);
                            }
                        }
                    }
                    synchronized (this.zzisl.zzisg) {
                        if (this.zzisp.peek() == null) {
                            break;
                        }
                    }
                } else {
                    futureTaskPoll.run();
                }
            } catch (Throwable th) {
                synchronized (this.zzisl.zzisg) {
                    this.zzisl.zzish.release();
                    this.zzisl.zzisg.notifyAll();
                    if (this == this.zzisl.zzisa) {
                        zzccr.zza(this.zzisl, null);
                    } else if (this == this.zzisl.zzisb) {
                        zzccr.zzb(this.zzisl, null);
                    } else {
                        this.zzisl.zzaum().zzaye().log("Current scheduler thread is neither worker nor network");
                    }
                    throw th;
                }
            }
        }
        synchronized (this.zzisl.zzisg) {
            this.zzisl.zzish.release();
            this.zzisl.zzisg.notifyAll();
            if (this == this.zzisl.zzisa) {
                zzccr.zza(this.zzisl, null);
            } else if (this == this.zzisl.zzisb) {
                zzccr.zzb(this.zzisl, null);
            } else {
                this.zzisl.zzaum().zzaye().log("Current scheduler thread is neither worker nor network");
            }
        }
    }

    public final void zzml() {
        synchronized (this.zziso) {
            this.zziso.notifyAll();
        }
    }
}
