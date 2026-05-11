package com.google.android.gms.internal;

import android.content.ComponentName;
import android.os.RemoteException;
import java.util.Collections;

/* loaded from: classes.dex */
public final class zzamy extends zzams {
    private final zzana zzdoz;
    private zzaoj zzdpa;
    private final zzanx zzdpb;
    private final zzaoz zzdpc;

    protected zzamy(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzdpc = new zzaoz(zzamuVar.zzvx());
        this.zzdoz = new zzana(this);
        this.zzdpb = new zzamz(this, zzamuVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onServiceDisconnected(ComponentName componentName) {
        com.google.android.gms.analytics.zzj.zzuj();
        if (this.zzdpa != null) {
            this.zzdpa = null;
            zza("Disconnected from device AnalyticsService", componentName);
            zzwc().zzvu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzaoj zzaojVar) {
        com.google.android.gms.analytics.zzj.zzuj();
        this.zzdpa = zzaojVar;
        zzww();
        zzwc().onServiceConnected();
    }

    private final void zzww() {
        this.zzdpc.start();
        this.zzdpb.zzs(zzaod.zzdsi.get().longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzwx() {
        com.google.android.gms.analytics.zzj.zzuj();
        if (isConnected()) {
            zzdm("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public final boolean connect() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (this.zzdpa != null) {
            return true;
        }
        zzaoj zzaojVarZzwy = this.zzdoz.zzwy();
        if (zzaojVarZzwy == null) {
            return false;
        }
        this.zzdpa = zzaojVarZzwy;
        zzww();
        return true;
    }

    public final void disconnect() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        try {
            com.google.android.gms.common.stats.zza.zzakz();
            getContext().unbindService(this.zzdoz);
        } catch (IllegalArgumentException e) {
        } catch (IllegalStateException e2) {
        }
        if (this.zzdpa != null) {
            this.zzdpa = null;
            zzwc().zzvu();
        }
    }

    public final boolean isConnected() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        return this.zzdpa != null;
    }

    public final boolean zzb(zzaoi zzaoiVar) {
        com.google.android.gms.common.internal.zzbp.zzu(zzaoiVar);
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        zzaoj zzaojVar = this.zzdpa;
        if (zzaojVar == null) {
            return false;
        }
        try {
            zzaojVar.zza(zzaoiVar.zziy(), zzaoiVar.zzyn(), zzaoiVar.zzyp() ? zzanv.zzyb() : zzanv.zzyc(), Collections.emptyList());
            zzww();
            return true;
        } catch (RemoteException e) {
            zzdm("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
    }

    public final boolean zzwv() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        zzaoj zzaojVar = this.zzdpa;
        if (zzaojVar == null) {
            return false;
        }
        try {
            zzaojVar.zzvr();
            zzww();
            return true;
        } catch (RemoteException e) {
            zzdm("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
}
