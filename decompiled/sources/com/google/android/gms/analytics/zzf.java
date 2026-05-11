package com.google.android.gms.analytics;

import java.util.Comparator;

/* loaded from: classes.dex */
final class zzf implements Comparator<zzh> {
    zzf(zze zzeVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzh zzhVar, zzh zzhVar2) {
        return zzhVar.getClass().getCanonicalName().compareTo(zzhVar2.getClass().getCanonicalName());
    }
}
