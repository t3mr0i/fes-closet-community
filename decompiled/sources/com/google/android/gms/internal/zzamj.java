package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class zzamj extends zzams {
    private final zzang zzdnu;

    public zzamj(zzamu zzamuVar, zzamw zzamwVar) {
        super(zzamuVar);
        com.google.android.gms.common.internal.zzbp.zzu(zzamwVar);
        this.zzdnu = new zzang(zzamuVar, zzamwVar);
    }

    final void onServiceConnected() {
        com.google.android.gms.analytics.zzj.zzuj();
        this.zzdnu.onServiceConnected();
    }

    public final void setLocalDispatchPeriod(int i) {
        zzwk();
        zzb("setLocalDispatchPeriod (sec)", Integer.valueOf(i));
        zzwa().zzc(new zzamk(this, i));
    }

    public final void start() {
        this.zzdnu.start();
    }

    public final long zza(zzamx zzamxVar) {
        zzwk();
        com.google.android.gms.common.internal.zzbp.zzu(zzamxVar);
        com.google.android.gms.analytics.zzj.zzuj();
        long jZza = this.zzdnu.zza(zzamxVar, true);
        if (jZza == 0) {
            this.zzdnu.zzb(zzamxVar);
        }
        return jZza;
    }

    public final void zza(zzaob zzaobVar) {
        zzwk();
        zzwa().zzc(new zzamp(this, zzaobVar));
    }

    public final void zza(zzaoi zzaoiVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzaoiVar);
        zzwk();
        zzb("Hit delivery requested", zzaoiVar);
        zzwa().zzc(new zzamn(this, zzaoiVar));
    }

    public final void zza(String str, Runnable runnable) {
        com.google.android.gms.common.internal.zzbp.zzh(str, "campaign param can't be empty");
        zzwa().zzc(new zzamm(this, str, runnable));
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
        this.zzdnu.initialize();
    }

    public final void zzvr() {
        zzwk();
        zzwa().zzc(new zzamo(this));
    }

    public final void zzvs() {
        zzwk();
        Context context = getContext();
        if (!zzaou.zzbe(context) || !zzaov.zzbi(context)) {
            zza((zzaob) null);
            return;
        }
        Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        intent.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
        context.startService(intent);
    }

    public final boolean zzvt() throws ExecutionException, InterruptedException, TimeoutException {
        zzwk();
        try {
            zzwa().zzc(new zzamq(this)).get(4L, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            zzd("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zze("syncDispatchLocalHits failed", e2);
            return false;
        } catch (TimeoutException e3) {
            zzd("syncDispatchLocalHits timed out", e3);
            return false;
        }
    }

    public final void zzvu() {
        zzwk();
        com.google.android.gms.analytics.zzj.zzuj();
        zzang zzangVar = this.zzdnu;
        com.google.android.gms.analytics.zzj.zzuj();
        zzangVar.zzwk();
        zzangVar.zzdm("Service disconnected");
    }

    final void zzvv() {
        com.google.android.gms.analytics.zzj.zzuj();
        this.zzdnu.zzvv();
    }
}
