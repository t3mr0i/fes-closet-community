package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class zzi extends zzo {
    private final SparseArray<zza> zzfij;

    class zza implements GoogleApiClient.OnConnectionFailedListener {
        public final int zzfik;
        public final GoogleApiClient zzfil;
        public final GoogleApiClient.OnConnectionFailedListener zzfim;

        public zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zzfik = i;
            this.zzfil = googleApiClient;
            this.zzfim = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String strValueOf = String.valueOf(connectionResult);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(strValueOf).length() + 27).append("beginFailureResolution for ").append(strValueOf).toString());
            zzi.this.zzb(connectionResult, this.zzfik);
        }
    }

    private zzi(zzcg zzcgVar) {
        super(zzcgVar);
        this.zzfij = new SparseArray<>();
        this.zzfon.zza("AutoManageHelper", this);
    }

    public static zzi zza(zzcf zzcfVar) {
        zzcg zzcgVarZzb = zzb(zzcfVar);
        zzi zziVar = (zzi) zzcgVarZzb.zza("AutoManageHelper", zzi.class);
        return zziVar != null ? zziVar : new zzi(zzcgVarZzb);
    }

    @Nullable
    private final zza zzbq(int i) {
        if (this.zzfij.size() <= i) {
            return null;
        }
        return this.zzfij.get(this.zzfij.keyAt(i));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zzfij.size(); i++) {
            zza zzaVarZzbq = zzbq(i);
            if (zzaVarZzbq != null) {
                printWriter.append((CharSequence) str).append("GoogleApiClient #").print(zzaVarZzbq.zzfik);
                printWriter.println(":");
                zzaVarZzbq.zzfil.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzo, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String strValueOf = String.valueOf(this.zzfij);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(strValueOf).length() + 14).append("onStart ").append(z).append(" ").append(strValueOf).toString());
        if (this.zzfiv.get() == null) {
            for (int i = 0; i < this.zzfij.size(); i++) {
                zza zzaVarZzbq = zzbq(i);
                if (zzaVarZzbq != null) {
                    zzaVarZzbq.zzfil.connect();
                }
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzo, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        super.onStop();
        for (int i = 0; i < this.zzfij.size(); i++) {
            zza zzaVarZzbq = zzbq(i);
            if (zzaVarZzbq != null) {
                zzaVarZzbq.zzfil.disconnect();
            }
        }
    }

    public final void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        com.google.android.gms.common.internal.zzbp.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        com.google.android.gms.common.internal.zzbp.zza(this.zzfij.indexOfKey(i) < 0, new StringBuilder(54).append("Already managing a GoogleApiClient with id ").append(i).toString());
        zzp zzpVar = this.zzfiv.get();
        boolean z = this.mStarted;
        String strValueOf = String.valueOf(zzpVar);
        Log.d("AutoManageHelper", new StringBuilder(String.valueOf(strValueOf).length() + 49).append("starting AutoManage for client ").append(i).append(" ").append(z).append(" ").append(strValueOf).toString());
        this.zzfij.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && zzpVar == null) {
            String strValueOf2 = String.valueOf(googleApiClient);
            Log.d("AutoManageHelper", new StringBuilder(String.valueOf(strValueOf2).length() + 11).append("connecting ").append(strValueOf2).toString());
            googleApiClient.connect();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzo
    protected final void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zzaVar = this.zzfij.get(i);
        if (zzaVar != null) {
            zzbp(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zzaVar.zzfim;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    @Override // com.google.android.gms.common.api.internal.zzo
    protected final void zzafw() {
        for (int i = 0; i < this.zzfij.size(); i++) {
            zza zzaVarZzbq = zzbq(i);
            if (zzaVarZzbq != null) {
                zzaVarZzbq.zzfil.connect();
            }
        }
    }

    public final void zzbp(int i) {
        zza zzaVar = this.zzfij.get(i);
        this.zzfij.remove(i);
        if (zzaVar != null) {
            zzaVar.zzfil.unregisterConnectionFailedListener(zzaVar);
            zzaVar.zzfil.disconnect();
        }
    }
}
