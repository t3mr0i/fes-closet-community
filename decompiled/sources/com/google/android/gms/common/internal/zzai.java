package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
final class zzai implements ServiceConnection {
    private IBinder zzftp;
    private ComponentName zzfuv;
    private boolean zzfvc;
    private final zzag zzfvd;
    private /* synthetic */ zzah zzfve;
    private final Set<ServiceConnection> zzfvb = new HashSet();
    private int mState = 2;

    public zzai(zzah zzahVar, zzag zzagVar) {
        this.zzfve = zzahVar;
        this.zzfvd = zzagVar;
    }

    public final IBinder getBinder() {
        return this.zzftp;
    }

    public final ComponentName getComponentName() {
        return this.zzfuv;
    }

    public final int getState() {
        return this.mState;
    }

    public final boolean isBound() {
        return this.zzfvc;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzfve.zzfux) {
            this.zzfve.mHandler.removeMessages(1, this.zzfvd);
            this.zzftp = iBinder;
            this.zzfuv = componentName;
            Iterator<ServiceConnection> it = this.zzfvb.iterator();
            while (it.hasNext()) {
                it.next().onServiceConnected(componentName, iBinder);
            }
            this.mState = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zzfve.zzfux) {
            this.zzfve.mHandler.removeMessages(1, this.zzfvd);
            this.zzftp = null;
            this.zzfuv = componentName;
            Iterator<ServiceConnection> it = this.zzfvb.iterator();
            while (it.hasNext()) {
                it.next().onServiceDisconnected(componentName);
            }
            this.mState = 2;
        }
    }

    public final void zza(ServiceConnection serviceConnection, String str) {
        com.google.android.gms.common.stats.zza unused = this.zzfve.zzfuy;
        Context unused2 = this.zzfve.mApplicationContext;
        this.zzfvd.zzaki();
        this.zzfvb.add(serviceConnection);
    }

    public final boolean zza(ServiceConnection serviceConnection) {
        return this.zzfvb.contains(serviceConnection);
    }

    public final boolean zzakj() {
        return this.zzfvb.isEmpty();
    }

    public final void zzb(ServiceConnection serviceConnection, String str) {
        com.google.android.gms.common.stats.zza unused = this.zzfve.zzfuy;
        Context unused2 = this.zzfve.mApplicationContext;
        this.zzfvb.remove(serviceConnection);
    }

    public final void zzgc(String str) {
        this.mState = 3;
        this.zzfvc = this.zzfve.zzfuy.zza(this.zzfve.mApplicationContext, str, this.zzfvd.zzaki(), this, this.zzfvd.zzakh());
        if (this.zzfvc) {
            this.zzfve.mHandler.sendMessageDelayed(this.zzfve.mHandler.obtainMessage(1, this.zzfvd), this.zzfve.zzfva);
        } else {
            this.mState = 2;
            try {
                com.google.android.gms.common.stats.zza unused = this.zzfve.zzfuy;
                this.zzfve.mApplicationContext.unbindService(this);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    public final void zzgd(String str) {
        this.zzfve.mHandler.removeMessages(1, this.zzfvd);
        com.google.android.gms.common.stats.zza unused = this.zzfve.zzfuy;
        this.zzfve.mApplicationContext.unbindService(this);
        this.zzfvc = false;
        this.mState = 2;
    }
}
