package com.google.android.gms.internal;

/* loaded from: classes.dex */
public final class zzefc implements zzefe {
    public static final zzefc zzncm = new zzefc();

    private zzefc() {
    }

    @Override // com.google.android.gms.internal.zzefe
    public final int zza(boolean z, int i, boolean z2, int i2) {
        return z2 ? i2 : i;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final zzeec zza(boolean z, zzeec zzeecVar, boolean z2, zzeec zzeecVar2) {
        return z2 ? zzeecVar2 : zzeecVar;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final <T> zzefi<T> zza(zzefi<T> zzefiVar, zzefi<T> zzefiVar2) {
        int size = zzefiVar.size();
        int size2 = zzefiVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzefiVar.zzcbr()) {
                zzefiVar = zzefiVar.zzgu(size2 + size);
            }
            zzefiVar.addAll(zzefiVar2);
        }
        return size > 0 ? zzefiVar : zzefiVar2;
    }

    @Override // com.google.android.gms.internal.zzefe
    public final <T extends zzefq> T zza(T t, T t2) {
        return (t == null || t2 == null) ? t == null ? t2 : t : (T) t.zzccw().zzc(t2).zzcdb();
    }

    @Override // com.google.android.gms.internal.zzefe
    public final zzegi zza(zzegi zzegiVar, zzegi zzegiVar2) {
        return zzegiVar2 == zzegi.zzcdq() ? zzegiVar : zzegi.zzb(zzegiVar, zzegiVar2);
    }

    @Override // com.google.android.gms.internal.zzefe
    public final String zza(boolean z, String str, boolean z2, String str2) {
        return z2 ? str2 : str;
    }
}
