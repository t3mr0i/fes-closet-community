package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzdh implements zzek {
    private final com.google.android.gms.common.util.zzd zzasb;
    private final String zzdmq;
    private long zzdte;
    private final Object zzdtf = new Object();
    private final int zzdtc = 5;
    private double zzdtd = Math.min(1, 5);
    private final long zzdtb = 900000;
    private final long zzjsr = 5000;

    public zzdh(int i, int i2, long j, long j2, String str, com.google.android.gms.common.util.zzd zzdVar) {
        this.zzdmq = str;
        this.zzasb = zzdVar;
    }

    @Override // com.google.android.gms.tagmanager.zzek
    public final boolean zzys() {
        boolean z = false;
        synchronized (this.zzdtf) {
            long jCurrentTimeMillis = this.zzasb.currentTimeMillis();
            if (jCurrentTimeMillis - this.zzdte < this.zzjsr) {
                String str = this.zzdmq;
                zzdj.zzcr(new StringBuilder(String.valueOf(str).length() + 34).append("Excessive ").append(str).append(" detected; call ignored.").toString());
            } else {
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
                    String str2 = this.zzdmq;
                    zzdj.zzcr(new StringBuilder(String.valueOf(str2).length() + 34).append("Excessive ").append(str2).append(" detected; call ignored.").toString());
                }
            }
        }
        return z;
    }
}
