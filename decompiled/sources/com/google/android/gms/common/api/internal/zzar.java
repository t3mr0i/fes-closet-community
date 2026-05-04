package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import com.google.android.gms.internal.zzcqf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public final class zzar implements zzbk {
    private final Context mContext;
    private final Api.zza<? extends zzcps, zzcpt> zzfhl;
    private final Lock zzfkd;
    private final com.google.android.gms.common.internal.zzq zzfki;
    private final Map<Api<?>, Boolean> zzfkl;
    private final com.google.android.gms.common.zze zzfkn;
    private ConnectionResult zzfkw;
    private final zzbl zzflg;
    private int zzflj;
    private int zzfll;
    private zzcps zzflo;
    private boolean zzflp;
    private boolean zzflq;
    private boolean zzflr;
    private com.google.android.gms.common.internal.zzam zzfls;
    private boolean zzflt;
    private boolean zzflu;
    private int zzflk = 0;
    private final Bundle zzflm = new Bundle();
    private final Set<Api.zzc> zzfln = new HashSet();
    private ArrayList<Future<?>> zzflv = new ArrayList<>();

    public zzar(zzbl zzblVar, com.google.android.gms.common.internal.zzq zzqVar, Map<Api<?>, Boolean> map, com.google.android.gms.common.zze zzeVar, Api.zza<? extends zzcps, zzcpt> zzaVar, Lock lock, Context context) {
        this.zzflg = zzblVar;
        this.zzfki = zzqVar;
        this.zzfkl = map;
        this.zzfkn = zzeVar;
        this.zzfhl = zzaVar;
        this.zzfkd = lock;
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzcqf zzcqfVar) {
        if (zzbr(0)) {
            ConnectionResult connectionResultZzagd = zzcqfVar.zzagd();
            if (!connectionResultZzagd.isSuccess()) {
                if (!zzd(connectionResultZzagd)) {
                    zze(connectionResultZzagd);
                    return;
                } else {
                    zzahd();
                    zzahb();
                    return;
                }
            }
            com.google.android.gms.common.internal.zzbs zzbsVarZzbcd = zzcqfVar.zzbcd();
            ConnectionResult connectionResultZzagd2 = zzbsVarZzbcd.zzagd();
            if (!connectionResultZzagd2.isSuccess()) {
                String strValueOf = String.valueOf(connectionResultZzagd2);
                Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(strValueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(strValueOf).toString(), new Exception());
                zze(connectionResultZzagd2);
            } else {
                this.zzflr = true;
                this.zzfls = zzbsVarZzbcd.zzakm();
                this.zzflt = zzbsVarZzbcd.zzakn();
                this.zzflu = zzbsVarZzbcd.zzako();
                zzahb();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzaha() {
        this.zzfll--;
        if (this.zzfll > 0) {
            return false;
        }
        if (this.zzfll < 0) {
            Log.w("GoogleApiClientConnecting", this.zzflg.zzfjt.zzahk());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zze(new ConnectionResult(8, null));
            return false;
        }
        if (this.zzfkw == null) {
            return true;
        }
        this.zzflg.zzfne = this.zzflj;
        zze(this.zzfkw);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzahb() {
        if (this.zzfll != 0) {
            return;
        }
        if (!this.zzflq || this.zzflr) {
            ArrayList arrayList = new ArrayList();
            this.zzflk = 1;
            this.zzfll = this.zzflg.zzfmm.size();
            for (Api.zzc<?> zzcVar : this.zzflg.zzfmm.keySet()) {
                if (!this.zzflg.zzfnb.containsKey(zzcVar)) {
                    arrayList.add(this.zzflg.zzfmm.get(zzcVar));
                } else if (zzaha()) {
                    zzahc();
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.zzflv.add(zzbo.zzahn().submit(new zzax(this, arrayList)));
        }
    }

    private final void zzahc() {
        this.zzflg.zzahm();
        zzbo.zzahn().execute(new zzas(this));
        if (this.zzflo != null) {
            if (this.zzflt) {
                this.zzflo.zza(this.zzfls, this.zzflu);
            }
            zzbf(false);
        }
        Iterator<Api.zzc<?>> it = this.zzflg.zzfnb.keySet().iterator();
        while (it.hasNext()) {
            this.zzflg.zzfmm.get(it.next()).disconnect();
        }
        this.zzflg.zzfnf.zzj(this.zzflm.isEmpty() ? null : this.zzflm);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzahd() {
        this.zzflq = false;
        this.zzflg.zzfjt.zzfmn = Collections.emptySet();
        for (Api.zzc<?> zzcVar : this.zzfln) {
            if (!this.zzflg.zzfnb.containsKey(zzcVar)) {
                this.zzflg.zzfnb.put(zzcVar, new ConnectionResult(17, null));
            }
        }
    }

    private final void zzahe() {
        ArrayList<Future<?>> arrayList = this.zzflv;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Future<?> future = arrayList.get(i);
            i++;
            future.cancel(true);
        }
        this.zzflv.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<Scope> zzahf() {
        if (this.zzfki == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zzfki.zzajs());
        Map<Api<?>, com.google.android.gms.common.internal.zzs> mapZzaju = this.zzfki.zzaju();
        for (Api<?> api : mapZzaju.keySet()) {
            if (!this.zzflg.zzfnb.containsKey(api.zzafe())) {
                hashSet.addAll(mapZzaju.get(api).zzecl);
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzb(com.google.android.gms.common.ConnectionResult r6, com.google.android.gms.common.api.Api<?> r7, boolean r8) {
        /*
            r5 = this;
            r1 = 0
            r0 = 1
            com.google.android.gms.common.api.Api$zzd r2 = r7.zzafc()
            int r3 = r2.getPriority()
            if (r8 == 0) goto L15
            boolean r2 = r6.hasResolution()
            if (r2 == 0) goto L2f
            r2 = r0
        L13:
            if (r2 == 0) goto L3f
        L15:
            com.google.android.gms.common.ConnectionResult r2 = r5.zzfkw
            if (r2 == 0) goto L1d
            int r2 = r5.zzflj
            if (r3 >= r2) goto L3f
        L1d:
            if (r0 == 0) goto L23
            r5.zzfkw = r6
            r5.zzflj = r3
        L23:
            com.google.android.gms.common.api.internal.zzbl r0 = r5.zzflg
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.ConnectionResult> r0 = r0.zzfnb
            com.google.android.gms.common.api.Api$zzc r1 = r7.zzafe()
            r0.put(r1, r6)
            return
        L2f:
            com.google.android.gms.common.zze r2 = r5.zzfkn
            int r4 = r6.getErrorCode()
            android.content.Intent r2 = r2.zzbn(r4)
            if (r2 == 0) goto L3d
            r2 = r0
            goto L13
        L3d:
            r2 = r1
            goto L13
        L3f:
            r0 = r1
            goto L1d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzar.zzb(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    private final void zzbf(boolean z) {
        if (this.zzflo != null) {
            if (this.zzflo.isConnected() && z) {
                this.zzflo.zzbbw();
            }
            this.zzflo.disconnect();
            this.zzfls = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzbr(int i) {
        if (this.zzflk == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zzflg.zzfjt.zzahk());
        String strValueOf = String.valueOf(this);
        Log.w("GoogleApiClientConnecting", new StringBuilder(String.valueOf(strValueOf).length() + 23).append("Unexpected callback in ").append(strValueOf).toString());
        Log.w("GoogleApiClientConnecting", new StringBuilder(33).append("mRemainingConnections=").append(this.zzfll).toString());
        String strZzbs = zzbs(this.zzflk);
        String strZzbs2 = zzbs(i);
        Log.wtf("GoogleApiClientConnecting", new StringBuilder(String.valueOf(strZzbs).length() + 70 + String.valueOf(strZzbs2).length()).append("GoogleApiClient connecting is in step ").append(strZzbs).append(" but received callback for step ").append(strZzbs2).toString(), new Exception());
        zze(new ConnectionResult(8, null));
        return false;
    }

    private static String zzbs(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzd(ConnectionResult connectionResult) {
        return this.zzflp && !connectionResult.hasResolution();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zze(ConnectionResult connectionResult) {
        zzahe();
        zzbf(!connectionResult.hasResolution());
        this.zzflg.zzg(connectionResult);
        this.zzflg.zzfnf.zzc(connectionResult);
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void begin() {
        zzas zzasVar = null;
        this.zzflg.zzfnb.clear();
        this.zzflq = false;
        this.zzfkw = null;
        this.zzflk = 0;
        this.zzflp = true;
        this.zzflr = false;
        this.zzflt = false;
        HashMap map = new HashMap();
        boolean z = false;
        for (Api<?> api : this.zzfkl.keySet()) {
            Api.zze zzeVar = this.zzflg.zzfmm.get(api.zzafe());
            boolean z2 = (api.zzafc().getPriority() == 1) | z;
            boolean zBooleanValue = this.zzfkl.get(api).booleanValue();
            if (zzeVar.zzaac()) {
                this.zzflq = true;
                if (zBooleanValue) {
                    this.zzfln.add(api.zzafe());
                } else {
                    this.zzflp = false;
                }
            }
            map.put(zzeVar, new zzat(this, api, zBooleanValue));
            z = z2;
        }
        if (z) {
            this.zzflq = false;
        }
        if (this.zzflq) {
            this.zzfki.zzc(Integer.valueOf(System.identityHashCode(this.zzflg.zzfjt)));
            zzba zzbaVar = new zzba(this, zzasVar);
            this.zzflo = (zzcps) this.zzfhl.zza(this.mContext, this.zzflg.zzfjt.getLooper(), this.zzfki, this.zzfki.zzajy(), zzbaVar, zzbaVar);
        }
        this.zzfll = this.zzflg.zzfmm.size();
        this.zzflv.add(zzbo.zzahn().submit(new zzau(this, map)));
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void connect() {
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final boolean disconnect() {
        zzahe();
        zzbf(true);
        this.zzflg.zzg(null);
        return true;
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void onConnected(Bundle bundle) {
        if (zzbr(1)) {
            if (bundle != null) {
                this.zzflm.putAll(bundle);
            }
            if (zzaha()) {
                zzahc();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void onConnectionSuspended(int i) {
        zze(new ConnectionResult(8, null));
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zzbr(1)) {
            zzb(connectionResult, api, z);
            if (zzaha()) {
                zzahc();
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        this.zzflg.zzfjt.zzfkr.add(t);
        return t;
    }

    @Override // com.google.android.gms.common.api.internal.zzbk
    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
