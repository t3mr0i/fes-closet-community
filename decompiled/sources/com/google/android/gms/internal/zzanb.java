package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzanb implements Runnable {
    private /* synthetic */ zzaoj zzdpg;
    private /* synthetic */ zzana zzdph;

    zzanb(zzana zzanaVar, zzaoj zzaojVar) {
        this.zzdph = zzanaVar;
        this.zzdpg = zzaojVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzdph.zzdpd.isConnected()) {
            return;
        }
        this.zzdph.zzdpd.zzdn("Connected to service after a timeout");
        this.zzdph.zzdpd.zza(this.zzdpg);
    }
}
