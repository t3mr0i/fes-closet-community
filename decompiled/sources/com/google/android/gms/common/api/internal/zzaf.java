package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes.dex */
final class zzaf implements OnCompleteListener<Void> {
    private /* synthetic */ zzad zzfkx;

    private zzaf(zzad zzadVar) {
        this.zzfkx = zzadVar;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<Void> task) {
        this.zzfkx.zzfkd.lock();
        try {
            if (this.zzfkx.zzfks) {
                if (task.isSuccessful()) {
                    this.zzfkx.zzfkt = new ArrayMap(this.zzfkx.zzfkj.size());
                    Iterator it = this.zzfkx.zzfkj.values().iterator();
                    while (it.hasNext()) {
                        this.zzfkx.zzfkt.put(((zzac) it.next()).zzafk(), ConnectionResult.zzffe);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    if (this.zzfkx.zzfkq) {
                        this.zzfkx.zzfkt = new ArrayMap(this.zzfkx.zzfkj.size());
                        for (zzac zzacVar : this.zzfkx.zzfkj.values()) {
                            Object objZzafk = zzacVar.zzafk();
                            ConnectionResult connectionResult = availabilityException.getConnectionResult(zzacVar);
                            if (this.zzfkx.zza((zzac<?>) zzacVar, connectionResult)) {
                                this.zzfkx.zzfkt.put(objZzafk, new ConnectionResult(16));
                            } else {
                                this.zzfkx.zzfkt.put(objZzafk, connectionResult);
                            }
                        }
                    } else {
                        this.zzfkx.zzfkt = availabilityException.zzafh();
                    }
                    this.zzfkx.zzfkw = this.zzfkx.zzagr();
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    this.zzfkx.zzfkt = Collections.emptyMap();
                    this.zzfkx.zzfkw = new ConnectionResult(8);
                }
                if (this.zzfkx.zzfku != null) {
                    this.zzfkx.zzfkt.putAll(this.zzfkx.zzfku);
                    this.zzfkx.zzfkw = this.zzfkx.zzagr();
                }
                if (this.zzfkx.zzfkw == null) {
                    this.zzfkx.zzagp();
                    this.zzfkx.zzagq();
                } else {
                    zzad.zza(this.zzfkx, false);
                    this.zzfkx.zzfkm.zzc(this.zzfkx.zzfkw);
                }
                this.zzfkx.zzfko.signalAll();
            }
        } finally {
            this.zzfkx.zzfkd.unlock();
        }
    }
}
