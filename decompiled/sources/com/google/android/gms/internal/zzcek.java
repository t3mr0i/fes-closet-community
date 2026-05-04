package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public final class zzcek extends zzcdu {
    protected zzcen zzivi;
    private volatile AppMeasurement.zzb zzivj;
    private AppMeasurement.zzb zzivk;
    private long zzivl;
    private final Map<Activity, zzcen> zzivm;
    private final CopyOnWriteArrayList<AppMeasurement.zza> zzivn;
    private boolean zzivo;
    private AppMeasurement.zzb zzivp;
    private String zzivq;

    public zzcek(zzccw zzccwVar) {
        super(zzccwVar);
        this.zzivm = new ArrayMap();
        this.zzivn = new CopyOnWriteArrayList<>();
    }

    @MainThread
    private final void zza(Activity activity, zzcen zzcenVar, boolean z) throws IllegalStateException {
        boolean z2;
        boolean zZza = true;
        AppMeasurement.zzb zzbVar = this.zzivj != null ? this.zzivj : (this.zzivk == null || Math.abs(zzvx().elapsedRealtime() - this.zzivl) >= 1000) ? null : this.zzivk;
        AppMeasurement.zzb zzbVar2 = zzbVar != null ? new AppMeasurement.zzb(zzbVar) : null;
        this.zzivo = true;
        try {
            try {
                Iterator<AppMeasurement.zza> it = this.zzivn.iterator();
                while (it.hasNext()) {
                    try {
                        zZza &= it.next().zza(zzbVar2, zzcenVar);
                    } catch (Exception e) {
                        zzaum().zzaye().zzj("onScreenChangeCallback threw exception", e);
                    }
                }
                this.zzivo = false;
                z2 = zZza;
            } catch (Throwable th) {
                this.zzivo = false;
                throw th;
            }
        } catch (Exception e2) {
            z2 = zZza;
            zzaum().zzaye().zzj("onScreenChangeCallback loop threw exception", e2);
            this.zzivo = false;
        }
        AppMeasurement.zzb zzbVar3 = this.zzivj == null ? this.zzivk : this.zzivj;
        if (z2) {
            if (zzcenVar.zzikn == null) {
                zzcenVar.zzikn = zzjt(activity.getClass().getCanonicalName());
            }
            zzcen zzcenVar2 = new zzcen(zzcenVar);
            this.zzivk = this.zzivj;
            this.zzivl = zzvx().elapsedRealtime();
            this.zzivj = zzcenVar2;
            zzaul().zzg(new zzcel(this, z, zzbVar3, zzcenVar2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zza(@NonNull zzcen zzcenVar) {
        zzaty().zzaj(zzvx().elapsedRealtime());
        if (zzauk().zzbs(zzcenVar.zzivw)) {
            zzcenVar.zzivw = false;
        }
    }

    public static void zza(AppMeasurement.zzb zzbVar, Bundle bundle) {
        if (bundle == null || zzbVar == null || bundle.containsKey("_sc")) {
            return;
        }
        if (zzbVar.zzikm != null) {
            bundle.putString("_sn", zzbVar.zzikm);
        }
        bundle.putString("_sc", zzbVar.zzikn);
        bundle.putLong("_si", zzbVar.zziko);
    }

    private static String zzjt(String str) {
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length == 0) {
            return str.substring(0, 36);
        }
        String str2 = strArrSplit[strArrSplit.length - 1];
        return str2.length() > 36 ? str2.substring(0, 36) : str2;
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @MainThread
    public final void onActivityDestroyed(Activity activity) {
        this.zzivm.remove(activity);
    }

    @MainThread
    public final void onActivityPaused(Activity activity) throws IllegalStateException {
        zzcen zzcenVarZzq = zzq(activity);
        this.zzivk = this.zzivj;
        this.zzivl = zzvx().elapsedRealtime();
        this.zzivj = null;
        zzaul().zzg(new zzcem(this, zzcenVarZzq));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) throws IllegalStateException {
        zza(activity, zzq(activity), false);
        zzcan zzcanVarZzaty = zzaty();
        zzcanVarZzaty.zzaul().zzg(new zzcaq(zzcanVarZzaty, zzcanVarZzaty.zzvx().elapsedRealtime()));
    }

    @MainThread
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzcen zzcenVar;
        if (bundle == null || (zzcenVar = this.zzivm.get(activity)) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", zzcenVar.zziko);
        bundle2.putString("name", zzcenVar.zzikm);
        bundle2.putString("referrer_name", zzcenVar.zzikn);
        bundle.putBundle("com.google.firebase.analytics.screen_service", bundle2);
    }

    @MainThread
    public final void registerOnScreenChangeCallback(@NonNull AppMeasurement.zza zzaVar) {
        zzatw();
        if (zzaVar == null) {
            zzaum().zzayg().log("Attempting to register null OnScreenChangeCallback");
        } else {
            this.zzivn.remove(zzaVar);
            this.zzivn.add(zzaVar);
        }
    }

    @MainThread
    public final void setCurrentScreen(@NonNull Activity activity, @Size(max = 36, min = 1) @Nullable String str, @Size(max = 36, min = 1) @Nullable String str2) throws IllegalStateException {
        if (activity == null) {
            zzaum().zzayg().log("setCurrentScreen must be called with a non-null activity");
            return;
        }
        zzaul();
        if (!zzccr.zzaq()) {
            zzaum().zzayg().log("setCurrentScreen must be called from the main thread");
            return;
        }
        if (this.zzivo) {
            zzaum().zzayg().log("Cannot call setCurrentScreen from onScreenChangeCallback");
            return;
        }
        if (this.zzivj == null) {
            zzaum().zzayg().log("setCurrentScreen cannot be called while no activity active");
            return;
        }
        if (this.zzivm.get(activity) == null) {
            zzaum().zzayg().log("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }
        if (str2 == null) {
            str2 = zzjt(activity.getClass().getCanonicalName());
        }
        boolean zEquals = this.zzivj.zzikn.equals(str2);
        boolean zZzas = zzcfw.zzas(this.zzivj.zzikm, str);
        if (zEquals && zZzas) {
            zzaum().zzayh().log("setCurrentScreen cannot be called with the same class and name");
            return;
        }
        if (str != null && (str.length() <= 0 || str.length() > zzcax.zzavr())) {
            zzaum().zzayg().zzj("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            return;
        }
        if (str2 != null && (str2.length() <= 0 || str2.length() > zzcax.zzavr())) {
            zzaum().zzayg().zzj("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            return;
        }
        zzaum().zzayk().zze("Setting current screen to name, class", str == null ? "null" : str, str2);
        zzcen zzcenVar = new zzcen(str, str2, zzaui().zzazy());
        this.zzivm.put(activity, zzcenVar);
        zza(activity, zzcenVar, true);
    }

    @MainThread
    public final void unregisterOnScreenChangeCallback(@NonNull AppMeasurement.zza zzaVar) {
        zzatw();
        this.zzivn.remove(zzaVar);
    }

    @WorkerThread
    public final void zza(String str, AppMeasurement.zzb zzbVar) {
        zzuj();
        synchronized (this) {
            if (this.zzivq == null || this.zzivq.equals(str) || zzbVar != null) {
                this.zzivq = str;
                this.zzivp = zzbVar;
            }
        }
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzatx() {
        super.zzatx();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcan zzaty() {
        return super.zzaty();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcau zzatz() {
        return super.zzatz();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcdw zzaua() {
        return super.zzaua();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbr zzaub() {
        return super.zzaub();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbe zzauc() {
        return super.zzauc();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzceo zzaud() {
        return super.zzaud();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcek zzaue() {
        return super.zzaue();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbs zzauf() {
        return super.zzauf();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcay zzaug() {
        return super.zzaug();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbu zzauh() {
        return super.zzauh();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfw zzaui() {
        return super.zzaui();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccq zzauj() {
        return super.zzauj();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcfl zzauk() {
        return super.zzauk();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzccr zzaul() {
        return super.zzaul();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcbw zzaum() {
        return super.zzaum();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcch zzaun() {
        return super.zzaun();
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ zzcax zzauo() {
        return super.zzauo();
    }

    @WorkerThread
    public final zzcen zzazo() {
        zzwk();
        zzuj();
        return this.zzivi;
    }

    public final AppMeasurement.zzb zzazp() {
        zzatw();
        AppMeasurement.zzb zzbVar = this.zzivj;
        if (zzbVar == null) {
            return null;
        }
        return new AppMeasurement.zzb(zzbVar);
    }

    @MainThread
    final zzcen zzq(@NonNull Activity activity) {
        com.google.android.gms.common.internal.zzbp.zzu(activity);
        zzcen zzcenVar = this.zzivm.get(activity);
        if (zzcenVar != null) {
            return zzcenVar;
        }
        zzcen zzcenVar2 = new zzcen(null, zzjt(activity.getClass().getCanonicalName()), zzaui().zzazy());
        this.zzivm.put(activity, zzcenVar2);
        return zzcenVar2;
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    @Override // com.google.android.gms.internal.zzcdu
    protected final void zzuk() {
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ com.google.android.gms.common.util.zzd zzvx() {
        return super.zzvx();
    }
}
