package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzdbq;
import com.google.android.gms.internal.zzdbu;
import java.util.Set;

/* loaded from: classes.dex */
final class zzfg implements zzfh {
    zzfg(zzfc zzfcVar) {
    }

    @Override // com.google.android.gms.tagmanager.zzfh
    public final void zza(zzdbu zzdbuVar, Set<zzdbq> set, Set<zzdbq> set2, zzer zzerVar) {
        set.addAll(zzdbuVar.zzbhi());
        set2.addAll(zzdbuVar.zzbhj());
        zzerVar.zzbea();
        zzerVar.zzbeb();
    }
}
