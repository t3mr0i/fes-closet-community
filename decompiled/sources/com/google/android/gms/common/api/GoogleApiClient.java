package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzbd;
import com.google.android.gms.common.api.internal.zzcf;
import com.google.android.gms.common.api.internal.zzcj;
import com.google.android.gms.common.api.internal.zzcv;
import com.google.android.gms.common.api.internal.zzdg;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.api.internal.zzw;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.internal.zzcpp;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.internal.zzcpt;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public abstract class GoogleApiClient {
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    private static final Set<GoogleApiClient> zzfgz = Collections.newSetFromMap(new WeakHashMap());

    public static final class Builder {
        private final Context mContext;
        private Looper zzakf;
        private Account zzduy;
        private String zzdxb;
        private final Set<Scope> zzfha;
        private final Set<Scope> zzfhb;
        private int zzfhc;
        private View zzfhd;
        private String zzfhe;
        private final Map<Api<?>, zzs> zzfhf;
        private final Map<Api<?>, Api.ApiOptions> zzfhg;
        private zzcf zzfhh;
        private int zzfhi;
        private OnConnectionFailedListener zzfhj;
        private GoogleApiAvailability zzfhk;
        private Api.zza<? extends zzcps, zzcpt> zzfhl;
        private final ArrayList<ConnectionCallbacks> zzfhm;
        private final ArrayList<OnConnectionFailedListener> zzfhn;
        private boolean zzfho;

        public Builder(@NonNull Context context) {
            this.zzfha = new HashSet();
            this.zzfhb = new HashSet();
            this.zzfhf = new ArrayMap();
            this.zzfhg = new ArrayMap();
            this.zzfhi = -1;
            this.zzfhk = GoogleApiAvailability.getInstance();
            this.zzfhl = zzcpp.zzdwp;
            this.zzfhm = new ArrayList<>();
            this.zzfhn = new ArrayList<>();
            this.zzfho = false;
            this.mContext = context;
            this.zzakf = context.getMainLooper();
            this.zzdxb = context.getPackageName();
            this.zzfhe = context.getClass().getName();
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            zzbp.zzb(connectionCallbacks, "Must provide a connected listener");
            this.zzfhm.add(connectionCallbacks);
            zzbp.zzb(onConnectionFailedListener, "Must provide a connection failed listener");
            this.zzfhn.add(onConnectionFailedListener);
        }

        private final <O extends Api.ApiOptions> void zza(Api<O> api, O o, Scope... scopeArr) {
            HashSet hashSet = new HashSet(api.zzafc().zzn(o));
            for (Scope scope : scopeArr) {
                hashSet.add(scope);
            }
            this.zzfhf.put(api, new zzs(hashSet));
        }

        public final Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            zzbp.zzb(api, "Api must not be null");
            this.zzfhg.put(api, null);
            List<Scope> listZzn = api.zzafc().zzn(null);
            this.zzfhb.addAll(listZzn);
            this.zzfha.addAll(listZzn);
            return this;
        }

        public final <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o) {
            zzbp.zzb(api, "Api must not be null");
            zzbp.zzb(o, "Null options are not permitted for this Api");
            this.zzfhg.put(api, o);
            List<Scope> listZzn = api.zzafc().zzn(o);
            this.zzfhb.addAll(listZzn);
            this.zzfha.addAll(listZzn);
            return this;
        }

        public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o, Scope... scopeArr) {
            zzbp.zzb(api, "Api must not be null");
            zzbp.zzb(o, "Null options are not permitted for this Api");
            this.zzfhg.put(api, o);
            zza(api, o, scopeArr);
            return this;
        }

        public final Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope... scopeArr) {
            zzbp.zzb(api, "Api must not be null");
            this.zzfhg.put(api, null);
            zza(api, null, scopeArr);
            return this;
        }

        public final Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            zzbp.zzb(connectionCallbacks, "Listener must not be null");
            this.zzfhm.add(connectionCallbacks);
            return this;
        }

        public final Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            zzbp.zzb(onConnectionFailedListener, "Listener must not be null");
            this.zzfhn.add(onConnectionFailedListener);
            return this;
        }

        public final Builder addScope(@NonNull Scope scope) {
            zzbp.zzb(scope, "Scope must not be null");
            this.zzfha.add(scope);
            return this;
        }

        public final GoogleApiClient build() {
            zzbp.zzb(!this.zzfhg.isEmpty(), "must call addApi() to add at least one API");
            zzq zzqVarZzafr = zzafr();
            Api<?> api = null;
            Map<Api<?>, zzs> mapZzaju = zzqVarZzafr.zzaju();
            ArrayMap arrayMap = new ArrayMap();
            ArrayMap arrayMap2 = new ArrayMap();
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Api<?> api2 : this.zzfhg.keySet()) {
                Api.ApiOptions apiOptions = this.zzfhg.get(api2);
                boolean z2 = mapZzaju.get(api2) != null;
                arrayMap.put(api2, Boolean.valueOf(z2));
                zzw zzwVar = new zzw(api2, z2);
                arrayList.add(zzwVar);
                Api.zza<?, O> zzaVarZzafd = api2.zzafd();
                Api.zze zzeVarZza = zzaVarZzafd.zza(this.mContext, this.zzakf, zzqVarZzafr, apiOptions, zzwVar, zzwVar);
                arrayMap2.put(api2.zzafe(), zzeVarZza);
                boolean z3 = zzaVarZzafd.getPriority() == 1 ? apiOptions != null : z;
                if (!zzeVarZza.zzaal()) {
                    api2 = api;
                } else if (api != null) {
                    String name = api2.getName();
                    String name2 = api.getName();
                    throw new IllegalStateException(new StringBuilder(String.valueOf(name).length() + 21 + String.valueOf(name2).length()).append(name).append(" cannot be used with ").append(name2).toString());
                }
                z = z3;
                api = api2;
            }
            if (api != null) {
                if (z) {
                    String name3 = api.getName();
                    throw new IllegalStateException(new StringBuilder(String.valueOf(name3).length() + 82).append("With using ").append(name3).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder").toString());
                }
                zzbp.zza(this.zzduy == null, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", api.getName());
                zzbp.zza(this.zzfha.equals(this.zzfhb), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", api.getName());
            }
            zzbd zzbdVar = new zzbd(this.mContext, new ReentrantLock(), this.zzakf, zzqVarZzafr, this.zzfhk, this.zzfhl, arrayMap, this.zzfhm, this.zzfhn, arrayMap2, this.zzfhi, zzbd.zza(arrayMap2.values(), true), arrayList, false);
            synchronized (GoogleApiClient.zzfgz) {
                GoogleApiClient.zzfgz.add(zzbdVar);
            }
            if (this.zzfhi >= 0) {
                zzi.zza(this.zzfhh).zza(this.zzfhi, zzbdVar, this.zzfhj);
            }
            return zzbdVar;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            zzcf zzcfVar = new zzcf(fragmentActivity);
            zzbp.zzb(i >= 0, "clientId must be non-negative");
            this.zzfhi = i;
            this.zzfhj = onConnectionFailedListener;
            this.zzfhh = zzcfVar;
            return this;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        public final Builder setAccountName(String str) {
            this.zzduy = str == null ? null : new Account(str, "com.google");
            return this;
        }

        public final Builder setGravityForPopups(int i) {
            this.zzfhc = i;
            return this;
        }

        public final Builder setHandler(@NonNull Handler handler) {
            zzbp.zzb(handler, "Handler must not be null");
            this.zzakf = handler.getLooper();
            return this;
        }

        public final Builder setViewForPopups(@NonNull View view) {
            zzbp.zzb(view, "View must not be null");
            this.zzfhd = view;
            return this;
        }

        public final Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public final zzq zzafr() {
            zzcpt zzcptVar = zzcpt.zzjno;
            if (this.zzfhg.containsKey(zzcpp.API)) {
                zzcptVar = (zzcpt) this.zzfhg.get(zzcpp.API);
            }
            return new zzq(this.zzduy, this.zzfha, this.zzfhf, this.zzfhc, this.zzfhd, this.zzdxb, this.zzfhe, zzcptVar);
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (zzfgz) {
            String strConcat = String.valueOf(str).concat("  ");
            int i = 0;
            for (GoogleApiClient googleApiClient : zzfgz) {
                printWriter.append((CharSequence) str).append("GoogleApiClient#").println(i);
                googleApiClient.dump(strConcat, fileDescriptor, printWriter, strArr);
                i++;
            }
        }
    }

    public static Set<GoogleApiClient> zzafo() {
        Set<GoogleApiClient> set;
        synchronized (zzfgz) {
            set = zzfgz;
        }
        return set;
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public void connect(int i) {
        throw new UnsupportedOperationException();
    }

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    @NonNull
    public <C extends Api.zze> C zza(@NonNull Api.zzc<C> zzcVar) {
        throw new UnsupportedOperationException();
    }

    public void zza(zzdg zzdgVar) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public boolean zza(zzcv zzcvVar) {
        throw new UnsupportedOperationException();
    }

    public void zzafp() {
        throw new UnsupportedOperationException();
    }

    public void zzb(zzdg zzdgVar) {
        throw new UnsupportedOperationException();
    }

    public <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <L> zzcj<L> zzp(@NonNull L l) {
        throw new UnsupportedOperationException();
    }
}
