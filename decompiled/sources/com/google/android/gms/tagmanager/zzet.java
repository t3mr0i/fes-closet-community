package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class zzet implements zzag {
    private boolean mClosed;
    private final Context mContext;
    private final String zzjoz;
    private String zzjpx;
    private zzdi<com.google.android.gms.internal.zzbo> zzjty;
    private zzal zzjtz;
    private final ScheduledExecutorService zzjub;
    private final zzew zzjuc;
    private ScheduledFuture<?> zzjud;

    public zzet(Context context, String str, zzal zzalVar) {
        this(context, str, zzalVar, null, null);
    }

    private zzet(Context context, String str, zzal zzalVar, zzex zzexVar, zzew zzewVar) {
        this.zzjtz = zzalVar;
        this.mContext = context;
        this.zzjoz = str;
        this.zzjub = new zzeu(this).zzbem();
        this.zzjuc = new zzev(this);
    }

    private final synchronized void zzbel() {
        if (this.mClosed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        zzbel();
        if (this.zzjud != null) {
            this.zzjud.cancel(false);
        }
        this.zzjub.shutdown();
        this.mClosed = true;
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zza(long j, String str) {
        String str2 = this.zzjoz;
        zzdj.v(new StringBuilder(String.valueOf(str2).length() + 55).append("loadAfterDelay: containerId=").append(str2).append(" delay=").append(j).toString());
        zzbel();
        if (this.zzjty == null) {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
        if (this.zzjud != null) {
            this.zzjud.cancel(false);
        }
        ScheduledExecutorService scheduledExecutorService = this.zzjub;
        zzes zzesVarZza = this.zzjuc.zza(this.zzjtz);
        zzesVarZza.zza(this.zzjty);
        zzesVarZza.zzlh(this.zzjpx);
        zzesVarZza.zzlx(str);
        this.zzjud = scheduledExecutorService.schedule(zzesVarZza, j, TimeUnit.MILLISECONDS);
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zza(zzdi<com.google.android.gms.internal.zzbo> zzdiVar) {
        zzbel();
        this.zzjty = zzdiVar;
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zzlh(String str) {
        zzbel();
        this.zzjpx = str;
    }
}
