package com.google.android.gms.internal;

/* loaded from: classes.dex */
final class zzeey implements zzefe {
    static final zzeey zzncj = new zzeey();
    private static zzeez zznck = new zzeez();

    private zzeey() {
    }

    @Override // com.google.android.gms.internal.zzefe
    public final int zza(boolean z, int i, boolean z2, int i2) {
        if (z == z2 && i == i2) {
            return i;
        }
        throw zznck;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final zzeec zza(boolean z, zzeec zzeecVar, boolean z2, zzeec zzeecVar2) {
        if (z == z2 && zzeecVar.equals(zzeecVar2)) {
            return zzeecVar;
        }
        throw zznck;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final <T> zzefi<T> zza(zzefi<T> zzefiVar, zzefi<T> zzefiVar2) {
        if (zzefiVar.equals(zzefiVar2)) {
            return zzefiVar;
        }
        throw zznck;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final <T extends zzefq> T zza(T t, T t2) {
        if (t == null && t2 == null) {
            return null;
        }
        if (t == null || t2 == null) {
            throw zznck;
        }
        zzeev zzeevVar = (zzeev) t;
        if (zzeevVar == t2 || !((zzeev) zzeevVar.zza(zzefd.zznct, (Object) null, (Object) null)).getClass().isInstance(t2)) {
            return t;
        }
        zzeev zzeevVar2 = (zzeev) t2;
        zzeevVar.zza(zzefd.zznco, this, zzeevVar2);
        zzeevVar.zznce = zza(zzeevVar.zznce, zzeevVar2.zznce);
        return t;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final zzegi zza(zzegi zzegiVar, zzegi zzegiVar2) {
        if (zzegiVar.equals(zzegiVar2)) {
            return zzegiVar;
        }
        throw zznck;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final String zza(boolean z, String str, boolean z2, String str2) {
        if (z == z2 && str.equals(str2)) {
            return str;
        }
        throw zznck;
    }
}
