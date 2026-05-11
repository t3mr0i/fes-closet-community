package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class zzdbv {
    private final List<zzdbq> zzkee;
    private final List<zzdbq> zzkef;
    private final List<zzdbq> zzkeg;
    private final List<zzdbq> zzkeh;
    private final List<zzdbq> zzkfm;
    private final List<zzdbq> zzkfn;
    private final List<String> zzkfo;
    private final List<String> zzkfp;
    private final List<String> zzkfq;
    private final List<String> zzkfr;

    private zzdbv() {
        this.zzkee = new ArrayList();
        this.zzkef = new ArrayList();
        this.zzkeg = new ArrayList();
        this.zzkeh = new ArrayList();
        this.zzkfm = new ArrayList();
        this.zzkfn = new ArrayList();
        this.zzkfo = new ArrayList();
        this.zzkfp = new ArrayList();
        this.zzkfq = new ArrayList();
        this.zzkfr = new ArrayList();
    }

    public final zzdbu zzbid() {
        return new zzdbu(this.zzkee, this.zzkef, this.zzkeg, this.zzkeh, this.zzkfm, this.zzkfn, this.zzkfo, this.zzkfp, this.zzkfq, this.zzkfr);
    }

    public final zzdbv zzd(zzdbq zzdbqVar) {
        this.zzkee.add(zzdbqVar);
        return this;
    }

    public final zzdbv zze(zzdbq zzdbqVar) {
        this.zzkef.add(zzdbqVar);
        return this;
    }

    public final zzdbv zzf(zzdbq zzdbqVar) {
        this.zzkeg.add(zzdbqVar);
        return this;
    }

    public final zzdbv zzg(zzdbq zzdbqVar) {
        this.zzkeh.add(zzdbqVar);
        return this;
    }

    public final zzdbv zzh(zzdbq zzdbqVar) {
        this.zzkfm.add(zzdbqVar);
        return this;
    }

    public final zzdbv zzi(zzdbq zzdbqVar) {
        this.zzkfn.add(zzdbqVar);
        return this;
    }

    public final zzdbv zznk(String str) {
        this.zzkfq.add(str);
        return this;
    }

    public final zzdbv zznl(String str) {
        this.zzkfr.add(str);
        return this;
    }

    public final zzdbv zznm(String str) {
        this.zzkfo.add(str);
        return this;
    }

    public final zzdbv zznn(String str) {
        this.zzkfp.add(str);
        return this;
    }
}
