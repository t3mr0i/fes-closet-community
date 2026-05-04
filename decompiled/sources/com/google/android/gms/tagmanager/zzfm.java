package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzfm implements zzek {
    private final com.google.android.gms.common.util.zzd zzasb;
    private final long zzdtb;
    private final int zzdtc;
    private double zzdtd;
    private final Object zzdtf;
    private long zzjve;

    public zzfm() {
        this(60, 2000L);
    }

    private zzfm(int i, long j) {
        this.zzdtf = new Object();
        this.zzdtc = 60;
        this.zzdtd = this.zzdtc;
        this.zzdtb = 2000L;
        this.zzasb = com.google.android.gms.common.util.zzh.zzald();
    }

    @Override // com.google.android.gms.tagmanager.zzek
    public final boolean zzys() {
        boolean z;
        synchronized (this.zzdtf) {
            long jCurrentTimeMillis = this.zzasb.currentTimeMillis();
            if (this.zzdtd < this.zzdtc) {
                double d = (jCurrentTimeMillis - this.zzjve) / this.zzdtb;
                if (d > 0.0d) {
                    this.zzdtd = Math.min(this.zzdtc, d + this.zzdtd);
                }
            }
            this.zzjve = jCurrentTimeMillis;
            if (this.zzdtd >= 1.0d) {
                this.zzdtd -= 1.0d;
                z = true;
            } else {
                zzdj.zzcr("No more tokens available.");
                z = false;
            }
        }
        return z;
    }
}
