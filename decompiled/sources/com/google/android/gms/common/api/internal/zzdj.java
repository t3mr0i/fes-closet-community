package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class zzdj {
    public static final Status zzfpp = new Status(8, "The connection to Google Play services was lost");
    private static final zzs<?>[] zzfpq = new zzs[0];
    private final Map<Api.zzc<?>, Api.zze> zzfmm;
    final Set<zzs<?>> zzfpr = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zzdm zzfps = new zzdk(this);

    public zzdj(Map<Api.zzc<?>, Api.zze> map) {
        this.zzfmm = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void release() throws RemoteException {
        com.google.android.gms.common.api.zze zzeVar = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        Object[] objArr4 = 0;
        com.google.android.gms.common.api.zze zzeVar2 = null;
        for (zzs zzsVar : (zzs[]) this.zzfpr.toArray(zzfpq)) {
            zzsVar.zza((zzdm) null);
            if (zzsVar.zzafs() != null) {
                zzsVar.setResultCallback(null);
                IBinder iBinderZzafg = this.zzfmm.get(((zzm) zzsVar).zzafe()).zzafg();
                if (zzsVar.isReady()) {
                    zzsVar.zza(new zzdl(zzsVar, objArr4 == true ? 1 : 0, iBinderZzafg, objArr3 == true ? 1 : 0));
                } else if (iBinderZzafg == null || !iBinderZzafg.isBinderAlive()) {
                    zzsVar.zza((zzdm) null);
                    zzsVar.cancel();
                    zzeVar.remove(zzsVar.zzafs().intValue());
                } else {
                    zzdl zzdlVar = new zzdl(zzsVar, objArr2 == true ? 1 : 0, iBinderZzafg, objArr == true ? 1 : 0);
                    zzsVar.zza(zzdlVar);
                    try {
                        iBinderZzafg.linkToDeath(zzdlVar, 0);
                    } catch (RemoteException e) {
                        zzsVar.cancel();
                        zzeVar2.remove(zzsVar.zzafs().intValue());
                    }
                }
                this.zzfpr.remove(zzsVar);
            } else if (zzsVar.zzagf()) {
                this.zzfpr.remove(zzsVar);
            }
        }
    }

    public final void zzair() {
        for (zzs zzsVar : (zzs[]) this.zzfpr.toArray(zzfpq)) {
            zzsVar.zzu(zzfpp);
        }
    }

    final void zzb(zzs<? extends Result> zzsVar) {
        this.zzfpr.add(zzsVar);
        zzsVar.zza(this.zzfps);
    }
}
