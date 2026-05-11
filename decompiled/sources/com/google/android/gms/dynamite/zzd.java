package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzd implements DynamiteModule.zzd {
    zzd() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.zzd
    public final zzj zza(Context context, String str, zzi zziVar) throws DynamiteModule.zzc {
        zzj zzjVar = new zzj();
        zzjVar.zzgpx = zziVar.zzad(context, str);
        zzjVar.zzgpy = zziVar.zzb(context, str, true);
        if (zzjVar.zzgpx == 0 && zzjVar.zzgpy == 0) {
            zzjVar.zzgpz = 0;
        } else if (zzjVar.zzgpx >= zzjVar.zzgpy) {
            zzjVar.zzgpz = -1;
        } else {
            zzjVar.zzgpz = 1;
        }
        return zzjVar;
    }
}
