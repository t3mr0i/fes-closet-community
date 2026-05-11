package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzdbm;
import com.google.android.gms.internal.zzdbn;
import com.google.android.gms.internal.zzdbs;
import com.google.android.gms.tagmanager.zzei;

/* loaded from: classes.dex */
public final class zzy extends com.google.android.gms.common.api.internal.zzs<ContainerHolder> {
    private final Context mContext;
    private final Looper zzakf;
    private final com.google.android.gms.common.util.zzd zzasb;
    private final String zzjoz;
    private long zzjpe;
    private final TagManager zzjpl;
    private final zzaf zzjpo;
    private final zzek zzjpp;
    private final int zzjpq;
    private final zzai zzjpr;
    private zzah zzjps;
    private zzdbn zzjpt;
    private volatile zzv zzjpu;
    private volatile boolean zzjpv;
    private com.google.android.gms.internal.zzbo zzjpw;
    private String zzjpx;
    private zzag zzjpy;
    private zzac zzjpz;

    private zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzah zzahVar, zzag zzagVar, zzdbn zzdbnVar, com.google.android.gms.common.util.zzd zzdVar, zzek zzekVar, zzai zzaiVar) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.zzjpl = tagManager;
        this.zzakf = looper == null ? Looper.getMainLooper() : looper;
        this.zzjoz = str;
        this.zzjpq = i;
        this.zzjps = zzahVar;
        this.zzjpy = zzagVar;
        this.zzjpt = zzdbnVar;
        this.zzjpo = new zzaf(this, null);
        this.zzjpw = new com.google.android.gms.internal.zzbo();
        this.zzasb = zzdVar;
        this.zzjpp = zzekVar;
        this.zzjpr = zzaiVar;
        if (zzbcw()) {
            zzlg(zzei.zzbei().zzbek());
        }
    }

    public zzy(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzalVar) {
        this(context, tagManager, looper, str, i, new zzey(context, str), new zzet(context, str, zzalVar), new zzdbn(context), com.google.android.gms.common.util.zzh.zzald(), new zzdh(1, 5, 900000L, 5000L, "refreshing", com.google.android.gms.common.util.zzh.zzald()), new zzai(context, str));
        this.zzjpt.zzni(zzalVar.zzbdd());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zza(com.google.android.gms.internal.zzbo zzboVar) {
        if (this.zzjps != null) {
            zzdbm zzdbmVar = new zzdbm();
            zzdbmVar.zzkfj = this.zzjpe;
            zzdbmVar.zzxw = new com.google.android.gms.internal.zzbl();
            zzdbmVar.zzkfk = zzboVar;
            this.zzjps.zza(zzdbmVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0047 A[Catch: all -> 0x0068, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0005, B:7:0x000b, B:11:0x0011, B:13:0x0047, B:14:0x0054, B:16:0x005a, B:18:0x0062, B:23:0x006b), top: B:25:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006b A[Catch: all -> 0x0068, TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0005, B:7:0x000b, B:11:0x0011, B:13:0x0047, B:14:0x0054, B:16:0x005a, B:18:0x0062, B:23:0x006b), top: B:25:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized void zza(com.google.android.gms.internal.zzbo r9, long r10, boolean r12) {
        /*
            r8 = this;
            monitor-enter(r8)
            if (r12 == 0) goto L5
            boolean r0 = r8.zzjpv     // Catch: java.lang.Throwable -> L68
        L5:
            boolean r0 = r8.isReady()     // Catch: java.lang.Throwable -> L68
            if (r0 == 0) goto L11
            com.google.android.gms.tagmanager.zzv r0 = r8.zzjpu     // Catch: java.lang.Throwable -> L68
            if (r0 != 0) goto L11
        Lf:
            monitor-exit(r8)
            return
        L11:
            r8.zzjpw = r9     // Catch: java.lang.Throwable -> L68
            r8.zzjpe = r10     // Catch: java.lang.Throwable -> L68
            com.google.android.gms.tagmanager.zzai r0 = r8.zzjpr     // Catch: java.lang.Throwable -> L68
            long r0 = r0.zzbcy()     // Catch: java.lang.Throwable -> L68
            r2 = 0
            long r4 = r8.zzjpe     // Catch: java.lang.Throwable -> L68
            long r4 = r4 + r0
            com.google.android.gms.common.util.zzd r6 = r8.zzasb     // Catch: java.lang.Throwable -> L68
            long r6 = r6.currentTimeMillis()     // Catch: java.lang.Throwable -> L68
            long r4 = r4 - r6
            long r0 = java.lang.Math.min(r0, r4)     // Catch: java.lang.Throwable -> L68
            long r0 = java.lang.Math.max(r2, r0)     // Catch: java.lang.Throwable -> L68
            r8.zzbf(r0)     // Catch: java.lang.Throwable -> L68
            com.google.android.gms.tagmanager.Container r0 = new com.google.android.gms.tagmanager.Container     // Catch: java.lang.Throwable -> L68
            android.content.Context r1 = r8.mContext     // Catch: java.lang.Throwable -> L68
            com.google.android.gms.tagmanager.TagManager r2 = r8.zzjpl     // Catch: java.lang.Throwable -> L68
            com.google.android.gms.tagmanager.DataLayer r2 = r2.getDataLayer()     // Catch: java.lang.Throwable -> L68
            java.lang.String r3 = r8.zzjoz     // Catch: java.lang.Throwable -> L68
            r4 = r10
            r6 = r9
            r0.<init>(r1, r2, r3, r4, r6)     // Catch: java.lang.Throwable -> L68
            com.google.android.gms.tagmanager.zzv r1 = r8.zzjpu     // Catch: java.lang.Throwable -> L68
            if (r1 != 0) goto L6b
            com.google.android.gms.tagmanager.zzv r1 = new com.google.android.gms.tagmanager.zzv     // Catch: java.lang.Throwable -> L68
            com.google.android.gms.tagmanager.TagManager r2 = r8.zzjpl     // Catch: java.lang.Throwable -> L68
            android.os.Looper r3 = r8.zzakf     // Catch: java.lang.Throwable -> L68
            com.google.android.gms.tagmanager.zzaf r4 = r8.zzjpo     // Catch: java.lang.Throwable -> L68
            r1.<init>(r2, r3, r0, r4)     // Catch: java.lang.Throwable -> L68
            r8.zzjpu = r1     // Catch: java.lang.Throwable -> L68
        L54:
            boolean r1 = r8.isReady()     // Catch: java.lang.Throwable -> L68
            if (r1 != 0) goto Lf
            com.google.android.gms.tagmanager.zzac r1 = r8.zzjpz     // Catch: java.lang.Throwable -> L68
            boolean r0 = r1.zzb(r0)     // Catch: java.lang.Throwable -> L68
            if (r0 == 0) goto Lf
            com.google.android.gms.tagmanager.zzv r0 = r8.zzjpu     // Catch: java.lang.Throwable -> L68
            r8.setResult(r0)     // Catch: java.lang.Throwable -> L68
            goto Lf
        L68:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        L6b:
            com.google.android.gms.tagmanager.zzv r1 = r8.zzjpu     // Catch: java.lang.Throwable -> L68
            r1.zza(r0)     // Catch: java.lang.Throwable -> L68
            goto L54
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzy.zza(com.google.android.gms.internal.zzbo, long, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzbcw() {
        zzei zzeiVarZzbei = zzei.zzbei();
        return (zzeiVarZzbei.zzbej() == zzei.zza.CONTAINER || zzeiVarZzbei.zzbej() == zzei.zza.CONTAINER_DEBUG) && this.zzjoz.equals(zzeiVarZzbei.getContainerId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzbf(long j) {
        if (this.zzjpy == null) {
            zzdj.zzcr("Refresh requested, but no network load scheduler.");
        } else {
            this.zzjpy.zza(j, this.zzjpw.zzxx);
        }
    }

    private final void zzbt(boolean z) {
        zzz zzzVar = null;
        this.zzjps.zza(new zzad(this, zzzVar));
        this.zzjpy.zza(new zzae(this, zzzVar));
        zzdbs zzdbsVarZzee = this.zzjps.zzee(this.zzjpq);
        if (zzdbsVarZzee != null) {
            this.zzjpu = new zzv(this.zzjpl, this.zzakf, new Container(this.mContext, this.zzjpl.getDataLayer(), this.zzjoz, 0L, zzdbsVarZzee), this.zzjpo);
        }
        this.zzjpz = new zzab(this, z);
        if (zzbcw()) {
            this.zzjpy.zza(0L, "");
        } else {
            this.zzjps.zzbcx();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.zzs
    /* renamed from: zzai, reason: merged with bridge method [inline-methods] */
    public final ContainerHolder zzb(Status status) {
        if (this.zzjpu != null) {
            return this.zzjpu;
        }
        if (status == Status.zzfhx) {
            zzdj.e("timer expired: setting result to failure");
        }
        return new zzv(status);
    }

    final synchronized String zzbcq() {
        return this.zzjpx;
    }

    public final void zzbct() {
        zzdbs zzdbsVarZzee = this.zzjps.zzee(this.zzjpq);
        if (zzdbsVarZzee != null) {
            setResult(new zzv(this.zzjpl, this.zzakf, new Container(this.mContext, this.zzjpl.getDataLayer(), this.zzjoz, 0L, zzdbsVarZzee), new zzaa(this)));
        } else {
            zzdj.e("Default was requested, but no default container was found");
            setResult(zzb(new Status(10, "Default was requested, but no default container was found", null)));
        }
        this.zzjpy = null;
        this.zzjps = null;
    }

    public final void zzbcu() {
        zzbt(false);
    }

    public final void zzbcv() {
        zzbt(true);
    }

    final synchronized void zzlg(String str) {
        this.zzjpx = str;
        if (this.zzjpy != null) {
            this.zzjpy.zzlh(str);
        }
    }
}
