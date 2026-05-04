package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes.dex */
public class zzak extends zzo {
    private zzbp zzfgu;
    private final ArraySet<zzh<?>> zzfld;

    private zzak(zzcg zzcgVar) {
        super(zzcgVar);
        this.zzfld = new ArraySet<>();
        this.zzfon.zza("ConnectionlessLifecycleHelper", this);
    }

    public static void zza(Activity activity, zzbp zzbpVar, zzh<?> zzhVar) {
        zzn(activity);
        zzcg zzcgVarZzn = zzn(activity);
        zzak zzakVar = (zzak) zzcgVarZzn.zza("ConnectionlessLifecycleHelper", zzak.class);
        if (zzakVar == null) {
            zzakVar = new zzak(zzcgVarZzn);
        }
        zzakVar.zzfgu = zzbpVar;
        com.google.android.gms.common.internal.zzbp.zzb(zzhVar, "ApiKey cannot be null");
        zzakVar.zzfld.add(zzhVar);
        zzbpVar.zza(zzakVar);
    }

    private final void zzagw() {
        if (this.zzfld.isEmpty()) {
            return;
        }
        this.zzfgu.zza(this);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onResume() {
        super.onResume();
        zzagw();
    }

    @Override // com.google.android.gms.common.api.internal.zzo, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStart() {
        super.onStart();
        zzagw();
    }

    @Override // com.google.android.gms.common.api.internal.zzo, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        super.onStop();
        this.zzfgu.zzb(this);
    }

    @Override // com.google.android.gms.common.api.internal.zzo
    protected final void zza(ConnectionResult connectionResult, int i) {
        this.zzfgu.zza(connectionResult, i);
    }

    @Override // com.google.android.gms.common.api.internal.zzo
    protected final void zzafw() {
        this.zzfgu.zzafw();
    }

    final ArraySet<zzh<?>> zzagv() {
        return this.zzfld;
    }
}
