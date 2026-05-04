package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import java.util.Set;

@WorkerThread
/* loaded from: classes.dex */
public interface zzcy {
    void zzb(com.google.android.gms.common.internal.zzam zzamVar, Set<Scope> set);

    void zzh(ConnectionResult connectionResult);
}
