package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: classes.dex */
public final class zzaor extends zzams {
    private SharedPreferences zzdtp;
    private long zzdtq;
    private long zzdtr;
    private final zzaot zzdts;

    protected zzaor(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzdtr = -1L;
        this.zzdts = new zzaot(this, "monitoring", zzaod.zzdsn.get().longValue());
    }

    public final void zzdx(String str) {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        SharedPreferences.Editor editorEdit = this.zzdtp.edit();
        if (TextUtils.isEmpty(str)) {
            editorEdit.remove("installation_campaign");
        } else {
            editorEdit.putString("installation_campaign", str);
        }
        if (editorEdit.commit()) {
            return;
        }
        zzdp("Failed to commit campaign data");
    }

    @Override // com.google.android.gms.internal.zzams
    protected final void zzuk() {
        this.zzdtp = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }

    public final long zzzb() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (this.zzdtq == 0) {
            long j = this.zzdtp.getLong("first_run", 0L);
            if (j != 0) {
                this.zzdtq = j;
            } else {
                long jCurrentTimeMillis = zzvx().currentTimeMillis();
                SharedPreferences.Editor editorEdit = this.zzdtp.edit();
                editorEdit.putLong("first_run", jCurrentTimeMillis);
                if (!editorEdit.commit()) {
                    zzdp("Failed to commit first run time");
                }
                this.zzdtq = jCurrentTimeMillis;
            }
        }
        return this.zzdtq;
    }

    public final zzaoz zzzc() {
        return new zzaoz(zzvx(), zzzb());
    }

    public final long zzzd() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        if (this.zzdtr == -1) {
            this.zzdtr = this.zzdtp.getLong("last_dispatch", 0L);
        }
        return this.zzdtr;
    }

    public final void zzze() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        long jCurrentTimeMillis = zzvx().currentTimeMillis();
        SharedPreferences.Editor editorEdit = this.zzdtp.edit();
        editorEdit.putLong("last_dispatch", jCurrentTimeMillis);
        editorEdit.apply();
        this.zzdtr = jCurrentTimeMillis;
    }

    public final String zzzf() {
        com.google.android.gms.analytics.zzj.zzuj();
        zzwk();
        String string = this.zzdtp.getString("installation_campaign", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public final zzaot zzzg() {
        return this.zzdts;
    }
}
