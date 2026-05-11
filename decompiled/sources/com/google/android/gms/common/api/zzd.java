package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzcz;
import com.google.android.gms.common.internal.zzbp;

/* loaded from: classes.dex */
public final class zzd {
    private Looper zzakf;
    private zzcz zzfgt;

    public final zzd zza(Looper looper) {
        zzbp.zzb(looper, "Looper must not be null.");
        this.zzakf = looper;
        return this;
    }

    public final zzd zza(zzcz zzczVar) {
        zzbp.zzb(zzczVar, "StatusExceptionMapper must not be null.");
        this.zzfgt = zzczVar;
        return this;
    }

    public final GoogleApi.zza zzafn() {
        if (this.zzfgt == null) {
            this.zzfgt = new com.google.android.gms.common.api.internal.zzg();
        }
        if (this.zzakf == null) {
            if (Looper.myLooper() != null) {
                this.zzakf = Looper.myLooper();
            } else {
                this.zzakf = Looper.getMainLooper();
            }
        }
        return new GoogleApi.zza(this.zzfgt, this.zzakf);
    }
}
