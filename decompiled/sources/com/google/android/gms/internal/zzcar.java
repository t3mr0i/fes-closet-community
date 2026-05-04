package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import android.text.TextUtils;

/* loaded from: classes.dex */
final class zzcar {
    private final String mAppId;
    private String zzcyd;
    private String zzdmb;
    private String zzgam;
    private final zzccw zzikh;
    private String zziky;
    private String zzikz;
    private long zzila;
    private long zzilb;
    private long zzilc;
    private long zzild;
    private String zzile;
    private long zzilf;
    private long zzilg;
    private boolean zzilh;
    private long zzili;
    private long zzilj;
    private long zzilk;
    private long zzill;
    private long zzilm;
    private long zziln;
    private long zzilo;
    private String zzilp;
    private boolean zzilq;
    private long zzilr;
    private long zzils;

    @WorkerThread
    zzcar(zzccw zzccwVar, String str) {
        com.google.android.gms.common.internal.zzbp.zzu(zzccwVar);
        com.google.android.gms.common.internal.zzbp.zzgg(str);
        this.zzikh = zzccwVar;
        this.mAppId = str;
        this.zzikh.zzaul().zzuj();
    }

    @WorkerThread
    public final String getAppId() {
        this.zzikh.zzaul().zzuj();
        return this.mAppId;
    }

    @WorkerThread
    public final String getAppInstanceId() {
        this.zzikh.zzaul().zzuj();
        return this.zzgam;
    }

    @WorkerThread
    public final String getGmpAppId() {
        this.zzikh.zzaul().zzuj();
        return this.zzcyd;
    }

