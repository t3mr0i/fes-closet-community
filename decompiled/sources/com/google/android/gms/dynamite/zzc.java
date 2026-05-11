package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: classes.dex */
final class zzc implements DynamiteModule.zzd {
    zzc() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.zzd
    public final zzj zza(Context context, String str, zzi zziVar) throws DynamiteModule.zzc {
        zzj zzjVar = new zzj();
        zzjVar.zzgpx = zziVar.zzad(context, str);
        if (zzjVar.zzgpx != 0) {
            zzjVar.zzgpz = -1;
        } else {
            zzjVar.zzgpy = zziVar.zzb(context, str, true);
            if (zzjVar.zzgpy != 0) {
                zzjVar.zzgpz = 1;
            }
        }
        return zzjVar;
    }
}
