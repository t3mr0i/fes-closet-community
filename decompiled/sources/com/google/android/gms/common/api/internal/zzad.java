package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbdy;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public final class zzad implements zzcd {
    private final Looper zzakf;
    private final zzbp zzfgu;
    private final Lock zzfkd;
    private final com.google.android.gms.common.internal.zzq zzfki;
    private final Map<Api<?>, Boolean> zzfkl;
    private final zzbd zzfkm;
    private final com.google.android.gms.common.zze zzfkn;
    private final Condition zzfko;
    private final boolean zzfkp;
    private final boolean zzfkq;
    private boolean zzfks;
    private Map<zzh<?>, ConnectionResult> zzfkt;
    private Map<zzh<?>, ConnectionResult> zzfku;
    private zzag zzfkv;
    private ConnectionResult zzfkw;
    private final Map<Api.zzc<?>, zzac<?>> zzfkj = new HashMap();
    private final Map<Api.zzc<?>, zzac<?>> zzfkk = new HashMap();
    private final Queue<zzm<?, ?>> zzfkr = new LinkedList();

    public zzad(Context context, Lock lock, Looper looper, com.google.android.gms.common.zze zzeVar, Map<Api.zzc<?>, Api.zze> map, com.google.android.gms.common.internal.zzq zzqVar, Map<Api<?>, Boolean> map2, Api.zza<? extends zzcps, zzcpt> zzaVar, ArrayList<zzw> arrayList, zzbd zzbdVar, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zzfkd = lock;
        this.zzakf = looper;
        this.zzfko = lock.newCondition();
        this.zzfkn = zzeVar;
        this.zzfkm = zzbdVar;
        this.zzfkl = map2;
        this.zzfki = zzqVar;
        this.zzfkp = z;
        HashMap map3 = new HashMap();
        for (Api<?> api : map2.keySet()) {
            map3.put(api.zzafe(), api);
        }
        HashMap map4 = new HashMap();
        ArrayList<zzw> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            zzw zzwVar = arrayList2.get(i);
            i++;
            zzw zzwVar2 = zzwVar;
            map4.put(zzwVar2.zzfdf, zzwVar2);
        }
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        for (Map.Entry<Api.zzc<?>, Api.zze> entry : map.entrySet()) {
            Api api2 = (Api) map3.get(entry.getKey());
            Api.zze value = entry.getValue();
            if (value.zzaff()) {
                z2 = true;
                if (this.zzfkl.get(api2).booleanValue()) {
                    z3 = z6;
                    z4 = z7;
                } else {
                    z3 = z6;
                    z4 = true;
                }
            } else {
                z2 = z5;
                z3 = false;
                z4 = z7;
            }
            zzac<?> zzacVar = new zzac<>(context, api2, looper, value, (zzw) map4.get(api2), zzqVar, zzaVar);
            this.zzfkj.put(entry.getKey(), zzacVar);
            if (value.zzaac()) {
                this.zzfkk.put(entry.getKey(), zzacVar);
            }
            z5 = z2;
            z6 = z3;
            z7 = z4;
        }
        this.zzfkq = (!z5 || z6 || z7) ? false : true;
        this.zzfgu = zzbp.zzaho();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(zzac<?> zzacVar, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zzfkl.get(zzacVar.zzafj()).booleanValue() && zzacVar.zzagn().zzaff() && this.zzfkn.isUserResolvableError(connectionResult.getErrorCode());
    }

    static /* synthetic */ boolean zza(zzad zzadVar, boolean z) {
        zzadVar.zzfks = false;
        return false;
    }

    private final boolean zzago() {
        this.zzfkd.lock();
        try {
            if (!this.zzfks || !this.zzfkp) {
                return false;
            }
            Iterator<Api.zzc<?>> it = this.zzfkk.keySet().iterator();
            while (it.hasNext()) {
                ConnectionResult connectionResultZzb = zzb(it.next());
                if (connectionResultZzb == null || !connectionResultZzb.isSuccess()) {
                    return false;
                }
            }
            this.zzfkd.unlock();
            return true;
        } finally {
            this.zzfkd.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzagp() {
        if (this.zzfki == null) {
            this.zzfkm.zzfmn = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zzfki.zzajs());
        Map<Api<?>, com.google.android.gms.common.internal.zzs> mapZzaju = this.zzfki.zzaju();
        for (Api<?> api : mapZzaju.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(mapZzaju.get(api).zzecl);
            }
        }
        this.zzfkm.zzfmn = hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzagq() {
        while (!this.zzfkr.isEmpty()) {
            zze((zzad) this.zzfkr.remove());
        }
        this.zzfkm.zzj(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public final ConnectionResult zzagr() {
        int i = 0;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zzac<?> zzacVar : this.zzfkj.values()) {
            Api<?> apiZzafj = zzacVar.zzafj();
            ConnectionResult connectionResult3 = this.zzfkt.get(zzacVar.zzafk());
            if (!connectionResult3.isSuccess() && (!this.zzfkl.get(apiZzafj).booleanValue() || connectionResult3.hasResolution() || this.zzfkn.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() == 4 && this.zzfkp) {
                    int priority = apiZzafj.zzafc().getPriority();
                    if (connectionResult == null || i > priority) {
                        i = priority;
                        connectionResult = connectionResult3;
                    }
                } else {
                    int priority2 = apiZzafj.zzafc().getPriority();
                    if (connectionResult2 != null && i2 <= priority2) {
                        priority2 = i2;
                        connectionResult3 = connectionResult2;
                    }
                    i2 = priority2;
                    connectionResult2 = connectionResult3;
                }
            }
        }
        return (connectionResult2 == null || connectionResult == null || i2 <= i) ? connectionResult2 : connectionResult;
    }

    @Nullable
    private final ConnectionResult zzb(@NonNull Api.zzc<?> zzcVar) {
        this.zzfkd.lock();
        try {
            zzac<?> zzacVar = this.zzfkj.get(zzcVar);
            if (this.zzfkt != null && zzacVar != null) {
                return this.zzfkt.get(zzacVar.zzafk());
            }
            this.zzfkd.unlock();
            return null;
        } finally {
            this.zzfkd.unlock();
        }
    }

    private final <T extends zzm<? extends Result, ? extends Api.zzb>> boolean zzg(@NonNull T t) {
        Api.zzc<?> zzcVarZzafe = t.zzafe();
        ConnectionResult connectionResultZzb = zzb(zzcVarZzafe);
        if (connectionResultZzb == null || connectionResultZzb.getErrorCode() != 4) {
            return false;
        }
        t.zzt(new Status(4, null, this.zzfgu.zza(this.zzfkj.get(zzcVarZzafe).zzafk(), System.identityHashCode(this.zzfkm))));
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final ConnectionResult blockingConnect() throws InterruptedException {
        connect();
        while (isConnecting()) {
            try {
                this.zzfko.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzffe : this.zzfkw != null ? this.zzfkw : new ConnectionResult(13, null);
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
                nanos = this.zzfko.awaitNanos(nanos);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
            Thread.currentThread().interrupt();
            return new ConnectionResult(15, null);
        }
        return isConnected() ? ConnectionResult.zzffe : this.zzfkw != null ? this.zzfkw : new ConnectionResult(13, null);
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void connect() {
        this.zzfkd.lock();
        try {
            if (this.zzfks) {
                return;
            }
            this.zzfks = true;
            this.zzfkt = null;
            this.zzfku = null;
            this.zzfkv = null;
            this.zzfkw = null;
            this.zzfgu.zzafw();
            this.zzfgu.zza(this.zzfkj.values()).addOnCompleteListener(new zzbdy(this.zzakf), new zzaf(this));
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void disconnect() {
        this.zzfkd.lock();
        try {
            this.zzfks = false;
            this.zzfkt = null;
            this.zzfku = null;
            if (this.zzfkv != null) {
                this.zzfkv.cancel();
                this.zzfkv = null;
            }
            this.zzfkw = null;
            while (!this.zzfkr.isEmpty()) {
                zzm<?, ?> zzmVarRemove = this.zzfkr.remove();
                zzmVarRemove.zza((zzdm) null);
                zzmVarRemove.cancel();
            }
            this.zzfko.signalAll();
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zzb(api.zzafe());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0014  */
    @Override // com.google.android.gms.common.api.internal.zzcd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isConnected() {
        /*
            r2 = this;
            java.util.concurrent.locks.Lock r0 = r2.zzfkd
            r0.lock()
            java.util.Map<com.google.android.gms.common.api.internal.zzh<?>, com.google.android.gms.common.ConnectionResult> r0 = r2.zzfkt     // Catch: java.lang.Throwable -> L16
            if (r0 == 0) goto L14
            com.google.android.gms.common.ConnectionResult r0 = r2.zzfkw     // Catch: java.lang.Throwable -> L16
            if (r0 != 0) goto L14
            r0 = 1
        Le:
            java.util.concurrent.locks.Lock r1 = r2.zzfkd
            r1.unlock()
            return r0
        L14:
            r0 = 0
            goto Le
        L16:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r2.zzfkd
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzad.isConnected():boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0014  */
    @Override // com.google.android.gms.common.api.internal.zzcd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isConnecting() {
        /*
            r2 = this;
            java.util.concurrent.locks.Lock r0 = r2.zzfkd
            r0.lock()
            java.util.Map<com.google.android.gms.common.api.internal.zzh<?>, com.google.android.gms.common.ConnectionResult> r0 = r2.zzfkt     // Catch: java.lang.Throwable -> L16
            if (r0 != 0) goto L14
            boolean r0 = r2.zzfks     // Catch: java.lang.Throwable -> L16
            if (r0 == 0) goto L14
            r0 = 1
        Le:
            java.util.concurrent.locks.Lock r1 = r2.zzfkd
            r1.unlock()
            return r0
        L14:
            r0 = 0
            goto Le
        L16:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r2.zzfkd
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzad.isConnecting():boolean");
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final boolean zza(zzcv zzcvVar) {
        this.zzfkd.lock();
        try {
            if (!this.zzfks || zzago()) {
                this.zzfkd.unlock();
                return false;
            }
            this.zzfgu.zzafw();
            this.zzfkv = new zzag(this, zzcvVar);
            this.zzfgu.zza(this.zzfkk.values()).addOnCompleteListener(new zzbdy(this.zzakf), this.zzfkv);
            this.zzfkd.unlock();
            return true;
        } catch (Throwable th) {
            this.zzfkd.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void zzafp() {
        this.zzfkd.lock();
        try {
            this.zzfgu.zzafp();
            if (this.zzfkv != null) {
                this.zzfkv.cancel();
                this.zzfkv = null;
            }
            if (this.zzfku == null) {
                this.zzfku = new ArrayMap(this.zzfkk.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            Iterator<zzac<?>> it = this.zzfkk.values().iterator();
            while (it.hasNext()) {
                this.zzfku.put(it.next().zzafk(), connectionResult);
            }
            if (this.zzfkt != null) {
                this.zzfkt.putAll(this.zzfku);
            }
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void zzagi() {
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        if (this.zzfkp && zzg((zzad) t)) {
            return t;
        }
        if (isConnected()) {
            this.zzfkm.zzfms.zzb(t);
            return (T) this.zzfkj.get(t.zzafe()).zza((zzac<?>) t);
        }
        this.zzfkr.add(t);
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        Api.zzc<A> zzcVarZzafe = t.zzafe();
        if (this.zzfkp && zzg((zzad) t)) {
            return t;
        }
        this.zzfkm.zzfms.zzb(t);
        return (T) this.zzfkj.get(zzcVarZzafe).zzb((zzac<?>) t);
    }
}