    @WorkerThread
    public final void setAppVersion(String str) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (!zzcfw.zzas(this.zzdmb, str)) | this.zzilq;
        this.zzdmb = str;
    }

    @WorkerThread
    public final void setMeasurementEnabled(boolean z) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilh != z) | this.zzilq;
        this.zzilh = z;
    }

    @WorkerThread
    public final void zzal(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilb != j) | this.zzilq;
        this.zzilb = j;
    }

    @WorkerThread
    public final void zzam(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilc != j) | this.zzilq;
        this.zzilc = j;
    }

    @WorkerThread
    public final void zzan(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzild != j) | this.zzilq;
        this.zzild = j;
    }

    @WorkerThread
    public final void zzao(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilf != j) | this.zzilq;
        this.zzilf = j;
    }

    @WorkerThread
    public final void zzap(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilg != j) | this.zzilq;
        this.zzilg = j;
    }

    @WorkerThread
    public final void zzaq(long j) {
        com.google.android.gms.common.internal.zzbp.zzbh(j >= 0);
        this.zzikh.zzaul().zzuj();
        this.zzilq |= this.zzila != j;
        this.zzila = j;
    }

    @WorkerThread
    public final void zzar(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilr != j) | this.zzilq;
        this.zzilr = j;
    }

    @WorkerThread
    public final void zzas(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzils != j) | this.zzilq;
        this.zzils = j;
    }

    @WorkerThread
    public final void zzat(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilj != j) | this.zzilq;
        this.zzilj = j;
    }

    @WorkerThread
    public final void zzau(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilk != j) | this.zzilq;
        this.zzilk = j;
    }

    @WorkerThread
    public final void zzaup() {
        this.zzikh.zzaul().zzuj();
        this.zzilq = false;
    }

    @WorkerThread
    public final String zzauq() {
        this.zzikh.zzaul().zzuj();
        return this.zziky;
    }

    @WorkerThread
    public final String zzaur() {
        this.zzikh.zzaul().zzuj();
        return this.zzikz;
    }

    @WorkerThread
    public final long zzaus() {
        this.zzikh.zzaul().zzuj();
        return this.zzilb;
    }

    @WorkerThread
    public final long zzaut() {
        this.zzikh.zzaul().zzuj();
        return this.zzilc;
    }

    @WorkerThread
    public final long zzauu() {
        this.zzikh.zzaul().zzuj();
        return this.zzild;
    }

    @WorkerThread
    public final String zzauv() {
        this.zzikh.zzaul().zzuj();
        return this.zzile;
    }

    @WorkerThread
    public final long zzauw() {
        this.zzikh.zzaul().zzuj();
        return this.zzilf;
    }

    @WorkerThread
    public final long zzaux() {
        this.zzikh.zzaul().zzuj();
        return this.zzilg;
    }

    @WorkerThread
    public final boolean zzauy() {
        this.zzikh.zzaul().zzuj();
        return this.zzilh;
    }

    @WorkerThread
    public final long zzauz() {
        this.zzikh.zzaul().zzuj();
        return this.zzila;
    }

    @WorkerThread
    public final void zzav(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzill != j) | this.zzilq;
        this.zzill = j;
    }

    @WorkerThread
    public final long zzava() {
        this.zzikh.zzaul().zzuj();
        return this.zzilr;
    }

    @WorkerThread
    public final long zzavb() {
        this.zzikh.zzaul().zzuj();
        return this.zzils;
    }

    @WorkerThread
    public final void zzavc() {
        this.zzikh.zzaul().zzuj();
        long j = this.zzila + 1;
        if (j > 2147483647L) {
            this.zzikh.zzaum().zzayg().zzj("Bundle index overflow. appId", zzcbw.zzjf(this.mAppId));
            j = 0;
        }
        this.zzilq = true;
        this.zzila = j;
    }

    @WorkerThread
    public final long zzavd() {
        this.zzikh.zzaul().zzuj();
        return this.zzilj;
    }

    @WorkerThread
    public final long zzave() {
        this.zzikh.zzaul().zzuj();
        return this.zzilk;
    }

    @WorkerThread
    public final long zzavf() {
        this.zzikh.zzaul().zzuj();
        return this.zzill;
    }

    @WorkerThread
    public final long zzavg() {
        this.zzikh.zzaul().zzuj();
        return this.zzilm;
    }

    @WorkerThread
    public final long zzavh() {
        this.zzikh.zzaul().zzuj();
        return this.zzilo;
    }

    @WorkerThread
    public final long zzavi() {
        this.zzikh.zzaul().zzuj();
        return this.zziln;
    }

    @WorkerThread
    public final String zzavj() {
        this.zzikh.zzaul().zzuj();
        return this.zzilp;
    }

    @WorkerThread
    public final String zzavk() {
        this.zzikh.zzaul().zzuj();
        String str = this.zzilp;
        zzir(null);
        return str;
    }

    @WorkerThread
    public final long zzavl() {
        this.zzikh.zzaul().zzuj();
        return this.zzili;
    }

    @WorkerThread
    public final void zzaw(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilm != j) | this.zzilq;
        this.zzilm = j;
    }

    @WorkerThread
    public final void zzax(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzilo != j) | this.zzilq;
        this.zzilo = j;
    }

    @WorkerThread
    public final void zzay(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zziln != j) | this.zzilq;
        this.zziln = j;
    }

    @WorkerThread
    public final void zzaz(long j) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (this.zzili != j) | this.zzilq;
        this.zzili = j;
    }

    @WorkerThread
    public final void zzim(String str) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (!zzcfw.zzas(this.zzgam, str)) | this.zzilq;
        this.zzgam = str;
    }

    @WorkerThread
    public final void zzin(String str) {
        this.zzikh.zzaul().zzuj();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzilq = (!zzcfw.zzas(this.zzcyd, str)) | this.zzilq;
        this.zzcyd = str;
    }

    @WorkerThread
    public final void zzio(String str) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (!zzcfw.zzas(this.zziky, str)) | this.zzilq;
        this.zziky = str;
    }

    @WorkerThread
    public final void zzip(String str) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (!zzcfw.zzas(this.zzikz, str)) | this.zzilq;
        this.zzikz = str;
    }

    @WorkerThread
    public final void zziq(String str) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (!zzcfw.zzas(this.zzile, str)) | this.zzilq;
        this.zzile = str;
    }

    @WorkerThread
    public final void zzir(String str) {
        this.zzikh.zzaul().zzuj();
        this.zzilq = (!zzcfw.zzas(this.zzilp, str)) | this.zzilq;
        this.zzilp = str;
    }

    @WorkerThread
    public final String zzuo() {
        this.zzikh.zzaul().zzuj();
        return this.zzdmb;
    }
}
