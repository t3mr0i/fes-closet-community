package com.google.android.gms.internal;

/* loaded from: classes.dex */
public final class zzaol {
    private final com.google.android.gms.common.util.zzd zzasb;
    private final String zzdmq;
    private final long zzdtb;
    private final int zzdtc;
    private double zzdtd;
    private long zzdte;
    private final Object zzdtf;

    private zzaol(int i, long j, String str, com.google.android.gms.common.util.zzd zzdVar) {
        this.zzdtf = new Object();
        this.zzdtc = 60;
        this.zzdtd = this.zzdtc;
        this.zzdtb = 2000L;
        this.zzdmq = str;
        this.zzasb = zzdVar;
    }

    public zzaol(String str, com.google.android.gms.common.util.zzd zzdVar) {
        this(60, 2000L, str, zzdVar);
    }

    public final boolean zzys() {
        boolean z;
        synchronized (this.zzdtf) {
            long jCurrentTimeMillis = this.zzasb.currentTimeMillis();
            if (this.zzdtd < this.zzdtc) {
                double d = (jCurrentTimeMillis - this.zzdte) / this.zzdtb;
                if (d > 0.0d) {
                    this.zzdtd = Math.min(this.zzdtc, d + this.zzdtd);
                }
            }
            this.zzdte = jCurrentTimeMillis;
            if (this.zzdtd >= 1.0d) {
                this.zzdtd -= 1.0d;
                z = true;
            } else {
                String str = this.zzdmq;
                zzaom.zzcr(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
                z = false;
            }
        }
        return z;
    }
}
