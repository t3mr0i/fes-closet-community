package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzbr<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzx {
    private final zzh<O> zzfgr;
    private final Api.zze zzfkg;
    private boolean zzfmh;
    private /* synthetic */ zzbp zzfnt;
    private final Api.zzb zzfnv;
    private final zzah zzfnw;
    private final int zzfnz;
    private final zzcw zzfoa;
    private final Queue<zza> zzfnu = new LinkedList();
    private final Set<zzj> zzfnx = new HashSet();
    private final Map<zzcl<?>, zzcs> zzfny = new HashMap();
    private ConnectionResult zzfob = null;

    @WorkerThread
    public zzbr(zzbp zzbpVar, GoogleApi<O> googleApi) {
        this.zzfnt = zzbpVar;
        this.zzfkg = googleApi.zza(zzbpVar.mHandler.getLooper(), this);
        if (this.zzfkg instanceof com.google.android.gms.common.internal.zzby) {
            this.zzfnv = com.google.android.gms.common.internal.zzby.zzakp();
        } else {
            this.zzfnv = this.zzfkg;
        }
        this.zzfgr = googleApi.zzafk();
        this.zzfnw = new zzah();
        this.zzfnz = googleApi.getInstanceId();
        if (this.zzfkg.zzaac()) {
            this.zzfoa = googleApi.zza(zzbpVar.mContext, zzbpVar.mHandler);
        } else {
            this.zzfoa = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzahu() {
        zzahx();
        zzi(ConnectionResult.zzffe);
        zzahz();
        Iterator<zzcs> it = this.zzfny.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().zzfic.zzb(this.zzfnv, new TaskCompletionSource<>());
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zzfkg.disconnect();
            } catch (RemoteException e2) {
            }
        }
        while (this.zzfkg.isConnected() && !this.zzfnu.isEmpty()) {
            zzb(this.zzfnu.remove());
        }
        zzaia();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzahv() {
        zzahx();
        this.zzfmh = true;
        this.zzfnw.zzagu();
        this.zzfnt.mHandler.sendMessageDelayed(Message.obtain(this.zzfnt.mHandler, 9, this.zzfgr), this.zzfnt.zzfmj);
        this.zzfnt.mHandler.sendMessageDelayed(Message.obtain(this.zzfnt.mHandler, 11, this.zzfgr), this.zzfnt.zzfmi);
        this.zzfnt.zzfnn = -1;
    }

    @WorkerThread
    private final void zzahz() {
        if (this.zzfmh) {
            this.zzfnt.mHandler.removeMessages(11, this.zzfgr);
            this.zzfnt.mHandler.removeMessages(9, this.zzfgr);
            this.zzfmh = false;
        }
    }

    private final void zzaia() {
        this.zzfnt.mHandler.removeMessages(12, this.zzfgr);
        this.zzfnt.mHandler.sendMessageDelayed(this.zzfnt.mHandler.obtainMessage(12, this.zzfgr), this.zzfnt.zzfnl);
    }

    @WorkerThread
    private final void zzb(zza zzaVar) {
        zzaVar.zza(this.zzfnw, zzaac());
        try {
            zzaVar.zza((zzbr<?>) this);
        } catch (DeadObjectException e) {
            onConnectionSuspended(1);
            this.zzfkg.disconnect();
        }
    }

    @WorkerThread
    private final void zzi(ConnectionResult connectionResult) {
        Iterator<zzj> it = this.zzfnx.iterator();
        while (it.hasNext()) {
            it.next().zza(this.zzfgr, connectionResult);
        }
        this.zzfnx.clear();
    }

    @WorkerThread
    public final void connect() {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        if (this.zzfkg.isConnected() || this.zzfkg.isConnecting()) {
            return;
        }
        if (this.zzfkg.zzaff() && this.zzfnt.zzfnn != 0) {
            this.zzfnt.zzfnn = this.zzfnt.zzfhk.isGooglePlayServicesAvailable(this.zzfnt.mContext);
            if (this.zzfnt.zzfnn != 0) {
                onConnectionFailed(new ConnectionResult(this.zzfnt.zzfnn, null));
                return;
            }
        }
        zzbv zzbvVar = new zzbv(this.zzfnt, this.zzfkg, this.zzfgr);
        if (this.zzfkg.zzaac()) {
            this.zzfoa.zza(zzbvVar);
        }
        this.zzfkg.zza(zzbvVar);
    }

    public final int getInstanceId() {
        return this.zzfnz;
    }

    final boolean isConnected() {
        return this.zzfkg.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        if (Looper.myLooper() == this.zzfnt.mHandler.getLooper()) {
            zzahu();
        } else {
            this.zzfnt.mHandler.post(new zzbs(this));
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        if (this.zzfoa != null) {
            this.zzfoa.zzain();
        }
        zzahx();
        this.zzfnt.zzfnn = -1;
        zzi(connectionResult);
        if (connectionResult.getErrorCode() == 4) {
            zzv(zzbp.zzfnk);
            return;
        }
        if (this.zzfnu.isEmpty()) {
            this.zzfob = connectionResult;
            return;
        }
        synchronized (zzbp.zzaqc) {
            if (this.zzfnt.zzfnq != null && this.zzfnt.zzfnr.contains(this.zzfgr)) {
                this.zzfnt.zzfnq.zzb(connectionResult, this.zzfnz);
            } else if (!this.zzfnt.zzc(connectionResult, this.zzfnz)) {
                if (connectionResult.getErrorCode() == 18) {
                    this.zzfmh = true;
                }
                if (this.zzfmh) {
                    this.zzfnt.mHandler.sendMessageDelayed(Message.obtain(this.zzfnt.mHandler, 9, this.zzfgr), this.zzfnt.zzfmj);
                } else {
                    String strZzafv = this.zzfgr.zzafv();
                    zzv(new Status(17, new StringBuilder(String.valueOf(strZzafv).length() + 38).append("API: ").append(strZzafv).append(" is not available on this device.").toString()));
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        if (Looper.myLooper() == this.zzfnt.mHandler.getLooper()) {
            zzahv();
        } else {
            this.zzfnt.mHandler.post(new zzbt(this));
        }
    }

    @WorkerThread
    public final void resume() {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        if (this.zzfmh) {
            connect();
        }
    }

    @WorkerThread
    public final void signOut() {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        zzv(zzbp.zzfnj);
        this.zzfnw.zzagt();
        Iterator<zzcl<?>> it = this.zzfny.keySet().iterator();
        while (it.hasNext()) {
            zza(new zzf(it.next(), new TaskCompletionSource()));
        }
        zzi(new ConnectionResult(4));
        this.zzfkg.disconnect();
    }

    @Override // com.google.android.gms.common.api.internal.zzx
    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (Looper.myLooper() == this.zzfnt.mHandler.getLooper()) {
            onConnectionFailed(connectionResult);
        } else {
            this.zzfnt.mHandler.post(new zzbu(this, connectionResult));
        }
    }

    @WorkerThread
    public final void zza(zza zzaVar) {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        if (this.zzfkg.isConnected()) {
            zzb(zzaVar);
            zzaia();
            return;
        }
        this.zzfnu.add(zzaVar);
        if (this.zzfob == null || !this.zzfob.hasResolution()) {
            connect();
        } else {
            onConnectionFailed(this.zzfob);
        }
    }

    @WorkerThread
    public final void zza(zzj zzjVar) {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        this.zzfnx.add(zzjVar);
    }

    public final boolean zzaac() {
        return this.zzfkg.zzaac();
    }

    public final Api.zze zzagn() {
        return this.zzfkg;
    }

    @WorkerThread
    public final void zzahh() {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        if (this.zzfmh) {
            zzahz();
            zzv(this.zzfnt.zzfhk.isGooglePlayServicesAvailable(this.zzfnt.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
            this.zzfkg.disconnect();
        }
    }

    public final Map<zzcl<?>, zzcs> zzahw() {
        return this.zzfny;
    }

    @WorkerThread
    public final void zzahx() {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        this.zzfob = null;
    }

    @WorkerThread
    public final ConnectionResult zzahy() {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        return this.zzfob;
    }

    @WorkerThread
    public final void zzaib() {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        if (this.zzfkg.isConnected() && this.zzfny.size() == 0) {
            if (this.zzfnw.zzags()) {
                zzaia();
            } else {
                this.zzfkg.disconnect();
            }
        }
    }

    final zzcps zzaic() {
        if (this.zzfoa == null) {
            return null;
        }
        return this.zzfoa.zzaic();
    }

    @WorkerThread
    public final void zzh(@NonNull ConnectionResult connectionResult) {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        this.zzfkg.disconnect();
        onConnectionFailed(connectionResult);
    }

    @WorkerThread
    public final void zzv(Status status) {
        com.google.android.gms.common.internal.zzbp.zza(this.zzfnt.mHandler);
        Iterator<zza> it = this.zzfnu.iterator();
        while (it.hasNext()) {
            it.next().zzr(status);
        }
        this.zzfnu.clear();
    }
}
