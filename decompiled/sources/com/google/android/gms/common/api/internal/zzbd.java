package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbcr;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public final class zzbd extends GoogleApiClient implements zzce {
    private final Context mContext;
    private final Looper zzakf;
    private final int zzfhi;
    private final GoogleApiAvailability zzfhk;
    private Api.zza<? extends zzcps, zzcpt> zzfhl;
    private final Lock zzfkd;
    private com.google.android.gms.common.internal.zzq zzfki;
    private Map<Api<?>, Boolean> zzfkl;
    private final com.google.android.gms.common.internal.zzad zzfmf;
    private volatile boolean zzfmh;
    private final zzbi zzfmk;
    private zzby zzfml;
    final Map<Api.zzc<?>, Api.zze> zzfmm;
    private final ArrayList<zzw> zzfmp;
    private Integer zzfmq;
    final zzdj zzfms;
    private zzcd zzfmg = null;
    final Queue<zzm<?, ?>> zzfkr = new LinkedList();
    private long zzfmi = 120000;
    private long zzfmj = 5000;
    Set<Scope> zzfmn = new HashSet();
    private final zzcn zzfmo = new zzcn();
    Set<zzdg> zzfmr = null;
    private final com.google.android.gms.common.internal.zzae zzfmt = new zzbe(this);
    private boolean zzfho = false;

    public zzbd(Context context, Lock lock, Looper looper, com.google.android.gms.common.internal.zzq zzqVar, GoogleApiAvailability googleApiAvailability, Api.zza<? extends zzcps, zzcpt> zzaVar, Map<Api<?>, Boolean> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.zzc<?>, Api.zze> map2, int i, int i2, ArrayList<zzw> arrayList, boolean z) {
        this.zzfmq = null;
        this.mContext = context;
        this.zzfkd = lock;
        this.zzfmf = new com.google.android.gms.common.internal.zzad(looper, this.zzfmt);
        this.zzakf = looper;
        this.zzfmk = new zzbi(this, looper);
        this.zzfhk = googleApiAvailability;
        this.zzfhi = i;
        if (this.zzfhi >= 0) {
            this.zzfmq = Integer.valueOf(i2);
        }
        this.zzfkl = map;
        this.zzfmm = map2;
        this.zzfmp = arrayList;
        this.zzfms = new zzdj(this.zzfmm);
        Iterator<GoogleApiClient.ConnectionCallbacks> it = list.iterator();
        while (it.hasNext()) {
            this.zzfmf.registerConnectionCallbacks(it.next());
        }
        Iterator<GoogleApiClient.OnConnectionFailedListener> it2 = list2.iterator();
        while (it2.hasNext()) {
            this.zzfmf.registerConnectionFailedListener(it2.next());
        }
        this.zzfki = zzqVar;
        this.zzfhl = zzaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resume() {
        this.zzfkd.lock();
        try {
            if (this.zzfmh) {
                zzahg();
            }
        } finally {
            this.zzfkd.unlock();
        }
    }

    public static int zza(Iterable<Api.zze> iterable, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        for (Api.zze zzeVar : iterable) {
            if (zzeVar.zzaac()) {
                z3 = true;
            }
            z2 = zzeVar.zzaal() ? true : z2;
        }
        if (z3) {
            return (z2 && z) ? 2 : 1;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(GoogleApiClient googleApiClient, zzda zzdaVar, boolean z) {
        zzbcr.zzfwg.zzd(googleApiClient).setResultCallback(new zzbh(this, zzdaVar, z, googleApiClient));
    }

    private final void zzahg() {
        this.zzfmf.zzakg();
        this.zzfmg.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzahh() {
        this.zzfkd.lock();
        try {
            if (zzahi()) {
                zzahg();
            }
        } finally {
            this.zzfkd.unlock();
        }
    }

    private final void zzbt(int i) {
        if (this.zzfmq == null) {
            this.zzfmq = Integer.valueOf(i);
        } else if (this.zzfmq.intValue() != i) {
            String strZzbu = zzbu(i);
            String strZzbu2 = zzbu(this.zzfmq.intValue());
            throw new IllegalStateException(new StringBuilder(String.valueOf(strZzbu).length() + 51 + String.valueOf(strZzbu2).length()).append("Cannot use sign-in mode: ").append(strZzbu).append(". Mode was already set to ").append(strZzbu2).toString());
        }
        if (this.zzfmg != null) {
            return;
        }
        boolean z = false;
        boolean z2 = false;
        for (Api.zze zzeVar : this.zzfmm.values()) {
            if (zzeVar.zzaac()) {
                z2 = true;
            }
            z = zzeVar.zzaal() ? true : z;
        }
        switch (this.zzfmq.intValue()) {
            case 1:
                if (!z2) {
                    throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                }
                if (z) {
                    throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
                break;
            case 2:
                if (z2) {
                    if (this.zzfho) {
                        this.zzfmg = new zzad(this.mContext, this.zzfkd, this.zzakf, this.zzfhk, this.zzfmm, this.zzfki, this.zzfkl, this.zzfhl, this.zzfmp, this, true);
                        return;
                    } else {
                        this.zzfmg = zzy.zza(this.mContext, this, this.zzfkd, this.zzakf, this.zzfhk, this.zzfmm, this.zzfki, this.zzfkl, this.zzfhl, this.zzfmp);
                        return;
                    }
                }
                break;
        }
        if (!this.zzfho || z) {
            this.zzfmg = new zzbl(this.mContext, this, this.zzfkd, this.zzakf, this.zzfhk, this.zzfmm, this.zzfki, this.zzfkl, this.zzfhl, this.zzfmp, this);
        } else {
            this.zzfmg = new zzad(this.mContext, this.zzfkd, this.zzakf, this.zzfhk, this.zzfmm, this.zzfki, this.zzfkl, this.zzfhl, this.zzfmp, this, false);
        }
    }

    private static String zzbu(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect() {
        com.google.android.gms.common.internal.zzbp.zza(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zzfkd.lock();
        try {
            if (this.zzfhi >= 0) {
                com.google.android.gms.common.internal.zzbp.zza(this.zzfmq != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzfmq == null) {
                this.zzfmq = Integer.valueOf(zza(this.zzfmm.values(), false));
            } else if (this.zzfmq.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzbt(this.zzfmq.intValue());
            this.zzfmf.zzakg();
            return this.zzfmg.blockingConnect();
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        com.google.android.gms.common.internal.zzbp.zza(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        com.google.android.gms.common.internal.zzbp.zzb(timeUnit, "TimeUnit must not be null");
        this.zzfkd.lock();
        try {
            if (this.zzfmq == null) {
                this.zzfmq = Integer.valueOf(zza(this.zzfmm.values(), false));
            } else if (this.zzfmq.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzbt(this.zzfmq.intValue());
            this.zzfmf.zzakg();
            return this.zzfmg.blockingConnect(j, timeUnit);
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        com.google.android.gms.common.internal.zzbp.zza(isConnected(), "GoogleApiClient is not connected yet.");
        com.google.android.gms.common.internal.zzbp.zza(this.zzfmq.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        zzda zzdaVar = new zzda(this);
        if (this.zzfmm.containsKey(zzbcr.zzdwo)) {
            zza(this, zzdaVar, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient googleApiClientBuild = new GoogleApiClient.Builder(this.mContext).addApi(zzbcr.API).addConnectionCallbacks(new zzbf(this, atomicReference, zzdaVar)).addOnConnectionFailedListener(new zzbg(this, zzdaVar)).setHandler(this.zzfmk).build();
            atomicReference.set(googleApiClientBuild);
            googleApiClientBuild.connect();
        }
        return zzdaVar;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect() {
        this.zzfkd.lock();
        try {
            if (this.zzfhi >= 0) {
                com.google.android.gms.common.internal.zzbp.zza(this.zzfmq != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzfmq == null) {
                this.zzfmq = Integer.valueOf(zza(this.zzfmm.values(), false));
            } else if (this.zzfmq.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zzfmq.intValue());
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void connect(int i) {
        boolean z = true;
        this.zzfkd.lock();
        if (i != 3 && i != 1 && i != 2) {
            z = false;
        }
        try {
            com.google.android.gms.common.internal.zzbp.zzb(z, new StringBuilder(33).append("Illegal sign-in mode: ").append(i).toString());
            zzbt(i);
            zzahg();
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void disconnect() {
        this.zzfkd.lock();
        try {
            this.zzfms.release();
            if (this.zzfmg != null) {
                this.zzfmg.disconnect();
            }
            this.zzfmo.release();
            for (zzm<?, ?> zzmVar : this.zzfkr) {
                zzmVar.zza((zzdm) null);
                zzmVar.cancel();
            }
            this.zzfkr.clear();
            if (this.zzfmg == null) {
                return;
            }
            zzahi();
            this.zzfmf.zzakf();
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("mContext=").println(this.mContext);
        printWriter.append((CharSequence) str).append("mResuming=").print(this.zzfmh);
        printWriter.append(" mWorkQueue.size()=").print(this.zzfkr.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zzfms.zzfpr.size());
        if (this.zzfmg != null) {
            this.zzfmg.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    @NonNull
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.zzfkd.lock();
        try {
            if (!isConnected() && !this.zzfmh) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
            if (!this.zzfmm.containsKey(api.zzafe())) {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
            ConnectionResult connectionResult = this.zzfmg.getConnectionResult(api);
            if (connectionResult == null) {
                if (this.zzfmh) {
                    connectionResult = ConnectionResult.zzffe;
                } else {
                    Log.w("GoogleApiClientImpl", zzahk());
                    Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                    connectionResult = new ConnectionResult(8, null);
                }
            }
            return connectionResult;
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.zzakf;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasConnectedApi(@NonNull Api<?> api) {
        if (!isConnected()) {
            return false;
        }
        Api.zze zzeVar = this.zzfmm.get(api.zzafe());
        return zzeVar != null && zzeVar.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnected() {
        return this.zzfmg != null && this.zzfmg.isConnected();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnecting() {
        return this.zzfmg != null && this.zzfmg.isConnecting();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zzfmf.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zzfmf.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void reconnect() {
        disconnect();
        connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zzfmf.registerConnectionCallbacks(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfmf.registerConnectionFailedListener(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        zzcf zzcfVar = new zzcf(fragmentActivity);
        if (this.zzfhi < 0) {
            throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
        }
        zzi.zza(zzcfVar).zzbp(this.zzfhi);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zzfmf.unregisterConnectionCallbacks(connectionCallbacks);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfmf.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    @NonNull
    public final <C extends Api.zze> C zza(@NonNull Api.zzc<C> zzcVar) {
        C c = (C) this.zzfmm.get(zzcVar);
        com.google.android.gms.common.internal.zzbp.zzb(c, "Appropriate Api was not requested.");
        return c;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zza(zzdg zzdgVar) {
        this.zzfkd.lock();
        try {
            if (this.zzfmr == null) {
                this.zzfmr = new HashSet();
            }
            this.zzfmr.add(zzdgVar);
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean zza(@NonNull Api<?> api) {
        return this.zzfmm.containsKey(api.zzafe());
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final boolean zza(zzcv zzcvVar) {
        return this.zzfmg != null && this.zzfmg.zza(zzcvVar);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zzafp() {
        if (this.zzfmg != null) {
            this.zzfmg.zzafp();
        }
    }

    final boolean zzahi() {
        if (!this.zzfmh) {
            return false;
        }
        this.zzfmh = false;
        this.zzfmk.removeMessages(2);
        this.zzfmk.removeMessages(1);
        if (this.zzfml != null) {
            this.zzfml.unregister();
            this.zzfml = null;
        }
        return true;
    }

    final boolean zzahj() {
        this.zzfkd.lock();
        try {
            if (this.zzfmr != null) {
                z = this.zzfmr.isEmpty() ? false : true;
            }
            return z;
        } finally {
            this.zzfkd.unlock();
        }
    }

    final String zzahk() {
        StringWriter stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final void zzb(zzdg zzdgVar) {
        this.zzfkd.lock();
        try {
            if (this.zzfmr == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.zzfmr.remove(zzdgVar)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zzahj()) {
                this.zzfmg.zzagi();
            }
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzc(ConnectionResult connectionResult) {
        if (!com.google.android.gms.common.zze.zze(this.mContext, connectionResult.getErrorCode())) {
            zzahi();
        }
        if (this.zzfmh) {
            return;
        }
        this.zzfmf.zzk(connectionResult);
        this.zzfmf.zzakf();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        com.google.android.gms.common.internal.zzbp.zzb(t.zzafe() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean zContainsKey = this.zzfmm.containsKey(t.zzafe());
        String name = t.zzafj() != null ? t.zzafj().getName() : "the API";
        com.google.android.gms.common.internal.zzbp.zzb(zContainsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zzfkd.lock();
        try {
            if (this.zzfmg == null) {
                this.zzfkr.add(t);
            } else {
                t = (T) this.zzfmg.zzd(t);
            }
            return t;
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        com.google.android.gms.common.internal.zzbp.zzb(t.zzafe() != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean zContainsKey = this.zzfmm.containsKey(t.zzafe());
        String name = t.zzafj() != null ? t.zzafj().getName() : "the API";
        com.google.android.gms.common.internal.zzbp.zzb(zContainsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zzfkd.lock();
        try {
            if (this.zzfmg == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (this.zzfmh) {
                this.zzfkr.add(t);
                while (!this.zzfkr.isEmpty()) {
                    zzm<?, ?> zzmVarRemove = this.zzfkr.remove();
                    this.zzfms.zzb(zzmVarRemove);
                    zzmVarRemove.zzt(Status.zzfhw);
                }
            } else {
                t = (T) this.zzfmg.zze(t);
            }
            return t;
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzf(int i, boolean z) {
        if (i == 1 && !z && !this.zzfmh) {
            this.zzfmh = true;
            if (this.zzfml == null) {
                this.zzfml = GoogleApiAvailability.zza(this.mContext.getApplicationContext(), new zzbj(this));
            }
            this.zzfmk.sendMessageDelayed(this.zzfmk.obtainMessage(1), this.zzfmi);
            this.zzfmk.sendMessageDelayed(this.zzfmk.obtainMessage(2), this.zzfmj);
        }
        this.zzfms.zzair();
        this.zzfmf.zzce(i);
        this.zzfmf.zzakf();
        if (i == 2) {
            zzahg();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzce
    public final void zzj(Bundle bundle) {
        while (!this.zzfkr.isEmpty()) {
            zze(this.zzfkr.remove());
        }
        this.zzfmf.zzk(bundle);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public final <L> zzcj<L> zzp(@NonNull L l) {
        this.zzfkd.lock();
        try {
            return this.zzfmo.zza(l, this.zzakf, "NO_TYPE");
        } finally {
            this.zzfkd.unlock();
        }
    }
}
