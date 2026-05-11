package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzcps;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class zzbp implements Handler.Callback {
    private static zzbp zzfnm;
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability zzfhk;
    public static final Status zzfnj = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zzfnk = new Status(4, "The user must be signed in to make this API call.");
    private static final Object zzaqc = new Object();
    private long zzfmj = 5000;
    private long zzfmi = 120000;
    private long zzfnl = 10000;
    private int zzfnn = -1;
    private final AtomicInteger zzfno = new AtomicInteger(1);
    private final AtomicInteger zzfnp = new AtomicInteger(0);
    private final Map<zzh<?>, zzbr<?>> zzfkj = new ConcurrentHashMap(5, 0.75f, 1);
    private zzak zzfnq = null;
    private final Set<zzh<?>> zzfnr = new ArraySet();
    private final Set<zzh<?>> zzfns = new ArraySet();

    private zzbp(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.mContext = context;
        this.mHandler = new Handler(looper, this);
        this.zzfhk = googleApiAvailability;
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6));
    }

    public static zzbp zzaho() {
        zzbp zzbpVar;
        synchronized (zzaqc) {
            com.google.android.gms.common.internal.zzbp.zzb(zzfnm, "Must guarantee manager is non-null before using getInstance");
            zzbpVar = zzfnm;
        }
        return zzbpVar;
    }

    public static void zzahp() {
        synchronized (zzaqc) {
            if (zzfnm != null) {
                zzbp zzbpVar = zzfnm;
                zzbpVar.zzfnp.incrementAndGet();
                zzbpVar.mHandler.sendMessageAtFrontOfQueue(zzbpVar.mHandler.obtainMessage(10));
            }
        }
    }

    @WorkerThread
    private final void zzahr() {
        Iterator<zzh<?>> it = this.zzfns.iterator();
        while (it.hasNext()) {
            this.zzfkj.remove(it.next()).signOut();
        }
        this.zzfns.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @WorkerThread
    private final void zzb(GoogleApi<?> googleApi) {
        Object objZzafk = googleApi.zzafk();
        zzbr<?> zzbrVar = this.zzfkj.get(objZzafk);
        if (zzbrVar == null) {
            zzbrVar = new zzbr<>(this, googleApi);
            this.zzfkj.put(objZzafk, zzbrVar);
        }
        if (zzbrVar.zzaac()) {
            this.zzfns.add(objZzafk);
        }
        zzbrVar.connect();
    }

    public static zzbp zzca(Context context) {
        zzbp zzbpVar;
        synchronized (zzaqc) {
            if (zzfnm == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zzfnm = new zzbp(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            zzbpVar = zzfnm;
        }
        return zzbpVar;
    }

    @Override // android.os.Handler.Callback
    @WorkerThread
    public final boolean handleMessage(Message message) {
        zzbr<?> next;
        switch (message.what) {
            case 1:
                this.zzfnl = ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.mHandler.removeMessages(12);
                Iterator<zzh<?>> it = this.zzfkj.keySet().iterator();
                while (it.hasNext()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(12, it.next()), this.zzfnl);
                }
                break;
            case 2:
                zzj zzjVar = (zzj) message.obj;
                Iterator<zzh<?>> it2 = zzjVar.zzafx().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else {
                        zzh<?> next2 = it2.next();
                        zzbr<?> zzbrVar = this.zzfkj.get(next2);
                        if (zzbrVar == null) {
                            zzjVar.zza(next2, new ConnectionResult(13));
                            break;
                        } else if (zzbrVar.isConnected()) {
                            zzjVar.zza(next2, ConnectionResult.zzffe);
                        } else if (zzbrVar.zzahy() != null) {
                            zzjVar.zza(next2, zzbrVar.zzahy());
                        } else {
                            zzbrVar.zza(zzjVar);
                        }
                    }
                }
            case 3:
                for (zzbr<?> zzbrVar2 : this.zzfkj.values()) {
                    zzbrVar2.zzahx();
                    zzbrVar2.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zzcq zzcqVar = (zzcq) message.obj;
                zzbr<?> zzbrVar3 = this.zzfkj.get(zzcqVar.zzfpa.zzafk());
                if (zzbrVar3 == null) {
                    zzb(zzcqVar.zzfpa);
                    zzbrVar3 = this.zzfkj.get(zzcqVar.zzfpa.zzafk());
                }
                if (!zzbrVar3.zzaac() || this.zzfnp.get() == zzcqVar.zzfoz) {
                    zzbrVar3.zza(zzcqVar.zzfoy);
                    break;
                } else {
                    zzcqVar.zzfoy.zzr(zzfnj);
                    zzbrVar3.signOut();
                    break;
                }
                break;
            case 5:
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<zzbr<?>> it3 = this.zzfkj.values().iterator();
                while (true) {
                    if (it3.hasNext()) {
                        next = it3.next();
                        if (next.getInstanceId() == i) {
                        }
                    } else {
                        next = null;
                    }
                }
                if (next != null) {
                    String errorString = this.zzfhk.getErrorString(connectionResult.getErrorCode());
                    String errorMessage = connectionResult.getErrorMessage();
                    next.zzv(new Status(17, new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                    break;
                } else {
                    Log.wtf("GoogleApiManager", new StringBuilder(76).append("Could not find API instance ").append(i).append(" while trying to fail enqueued calls.").toString(), new Exception());
                    break;
                }
            case 6:
                if (this.mContext.getApplicationContext() instanceof Application) {
                    zzk.zza((Application) this.mContext.getApplicationContext());
                    zzk.zzafz().zza(new zzbq(this));
                    if (!zzk.zzafz().zzbd(true)) {
                        this.zzfnl = 300000L;
                        break;
                    }
                }
                break;
            case 7:
                zzb((GoogleApi<?>) message.obj);
                break;
            case 9:
                if (this.zzfkj.containsKey(message.obj)) {
                    this.zzfkj.get(message.obj).resume();
                    break;
                }
                break;
            case 10:
                zzahr();
                break;
            case 11:
                if (this.zzfkj.containsKey(message.obj)) {
                    this.zzfkj.get(message.obj).zzahh();
                    break;
                }
                break;
            case 12:
                if (this.zzfkj.containsKey(message.obj)) {
                    this.zzfkj.get(message.obj).zzaib();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                return false;
        }
        return true;
    }

    final PendingIntent zza(zzh<?> zzhVar, int i) {
        zzcps zzcpsVarZzaic;
        zzbr<?> zzbrVar = this.zzfkj.get(zzhVar);
        if (zzbrVar != null && (zzcpsVarZzaic = zzbrVar.zzaic()) != null) {
            return PendingIntent.getActivity(this.mContext, i, zzcpsVarZzaic.zzaam(), 134217728);
        }
        return null;
    }

    public final <O extends Api.ApiOptions> Task<Boolean> zza(@NonNull GoogleApi<O> googleApi, @NonNull zzcl<?> zzclVar) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(13, new zzcq(new zzf(zzclVar, taskCompletionSource), this.zzfnp.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends Api.ApiOptions> Task<Void> zza(@NonNull GoogleApi<O> googleApi, @NonNull zzcr<Api.zzb, ?> zzcrVar, @NonNull zzdn<Api.zzb, ?> zzdnVar) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(8, new zzcq(new zzd(new zzcs(zzcrVar, zzdnVar), taskCompletionSource), this.zzfnp.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final Task<Void> zza(Iterable<? extends GoogleApi<?>> iterable) {
        zzj zzjVar = new zzj(iterable);
        Iterator<? extends GoogleApi<?>> it = iterable.iterator();
        while (it.hasNext()) {
            zzbr<?> zzbrVar = this.zzfkj.get(it.next().zzafk());
            if (zzbrVar == null || !zzbrVar.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(2, zzjVar));
                return zzjVar.getTask();
            }
        }
        zzjVar.zzafy();
        return zzjVar.getTask();
    }

    public final void zza(ConnectionResult connectionResult, int i) {
        if (zzc(connectionResult, i)) {
            return;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i, 0, connectionResult));
    }

    public final void zza(GoogleApi<?> googleApi) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, googleApi));
    }

    public final <O extends Api.ApiOptions, TResult> void zza(GoogleApi<O> googleApi, int i, zzdd<Api.zzb, TResult> zzddVar, TaskCompletionSource<TResult> taskCompletionSource, zzcz zzczVar) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzcq(new zze(i, zzddVar, taskCompletionSource, zzczVar), this.zzfnp.get(), googleApi)));
    }

    public final <O extends Api.ApiOptions> void zza(GoogleApi<O> googleApi, int i, zzm<? extends Result, Api.zzb> zzmVar) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzcq(new zzc(i, zzmVar), this.zzfnp.get(), googleApi)));
    }

    public final void zza(@NonNull zzak zzakVar) {
        synchronized (zzaqc) {
            if (this.zzfnq != zzakVar) {
                this.zzfnq = zzakVar;
                this.zzfnr.clear();
                this.zzfnr.addAll(zzakVar.zzagv());
            }
        }
    }

    final void zzafp() {
        this.zzfnp.incrementAndGet();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(10));
    }

    public final void zzafw() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }

    public final int zzahq() {
        return this.zzfno.getAndIncrement();
    }

    final void zzb(@NonNull zzak zzakVar) {
        synchronized (zzaqc) {
            if (this.zzfnq == zzakVar) {
                this.zzfnq = null;
                this.zzfnr.clear();
            }
        }
    }

    final boolean zzc(ConnectionResult connectionResult, int i) {
        return this.zzfhk.zza(this.mContext, connectionResult, i);
    }
}
