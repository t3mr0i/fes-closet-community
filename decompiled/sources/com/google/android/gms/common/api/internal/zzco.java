package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

/* loaded from: classes.dex */
public class zzco extends zzo {
    private TaskCompletionSource<Void> zzdzb;

    private zzco(zzcg zzcgVar) {
        super(zzcgVar);
        this.zzdzb = new TaskCompletionSource<>();
        this.zzfon.zza("GmsAvailabilityHelper", this);
    }

    public static zzco zzp(Activity activity) {
        zzcg zzcgVarZzn = zzn(activity);
        zzco zzcoVar = (zzco) zzcgVarZzn.zza("GmsAvailabilityHelper", zzco.class);
        if (zzcoVar == null) {
            return new zzco(zzcgVarZzn);
        }
        if (!zzcoVar.zzdzb.getTask().isComplete()) {
            return zzcoVar;
        }
        zzcoVar.zzdzb = new TaskCompletionSource<>();
        return zzcoVar;
    }

    public final Task<Void> getTask() {
        return this.zzdzb.getTask();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onDestroy() {
        super.onDestroy();
        this.zzdzb.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    @Override // com.google.android.gms.common.api.internal.zzo
    protected final void zza(ConnectionResult connectionResult, int i) {
        this.zzdzb.setException(com.google.android.gms.common.internal.zzb.zzx(new Status(connectionResult.getErrorCode(), connectionResult.getErrorMessage(), connectionResult.getResolution())));
    }

    @Override // com.google.android.gms.common.api.internal.zzo
    protected final void zzafw() {
        int iIsGooglePlayServicesAvailable = this.zzfhk.isGooglePlayServicesAvailable(this.zzfon.zzaik());
        if (iIsGooglePlayServicesAvailable == 0) {
            this.zzdzb.setResult(null);
        } else {
            if (this.zzdzb.getTask().isComplete()) {
                return;
            }
            zzb(new ConnectionResult(iIsGooglePlayServicesAvailable, null), 0);
        }
    }
}
