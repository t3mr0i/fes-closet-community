package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class zza implements ServiceConnection {
    private boolean zzffc = false;
    private final BlockingQueue<IBinder> zzffd = new LinkedBlockingQueue();

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.zzffd.add(iBinder);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }

    public final IBinder zza(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        zzbp.zzgh("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.zzffc) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zzffc = true;
        IBinder iBinderPoll = this.zzffd.poll(10000L, timeUnit);
        if (iBinderPoll == null) {
            throw new TimeoutException("Timed out waiting for the service connection");
        }
        return iBinderPoll;
    }

    public final IBinder zzaew() throws InterruptedException {
        zzbp.zzgh("BlockingServiceConnection.getService() called on main thread");
        if (this.zzffc) {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        this.zzffc = true;
        return this.zzffd.take();
    }
}
