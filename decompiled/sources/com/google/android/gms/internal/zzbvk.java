package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;

/* loaded from: classes.dex */
public final class zzbvk {
    private boolean zzaqe = false;
    private zzbvl zzhax = null;

    public final void initialize(Context context) {
        synchronized (this) {
            if (this.zzaqe) {
                return;
            }
            try {
                this.zzhax = zzbvm.asInterface(DynamiteModule.zza(context, DynamiteModule.zzgps, ModuleDescriptor.MODULE_ID).zzgv("com.google.android.gms.flags.impl.FlagProviderImpl"));
                this.zzhax.init(com.google.android.gms.dynamic.zzn.zzw(context));
                this.zzaqe = true;
            } catch (RemoteException | DynamiteModule.zzc e) {
                Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
            }
        }
    }

    public final <T> T zzb(zzbvd<T> zzbvdVar) {
        synchronized (this) {
            if (this.zzaqe) {
                return zzbvdVar.zza(this.zzhax);
            }
            return zzbvdVar.zzil();
        }
    }
}
