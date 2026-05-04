package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class zzd<T extends IInterface> {
    private static String[] zzfti = {"service_esmobile", "service_googleme"};
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private final Looper zzakf;
    private final com.google.android.gms.common.zze zzfkn;
    private int zzfsn;
    private long zzfso;
    private long zzfsp;
    private int zzfsq;
    private long zzfsr;
    private zzal zzfss;
    private final zzaf zzfst;
    private final Object zzfsu;
    private zzax zzfsv;
    protected zzj zzfsw;
    private T zzfsx;
    private final ArrayList<zzi<?>> zzfsy;
    private zzl zzfsz;
    private int zzfta;
    private final zzf zzftb;
    private final zzg zzftc;
    private final int zzftd;
    private final String zzfte;
    private ConnectionResult zzftf;
    private boolean zzftg;
    protected AtomicInteger zzfth;

    protected zzd(Context context, Looper looper, int i, zzf zzfVar, zzg zzgVar, String str) {
        this(context, looper, zzaf.zzce(context), com.google.android.gms.common.zze.zzaex(), i, (zzf) zzbp.zzu(zzfVar), (zzg) zzbp.zzu(zzgVar), null);
    }

    protected zzd(Context context, Looper looper, zzaf zzafVar, com.google.android.gms.common.zze zzeVar, int i, zzf zzfVar, zzg zzgVar, String str) {
        this.mLock = new Object();
        this.zzfsu = new Object();
        this.zzfsy = new ArrayList<>();
        this.zzfta = 1;
        this.zzftf = null;
        this.zzftg = false;
        this.zzfth = new AtomicInteger(0);
        this.mContext = (Context) zzbp.zzb(context, "Context must not be null");
        this.zzakf = (Looper) zzbp.zzb(looper, "Looper must not be null");
        this.zzfst = (zzaf) zzbp.zzb(zzafVar, "Supervisor must not be null");
        this.zzfkn = (com.google.android.gms.common.zze) zzbp.zzb(zzeVar, "API availability must not be null");
        this.mHandler = new zzh(this, looper);
        this.zzftd = i;
        this.zzftb = zzfVar;
        this.zzftc = zzgVar;
        this.zzfte = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i, T t) {
        zzbp.zzbh((i == 4) == (t != null));
        synchronized (this.mLock) {
            this.zzfta = i;
            this.zzfsx = t;
            switch (i) {
                case 1:
                    if (this.zzfsz != null) {
                        this.zzfst.zza(zzhc(), zzaje(), 129, this.zzfsz, zzajf());
                        this.zzfsz = null;
                        break;
                    }
                    break;
                case 2:
                case 3:
                    if (this.zzfsz != null && this.zzfss != null) {
                        String strZzakl = this.zzfss.zzakl();
                        String packageName = this.zzfss.getPackageName();
                        Log.e("GmsClient", new StringBuilder(String.valueOf(strZzakl).length() + 70 + String.valueOf(packageName).length()).append("Calling connect() while still connected, missing disconnect() for ").append(strZzakl).append(" on ").append(packageName).toString());
                        this.zzfst.zza(this.zzfss.zzakl(), this.zzfss.getPackageName(), this.zzfss.zzakh(), this.zzfsz, zzajf());
                        this.zzfth.incrementAndGet();
                    }
                    this.zzfsz = new zzl(this, this.zzfth.get());
                    this.zzfss = new zzal(zzaje(), zzhc(), false, 129);
                    if (!this.zzfst.zza(new zzag(this.zzfss.zzakl(), this.zzfss.getPackageName(), this.zzfss.zzakh()), this.zzfsz, zzajf())) {
                        String strZzakl2 = this.zzfss.zzakl();
                        String packageName2 = this.zzfss.getPackageName();
                        Log.e("GmsClient", new StringBuilder(String.valueOf(strZzakl2).length() + 34 + String.valueOf(packageName2).length()).append("unable to connect to service: ").append(strZzakl2).append(" on ").append(packageName2).toString());
                        zza(16, (Bundle) null, this.zzfth.get());
                        break;
                    }
                    break;
                case 4:
                    zza((zzd<T>) t);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(int i, int i2, T t) {
        boolean z;
        synchronized (this.mLock) {
            if (this.zzfta != i) {
                z = false;
            } else {
                zza(i2, (int) t);
                z = true;
            }
        }
        return z;
    }

    @Nullable
    private final String zzajf() {
        return this.zzfte == null ? this.mContext.getClass().getName() : this.zzfte;
    }

    private final boolean zzajh() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzfta == 3;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzajn() throws ClassNotFoundException {
        if (this.zzftg || TextUtils.isEmpty(zzhd()) || TextUtils.isEmpty(null)) {
            return false;
        }
        try {
            Class.forName(zzhd());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzcd(int i) {
        int i2;
        if (zzajh()) {
            i2 = 5;
            this.zzftg = true;
        } else {
            i2 = 4;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(i2, this.zzfth.get(), 16));
    }

    public void disconnect() {
        this.zzfth.incrementAndGet();
        synchronized (this.zzfsy) {
            int size = this.zzfsy.size();
            for (int i = 0; i < size; i++) {
                this.zzfsy.get(i).removeListener();
            }
            this.zzfsy.clear();
        }
        synchronized (this.zzfsu) {
            this.zzfsv = null;
        }
        zza(1, (int) null);
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        zzax zzaxVar;
        synchronized (this.mLock) {
            i = this.zzfta;
            t = this.zzfsx;
        }
        synchronized (this.zzfsu) {
            zzaxVar = this.zzfsv;
        }
        printWriter.append((CharSequence) str).append("mConnectState=");
        switch (i) {
            case 1:
                printWriter.print("DISCONNECTED");
                break;
            case 2:
                printWriter.print("REMOTE_CONNECTING");
                break;
            case 3:
                printWriter.print("LOCAL_CONNECTING");
                break;
            case 4:
                printWriter.print("CONNECTED");
                break;
            case 5:
                printWriter.print("DISCONNECTING");
                break;
            default:
                printWriter.print("UNKNOWN");
                break;
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append((CharSequence) zzhd()).append("@").append((CharSequence) Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (zzaxVar == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(zzaxVar.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzfsp > 0) {
            PrintWriter printWriterAppend = printWriter.append((CharSequence) str).append("lastConnectedTime=");
            long j = this.zzfsp;
            String str2 = simpleDateFormat.format(new Date(this.zzfsp));
            printWriterAppend.println(new StringBuilder(String.valueOf(str2).length() + 21).append(j).append(" ").append(str2).toString());
        }
        if (this.zzfso > 0) {
            printWriter.append((CharSequence) str).append("lastSuspendedCause=");
            switch (this.zzfsn) {
                case 1:
                    printWriter.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case 2:
                    printWriter.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    printWriter.append((CharSequence) String.valueOf(this.zzfsn));
                    break;
            }
            PrintWriter printWriterAppend2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzfso;
            String str3 = simpleDateFormat.format(new Date(this.zzfso));
            printWriterAppend2.println(new StringBuilder(String.valueOf(str3).length() + 21).append(j2).append(" ").append(str3).toString());
        }
        if (this.zzfsr > 0) {
            printWriter.append((CharSequence) str).append("lastFailedStatus=").append((CharSequence) CommonStatusCodes.getStatusCodeString(this.zzfsq));
            PrintWriter printWriterAppend3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzfsr;
            String str4 = simpleDateFormat.format(new Date(this.zzfsr));
            printWriterAppend3.println(new StringBuilder(String.valueOf(str4).length() + 21).append(j3).append(" ").append(str4).toString());
        }
    }

    public Account getAccount() {
        return null;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzakf;
    }

    public final boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzfta == 4;
        }
        return z;
    }

    public final boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzfta == 2 || this.zzfta == 3;
        }
        return z;
    }

    @CallSuper
    protected void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzfsq = connectionResult.getErrorCode();
        this.zzfsr = System.currentTimeMillis();
    }

    @CallSuper
    protected final void onConnectionSuspended(int i) {
        this.zzfsn = i;
        this.zzfso = System.currentTimeMillis();
    }

    protected final void zza(int i, @Nullable Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, new zzo(this, i, null)));
    }

    protected void zza(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new zzn(this, i, iBinder, bundle)));
    }

    @CallSuper
    protected void zza(@NonNull T t) {
        this.zzfsp = System.currentTimeMillis();
    }

    @WorkerThread
    public final void zza(zzam zzamVar, Set<Scope> set) {
        Bundle bundleZzzu = zzzu();
        zzy zzyVar = new zzy(this.zzftd);
        zzyVar.zzfud = this.mContext.getPackageName();
        zzyVar.zzfug = bundleZzzu;
        if (set != null) {
            zzyVar.zzfuf = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (zzaac()) {
            zzyVar.zzfuh = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (zzamVar != null) {
                zzyVar.zzfue = zzamVar.asBinder();
            }
        } else if (zzajl()) {
            zzyVar.zzfuh = getAccount();
        }
        zzyVar.zzfui = zzaji();
        try {
            synchronized (this.zzfsu) {
                if (this.zzfsv != null) {
                    this.zzfsv.zza(new zzk(this, this.zzfth.get()), zzyVar);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            zzcc(1);
        } catch (RemoteException e2) {
            e = e2;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            zza(8, (IBinder) null, (Bundle) null, this.zzfth.get());
        } catch (SecurityException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            e = e4;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            zza(8, (IBinder) null, (Bundle) null, this.zzfth.get());
        }
    }

    public void zza(@NonNull zzj zzjVar) {
        this.zzfsw = (zzj) zzbp.zzb(zzjVar, "Connection progress callbacks cannot be null.");
        zza(2, (int) null);
    }

    protected final void zza(@NonNull zzj zzjVar, int i, @Nullable PendingIntent pendingIntent) {
        this.zzfsw = (zzj) zzbp.zzb(zzjVar, "Connection progress callbacks cannot be null.");
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzfth.get(), i, pendingIntent));
    }

    public boolean zzaac() {
        return false;
    }

    public boolean zzaal() {
        return false;
    }

    public Intent zzaam() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public Bundle zzaeh() {
        return null;
    }

    public boolean zzaff() {
        return true;
    }

    @Nullable
    public final IBinder zzafg() {
        IBinder iBinderAsBinder;
        synchronized (this.zzfsu) {
            iBinderAsBinder = this.zzfsv == null ? null : this.zzfsv.asBinder();
        }
        return iBinderAsBinder;
    }

    protected String zzaje() {
        return "com.google.android.gms";
    }

    public final void zzajg() {
        int iIsGooglePlayServicesAvailable = this.zzfkn.isGooglePlayServicesAvailable(this.mContext);
        if (iIsGooglePlayServicesAvailable == 0) {
            zza(new zzm(this));
        } else {
            zza(1, (int) null);
            zza(new zzm(this), iIsGooglePlayServicesAvailable, (PendingIntent) null);
        }
    }

    public com.google.android.gms.common.zzc[] zzaji() {
        return new com.google.android.gms.common.zzc[0];
    }

    protected final void zzajj() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public final T zzajk() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzfta == 5) {
                throw new DeadObjectException();
            }
            zzajj();
            zzbp.zza(this.zzfsx != null, "Client is connected but service is null");
            t = this.zzfsx;
        }
        return t;
    }

    public boolean zzajl() {
        return false;
    }

    protected Set<Scope> zzajm() {
        return Collections.EMPTY_SET;
    }

    public final void zzcc(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, this.zzfth.get(), i));
    }

    @Nullable
    protected abstract T zzd(IBinder iBinder);

    @NonNull
    protected abstract String zzhc();

    @NonNull
    protected abstract String zzhd();

    protected Bundle zzzu() {
        return new Bundle();
    }
}
