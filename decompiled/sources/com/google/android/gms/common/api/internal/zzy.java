package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
final class zzy implements zzcd {
    private final Context mContext;
    private final Looper zzakf;
    private final zzbd zzfjt;
    private final zzbl zzfju;
    private final zzbl zzfjv;
    private final Map<Api.zzc<?>, zzbl> zzfjw;
    private final Api.zze zzfjy;
    private Bundle zzfjz;
    private final Lock zzfkd;
    private final Set<zzcv> zzfjx = Collections.newSetFromMap(new WeakHashMap());
    private ConnectionResult zzfka = null;
    private ConnectionResult zzfkb = null;
    private boolean zzfkc = false;
    private int zzfke = 0;

    private zzy(Context context, zzbd zzbdVar, Lock lock, Looper looper, com.google.android.gms.common.zze zzeVar, Map<Api.zzc<?>, Api.zze> map, Map<Api.zzc<?>, Api.zze> map2, com.google.android.gms.common.internal.zzq zzqVar, Api.zza<? extends zzcps, zzcpt> zzaVar, Api.zze zzeVar2, ArrayList<zzw> arrayList, ArrayList<zzw> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.mContext = context;
        this.zzfjt = zzbdVar;
        this.zzfkd = lock;
        this.zzakf = looper;
        this.zzfjy = zzeVar2;
        this.zzfju = new zzbl(context, this.zzfjt, lock, looper, zzeVar, map2, null, map4, null, arrayList2, new zzaa(this, null));
        this.zzfjv = new zzbl(context, this.zzfjt, lock, looper, zzeVar, map, zzqVar, map3, zzaVar, arrayList, new zzab(this, null));
        ArrayMap arrayMap = new ArrayMap();
        Iterator<Api.zzc<?>> it = map2.keySet().iterator();
        while (it.hasNext()) {
            arrayMap.put(it.next(), this.zzfju);
        }
        Iterator<Api.zzc<?>> it2 = map.keySet().iterator();
        while (it2.hasNext()) {
            arrayMap.put(it2.next(), this.zzfjv);
        }
        this.zzfjw = Collections.unmodifiableMap(arrayMap);
    }

