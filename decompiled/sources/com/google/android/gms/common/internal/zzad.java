package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class zzad implements Handler.Callback {
    private final Handler mHandler;
    private final zzae zzful;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzfum = new ArrayList<>();
    private ArrayList<GoogleApiClient.ConnectionCallbacks> zzfun = new ArrayList<>();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzfuo = new ArrayList<>();
    private volatile boolean zzfup = false;
    private final AtomicInteger zzfuq = new AtomicInteger(0);
    private boolean zzfur = false;
    private final Object mLock = new Object();

    public zzad(Looper looper, zzae zzaeVar) {
        this.zzful = zzaeVar;
        this.mHandler = new Handler(looper, this);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 1) {
            Log.wtf("GmsClientEvents", new StringBuilder(45).append("Don't know how to handle message: ").append(message.what).toString(), new Exception());
            return false;
        }
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
        synchronized (this.mLock) {
            if (this.zzfup && this.zzful.isConnected() && this.zzfum.contains(connectionCallbacks)) {
                connectionCallbacks.onConnected(this.zzful.zzaeh());
            }
        }
        return true;
    }

    public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean zContains;
        zzbp.zzu(connectionCallbacks);
        synchronized (this.mLock) {
            zContains = this.zzfum.contains(connectionCallbacks);
        }
        return zContains;
    }

    public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean zContains;
        zzbp.zzu(onConnectionFailedListener);
        synchronized (this.mLock) {
            zContains = this.zzfuo.contains(onConnectionFailedListener);
        }
        return zContains;
    }

    public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzbp.zzu(connectionCallbacks);
        synchronized (this.mLock) {
            if (this.zzfum.contains(connectionCallbacks)) {
                String strValueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(strValueOf).length() + 62).append("registerConnectionCallbacks(): listener ").append(strValueOf).append(" is already registered").toString());
            } else {
                this.zzfum.add(connectionCallbacks);
            }
        }
        if (this.zzful.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzbp.zzu(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (this.zzfuo.contains(onConnectionFailedListener)) {
                String strValueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(strValueOf).length() + 67).append("registerConnectionFailedListener(): listener ").append(strValueOf).append(" is already registered").toString());
            } else {
                this.zzfuo.add(onConnectionFailedListener);
            }
        }
    }

    public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzbp.zzu(connectionCallbacks);
        synchronized (this.mLock) {
            if (!this.zzfum.remove(connectionCallbacks)) {
                String strValueOf = String.valueOf(connectionCallbacks);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(strValueOf).length() + 52).append("unregisterConnectionCallbacks(): listener ").append(strValueOf).append(" not found").toString());
            } else if (this.zzfur) {
                this.zzfun.add(connectionCallbacks);
            }
        }
    }

    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzbp.zzu(onConnectionFailedListener);
        synchronized (this.mLock) {
            if (!this.zzfuo.remove(onConnectionFailedListener)) {
                String strValueOf = String.valueOf(onConnectionFailedListener);
                Log.w("GmsClientEvents", new StringBuilder(String.valueOf(strValueOf).length() + 57).append("unregisterConnectionFailedListener(): listener ").append(strValueOf).append(" not found").toString());
            }
        }
    }

    public final void zzakf() {
        this.zzfup = false;
        this.zzfuq.incrementAndGet();
    }

    public final void zzakg() {
        this.zzfup = true;
    }

    public final void zzce(int i) {
        int i2 = 0;
        zzbp.zza(Looper.myLooper() == this.mHandler.getLooper(), "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            this.zzfur = true;
            ArrayList arrayList = new ArrayList(this.zzfum);
            int i3 = this.zzfuq.get();
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            while (i2 < size) {
                Object obj = arrayList2.get(i2);
                i2++;
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) obj;
                if (!this.zzfup || this.zzfuq.get() != i3) {
                    break;
                } else if (this.zzfum.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.zzfun.clear();
            this.zzfur = false;
        }
    }

    public final void zzk(Bundle bundle) {
        int i = 0;
        zzbp.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.mLock) {
            zzbp.zzbg(!this.zzfur);
            this.mHandler.removeMessages(1);
            this.zzfur = true;
            zzbp.zzbg(this.zzfun.size() == 0);
            ArrayList arrayList = new ArrayList(this.zzfum);
            int i2 = this.zzfuq.get();
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) obj;
                if (!this.zzfup || !this.zzful.isConnected() || this.zzfuq.get() != i2) {
                    break;
                } else if (!this.zzfun.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.zzfun.clear();
            this.zzfur = false;
        }
    }

    public final void zzk(ConnectionResult connectionResult) {
        int i = 0;
        zzbp.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.mLock) {
            ArrayList arrayList = new ArrayList(this.zzfuo);
            int i2 = this.zzfuq.get();
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) obj;
                if (!this.zzfup || this.zzfuq.get() != i2) {
                    return;
                }
                if (this.zzfuo.contains(onConnectionFailedListener)) {
                    onConnectionFailedListener.onConnectionFailed(connectionResult);
                }
            }
        }
    }
}
