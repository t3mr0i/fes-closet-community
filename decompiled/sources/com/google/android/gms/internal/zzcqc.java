package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbq;

/* loaded from: classes.dex */
public final class zzcqc extends com.google.android.gms.common.internal.zzaa<zzcqa> implements zzcps {
    private final com.google.android.gms.common.internal.zzq zzfki;
    private Integer zzftt;
    private final boolean zzjnv;
    private final Bundle zzjnw;

    private zzcqc(Context context, Looper looper, boolean z, com.google.android.gms.common.internal.zzq zzqVar, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, zzqVar, connectionCallbacks, onConnectionFailedListener);
        this.zzjnv = true;
        this.zzfki = zzqVar;
        this.zzjnw = bundle;
        this.zzftt = zzqVar.zzajz();
    }

    public zzcqc(Context context, Looper looper, boolean z, com.google.android.gms.common.internal.zzq zzqVar, zzcpt zzcptVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, true, zzqVar, zza(zzqVar), connectionCallbacks, onConnectionFailedListener);
    }

    public static Bundle zza(com.google.android.gms.common.internal.zzq zzqVar) {
        zzcpt zzcptVarZzajy = zzqVar.zzajy();
        Integer numZzajz = zzqVar.zzajz();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", zzqVar.getAccount());
        if (numZzajz != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", numZzajz.intValue());
        }
        if (zzcptVarZzajy != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzcptVarZzajy.zzbbx());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzcptVarZzajy.isIdTokenRequested());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzcptVarZzajy.getServerClientId());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzcptVarZzajy.zzbby());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzcptVarZzajy.zzbbz());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzcptVarZzajy.zzbca());
            if (zzcptVarZzajy.zzbcb() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzcptVarZzajy.zzbcb().longValue());
            }
            if (zzcptVarZzajy.zzbcc() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzcptVarZzajy.zzbcc().longValue());
            }
        }
        return bundle;
    }

    @Override // com.google.android.gms.internal.zzcps
    public final void connect() {
        zza(new com.google.android.gms.common.internal.zzm(this));
    }

    @Override // com.google.android.gms.internal.zzcps
    public final void zza(com.google.android.gms.common.internal.zzam zzamVar, boolean z) {
        try {
            ((zzcqa) zzajk()).zza(zzamVar, this.zzftt.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    @Override // com.google.android.gms.internal.zzcps
    public final void zza(zzcpy zzcpyVar) {
        com.google.android.gms.common.internal.zzbp.zzb(zzcpyVar, "Expecting a valid ISignInCallbacks");
        try {
            Account accountZzajq = this.zzfki.zzajq();
            ((zzcqa) zzajk()).zza(new zzcqd(new zzbq(accountZzajq, this.zzftt.intValue(), "<<default account>>".equals(accountZzajq.name) ? com.google.android.gms.auth.api.signin.internal.zzy.zzbl(getContext()).zzaas() : null)), zzcpyVar);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zzcpyVar.zzb(new zzcqf(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    @Override // com.google.android.gms.common.internal.zzd, com.google.android.gms.common.api.Api.zze
    public final boolean zzaac() {
        return this.zzjnv;
    }

    @Override // com.google.android.gms.internal.zzcps
    public final void zzbbw() {
        try {
            ((zzcqa) zzajk()).zzec(this.zzftt.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    @Override // com.google.android.gms.common.internal.zzd
    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return iInterfaceQueryLocalInterface instanceof zzcqa ? (zzcqa) iInterfaceQueryLocalInterface : new zzcqb(iBinder);
    }

    @Override // com.google.android.gms.common.internal.zzd
    protected final String zzhc() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.zzd
    protected final String zzhd() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.zzd
    protected final Bundle zzzu() {
        if (!getContext().getPackageName().equals(this.zzfki.zzajv())) {
            this.zzjnw.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzfki.zzajv());
        }
        return this.zzjnw;
    }
}
