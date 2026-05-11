package com.google.android.gms.common.api.internal;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzj {
    private int zzfip;
    private final TaskCompletionSource<Void> zzfio = new TaskCompletionSource<>();
    private boolean zzfiq = false;
    private final ArrayMap<zzh<?>, ConnectionResult> zzfgi = new ArrayMap<>();

    public zzj(Iterable<? extends GoogleApi<?>> iterable) {
        Iterator<? extends GoogleApi<?>> it = iterable.iterator();
        while (it.hasNext()) {
            this.zzfgi.put(it.next().zzafk(), null);
        }
        this.zzfip = this.zzfgi.keySet().size();
    }

    public final Task<Void> getTask() {
        return this.zzfio.getTask();
    }

    public final void zza(zzh<?> zzhVar, ConnectionResult connectionResult) {
        this.zzfgi.put(zzhVar, connectionResult);
        this.zzfip--;
        if (!connectionResult.isSuccess()) {
            this.zzfiq = true;
        }
        if (this.zzfip == 0) {
            if (!this.zzfiq) {
                this.zzfio.setResult(null);
            } else {
                this.zzfio.setException(new AvailabilityException(this.zzfgi));
            }
        }
    }

    public final Set<zzh<?>> zzafx() {
        return this.zzfgi.keySet();
    }

    public final void zzafy() {
        this.zzfio.setResult(null);
    }
}
