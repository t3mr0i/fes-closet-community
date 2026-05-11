package com.google.android.gms.tagmanager;

/* loaded from: classes.dex */
final class zzab implements zzac {
    private /* synthetic */ zzy zzjqa;
    private Long zzjqb;
    private /* synthetic */ boolean zzjqc;

    zzab(zzy zzyVar, boolean z) {
        this.zzjqa = zzyVar;
        this.zzjqc = z;
    }

    @Override // com.google.android.gms.tagmanager.zzac
    public final boolean zzb(Container container) {
        if (!this.zzjqc) {
            return !container.isDefault();
        }
        long lastRefreshTime = container.getLastRefreshTime();
        if (this.zzjqb == null) {
            this.zzjqb = Long.valueOf(this.zzjqa.zzjpr.zzbcy());
        }
        return lastRefreshTime + this.zzjqb.longValue() >= this.zzjqa.zzasb.currentTimeMillis();
    }
}