    public static zzy zza(Context context, zzbd zzbdVar, Lock lock, Looper looper, com.google.android.gms.common.zze zzeVar, Map<Api.zzc<?>, Api.zze> map, com.google.android.gms.common.internal.zzq zzqVar, Map<Api<?>, Boolean> map2, Api.zza<? extends zzcps, zzcpt> zzaVar, ArrayList<zzw> arrayList) {
        Api.zze zzeVar2 = null;
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        for (Map.Entry<Api.zzc<?>, Api.zze> entry : map.entrySet()) {
            Api.zze value = entry.getValue();
            if (value.zzaal()) {
                zzeVar2 = value;
            }
            if (value.zzaac()) {
                arrayMap.put(entry.getKey(), value);
            } else {
                arrayMap2.put(entry.getKey(), value);
            }
        }
        com.google.android.gms.common.internal.zzbp.zza(!arrayMap.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        ArrayMap arrayMap3 = new ArrayMap();
        ArrayMap arrayMap4 = new ArrayMap();
        for (Api<?> api : map2.keySet()) {
            Api.zzc<?> zzcVarZzafe = api.zzafe();
            if (arrayMap.containsKey(zzcVarZzafe)) {
                arrayMap3.put(api, map2.get(api));
            } else {
                if (!arrayMap2.containsKey(zzcVarZzafe)) {
                    throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                }
                arrayMap4.put(api, map2.get(api));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList<zzw> arrayList4 = arrayList;
        int size = arrayList4.size();
        int i = 0;
        while (i < size) {
            zzw zzwVar = arrayList4.get(i);
            i++;
            zzw zzwVar2 = zzwVar;
            if (arrayMap3.containsKey(zzwVar2.zzfdf)) {
                arrayList2.add(zzwVar2);
            } else {
                if (!arrayMap4.containsKey(zzwVar2.zzfdf)) {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                }
                arrayList3.add(zzwVar2);
            }
        }
        return new zzy(context, zzbdVar, lock, looper, zzeVar, arrayMap, arrayMap2, zzqVar, zzaVar, zzeVar2, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private final void zza(ConnectionResult connectionResult) {
        switch (this.zzfke) {
            case 2:
                this.zzfjt.zzc(connectionResult);
            case 1:
                zzagk();
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        this.zzfke = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzagj() {
        if (!zzb(this.zzfka)) {
            if (this.zzfka != null && zzb(this.zzfkb)) {
                this.zzfjv.disconnect();
                zza(this.zzfka);
                return;
            } else {
                if (this.zzfka == null || this.zzfkb == null) {
                    return;
                }
                ConnectionResult connectionResult = this.zzfka;
                if (this.zzfjv.zzfne < this.zzfju.zzfne) {
                    connectionResult = this.zzfkb;
                }
                zza(connectionResult);
                return;
            }
        }
        if (zzb(this.zzfkb) || zzagl()) {
            switch (this.zzfke) {
                case 2:
                    this.zzfjt.zzj(this.zzfjz);
                case 1:
                    zzagk();
                    break;
                default:
                    Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                    break;
            }
            this.zzfke = 0;
            return;
        }
        if (this.zzfkb != null) {
            if (this.zzfke == 1) {
                zzagk();
            } else {
                zza(this.zzfkb);
                this.zzfju.disconnect();
            }
        }
    }

    private final void zzagk() {
        Iterator<zzcv> it = this.zzfjx.iterator();
        while (it.hasNext()) {
            it.next().zzaak();
        }
        this.zzfjx.clear();
    }

    private final boolean zzagl() {
        return this.zzfkb != null && this.zzfkb.getErrorCode() == 4;
    }

    @Nullable
    private final PendingIntent zzagm() {
        if (this.zzfjy == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zzfjt), this.zzfjy.zzaam(), 134217728);
    }

    private static boolean zzb(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zze(int i, boolean z) {
        this.zzfjt.zzf(i, z);
        this.zzfkb = null;
        this.zzfka = null;
    }

    private final boolean zzf(zzm<? extends Result, ? extends Api.zzb> zzmVar) {
        Object objZzafe = zzmVar.zzafe();
        com.google.android.gms.common.internal.zzbp.zzb(this.zzfjw.containsKey(objZzafe), "GoogleApiClient is not configured to use the API required for this call.");
        return this.zzfjw.get(objZzafe).equals(this.zzfjv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzi(Bundle bundle) {
        if (this.zzfjz == null) {
            this.zzfjz = bundle;
        } else if (bundle != null) {
            this.zzfjz.putAll(bundle);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void connect() {
        this.zzfke = 2;
        this.zzfkc = false;
        this.zzfkb = null;
        this.zzfka = null;
        this.zzfju.connect();
        this.zzfjv.connect();
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void disconnect() {
        this.zzfkb = null;
        this.zzfka = null;
        this.zzfke = 0;
        this.zzfju.disconnect();
        this.zzfjv.disconnect();
        zzagk();
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append((CharSequence) str).append("authClient").println(":");
        this.zzfjv.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append((CharSequence) str).append("anonClient").println(":");
        this.zzfju.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return this.zzfjw.get(api.zzafe()).equals(this.zzfjv) ? zzagl() ? new ConnectionResult(4, zzagm()) : this.zzfjv.getConnectionResult(api) : this.zzfju.getConnectionResult(api);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    @Override // com.google.android.gms.common.api.internal.zzcd
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isConnected() {
        /*
            r2 = this;
            r0 = 1
            java.util.concurrent.locks.Lock r1 = r2.zzfkd
            r1.lock()
            com.google.android.gms.common.api.internal.zzbl r1 = r2.zzfju     // Catch: java.lang.Throwable -> L28
            boolean r1 = r1.isConnected()     // Catch: java.lang.Throwable -> L28
            if (r1 == 0) goto L26
            com.google.android.gms.common.api.internal.zzbl r1 = r2.zzfjv     // Catch: java.lang.Throwable -> L28
            boolean r1 = r1.isConnected()     // Catch: java.lang.Throwable -> L28
            if (r1 != 0) goto L20
            boolean r1 = r2.zzagl()     // Catch: java.lang.Throwable -> L28
            if (r1 != 0) goto L20
            int r1 = r2.zzfke     // Catch: java.lang.Throwable -> L28
            if (r1 != r0) goto L26
        L20:
            java.util.concurrent.locks.Lock r1 = r2.zzfkd
            r1.unlock()
            return r0
        L26:
            r0 = 0
            goto L20
        L28:
            r0 = move-exception
            java.util.concurrent.locks.Lock r1 = r2.zzfkd
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzy.isConnected():boolean");
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final boolean isConnecting() {
        this.zzfkd.lock();
        try {
            return this.zzfke == 2;
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final boolean zza(zzcv zzcvVar) {
        this.zzfkd.lock();
        try {
            if ((!isConnecting() && !isConnected()) || this.zzfjv.isConnected()) {
                this.zzfkd.unlock();
                return false;
            }
            this.zzfjx.add(zzcvVar);
            if (this.zzfke == 0) {
                this.zzfke = 1;
            }
            this.zzfkb = null;
            this.zzfjv.connect();
            return true;
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void zzafp() {
        this.zzfkd.lock();
        try {
            boolean zIsConnecting = isConnecting();
            this.zzfjv.disconnect();
            this.zzfkb = new ConnectionResult(4);
            if (zIsConnecting) {
                new Handler(this.zzakf).post(new zzz(this));
            } else {
                zzagk();
            }
        } finally {
            this.zzfkd.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final void zzagi() {
        this.zzfju.zzagi();
        this.zzfjv.zzagi();
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        if (!zzf((zzm<? extends Result, ? extends Api.zzb>) t)) {
            return (T) this.zzfju.zzd(t);
        }
        if (!zzagl()) {
            return (T) this.zzfjv.zzd(t);
        }
        t.zzt(new Status(4, null, zzagm()));
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zzcd
    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        if (!zzf((zzm<? extends Result, ? extends Api.zzb>) t)) {
            return (T) this.zzfju.zze(t);
        }
        if (!zzagl()) {
            return (T) this.zzfjv.zze(t);
        }
        t.zzt(new Status(4, null, zzagm()));
        return t;
    }
}
