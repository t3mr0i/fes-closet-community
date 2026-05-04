package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class zzceo extends zzcdu {
    private final zzcfb zzivx;
    private zzcbo zzivy;
    private Boolean zzivz;
    private final zzcbc zziwa;
    private final zzcfq zziwb;
    private final List<Runnable> zziwc;
    private final zzcbc zziwd;

    protected zzceo(zzccw zzccwVar) {
        super(zzccwVar);
        this.zziwc = new ArrayList();
        this.zziwb = new zzcfq(zzccwVar.zzvx());
        this.zzivx = new zzcfb(this);
        this.zziwa = new zzcep(this, zzccwVar);
        this.zziwd = new zzcet(this, zzccwVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void onServiceDisconnected(ComponentName componentName) {
        zzuj();
        if (this.zzivy != null) {
            this.zzivy = null;
            zzaum().zzayk().zzj("Disconnected from device MeasurementService", componentName);
            zzuj();
            zzxh();
        }
    }

    static /* synthetic */ zzcbo zza(zzceo zzceoVar, zzcbo zzcboVar) {
        zzceoVar.zzivy = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzazs() {
        zzuj();
        zzaum().zzayk().zzj("Processing queued up service tasks", Integer.valueOf(this.zziwc.size()));
        Iterator<Runnable> it = this.zziwc.iterator();
        while (it.hasNext()) {
            try {
                it.next().run();
            } catch (Throwable th) {
                zzaum().zzaye().zzj("Task exception while flushing queue", th);
            }
        }
        this.zziwc.clear();
        this.zziwd.cancel();
    }

    @WorkerThread
    @Nullable
    private final zzcas zzbr(boolean z) {
        zzcax.zzawl();
        return zzaub().zzjb(z ? zzaum().zzayl() : null);
    }

    @WorkerThread
    private final void zzj(Runnable runnable) throws IllegalStateException {
        zzuj();
        if (isConnected()) {
            runnable.run();
        } else {
            if (this.zziwc.size() >= zzcax.zzawq()) {
                zzaum().zzaye().log("Discarding data. Max runnable queue size reached");
                return;
            }
            this.zziwc.add(runnable);
            this.zziwd.zzs(60000L);
            zzxh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzww() {
        zzuj();
        this.zziwb.start();
        this.zziwa.zzs(zzcax.zzawi());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void zzwx() {
        zzuj();
        if (isConnected()) {
            zzaum().zzayk().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    public final void disconnect() {
        zzuj();
        zzwk();
        try {
            com.google.android.gms.common.stats.zza.zzakz();
            getContext().unbindService(this.zzivx);
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        }
        this.zzivy = null;
    }

    @Override // com.google.android.gms.internal.zzcdt
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final boolean isConnected() {
        zzuj();
        zzwk();
        return this.zzivy != null;
    }

    @WorkerThread
    protected final void zza(zzcbo zzcboVar) {
        zzuj();
        com.google.android.gms.common.internal.zzbp.zzu(zzcboVar);
        this.zzivy = zzcboVar;
        zzww();
        zzazs();
    }

    @WorkerThread
    final void zza(zzcbo zzcboVar, zzbck zzbckVar, zzcas zzcasVar) throws Throwable {
        zzuj();
        zzatw();
        zzwk();
        zzcax.zzawl();
        ArrayList arrayList = new ArrayList();
        zzcax.zzawu();
        int size = 100;
        for (int i = 0; i < 1001 && size == 100; i++) {
            List<zzbck> listZzdw = zzauf().zzdw(100);
            if (listZzdw != null) {
                arrayList.addAll(listZzdw);
                size = listZzdw.size();
            } else {
                size = 0;
            }
            if (zzbckVar != null && size < 100) {
                arrayList.add(zzbckVar);
            }
            ArrayList arrayList2 = arrayList;
            int size2 = arrayList2.size();
            int i2 = 0;
            while (i2 < size2) {
                Object obj = arrayList2.get(i2);
                i2++;
                zzbck zzbckVar2 = (zzbck) obj;
                if (zzbckVar2 instanceof zzcbk) {
                    try {
                        zzcboVar.zza((zzcbk) zzbckVar2, zzcasVar);
                    } catch (RemoteException e) {
                        zzaum().zzaye().zzj("Failed to send event to the service", e);
                    }
                } else if (zzbckVar2 instanceof zzcft) {
                    try {
                        zzcboVar.zza((zzcft) zzbckVar2, zzcasVar);
                    } catch (RemoteException e2) {
                        zzaum().zzaye().zzj("Failed to send attribute to the service", e2);
                    }
                } else if (zzbckVar2 instanceof zzcav) {
                    try {
                        zzcboVar.zza((zzcav) zzbckVar2, zzcasVar);
                    } catch (RemoteException e3) {
                        zzaum().zzaye().zzj("Failed to send conditional property to the service", e3);
                    }
                } else {
                    zzaum().zzaye().log("Discarding data. Unrecognized parcel type.");
                }
            }
        }
    }

    @WorkerThread
    protected final void zza(AppMeasurement.zzb zzbVar) throws IllegalStateException {
        zzuj();
        zzwk();
        zzj(new zzces(this, zzbVar));
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzuj();
        zzwk();
        zzj(new zzceq(this, atomicReference, zzbr(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzcav>> atomicReference, String str, String str2, String str3) throws IllegalStateException {
        zzuj();
        zzwk();
        zzj(new zzcex(this, atomicReference, str, str2, str3, zzbr(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzcft>> atomicReference, String str, String str2, String str3, boolean z) throws IllegalStateException {
        zzuj();
        zzwk();
        zzj(new zzcey(this, atomicReference, str, str2, str3, z, zzbr(false)));
    }

    @WorkerThread
    protected final void zza(AtomicReference<List<zzcft>> atomicReference, boolean z) throws IllegalStateException {
        zzuj();
        zzwk();
        zzj(new zzcfa(this, atomicReference, zzbr(false), z));
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
    protected final void zzazq() {
        zzuj();
        zzwk();
        zzj(new zzceu(this, zzbr(true)));
    }

    @WorkerThread
    protected final void zzazr() {
        zzuj();
        zzwk();
        zzj(new zzcer(this, zzbr(true)));
    }

    @WorkerThread
    protected final void zzb(zzcft zzcftVar) {
        zzuj();
        zzwk();
        zzcax.zzawl();
        zzj(new zzcez(this, zzauf().zza(zzcftVar), zzcftVar, zzbr(true)));
    }

    @WorkerThread
    protected final void zzc(zzcbk zzcbkVar, String str) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcbkVar);
        zzuj();
        zzwk();
        zzcax.zzawl();
        zzj(new zzcev(this, true, zzauf().zza(zzcbkVar), zzcbkVar, zzbr(true), str));
    }

    @WorkerThread
    protected final void zzf(zzcav zzcavVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzcavVar);
        zzuj();
        zzwk();
        zzcax.zzawl();
        zzj(new zzcew(this, true, zzauf().zzc(zzcavVar), new zzcav(zzcavVar), zzbr(true), zzcavVar));
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

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @WorkerThread
    final void zzxh() {
        boolean z;
        zzuj();
        zzwk();
        if (isConnected()) {
            return;
        }
        if (this.zzivz == null) {
            this.zzivz = zzaun().zzayp();
            if (this.zzivz == null) {
                zzaum().zzayk().log("State of service unknown");
                zzuj();
                zzwk();
                zzcax.zzawl();
                zzaum().zzayk().log("Checking service availability");
                switch (com.google.android.gms.common.zze.zzaex().isGooglePlayServicesAvailable(getContext())) {
                    case 0:
                        zzaum().zzayk().log("Service available");
                        z = true;
                        break;
                    case 1:
                        zzaum().zzayk().log("Service missing");
                        z = false;
                        break;
                    case 2:
                        zzaum().zzayj().log("Service container out of date");
                        z = true;
                        break;
                    case 3:
                        zzaum().zzayg().log("Service disabled");
                        z = false;
                        break;
                    case 9:
                        zzaum().zzayg().log("Service invalid");
                        z = false;
                        break;
                    case 18:
                        zzaum().zzayg().log("Service updating");
                        z = true;
                        break;
                    default:
                        z = false;
                        break;
                }
                this.zzivz = Boolean.valueOf(z);
                zzaun().zzbm(this.zzivz.booleanValue());
            }
        }
        if (this.zzivz.booleanValue()) {
            zzaum().zzayk().log("Using measurement service");
            this.zzivx.zzazt();
            return;
        }
        zzcax.zzawl();
        List<ResolveInfo> listQueryIntentServices = getContext().getPackageManager().queryIntentServices(new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        if (!(listQueryIntentServices != null && listQueryIntentServices.size() > 0)) {
            zzaum().zzaye().log("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            return;
        }
        zzaum().zzayk().log("Using local app measurement service");
        Intent intent = new Intent("com.google.android.gms.measurement.START");
        Context context = getContext();
        zzcax.zzawl();
        intent.setComponent(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
        this.zzivx.zzk(intent);
    }
}
