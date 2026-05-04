package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/* loaded from: classes.dex */
public final class zzana implements ServiceConnection {
    final /* synthetic */ zzamy zzdpd;
    private volatile zzaoj zzdpe;
    private volatile boolean zzdpf;

    protected zzana(zzamy zzamyVar) {
        this.zzdpd = zzamyVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0072 A[Catch: all -> 0x0064, TRY_ENTER, TryCatch #2 {all -> 0x0064, blocks: (B:5:0x0009, B:9:0x0015, B:13:0x0024, B:15:0x002d, B:36:0x0072, B:38:0x0076, B:39:0x008c, B:29:0x005c, B:22:0x0047, B:24:0x0051, B:25:0x0054, B:34:0x0069), top: B:47:0x0007, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x002d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.content.ServiceConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onServiceConnected(android.content.ComponentName r5, android.os.IBinder r6) {
        /*
            r4 = this;
            r1 = 0
            java.lang.String r0 = "AnalyticsServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.zzbp.zzfy(r0)
            monitor-enter(r4)
            if (r6 != 0) goto L15
            com.google.android.gms.internal.zzamy r0 = r4.zzdpd     // Catch: java.lang.Throwable -> L64
            java.lang.String r1 = "Service connected with null binder"
            r0.zzdq(r1)     // Catch: java.lang.Throwable -> L64
            r4.notifyAll()     // Catch: java.lang.Throwable -> L44
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L44
        L14:
            return
        L15:
            java.lang.String r0 = r6.getInterfaceDescriptor()     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            java.lang.String r2 = "com.google.android.gms.analytics.internal.IAnalyticsService"
            boolean r2 = r2.equals(r0)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            if (r2 == 0) goto L69
            if (r6 != 0) goto L47
            r0 = r1
        L24:
            com.google.android.gms.internal.zzamy r1 = r4.zzdpd     // Catch: java.lang.Throwable -> L64 android.os.RemoteException -> L91
            java.lang.String r2 = "Bound to IAnalyticsService interface"
            r1.zzdm(r2)     // Catch: java.lang.Throwable -> L64 android.os.RemoteException -> L91
        L2b:
            if (r0 != 0) goto L72
            com.google.android.gms.common.stats.zza.zzakz()     // Catch: java.lang.Throwable -> L64 java.lang.IllegalArgumentException -> L8f
            com.google.android.gms.internal.zzamy r0 = r4.zzdpd     // Catch: java.lang.Throwable -> L64 java.lang.IllegalArgumentException -> L8f
            android.content.Context r0 = r0.getContext()     // Catch: java.lang.Throwable -> L64 java.lang.IllegalArgumentException -> L8f
            com.google.android.gms.internal.zzamy r1 = r4.zzdpd     // Catch: java.lang.Throwable -> L64 java.lang.IllegalArgumentException -> L8f
            com.google.android.gms.internal.zzana r1 = com.google.android.gms.internal.zzamy.zza(r1)     // Catch: java.lang.Throwable -> L64 java.lang.IllegalArgumentException -> L8f
            r0.unbindService(r1)     // Catch: java.lang.Throwable -> L64 java.lang.IllegalArgumentException -> L8f
        L3f:
            r4.notifyAll()     // Catch: java.lang.Throwable -> L44
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L44
            goto L14
        L44:
            r0 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L44
            throw r0
        L47:
            java.lang.String r0 = "com.google.android.gms.analytics.internal.IAnalyticsService"
            android.os.IInterface r0 = r6.queryLocalInterface(r0)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            boolean r2 = r0 instanceof com.google.android.gms.internal.zzaoj     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            if (r2 == 0) goto L54
            com.google.android.gms.internal.zzaoj r0 = (com.google.android.gms.internal.zzaoj) r0     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            goto L24
        L54:
            com.google.android.gms.internal.zzaok r0 = new com.google.android.gms.internal.zzaok     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            r0.<init>(r6)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            goto L24
        L5a:
            r0 = move-exception
            r0 = r1
        L5c:
            com.google.android.gms.internal.zzamy r1 = r4.zzdpd     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = "Service connect failed to get IAnalyticsService"
            r1.zzdq(r2)     // Catch: java.lang.Throwable -> L64
            goto L2b
        L64:
            r0 = move-exception
            r4.notifyAll()     // Catch: java.lang.Throwable -> L44
            throw r0     // Catch: java.lang.Throwable -> L44
        L69:
            com.google.android.gms.internal.zzamy r2 = r4.zzdpd     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            java.lang.String r3 = "Got binder with a wrong descriptor"
            r2.zze(r3, r0)     // Catch: android.os.RemoteException -> L5a java.lang.Throwable -> L64
            r0 = r1
            goto L2b
        L72:
            boolean r1 = r4.zzdpf     // Catch: java.lang.Throwable -> L64
            if (r1 != 0) goto L8c
            com.google.android.gms.internal.zzamy r1 = r4.zzdpd     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = "onServiceConnected received after the timeout limit"
            r1.zzdp(r2)     // Catch: java.lang.Throwable -> L64
            com.google.android.gms.internal.zzamy r1 = r4.zzdpd     // Catch: java.lang.Throwable -> L64
            com.google.android.gms.analytics.zzj r1 = r1.zzwa()     // Catch: java.lang.Throwable -> L64
            com.google.android.gms.internal.zzanb r2 = new com.google.android.gms.internal.zzanb     // Catch: java.lang.Throwable -> L64
            r2.<init>(r4, r0)     // Catch: java.lang.Throwable -> L64
            r1.zzc(r2)     // Catch: java.lang.Throwable -> L64
            goto L3f
        L8c:
            r4.zzdpe = r0     // Catch: java.lang.Throwable -> L64
            goto L3f
        L8f:
            r0 = move-exception
            goto L3f
        L91:
            r1 = move-exception
            goto L5c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzana.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        com.google.android.gms.common.internal.zzbp.zzfy("AnalyticsServiceConnection.onServiceDisconnected");
        this.zzdpd.zzwa().zzc(new zzanc(this, componentName));
    }

    public final zzaoj zzwy() {
        zzaoj zzaojVar = null;
        com.google.android.gms.analytics.zzj.zzuj();
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        Context context = this.zzdpd.getContext();
        intent.putExtra("app_package_name", context.getPackageName());
        com.google.android.gms.common.stats.zza zzaVarZzakz = com.google.android.gms.common.stats.zza.zzakz();
        synchronized (this) {
            this.zzdpe = null;
            this.zzdpf = true;
            boolean zZza = zzaVarZzakz.zza(context, intent, this.zzdpd.zzdoz, 129);
            this.zzdpd.zza("Bind to service requested", Boolean.valueOf(zZza));
            if (zZza) {
                try {
                    wait(zzaod.zzdsj.get().longValue());
                } catch (InterruptedException e) {
                    this.zzdpd.zzdp("Wait for service connect was interrupted");
                }
                this.zzdpf = false;
                zzaojVar = this.zzdpe;
                this.zzdpe = null;
                if (zzaojVar == null) {
                    this.zzdpd.zzdq("Successfully bound to service but never got onServiceConnected callback");
                }
            } else {
                this.zzdpf = false;
            }
        }
        return zzaojVar;
    }
}
