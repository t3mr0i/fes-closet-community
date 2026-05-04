package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class zzaa<T extends IInterface> extends zzd<T> implements Api.zze, zzae {
    private final Account zzduy;
    private final Set<Scope> zzecl;
    private final zzq zzfki;

    protected zzaa(Context context, Looper looper, int i, zzq zzqVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzaf.zzce(context), GoogleApiAvailability.getInstance(), i, zzqVar, (GoogleApiClient.ConnectionCallbacks) zzbp.zzu(connectionCallbacks), (GoogleApiClient.OnConnectionFailedListener) zzbp.zzu(onConnectionFailedListener));
    }

    private zzaa(Context context, Looper looper, zzaf zzafVar, GoogleApiAvailability googleApiAvailability, int i, zzq zzqVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, zzafVar, googleApiAvailability, i, connectionCallbacks == null ? null : new zzab(connectionCallbacks), onConnectionFailedListener == null ? null : new zzac(onConnectionFailedListener), zzqVar.zzajw());
        this.zzfki = zzqVar;
        this.zzduy = zzqVar.getAccount();
        Set<Scope> setZzajt = zzqVar.zzajt();
        Set<Scope> setZzb = zzb(setZzajt);
        Iterator<Scope> it = setZzb.iterator();
        while (it.hasNext()) {
            if (!setZzajt.contains(it.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.zzecl = setZzb;
    }

    @Override // com.google.android.gms.common.internal.zzd
    public final Account getAccount() {
        return this.zzduy;
    }

    @Override // com.google.android.gms.common.internal.zzd
    public com.google.android.gms.common.zzc[] zzaji() {
        return new com.google.android.gms.common.zzc[0];
    }

    @Override // com.google.android.gms.common.internal.zzd
    protected final Set<Scope> zzajm() {
        return this.zzecl;
    }

    protected final zzq zzake() {
        return this.zzfki;
    }

    @NonNull
    protected Set<Scope> zzb(@NonNull Set<Scope> set) {
        return set;
    }
}
