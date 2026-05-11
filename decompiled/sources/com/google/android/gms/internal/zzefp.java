package com.google.android.gms.internal;

/* loaded from: classes.dex */
public class zzefp {
    private static final zzeer zznbb = zzeer.zzccr();
    private zzeec zzndd;
    private volatile zzefq zznde;
    private volatile zzeec zzndf;

    private zzeec zzcbp() {
        if (this.zzndf != null) {
            return this.zzndf;
        }
        synchronized (this) {
            if (this.zzndf != null) {
                return this.zzndf;
            }
            if (this.zznde == null) {
                this.zzndf = zzeec.zznbd;
            } else {
                this.zzndf = this.zznde.zzcbp();
            }
            return this.zzndf;
        }
    }

    private zzefq zzf(zzefq zzefqVar) {
        if (this.zznde == null) {
            synchronized (this) {
                if (this.zznde == null) {
                    try {
                        this.zznde = zzefqVar;
                        this.zzndf = zzeec.zznbd;
                    } catch (zzefj e) {
                        this.zznde = zzefqVar;
                        this.zzndf = zzeec.zznbd;
                    }
                }
            }
        }
        return this.zznde;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzefp)) {
            return false;
        }
        zzefp zzefpVar = (zzefp) obj;
        zzefq zzefqVar = this.zznde;
        zzefq zzefqVar2 = zzefpVar.zznde;
        return (zzefqVar == null && zzefqVar2 == null) ? zzcbp().equals(zzefpVar.zzcbp()) : (zzefqVar == null || zzefqVar2 == null) ? zzefqVar != null ? zzefqVar.equals(zzefpVar.zzf(zzefqVar.zzccx())) : zzf(zzefqVar2.zzccx()).equals(zzefqVar2) : zzefqVar.equals(zzefqVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzefq zzg(zzefq zzefqVar) {
        zzefq zzefqVar2 = this.zznde;
        this.zzndd = null;
        this.zzndf = null;
        this.zznde = zzefqVar;
        return zzefqVar2;
    }
}
