package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.zzak;
import com.google.android.gms.common.api.internal.zzbp;
import com.google.android.gms.common.api.internal.zzbr;
import com.google.android.gms.common.api.internal.zzbx;
import com.google.android.gms.common.api.internal.zzcw;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.api.internal.zzdd;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;

/* loaded from: classes.dex */
public class GoogleApi<O extends Api.ApiOptions> {
    private final Context mContext;
    private final int mId;
    private final Looper zzakf;
    private final Api<O> zzfdf;
    private final O zzfgq;
    private final zzh<O> zzfgr;
    private final GoogleApiClient zzfgs;
    private final zzcz zzfgt;
    protected final zzbp zzfgu;

    public static class zza {
        public static final zza zzfgv = new zzd().zzafn();
        public final zzcz zzfgw;
        public final Looper zzfgx;

        private zza(zzcz zzczVar, Account account, Looper looper) {
            this.zzfgw = zzczVar;
            this.zzfgx = looper;
        }
    }

    @MainThread
    public GoogleApi(@NonNull Activity activity, Api<O> api, O o, zza zzaVar) {
        com.google.android.gms.common.internal.zzbp.zzb(activity, "Null activity is not permitted.");
        com.google.android.gms.common.internal.zzbp.zzb(api, "Api must not be null.");
        com.google.android.gms.common.internal.zzbp.zzb(zzaVar, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = activity.getApplicationContext();
        this.zzfdf = api;
        this.zzfgq = o;
        this.zzakf = zzaVar.zzfgx;
        this.zzfgr = zzh.zza(this.zzfdf, this.zzfgq);
        this.zzfgs = new zzbx(this);
        this.zzfgu = zzbp.zzca(this.mContext);
        this.mId = this.zzfgu.zzahq();
        this.zzfgt = zzaVar.zzfgw;
        zzak.zza(activity, this.zzfgu, this.zzfgr);
        this.zzfgu.zza((GoogleApi<?>) this);
    }

    @Deprecated
    public GoogleApi(@NonNull Activity activity, Api<O> api, O o, zzcz zzczVar) {
        this(activity, (Api) api, (Api.ApiOptions) o, new zzd().zza(zzczVar).zza(activity.getMainLooper()).zzafn());
    }

    protected GoogleApi(@NonNull Context context, Api<O> api, Looper looper) {
        com.google.android.gms.common.internal.zzbp.zzb(context, "Null context is not permitted.");
        com.google.android.gms.common.internal.zzbp.zzb(api, "Api must not be null.");
        com.google.android.gms.common.internal.zzbp.zzb(looper, "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.zzfdf = api;
        this.zzfgq = null;
        this.zzakf = looper;
        this.zzfgr = zzh.zzb(api);
        this.zzfgs = new zzbx(this);
        this.zzfgu = zzbp.zzca(this.mContext);
        this.mId = this.zzfgu.zzahq();
        this.zzfgt = new com.google.android.gms.common.api.internal.zzg();
    }

    @Deprecated
    public GoogleApi(@NonNull Context context, Api<O> api, O o, Looper looper, zzcz zzczVar) {
        this(context, api, (Api.ApiOptions) null, new zzd().zza(looper).zza(zzczVar).zzafn());
    }

    public GoogleApi(@NonNull Context context, Api<O> api, O o, zza zzaVar) {
        com.google.android.gms.common.internal.zzbp.zzb(context, "Null context is not permitted.");
        com.google.android.gms.common.internal.zzbp.zzb(api, "Api must not be null.");
        com.google.android.gms.common.internal.zzbp.zzb(zzaVar, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = context.getApplicationContext();
        this.zzfdf = api;
        this.zzfgq = o;
        this.zzakf = zzaVar.zzfgx;
        this.zzfgr = zzh.zza(this.zzfdf, this.zzfgq);
        this.zzfgs = new zzbx(this);
        this.zzfgu = zzbp.zzca(this.mContext);
        this.mId = this.zzfgu.zzahq();
        this.zzfgt = zzaVar.zzfgw;
        this.zzfgu.zza((GoogleApi<?>) this);
    }

    @Deprecated
    public GoogleApi(@NonNull Context context, Api<O> api, O o, zzcz zzczVar) {
        this(context, api, o, new zzd().zza(zzczVar).zzafn());
    }

    private final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zza(int i, @NonNull T t) {
        t.zzagg();
        this.zzfgu.zza(this, i, (zzm<? extends Result, Api.zzb>) t);
        return t;
    }

    private final <TResult, A extends Api.zzb> Task<TResult> zza(int i, @NonNull zzdd<A, TResult> zzddVar) {
        TaskCompletionSource<TResult> taskCompletionSource = new TaskCompletionSource<>();
        this.zzfgu.zza(this, i, zzddVar, taskCompletionSource, this.zzfgt);
        return taskCompletionSource.getTask();
    }

    private final zzr zzafm() {
        GoogleSignInAccount googleSignInAccount;
        return new zzr().zze(this.zzfgq instanceof Api.ApiOptions.HasGoogleSignInAccountOptions ? ((Api.ApiOptions.HasGoogleSignInAccountOptions) this.zzfgq).getGoogleSignInAccount().getAccount() : this.zzfgq instanceof Api.ApiOptions.HasAccountOptions ? ((Api.ApiOptions.HasAccountOptions) this.zzfgq).getAccount() : null).zze((!(this.zzfgq instanceof Api.ApiOptions.HasGoogleSignInAccountOptions) || (googleSignInAccount = ((Api.ApiOptions.HasGoogleSignInAccountOptions) this.zzfgq).getGoogleSignInAccount()) == null) ? Collections.emptySet() : googleSignInAccount.getGrantedScopes());
    }

    public final Context getApplicationContext() {
        return this.mContext;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    public final Looper getLooper() {
        return this.zzakf;
    }

    @WorkerThread
    public Api.zze zza(Looper looper, zzbr<O> zzbrVar) {
        return this.zzfdf.zzafd().zza(this.mContext, looper, zzafm().zzfz(this.mContext.getPackageName()).zzga(this.mContext.getClass().getName()).zzaka(), this.zzfgq, zzbrVar, zzbrVar);
    }

    public zzcw zza(Context context, Handler handler) {
        return new zzcw(context, handler, zzafm().zzaka());
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zza(@NonNull T t) {
        return (T) zza(0, (int) t);
    }

    public final <TResult, A extends Api.zzb> Task<TResult> zza(zzdd<A, TResult> zzddVar) {
        return zza(0, zzddVar);
    }

    public final Api<O> zzafj() {
        return this.zzfdf;
    }

    public final zzh<O> zzafk() {
        return this.zzfgr;
    }

    public final GoogleApiClient zzafl() {
        return this.zzfgs;
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T t) {
        return (T) zza(1, (int) t);
    }

    public final <TResult, A extends Api.zzb> Task<TResult> zzb(zzdd<A, TResult> zzddVar) {
        return zza(1, zzddVar);
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzc(@NonNull T t) {
        return (T) zza(2, (int) t);
    }
}
