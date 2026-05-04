package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;

/* loaded from: classes.dex */
final class zzfo extends zzfn {
    private static final Object zzjvf = new Object();
    private static zzfo zzjvr;
    private Context zzjvg;
    private zzcc zzjvh;
    private volatile zzbz zzjvi;
    private zzfr zzjvo;
    private zzdo zzjvp;
    private int zzjvj = 1800000;
    private boolean zzjvk = true;
    private boolean zzjvl = false;
    private boolean connected = true;
    private boolean zzjvm = true;
    private zzcd zzjvn = new zzfp(this);
    private boolean zzjvq = false;

    private zzfo() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPowerSaveMode() {
        return this.zzjvq || !this.connected || this.zzjvj <= 0;
    }

    public static zzfo zzbfa() {
        if (zzjvr == null) {
            zzjvr = new zzfo();
        }
        return zzjvr;
    }

    @Override // com.google.android.gms.tagmanager.zzfn
    public final synchronized void dispatch() {
        if (this.zzjvl) {
            this.zzjvi.zzk(new zzfq(this));
        } else {
            zzdj.v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzjvk = true;
        }
    }

    final synchronized void zza(Context context, zzbz zzbzVar) {
        if (this.zzjvg == null) {
            this.zzjvg = context.getApplicationContext();
            if (this.zzjvi == null) {
                this.zzjvi = zzbzVar;
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.zzfn
    public final synchronized void zzbez() {
        if (!isPowerSaveMode()) {
            this.zzjvo.zzbfd();
        }
    }

    final synchronized zzcc zzbfb() {
        if (this.zzjvh == null) {
            if (this.zzjvg == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.zzjvh = new zzec(this.zzjvn, this.zzjvg);
        }
        if (this.zzjvo == null) {
            this.zzjvo = new zzfs(this, null);
            if (this.zzjvj > 0) {
                this.zzjvo.zzs(this.zzjvj);
            }
        }
        this.zzjvl = true;
        if (this.zzjvk) {
            dispatch();
            this.zzjvk = false;
        }
        if (this.zzjvp == null && this.zzjvm) {
            this.zzjvp = new zzdo(this);
            zzdo zzdoVar = this.zzjvp;
            Context context = this.zzjvg;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(zzdoVar, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter2.addCategory(context.getPackageName());
            context.registerReceiver(zzdoVar, intentFilter2);
        }
        return this.zzjvh;
    }

    @Override // com.google.android.gms.tagmanager.zzfn
    public final synchronized void zzbv(boolean z) {
        zzd(this.zzjvq, z);
    }

    final synchronized void zzd(boolean z, boolean z2) {
        boolean zIsPowerSaveMode = isPowerSaveMode();
        this.zzjvq = z;
        this.connected = z2;
        if (isPowerSaveMode() != zIsPowerSaveMode) {
            if (isPowerSaveMode()) {
                this.zzjvo.cancel();
                zzdj.v("PowerSaveMode initiated.");
            } else {
                this.zzjvo.zzs(this.zzjvj);
                zzdj.v("PowerSaveMode terminated.");
            }
        }
    }
}
