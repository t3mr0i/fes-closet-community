package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzefb implements zzefe {
    int hashCode = 0;

    zzefb() {
    }

    @Override // com.google.android.gms.internal.zzefe
    public final int zza(boolean z, int i, boolean z2, int i2) {
        this.hashCode = (this.hashCode * 53) + i;
        return i;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final zzeec zza(boolean z, zzeec zzeecVar, boolean z2, zzeec zzeecVar2) {
        this.hashCode = (this.hashCode * 53) + zzeecVar.hashCode();
        return zzeecVar;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final <T> zzefi<T> zza(zzefi<T> zzefiVar, zzefi<T> zzefiVar2) {
        this.hashCode = (this.hashCode * 53) + zzefiVar.hashCode();
        return zzefiVar;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final <T extends zzefq> T zza(T t, T t2) {
        int iHashCode;
        if (t == null) {
            iHashCode = 37;
        } else if (t instanceof zzeev) {
            zzeev zzeevVar = (zzeev) t;
            if (zzeevVar.zznaz == 0) {
                int i = this.hashCode;
                this.hashCode = 0;
                zzeevVar.zza(zzefd.zznco, this, zzeevVar);
                zzeevVar.zznce = zza(zzeevVar.zznce, zzeevVar.zznce);
                zzeevVar.zznaz = this.hashCode;
                this.hashCode = i;
            }
            iHashCode = zzeevVar.zznaz;
        } else {
            iHashCode = t.hashCode();
        }
        this.hashCode = iHashCode + (this.hashCode * 53);
        return t;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final zzegi zza(zzegi zzegiVar, zzegi zzegiVar2) {
        this.hashCode = (this.hashCode * 53) + zzegiVar.hashCode();
        return zzegiVar;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final String zza(boolean z, String str, boolean z2, String str2) {
        this.hashCode = (this.hashCode * 53) + str.hashCode();
        return str;
    }
}
