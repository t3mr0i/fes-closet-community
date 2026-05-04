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
final class zzag implements OnCompleteListener<Void> {
    private /* synthetic */ zzad zzfkx;
    private zzcv zzfky;

    zzag(zzad zzadVar, zzcv zzcvVar) {
        this.zzfkx = zzadVar;
        this.zzfky = zzcvVar;
    }

    final void cancel() {
        this.zzfky.zzaak();
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<Void> task) {
        this.zzfkx.zzfkd.lock();
        try {
            if (!this.zzfkx.zzfks) {
                this.zzfky.zzaak();
                return;
            }
            if (task.isSuccessful()) {
                this.zzfkx.zzfku = new ArrayMap(this.zzfkx.zzfkk.size());
                Iterator it = this.zzfkx.zzfkk.values().iterator();
                while (it.hasNext()) {
                    this.zzfkx.zzfku.put(((zzac) it.next()).zzafk(), ConnectionResult.zzffe);
                }
            } else if (task.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task.getException();
                if (this.zzfkx.zzfkq) {
                    this.zzfkx.zzfku = new ArrayMap(this.zzfkx.zzfkk.size());
                    for (zzac zzacVar : this.zzfkx.zzfkk.values()) {
                        Object objZzafk = zzacVar.zzafk();
                        ConnectionResult connectionResult = availabilityException.getConnectionResult(zzacVar);
                        if (this.zzfkx.zza((zzac<?>) zzacVar, connectionResult)) {
                            this.zzfkx.zzfku.put(objZzafk, new ConnectionResult(16));
                        } else {
                            this.zzfkx.zzfku.put(objZzafk, connectionResult);
                        }
                    }
                } else {
                    this.zzfkx.zzfku = availabilityException.zzafh();
                }
            } else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                this.zzfkx.zzfku = Collections.emptyMap();
            }
            if (this.zzfkx.isConnected()) {
                this.zzfkx.zzfkt.putAll(this.zzfkx.zzfku);
                if (this.zzfkx.zzagr() == null) {
                    this.zzfkx.zzagp();
                    this.zzfkx.zzagq();
                    this.zzfkx.zzfko.signalAll();
                }
            }
            this.zzfky.zzaak();
        } finally {
            this.zzfkx.zzfkd.unlock();
        }
    }
}
