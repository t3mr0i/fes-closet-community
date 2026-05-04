package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzapb extends zzamr implements zzanu<zzapc> {
    private final zzapc zzdue;

    public zzapb(zzamu zzamuVar) {
        super(zzamuVar);
        this.zzdue = new zzapc();
    }

    @Override // com.google.android.gms.internal.zzanu
    public final void zzc(String str, boolean z) {
        if ("ga_autoActivityTracking".equals(str)) {
            this.zzdue.zzduh = z ? 1 : 0;
            return;
        }
        if ("ga_anonymizeIp".equals(str)) {
            this.zzdue.zzdui = z ? 1 : 0;
        } else if (!"ga_reportUncaughtExceptions".equals(str)) {
            zzd("bool configuration name not recognized", str);
        } else {
            this.zzdue.zzduj = z ? 1 : 0;
        }
    }

    @Override // com.google.android.gms.internal.zzanu
    public final void zzd(String str, int i) {
        if ("ga_sessionTimeout".equals(str)) {
            this.zzdue.zzdug = i;
        } else {
            zzd("int configuration name not recognized", str);
        }
    }

    @Override // com.google.android.gms.internal.zzanu
    public final void zzj(String str, String str2) {
        this.zzdue.zzduk.put(str, str2);
    }

    @Override // com.google.android.gms.internal.zzanu
    public final void zzk(String str, String str2) {
        if ("ga_trackingId".equals(str)) {
            this.zzdue.zzdjn = str2;
            return;
        }
        if (!"ga_sampleFrequency".equals(str)) {
            zzd("string configuration name not recognized", str);
            return;
        }
        try {
            this.zzdue.zzduf = Double.parseDouble(str2);
        } catch (NumberFormatException e) {
            zzc("Error parsing ga_sampleFrequency value", str2, e);
        }
    }

    @Override // com.google.android.gms.internal.zzanu
    public final /* synthetic */ zzans zzxt() {
        return this.zzdue;
    }
}
