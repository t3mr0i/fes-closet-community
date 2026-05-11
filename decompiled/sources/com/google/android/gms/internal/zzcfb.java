package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
public final class zzcfb implements ServiceConnection, com.google.android.gms.common.internal.zzf, com.google.android.gms.common.internal.zzg {
    final /* synthetic */ zzceo zziwe;
    private volatile boolean zziwl;
    private volatile zzcbv zziwm;

    protected zzcfb(zzceo zzceoVar) {
        this.zziwe = zzceoVar;
    }

    static /* synthetic */ boolean zza(zzcfb zzcfbVar, boolean z) {
        zzcfbVar.zziwl = false;
        return false;
    }

    @Override // com.google.android.gms.common.internal.zzf
    @MainThread
    public final void onConnected(@Nullable Bundle bundle) {
        com.google.android.gms.common.internal.zzbp.zzfy("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                zzcbo zzcboVarZzajk = this.zziwm.zzajk();
                this.zziwm = null;
                this.zziwe.zzaul().zzg(new zzcfe(this, zzcboVarZzajk));
            } catch (DeadObjectException | IllegalStateException e) {
                this.zziwm = null;
                this.zziwl = false;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.zzg
    @MainThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzfy("MeasurementServiceConnection.onConnectionFailed");
        zzcbw zzcbwVarZzayx = this.zziwe.zzikh.zzayx();
        if (zzcbwVarZzayx != null) {
            zzcbwVarZzayx.zzayg().zzj("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zziwl = false;
            this.zziwm = null;
        }
        this.zziwe.zzaul().zzg(new zzcfg(this));
    }

    @Override // com.google.android.gms.common.internal.zzf
    @MainThread
    public final void onConnectionSuspended(int i) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzfy("MeasurementServiceConnection.onConnectionSuspended");
        this.zziwe.zzaum().zzayj().log("Service connection suspended");
        this.zziwe.zzaul().zzg(new zzcff(this));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008d A[Catch: all -> 0x0054, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:6:0x000a, B:7:0x001b, B:9:0x001d, B:13:0x002c, B:16:0x003e, B:17:0x0040, B:18:0x0052, B:34:0x008d, B:30:0x006c, B:23:0x0057, B:25:0x0061, B:26:0x0064, B:32:0x007c), top: B:40:0x0007 }] */
    @Override // android.content.ServiceConnection
    @android.support.annotation.MainThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
        /*
            r4 = this;
            r1 = 0
            java.lang.String r0 = "MeasurementServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.zzbp.zzfy(r0)
            monitor-enter(r4)
            if (r6 != 0) goto L1d
            r0 = 0
            r4.zziwl = r0     // Catch: java.lang.Throwable -> L54
            com.google.android.gms.internal.zzceo r0 = r4.zziwe     // Catch: java.lang.Throwable -> L54
            com.google.android.gms.internal.zzcbw r0 = r0.zzaum()     // Catch: java.lang.Throwable -> L54
            com.google.android.gms.internal.zzcby r0 = r0.zzaye()     // Catch: java.lang.Throwable -> L54
            java.lang.String r1 = "Service connected with null binder"
            r0.log(r1)     // Catch: java.lang.Throwable -> L54
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L54
        L1c:
            return
        L1d:
            java.lang.String r0 = r6.getInterfaceDescriptor()     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r0)     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            if (r2 == 0) goto L7c
            if (r6 != 0) goto L57
            r0 = r1
        L2c:
            com.google.android.gms.internal.zzceo r1 = r4.zziwe     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L9e
            com.google.android.gms.internal.zzcbw r1 = r1.zzaum()     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L9e
            com.google.android.gms.internal.zzcby r1 = r1.zzayk()     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L9e
            java.lang.String r2 = "Bound to IMeasurementService interface"
            r1.log(r2)     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L9e
        L3b:
            if (r0 != 0) goto L8d
            r0 = 0
            r4.zziwl = r0     // Catch: java.lang.Throwable -> L54
            com.google.android.gms.common.stats.zza.zzakz()     // Catch: java.lang.Throwable -> L54 java.lang.IllegalArgumentException -> L9c
            com.google.android.gms.internal.zzceo r0 = r4.zziwe     // Catch: java.lang.Throwable -> L54 java.lang.IllegalArgumentException -> L9c
            android.content.Context r0 = r0.getContext()     // Catch: java.lang.Throwable -> L54 java.lang.IllegalArgumentException -> L9c
            com.google.android.gms.internal.zzceo r1 = r4.zziwe     // Catch: java.lang.Throwable -> L54 java.lang.IllegalArgumentException -> L9c
            com.google.android.gms.internal.zzcfb r1 = com.google.android.gms.internal.zzceo.zza(r1)     // Catch: java.lang.Throwable -> L54 java.lang.IllegalArgumentException -> L9c
            r0.unbindService(r1)     // Catch: java.lang.Throwable -> L54 java.lang.IllegalArgumentException -> L9c
        L52:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L54
            goto L1c
        L54:
            r0 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L54
            throw r0
        L57:
            java.lang.String r0 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r0 = r6.queryLocalInterface(r0)     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            boolean r2 = r0 instanceof com.google.android.gms.internal.zzcbo     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            if (r2 == 0) goto L64
            com.google.android.gms.internal.zzcbo r0 = (com.google.android.gms.internal.zzcbo) r0     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            goto L2c
        L64:
            com.google.android.gms.internal.zzcbq r0 = new com.google.android.gms.internal.zzcbq     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            r0.<init>(r6)     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            goto L2c
        L6a:
            r0 = move-exception
            r0 = r1
        L6c:
            com.google.android.gms.internal.zzceo r1 = r4.zziwe     // Catch: java.lang.Throwable -> L54
            com.google.android.gms.internal.zzcbw r1 = r1.zzaum()     // Catch: java.lang.Throwable -> L54
            com.google.android.gms.internal.zzcby r1 = r1.zzaye()     // Catch: java.lang.Throwable -> L54
            java.lang.String r2 = "Service connect failed to get IMeasurementService"
            r1.log(r2)     // Catch: java.lang.Throwable -> L54
            goto L3b
        L7c:
            com.google.android.gms.internal.zzceo r2 = r4.zziwe     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            com.google.android.gms.internal.zzcbw r2 = r2.zzaum()     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            com.google.android.gms.internal.zzcby r2 = r2.zzaye()     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            java.lang.String r3 = "Got binder with a wrong descriptor"
            r2.zzj(r3, r0)     // Catch: java.lang.Throwable -> L54 android.os.RemoteException -> L6a
            r0 = r1
            goto L3b
        L8d:
            com.google.android.gms.internal.zzceo r1 = r4.zziwe     // Catch: java.lang.Throwable -> L54
            com.google.android.gms.internal.zzccr r1 = r1.zzaul()     // Catch: java.lang.Throwable -> L54
            com.google.android.gms.internal.zzcfc r2 = new com.google.android.gms.internal.zzcfc     // Catch: java.lang.Throwable -> L54
            r2.<init>(r4, r0)     // Catch: java.lang.Throwable -> L54
            r1.zzg(r2)     // Catch: java.lang.Throwable -> L54
            goto L52
        L9c:
            r0 = move-exception
            goto L52
        L9e:
            r1 = move-exception
            goto L6c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcfb.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @Override // android.content.ServiceConnection
    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) throws IllegalStateException {
        com.google.android.gms.common.internal.zzbp.zzfy("MeasurementServiceConnection.onServiceDisconnected");
        this.zziwe.zzaum().zzayj().log("Service disconnected");
        this.zziwe.zzaul().zzg(new zzcfd(this, componentName));
    }

    @WorkerThread
    public final void zzazt() {
        this.zziwe.zzuj();
        Context context = this.zziwe.getContext();
        synchronized (this) {
            if (this.zziwl) {
                this.zziwe.zzaum().zzayk().log("Connection attempt already in progress");
                return;
            }
            if (this.zziwm != null) {
                this.zziwe.zzaum().zzayk().log("Already awaiting connection attempt");
                return;
            }
            this.zziwm = new zzcbv(context, Looper.getMainLooper(), this, this);
            this.zziwe.zzaum().zzayk().log("Connecting to remote service");
            this.zziwl = true;
            this.zziwm.zzajg();
        }
    }

    @WorkerThread
    public final void zzk(Intent intent) {
        this.zziwe.zzuj();
        Context context = this.zziwe.getContext();
        com.google.android.gms.common.stats.zza zzaVarZzakz = com.google.android.gms.common.stats.zza.zzakz();
        synchronized (this) {
            if (this.zziwl) {
                this.zziwe.zzaum().zzayk().log("Connection attempt already in progress");
            } else {
                this.zziwl = true;
                zzaVarZzakz.zza(context, intent, this.zziwe.zzivx, 129);
            }
        }
    }
}
