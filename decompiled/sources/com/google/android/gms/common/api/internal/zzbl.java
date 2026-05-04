package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public final class zzbl implements zzcd, zzx {
    private final Context mContext;
    private Api.zza<? extends zzcps, zzcpt> zzfhl;
    final zzbd zzfjt;
    private final Lock zzfkd;
    private com.google.android.gms.common.internal.zzq zzfki;
    private Map<Api<?>, Boolean> zzfkl;
    private final com.google.android.gms.common.zze zzfkn;
    final Map<Api.zzc<?>, Api.zze> zzfmm;
    private final Condition zzfmz;
    private final zzbn zzfna;
    private volatile zzbk zzfnc;
    int zzfne;
    final zzce zzfnf;
    final Map<Api.zzc<?>, ConnectionResult> zzfnb = new HashMap();
    private ConnectionResult zzfnd = null;

    public zzbl(Context context, zzbd zzbdVar, Lock lock, Looper looper, com.google.android.gms.common.zze zzeVar, Map<Api.zzc<?>, Api.zze> map, com.google.android.gms.common.internal.zzq zzqVar, Map<Api<?>, Boolean> map2, Api.zza<? extends zzcps, zzcpt> zzaVar, ArrayList<zzw> arrayList, zzce zzceVar) {
        this.mContext = context;
        this.zzfkd = lock;
        this.zzfkn = zzeVar;
        this.zzfmm = map;
        this.zzfki = zzqVar;
        this.zzfkl = map2;
        this.zzfhl = zzaVar;
        this.zzfjt = zzbdVar;
        this.zzfnf = zzceVar;
        ArrayList<zzw> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            zzw zzwVar = arrayList2.get(i);
            i++;
            zzwVar.zza(this);
        }
        this.zzfna = new zzbn(this, looper);
        this.zzfmz = lock.newCondition();
        this.zzfnc = new zzbc(this);
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final ConnectionResult blockingConnect() throws InterruptedException {
        connect();
        while (isConnecting()) {
            try {
                this.zzfmz.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzffe : this.zzfnd != null ? this.zzfnd : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) throws InterruptedException {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                disconnect();
                return new ConnectionResult(14, null);
            }
            try {
                nanos = this.zzfmz.awaitNanos(nanos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        return isConnected() ? ConnectionResult.zzffe : this.zzfnd != null ? this.zzfnd : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void connect() {
        this.zzfnc.connect();
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void disconnect() {
        if (this.zzfnc.disconnect()) {
            this.zzfnb.clear();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String strConcat = String.valueOf(str).concat("  ");
        printWriter.append((CharSequence) str).append("mState=").println(this.zzfnc);
        for (Api<?> api : this.zzfkl.keySet()) {
            printWriter.append((CharSequence) str).append((CharSequence) api.getName()).println(":");
            this.zzfmm.get(api.zzafe()).dump(strConcat, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        Api.zzc<?> zzcVarZzafe = api.zzafe();
        if (this.zzfmm.containsKey(zzcVarZzafe)) {
            if (this.zzfmm.get(zzcVarZzafe).isConnected()) {
                return ConnectionResult.zzffe;
            }
            if (this.zzfnb.containsKey(zzcVarZzafe)) {
                return this.zzfnb.get(zzcVarZzafe);
            }
        }
        return null;
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final boolean isConnected() {
        return this.zzfnc instanceof zzao;
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final boolean isConnecting() {
        return this.zzfnc instanceof zzar;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        this.zzfkd.lock();
        try {
            this.zzfnc.onConnected(bundle);
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        this.zzfkd.lock();
        try {
            this.zzfnc.onConnectionSuspended(i);
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzx
    public final void zza(@NonNull ConnectionResult connectionResult, @NonNull Api<?> api, boolean z) {
        this.zzfkd.lock();
        try {
            this.zzfnc.zza(connectionResult, api, z);
        } finally {
            this.zzfkd.unlock();
        }
    }

    final void zza(zzbm zzbmVar) {
        this.zzfna.sendMessage(this.zzfna.obtainMessage(1, zzbmVar));
    }

    final void zza(RuntimeException runtimeException) {
        this.zzfna.sendMessage(this.zzfna.obtainMessage(2, runtimeException));
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final boolean zza(zzcv zzcvVar) {
        return false;
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void zzafp() {
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void zzagi() {
        if (isConnected()) {
            ((zzao) this.zzfnc).zzagy();
        }
    }

    final void zzahl() {
        this.zzfkd.lock();
        try {
            this.zzfnc = new zzar(this, this.zzfki, this.zzfkl, this.zzfkn, this.zzfhl, this.zzfkd, this.mContext);
            this.zzfnc.begin();
            this.zzfmz.signalAll();
        } finally {
            this.zzfkd.unlock();
        }
    }

    final void zzahm() {
        this.zzfkd.lock();
        try {
            this.zzfjt.zzahi();
            this.zzfnc = new zzao(this);
            this.zzfnc.begin();
            this.zzfmz.signalAll();
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        t.zzagg();
        return (T) this.zzfnc.zzd(t);
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        t.zzagg();
        return (T) this.zzfnc.zze(t);
    }

    final void zzg(ConnectionResult connectionResult) {
        this.zzfkd.lock();
        try {
            this.zzfnd = connectionResult;
            this.zzfnc = new zzbc(this);
            this.zzfnc.begin();
            this.zzfmz.signalAll();
        } finally {
            this.zzfkd.unlock();
        }
    }
}
